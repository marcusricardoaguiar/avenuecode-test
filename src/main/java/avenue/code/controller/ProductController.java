package avenue.code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import avenue.code.bll.IImageBLL;
import avenue.code.bll.IProductBLL;
import avenue.code.errors.ProductException;
import avenue.code.model.ImageModel;
import avenue.code.model.GETProductModel;
import avenue.code.model.ModelFactory;
import avenue.code.model.POSTProductModel;

@Component
public class ProductController implements IProductController {

	@Autowired
	private IProductBLL productBLL;
	
	@Autowired
	private IImageBLL imageBLL;
	
	@Override
	public List<GETProductModel> listProducts(boolean childs, boolean images) {
		
		return ModelFactory.convert(productBLL.listProducts(), childs, images, true);
	}

	@Override
	public GETProductModel getProduct(Long idProduct, boolean childs, boolean images) throws ProductException {
		return ModelFactory.convert(productBLL.getProduct(idProduct), childs, images, true);
	}

	@Override
	public GETProductModel createProduct(POSTProductModel product) throws ProductException {
		return ModelFactory.convert(productBLL.createProduct(product), true, true, true);
	}

	@Override
	public GETProductModel updateProduct(Long idProduct, POSTProductModel product) throws ProductException {
		return ModelFactory.convert(productBLL.updateProduct(idProduct, product), true, true, true);
	}

	@Override
	public void deleteProduct(Long idProduct) throws ProductException {
		productBLL.deleteProduct(idProduct);
	}

	@Override
	public List<GETProductModel> listChilds(Long idProduct) throws ProductException {
		return ModelFactory.convert(productBLL.listChilds(idProduct), true, false, false);
	}

	@Override
	public List<ImageModel> listImages(Long idProduct) throws ProductException {
		return ModelFactory.convert(imageBLL.listImages(idProduct), false, true, false);
	}
}
