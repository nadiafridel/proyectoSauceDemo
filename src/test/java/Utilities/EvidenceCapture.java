package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class EvidenceCapture {
	public static void evidenceCaptureinDoc(WebDriver driver, String pathImage, String nameDocument, String evidenceTitle) throws IOException, InvalidFormatException, InterruptedException {
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File imageFile = new File(pathImage);
		FileUtils.copyFile(screen, imageFile);  
		
		File file = new File(nameDocument); 
		XWPFDocument docx;
		
		if (!file.exists()) {
			docx = new XWPFDocument();
		} else {
			FileInputStream fileStream = new FileInputStream(file);
			docx = new XWPFDocument(fileStream);
		}

	    XWPFParagraph paragraph = docx.createParagraph();    
	    XWPFRun run = paragraph.createRun();
	    run.setText(evidenceTitle);
	    run.setFontSize(13);

	    InputStream pic = new FileInputStream(pathImage);
		run.addPicture(pic, Document.PICTURE_TYPE_PNG, pathImage, Units.toEMU(500), Units.toEMU(200));
	    pic.close();
	    
	    FileOutputStream out = new FileOutputStream(nameDocument);
	    docx.write(out);
	    out.flush();
	    out.close();
	    docx.close();

	    TimeUnit.SECONDS.sleep(1);
	}
	
	public static void writeTitleinDocument(String nameDocument, String evidenceTitle,  int fontSize) throws IOException, InvalidFormatException, InterruptedException {
	    File file = new File(nameDocument); 
		XWPFDocument docx;
		
		if (!file.exists()) {
			docx = new XWPFDocument();
		} else {
			FileInputStream fileStream = new FileInputStream(file);
			docx = new XWPFDocument(fileStream);
		}
		
	    XWPFParagraph paragraph = docx.createParagraph();    
	    XWPFRun run = paragraph.createRun();
	    run.setText(evidenceTitle);
	    run.setFontSize(fontSize);

	    FileOutputStream out = new FileOutputStream(nameDocument);
	    docx.write(out);
	    out.flush();
	    out.close();
	    docx.close();

	    TimeUnit.SECONDS.sleep(1);
	}
}
