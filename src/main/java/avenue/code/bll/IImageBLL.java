package avenue.code.bll;

import java.util.List;

import avenue.code.entity.ImageEntity;

public interface IImageBLL {

	List<ImageEntity> listImages();
	
	List<ImageEntity> listImages(Long idProduct);
}
