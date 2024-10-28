package com.ots.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader 
{
	
	public static Object[][] getDataFromExcel(String sheetName)
	{
		XSSFWorkbook wb = null;
		
		try 
		{
			wb = new XSSFWorkbook(new FileInputStream(new File("./TestData/testdata.xlsx")));
		} catch (FileNotFoundException e) {
			
			System.out.println("File not found "+e.getMessage());
			
		} catch (IOException e) {
			
			System.out.println("Could not read file");
		}
		
		int rowCount=wb.getSheet(sheetName).getPhysicalNumberOfRows();
		
		int column=wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
		
		Object [][] arr=new Object[rowCount-1][column];
		
		for(int i=1;i<rowCount;i++)
		{
			for(int j=0;j<column;j++)
			{
					String value="";
				
					CellType type=wb.getSheet(sheetName).getRow(i).getCell(j).getCellType();
					
					if(type==CellType.NUMERIC)
					{
						value=String.valueOf(wb.getSheet(sheetName).getRow(i).getCell(j).getNumericCellValue());
					}
					else if(type==CellType.STRING)
					{
						value=wb.getSheet(sheetName).getRow(i).getCell(j).getStringCellValue();
					}
					else if(type==CellType.BOOLEAN)
					{
						value=String.valueOf(wb.getSheet(sheetName).getRow(i).getCell(j).getBooleanCellValue());
					}
					else if(type==CellType.BLANK)
					{
						value="";
					}
					
				arr[i-1][j]=value;
			}


		}
		
		return arr;
		
	}

}
