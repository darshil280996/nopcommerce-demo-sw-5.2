package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.BuildYourOwnComputerPage;
import com.nopcommerce.demo.pages.ComputerPage;
import com.nopcommerce.demo.pages.DesktopsPage;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;


@Listeners(CustomListeners.class)
public class ComputerPageTest extends BaseTest {

    HomePage homePage;
    ComputerPage computerPage;
    DesktopsPage desktopsPage;
    BuildYourOwnComputerPage buildYourOwnComputerPage;

    @BeforeMethod (alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        computerPage = new ComputerPage();
        desktopsPage = new DesktopsPage();
        buildYourOwnComputerPage = new BuildYourOwnComputerPage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyUserShouldNavigateToComputerPageSuccessfully(){
        homePage.clickOnComputersMenu();
        String expectedMessage = "Computers";
        String actualMessage = computerPage.getWelcomeText();
        Assert.assertEquals(actualMessage, expectedMessage, "Computers page not displayed");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        homePage.clickOnComputersMenu();
        computerPage.clickOnDesktopsLink();
        String expectedMessage = "Desktops";
        String actualMessage = desktopsPage.getWelcomeText();
        Assert.assertEquals(actualMessage, expectedMessage, "Desktops page not displayed");
    }

    @Test(dataProvider = "builtYourOwnComputer", dataProviderClass = TestData.class, groups = {"regression"})
    public void verifyThatUserShouldBuildYouOwnComputerAndAddThemToCartSuccessfully(String processor, String ram, String hdd, String os, String software){
        homePage.clickOnComputersMenu();
        computerPage.clickOnDesktopsLink();
        desktopsPage.clickOnBuildYourOwnComputerLink();
        buildYourOwnComputerPage.optionsToBuiltYourOwnComputer(processor, ram, hdd, os, software);
        buildYourOwnComputerPage.clickOnAddToCart();
        String expectedMessage = "The product has been added to your shopping cart";
        String actualMessage = buildYourOwnComputerPage.getAddToCartSuccessfulMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Product is not added successfully");
    }
}
