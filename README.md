# API_Testing_Automation_Task
This project contains automated test cases for the User API using RestAssured and TestNG.

## Getting Started

To get started with the project, follow these steps:

1. Clone the repository to your local machine:
2. 2. Open the project in your preferred Java IDE (IntelliJ).
3. Install the required dependencies specified in the pom.xml file, including RestAssured and TestNG.

## Project Structure

The project structure is as follows:

- `src/test/java`: Contains the Java test script `UserApi.java`, which implements the automated test cases.
- `src/test/resources`: Includes test data or configuration files, if any.

  ## Running the Tests

To run the test cases, open the `UserApi.java` class in your IDE and locate the `testGetRandomUserPosts()` method. Right-click on the method and select "Run" or "Run Test" to execute the tests.

The test method performs the following steps:
1. Fetch a random user ID between 1 and 10.
2. Retrieve the email address of the random user and print it to the console.
3. Get the user's associated posts and validate that the post IDs are within the range of 1 to 100.
4. Verify the response status code (200) for successful retrieval of user posts.
5. Create a new post for the random user with a title and body.

## Prerequisites

- Java 8 or higher
- Maven (optional, if using a build tool)
- RestAssured and TestNG dependencies (specified in pom.xml)

- 
## Running the Tests

- Make sure the API endpoint is accessible and valid during test execution.
- Open the `UserApi` class and run the `testGetRandomUserPosts()` method.
