package com.ttcn.vnuaexam.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public final class ExcelUtils {
    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        try {
            return switch (cell.getCellType()) {
                case STRING -> cell.getStringCellValue().trim();
                case NUMERIC -> handleNumericCell(cell);
                case FORMULA -> cell.getCellFormula();
                case BLANK -> " ";
                default -> "";
            };
        } catch (Exception e) {
            // Log error if needed
            return "";
        }
    }

    private static String handleNumericCell(Cell cell) {
        if (DateUtil.isCellDateFormatted(cell)) {
            return formatDate(cell.getDateCellValue());
        }
        // Avoid scientific notation and trailing zeroes
        double value = cell.getNumericCellValue();
        if (value == (long) value) {
            return String.format("%d", (long) value);
        }
        return String.format("%.2f", value).replaceAll("\\.?0*$", "");
    }

    private static String formatDate(Date date) {
        return date != null ?
                new SimpleDateFormat("dd/MM/yyyy").format(date) :
                "";
    }

    public static boolean isRowEmpty(Row row) {
        if (row == null) {
            return true;
        }

        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }
}
