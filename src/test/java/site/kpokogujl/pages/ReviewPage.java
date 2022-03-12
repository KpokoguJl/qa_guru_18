package site.kpokogujl.pages;

import com.codeborne.selenide.SelenideElement;
import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import site.kpokogujl.tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ReviewPage extends TestBase {
    SelenideElement reviewTitle = $(".product-review-list").lastChild().$(".review-title"),
            reviewText = $(".product-review-list").lastChild().$(".review-text"),
            reviewAuthor = $(".product-review-list").lastChild().$(".user");

    public ReviewPage openPage(String cookie){
        open("Themes/DefaultClean/Content/images/logo.png");
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookie));

        open("productreviews/74");
        $(".account").shouldHave(text(USER_LOGIN));

        return this;
    }

    public void checkResult (String title, String review){
        reviewTitle.scrollTo();
        reviewTitle.shouldHave(text(title));
        reviewText.shouldHave(text(review));
        reviewAuthor.shouldHave(text(USER_NAME));
    }

}
