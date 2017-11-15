package avenue.code.controller;

import java.util.List;

import avenue.code.errors.ProductException;
import avenue.code.model.ImageModel;
import avenue.code.model.GETProductModel;
import avenue.code.model.POSTProductModel;

public interface IProductController {

	List<GETProductModel> listProducts(boolean childs, boolean images);

	GETProductModel getProduct(Long idProduct, boolean childs, boolean images) throws ProductException;

	GETProductModel createProduct(POSTProductModel product) throws ProductException;

	GETProductModel updateProduct(Long idProduct, POSTProductModel product) throws ProductException;

	void deleteProduct(Long idProduct) throws ProductException;

	List<GETProductModel> listChilds(Long idProduct);

	List<ImageModel> listImages(Long idProduct);

}
