# 27 - 04 - 2025 

- Initialized project with spring initializer 
- Initialized git
- Created Postgres database `payment_service_provider`



# 30 - 04 - 2025

- Continue setting up the project
- Updated the application.properties file and Connected to the database with a DatabaseInitializer class
- Create Entities 

# 03 - 05 - 2025

- Created a UserRegistrationDTO
- Created a mapper for User and the dto
- Created a UserRepository
- Created a Password encryptor class
- Updated the id type in the Entities

# 06 - 05 - 2025

- Create User service
- Create User Controller
- Update dto and entity mapping
- Made Register endpoint

# 07 - 05 - 2025 

- Updated error message response
- Created GlobalExceptionHandler to handle controller exceptions
- Created custom response objects for json api response entity
- Created custom exception to handle users already exists

# 12 - 05 - 2025

- Created the login endpoint :   
Made a dto for user login details, made a handler and a custom exception for
handling a user not found and incorrect details error, made updates to the controller and service.


# 15 - 05 - 2025
- Added new feature, an endpoint for verifying users after registration
- Added a table verification_tokens for handling verification
- Added services, for email and verification, and a custom exception for handling email
- Added smtp in application properties and an env file for email connection