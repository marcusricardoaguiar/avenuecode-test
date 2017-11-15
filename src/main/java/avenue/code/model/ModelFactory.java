package avenue.code.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import avenue.code.entity.ImageEntity;
import avenue.code.entity.ProductEntity;

public abstract class ModelFactory {
	
	/*
	 * Used to transform Entities into POJO models.
	 */

	/*
	 * Transform a list of Entities into a list of POJOs.
	 * The boolean parameters can be used to show/hide some properties like childs, images and parent.
	 * If true, the transform will show the properties. If not, the transform method will hide the properties. 
	 */
	@SuppressWarnings("unchecked")
	public static <T, Y> List<Y> convert(List<T> listEntity, boolean getChilds, boolean getImages, boolean getParent) {

		List<Y> listModel = new ArrayList<Y>();
		
		for(T objectEntity : listEntity){
			if(objectEntity instanceof ProductEntity)
				listModel.add((Y)convert((ProductEntity)objectEntity, getChilds, getImages, getParent));
			if(getImages && objectEntity instanceof ImageEntity)
				listModel.add((Y)convert((ImageEntity)objectEntity));
		}
		
		return listModel;
	}
	
	public static GETProductModel convert(ProductEntity productEntity, boolean getChilds, boolean getImages, boolean getParent){
		
		GETProductModel product = new GETProductModel();
		product.setId(productEntity.getId());
		product.setName(productEntity.getName());
		product.setDescription(productEntity.getDescription());
		
		// convert parent
		if(getParent && productEntity.getParent() != null)
			product.setParent(ModelFactory.convert(productEntity.getParent(), false, false, false));
		
		// convert images
		if(getImages && productEntity.getImages() != null && !productEntity.getImages().isEmpty())
			product.setImages(convert(productEntity.getImages().stream().collect(Collectors.toList()), false, true, false));
		
		// convert childs
		if(getChilds && productEntity.getChildProducts() != null && !productEntity.getChildProducts().isEmpty())
			product.setChilds(convert(productEntity.getChildProducts().stream().collect(Collectors.toList()), false, false, false));
		
		return product;
	}
	
	public static ImageModel convert(ImageEntity imageEntity){
		ImageModel image = new ImageModel();
		BeanUtils.copyProperties(imageEntity, image);
		return image;
	}
}
