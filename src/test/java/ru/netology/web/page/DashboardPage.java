package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    private SelenideElement card1Balance = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");

    private SelenideElement card1Button = card1Balance.$("[data-test-id=action-deposit]");
    private SelenideElement card2Balance = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement card2Button = card2Balance.$("[data-test-id=action-deposit]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement updateButton = $("[data-test-id=action-reload]");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCard1Balance() {
        val text = card1Balance.text();
        return extractBalance(text);
    }

    public int getCard2Balance() {
        val text = card2Balance.text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TopUpPage topUpCard1() {
        card1Button.click();
        return new TopUpPage();
    }

    public TopUpPage topUpCard2() {
        card2Button.click();
        return new TopUpPage();
    }

    public void updateButton() {
        updateButton.click();
    }
}
