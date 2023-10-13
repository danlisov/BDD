package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading = $(byText("Пополнение карты"));
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement toField = $("[data-test-id=to] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelButton = $("[data-test-id=action-cancel]");
    private SelenideElement error = $("[data-test-id=error-notification]");

    public TransferPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage makeValidTransfer(String amount, DataHelper.CardInfo cardInfo) {
        validTransfer(amount, cardInfo);
        return new DashboardPage();
    }

    public void validTransfer(String amount, DataHelper.CardInfo cardInfo) {
        amountField.setValue(amount);
        fromField.setValue(cardInfo.getNumber());
        transferButton.click();
    }

    public void errorMessage(String expectedText) {
        error.shouldHave(exactText(expectedText)).shouldBe();
    }
}
