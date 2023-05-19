package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Start {
	@FindBy(id="user-name")
	WebElement name;
	
	@FindBy(id="password")
	WebElement pass;
	
	@FindBy(id="login-button")
	WebElement login;
	
	@FindBy (xpath="//button[@id='add-to-cart-sauce-labs-backpack']")
	WebElement product;
	
	@FindBy (xpath="//body/div[@id='root']/div[@id='page_wrapper']/div[@id='contents_wrapper']/div[@id='header_container']/div[1]/div[3]/a[1]")
	WebElement cart;
	
	@FindBy (xpath="//button[@id='checkout']")
	WebElement checkout;
	
	WebDriver driver;
	

public Start(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

public void writename(String palabra) {
	name.sendKeys(palabra);
}

public void writepassword(String password) {
	pass.sendKeys(password);
}

public void pressclick() {
	login.click();
}

public void chooseproduct()	{
	product.click();
}

public void clickcart() {
	cart.click();
}

public void clickcheckout() {
	checkout.click();
}

}
