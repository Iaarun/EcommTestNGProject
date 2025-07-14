package tests;

import base.BaseClass;
import io.qameta.allure.Description;
import listener.TestListeners;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MobilePage;

import java.util.ArrayList;
import java.util.List;



public class EcommTests extends BaseClass {

 Logger logger = LogManager.getLogger(EcommTests.class);

    @Test
    @Description("This test verifies that the home page title is correct.")
    public void validateHomePageTitle(){
        logger.info("Starting validateHomePageTitle test");
        HomePage homePage = new HomePage(driver);
      String actualHomePageTitle=  homePage.getHomePageTitle();
        Assert.assertEquals(actualHomePageTitle,"Home page", "Home page title is incorrect!");
      logger.info("Home page title validation completed successfully");
    }

    @Test
    @Description("This test verifies that the mobile list page is sorted by name.")
    public void validateMobileListPageIsSorted(){
        logger.info("Starting validateMobileListPageIsSorted test");
        HomePage homePage= new HomePage(driver);
        MobilePage mobilePage = new MobilePage(driver);

    String actualHomePageTitle=    homePage.getHomePageTitle();
    logger.info("Home page title: {}", actualHomePageTitle);
    Assert.assertEquals(actualHomePageTitle, "Home page", "Home Page title found is different than expected!");
    //click on mobile link
        homePage.clickOnMobileLink();
        //verify title of the mobile page
    String actualMobilePageTitle=    mobilePage.getMobilePageTitle();
    logger.info("Mobile page title: {}", actualMobilePageTitle);
    Assert.assertEquals(actualMobilePageTitle, "Mobile", "Mobile page title found is different than expected!");
    //select sort by dropdown as name
    mobilePage.selectSortBy("Name");
    //validate all the products are sorted by name
     List<String> names= mobilePage.getProductNames();
     List<String> sortedNames =  new ArrayList<>(names);
     sortedNames.sort(String::compareTo);
     Assert.assertEquals(names, sortedNames, "Product names are not sorted by name!");
     logger.info("Product names are sorted by name successfully");
    }
}
