package com.me.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.me.entity.Excel;
import com.me.entity.Sheet;

public class ExcelReader {

	private static ExcelReader instance;

	private ExcelReader() {
	}

	public synchronized static ExcelReader getInstance() {
		if (instance == null) {
			instance = new ExcelReader();
		}
		return instance;
	}

	public Workbook getWorkbook(String path) {
		File file = null;
		if (path != null) {
			file = new File(path);
		}
		return getWorkbook(file);
	}

	public Workbook getWorkbook(File file) {
		try {
			String name = file.getName();
			FileInputStream in = new FileInputStream(file);
			if (name.endsWith(".xlsx")) {
				return new XSSFWorkbook(in);
			} else {
				POIFSFileSystem poifsFileSystem = new POIFSFileSystem(in);
				return new HSSFWorkbook(poifsFileSystem);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String getCellStringValue(Cell cell) {
		String value = null;
		switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_NUMERIC: // 数字
				value = String.valueOf(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_STRING: // 字符串
				value = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA: // 公式
				// value =
				// String.valueOf(cell.getCellFormula());
				value = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BLANK: // 空值
				value = "";
				break;
			case HSSFCell.CELL_TYPE_ERROR: // 故障
				value = "";
				break;
			default:
				value = "";
				break;
		}
		return value;
	}

	public void exportExcel(String path, Excel excel) throws IOException {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(path + File.separator + excel.getName() + "." + excel.getEx());
		if (!file.exists()) {
			file.createNewFile();
		}
		Workbook wb = new XSSFWorkbook();
		for (Sheet sheet : excel.getSheets()) {
			org.apache.poi.ss.usermodel.Sheet st = wb.createSheet(sheet.getTitle());
			List<List<String>> list = new ArrayList<List<String>>();
			list.add(sheet.getHeads());
			list.addAll(sheet.getContents());
			for (int i = 0; i < list.size(); i++) {
				Row row = st.createRow(i);
				for (int j = 0; j < list.get(i).size(); j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(list.get(i).get(j));
				}

			}
		}
		FileOutputStream out = new FileOutputStream(file);
		wb.write(out);
		// if (out != null) {
		// out.close();
		// }
	}
}
