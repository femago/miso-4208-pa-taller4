/*
 * @(#)FileWriter.java
 *
 * Copyright (c) 2017 Southwest Airlines, Co.
 * 2702 Love Field Drive, Dallas, TX 75235, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Southwest Airlines, Co.
 */
package uniandes.miso4208.output;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AppsReportWriter {
    private static AppsReportWriter instance;
    private final XSSFWorkbook workbook;
    private final XSSFSheet sheet;
    private int rowNum = 0;

    private AppsReportWriter() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Apps encontradas");
    }

    public static AppsReportWriter getInstance() {
        return instance == null ? instance = new AppsReportWriter() : instance;
    }

    public synchronized void saveRow(List<? extends Object> rowContent) {
        Row row = sheet.createRow(rowNum++);
        final AtomicInteger colNum = new AtomicInteger(0);
        rowContent.forEach(val -> {
            Cell cell = row.createCell(colNum.getAndAdd(1));
            cell.setCellValue(val.toString());
        });
    }

    public void close() {
        try (FileOutputStream outputStream = new FileOutputStream("Reporte.xlsx")) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
