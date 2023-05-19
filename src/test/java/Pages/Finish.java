package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Finish {
	@FindBy(xpath="//input[@id='first-name']")
	WebElement name;
	
	@FindBy(xpath="//input[@id='last-name']")
	WebElement last;
	
	@FindBy(xpath="//input[@id='postal-code']")
	WebElement zip;
	
	@FindBy(xpath="//input[@id='continue']")
	WebElement contin;
	
	@FindBy(xpath="//button[@id='finish']")
	WebElement finish;
		
	WebDriver driver;

public Finish(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

public void writename(String word) {
	name.sendKeys(word);
}

public void writelast(String word) {
	last.sendKeys(word);
}

public void writezip(String code) {
	zip.sendKeys(code);
}

public void clickcontinue() {
	contin.click();
}

public void clickfinish() {
	finish.click();
}
}