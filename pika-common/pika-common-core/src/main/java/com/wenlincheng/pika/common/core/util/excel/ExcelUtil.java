/**
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */
package com.wenlincheng.pika.common.core.util.excel;

import com.google.common.collect.Maps;
import com.wenlincheng.pika.common.core.annotation.CsvField;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.common.core.util.CheckUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum.EXCEL_PARSING_FAIL;

/**
 * Excel工具
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class ExcelUtil<T> {

    /**
     * excel book
     */
    private final HSSFWorkbook workbook;

    /**
     * excel sheet
     */
    private final HSSFSheet sheet;
    /**
     * sheetName
     */
    private final String sheetName;
    /**
     * excel单元格样式
     */
    private HSSFCellStyle cellStyle = null;

    /**
     * 构造方法
     *
     * @param sheetName excel页名
     */
    public ExcelUtil(String sheetName) {

        this.sheetName = sheetName;

        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet();
        workbook.setSheetName(0, sheetName);

        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.GREEN.index);

        cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
    }

    /**
     * 读取excel表格的数据
     * 支持xls 和 xls
     *
     * @param file         上传的文件
     * @param tClass       转化的目标对象类
     * @param needFieldSet excel中必须包含的表头名称
     */
    public static <T> List<T> readExcelByFile(MultipartFile file, Class<T> tClass, Set<String> needFieldSet) throws Exception {
        List<T> tList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        Workbook wb = WorkbookFactory.create(inputStream);

        Map<String, Method> descriptorMap = ExcelUtil.getCsvFieldMapPropertyWriteMethod(tClass);
        Map<Integer, String> indexHeader = Maps.newHashMap();

        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet sheet = wb.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }

            // 取头 保存头的顺序
            Row firstRow = sheet.getRow(0);
            if (firstRow == null) {
                continue;
            }
            Set<String> existFieldName = new HashSet<>();
            for (int cellNum = 0; cellNum < firstRow.getLastCellNum(); cellNum++) {
                String fieldName = firstRow.getCell(cellNum).getStringCellValue();
                indexHeader.put(cellNum, fieldName);
                existFieldName.add(fieldName);
            }

            if (CheckUtils.isNotEmpty(needFieldSet)) {
                for (String needFieldName : needFieldSet) {
                    if (!existFieldName.contains(needFieldName)) {
                        throw new BaseException(EXCEL_PARSING_FAIL, "Excel表格中未包含" + needFieldName + "列");
                    }
                }
            }

            // 循环行Row
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                T t = BeanUtils.instantiate(tClass);
                // 循环列Cell
                for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    if (cell == null) {
                        continue;
                    }
                    cell.setCellType(CellType.STRING);
                    if (descriptorMap.containsKey(indexHeader.get(cellNum)) && CheckUtils.isNotEmpty(cell.getStringCellValue())) {
                        descriptorMap.get(indexHeader.get(cellNum)).invoke(t, cell.getStringCellValue().trim());
                    }
                }
                tList.add(t);
            }
        }
        inputStream.close();
        return tList;
    }

    /**
     * 读取excel表格的数据
     * 支持xls 和 xls
     */
    public static <T> List<T> readExcelByInputStream(InputStream inputStream, Class<T> tClass) throws Exception {
        List<T> tList = new ArrayList<>();
        Workbook wb = WorkbookFactory.create(inputStream);
        Map<String, Method> descriptorMap = ExcelUtil.getCsvFieldMapPropertyWriteMethod(tClass);
        Map<Integer, String> indexHeader = Maps.newHashMap();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
            Sheet sheet = wb.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            // 取头 保存头的顺序
            Row firstRow = sheet.getRow(0);
            if (firstRow == null) {
                continue;
            }
            for (int cellNum = 0; cellNum < firstRow.getLastCellNum(); cellNum++) {
                indexHeader.put(cellNum, firstRow.getCell(cellNum).getStringCellValue());
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                T t = BeanUtils.instantiate(tClass);
                // 循环列Cell
                for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                    Cell cell = row.getCell(cellNum);
                    if (cell == null) {
                        continue;
                    }
                    if (descriptorMap.containsKey(indexHeader.get(cellNum))) {
                        descriptorMap.get(indexHeader.get(cellNum)).invoke(t, cell.getStringCellValue());
                    }
                }
                tList.add(t);
            }
        }
        inputStream.close();
        return tList;
    }

    /**
     * 获取对应对象中包含CsvCsvField字段的 PropertyDescriptor
     *
     * @param tClass 对象的class
     * @return Map
     * @throws Exception 异常
     */
    public static Map<String, PropertyDescriptor> getCsvFieldMapPropertyDescriptor(Class tClass) {
        Map<String, PropertyDescriptor> descriptorMap = Maps.newHashMap();
        try {
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(tClass);
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                // 获取该字段赋值过来的  字段名称
                if (propertyDescriptor.getWriteMethod() == null) {
                    continue;
                }
                Field field = tClass.getDeclaredField(propertyDescriptor.getName());
                CsvField csvField = field.getAnnotation(CsvField.class);
                if (csvField == null) {
                    continue;
                }
                String fieldMetaName = csvField.name();
                if (CheckUtils.isEmpty(fieldMetaName)) {
                    continue;
                }
                descriptorMap.put(fieldMetaName, propertyDescriptor);
            }
        } catch (Exception e) {
            throw new BaseException(EXCEL_PARSING_FAIL, e);
        }
        return descriptorMap;
    }

    /**
     * 获取对应对象中包含CsvCsvField字段的 的方法  可以使readMethod或者writeMethod
     *
     * @param tClass 对象的class
     * @param isRead 是不是readMethod
     * @return Map
     * @throws Exception 异常
     */
    private static Map<String, Method> getCsvFieldMapPropertyMethod(Class tClass, boolean isRead) {
        Map<String, Method> descriptorMap = Maps.newHashMap();
        try {
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(tClass);
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                // 获取该字段赋值过来的  字段名称
                if (propertyDescriptor.getWriteMethod() == null) {
                    continue;
                }
                Field field = tClass.getDeclaredField(propertyDescriptor.getName());
                CsvField csvField = field.getAnnotation(CsvField.class);
                if (csvField == null) {
                    continue;
                }
                String fieldMetaName = csvField.name();
                if (CheckUtils.isEmpty(fieldMetaName)) {
                    continue;
                }
                if (isRead) {
                    descriptorMap.put(fieldMetaName, propertyDescriptor.getReadMethod());
                } else {
                    descriptorMap.put(fieldMetaName, propertyDescriptor.getWriteMethod());
                }
            }
        } catch (Exception e) {
            throw new BaseException();
        }
        return descriptorMap;
    }

    /**
     * 获取对应对象中包含CsvCsvField字段的 ReadMathod
     *
     * @param tClass 对象的class
     * @return Map
     * @throws Exception 异常
     */
    public static Map<String, Method> getCsvFieldMapPropertyReadMethod(Class tClass) {
        return ExcelUtil.getCsvFieldMapPropertyMethod(tClass, true);
    }

    /**
     * 获取对应对象中包含CsvCsvField字段的 ReadMathod
     *
     * @param tClass 对象的class
     * @return Map
     * @throws Exception 异常
     */
    public static Map<String, Method> getCsvFieldMapPropertyWriteMethod(Class tClass) {
        return ExcelUtil.getCsvFieldMapPropertyMethod(tClass, false);
    }

    /**
     * 创建excel文件需要调用这个方法
     *
     * @param list       数据列表
     * @param titleArray 生成的excel标题头
     * @param response   生成的excel文件的全路径
     */
    public void exportExcel(List<T> list, String[] titleArray, HttpServletResponse response) {
        createSheetTitle(sheet, titleArray);
        createSheetContent(list, sheet);
        exportExcel2Client(workbook, response);
    }

    /**
     * 创建excel文件需要调用这个方法
     *
     * @param list       数据列表
     * @param titleArray 生成的excel标题头
     * @return
     */
    public HSSFWorkbook createExcel(List<T> list, String[] titleArray) {
        createSheetTitle(sheet, titleArray);
        createSheetContent(list, sheet);
        return workbook;
    }

    /**
     * 输出excel的主体内容
     *
     * @param list  输出到excel的内容列表
     * @param sheet excel的sheet页
     * @return excel的记录行数
     */
    protected int createSheetContent(List<T> list, HSSFSheet sheet) {
        // 共导出多少条
        int sumRow = 0;
        HSSFCell cell = null;
        HSSFRow row = null;
        for (T t : list) {
            if (t == null) {
                continue;
            }
            int cellCount = 0;
            sumRow++;
            row = sheet.createRow(sumRow);
            cell = createCell(sumRow, t, row, cellCount++, 1);
            cell.setCellValue("");
        }
        return sumRow;
    }

    /**
     * 偶数行字体增加颜色
     *
     * @param t        每行的内容对象
     * @param row      excel行
     * @param location 竖行位置
     * @param size     excel格大小
     * @return excel格对象
     */
    protected HSSFCell createCell(int sumRow, T t, HSSFRow row, int location, int size) {
        HSSFCell cell = row.createCell(location, size);
        if (sumRow % 2 == 0) {
            cell.setCellStyle(cellStyle);
        }
        return cell;
    }

    /**
     *
     * 导出Excel
     *
     * @param workbook
     * @param response
     */
    private void exportExcel2Client(HSSFWorkbook workbook, HttpServletResponse response) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(new Date());
        String fileName = "attachment; filename=" + sheetName + "-" + nowDate + ".xls";
        OutputStream out = null;
        try {
            response.setHeader("Content-disposition", new String(fileName.getBytes("gbk"), "ISO-8859-1"));
            response.setContentType("application/msexcel;charset=UTF-8");
            out = response.getOutputStream();
            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 输出excel的标题行
     *
     * @param titleArray 标题行数组
     * @param sheet      excel sheet对象
     */
    private void createSheetTitle(HSSFSheet sheet, String[] titleArray) {
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < titleArray.length; i++) {
            HSSFCell cell = row.createCell(i, 1);
            cell.setCellValue(titleArray[i]);
        }
    }

    /**
     * excel文件写入
     *
     * @param sheetName sheet名称
     * @param cellName  头列表
     * @param date      数据(key 头名称，value 头对应该列的数据)
     * @return SXSSFWorkbook
     */
    private SXSSFWorkbook createExcel(String sheetName, List<String> cellName, List<String[]> date) {
        XSSFWorkbook wb = new XSSFWorkbook();
        // TODO (XSSF的导入大量数据 可以保存固定条数   超过就写入硬盘中  避免内存溢出)
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(wb, 500);
        Sheet sheet = sxssfWorkbook.createSheet(sheetName);
        if (CheckUtils.isNotEmpty(date)) {
            int rowNum = date.size();
            //循环行数
            for (int i = 0; i < rowNum + 1; i++) {
                Row row = sheet.createRow(i);
                //获取单元格数 并循环赋值
                int cellNum = cellName.size();
                for (int j = 0; j < cellNum; j++) {
                    if (i == 0) {
                        row.createCell(j).setCellValue(cellName.get(j));
                    } else {
                        row.createCell(j).setCellValue(date.get(i - 1)[j]);
                    }
                }
            }
        }
        return sxssfWorkbook;
    }


}
