package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideQaseTest {
    public final static By INPUT_EMAIL = By.cssSelector("#inputEmail");
    public final static By INPUT_PASSWORD = By.cssSelector("#inputPassword");
    public final static By LOGIN_BUTTON = By.cssSelector("#btnLogin");
    public final static By USER_ICON_LOCATOR = By.cssSelector(".BflVGM");
    public final static String BASE_URL = "https://app.qase.io";
    public final static String EMAIL = "sergei.glinisty@gmail.com";
    public final static String PASSWORD = "maa996160";
    public final static By CASE = By.xpath("//div[text()='TMSQA19']");
    public final static By DEFECT = By.xpath("//a[text()='The button is not pressed']");
    String CASE_NAME = "TMSQA19";
    String DEFECT_ACTUAL_RESULT = "All added books remain in the trash.";
    String DEFECT_NAME = "The button is not pressed";


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    public void loginTest() {
        open(BASE_URL);
        $(INPUT_EMAIL).setValue(EMAIL);
        $(INPUT_PASSWORD).setValue(PASSWORD);
        $(LOGIN_BUTTON).click();
        $(USER_ICON_LOCATOR).should(enabled);
    }

    @Test
    public void createCaseTest() {
        open(BASE_URL);
        $("#inputEmail").setValue(EMAIL);
        $("#inputPassword").setValue(PASSWORD);
        $("#btnLogin").click();
        $(By.xpath("//a[text()='QA19']")).click();
        $("#create-case-button").click();
        $("#title").sendKeys(CASE_NAME);
        $("#save-case").click();
        $("span.OL6rtE").shouldHave(Condition.exactText("Test case was created successfully!"));
        $(CASE).shouldHave(enabled);
    }

    @Test
    public void createDefectTest() {
        open(BASE_URL);
        $("#inputEmail").setValue(EMAIL);
        $("#inputPassword").setValue(PASSWORD);
        $("#btnLogin").click();
        $(By.xpath("//a[text()='QA19']")).click();
        $(By.xpath("//span[text()='Defects']")).click();
        $(".me-3").click();
        $("#title").sendKeys(DEFECT_NAME);
        $("p.empty-node").sendKeys(DEFECT_ACTUAL_RESULT);
        $("button.btn.btn-primary.me-3.save-button").click();
        $("span.OL6rtE").shouldHave(Condition.exactText("Defect was created successfully!"));
        $(DEFECT).shouldHave(enabled);

    }


}
