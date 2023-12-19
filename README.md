# Project Title

This project provides a prebuilt api that you can interact with in order to understand how to use certain features of Microsoft Azure Services (Cosmos DB for Mongo and Blob Storage).

## Prerequisites

Before you begin, ensure you have met the following requirements:
- You have installed [Git](https://git-scm.com/downloads).
- You have installed [Visual Studio Code](https://code.visualstudio.com/Download).
- You have installed [JDK 17] or higher.
- You have installed the [Extension Pack for Java], [Spring Boot Extension Pack], VSC extensions

## Cloning and Running the Project in Visual Studio Code

To clone and run this project in Visual Studio Code, follow these steps:

### 1. Clone the Repository

Open your terminal in the location you want the project in, and run the following git command:

```bash
git clone https://github.com/KanamoriAlice/azure-bakery-api-example.git
```

### 2. Add the Azure connection strings in the application.properties

1. To use Blob Storage, you need to add your connection string in:

```aplication.properties
azure.storage.connection-string = yourConnectionString
```
2. To use Cosmos DB for Mongo, you need to add your database uri string in:

```aplication.properties
spring.data.mongodb.uri = yourURIString
```

And the name of the database you want to use in =

```aplication.properties
spring.data.mongodb.database = yourDatabaseName
```

Note: just paste the values in the file, do not add "" symbols to the strings.

### 3. Interact with the OpenAPI documentation

After running the project, to interact with the API endpoints you can access to the following page:

http://localhost:8080/swagger-ui/index.html

Note: Although it looks available, you won't be able to work with the POST Blob Storage API endpoint to upload pictures. For that purpose, I recommend using Postman.
