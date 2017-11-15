1 - How to compile and run the application with an example for each call.
	To compile and run the application, execute the command below:
		mvn clean install package test spring-boot:run
	CALL EXAMPLES:
		a) Get all products excluding relationships (child products, images)
			GET: http://localhost:8080/api/rest/products?images=false&childs=false
		b) Get all products including specified relationships (child product and/or images)
			GET: http://localhost:8080/api/rest/products?images=true&childs=true
		c) Same as 1 using specific product identity
			GET: http://localhost:8080/api/rest/products/3?images=false&childs=false
		d) Same as 2 using specific product identity
			GET: http://localhost:8080/api/rest/products/3?images=true&childs=true
		e) Get set of child products for specific product
			GET: http://localhost:8080/api/rest/products/3/childs
		f) Get set of images for specific product
			GET: http://localhost:8080/api/rest/products/3/images
		g) Creating a product
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
		h) Updating a product
			PUT: http://localhost:8080/api/rest/products/1
			Header: Content-Type: application/json
			Body: {
				    "id": 1,
				    "name": "Product 1",
				    "description": "OneImage",
				    "parent": 2,
				    "images": [
				      {
				        "id": 1,
				        "type": "GIF"
				      }
				    ]
				  }
		i) Removing a product
			DELETE: http://localhost:8080/api/rest/products/4
			
	If you prefer, you can use Postman(https://www.getpostman.com/) and/or Swagger(http://editor.swagger.io/#/) files attachment to see documentation API.

2 - How to run the suite of automated tests.
	To run the suite of automated tests, execute the simple command below:
		mvn test
3 - Mention anything that was asked but not delivered and why, and any additional comments.
	I didn't use authentication methods to simplify the tests.