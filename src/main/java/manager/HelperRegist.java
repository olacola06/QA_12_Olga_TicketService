package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HelperRegist extends HelperBase{

    public HelperRegist(WebDriver wd){
        super(wd);
    }
    public void clickRegister(){
        click(By.xpath("//button[text()=' Register']"));
    }

    public void fillRegistrationForm(User user){
        type(By.cssSelector("input[placeholder='Name*']"),user.getName());
        type(By.cssSelector("input[placeholder='Surname*'"),user.getSurname());
        type(By.cssSelector("input[placeholder='Email*'"),user.getEmail());
        type(By.cssSelector("input[placeholder='Password*'"),user.getPassword());
        type(By.cssSelector("input[placeholder='Confirm password*'"),user.getConfirmPassword());
        type(By.cssSelector("input[placeholder='Phone number*'"),user.getPhone());
    }

    public void agreeCheckBox() {
        if (!wd.findElement(By.cssSelector("label[for='c1'].label-for-checkbox")).isSelected()) {
            click(By.cssSelector("label[for='c1'].label-for-checkbox"));
        }
    }
    public void registerButton(){
        click(By.xpath("//button[text()=' Register']"));
    }
    public void returnToRegistrationBtn(){
        wd.findElement(By.xpath("//*[text()=' Return to registration']")).click();
    }

    public boolean registrationSuccess(User user){
        String message = wd.findElement(By.xpath("//p[@class = 'fl-login f35 text-center']")).getText();
        return (message.contains("was successful") & message.contains(user.getEmail()));
    }
    public boolean registrationFail(){
        String message = wd.findElement(By.xpath("//p[@class = 'fl-login f35 text-center']")).getText();
        System.out.println(message.toString());
        return (message.contains("Something went wrong!"));
    }
}
