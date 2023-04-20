package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {


    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        int amount = 100;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int card1BalanceTo = dashboardPage.getCard1Balance();
        int card2BalanceTo = dashboardPage.getCard2Balance();
        var topUpPage = dashboardPage.topUpCard1();
        topUpPage.topUpCard(String.valueOf(amount), DataHelper.getCardNumber().getCard2());
        dashboardPage.updateButton();
        int card1BalanceFrom = dashboardPage.getCard1Balance();
        int card2BalanceFrom = dashboardPage.getCard2Balance();
        Assertions.assertEquals(card1BalanceTo + amount, card1BalanceFrom);
        Assertions.assertEquals(card2BalanceTo - amount, card2BalanceFrom);

    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        int amount = 100;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int card1BalanceTo = dashboardPage.getCard1Balance();
        int card2BalanceTo = dashboardPage.getCard2Balance();
        var topUpPage = dashboardPage.topUpCard2();
        topUpPage.topUpCard(String.valueOf(amount), DataHelper.getCardNumber().getCard1());
        dashboardPage.updateButton();
        int card1BalanceFrom = dashboardPage.getCard1Balance();
        int card2BalanceFrom = dashboardPage.getCard2Balance();
        Assertions.assertEquals(card1BalanceTo - amount, card1BalanceFrom);
        Assertions.assertEquals(card2BalanceTo + amount, card2BalanceFrom);

    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCardOverBalance() {
        int amount = 100000;
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int card1BalanceTo = dashboardPage.getCard1Balance();
        int card2BalanceTo = dashboardPage.getCard2Balance();
        var topUpPage = dashboardPage.topUpCard1();
        topUpPage.topUpCard(String.valueOf(amount), DataHelper.getCardNumber().getCard2());
        dashboardPage.updateButton();
        int card1BalanceFrom = dashboardPage.getCard1Balance();
        int card2BalanceFrom = dashboardPage.getCard2Balance();
        Assertions.assertEquals(card1BalanceTo + amount, card1BalanceFrom);
        Assertions.assertEquals(card2BalanceTo - amount, card2BalanceFrom);

    }
}
