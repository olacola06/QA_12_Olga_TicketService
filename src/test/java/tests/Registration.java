package tests;

import manager.MyDataProvider;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.TestRunner.PriorityWeight.dependsOnMethods;

public class Registration extends TestBase{

    Logger logger = LoggerFactory.getLogger(Registration.class);

    int i = (int) (System.currentTimeMillis()/1000)%3600;
    User user = User.builder().name("Ola").surname("Mar").email("olamm"+i+"@gmail.com").password("Bo12345$"+i).
            confirmPassword("Bo12345$"+i).phone("+123456789").build();


    @BeforeMethod
    public void preCondition(){
        app.regist().clickLogin();
        app.regist().clickRegister();
    }
    @Test
    public void registrationPositive(){
        //String email = "olam"+i+"@gmail.com";//Fail with first UpperCase letter
        logger.info("Test starts with details:-->"+user.getName()+", "+user.getSurname()+", "+user.getEmail());
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().registerButton();

        Assert.assertTrue(app.regist().registrationSuccess(user));
        logger.info("Registration test passed Success");
        app.regist().clickReturn();
        app.regist().refresh();

    }
    @Test
    public void registrationNegExistUser(){
        User user = User.builder().name("Ola").surname("Mar").email("olamm@gmail.com").password("Bo12345$").
                confirmPassword("Bo12345$").phone("+123456789").build();

        logger.info("Test starts with details:-->"+user.getName()+", "+user.getSurname()+", "+user.getEmail());
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().registerButton();

        Assert.assertTrue(app.regist().registrationFail());
        app.regist().returnToRegistrationBtn();

        logger.info("Registration test with wrong data finished");

    }
    @Test
    public void registrationWrongEmailFormat() {
        user.setEmail("olamm" + i + "gmail.com");
        logger.info("Test starts with details:-->" + user.getName() + ", " + user.getSurname() + ", " + user.getEmail());
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().registerButton();
        Assert.assertTrue(app.regist().registrationFailedWrongData());

        logger.info("Registration test with wrong data finished");
    }
    @Test
    public void registrationNegEmailEmpty() {
        User user = User.builder().name("Ola").surname("Mar").email("").password("Bo12345$"+i).
                confirmPassword("Bo12345$"+i).phone("+123456789").build();
        logger.info("Test starts with details:-->" + user.getName() + ", " + user.getSurname() + ", " + user.getEmail());
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().registerButton();
        app.regist().registrationEmptyEmailAssert();

        logger.info("Registration test with wrong data finished");
    }
    @Test(enabled = false, dataProvider = "registrationValidDataLom",dataProviderClass = MyDataProvider.class)
    public void registrationPosLB(User user) {
        user.setEmail(i+user.getEmail());
        user.setPhone(user.getPhone()+i);
        user.setPassword(user.getPassword()+i);
        user.setConfirmPassword(user.getConfirmPassword()+i);

        logger.info("Test starts with details:-->"+user.getName()+" ,"+user.getSurname()+" ,"+user.getEmail()
        +" ,"+user.getPassword()+" ,"+user.getConfirmPassword()+" ,"+user.getPhone());
        //app.regist().clickLogin();
        //app.regist().clickRegister();
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().registerButton();

        Assert.assertTrue(app.regist().registrationSuccess(user));
        logger.info("Registration test passed Success");
        app.regist().clickReturn();
        app.regist().refresh();

    }
    @Test(enabled = false,dataProvider = "registrationValidData",dataProviderClass = MyDataProvider.class)
    public void registrationPosDP(User user){
        user.setEmail(i+user.getEmail());
        user.setPhone(user.getPhone()+i/100);

        logger.info("Test starts with details:-->"+user.getName()+", "+user.getSurname()+", "+user.getEmail());
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().registerButton();

        Assert.assertTrue(app.regist().registrationSuccess(user));
        logger.info("Registration test passed Success");
        app.regist().clickReturn();
        app.regist().refresh();

    }

}
