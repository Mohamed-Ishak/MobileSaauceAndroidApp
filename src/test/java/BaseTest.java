import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class BaseTest {

    AndroidDriver androidDriver ;


    @BeforeClass
    public  void setupAndroidDeviceCapabilities(){

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Pixel 9 Pro");
        options.setUdid("emulator-5554");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.MainActivity");

        URL appiumServerUrl = null;
        try {
            appiumServerUrl = URI.create("http://127.0.0.1:4723").toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        androidDriver = new AndroidDriver(appiumServerUrl,options);

    }
}
