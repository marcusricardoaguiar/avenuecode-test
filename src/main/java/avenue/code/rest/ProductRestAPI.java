package avenue.code.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import avenue.code.controller.IProductController;
import avenue.code.errors.ProductException;
import avenue.code.model.ImageModel;
import avenue.code.model.GETProductModel;
import avenue.code.model.POSTProductModel;
import ch.qos.logback.core.status.Status;

@Component
@Path("/api/rest/products")
public class ProductRestAPI {
	
	@Autowired
	private IProductController controller;

	/*
	 * The parameters childs and images can give you two functionalities below:
	 * 		a) Get all products excluding relationships (child products, images)
	 *		b) Get all products including specified relationships (child product and/or images)
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listProducts(@DefaultValue("true") @QueryParam("childs") boolean childs,
								 @DefaultValue("true") @QueryParam("images") boolean images) {
		
		List<GETProductModel> products = new ArrayList<GETProductModel>();
		try {
			products = controller.listProducts(childs, images);
		} catch (ProductException pe){
			return Response.ok(pe.showError()).build();
		} catch (Exception e){
			return Response.status(Status.ERROR).build();
		}
		return Response.ok(products).build();		
	}
	
	/*
	 * The parameters childs and images can give you two functionalities below:
	 * 		c) Same as 1 using specific product identity
	 *		d) Same as 2 using specific product identity
	 */
	@GET
	@Path("/{idProduct}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@NotNull @PathParam("idProduct") Long idProduct,
							   @DefaultValue("true") @QueryParam("childs") boolean childs,
							   @DefaultValue("true") @QueryParam("images") boolean images) {
		
		GETProductModel product = new GETProductModel(); 
		try {
			product = controller.getProduct(idProduct, childs, images);
		} catch (ProductException pe){
			return Response.ok(pe.showError()).build();
		} catch (Exception e){
			return Response.status(Status.ERROR).build();
		}
		return Response.ok(product).build();
	}
	
	/*
	 * Pass the id to get childs of a product
	 * 		e) Get set of child products for specific product
	 */
	@GET
	@Path("/{idProduct}/childs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listChilds(@PathParam("idProduct") Long idProduct) {
		
		List<GETProductModel> products = new ArrayList<GETProductModel>(); 
		try {
			products = controller.listChilds(idProduct);
		} catch (ProductException pe){
			return Response.ok(pe.showError()).build();
		} catch (Exception e){
			return Response.status(Status.ERROR).build();
		}
		return Response.ok(products).build();
	}
	
	/*
	 * Pass the id to get images of a product
	 * 		f) Get set of images for specific product
	 */
	@GET
	@Path("/{idProduct}/images")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listImages(@PathParam("idProduct") Long idProduct) {
		
		List<ImageModel> images = new ArrayList<ImageModel>();
		try {
			images = controller.listImages(idProduct);
		} catch (ProductException pe){
			return Response.ok(pe.showError()).build();
		} catch (Exception e){
			return Response.status(Status.ERROR).build();
		}
		return Response.ok(images).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(POSTProductModel product) {
		
		GETProductModel productModel = new GETProductModel();
		try {
			productModel = controller.createProduct(product);
		} catch (ProductException pe){
			return Response.ok(pe.showError()).build();
		} catch (Exception e){
			return Response.status(Status.ERROR).build();
		}
		return Response.ok(productModel).build();
	}
	
	@PUT
	@Path("/{idProduct}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduct(@NotNull @PathParam("idProduct") Long idProduct,
								  POSTProductModel product) {
		
		GETProductModel productModel = new GETProductModel();
		try {
			productModel = controller.updateProduct(idProduct, product);
		} catch (ProductException pe){
			return Response.ok(pe.showError()).build();
		} catch (Exception e){
			return Response.status(Status.ERROR).build();
		}
		return Response.ok(productModel).build();
	}
	
	@DELETE
	@Path("/{idProduct}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteProduct(@PathParam("idProduct") Long idProduct) {
		
		try {
			controller.deleteProduct(idProduct);
		} catch (ProductException pe){
			return Response.ok(pe.showError()).build();
		} catch (Exception e){
			return Response.status(Status.ERROR).build();
		}
		return Response.ok().build();		
	}
}