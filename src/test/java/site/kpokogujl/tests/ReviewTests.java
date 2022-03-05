package site.kpokogujl.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ReviewTests {
    Faker faker = new Faker();
    String url = "http://demowebshop.tricentis.com/productreviews/74",
            customer = "be4bb1cb-d534-42b4-a93b-9325e8ec6ef0",
            authCookie = "59939EB48C0CDD289B75FF2E0C5FCAAAC6931A6D6B04491F0D0F016CD92AC86E70BFF783ED3F6F5" +
                    "34F301754DAD1C7F2D406A5CE8996D9280B1BB0F8C154389E7BE48085294F738ECC81DF31932BADC191B6" +
                    "F968520D15D025A9E39655C2FBD773003A8F09A1899EB4074940807CA476563314FC858ECD1EE398DED72F" +
                    "C4C5D05BF762595B34D6BFAD8617751396514D";

    @Test
    void addReviewTest() {
        String title = faker.hobbit().character(),
                review = faker.hobbit().location();

        int rating = 5;

                //Создаю отзыв о товаре
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .cookie (String.format("Nop.customer=%s;NOPCOMMERCE.AUTH=%s;", customer, authCookie))
                        .formParam("AddProductReview.Title", title)
                        .formParam("AddProductReview.ReviewText", review)
                        .formParam("AddProductReview.Rating", rating)
                        .formParam("add-review", "Submit+review")
                        .when()
                        .post(url)
                        .then()
                        .log().all()
                        .statusCode(200);

                //Получаю все отзывы о данном товаре
                String response = given()
                        .get(url)
                        .then()
                        .statusCode(200)
                        .extract().response().asString();

                //Проверяю, что отзыв создался
                assert response.contains(title);
                assert response.contains(review);
    }
}
