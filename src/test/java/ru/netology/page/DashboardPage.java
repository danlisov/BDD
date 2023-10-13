package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private String balanceStart = "баланс: ";
    private String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public int getCardBalance(int index) {
        val text = cards.get(index).getText();
        return extractBalance(text);
    }

    public TransferPage selectCardForTransfer(DataHelper.CardInfo cardInfo) {
        cards.findBy(attribute("data-test-id",
                cardInfo.getId())).$("button").click();
        return new TransferPage();
    }
}
