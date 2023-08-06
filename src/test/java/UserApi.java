import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class UserApi {

    public int randomUserId ;

    @Test
    public void testGetRandomUserPosts() {
        // Set the base URL for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Step 1: Get a random user (userID)
        randomUserId = getRandomUserId();

        // Step 2: Print out the user's email address to the console
        String email = getEmailAddressForUser(randomUserId);
        System.out.println("User ID: " + randomUserId + " | Email: " + email);

        // Step 3: Get the user's associated posts
        Response response = getUserPosts(randomUserId);

        // Step 4: Verify the posts contain valid Post IDs (an Integer between 1 and 100)
        List<Integer> postIds = response.jsonPath().getList("id");
        for (int postId : postIds) {
            Assert.assertTrue(postId >= 1 && postId <= 100, "Invalid Post ID found: " + postId);
        }

        //  Check the response status code ( 200 for success)
        Assert.assertEquals(response.getStatusCode(), 200, "Failed to get user posts.");

        // Step 5: Create a new post with a non-empty title and body
        String title = "Test Post Title";
        String body = "Test Post Body";

        Response postResponse = createPostForUser(randomUserId, title, body);

        // Step 6: Verify the correct response is returned for post creation
        Assert.assertEquals(postResponse.getStatusCode(), 201, "Post creation failed.");

        // Additional assertions or validations based on the API response
        int postId = postResponse.jsonPath().getInt("id");
        String actualTitle = postResponse.jsonPath().get("title");
        String actualBody = postResponse.jsonPath().get("body");

        Assert.assertTrue(postId > 0, "Invalid Post ID: " + postId);
        Assert.assertEquals(actualTitle, title, "Incorrect Post Title.");
        Assert.assertEquals(actualBody, body, "Incorrect Post Body.");
    }

    // Helper method to get a random user ID between 1 and 10
    private int getRandomUserId() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }

    // Helper method to get the email address of a user
    private String getEmailAddressForUser(int userId) {
        Response response = RestAssured.given()
                .pathParam("userId", userId)
                .when()
                .get("/users/{userId}");

        return response.jsonPath().get("email");
    }

    // Helper method to get the posts of a user
    private Response getUserPosts(int userId) {
        Response response = RestAssured.given()
                .queryParam("userId", userId)
                .when()
                .get("/posts");

        return response;
    }

    // Helper method to create a new post for a user
    private Response createPostForUser(int userId, String title, String body) {
        String payload = "{ \"userId\": " + userId + ", \"title\": \"" + title + "\", \"body\": \"" + body + "\" }";

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .post("/posts");

        return response;
    }

}
