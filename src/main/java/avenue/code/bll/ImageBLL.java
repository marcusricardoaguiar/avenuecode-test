package avenue.code.bll;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import avenue.code.dao.IImageDAO;
import avenue.code.dao.IProductDAO;
import avenue.code.entity.ImageEntity;
import avenue.code.entity.ProductEntity;
import avenue.code.errors.ErrorTypeEnum;
import avenue.code.errors.ProductException;

@Component
public class ImageBLL implements IImageBLL {

	@Autowired
	private IProductDAO productDAO;
	
	@Autowired
	private IImageDAO imageDAO;

	@Override
	public List<ImageEntity> listImages() {
		return imageDAO.findAll();
	}
	
	@Override
	public List<ImageEntity> listImages(Long idProduct) {
		List<ImageEntity> images = new ArrayList<ImageEntity>();
		try {
			ProductEntity productEntity = productDAO.getById(idProduct);
			if(productEntity == null)
				throw new ProductException(ErrorTypeEnum.NOT_FOUND, "Product not found!", "id: " + idProduct);
			
			images = imageDAO.findAllByProductId(idProduct);
		} catch (NoResultException e){
			throw new ProductException(ErrorTypeEnum.NOT_FOUND, "Product not found!", "id: " + idProduct);
		}
		return images;
	}
}
