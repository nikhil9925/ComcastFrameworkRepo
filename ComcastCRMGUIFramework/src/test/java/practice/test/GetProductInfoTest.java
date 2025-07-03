package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.flipkart.com/");

		// search product
		driver.findElement(By.name("q")).sendKeys(brandName, Keys.ENTER);

		// capture product info
		String x = "//div[text()='" + productName
				+ "']/ancestor::div[@class='yKfJKb row']/descendant::div[@class='Nx9bqj _4b5DiR']";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);

		driver.quit();
	}

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException{
		ExcelUtility elib=new ExcelUtility();
		int rowCount = elib.getRowCount("product");
		Object[][] objArr=new Object[rowCount][1];
		for(int i=0; i<rowCount; i++) {
			objArr[i][0]=elib.getDataFromExcel("product", i+1, 0);
			//objArr[i][1]=elib.getDataFromExcel("product", i+1, 1);
		}
		return objArr;
		
	}
}
