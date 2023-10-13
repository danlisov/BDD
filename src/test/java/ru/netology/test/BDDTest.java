package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BDDTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TransferPage transferPage;
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage =loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        $("[data-test-id=login] input").setValue("vasya");
        $("[data-test-id=password] input").setValue("qwerty123");
        $("[data-test-id=action-login]").click();
        $(byText("Интернет Банк")).shouldBe(visible);


        $("[data-test-id=code] input").setValue("12345");
        $("[data-test-id=action-verify]").click();
        $("[data-test-id=dashboard]").shouldBe(visible);

        $$("[data-test-id=action-deposit]").get(0).click();
        $(byText("Пополнение карты")).shouldBe(visible);

        $("[data-test-id=amount] input").setValue("1000");
        $$("[data-test-id=from] input").get(0).setValue("5559 0000 0000 0002");
        $$("[data-test-id=action-transfer]").get(0).click();
        $("[data-test-id=dashboard]").shouldBe(visible);
    }
}
