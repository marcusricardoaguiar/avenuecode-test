package avenue.code.bll;

import java.util.List;

import avenue.code.entity.ProductEntity;
import avenue.code.errors.ProductException;
import avenue.code.model.POSTProductModel;

public interface IProductBLL {

	List<ProductEntity> listProducts();

	ProductEntity getProduct(Long idProduct) throws ProductException;

	ProductEntity createProduct(POSTProductModel product) throws ProductException;

	ProductEntity updateProduct(Long idProduct, POSTProductModel product) throws ProductException;

	void deleteProduct(Long idProduct) throws ProductException;

	List<ProductEntity> listChilds(Long idProduct) throws ProductException;
}
