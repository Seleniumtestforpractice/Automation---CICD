package com.shakti.selenium.basecomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shakti.pageobject.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver = null;
	protected LandingPage landingPage=null;

	public WebDriver initializeDriver() throws FileNotFoundException, IOException {

		Properties properties = new Properties();
		properties.load(new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/com/shakti/resources/global-data.properties"));
		//String browserName = properties.getProperty("browser");
		String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):properties.getProperty("browser");
		
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	//@BeforeMethod(groups = {"ErrorHandling"})
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws FileNotFoundException, IOException {
		WebDriver driver = initializeDriver();
	    landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	//@AfterMethod(groups = {"ErrorHandling"})
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonToMap() throws IOException {
		
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/data/data.json"),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data;
	}
	
	//174 - ScreenShot Utility in Base Test
	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		File src =screenshot.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"//report//"+testCaseName+".png");
		FileUtils.copyFile(src, dest);
		
		return System.getProperty("user.dir")+"//report//"+testCaseName+".png";
	}
	
	
	
}
