package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();
        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(1);

        element = driver.findElement(By.linkText("login"));
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys("esa");
        element = driver.findElement(By.name("password"));
        element.sendKeys("esaesa");
        element = driver.findElement(By.name("login"));
        element.submit();
        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(1);

        element = driver.findElement(By.linkText("register new user"));
        element.click();
        element = driver.findElement(By.name("username"));
        Random r = new Random();
        element = driver.findElement(By.name("username"));
        element.sendKeys("esa"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("esaesa");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("esaesa");
        element = driver.findElement(By.name("signup"));
        element.submit();
        sleep(1);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(2);
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
