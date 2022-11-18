


import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/*import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.documents.TextSelection;
*/

public class ExcelReader {
	
	public  String path;
	public  FileInputStream fis = null;
	public  FileOutputStream fileOut =null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row   =null;
	private XSSFCell cell = null;
	public static ExcelReader ex;
	
	public void ExcelReader1(String path) {
		
		this.path=path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
	}
	// returns the row count in a sheet
	public int getRowCount(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else{
		sheet = workbook.getSheetAt(index);
		int number=sheet.getLastRowNum()+1;
		return number;
		}
		
	}
// returns the data from a cell
	public String getCellData(String sheetName,String colName,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);
		int col_Num=-1;
		if(index==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				col_Num=i;
		}
		if(col_Num==-1)
			return "";
		
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(col_Num);
		
		if(cell==null)
			return "";
		
		if(cell.getCellType()==CellType.STRING)
			  return cell.getStringCellValue();
		else if(cell.getCellType()==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA ){
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			  if (HSSFDateUtil.isCellDateFormatted(cell)) {
		           
				  double d = cell.getNumericCellValue();

				  Calendar cal =Calendar.getInstance();
				  cal.setTime(HSSFDateUtil.getJavaDate(d));
		            cellText =
		             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                      cal.get(Calendar.MONTH)+1 + "/" + 
		                      cellText;
		           
		          

		         }

			  
			  
			  return cellText;
		  }else if(cell.getCellType()==CellType.BLANK)
		      return ""; 
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
		
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}
	// returns the data from a cell
	public String getCellData(String sheetName,int colNum,int rowNum){
		try{
			if(rowNum <=0)
				return "";
		
		int index = workbook.getSheetIndex(sheetName);

		if(index==-1)
			return "";
		
	
		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(colNum);
		if(cell==null)
			return "";
		
	  if(cell.getCellType()==CellType.STRING)
		  return cell.getStringCellValue();
	  else if(cell.getCellType()==CellType.NUMERIC || cell.getCellType()==CellType.FORMULA ){
		  
		  String cellText  = String.valueOf(cell.getNumericCellValue());
		  if (HSSFDateUtil.isCellDateFormatted(cell)) {
	           // format in form of M/D/YY
			  double d = cell.getNumericCellValue();

			  Calendar cal =Calendar.getInstance();
			  cal.setTime(HSSFDateUtil.getJavaDate(d));
	            cellText =
	             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
	           cellText = cal.get(Calendar.MONTH)+1 + "/" +
	                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
	                      cellText;
	           
	         

	         }

		  
		  
		  return cellText;
	  }else if(cell.getCellType()==CellType.BLANK)
	      return "";
	  else 
		  return String.valueOf(cell.getBooleanCellValue());
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}
	}
// returns true if data is set successfully else false
	public boolean setCellData(String sheetName,String colName,int rowNum, String data){
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		

		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			//System.out.println(row.getCell(i).getStringCellValue().trim());
			if(row.getCell(i).getStringCellValue().trim().equals(colName))
				colNum=i;
		}
		if(colNum==-1)
			return false;

		sheet.autoSizeColumn(colNum); 
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);

	    
	    cell.setCellValue(data);

	    fileOut = new FileOutputStream(path);

		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
// returns true if data is set successfully else false
	public boolean setCellData(String sheetName,String colName,int rowNum, String data,String url){
		
		try{
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);

		if(rowNum<=0)
			return false;
		
		int index = workbook.getSheetIndex(sheetName);
		int colNum=-1;
		if(index==-1)
			return false;
		
		
		sheet = workbook.getSheetAt(index);
		
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			
			if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
				colNum=i;
		}
		
		if(colNum==-1)
			return false;
		sheet.autoSizeColumn(colNum); 
		row = sheet.getRow(rowNum-1);
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);
			
	    cell.setCellValue(data);
	    XSSFCreationHelper createHelper = workbook.getCreationHelper();

	    //cell style for hyperlinks
	    
	    CellStyle hlink_style = workbook.createCellStyle();
	    XSSFFont hlink_font = workbook.createFont();
	    hlink_font.setUnderline(XSSFFont.U_SINGLE);
	    hlink_font.setColor(IndexedColors.BLUE.getIndex());
	    hlink_style.setFont(hlink_font);
	    //hlink_style.setWrapText(true);

	    XSSFHyperlink link = createHelper.createHyperlink(HyperlinkType.FILE);
	    link.setAddress(url);
	    cell.setHyperlink(link);
	    cell.setCellStyle(hlink_style);
	      
	    fileOut = new FileOutputStream(path);
		workbook.write(fileOut);

	    fileOut.close();	

		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
// returns true if sheet is created successfully else false
	public boolean addSheet(String  sheetname){		
		
		FileOutputStream fileOut;
		try {
			 workbook.createSheet(sheetname);	
			 fileOut = new FileOutputStream(path);
			 workbook.write(fileOut);
		     fileOut.close();		    
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	// returns true if sheet is removed successfully else false if sheet does not exist
	public boolean removeSheet(String sheetName){		
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return false;
		
		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();		    
		} catch (Exception e) {			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	// returns true if column is created successfully
	public boolean addColumn(String sheetName,String colName){
		
		
		try{				
			fis = new FileInputStream(path); 
			workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return false;
			
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		sheet=workbook.getSheetAt(index);
		
		row = sheet.getRow(0);
		if (row == null)
			row = sheet.createRow(0);
		
		
		if(row.getLastCellNum() == -1)
			cell = row.createCell(0);
		else
			cell = row.createCell(row.getLastCellNum());
	        
	        cell.setCellValue(colName);
	        cell.setCellStyle(style);
	        
	        fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();		    

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
		
		
	}
// removes a column and all the contents
	public boolean removeColumn(String sheetName, int colNum) {
		try{
		if(!isSheetExist(sheetName))
			return false;
		fis = new FileInputStream(path); 
		workbook = new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		XSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
		XSSFCreationHelper createHelper = workbook.getCreationHelper();
		style.setFillPattern(FillPatternType.NO_FILL);
		
	    
	
		for(int i =0;i<getRowCount(sheetName);i++){
			row=sheet.getRow(i);	
			if(row!=null){
				cell=row.getCell(colNum);
				if(cell!=null){
					cell.setCellStyle(style);
					row.removeCell(cell);
				}
			}
		}
		fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
	    fileOut.close();
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
// find whether sheets exists	
	public boolean isSheetExist(String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1){
			index=workbook.getSheetIndex(sheetName.toUpperCase());
				if(index==-1)
					return false;
				else
					return true;
		}
		else
			return true;
	}
// returns number of columns in a sheet	
	public int getColumnCount(String sheetName){
		// check if sheet exists
		if(!isSheetExist(sheetName))
		 return -1;
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		
		if(row==null)
			return -1;
		
		return row.getLastCellNum();
		
		
		
	}
//String sheetName, String testCaseName,String keyword ,String URL,String message
	public boolean addHyperLink(String sheetName,String screenShotColName,String testCaseName,int index,String url,String message){
		
		
		url=url.replace('\\', '/');
		if(!isSheetExist(sheetName))
			 return false;
		
	    sheet = workbook.getSheet(sheetName);
	    
	    for(int i=2;i<=getRowCount(sheetName);i++){
	    	if(getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)){
	    		
	    		setCellData(sheetName, screenShotColName, i+index, message,url);
	    		break;
	    	}
	    }


		return true; 
	}
	public int getCellRowNum(String sheetName,String colName,String cellValue){
		
		for(int i=2;i<=getRowCount(sheetName);i++){
	    	if(getCellData(sheetName,colName , i).equalsIgnoreCase(cellValue)){
	    		return i;
	    	}
	    }
		return -1;
		
	}
	
	public void CreateExcelonly(String filenam) {
		
		
		try{//declare file name to be create   
			String filename = filenam; 
			//creating an instance of HSSFWorkbook class  
			XSSFWorkbook workbook = new XSSFWorkbook();  
			//invoking creatSheet() method and passing the name of the sheet to be created   
			XSSFSheet sheet = workbook.createSheet("Sheet1");   
			//creating the 0th row using the createRow() method  
			XSSFRow rowhead = sheet.createRow((short)0);  
			//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
			rowhead.createCell(0).setCellValue("Remedations");  
			rowhead.createCell(1).setCellValue("Check");  
			/*	rowhead.createCell(2).setCellValue("compared1");  
			rowhead.createCell(3).setCellValue("2nd option server");  
			rowhead.createCell(4).setCellValue("2nd option excel"); 
			rowhead.createCell(5).setCellValue("compared2");  
			rowhead.createCell(6).setCellValue("3rd option server");  
			rowhead.createCell(7).setCellValue("3rd option excel");  
			rowhead.createCell(8).setCellValue("compared3");  
			rowhead.createCell(9).setCellValue("4th option server");
			rowhead.createCell(10).setCellValue("4th option excel");  
			rowhead.createCell(11).setCellValue("compared4");  
			rowhead.createCell(12).setCellValue("Question from server");  
			rowhead.createCell(13).setCellValue("Question from Excel");  
			rowhead.createCell(14).setCellValue("compared5");*/
		 
		
			/*XSSFSheet sheet2 = workbook.createSheet("Sheet2");
			XSSFRow rowhead1 = sheet2.createRow((short)0);
			rowhead1.createCell(0).setCellValue("Question are not matching need to check");  
			rowhead1.createCell(1).setCellValue("Option 1");  
			rowhead1.createCell(2).setCellValue("Option 2");  
			rowhead1.createCell(3).setCellValue("Option 3");  
			rowhead1.createCell(4).setCellValue("Option 4"); */
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();  
			//closing the workbook  
			workbook.close();  
			}   
			catch (Exception e)   
			{  
			e.printStackTrace();  
			}  
			
		}
			
	public static void CreateExcel(String filenam) {
		
		try{//declare file name to be create   
		String filename = filenam; 
		//creating an instance of HSSFWorkbook class  
		XSSFWorkbook workbook = new XSSFWorkbook();  
		//invoking creatSheet() method and passing the name of the sheet to be created   
		XSSFSheet sheet = workbook.createSheet("Sheet1");   
		//creating the 0th row using the createRow() method  
		XSSFRow rowhead = sheet.createRow((short)0);  
		//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method  
		rowhead.createCell(0).setCellValue("1 st option server");  
		rowhead.createCell(1).setCellValue("1 st option excel");  
		rowhead.createCell(2).setCellValue("compared1");  
		rowhead.createCell(3).setCellValue("2nd option server");  
		rowhead.createCell(4).setCellValue("2nd option excel"); 
		rowhead.createCell(5).setCellValue("compared2");  
		rowhead.createCell(6).setCellValue("3rd option server");  
		rowhead.createCell(7).setCellValue("3rd option excel");  
		rowhead.createCell(8).setCellValue("compared3");  
		rowhead.createCell(9).setCellValue("4th option server");
		rowhead.createCell(10).setCellValue("4th option excel");  
		rowhead.createCell(11).setCellValue("compared4");  
		rowhead.createCell(12).setCellValue("Question from server");  
		rowhead.createCell(13).setCellValue("Question from Excel");  
		rowhead.createCell(14).setCellValue("compared5");
	 
	
		XSSFSheet sheet2 = workbook.createSheet("Sheet2");
		XSSFRow rowhead1 = sheet2.createRow((short)0);
		rowhead1.createCell(0).setCellValue("Question are not matching need to check");  
		rowhead1.createCell(1).setCellValue("Option 1");  
		rowhead1.createCell(2).setCellValue("Option 2");  
		rowhead1.createCell(3).setCellValue("Option 3");  
		rowhead1.createCell(4).setCellValue("Option 4"); 
		FileOutputStream fileOut = new FileOutputStream(filename);
		workbook.write(fileOut);
		fileOut.close();  
		//closing the workbook  
		workbook.close();  
		}   
		catch (Exception e)   
		{  
		e.printStackTrace();  
		}  
		
	}
	public static void ExcelWriting(String path, String sname, int cindex, int rindex) throws FileNotFoundException, IOException {
		
		
		try (FileInputStream inp = new FileInputStream(path)) {
			//InputStream inp = new FileInputStream("workbook.xlsx");
			    Workbook wb = WorkbookFactory.create(inp);
			    Sheet sheet = wb.getSheet(sname);
			   
			   // cell.setCellValue("This is a test of merging");
			   
			   //XSSFCellStyle style = new XSSFCellStyle(new StylesTable());
			   // style.setFillForegroundColor(IndexedColors.RED.getIndex());
			    
			   CellStyle style = wb.createCellStyle();  
	            // Setting Background color  
			   style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());  
	            style.setFillPattern(FillPatternType.BIG_SPOTS);  
	            
	           //style.setFillPattern(FillPatternType.BIG_SPOTS); 
	          	            Row row = sheet.getRow(rindex);
			    Cell cell = row.getCell(cindex);
	            // cell.setCellValue(textfinded);
	            cell.setCellStyle(style);  
			    
			  //  cell.setCellStyle(style);
			    // Write the output to a file
			    try (FileOutputStream fileOut = new FileOutputStream(path)) {
			        wb.write(fileOut);
			    }
			
	}}
public static void ExcelWriting1(String path, String sname, int cindex, int rindex, String opt) throws FileNotFoundException, IOException {
		
		
		try (FileInputStream inp = new FileInputStream(path)) {
			///InputStream inp = new FileInputStream("workbook.xlsx");
			    Workbook wb = WorkbookFactory.create(inp);
			    Sheet sheet = wb.getSheet(sname);
			    Row row = sheet.getRow(rindex);
			 Cell cell=row.getCell(cindex);
			 
			 cell.setCellValue(opt);
			   
			   //XSSFCellStyle style = new XSSFCellStyle(new StylesTable());
			   // style.setFillForegroundColor(IndexedColors.RED.getIndex());
			    
			   CellStyle style = wb.createCellStyle();  
	            // Setting Background color  
			   style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());  
	            style.setFillPattern(FillPatternType.BIG_SPOTS);  
	            
	           //style.setFillPattern(FillPatternType.BIG_SPOTS); 
	          	          //  Row row = sheet.getRow(rindex);
			  //  Cell cell = row.getCell(cindex);
	            // cell.setCellValue(textfinded);
	           // cell.setCellStyle(style);  
	           // cell.setCellValue(opt);
			    
			  //  cell.setCellStyle(style);
			    // Write the output to a file
			    try (FileOutputStream fileOut = new FileOutputStream(path)) {
			        wb.write(fileOut);
			    }
			
	}}
// to run this on stand alone
public static int rowstoenter=2;
public static int exRow=2;
	public static int excelfinder(String examquestion, String Exduc, String sheet, String option[]) throws IOException{
		
		//ExcelReader ex =new ExcelReader(System.getProperty("user.dir")+"\\com.xlsx");
		ex =new ExcelReader();
		ex.ExcelReader1("com.xlsx");
		int correctOpt = 0;
		int r;
		ExcelReader datatable = null;
		System.out.println("Checking");

			 //datatable = new ExcelReader(Exduc+".xlsx");
		
		datatable = new ExcelReader();
		datatable.ExcelReader1(Exduc+".xlsx");
			 //System.out.println("Checking");
				for(int col=0 ;col< datatable.getColumnCount(sheet); col++){
					int totalrow=datatable.getRowCount(sheet);
					//System.out.println(totalrow);
					for( r=2;r< datatable.getRowCount(sheet)-1; r++){
						//System.out.println(datatable.getRowCount("Sheet1"));
						if((datatable.getCellData(sheet, col, 1)).equals("Question")) {
							 //System.out.println("Checking");
						String texttofind= datatable.getCellData(sheet, col, r).trim();
						System.out.println(texttofind);
						/*System.out.println(examquestion);
						System.out.println("excel working " +texttofind.equalsIgnoreCase("What happens when the tension load is increased beyond the initial preload force in a joint?"));
					try {	*/if(texttofind.equalsIgnoreCase(examquestion))
						{
						//ExcelWriting("Highlight.xlsx", "Sheet1", col, r);
				System.out.println("Imediate "+texttofind);
				//datatable.ExcelWriting("Tier 3 Training.xlsx", "Sheet2", 1, 1, );
						for (int mCol=col;mCol<10;mCol++) {
							/*System.out.println(r+"   "+mCol);
							String answer= datatable.getCellData("Sheet1", mCol, r);
							System.out.println("Checking "+answer);*/
						System.out.println("checking for excel"+texttofind);
							
							if((datatable.getCellData(sheet, mCol, r)).equals("true"))
							//if((datatable.getCellData(sheet, mCol, r)).
							{
								
								String answer= datatable.getCellData(sheet, mCol-1, r);
								
								try{
									
									
								String opt1 =datatable.getCellData(sheet, col+1, r).trim();
								ex.setCellData("Sheet1",  "1 st option server",exRow, option[0]);
								ex.setCellData("Sheet1",  "1 st option excel",exRow, opt1);
								if(opt1.equalsIgnoreCase(option[0])) {
								ex.setCellData("Sheet1",  "compared1",exRow, "TRUE");
								}else {
									ex.setCellData("Sheet1",  "compared1",exRow, "FALSE");
								}
								
								String opt2 =datatable.getCellData(sheet, col+3, r).trim();
								ex.setCellData("Sheet1",  "2nd option server",exRow, option[1]);
								ex.setCellData("Sheet1",  "2nd option excel",exRow, opt2);
								
								if(opt2.equalsIgnoreCase(option[1])) {
									ex.setCellData("Sheet1",  "compared2",exRow, "TRUE");
									}else {
										ex.setCellData("Sheet1",  "compared2",exRow, "FALSE");
									}
								
								String opt3 =datatable.getCellData(sheet, col+5, r).trim();
								ex.setCellData("Sheet1",  "3rd option server",exRow, option[2]);
								ex.setCellData("Sheet1",  "3rd option excel",exRow, opt3);
								
								if(opt3.equalsIgnoreCase(option[2])) {
									ex.setCellData("Sheet1",  "compared3",exRow, "TRUE");
									}else {
										ex.setCellData("Sheet1",  "compared3",exRow, "FALSE");
									}
								String opt4 =datatable.getCellData(sheet, col+7, r).trim();
								ex.setCellData("Sheet1",  "4th option server",exRow, option[3]);
								ex.setCellData("Sheet1",  "4th option excel",exRow, opt4);
								if(opt4.equalsIgnoreCase(option[3])) {
									ex.setCellData("Sheet1",  "compared4",exRow, "TRUE");
									}else {
										ex.setCellData("Sheet1",  "compared4",exRow, "FALSE");
									}
								
								ex.setCellData("Sheet1",  "Question from server",exRow, texttofind);
								ex.setCellData("Sheet1",  "Question from Excel",exRow, examquestion);
								if(texttofind.equalsIgnoreCase(examquestion)) {
									ex.setCellData("Sheet1",  "compared5",exRow, "TRUE");
									}else {
										ex.setCellData("Sheet1",  "compared5",exRow, "FALSE");
									}
								//datatable.ExcelWriting("V250MDC.xlsx", "Sheet1", col, r-1);
								}
								catch (Exception e) {
									System.out.println("Error wile writing");
									
								}exRow=exRow+1;
								
								if(mCol==3) {
									correctOpt=1;
								}else if(mCol==5) {
									correctOpt=2;
								}else if(mCol==7) {
									correctOpt=3;
								}else if(mCol==9) {
									correctOpt=4;
								}
								//System.out.println(texttofind);
								//System.out.println("Finala   "+answer + mCol);
							}
							
						}
						
						}
					
					
				
					/*}catch(Exception e) {
						
						
						System.out.println("failed reading and wirting");
						
						
					}*/
					}
					}
				}
				System.out.println("End "+correctOpt);
				
				if(correctOpt==0) {
					ex.setCellData("Sheet2",  "Question are not matching need to check",rowstoenter, examquestion);
					ex.setCellData("Sheet2",  "Option 1",rowstoenter, option[0]);
					ex.setCellData("Sheet2",  "Option 2",rowstoenter, option[1]);
					ex.setCellData("Sheet2",  "Option 3",rowstoenter, option[2]);
					ex.setCellData("Sheet2",  "Option 4",rowstoenter, option[3]);
					rowstoenter=rowstoenter+1;
				}
				return correctOpt;
	}
	public static WebDriver driver;
	public static void staging45(int id, String m, String duc, String sheet) throws InterruptedException, IOException {
Scanner s=new Scanner(System.in);

//WebDriverManager.chromedriver().setup();
System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\FrameworksProjects\\Sat\\src\\test\\java\\chromedriver2.exe");
ChromeOptions options = new ChromeOptions(); 
options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
driver = new ChromeDriver(options); 
driver.manage().deleteAllCookies();
driver.get("chrome://settings/clearBrowserData");
Thread.sleep(5000);
driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);

	
	driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		/*driver.get("chrome://settings/");
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		driver.executeScript("chrome.settingsPrivate.setDefaultZoom(1.5);")*/;
		
		driver.get("https://wabtecdev:wabtecdev@staging70.wabtecuniversity.com/lms/course/view.php?id="+id);
		
		
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.id("login-myBtn"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		
		Thread.sleep(1000);
		//driver.findElement(By.id("login-myBtn")).click();
		/*driver.findElement(By.id("login_username")).sendKeys("lisho_origin");
		driver.findElement(By.id("password")).sendKeys("Password#03");
		driver.findElement(By.id("submitbutton")).click();*/
		
		driver.findElement(By.id("username")).sendKeys("Lisho_origin");
		driver.findElement(By.id("password")).sendKeys("Password#03");
		driver.findElement(By.id("submitbutton")).click();
		//Lisho_origin
		//changeME2021
		
		driver.findElement(By.xpath("//*[@id='support']/div[1]/input[3]")).click();
		driver.findElement(By.xpath("//*[@id=\"course-view\"]/div[2]/div[1]/div[3]/div/div[3]/form[2]/button")).click();
		
		ExcelReader ex=new ExcelReader();
		boolean c=ex.isElementPresent(By.linkText(m));
		
		/*int k=5;
		while(c==false){
		WebElement scrol=driver.findElement(By.xpath("(//div/div[@class='ps-scrollbar-y-rail'])[3]"));
		 Actions act = new Actions(driver);               //Object of <em>Actions</em> class
		 
		 WebElement source=driver.findElement(By.xpath("(//div/div[@class='ps-scrollbar-y'])[3]"));
		act.moveToElement(scrol).dragAndDropBy(source, 0,k).build().perform(); //Page Down  
		c=ex.isElementPresent(By.linkText(m));
		
		k=k+10;
		
		}*/

		try {
		driver.findElement(By.linkText(m)).click();
		}catch (Exception e) {
			System.out.println("Please re enter the Course name: ");
			 m=s.nextLine().trim();
			 driver.findElement(By.linkText(m)).click();
			
		}
		
		
	try{
		driver.findElement(By.xpath("//*[@id=\"toggleHeaderBox1\"]/div/div[3]/input")).click();
	}catch(Exception e) {
		//2nd attempt
		driver.findElement(By.xpath("//*[@id=\"toggleHeaderBox1\"]/div/div[5]/input")).click();
	}
		Thread.sleep(1000);
		
		driver.switchTo().alert().accept();
		/*JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("document.body.style.zoom = '80%';");*/
		
		int i=1;
		int totalqus=5;
		int activeq;
		CreateExcel("com.xlsx");
		//for(int i=1;i<=250;i++)
		do {
		
		String examqus=(driver.findElement(By.xpath("//*[@class='qtext translatable']")).getText()).trim();
		String[] optionsc=new String[4];
				
			optionsc[0]	= driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span")).getText().replaceFirst("a.", "").trim();
				
			optionsc[1]	= driver.findElement(By.xpath("//tbody/tr[2]/td[1]/span")).getText().replaceFirst("b.", "").trim();
			optionsc[2]	= driver.findElement(By.xpath("//tbody/tr[3]/td[1]/span")).getText().replaceFirst("c.", "").trim();
			optionsc[3]	= driver.findElement(By.xpath("//tbody/tr[4]/td[1]/span")).getText().replaceFirst("d.", "").trim();
				
		int option=excelfinder(examqus,duc,sheet, optionsc);
		
		/*Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='reportBox1']/tbody/tr[2]/td/span/span/label")).click();*/
		//*[@id="reportBox1"]/tbody/tr[2]  //*[@id="reportBox1"]/tbody/tr[2]/td/span/span/label
		//Thread.sleep(2000);
		
		//Option select
		try {
		driver.findElement(By.xpath("//*[@id='reportBox1']/tbody/tr["+option+"]/td/span/span/label")).click();
		}catch (Exception e) {
			//driver.findElement(By.xpath("//*[@id='reportBox1']/tbody/tr[1]/td/span/span/label")).click();
			System.out.println("Faild : "+i+": "+ examqus);
		}
	
		//driver.findElement(By.xpath("//*[@id=\"mod-quiz-attempt\"]/div[2]/div[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"mod-quiz-attempt\"]/div[2]/div[2]")).click();
		
	Thread.sleep(1500);
		for(int j=0;j<9;j++) {
			Actions dragger = new Actions(driver);
		dragger.sendKeys(Keys.ARROW_DOWN).build().perform();
		}
		Thread.sleep(1000);
		
		
		String a=driver.findElement(By.xpath("//ul/li[@class='active']")).getText();
		activeq=Integer.parseInt(a);
		
		if(activeq<totalqus) {
		
		String d=driver.findElement(By.xpath("//ul/li[20]/a")).getText();
		totalqus=Integer.parseInt(d);
		driver.findElement(By.linkText("Next")).click();
		}
		System.out.println(activeq+"    "+totalqus);
		i++;
		}while(activeq<totalqus);
		
	}
	public static void stagingPreview(int id, String m, String duc, String sheet) throws InterruptedException, IOException {
		Scanner s=new Scanner(System.in);
				
			


		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\FrameworksProjects\\Sat\\src\\test\\java\\chromedriver2.exe");
		ChromeOptions options = new ChromeOptions(); 
		driver=new ChromeDriver(options);
		
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
		driver = new ChromeDriver(options); 
		driver.manage().deleteAllCookies();
		driver.get("chrome://settings/clearBrowserData");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);

			
			driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	/*	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				options.setExperimentalOption("useAutomationExtension", false);*/
				
				//System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				//driver=new ChromeDriver(options);
				//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				/*driver.get("chrome://settings/");
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				driver.executeScript("chrome.settingsPrivate.setDefaultZoom(1.5);")*/;
			
				driver.get("https://wabtecdev:wabtecdev@staging70.wabtecuniversity.com/lms/course/view.php?id="+id);
				
				
				
				WebElement element = driver.findElement(By.id("login-myBtn"));
				Actions actions = new Actions(driver);
				actions.moveToElement(element).click().build().perform();
				
				Thread.sleep(1000);
				//driver.findElement(By.id("login-myBtn")).click();
				//driver.findElement(By.id("username")).sendKeys("Lisho_origin");
				driver.findElement(By.id("username")).sendKeys("Lisho_origin");
				driver.findElement(By.id("password")).sendKeys("Password#03");
				driver.findElement(By.id("submitbutton")).click();
				//Lisho_origin
				//changeME2021
				//driver.findElement(By.xpath("//*[@id='support']/div[1]/input[3]")).click();
				//driver.findElement(By.xpath("//*[@id=\"course-view\"]/div[2]/div[1]/div[3]/div/div[3]/form[2]/button")).click();
				//driver.findElement(By.xpath("//*[@title='Student View On']")).click();
				ExcelReader ex=new ExcelReader();
				ex.ExcelReader1("com.xlsx");
				boolean c=false;
				/*
				int i=5;
				do {
				WebElement scrol=driver.findElement(By.xpath("(//div/div[@class='ps-scrollbar-y-rail'])[3]"));
				 Actions act = new Actions(driver);               //Object of <em>Actions</em> class
				 
				WebElement source=driver.findElement(By.xpath("(//div/div[@class='ps-scrollbar-y'])[3]"));
				act.moveToElement(scrol).dragAndDropBy(source, 0,i).build().perform(); //Page Down  
				c=ex.isElementPresent(By.linkText(m));
				
				i=i+10;
				
				}while(c==false);
				*/	
					try {
				driver.findElement(By.linkText(m)).click();
					//driver.findElement(By.xpath("//*[@id=\"toggleHeaderBox2\"]/div[2]/ul/li[5]")).click();
				}catch (Exception e) {
					
					System.out.println("Please re enter the Course name: ");
					 m=s.nextLine().trim();
					 driver.findElement(By.linkText(m)).click();
				
				}
				driver.findElement(By.linkText("Preview")).click();
				
				
				CreateExcel("com.xlsx");
				//for(int k=1;k<=250;k++)
				int k=1;
				int activeq;
				int totalqus=5;
				boolean next=true;
				
				do {
				WebDriverWait wait = new WebDriverWait(driver,30);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='qtext translatable']")));	
					
				String examqus=(driver.findElement(By.xpath("//*[@class='qtext translatable']")).getText()).trim();
				//System.out.println(examqus);
				
				boolean inside=false;
				
				int insides=38;
				//scroll
				do {
					
					WebElement h=driver.findElement(By.xpath("//*[@class='qtext translatable']"));
					//driver.findElement(By.xpath("//*[@id='page-content-wrapper']")).sendKeys(Keys.ARROW_DOWN);
					Actions move = new Actions(driver);
					//here you specify the condition for the scrolling length 
					move.click(h).sendKeys(Keys.ARROW_DOWN).build().perform();
					inside=ex.isElementPresent(By.xpath("//div[2]/div[1]/div[2]/div[4]/div/label"));
				inside=ex.isElementPresent(By.linkText("Next"));
				System.out.println(inside +"check scroll");
				insides=insides+5;
				
				}while(inside==false);
				
				
				String[] optionsc=new String[4];
						
					optionsc[0]	= driver.findElement(By.xpath("//div[2]/div[1]/div[2]/div[1]/div/label")).getText().replaceFirst("A", "").trim();
					optionsc[1]	= driver.findElement(By.xpath("//div[2]/div[1]/div[2]/div[2]/div/label")).getText().replaceFirst("B", "").trim();
					optionsc[2]	= driver.findElement(By.xpath("//div[2]/div[1]/div[2]/div[3]/div/label")).getText().replaceFirst("C", "").trim();
					optionsc[3]	= driver.findElement(By.xpath("//div[2]/div[1]/div[2]/div[4]/div/label")).getText().replaceFirst("D", "").trim();
						
				int option=excelfinder(examqus,duc,sheet,optionsc);
				
				/*Thread.sleep(5000);
				//driver.findElement(By.xpath("//*[@id='reportBox1']/tbody/tr[2]/td/span/span/label")).click();*/
				//*[@id="reportBox1"]/tbody/tr[2]  //*[@id="reportBox1"]/tbody/tr[2]/td/span/span/label
				//Thread.sleep(2000);
				System.out.println("out   " +option);
				//Option select
				//*[@id="q22918"]/div[2]/div[1]/div[2]/div[3]
				//*[@id='reportBox1']/tbody/tr["+option+"]/td/span/span/label
				
					boolean checkopt=ex.isElementPresent(By.xpath("//div[2]/div[1]/div[2]/div["+option+"]/div/label"));
				if (checkopt==true) {
					driver.findElement(By.xpath("//div[2]/div[1]/div[2]/div["+option+"]/div/label")).click();
				}else{
					//driver.findElement(By.xpath("//*[@id='reportBox1']/tbody/tr[1]/td/span/span/label")).click();
				System.out.println("Faild : "+k+": "+ examqus);
				
				
				//System.out.println("Excel:"+ texttofind );
				
				}
				try {
				driver.findElement(By.linkText("Next")).click();
				}catch(Exception e) {
					next=false;
				}
				}while (next==true);
				/*String a=driver.findElement(By.xpath("//ul/li[@class='active']")).getText();
				activeq=Integer.parseInt(a);*/
				
				
				//
			/*if(activeq<totalqus) {
					String b=driver.findElement(By.xpath("//ul/li[20]/a")).getText();
					totalqus=Integer.parseInt(b);
				driver.findElement(By.linkText("Next")).click();
				}
				System.out.println( "     "+totalqus);
				k++;
				}while(activeq<totalqus);
				*/
				k++;	
				}	
			
	public boolean isElementPresent(By by) {
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		try {
			//wait.withTimeout(2, TimeUnit.SECONDS);  //*[@class="text-center button_bdr_top button_bottom"]/a[2] //*[contains(@class,'text-center button_bdr_top button_bottom')]/a[2]
			driver.findElement(by);
			return true;
			
		}catch (Exception e) {
		
		return false;
		}
	}
	public static void staging70(int id, String m, String duc, String sheet) throws InterruptedException, IOException {
		Scanner s=new Scanner(System.in);

		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\FrameworksProjects\\Sat\\src\\test\\java\\chromedriver2.exe");
		ChromeOptions options = new ChromeOptions(); 
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
		driver = new ChromeDriver(options); 
		driver.manage().deleteAllCookies();
		driver.get("chrome://settings/clearBrowserData");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);

			
			driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				/*driver.get("chrome://settings/");
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				driver.executeScript("chrome.settingsPrivate.setDefaultZoom(1.5);")*/;
				
				driver.get("https://wabtecdev:wabtecdev@staging70.wabtecuniversity.com/lms/course/view.php?id="+id);
				
				
				Thread.sleep(2000);
				WebElement element = driver.findElement(By.id("login-myBtn"));
				Actions actions = new Actions(driver);
				actions.moveToElement(element).click().build().perform();
				
				Thread.sleep(1000);
				//driver.findElement(By.id("login-myBtn")).click();
				/*driver.findElement(By.id("login_username")).sendKeys("lisho_origin");
				driver.findElement(By.id("password")).sendKeys("Password#03");
				driver.findElement(By.id("submitbutton")).click();*/
				
				driver.findElement(By.id("username")).sendKeys("Lisho_origin");
				driver.findElement(By.id("password")).sendKeys("Password#03");
				driver.findElement(By.id("submitbutton")).click();
				//Lisho_origin
				//changeME2021
				
				/*driver.findElement(By.xpath("//*[@id='support']/div[1]/input[3]")).click();
				driver.findElement(By.xpath("//*[@id=\"course-view\"]/div[2]/div[1]/div[3]/div/div[3]/form[2]/button")).click();
				*/
				ExcelReader ex=new ExcelReader();
				boolean c=ex.isElementPresent(By.linkText(m));
				
				/*int k=5;
				while(c==false){
				WebElement scrol=driver.findElement(By.xpath("(//div/div[@class='ps-scrollbar-y-rail'])[3]"));
				 Actions act = new Actions(driver);               //Object of <em>Actions</em> class
				 
				 WebElement source=driver.findElement(By.xpath("(//div/div[@class='ps-scrollbar-y'])[3]"));
				act.moveToElement(scrol).dragAndDropBy(source, 0,k).build().perform(); //Page Down  
				c=ex.isElementPresent(By.linkText(m));
				
				k=k+10;
				
				}*/

				try {
				driver.findElement(By.linkText(m)).click();
				}catch (Exception e) {
					System.out.println("Please re enter the Course name: ");
					 m=s.nextLine().trim();
					 driver.findElement(By.linkText(m)).click();
					
				}
				driver.findElement(By.linkText("Preview")).click();
				
			/*try{
				driver.findElement(By.xpath("//*[@id=\"toggleHeaderBox1\"]/div/div[3]/input")).click();
			}catch(Exception e) {
				//2nd attempt
				driver.findElement(By.xpath("//*[@id=\"toggleHeaderBox1\"]/div/div[5]/input")).click();
			}*/
				Thread.sleep(1000);
				
				//driver.switchTo().alert().accept();
				/*JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("document.body.style.zoom = '80%';");*/
				
				int i=1;
				int totalqus=5;
				int activeq;
				CreateExcel("com.xlsx");
				//for(int i=1;i<=250;i++)
				do {
				
				String examqus=(driver.findElement(By.xpath("//*[@class='qtext translatable']")).getText()).trim();
				String[] optionsc=new String[4];
						
					/*optionsc[0]	= driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span")).getText().replaceFirst("a.", "").trim();
						
					optionsc[1]	= driver.findElement(By.xpath("//tbody/tr[2]/td[1]/span")).getText().replaceFirst("b.", "").trim();
					optionsc[2]	= driver.findElement(By.xpath("//tbody/tr[3]/td[1]/span")).getText().replaceFirst("c.", "").trim();
					optionsc[3]	= driver.findElement(By.xpath("//tbody/tr[4]/td[1]/span")).getText().replaceFirst("d.", "").trim();
						*/
					optionsc[0]	= driver.findElement(By.xpath("//div[2]/div[1]/div[2]/div[1]/div/label")).getText().replaceFirst("A", "").trim();
					optionsc[1]	= driver.findElement(By.xpath("//div[2]/div[1]/div[2]/div[2]/div/label")).getText().replaceFirst("B", "").trim();
					optionsc[2]	= driver.findElement(By.xpath("//div[2]/div[1]/div[2]/div[3]/div/label")).getText().replaceFirst("C", "").trim();
					optionsc[3]	= driver.findElement(By.xpath("//div[2]/div[1]/div[2]/div[4]/div/label")).getText().replaceFirst("D", "").trim();
						
				int option=excelfinder(examqus,duc,sheet, optionsc);
				
				/*Thread.sleep(5000); //*[@id="q38374"]/div[2]/div[1]/div[2]/div[2]/div/label
				driver.findElement(By.xpath("//*[@id='reportBox1']/tbody/tr[2]/td/span/span/label")).click();*/
				//*[@id="reportBox1"]/tbody/tr[2]  //*[@id="reportBox1"]/tbody/tr[2]/td/span/span/label
				//Thread.sleep(2000);
				
				//Option select
				
				try {
				//driver.findElement(By.xpath("//*[@id='reportBox1']/tbody/tr["+option+"]/td/span/span/label")).click();
				driver.findElement(By.xpath("//div[2]/div[1]/div[2]/div["+option+"]/div/label")).click();
				}catch (Exception e) {
					//driver.findElement(By.xpath("//*[@id='reportBox1']/tbody/tr[1]/td/span/span/label")).click();
					System.out.println("Faild : "+i+": "+ examqus);
				}
			
				//driver.findElement(By.xpath("//*[@id=\"mod-quiz-attempt\"]/div[2]/div[2]")).click();
				driver.findElement(By.xpath("//*[@id=\"mod-quiz-attempt\"]/div[2]/div[2]")).click();
				
			Thread.sleep(1500);
				for(int j=0;j<9;j++) {
					Actions dragger = new Actions(driver);
				dragger.sendKeys(Keys.ARROW_DOWN).build().perform();
				}
				Thread.sleep(1000);
				
				
				String a=driver.findElement(By.xpath("//ul/li[@class='active']")).getText();
				activeq=Integer.parseInt(a);
				
				if(activeq<totalqus) {
				
				String d=driver.findElement(By.xpath("//ul/li[20]/a")).getText();
				totalqus=Integer.parseInt(d);
				driver.findElement(By.linkText("Next")).click();
				}
				System.out.println(activeq+"    "+totalqus);
				i++;
				}while(activeq<totalqus);
				
			}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//staging45();
		
		stagingPreview(547, "ES30ACi - Electrical Systems - Advanced - Exam","250 Series Diesel Engine Advanced (3)","Sheet1");
		
		
		

	}

}

