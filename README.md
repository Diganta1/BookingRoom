# BookingRoom
This is a room booking service

# Setup
 ## Database components 

(http://localhost:8080/h2-console/):
Parameters:
Saved settings: Generic H2(Embeded)
Driver Class: org.h2.Driver
JDBC Url: jdbc:h2:mem:testdb
User name: sa

## Docker Setup
there is a docker file present inside the project (Dockerfile)
go to the project specific path and run below command in docker to create a image of spring boot application

1. creating a springboot application : (docker build -f Dockerfile -t roombookingmanagement .)
2. run it on container : (docker run -p 8080:8080 roombookingmanagement)

## Security setup in postman
Spring web security has been integrated with the module. Please follow the below steps to acheive those:

Settings : Under "Authorization" tab of postman pass the (Type : Basic Auth)
   Username: stock
   password: stock
   
   "Headers" 
   Accept : Application/json
   
   "Body"      form-data
   
    Key                value
   
     Username           digi
     password           password
     grant_type         password
     
     # POST: localhost:8080/oauth/token
   
   Response: 
   
    {
    "access_token": "bbc50a3c-25fa-4141-9b62-cab9fa8dfa96",
    "token_type": "bearer",
    "refresh_token": "168eb83a-992a-4b4a-9fbc-e892f2c0217d",
    "expires_in": 3599,
    "scope": "read write trust"
    }

#<After generating the access_token pass it in header of other services to validate>

# POST : localhost:8080/api/customer?access_token=<access-token>

{
"firstname":"Diganta",
"lastname":"Mohapatra",
"username":"digmohap",
"password": "Licku.1991"
}

Success:
Response:                     Status: 201 Created

{
    "status": "Added in queue",
    "message": "Generated ID : 1"
}

Failure: User name is same
Response:                     Status: 400 Bad Request

{
    "timestamp": "2020-02-16T05:44:00.415+0000",
    "message": "Username already exists"
}

Failure: Password validation

Response:                   Status: 400 Bad Request

{
    "timestamp": "2020-02-16T05:45:56.223+0000",
    "message": "Please provide following mandatory details [Password must be 8 or more characters in length.,Password must contain 1 or more digit characters.,Password must contain 1 or more special characters.] "
}


# GET : localhost:8080/api/customer/1?access_token= <access-token>

Success :
Response:                   Status: 200 ok

{
    "id": 1,
    "firstname": "Diganta",
    "lastname": "Mohapatra",
    "username": "digmohap",
    "dob": null,
    "email": null,
    "password": "*****"
}

Failure:
Response:                 Status: 404 Not Found

{
    "timestamp": "2020-02-16T05:49:05.022+0000",
    "message": "Booking 5 does not exists"
}

Test Cases has been covered.

