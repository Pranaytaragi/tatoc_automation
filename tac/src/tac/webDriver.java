package tac;

import java.util.List;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;		
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;		

 class webDriver {				
    		
    public static void main(String[] args) {									
//        String baseUrl = "10.01.86/tatoc";					
        System.setProperty("webdriver.chrome.driver","C:\\Users\\pranaytaragi\\Downloads\\chromedriver_win32\\chromedriver.exe");					
        WebDriver driver = new ChromeDriver();					
        //1 page		
        driver.get("http://10.0.1.86/tatoc");
        //2 page
        driver.findElement(By.tagName("a")).click();
        //3 page
        driver.findElement(By.className("greenbox")).click();
        //4 page
        driver.switchTo().frame(driver.findElement(By.id("main")));
        WebElement box1=driver.findElement(By.id("answer"));
        String color1=box1.getAttribute("class");
        driver.switchTo().frame(driver.findElement(By.id("child")));
        WebElement box2=driver.findElement(By.id("answer"));
        String color2=box2.getAttribute("class");
        while(!color1.equals(color2))
        {
        	driver.switchTo().defaultContent();
        	driver.switchTo().frame(driver.findElement(By.id("main")));
        	driver.findElement(By.tagName("a")).click();
        	driver.switchTo().frame(driver.findElement(By.id("child")));
        	box2=driver.findElement(By.id("answer"));
        	color2=box2.getAttribute("class");
        }
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.id("main")));
        List<WebElement> anchors=driver.findElements(By.tagName("a"));
        anchors.get(1).click();
        //5 page
        Actions builder = new Actions(driver);
        Action dragAndDrop = builder.clickAndHold(driver.findElement(By.id("dragbox"))).moveToElement((driver.findElement(By.id("dropbox")))).release((driver.findElement(By.id("dropbox")))).build();
        dragAndDrop.perform();
        driver.findElement(By.tagName("a")).click();
        //5 page
        String  handle= driver.getWindowHandle();
        List<WebElement> anchor=driver.findElements(By.tagName("a"));
        anchor.get(0).click();
        driver.switchTo().window("popup");
        driver.findElement(By.id("name")).sendKeys("Pranay");
        driver.findElement(By.id("submit")).click();
        driver.switchTo().window(handle);
        anchor.get(1).click();
        //6 page
        List<WebElement> anchor1=driver.findElements(By.tagName("a"));
        anchor1.get(0).click();
        String tokens=driver.findElement(By.id("token")).getText();
        String tokens1=tokens.replace("Token: ", "");
        Cookie tactoc= new Cookie("Token",tokens1);
        driver.manage().addCookie(tactoc);
        anchor1.get(1).click();
        
        
       // driver.quit();			
    }		

}