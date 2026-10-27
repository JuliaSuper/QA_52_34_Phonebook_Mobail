package mobile;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactsScreen;
import screens.ErrorScreen;
import screens.RegistrationLoginScreen;
import screens.SplashScreen;
import static utils.PropertiesReader.*;

public class LoginTests extends TestBase{
    RegistrationLoginScreen registrationLoginScreen;
    User user;
    @BeforeMethod
    public  void goToRegistrationScreen(){
        new SplashScreen(driver).goToRegistrationScreen();
        registrationLoginScreen = new RegistrationLoginScreen(driver);
        user = User.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties","password")).build();
    }
    @Test
    public void loginPositiveTest(){
        registrationLoginScreen.typeFormLoginRegistration(user);
        registrationLoginScreen.clickBtnLogin();
        Assert.assertTrue(new ContactsScreen(driver).isBtnAddContactPresent());
    }

    @Test
    public void loginNegativeTest(){
        user.setPassword("juli123!!");
        registrationLoginScreen.typeFormLoginRegistration(user);
        registrationLoginScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("Login or Password incorrect"));
    }
}
