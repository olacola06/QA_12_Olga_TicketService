package tests;

import manager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void init(){
        app.start();
    }

//    @AfterSuite
//    public void tearDown(){
//        app.stop();
//    }

}
