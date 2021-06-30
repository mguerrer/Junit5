package cl.set.junit5;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ReadDataFromExcel {

    public List<Map<String, Map.Entry<String, String>>> readData(File file, String sheetName, String templateSheet)
            throws IOException {

        FileInputStream inputStream = new FileInputStream(file);
        HSSFWorkbook hssfw = new HSSFWorkbook(inputStream);

        Map<String, Map.Entry<String, String>> templateDataMap = getTemplateDataMap(hssfw, templateSheet);

        HSSFSheet sheet = hssfw.getSheet(sheetName);

        List<CellRangeAddress> mergedRegionList = new ArrayList<CellRangeAddress>();

        int mergedRegionsNumber = sheet.getNumMergedRegions();

        Set<Integer> mergedRowNumSet = new TreeSet<Integer>();
        for (int i = 0; i < mergedRegionsNumber; i++) {
            CellRangeAddress cellRangeAddress = sheet.getMergedRegion(i);
            int firstRow = cellRangeAddress.getFirstRow();
            int lastRow = cellRangeAddress.getLastRow();
            for (int rowNum = firstRow; rowNum <= lastRow; rowNum++) {
                mergedRowNumSet.add(rowNum);
            }
            mergedRegionList.add(cellRangeAddress);
        }

        Collections.sort(mergedRegionList, new Comparator<CellRangeAddress>() {
            public int compare(CellRangeAddress rangeAddress1, CellRangeAddress rangeAddress2) {
                if (rangeAddress1.getFirstRow() > rangeAddress2.getFirstRow()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        // key is the element path in the html, the value is a map which key is field
        // value & value is expected info.
        Map<String, LinkedHashMap<String, String>> dataWarehouse = new HashMap<String, LinkedHashMap<String, String>>();

        String biggestSubMapPath = null;

        int currentSubMapSize = 0;

        // Put the merged row data in the dataWarehouse map
        for (CellRangeAddress cellRangeAddress : mergedRegionList) {
            int firstRow = cellRangeAddress.getFirstRow();
            int lastRow = cellRangeAddress.getLastRow();
            HSSFCell firstCell = sheet.getRow(cellRangeAddress.getFirstRow())
                    .getCell(cellRangeAddress.getFirstColumn());
            String firstValue = firstCell.getStringCellValue().trim();

            // key is field value, value is the expected info
            LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
            for (int rowNum = firstRow; rowNum <= lastRow; rowNum++) {
                HSSFRow row = sheet.getRow(rowNum);
                HSSFCell secondCell = row.getCell(1);
                HSSFCell thirdCell = row.getCell(2);

                if (secondCell != null) {
                    String secondValue = secondCell.getStringCellValue().trim();
                    if (thirdCell != null) {
                        valueMap.put(secondValue, thirdCell.getStringCellValue().trim());
                    } else {
                        valueMap.put(secondValue, "");
                    }
                }
            }

            if (valueMap.size() > 0) {
                dataWarehouse.put(firstValue, valueMap);
            }

            if (currentSubMapSize < valueMap.size()) {
                currentSubMapSize = valueMap.size();
                biggestSubMapPath = firstValue;
            }
        }

        // Put the non-merged row data into the dataWarehouse Map
        for (int rowNum = 0; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
            if (!mergedRowNumSet.contains(rowNum)) {
                HSSFRow row = sheet.getRow(rowNum);
                HSSFCell firstCell = row.getCell(0);
                HSSFCell secondCell = row.getCell(1);
                HSSFCell thirdCell = row.getCell(2);
                if (firstCell != null) {
                    if (secondCell != null) {
                        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
                        String secondValue = secondCell.getStringCellValue().trim();
                        if (thirdCell != null) {
                            valueMap.put(secondValue, thirdCell.getStringCellValue().trim());
                        } else {
                            valueMap.put(secondValue, "");
                        }
                        String firstValue = firstCell.getStringCellValue().trim();
                        if (!firstValue.equals("")) {
                            dataWarehouse.put(firstValue, valueMap);
                        }
                    }
                }
            }
        }

        // Transform to the required data structure by TestNG
        List<Map<String, Map.Entry<String, String>>> resultList = new ArrayList<Map<String, Map.Entry<String, String>>>();
        resultList.add(templateDataMap);
        if (biggestSubMapPath != null) {
            int resultSize = dataWarehouse.get(biggestSubMapPath).size();
            for (int i = 0; i < resultSize; i++) {
                Map<String, Map.Entry<String, String>> resultMap = new HashMap<String, Map.Entry<String, String>>();
                for (String path : dataWarehouse.keySet()) {
                    LinkedHashMap<String, String> valueMap = dataWarehouse.get(path);
                    if (valueMap.size() > 0) {
                        Map.Entry<String, String> firstEntry = valueMap.entrySet().iterator().next();
                        valueMap.remove(firstEntry.getKey());
                        resultMap.put(path, firstEntry);
                    } else {
                        resultMap.put(path, templateDataMap.get(path));
                    }
                }
                if (resultMap.size() > 1) {
                    resultList.add(resultMap);
                }
            }
        }

        // System.out.println("resultList.size() = " + resultList.size());
        // for (Map<String, Map.Entry<String, String>> resultMap : resultList) {
        // for (String path : resultMap.keySet()) {
        // System.out.println("resultMap.size() = " + resultMap.size());
        // System.out.println("path = " + path);
        // System.out.println("resultMap.get(path) = " + resultMap.get(path));
        // System.out.println(" =================================== ");
        // }
        // System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        // }
        return resultList;
    }

    private Map<String, Map.Entry<String, String>> getTemplateDataMap(HSSFWorkbook hssfw, String templateSheet) {
        HSSFSheet template = hssfw.getSheet(templateSheet);
        Map<String, Map.Entry<String, String>> templateDataMap = new HashMap<String, Map.Entry<String, String>>();
        // System.out.println("template.getPhysicalNumberOfRows() = " +
        // template.getPhysicalNumberOfRows());
        for (int i = 0; i < template.getPhysicalNumberOfRows(); i++) {
            HSSFRow row = template.getRow(i);
            if (row != null) {
                HSSFCell firstCell = row.getCell(0);
                HSSFCell secondCell = row.getCell(1);
                HSSFCell thirdCell = row.getCell(2);
                if (firstCell != null) {
                    String firstValue = firstCell.getStringCellValue().trim();
                    if (!firstValue.equals("")) {
                        if (secondCell != null) {
                            Map<String, String> valueMap = new HashMap<String, String>();
                            String secondValue = secondCell.getStringCellValue().trim();
                            if (thirdCell != null) {
                                valueMap.put(secondValue, thirdCell.getStringCellValue().trim());
                            } else {
                                valueMap.put(secondValue, "");
                            }
                            templateDataMap.put(firstValue, valueMap.entrySet().iterator().next());
                        }
                    }
                }
            }
        }
        return templateDataMap;
    }

    public List<HSSFRow> getRows(File file, String sheet) throws FileNotFoundException {
        List<HSSFRow> rows = new ArrayList<HSSFRow>();
        FileInputStream inputStream = new FileInputStream(file);
        try {
            HSSFWorkbook hssfw = new HSSFWorkbook(inputStream);
            HSSFSheet template = hssfw.getSheet(sheet);
            for (int i = 0; i < template.getPhysicalNumberOfRows(); i++) {
                HSSFRow row = template.getRow(i);
                if (row != null) {
                    rows.add(row);
                }
            }
            hssfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        ReadDataFromExcel rdf = new ReadDataFromExcel();
        List<Map<String, Entry<String, String>>> data = rdf.readData(new File("/Data.xlsx"),
                "Register page", "Sheet3");
        System.out.println("Datos="+data.toString());

    }

}
