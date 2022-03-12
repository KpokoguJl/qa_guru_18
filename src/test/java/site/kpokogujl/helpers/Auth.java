package site.kpokogujl.helpers;

import io.restassured.http.Cookies;
import site.kpokogujl.tests.TestBase;

import static io.restassured.RestAssured.given;

public class Auth extends TestBase {
    private final String url = API_BASE_URL + "login";

    public String getAuthCookie (){

        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", USER_LOGIN)
                .formParam("Password", USER_PASSWORD)
                .formParam("RememberMe", "false")
                .when()
                .post(url)
                .then()
//                .log().all()
                .statusCode(302)
                .extract().cookie("NOPCOMMERCE.AUTH");
    }
}
