package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static XSSFCellStyle style;
	String path;
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	public int getRowCount(String xlsheet) throws IOException {
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int rowCount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public int getCellCount(String xlsheet,int rowCount) throws IOException {
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rowCount);
		int cellCount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
		
	}
	
	
	//Reading data from cell
	public String getCellValue(String xlsheet, int rowCount, int cellCount) throws IOException {
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rowCount);
		cell=row.getCell(cellCount);
		
		String data;
		try {
//			data=cell.toString();
			DataFormatter format=new DataFormatter();
			data=format.formatCellValue(cell);   //returns formatted value of cell as string
		}catch(Exception e) {
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}

	//Writing data into cell
	//Some times we add the data to existing sheet, so first open the sheet get cell and write into cell
	public void setCellData(String xlsheet, int rowCount, int cellCount, String data) throws IOException {
		File xlfile=new File(path);
		if(!xlfile.exists()) {
			//If file not exist, create new file and write data into it
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);
		}
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		
		//If sheet not exist, create new sheet
		if(wb.getSheetIndex(xlsheet)==1) {
			wb.createSheet(xlsheet);
		ws=wb.getSheet(xlsheet);
		}
		
		//If row not exist, create new row
		if(ws.getRow(rowCount)==null) {
			ws.createRow(rowCount);
		row=ws.getRow(rowCount);
		}
		
		cell=row.createCell(cellCount);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
	
	public void fillGreenColor(String xlfile, String xlsheet, int rowCount, int cellCouunt) throws IOException {
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rowCount);
		cell=row.getCell(cellCouunt);
		
		style=wb.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
	
	public void fillRedColor(String xlfile, String xlsheet, int rowCount, int cellCouunt) throws IOException {
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rowCount);
		cell=row.getCell(cellCouunt);
		
		style=wb.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fo=new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}

}
