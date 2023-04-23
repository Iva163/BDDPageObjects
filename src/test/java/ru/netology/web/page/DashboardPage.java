package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private String cardButton = "[data-test-id=action-deposit]";
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement updateButton = $("[data-test-id=action-reload]");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(int id) {
        int index = id - 1;
        val text = cards.get(index).text();
        return extractBalance(text);
    }


    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TopUpPage topUpCard(int id) {
        int index = id - 1;
        cards.get(index).find(cardButton).click();
        return new TopUpPage();
    }


    public void updateButton() {
        updateButton.click();
    }
}
