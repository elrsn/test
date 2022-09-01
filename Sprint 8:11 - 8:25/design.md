Users Microservice
===
This microservice will contain the required endpoints and database connections to create accounts of varying types, such as Customers, Web Admins, Restaurant Admins, and Drivers.
Currently the Microservice only contains endpoints for creating, querying, and deleting customer accounts, which results in the following endpoints:

### GET: `/customer`
- Returns a JSON list of customers (defined at the bottom of this document)
- If no cusomters have been registered, the endpoint will return code 204 (No Content), on browsers this will result in the current page not being changed, this is intended behaviour

### POST: `/customer`
- Creates a new customer using a JSON object (defined at the bottom of this document)
- On successful creation of the customer, code 201 will be returned alongside a JSON object representing the customer (defined at the bottom of this document) and the `Location` header will contain a url to an endpoint that's created. (e.g. `/customer/20` for a customer that's created with id 20)
- On failure, code 400 will be returned, there are currently no error messages to be read, this will be fixed as we get further with the project

### GET: `/customer/email/{email}`
- Searches for and returns a customer based on a passed in {email}.
- Returns code 200 and the JSON representation of the customer (defined at the bottom of this document) on success
- Returns code 404 on failure to find the customer
- Returns code 400 when passing an invalid email or no email

### GET: `/customer/{customerId}`
- Ditto for the /customer/email/{email} endpoint but instead searching based on a customer id.

### DELETE: `/customer/{customerId}`
- Deletes the customer account identified by the passed in ID, returning code 200 on success
- Returns code 404 on failure to find the customer
- Returns code 400 when passing an invalid id or no id

Customer JSON:
```json
{
	"id": int,
	"firstName": string,
	"lastName": string,
	"email": string,
	"phoneNo": string, // No special formating (e.g. instead of (123) 456-7890 it's 1234567890)
	"hashedPassword": string, // To be removed
	"type": enum["R", "D", "C", "S"], // R: Restaurant Admin, D: Driver, C: Customer, S: Site Admin
	"address": {
		"id": int,
		"latitude": real,
		"longitude": real,
		"zipCode": int, // Soon to be changed to string
		"country": "US", // This never changes
		"streetAddress": string,
		"houseNumber": string?, // Nullable
		"unitNumber": string? // Nullable
	}
}
```

Customer Creation JSON:
```json
{
  "firstName": string,
  "lastName": string,
  "email": string,
  "phoneNo": string,
  "password": string,
  "addressLine": string,
  "houseNumber": string?, // Nullable
  "unitNumber": string?, // Nullable
  "city": string,
  "state": string,
  "zipcode': string
}
```

Restaurants Microservice
===
This microservice will contain the required endpoints and database connections to create restaurants, add food items to those restaurants, view the details of each restaurant, and search for specific types of food and restaurants.


### GET: `/restaurants`
- Returns 200 and a JSON list of restaurants
- Returns 204 if no restaurants was created

### POST: `/restaurants`
- Creates a new restaurant using a JSON object
- Returns 201 and a JSON object representing the restaurant on creation success
- Returns 400 on creation failure

### GET: `/restaurants/{id}`
- Returns 200 and a JSON object of restaurant with id
- Returns 404 on failure to find restaurant
- Returns 400 on passing an invalid id

### DELETE: `/restaurants/{id}`
- Returns 200 on successful deletion of restaurant
- Returns 404 on failure to find the restaurant
- Returns 400 on passing invalid id



Restaurant JSON:
```json
{
  "id": int,
  "name": string,
  "address": {
    "id": int,
    "latitude": real,
    "longitude": real,
    "zipCode": string,
    "state": string,
    "country": string,
    "streetAddress": string,
    "houseNumber": string? //Nullable
    "unitNumber": string? //Nullable
  },
  "phone_no": string,
  "website": string,
  "openTime": time,
  "closeTime": time,
  "rating": decimal,
  "ratingCount": int,
  "menuItems": {
    "item": {
      "id": int,
      "name": string,
      "price" decimal,
      "description": string
    }
  }
}
```

Restaurant Creation JSON:
```json
{
  "name": string, 
  "zipCode": string,
  "state": string,
  "country": string,
  "streetAddress": string,
  "houseNumber": string? //Nullable
  "unitNumber": string? //Nullable
  "phone_no": string,
  "website": string,
  "openTime": time,
  "closeTime": time
}


```

Food Microservice
===

### POST: `food/`         
- Ask for the Food object in request body and Add that to the database if not existing
- return “Added successfully” on successful Add
- return “An Error occurred” on fault

### GET: `food/by-id/food/{id}`
- Return FoodDto Object Corresponding to the ID
- Return Empty Object on if ID not found

### GET: `food/{name}`
- Responds List of FoodDto Objects with respect to String({name}) given in URL

### PUT: `food/name/{id}`
- Update Food Name in the database having id = {id} with httpParam(name)
- on Success “updated successfully”
- on Error “And Error Occured”

### PUT: `food/price/{id}`
- Update Food price in the database having id = {id} with httpParam(price)
- on Success “updated successfully”
- on Error “And Error Occured”

### PUT: `food/description/{id}`    
- Update Food description in the database having id = {id} with httpParam(description)
- on Success “updated successfully”
- on Error “And Error Occured”

### PUT: `food/restaurant/{id}`
- Update Food restaurant Id in the database having id = {id} with httpParam(restaurantId)
- on Success “updated successfully”
- on Error “And Error Occured”

### DELETE: `food/`             
- delete food from database according to the id passed in httpParam
- if not enertry in data base returns “no item found by id given”
- if found and deleted successfully returns “deleted successfully”
- if found and error occurred returns “something went wrong”

### GET:  `search/`
- Responds with list of Restaurants and foods according to the string passed in httpParam




FoodDto JSON:

```json
{
  Id : Integer,
  Name : String,
  RestaurantName : String,
  Price : Float,
  Description : String,
}
```