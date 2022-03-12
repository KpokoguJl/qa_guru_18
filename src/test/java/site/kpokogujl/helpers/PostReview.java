package site.kpokogujl.helpers;

import io.restassured.http.Cookies;
import site.kpokogujl.tests.TestBase;

import static io.restassured.RestAssured.given;

public class PostReview extends TestBase {
    private final String url = API_BASE_URL + "productreviews/74";

    public void postReview (String cookie, String title, String review, int rating){
        given()
                .cookie("NOPCOMMERCE.AUTH", cookie)
                .contentType("application/x-www-form-urlencoded")
                .formParam("AddProductReview.Title", title)
                .formParam("AddProductReview.ReviewText", review)
                .formParam("AddProductReview.Rating", rating)
                .formParam("add-review", "Submit+review")
                .when()
                .post(url)
                .then()
//                .log().all()
                .statusCode(200);
    }
}
