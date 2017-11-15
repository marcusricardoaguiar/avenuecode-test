package avenue.code.dao;

import java.util.List;

import avenue.code.entity.ProductEntity;

public interface IProductDAO {

	List<ProductEntity> findAll();

	ProductEntity getById(Long idProduct);

	ProductEntity create(ProductEntity productEntity);

	ProductEntity update(ProductEntity productEntity);

	void delete(ProductEntity productEntity);

	List<ProductEntity> findAllChilds(Long idProduct);
	
}
