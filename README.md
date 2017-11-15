# README #

### COMPILE AND RUN ###

To compile and run the application, execute the command below:

	mvn clean install package test spring-boot:run

Get all products excluding relationships (child products, images)

	GET: http://localhost:8080/api/rest/products?images=false&childs=false

Get all products including specified relationships (child product and/or images)

	GET: http://localhost:8080/api/rest/products?images=true&childs=true

Same as 1 using specific product identity (idProduct = 3)

	GET: http://localhost:8080/api/rest/products/3?images=false&childs=false

Same as 2 using specific product identity (idProduct = 3)

	GET: http://localhost:8080/api/rest/products/3?images=true&childs=true

Get set of child products for specific product (idProduct = 3)

	GET: http://localhost:8080/api/rest/products/3/childs

Get set of images for specific product (idProduct = 3)

	GET: http://localhost:8080/api/rest/products/3/images

Creating a product

	POST: http://localhost:8080/api/rest/products
	Header: Content-Type: application/json
	Body: {
		"name": "Fogão 2",
		"description": "Eletro-Doméstico",
		"images": [{
			"type":"JPEG"
		}, {
			"type":"JPEG"
		}, {
			"type":"PNG"
		}],
		"parent": 4
	}
	
Updating a product

	PUT: http://localhost:8080/api/rest/products/1
	Header: Content-Type: application/json
	Body: {
		"id": 1,
		"name": "Product 1",
		"description": "OneImage",
		"parent": 2,
		"images": [{
			"id": 1,
			"type": "GIF"
		}]
	      }

Removing a product

	DELETE: http://localhost:8080/api/rest/products/4
			
If you prefer, you can use Postman (https://www.getpostman.com/) and/or Swagger (http://editor.swagger.io/#/) files attachment to see documentation API.

	SWAGGER: https://bitbucket.org/mr_santos/avenuecodetest/src/25bf3ff0a1350b4d27757e4e6cc29e6627227f0a/src/main/resources/docs/swagger_api_documentation.yaml?at=master&fileviewer=file-view-default

	POSTMAN: https://bitbucket.org/mr_santos/avenuecodetest/src/25bf3ff0a1350b4d27757e4e6cc29e6627227f0a/src/main/resources/docs/AvenueTest.postman_collection.json?at=master&fileviewer=file-view-default

### AUTOMATED TESTS ###

To run the suite of automated tests, execute the simple command below:

	mvn test

### ADDITIONAL COMMENTS ###

I didn't use authentication methods to simplify the tests.