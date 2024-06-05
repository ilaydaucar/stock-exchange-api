# Stock Exchange API
### Installation
To run the Stock Exchange API locally, follow these steps:

1. **Clone the repository:**
    ```sh
    git clone https://github.com/ilaydaucar/stock-exchange-api.git
    ```

2. **Navigate to the project directory:**
    ```sh
    cd stock-exchange-api
    ```

3. **Ensure you have the following technologies installed:**
    - Java Development Kit (JDK) version 21 or later
    - Maven version 3.3.0
    - H2 Database

4. **Build and run the application with Maven:**
    ```sh
    mvn spring-boot:run
    ```

Maven will handle the compilation, dependency resolution, and launching of the application. You'll see logs indicating that the server is running.

5. **Access the application:**
   The application will be accessible at [http://localhost:8080/api/v1/stock/](http://localhost:8080/api/v1/stock/) or [http://localhost:8080/api/v1/stock-exchange/](http://localhost:8080/api/v1/stock-exchange/) by default, unless you've configured a different port in your Spring Boot application properties.

### Data Generation

Sample data for testing purposes will generate automatically

### Postman Collection

To interact with the API endpoints using Postman, import the provided collection:

[Stock Exchange API Postman Collection](https://github.com/ilaydaucar/stock-exchange-api/stock-exchange-api/src/main/resources/postman/Stock-Exchange.postman_collection.json)

1. **Download and install Postman:**
    - If you haven't already installed Postman, you can download it from [here](https://www.postman.com/downloads/).

2. **Import the collection:**
    - Open Postman and click on the "Import" button.
    - Choose "Import From Link" and paste the provided link to import the collection.

3. **Explore the endpoints:**
    - Once imported, you can explore and test the API endpoints using the collection.
