import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.WaitUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static io.appium.java_client.AppiumBy.accessibilityId;


public class LoginScenarios_Positive_Negative_Test {

   private AndroidDriver androidDriver;

    @BeforeTest
    public  void setupAndroidDeviceCapabilities() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Pixel 9 Pro");
        options.setUdid("emulator-5554");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.MainActivity");


        URL appiumServerUrl = URI.create("http://127.0.0.1:4723").toURL();
        androidDriver = new AndroidDriver(appiumServerUrl,options);

    }
        @Test
        public void StartPositiveScenario(){

            WaitUtils waitUtils = new WaitUtils(androidDriver);

            waitUtils.waitForElementToBeVisible(accessibilityId("test-Username"));
            androidDriver.findElement(accessibilityId("test-Username")).sendKeys("standard_user");

            waitUtils.waitForElementToBeVisible(accessibilityId("test-Password"));
            androidDriver.findElement(accessibilityId("test-Password")).sendKeys("secret_sauce");

            waitUtils.waitForElementToBeVisible(accessibilityId("test-LOGIN"));
            androidDriver.findElement(accessibilityId("test-LOGIN")).click();


            waitUtils.waitForElementToBeVisible(By.xpath("//android.widget.TextView[@text='PRODUCTS']"));
            String assertedText = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='PRODUCTS']")).getText();

            Assert.assertEquals(assertedText,"PRODUCTS");
        }

        @Test
        public void StartNegativeScenario(){
            WaitUtils waitUtils = new WaitUtils(androidDriver);

            waitUtils.waitForElementToBeVisible(accessibilityId("test-Username"));
            androidDriver.findElement(accessibilityId("test-Username")).sendKeys("invalidUSerName");

            waitUtils.waitForElementToBeVisible(accessibilityId("test-Password"));
            androidDriver.findElement(accessibilityId("test-Password")).sendKeys("invalidPassword");

            waitUtils.waitForElementToBeVisible(accessibilityId("test-LOGIN"));
            androidDriver.findElement(accessibilityId("test-LOGIN")).click();

            waitUtils.waitForElementToBeVisible(By.xpath("//android.widget.TextView[@text='Username and password do not match any user in this service.']"));
            String errorMessage = androidDriver.findElement(By.xpath("//android.widget.TextView[@text='Username and password do not match any user in this service.']")).getText();
            Assert.assertEquals(errorMessage,"Username and password do not match any user in this service.");
        }

        @AfterTest
        public void tearDown(){
           if(androidDriver != null){
               androidDriver.quit();
           }

        }
}
