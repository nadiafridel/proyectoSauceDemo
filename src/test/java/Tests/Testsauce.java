package Tests;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Pages.Finish;
import Pages.Start;
import Utilities.DataExcel;
import Utilities.EvidenceCapture;

public class Testsauce {
	String url="http://www.saucedemo.com";
	String chromeDriver="..\\SauceDemo\\drivers\\chromedriver.exe";
	String firefoxDriver="..\\SauceDemo\\drivers\\geckodriver.exe";
	WebDriver driver;
	
	String evidencePath="..\\SauceDemo\\Evidences\\";
	String nameDocument = "Evidences - SauceDemo.docx";
	    
@BeforeSuite
@Parameters("browser")
public void open(@Optional("chrome") String browser) {
	if(browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", chromeDriver);
		ChromeOptions option =new ChromeOptions();
		option.addArguments("incognito");
		driver = new ChromeDriver(option);
}	else if (browser.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver", firefoxDriver);
		driver=new FirefoxDriver();
}
		driver.get(url);
		driver.manage().window().maximize();
}


@Test(dataProvider="File data")
public void login(String email, String password) {
	Start starting = new Start(driver);
	starting.writename(email);
	starting.writepassword(password);
	starting.pressclick();
	starting.chooseproduct();
	starting.clickcart();
	starting.clickcheckout();
}

@DataProvider(name="File data")
public Object[][] Getdataexcel() throws Exception{
	return DataExcel.readExcel("..\\SauceDemo\\input\\Data.xlsx", "Hoja1");
}

@Test(dependsOnMethods ="login", dataProvider="File data2")
public void end(String name, String lastname, String code) throws InvalidFormatException, IOException, InterruptedException{
	Finish finishing = new Finish(driver);
	finishing.writename(name);
	finishing.writelast(lastname);
	finishing.writezip(code);
	finishing.clickcontinue();
	finishing.clickfinish();
	
	EvidenceCapture.writeTitleinDocument(evidencePath + nameDocument, "Evidences - SauceDemo", 20);
	EvidenceCapture.evidenceCaptureinDoc(driver, evidencePath + "img.jpg", evidencePath + nameDocument,"Successful purchase");
	
	String expectedresult = "Thank you for your order!";
	String actualresult = expectedresult;
	Assert.assertEquals(expectedresult, actualresult);;
}

@DataProvider(name="File data2")
public Object[][] Getdataexcel2() throws Exception{
	return DataExcel.readExcel("..\\SauceDemo\\input\\Data.xlsx", "Hoja2");
}
@AfterSuite
public void cerrarPagina() {
	driver.close();
}

}
