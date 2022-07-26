package tests;

import manager.MyDataProvider;
import models.User;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Registration extends TestBase{

    Logger logger = LoggerFactory.getLogger(Registration.class);

    @BeforeMethod
    public void preCondition(){
        app.regist().clickLogin();
        app.regist().clickRegister();
    }
    @Test
    public void registrationPositive(){
        //String email = "olam"+i+"@gmail.com";//Fail with first UpperCase letter
       User user = User.builder().name("Ola").surname("Mar").email("olamm@gmail.com").password("Bo12345$").
              confirmPassword("Bo12345$").phone("+123456789").build();

        logger.info("Test starts with details:-->"+user.getName()+", "+user.getSurname()+", "+user.getEmail());
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().pause(1000);
        app.regist().registerButton();

        Assert.assertTrue(app.regist().registrationSuccess(user));
        logger.info("Registration test passed Success");

    }
    @Test
    public void registrationNegExistUser(){
        User user = User.builder().name("Ola").surname("Mar").email("olamm@gmail.com").password("Bo12345$").
                confirmPassword("Bo12345$").phone("+123456789").build();

        logger.info("Test starts with details:-->"+user.getName()+", "+user.getSurname()+", "+user.getEmail());
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().pause(1000);
        app.regist().registerButton();

        Assert.assertTrue(app.regist().registrationFail());
        app.regist().returnToRegistrationBtn();

        logger.info("Registration test with wrong data failed");

    }
    @Test(enabled = false, dataProvider = "registrationValidDataLom",dataProviderClass = MyDataProvider.class)
    public void registrationPosLB(User user) {
        int i = (int) (System.currentTimeMillis()/1000)%3600;
        user.setEmail(i+user.getEmail());
        user.setPhone(user.getPhone()+i);
        user.setPassword(user.getPassword()+i);
        user.setConfirmPassword(user.getConfirmPassword()+i);

        logger.info("Test starts with details:-->"+user.getName()+" ,"+user.getSurname()+" ,"+user.getEmail()
        +" ,"+user.getPassword()+" ,"+user.getConfirmPassword()+" ,"+user.getPhone());
        //app.regist().clickLogin();
        //app.regist().clickRegister();
        app.regist().fillRegistrationForm(user);
        app.regist().pause(3000);
        app.regist().agreeCheckBox();
        app.regist().registerButton();

        Assert.assertTrue(app.regist().registrationSuccess(user));
        logger.info("Registration test passed Success");

    }
    @Test(enabled = false,dataProvider = "registrationValidData",dataProviderClass = MyDataProvider.class)
    public void registrationPosDP(User user){
        int i = (int) (System.currentTimeMillis()/1000)%3600;
        user.setEmail(i+user.getEmail());
        user.setPhone(user.getPhone()+i/100);

        logger.info("Test starts with details:-->"+user.getName()+", "+user.getSurname()+", "+user.getEmail());
        app.regist().fillRegistrationForm(user);
        app.regist().agreeCheckBox();
        app.regist().pause(3000);
        app.regist().registerButton();

        Assert.assertTrue(app.regist().registrationSuccess(user));
        logger.info("Registration test passed Success");

    }
//    @AfterMethod(dependsOnMethods ={registrationPositive()})
//    public void postCondition(){
//        app.regist().clickReturn();
//        app.regist().refresh();
//    }
}
