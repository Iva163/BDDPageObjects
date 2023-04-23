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
        String number = "5559 0000 0000 0002";
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int card1BalanceTo = dashboardPage.getCardBalance(1);
        int card2BalanceTo = dashboardPage.getCardBalance(2);
        var topUpPage = dashboardPage.topUpCard(1);
        topUpPage.topUpCard(String.valueOf(amount), DataHelper.setCardNumber(number).getCardNumber());
        dashboardPage.updateButton();
        int card1BalanceFrom = dashboardPage.getCardBalance(1);
        int card2BalanceFrom = dashboardPage.getCardBalance(2);
        Assertions.assertEquals(card1BalanceTo + amount, card1BalanceFrom);
        Assertions.assertEquals(card2BalanceTo - amount, card2BalanceFrom);

    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        int amount = 100;
        String number = "5559 0000 0000 0001";
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int card1BalanceTo = dashboardPage.getCardBalance(1);
        int card2BalanceTo = dashboardPage.getCardBalance(2);
        var topUpPage = dashboardPage.topUpCard(2);
        topUpPage.topUpCard(String.valueOf(amount), DataHelper.setCardNumber(number).getCardNumber());
        dashboardPage.updateButton();
        int card1BalanceFrom = dashboardPage.getCardBalance(1);
        int card2BalanceFrom = dashboardPage.getCardBalance(2);
        Assertions.assertEquals(card1BalanceTo - amount, card1BalanceFrom);
        Assertions.assertEquals(card2BalanceTo + amount, card2BalanceFrom);

    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCardOverBalance() {
        int amount = 100000;
        String number = "5559 0000 0000 0002";
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        int card1BalanceTo = dashboardPage.getCardBalance(1);
        int card2BalanceTo = dashboardPage.getCardBalance(2);
        var topUpPage = dashboardPage.topUpCard(1);
        topUpPage.topUpCard(String.valueOf(amount), DataHelper.setCardNumber(number).getCardNumber());
        dashboardPage.updateButton();
        int card1BalanceFrom = dashboardPage.getCardBalance(1);
        int card2BalanceFrom = dashboardPage.getCardBalance(2);
        Assertions.assertEquals(card1BalanceTo + amount, card1BalanceFrom);
        Assertions.assertEquals(card2BalanceTo - amount, card2BalanceFrom);

    }
}
