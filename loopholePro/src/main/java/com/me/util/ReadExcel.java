package com.me.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.me.entity.Words;


public class ReadExcel {

	public List<Words> readXls(int bookTag,String filePath) throws IOException {
		InputStream is = new FileInputStream(filePath);
		@SuppressWarnings("resource")
		XSSFWorkbook   hssfWorkbook = new XSSFWorkbook (is);
		Words Words = null;
		List<Words> list = new ArrayList<Words>();
		System.out.println(hssfWorkbook.getNumberOfSheets()+"------");
//		int bookTag = 5;
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			
			// 循环行Row
			System.out.println(hssfSheet.getLastRowNum());
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					Words = new Words();
					XSSFCell word = hssfRow.getCell(0);
					XSSFCell pronunciation = hssfRow.getCell(1);
					XSSFCell prop = hssfRow.getCell(2);
					XSSFCell maleVoice = hssfRow.getCell(3);
					XSSFCell meaning = hssfRow.getCell(5);
					XSSFCell synonym1 = hssfRow.getCell(6);
					XSSFCell synonym2 = hssfRow.getCell(7);
					XSSFCell synonym3 = hssfRow.getCell(8);
					XSSFCell synonym4 = hssfRow.getCell(9);
					XSSFCell synonym5 = hssfRow.getCell(10);
					XSSFCell antonym1 = hssfRow.getCell(11);
					XSSFCell antonym2 = hssfRow.getCell(12);
					XSSFCell antonym3 = hssfRow.getCell(13);
					XSSFCell antonym4 = hssfRow.getCell(14);
					XSSFCell antonym5 = hssfRow.getCell(15);
					/*XSSFCell root = hssfRow.getCell(2);
					XSSFCell rootMeaning = hssfRow.getCell(3);
					XSSFCell handoutPage = hssfRow.getCell(4);*/
					try {
						
						Words.setWord(getValue(word));
					} catch (Exception e) {
						continue;
					}
					Words.setBookTag(bookTag);
					try {
						
						Words.setProp(getValue(prop));
					} catch (Exception e) {
						Words.setProp("");
					}
					try {
						Words.setMeaning(getValue(meaning));
						
					} catch (Exception e) {
						Words.setMeaning("");
					}
					
					try {
						
						Words.setPronunciation(getValue(pronunciation));
					} catch (Exception e) {
						Words.setPronunciation("");
					}
					try {
						Words.setMaleVoice(getValue(maleVoice));
						
					} catch (Exception e) {
						Words.setMaleVoice("");
					}
					StringBuffer synonym = new StringBuffer();
					StringBuffer antonym = new StringBuffer();
					if(synonym1!=null&&!"".equals(synonym1)){
						synonym.append(getValue(synonym1)+",");
					}
					if(synonym2!=null&&!"".equals(synonym2)){
						synonym.append(getValue(synonym2)+",");
					}
					if(synonym3!=null&&!"".equals(synonym3)){
						synonym.append(getValue(synonym3)+",");
					}
					if(synonym4!=null&&!"".equals(synonym4)){
						synonym.append(getValue(synonym4)+",");
					}
					if(synonym5!=null&&!"".equals(synonym5)){
						synonym.append(getValue(synonym5)+",");
					}
					try {
						
						Words.setSynonym(synonym.toString().substring(0,synonym.toString().length()-1));
					} catch (Exception e) {
						Words.setSynonym(",");
					}
					if(antonym1!=null&&!"".equals(antonym1)){
						antonym.append(getValue(antonym1)+",");
					}
					if(antonym2!=null&&!"".equals(antonym2)){
						antonym.append(getValue(antonym2)+",");
					}
					if(antonym3!=null&&!"".equals(antonym3)){
						antonym.append(getValue(antonym3)+",");
					}
					if(antonym4!=null&&!"".equals(antonym4)){
						antonym.append(getValue(antonym4)+",");
					}
					if(antonym5!=null&&!"".equals(antonym5)){
						antonym.append(getValue(antonym5)+",");
					}
					try {
						
						Words.setAntonym(antonym.toString().substring(0,antonym.toString().length()-1));
					} catch (Exception e) {
						Words.setAntonym(",");
					}
					list.add(Words);
				}
			}
			bookTag++;
		}
		System.out.println(list.get(1).getWord());
		return list;
	}
	
	 @SuppressWarnings("static-access")
	private String getValue(XSSFCell maleVoice) {
	        if (maleVoice.getCellType() == maleVoice.CELL_TYPE_BOOLEAN) {
	            // 返回布尔类型的值
	            return String.valueOf(maleVoice.getBooleanCellValue());
	        } else if (maleVoice.getCellType() == maleVoice.CELL_TYPE_NUMERIC) {
	            // 返回数值类型的值
	            return String.valueOf(maleVoice.getNumericCellValue());
	        } else {
	            // 返回字符串类型的值
	            return String.valueOf(maleVoice.getStringCellValue());
	        }
	    }
}