package mobile;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactsScreen;
import screens.ErrorScreen;
import screens.RegistrationLoginScreen;
import screens.SplashScreen;
import utils.UserFactory;
import static utils.PropertiesReader.*;

public class RegistrationTests extends TestBase{
    RegistrationLoginScreen registrationLoginScreen;
    User user;
    @BeforeMethod
    public  void goToRegistrationScreen(){
        new SplashScreen(driver).goToRegistrationScreen();
        registrationLoginScreen = new RegistrationLoginScreen(driver);
        user = UserFactory.positiveUser();
    }

    @Test
    public void registrationPositiveTest(){
        registrationLoginScreen.typeFormLoginRegistration(user);
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new ContactsScreen(driver)
                .validateTextInTextNoContact("No Contacts. Add One more!"));
    }

    @Test
    public void registrationNotEmailNegativeTest(){
        user.setUsername("");
        registrationLoginScreen.typeFormLoginRegistration(user);
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("{username=must not be blank}"));
    }

    @Test
    public void registrationRecurringUserNegativeTest(){
        user = User.builder()
                .username(getProperty("base.properties","email"))
                .password(getProperty("base.properties","password"))
                .build();
        registrationLoginScreen.typeFormLoginRegistration(user);
        registrationLoginScreen.clickBtnRegistration();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateErrorMessage("User already exists"));
    }

}
