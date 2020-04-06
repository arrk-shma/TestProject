package trials;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ExcelToJson {
	
	public static void main (String [] args) throws InvalidFormatException{
		
		
		File excelFile = new File (".\\Friends.xlsx");
		JsonObject jo = getExcelDataAsJsonObject(excelFile);
		
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		   try (FileWriter writer = new FileWriter(".\\fromExcel.json")) {
	            gson.toJson(jo, writer);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			
	}
	
	
	
	
	

	public static JsonObject getExcelDataAsJsonObject(File excelFile) throws InvalidFormatException {
		JsonObject sheetsJsonObject = new JsonObject();

		Workbook workbook = null;

		// Read workbook using Apache POI
		try {
			workbook = new XSSFWorkbook(excelFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Iterate

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			JsonArray sheetArray = new JsonArray();
			ArrayList<String> columnNames = new ArrayList<String>();
			Sheet sheet = workbook.getSheetAt(i);
			Iterator<Row> sheetIterator = sheet.iterator();
			while (sheetIterator.hasNext()) {

				Row currentRow = sheetIterator.next();
				JsonObject jsonObject = new JsonObject();

				if (currentRow.getRowNum() != 0) {

					for (int j = 0; j < columnNames.size(); j++) {

						if (currentRow.getCell(j) != null) {
							if (currentRow.getCell(j).getCellTypeEnum() == CellType.STRING) {
								jsonObject.addProperty(columnNames.get(j),
										currentRow.getCell(j)
												.getStringCellValue());
							} else if (currentRow.getCell(j).getCellTypeEnum() == CellType.NUMERIC) {
								jsonObject.addProperty(columnNames.get(j),
										currentRow.getCell(j)
												.getNumericCellValue());
							} else if (currentRow.getCell(j).getCellTypeEnum() == CellType.BOOLEAN) {
								jsonObject.addProperty(columnNames.get(j),
										currentRow.getCell(j)
												.getBooleanCellValue());
							} else if (currentRow.getCell(j).getCellTypeEnum() == CellType.BLANK) {
								jsonObject.addProperty(columnNames.get(j), "");
							}
						} else {
							jsonObject.addProperty(columnNames.get(j), "");
						}

					}

					sheetArray.add(jsonObject);
				} else {
					// store column names
					for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
						columnNames.add(currentRow.getCell(k)
								.getStringCellValue());
					}
				}

			}

			sheetsJsonObject.add(workbook.getSheetName(i), sheetArray);

		}
		return sheetsJsonObject;

	}

}
