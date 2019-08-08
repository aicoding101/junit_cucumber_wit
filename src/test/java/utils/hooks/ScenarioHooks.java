package utils.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import utils.Settings;
import utils.drivers.Driver;

import java.util.concurrent.TimeUnit;

public class ScenarioHooks {

    @Before
    public void startWebDriver()
    {
        String browser = Settings.BROWSER;
        switch (browser.trim().toUpperCase())
        {
            case "CHROME": Driver.Instance.startChrome(); break;
            case "IE" : Driver.Instance.startIE(); break;
            case "HEADLESS": Driver.Instance.startHeadless(); break;
            case "DEBUG": Driver.Instance.startChrome();
            default: // log some error message or start chrome by default
        }
        Driver.getDriver().manage().timeouts().implicitlyWait(Settings.IMPLICITWAITTIME, TimeUnit.SECONDS);
        Driver.getDriver().manage().window().maximize();
    }

    @After
    public  void stopWebDriver()
    {
        if(!Settings.BROWSER.trim().toUpperCase().equalsIgnoreCase("DEBUG"))
        {
            Driver.Instance.stopWebDriver();
        }
    }

}
