package avenue.code.bll;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import avenue.code.dao.IImageDAO;
import avenue.code.dao.IProductDAO;
import avenue.code.entity.ImageEntity;
import avenue.code.entity.ProductEntity;
import avenue.code.errors.ErrorTypeEnum;
import avenue.code.errors.ProductException;
import avenue.code.model.ImageModel;
import avenue.code.model.POSTProductModel;

@Component
public class ProductBLL implements IProductBLL {

	@Autowired
	private IProductDAO productDAO;
	
	@Autowired
	private IImageDAO imageDAO;

	@Override
	public List<ProductEntity> listProducts() {
		return productDAO.findAll();
	}

	@Override
	public ProductEntity getProduct(Long idProduct) throws ProductException{
		ProductEntity product = new ProductEntity(); 
		try {
			product = productDAO.getById(idProduct);
		} catch (NoResultException e){
			throw new ProductException(ErrorTypeEnum.NOT_FOUND, "Product not found!", "id: " + idProduct);
		}
		return product;
	}

	@Override
	public ProductEntity createProduct(POSTProductModel product) throws ProductException {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setName(product.getName());
		productEntity.setDescription(product.getDescription());
		
		// getting parent
		if(product.getParent() != null){
			try {
				ProductEntity parentEntity = productDAO.getById(product.getParent());
				productEntity.setParent(parentEntity);
			} catch (NoResultException e){
				throw new ProductException(ErrorTypeEnum.INVALID_PARAMETER, "Parent Product not found!", "parent: " + product.getParent());
			}
		}
		
		// getting images
		if(product.getImages() != null){
			Set<ImageEntity> imagesEntities = new HashSet<ImageEntity>();
			for(ImageModel image : product.getImages()){
				ImageEntity imageEntity = new ImageEntity();
				imageEntity.setProduct(productEntity);
				imageEntity.setType(image.getType());
				imagesEntities.add(imageEntity);
			}
			productEntity.setImages(imagesEntities);
		}
		
		// persist product
		productEntity = productDAO.create(productEntity);
		
		return productEntity;
	}

	@Override
	public ProductEntity updateProduct(Long idProduct, POSTProductModel product) throws ProductException {
		ProductEntity productEntity = productDAO.getById(idProduct);
		
		// updating if changed
		if(product.getName() != null && !productEntity.getName().isEmpty())
			productEntity.setName(product.getName());
		if(product.getDescription() != null && !productEntity.getDescription().isEmpty())
			productEntity.setDescription(product.getDescription());
		
		// updating parent
		if(product.getParent() != null){
			try {
				ProductEntity parentEntity = productDAO.getById(product.getParent());
				productEntity.setParent(parentEntity);
			} catch (NoResultException e){
				throw new ProductException(ErrorTypeEnum.INVALID_PARAMETER, "Parent Product not found!", "parent: " + product.getParent());
			}
		} else {
			productEntity.setParent(null);
		}

		// updating images
		if(product.getImages() != null){
			
			Set<ImageEntity> imagesCreated = new HashSet<ImageEntity>();
			Set<ImageEntity> imagesToRemove = new HashSet<ImageEntity>();
			List<ImageEntity> productImages = imageDAO.findAllByProductId(idProduct);
			
			// identifying images to delete  
			if(productImages != null){
				List<ImageModel> images = product.getImages();
				List<Long> imageIds = images.stream().map(ImageModel::getId).collect(Collectors.toList());
				for(ImageEntity image : productEntity.getImages()){
					if(!imageIds.contains(image.getId())){
						imagesToRemove.add(image);
						imageDAO.delete(image);
					} else
						imagesCreated.add(image);
				}
			}
			if(imagesToRemove.size() > 0)
				productEntity.getImages().removeAll(imagesToRemove);
			
			// identifying images to create
			Set<ImageEntity> imagesEntities = new HashSet<ImageEntity>();
			List<Long> imagesCreatedIds = imagesCreated.stream().map(ImageEntity::getId).collect(Collectors.toList());
			for(ImageModel image : product.getImages()){
				if(image.getId() == null || !imagesCreatedIds.contains(image.getId())){
					ImageEntity imageEntity = new ImageEntity();
					imageEntity.setProduct(productEntity);
					imageEntity.setType(image.getType());
					imagesEntities.add(imageEntity);
					productEntity.getImages().add(imageEntity);
				}
			}
		}
		
		// persist changes
		return productDAO.update(productEntity);
	}

	@Override
	public void deleteProduct(Long idProduct) throws ProductException {
		ProductEntity productEntity = new ProductEntity();
		try {
			productEntity = productDAO.getById(idProduct);
			
			// delete product is only allow if the product hadn't childs 
			List<ProductEntity> childs = productDAO.findAllChilds(idProduct);
			if(childs != null && childs.size() > 0)
				throw new ProductException(ErrorTypeEnum.INTEGRITY_VIOLATION, "Product has childs!", "childs size: " + childs.size());
			if(productEntity.getImages() != null)
				productEntity.getImages().stream().forEach(i -> imageDAO.delete(i));
			
			// persist delete
			productDAO.delete(productEntity);
		} catch (NoResultException e){
			throw new ProductException(ErrorTypeEnum.NOT_FOUND, "Product not found!", "id: " + idProduct);
		}
	}

	@Override
	public List<ProductEntity> listChilds(Long idProduct) throws ProductException {
		List<ProductEntity> products = new ArrayList<ProductEntity>();
		try {
			ProductEntity productEntity = productDAO.getById(idProduct);
			if(productEntity == null)
				throw new ProductException(ErrorTypeEnum.NOT_FOUND, "Product not found!", "id: " + idProduct);
			
			products = productDAO.findAllChilds(idProduct);
		} catch (NoResultException e){
			throw new ProductException(ErrorTypeEnum.NOT_FOUND, "Product not found!", "id: " + idProduct);
		}
		return products;
	}
}