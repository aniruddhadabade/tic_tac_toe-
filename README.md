Steps to Build and Run the App

Clone the Repository:

    git clone https://github.com/aniruddhadabade/tic_tac_toe-.git
    
    cd tic_tac_toe

Set Up the Database:

Install MySQL and create a database named tic_tac_toe.

Update the application.properties file in the src/main/resources folder with your MySQL credentials:

    spring.datasource.url=jdbc:mysql://localhost:3306/tic_tac_toe
    spring.datasource.username=<your-username>
    spring.datasource.password=<your-password>
    spring.jpa.hibernate.ddl-auto=update

Install Dependencies:

Ensure you have Java and Maven installed. Then, run:
    mvn clean install

Run the Application:
   mvn spring-boot:run

Access the API:

    The API will be available at http://localhost:8080.
    
    Use tools like Postman or URL to test the endpoints.

