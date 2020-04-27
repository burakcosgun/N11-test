package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.FavouritePage;
import pages.LoginPage;
import pages.SearchPage;

public class FavoriteProductTest extends BaseTest{
    private final static int SEARCH_PAGE_INDEX = 2;
    private final static int SEARCHED_PRODUCT_INDEX =3;
    private final static String SEARCH_PRODUCT_NAME = "samsung";
    private final static String SEARCH_PAGE_URL="https://www.n11.com/arama?q="+SEARCH_PRODUCT_NAME;
    private final static String EXPECTED_SEARCH_PAGE="https://www.n11.com/arama?q="+SEARCH_PRODUCT_NAME+"&pg="+Integer.toString(SEARCH_PAGE_INDEX);
    private final static String EXPECTED_FAVORITE_URL="https://www.n11.com/hesabim/favorilerim";

    @Test
    public void addAndDeleteFavoriteProductTest() throws InterruptedException {

        String currentURL;
        String favoritedItemProductId;
        WebElement loggedInUser;

        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        FavouritePage favouritesPage = PageFactory.initElements(driver,FavouritePage.class);
        SearchPage searchText=  PageFactory.initElements(driver,SearchPage.class);

        driver.manage().window().maximize();

        //Open main page
        loginPage.getPage(MAAIN_PAGE_URL);
        Assert.assertEquals(driver.getCurrentUrl(), MAAIN_PAGE_URL);
        System.out.println("Web site was opened!");

        //Login
        loginPage.openLoginPage();
        loginPage.loginAttempt(VALID_EMAIL_DUMMY,VALID_PASSWORD_DUMMY);
        loggedInUser=driver.findElement(By.linkText(VALID_USERNAME_DUMMY));
        Assert.assertEquals(VALID_USERNAME_DUMMY, loggedInUser.getText());
        System.out.println("Login is succesfull!");

        //Search product
        searchText.search(SEARCH_PRODUCT_NAME);
        Assert.assertEquals(SEARCH_PAGE_URL, driver.getCurrentUrl());
        System.out.println("Product was searched succesfully!");

        //Go to Nth search page
        searchText.goSearchPage(SEARCH_PAGE_INDEX);
        Assert.assertEquals(EXPECTED_SEARCH_PAGE, driver.getCurrentUrl());
        System.out.println(Integer.toString(SEARCH_PAGE_INDEX)+"th search page was opened succesfully!");

        //Add product to favorite
        favoritedItemProductId=searchText.addFavouriteSearchedProduct(SEARCHED_PRODUCT_INDEX);
        System.out.println("Product was added to favorite list succesfully. pId:"+favoritedItemProductId);

        //Go to favorite page
        searchText.goToFavouritePage();
        Assert.assertEquals(EXPECTED_FAVORITE_URL, driver.getCurrentUrl());
        System.out.println("Favorite page was opened succesfully.");

        //Remove product from favorite list
        favouritesPage.removeFavouritedItem(favoritedItemProductId);
        boolean isProdExist= favouritesPage.isExistProductInFavList(favoritedItemProductId);
        Assert.assertFalse(isProdExist);
        System.out.println("Product was removed from favorite list succesfully!");

        //Logout
        favouritesPage.logout();
        System.out.println("Logout completed!");
    }
}
