package avenue.code;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import avenue.code.enumerators.ImageTypeEnum;
import avenue.code.model.ImageModel;
import avenue.code.model.GETProductModel;
import avenue.code.model.POSTProductModel;
import avenue.code.rest.ProductRestAPI;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CodingTestApplicationTests {
	
	/*
	 * PLANNING TESTS
	 * 	
	 * 	PRE TESTS
	 * 		* pre-tests insert some images and products with childs and images relationships.
	 * 	
	 * 	GET TESTS
	 * 		* list products with relationships
	 * 		* list products without relationships
	 * 		* list products with images relationships
	 * 		* list products with childs relationships
	 * 		* get a product with relationships
	 * 		* get a product with images relationships
	 * 		* get a product with childs relationships
	 * 		* get a product without relationships
	 * 		* get childs of a product
	 * 		* get images of a product
	 * 
	 * 	POST TESTS
	 * 		* create a product with 0 image without parent
	 * 		* create a product with 1 image without parent
	 * 		* create a product with 1 image and a parent
	 * 		* create a product with 2 image and a parent
	 * 
	 * 	PUT TESTS
	 * 		* update a product removing images relationships
	 * 		* update a product removing parent relationship
	 * 		* update a product changing parent relationship
	 * 		* update a product adding and removing images relationships
	 * 
	 * 	DELETE TESTS
	 * 		* delete a product without childs
	 */
	
	@Autowired
	private ProductRestAPI resource;
	
	private static final int numberInitialOfObject = 5;
	
	@Test
	@SuppressWarnings("unchecked")
	public void t01_listProductsWithRelationships() throws Exception {
		
		Response response = resource.listProducts(true, true);
		List<GETProductModel> products = (List<GETProductModel>) response.getEntity();
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(products.size(), numberInitialOfObject);
		
		assertEquals(1, products.get(0).getImages().size());
		assertEquals(2, products.get(2).getImages().size());
		assertEquals(null, products.get(3).getImages());
		
		assertEquals(null, products.get(0).getChilds());
		assertEquals(2, products.get(2).getChilds().size());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void t02_listProductsWithoutRelationships() throws Exception {
		
		Response response = resource.listProducts(false, false);
		List<GETProductModel> products = (List<GETProductModel>) response.getEntity();
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(products.size(), numberInitialOfObject);
		
		assertEquals(null, products.get(0).getImages());
		assertEquals(null, products.get(1).getImages());
		assertEquals(null, products.get(3).getImages());
		
		assertEquals(null, products.get(0).getChilds());
		assertEquals(null, products.get(2).getChilds());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void t03_listProductsWithImagesRelationships() throws Exception {
		
		Response response = resource.listProducts(false, true);
		List<GETProductModel> products = (List<GETProductModel>) response.getEntity();
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(products.size(), numberInitialOfObject);
		
		assertEquals(1, products.get(0).getImages().size());
		assertEquals(2, products.get(2).getImages().size());
		assertEquals(null, products.get(3).getImages());
		
		assertEquals(null, products.get(0).getChilds());
		assertEquals(null, products.get(2).getChilds());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void t04_listProductsWithChildsRelationships() throws Exception {
		
		Response response = resource.listProducts(true, false);
		List<GETProductModel> products = (List<GETProductModel>) response.getEntity();
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(products.size(), numberInitialOfObject);
		
		assertEquals(null, products.get(0).getImages());
		assertEquals(null, products.get(1).getImages());
		assertEquals(null, products.get(3).getImages());
		
		assertEquals(null, products.get(0).getChilds());
		assertEquals(2, products.get(2).getChilds().size());
	}
	
	@Test
	public void t05_getAProductWithRelationships() throws Exception {
		
		Long getProduct = (long) 3;
		Response response = resource.getProduct(getProduct, true, true);
		GETProductModel product = (GETProductModel) response.getEntity();
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(product.getId(), getProduct);
		
		assertEquals(2, product.getImages().size());
		assertEquals(2, product.getChilds().size());
	}
	
	@Test
	public void t06_getAProductWithImagesRelationships() throws Exception {
		
		Long getProduct = (long) 3;
		Response response = resource.getProduct(getProduct, false, true);
		GETProductModel product = (GETProductModel) response.getEntity();
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(product.getId(), getProduct);
		
		assertEquals(2, product.getImages().size());
		assertEquals(null, product.getChilds());
	}
	
	@Test
	public void t07_getAProductWithChildsRelationships() throws Exception {
		
		Long getProduct = (long) 3;
		Response response = resource.getProduct(getProduct, true, false);
		GETProductModel product = (GETProductModel) response.getEntity();
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(product.getId(), getProduct);
		
		assertEquals(null, product.getImages());
		assertEquals(2, product.getChilds().size());
	}
	
	@Test
	public void t08_getAProductWithoutRelationships() throws Exception {
		
		Long getProduct = (long) 3;
		Response response = resource.getProduct(getProduct, false, false);
		GETProductModel product = (GETProductModel) response.getEntity();
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(product.getId(), getProduct);
		
		assertEquals(null, product.getImages());
		assertEquals(null, product.getChilds());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void t09_getChildsOfAProduct() throws Exception {
		
		Long getProduct1 = (long) 3, getProduct2 = (long) 4;
		Response response = resource.listChilds(getProduct1);
		List<GETProductModel> childs = (List<GETProductModel>) response.getEntity();
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(2, childs.size());
		
		response = resource.listChilds(getProduct2);
		childs = (List<GETProductModel>) response.getEntity();
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(0, childs.size());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void t10_getImagesOfAProduct() throws Exception {
		
		Long getProduct1 = (long) 3, getProduct2 = (long) 4;
		Response response = resource.listImages(getProduct1);
		List<GETProductModel> images = (List<GETProductModel>) response.getEntity();
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(2, images.size());
		
		response = resource.listImages(getProduct2);
		images = (List<GETProductModel>) response.getEntity();
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(0, images.size());
	}
	
	@Test
	public void t11_createProductWithoutRelationships() throws Exception {
		
		POSTProductModel product = new POSTProductModel();
		product.setName("Product 6");
		product.setDescription("");
		Response response = resource.createProduct(product);
		GETProductModel resultProduct = (GETProductModel) response.getEntity();
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(resultProduct.getName(), product.getName());
		assertNotNull(resultProduct.getId());
		
		assertEquals(null, resultProduct.getImages());
		assertEquals(null, resultProduct.getParent());
	}
	
	@Test
	public void t12_createProductWithOneImageRelationship() throws Exception {
		
		ImageTypeEnum imageType = ImageTypeEnum.JPEG;
		
		POSTProductModel product = new POSTProductModel();
		product.setName("Product 7");
		product.setDescription("OneImage");
		List<ImageModel> images = new ArrayList<ImageModel>();
		images.add(new ImageModel(imageType));
		product.setImages(images);
		Response response = resource.createProduct(product);
		GETProductModel resultProduct = (GETProductModel) response.getEntity();
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(resultProduct.getName(), product.getName());
		assertNotNull(resultProduct.getId());
		assertEquals(1, resultProduct.getImages().size());
		assertEquals(null, resultProduct.getParent());
	}
	
	@Test
	public void t13_createProductWithOneImageAParentRelationship() throws Exception {
		
		ImageTypeEnum imageType = ImageTypeEnum.JPEG;
		Long parentId = (long)2;
		
		POSTProductModel product = new POSTProductModel();
		product.setName("Product 8");
		product.setDescription("OneImageAParent");
		List<ImageModel> images = new ArrayList<ImageModel>();
		images.add(new ImageModel(imageType));
		product.setImages(images);
		product.setParent((long)2);
		Response response = resource.createProduct(product);
		GETProductModel resultProduct = (GETProductModel) response.getEntity();
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(resultProduct.getName(), product.getName());
		assertNotNull(resultProduct.getId());
		assertEquals(1, resultProduct.getImages().size());
		assertNotNull(resultProduct.getParent());
		assertEquals(parentId, resultProduct.getParent().getId());
	}
	
	@Test
	public void t14_createProductWithTwoImagesAParentRelationship() throws Exception {
		
		ImageTypeEnum imageType1 = ImageTypeEnum.JPEG, imageType2 = ImageTypeEnum.JPG;
		Long parentId = (long)4;
		
		POSTProductModel product = new POSTProductModel();
		product.setName("Product 9");
		product.setDescription("OneImageAParent");
		List<ImageModel> images = new ArrayList<ImageModel>();
		images.add(new ImageModel(imageType1));
		images.add(new ImageModel(imageType2));
		product.setImages(images);
		product.setParent(parentId);
		Response response = resource.createProduct(product);
		GETProductModel resultProduct = (GETProductModel) response.getEntity();
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(resultProduct.getName(), product.getName());
		assertNotNull(resultProduct.getId());
		assertEquals(2, resultProduct.getImages().size());
		assertNotNull(resultProduct.getParent());
		assertEquals(parentId, resultProduct.getParent().getId());
	}
	
	@Test
	public void t15_updateProductChangingParentRelationship() throws Exception {
		
		Long productId = (long)9;
		Long parentId = (long)1;
		
		Response response = resource.getProduct(productId, true, true);
		GETProductModel originalProduct = (GETProductModel) response.getEntity();
		POSTProductModel product = new POSTProductModel();
		product.setName(originalProduct.getName() + " altered");
		product.setDescription(originalProduct.getDescription());
		if(originalProduct.getImages() != null)
			product.setImages(originalProduct.getImages());
		product.setParent(parentId);
		
		response = resource.updateProduct(productId, product);
		GETProductModel resultProduct = (GETProductModel) response.getEntity();
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertNotEquals(resultProduct.getName(), originalProduct.getName());
		assertNotNull(resultProduct.getId());
		assertEquals(originalProduct.getImages().stream().map(ImageModel::getId).collect(Collectors.toList()),
				resultProduct.getImages().stream().map(ImageModel::getId).collect(Collectors.toList()));
		assertNotNull(resultProduct.getParent());
		assertEquals(parentId, resultProduct.getParent().getId());
	}
	
	@Test
	public void t16_updateProductRemovingParentRelationship() throws Exception {
		
		Long productId = (long)8;
		
		Response response = resource.getProduct(productId, true, true);
		GETProductModel originalProduct = (GETProductModel) response.getEntity();
		POSTProductModel product = new POSTProductModel();
		product.setName(originalProduct.getName() + " altered");
		product.setDescription(originalProduct.getDescription());
		if(originalProduct.getImages() != null)
			product.setImages(originalProduct.getImages());
		product.setParent(null);
		
		response = resource.updateProduct(productId, product);
		GETProductModel resultProduct = (GETProductModel) response.getEntity();
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertNotEquals(resultProduct.getName(), originalProduct.getName());
		assertNotNull(resultProduct.getId());
		assertEquals(originalProduct.getImages().stream().map(ImageModel::getId).collect(Collectors.toList()),
				resultProduct.getImages().stream().map(ImageModel::getId).collect(Collectors.toList()));
		assertEquals(null, resultProduct.getParent());
	}
	
	@Test
	public void t17_updateProductRemovingImagesRelationships() throws Exception {
		
		Long productId = (long)9;
		
		Response response = resource.getProduct(productId, true, true);
		GETProductModel originalProduct = (GETProductModel) response.getEntity();
		POSTProductModel product = new POSTProductModel();
		product.setName(originalProduct.getName() + " altered");
		product.setDescription(originalProduct.getDescription());
		product.setImages(new ArrayList<ImageModel>());
		product.setParent(originalProduct.getParent().getId());
		
		response = resource.updateProduct(productId, product);
		GETProductModel resultProduct = (GETProductModel) response.getEntity();
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertNotEquals(resultProduct.getName(), originalProduct.getName());
		assertNotNull(resultProduct.getId());
		assertEquals(null, resultProduct.getImages());
		assertNotNull(resultProduct.getParent());
	}
	
	@Test
	public void t18_updateProductChangingParentRelationship() throws Exception {
		
		Long productId = (long)5;
		Long parentId = (long)1;
		
		Response response = resource.getProduct(productId, true, true);
		GETProductModel originalProduct = (GETProductModel) response.getEntity();
		POSTProductModel product = new POSTProductModel();
		product.setName(originalProduct.getName() + " altered");
		product.setDescription(originalProduct.getDescription());
		if(originalProduct.getImages() != null){
			product.setImages(new ArrayList<ImageModel>());
			ImageModel newImage = new ImageModel();
			for(ImageModel image : originalProduct.getImages()){
				BeanUtils.copyProperties(image, newImage);
				product.getImages().add(newImage);
				newImage = new ImageModel();
			}
			product.getImages().remove(0);
		}
		product.getImages().add(new ImageModel(ImageTypeEnum.BPM));
		product.getImages().add(new ImageModel(ImageTypeEnum.JPEG));
		product.setParent(parentId);
		
		response = resource.updateProduct(productId, product);
		GETProductModel resultProduct = (GETProductModel) response.getEntity();
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertNotNull(resultProduct.getId());
		assertNotEquals(originalProduct.getImages().size(), resultProduct.getImages().size());
		assertNotNull(resultProduct.getParent());
		assertEquals(parentId, resultProduct.getParent().getId());
	}
	
	@Test
	public void t19_removeProduct() throws Exception {
		
		Long removeProduct = (long) 8;
		Response response = resource.deleteProduct(removeProduct);
		
		assertEquals(response.getStatus(), HttpStatus.OK.value());
	}
}
