package com.scb.util;
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.InputStream;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Map;  
  
import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFDateUtil;  
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.ss.usermodel.WorkbookFactory;  
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
  
/** 
 * 操作Excel表格的功能类 
 */  
public class ExcelReader {  
      
    private Workbook wb;  
    private Sheet sheet;  
    private Row row;  
    private boolean isXssf = false;  
    public ExcelReader(InputStream is) {  
        try {  
            wb = WorkbookFactory.create(is);  
            if (wb instanceof XSSFWorkbook) {  
                isXssf = true;   
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (InvalidFormatException e) {  
            e.printStackTrace();  
        }  
    }  
    /** 
     * 读取Excel表格表头的内容 
     */  
    public String[] readExcelTitle() {  
        sheet = wb.getSheetAt(0);  
        row = sheet.getRow(0);  
        // 标题总列数  
        int colNum = row.getPhysicalNumberOfCells();  
        String[] title = new String[colNum];  
        for (int i = 0,length=colNum; i < length; i++) {  
            //title[i] = getStringCellValue(row.getCell((short) i));  
            title[i] = (String) getCellFormatValue1(row.getCell((short) i));  
           // String title1=sheet.getRow(0).getCell(1).toString();  
        }  
        sheet=wb.getSheetAt(0);  
        return title;  
    }  
  
    /** 
     * 读取Excel数据内容 
     */  
    public Map<Integer, String> readExcelContent() {  
        Map<Integer, String> content = new HashMap<Integer, String>();  
        String str = "";  
        sheet = wb.getSheetAt(0);  
        int rowNum = sheet.getLastRowNum();  
        row = sheet.getRow(0);  
        int colNum = row.getPhysicalNumberOfCells();  
        // 正文内容应该从第二行开始,第一行为表头的标题  
        for (int i = 1; i <= rowNum; i++) {  
            row = sheet.getRow(i);  
            int j = 0;  
            while (j < colNum) {  
                str += getCellFormatValue1(row.getCell((short) j)) + "    ";  
                j++;  
            }  
            content.put(i, str);  
            str = "";  
        }  
        return content;  
    }  
  
    /** 
     * 获取单元格数据内容为字符串类型的数据 
     */  
    private String getStringCellValue(HSSFCell cell) {  
        String strCell = "";  
        switch (cell.getCellType()) {  
            case HSSFCell.CELL_TYPE_STRING:  
                strCell = cell.getStringCellValue();  
                break;  
            case HSSFCell.CELL_TYPE_NUMERIC:  
                strCell = String.valueOf(cell.getNumericCellValue());  
                break;  
            case HSSFCell.CELL_TYPE_BOOLEAN:  
                strCell = String.valueOf(cell.getBooleanCellValue());  
                break;  
            case HSSFCell.CELL_TYPE_BLANK:  
                strCell = "";  
                break;  
            default:  
                strCell = "";  
                break;  
        }  
        if (strCell.equals("") || strCell == null) {  
            return "";  
        }  
        if (cell == null) {  
            return "";  
        }  
        return strCell;  
    }  
  
    /** 
     * 获取单元格数据内容为日期类型的数据 
     */  
    private String getDateCellValue(HSSFCell cell) {  
        String result = "";  
        try {  
            int cellType = cell.getCellType();  
            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {  
                Date date = cell.getDateCellValue();  
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)  
                        + "-" + date.getDate();  
            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {  
                String date = getStringCellValue(cell);  
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();  
            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {  
                result = "";  
            }  
        } catch (Exception e) {  
            System.out.println("日期格式不正确!");  
            e.printStackTrace();  
        }  
        return result;  
    }  
  
    /** 
     * 根据HSSFCell类型设置数据 
     */  
    private String getCellFormatValue(HSSFCell cell) {  
        String cellvalue = "";  
        if (cell != null) {  
            switch (cell.getCellType()) {  
                case HSSFCell.CELL_TYPE_NUMERIC:  
                case HSSFCell.CELL_TYPE_FORMULA: {  
                    // 判断当前的cell是否为Date  
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {  
                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00  
                        //cellvalue = cell.getDateCellValue().toLocaleString();  
                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12  
                        Date date = cell.getDateCellValue();  
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                        cellvalue = sdf.format(date);  
                    }  
                    // 如果是纯数字  
                    else {  
                        cellvalue = String.valueOf(cell.getNumericCellValue());  
                    }  
                    break;  
                }  
                // 如果当前Cell的Type为STRIN  
                case HSSFCell.CELL_TYPE_STRING:  
                    cellvalue = cell.getRichStringCellValue().getString();  
                    break;  
                // 默认的Cell值  
                default:  
                    cellvalue = " ";  
            }  
        } else {  
            cellvalue = "";  
        }  
        return cellvalue;  
  
    }  
    public Object getCellFormatValue1(Cell cell) {  
       Object cellvalue = null;  
         if (cell != null) {  
             switch (cell.getCellType()) {  
                case Cell.CELL_TYPE_NUMERIC:{  
                    cellvalue = cell.getNumericCellValue();  
                    String type = (String)getCellFormatValue1(cell.getRow().getCell(cell.getColumnIndex()+1));  
                    if("Date".equals(type)){  
                        cellvalue = cell.getDateCellValue();  
                    }else if("String".equals(type)){  
                        Double value = Double.parseDouble(cellvalue.toString());  
                        cellvalue=value.longValue()+"";  
                    }else if("Long".equals(type)){  
                        cellvalue = cell.getNumericCellValue()+"";  
                          
                    }else if("Double".equals(type)){  
                        cellvalue = cell.getNumericCellValue()+"";  
                          
                    }  
                    break;  
                }  
                case Cell.CELL_TYPE_FORMULA:{  
                    FormulaEvaluator evaluator = null;  
                    if(isXssf){  
                        evaluator = new XSSFFormulaEvaluator((XSSFWorkbook)cell.getRow().getSheet().getWorkbook());  
                    } else {  
                        evaluator = new HSSFFormulaEvaluator((HSSFWorkbook)cell.getRow().getSheet().getWorkbook());  
                    }  
                    cell.setCellFormula(cell.getCellFormula());  
                    Cell cell1 = evaluator.evaluateInCell(cell);  
                    cellvalue = getCellFormatValue1(cell1);  
                    break;  
                }  
                case Cell.CELL_TYPE_STRING:{  
                    cellvalue = cell.getRichStringCellValue().getString();  
    //              Object  obj = getCellFormatValue(cell.getRow().getCell(cell.getColumnIndex()+1));  
  //                String type = (String)obj;  
//                  if( "0".equals(cellvalue) && "String".equals(type)){  
//                      cellvalue = "";  
//                  }  
                    break;  
                }  
                default:  
                return null;  
            }  
         }   
         return cellvalue;  
     }  
    public static void main(String[] args) {  
        try {  
            InputStream is = new FileInputStream("C:\\Users\\1557881\\Desktop\\POI\\三级联动数据.xlsx");  
            ExcelReader excelReader = new ExcelReader(is);  
            String[] title = excelReader.readExcelTitle();  
            System.out.println("获得Excel表格的标题:");  
            for (String s : title) {  
                System.out.print(s + " ");  
            }  
            System.out.println("--------------");  
            Map<Integer, String> map = excelReader.readExcelContent();  
            System.out.println("获得Excel表格的内容:");  
            for (int i = 1; i <= map.size(); i++) {  
                System.out.println(map.get(i));  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
}  