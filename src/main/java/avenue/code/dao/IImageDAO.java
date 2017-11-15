package avenue.code.dao;

import java.util.List;

import avenue.code.entity.ImageEntity;

public interface IImageDAO {

	List<ImageEntity> findAll();
	
	List<ImageEntity> findAllByProductId(Long idProduct);
	
	ImageEntity findById(Long idImage);
	
	ImageEntity create(ImageEntity imageEntity);

	ImageEntity update(ImageEntity imageEntity);

	void delete(ImageEntity imageEntity);
}
