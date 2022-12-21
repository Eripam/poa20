/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.reportes.excel;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import poa.acceso.adActividadRequerimiento;
import poa.acceso.adProyecto;
import poa.clases.cActividadRequerimiento;
import poa.clases.cProyecto;

/**
 *
 * @author Erika Arévalo
 */
@MultipartConfig
public class reporteExcel2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {
        if (request.getParameter("accion") != null) {
            String strAccion = request.getParameter("accion");
            switch (strAccion) {
                case "reporteExcelVer": {
                    try {
                        reporteExcelVer(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteExcelVerFinal": {
                    try {
                        reporteExcelVerFinal(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }
    }

    //Estilos
    private static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style;
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFPalette palette = hwb.getCustomPalette();
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 18);
        titleFont.setBold(true);
        titleFont.setFontName("Arial");
        style = wb.createCellStyle();
// get the color which most closely matches the color you want to use
        HSSFColor myColor = palette.findSimilarColor(179, 179, 179);
// get the palette index of that color 
        short palIndex = myColor.getIndex();
// code to get the style for the cell goes here
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(palIndex);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(titleFont);
        styles.put("title", style);

        Font titleFont2 = wb.createFont();
        titleFont2.setFontHeightInPoints((short) 9);
        HSSFColor myColor2 = palette.findSimilarColor(130, 191, 248);
        titleFont2.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColor2.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFont2);
        styles.put("title3", style);

        Font titleFont3 = wb.createFont();
        titleFont3.setFontHeightInPoints((short) 9);
        HSSFColor myColor3 = palette.findSimilarColor(204, 255, 204);
        titleFont3.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColor3.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFont3);
        styles.put("title4", style);

        Font titleFontre = wb.createFont();
        titleFontre.setFontHeightInPoints((short) 9);
        HSSFColor myColorre = palette.findSimilarColor(180, 198, 231);
        titleFontre.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColorre.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFontre);
        styles.put("titlere", style);

        Font titleFontret = wb.createFont();
        titleFontret.setFontHeightInPoints((short) 9);
        HSSFColor myColorret = palette.findSimilarColor(31, 78, 120);
        titleFontret.setFontName("Arial");
        titleFontret.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColorret.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFontret);
        styles.put("titleret", style);

        Font titleFont8 = wb.createFont();
        titleFont8.setFontHeightInPoints((short) 9);
        HSSFColor myColor8 = palette.findSimilarColor(244, 176, 132);
        titleFont8.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColor8.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFont8);
        styles.put("title8", style);

        Font titleFont7 = wb.createFont();
        titleFont7.setFontHeightInPoints((short) 9);
        HSSFColor myColor7 = palette.findSimilarColor(200, 240, 240);
        titleFont7.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColor7.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFont7);
        styles.put("title7", style);

        Font titleFont9 = wb.createFont();
        titleFont9.setFontHeightInPoints((short) 9);
        HSSFColor myColor9 = palette.findSimilarColor(255, 0, 0);
        titleFont9.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColor9.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(false);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFont9);
        styles.put("title9", style);

        Font titleFontverde = wb.createFont();
        titleFontverde.setFontHeightInPoints((short) 9);
        HSSFColor myColorVerde = palette.findSimilarColor(173, 255, 47);
        titleFontverde.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColorVerde.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFontverde);
        styles.put("verde", style);

        Font titleFontsval = wb.createFont();
        titleFontsval.setFontHeightInPoints((short) 9);
        HSSFColor myColorSval = palette.findSimilarColor(34, 139, 34);
        titleFontsval.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColorSval.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFontsval);
        styles.put("sinvalor", style);

        Font titleFontNPac = wb.createFont();
        titleFontNPac.setFontHeightInPoints((short) 9);
        HSSFColor myColorNPac = palette.findSimilarColor(173, 216, 230);
        titleFontNPac.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColorNPac.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFontNPac);
        styles.put("nopac", style);

        Font titleFont4 = wb.createFont();
        titleFont3.setFontHeightInPoints((short) 9);
        titleFont3.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(false);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFont4);
        styles.put("title5", style);

        Font titleFont5 = wb.createFont();
        titleFont3.setFontHeightInPoints((short) 9);
        titleFont3.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(false);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setDataFormat(wb.createDataFormat().getFormat("0,00"));
        style.setFont(titleFont5);
        styles.put("title6", style);

        Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short) 11);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(true);
        styles.put("header", style);

        Font title2 = wb.createFont();
        title2.setFontHeightInPoints((short) 11);
        title2.setColor(IndexedColors.BLACK.getIndex());
        title2.setFontName("Calibri");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(title2);
        style.setWrapText(false);
        styles.put("title2", style);

        Font titleg = wb.createFont();
        titleg.setFontHeightInPoints((short) 11);
        titleg.setColor(IndexedColors.BLACK.getIndex());
        titleg.setFontName("Calibri");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(titleg);
        style.setWrapText(false);
        styles.put("titleg", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula", style);

        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(wb.createDataFormat().getFormat("0.00"));
        styles.put("formula_2", style);

        Font border2 = wb.createFont();
        border2.setFontHeightInPoints((short) 11);
        border2.setColor(IndexedColors.BLACK.getIndex());
        border2.setFontName("Arial Narrow");
        border2.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(border2);
        styles.put("border2", style);

        Font border3 = wb.createFont();
        border3.setFontHeightInPoints((short) 5);
        border3.setColor(IndexedColors.BLACK.getIndex());
        border3.setFontName("Arial");
        border3.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(border3);
        styles.put("border3", style);

        Font border4 = wb.createFont();
        border4.setFontHeightInPoints((short) 10);
        border4.setColor(IndexedColors.BLACK.getIndex());
        border4.setFontName("Arial Narrow");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(border4);
        styles.put("border4", style);

        Font titleFont10 = wb.createFont();
        titleFont10.setFontHeightInPoints((short) 8);
        HSSFColor myColor10 = palette.findSimilarColor(217, 200, 200);
        titleFont10.setFontName("Arial Narrow");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColor10.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(true);
        titleFont10.setBold(true);
        style.setBorderRight(BorderStyle.THICK);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THICK);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THICK);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THICK);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFont10);
        styles.put("border5", style);

        Font titleFont11 = wb.createFont();
        titleFont11.setFontHeightInPoints((short) 8);
        titleFont11.setFontName("Arial Narrow");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFont11);
        styles.put("border6", style);

        Font titleFont12 = wb.createFont();
        titleFont12.setFontHeightInPoints((short) 10);
        HSSFColor myColor12 = palette.findSimilarColor(217, 200, 200);
        titleFont12.setFontName("Arial Narrow");
        titleFont12.setBold(true);
        style = wb.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColor12.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderRight(BorderStyle.THICK);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THICK);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THICK);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THICK);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFont12);
        styles.put("border7", style);

        Font border5 = wb.createFont();
        border5.setFontHeightInPoints((short) 10);
        border5.setColor(IndexedColors.BLACK.getIndex());
        border5.setFontName("Arial Narrow");
        border5.setBold(true);
        style = wb.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(border5);
        styles.put("border8", style);

        Font titleFont13 = wb.createFont();
        titleFont13.setFontHeightInPoints((short) 8);
        titleFont13.setFontName("Arial Narrow");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.JUSTIFY);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFont13);
        styles.put("border9", style);

        Font border6 = wb.createFont();
        border6.setFontHeightInPoints((short) 10);
        border6.setColor(IndexedColors.BLACK.getIndex());
        border6.setFontName("Arial Narrow");
        border6.setBold(true);
        style = wb.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(border6);
        styles.put("border10", style);

        Font border7 = wb.createFont();
        border7.setFontHeightInPoints((short) 7);
        border7.setColor(IndexedColors.BLACK.getIndex());
        border7.setFontName("Arial Narrow");
        border7.setBold(true);
        style = wb.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(border7);
        styles.put("border11", style);

        Font border8 = wb.createFont();
        border8.setFontHeightInPoints((short) 7);
        border8.setColor(IndexedColors.BLACK.getIndex());
        border8.setFontName("Arial Narrow");
        border8.setBold(true);
        style = wb.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(border8);
        styles.put("border12", style);

        Font titleFont14 = wb.createFont();
        titleFont14.setFontHeightInPoints((short) 10);
        HSSFColor myColor14 = palette.findSimilarColor(217, 200, 200);
        titleFont14.setFontName("Arial Narrow");
        titleFont14.setBold(true);
        style = wb.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColor14.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(titleFont14);
        styles.put("border13", style);

        Font border10 = wb.createFont();
        border10.setFontHeightInPoints((short) 7);
        border10.setColor(IndexedColors.BLACK.getIndex());
        border10.setFontName("Arial Narrow");
        border10.setBold(true);
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setFont(border10);
        styles.put("border14", style);

        Font titleFontp = wb.createFont();
        titleFontp.setFontHeightInPoints((short) 9);
        HSSFColor myColorp = palette.findSimilarColor(255, 255, 0);
        titleFontp.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColorp.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(false);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFontp);
        styles.put("titlepr", style);

        Font titleFontpe = wb.createFont();
        titleFontpe.setFontHeightInPoints((short) 9);
        HSSFColor myColorpe = palette.findSimilarColor(34, 113, 179);
        titleFontpe.setFontName("Arial");
        style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(myColorpe.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(false);
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setFont(titleFontpe);
        styles.put("titlepre", style);

        return styles;
    }

    private static final String[] titles = {
        "EJERCICIO", "ENTIDAD", "UNIDAD EJECUTORA", "UNIDAD DESCONCENTRADA", "PROGRAMA", "SUBPROGRAMA", "PROYECTO", "ACTIVIDAD", "OBRAS",
        "GEOGRAFICO", "RENGLO", "RENGLON AUXILIAR", "FUENTE", "ORGANISMO", "CORRELATIVO", "TIPO DE PRESUPUESTO (proyecto de inversión, gasto corriente)", "CODIGO CATEGORIA CPC A NIVEL 9", "TIPO COMPRA (Bien, obras, servicio o consultoría)",
        "DETALLE DEL PRODUCTO (Descripción de la contratación)", "CANTIDAD ANUAL", "UNIDAD (metro, litro etc)", "COSTO UNITARIO (Dólares)", "CUATRIMESTRE 1 (marcar con una S en el cuatrimestre que va a contratar)",
        "CUATRIMESTRE 2 (marcar con una S en el cuatrimestre que va a contratar)", "CUATRIMESTRE 3 (marcar con una S en el cuatrimestre que va a contratar)", "TIPO DE PRODUCTO (normalizado / no normalizado)",
        "CATALOGO ELECTRÓNICO (si/no)", "PROCEDIMIENTO SUGERIDO (son los procedimientos de contratación)", "FONDOS BID (si/no)", "NUMERO CÓDIGO DE OPERACIÓN DEL PRÉSTAMO BID",
        "NUMERO CÓDIGO DE PROYECTO BID", "TIPO DE RÉGIMEN (común, especial)"
    };

    private static final String[] titles2 = {
        "CODIGO CATEGORIA CPC A NIVEL 9", "TIPO COMPRA (Bien, obras, servicio o consultoría)",
        "DETALLE DEL PRODUCTO (Descripción de la contratación)", "DESCRIPCIÓN DEL REQUERIMIENTO", "CANTIDAD ANUAL", "UNIDAD (metro, litro etc)", "COSTO UNITARIO (Dólares)", "COSTO TOTAL (Dólares)", "COSTO TOTAL IVA (Dólares)", "CUATRIMESTRE 1 (marcar con una S en el cuatrimestre que va a contratar)",
        "CUATRIMESTRE 2 (marcar con una S en el cuatrimestre que va a contratar)", "CUATRIMESTRE 3 (marcar con una S en el cuatrimestre que va a contratar)", "TIPO DE PRODUCTO (normalizado / no normalizado)",
        "CATALOGO ELECTRÓNICO (si/no)", "PROCEDIMIENTO SUGERIDO (son los procedimientos de contratación)", "FONDOS BID (si/no)", "NUMERO CÓDIGO DE OPERACIÓN DEL PRÉSTAMO BID",
        "NUMERO CÓDIGO DE PROYECTO BID", "TIPO DE RÉGIMEN (común, especial)", "TIPO DE PRESUPUESTO (proyecto de inversión, gasto corriente)"
    };

    private static final String[] titlesj = {
        "EJERCICIO", "ENTIDAD", "UNIDAD EJECUTORA", "UNIDAD DESCONCENTRADA", "PROGRAMA", "SUBPROGRAMA", "PROYECTO", "ACTIVIDAD", "OBRAS",
        "GEOGRAFICO", "RENGLO", "RENGLON AUXILIAR", "FUENTE", "ORGANISMO", "CORRELATIVO", "TIPO DE PRESUPUESTO (proyecto de inversión, gasto corriente)", "CODIGO CATEGORIA CPC A NIVEL 9", "TIPO COMPRA (Bien, obras, servicio o consultoría)",
        "DETALLE DEL PRODUCTO (Descripción de la contratación) (PLANIFICADO)", "CANTIDAD ANUAL (PLANIFICADO)", "UNIDAD (metro, litro etc)", "COSTO UNITARIO (Dólares) (PLANIFICADO)", "DETALLE DEL PRODUCTO (Descripción de la contratación) (UNIFICADO)", "CANTIDAD ANUAL (UNIFICADO)",
        "COSTO UNITARIO (Dólares) (UNIFICADO)", "CUATRIMESTRE 1 (marcar con una S en el cuatrimestre que va a contratar)", "CUATRIMESTRE 2 (marcar con una S en el cuatrimestre que va a contratar)", "CUATRIMESTRE 3 (marcar con una S en el cuatrimestre que va a contratar)",
        "TIPO DE PRODUCTO (normalizado / no normalizado)", "CATALOGO ELECTRÓNICO (si/no)", "PROCEDIMIENTO SUGERIDO (son los procedimientos de contratación)", "FONDOS BID (si/no)", "NUMERO CÓDIGO DE OPERACIÓN DEL PRÉSTAMO BID",
        "NUMERO CÓDIGO DE PROYECTO BID", "TIPO DE RÉGIMEN (común, especial)"
    };

    private static final String[] titles2j = {
        "CODIGO CATEGORIA CPC A NIVEL 9", "TIPO COMPRA (Bien, obras, servicio o consultoría)",
        "DETALLE DEL PRODUCTO (Descripción de la contratación) (PLANIFICADO)", "DESCRIPCIÓN DEL REQUERIMIENTO", "CANTIDAD ANUAL (PLANIFICADO)", "UNIDAD (metro, litro etc)", "COSTO UNITARIO (Dólares) (PLANIFICADO)",
        "COSTO TOTAL (Dólares)(PLANIFICADO)", "COSTO TOTAL IVA(Dólares)(PLANIFICADO)", "DETALLE DEL PRODUCTO (UNIFICADO)", "CANTIDAD ANUAL (UNIFICADO)", "COSTO UNITARIO (Dólares) (UNIFICADO)", "COSTO TOTAL (Dólares)(UNIFICADO)",
        "JUSTIFICATIVO", "CUATRIMESTRE 1 (marcar con una S en el cuatrimestre que va a contratar)", "CUATRIMESTRE 2 (marcar con una S en el cuatrimestre que va a contratar)", "CUATRIMESTRE 3 (marcar con una S en el cuatrimestre que va a contratar)",
        "TIPO DE PRODUCTO (normalizado / no normalizado)", "CATALOGO ELECTRÓNICO (si/no)", "PROCEDIMIENTO SUGERIDO (son los procedimientos de contratación)", "FONDOS BID (si/no)", "NUMERO CÓDIGO DE OPERACIÓN DEL PRÉSTAMO BID",
        "NUMERO CÓDIGO DE PROYECTO BID", "TIPO DE RÉGIMEN (común, especial)", "TIPO DE PRESUPUESTO (proyecto de inversión, gasto corriente)"
    };

    private void reporteExcelVer(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=requerimientosEspochVer.xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> re = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> deudas = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> comprometidos = new ArrayList<cActividadRequerimiento>();
        re = aReq.ListarRequerimientosExcel();
        deudas = aReq.ListarRequerimientosExcelDeudas(2020);
        comprometidos = aReq.ListarRequerimientosExcelComprometidos(2020);
        Row row = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        Row row3 = sheet.createRow(2);
        Row row4 = sheet.createRow(3);
        Row row5 = sheet.createRow(4);
        Row row6 = sheet.createRow(5);
        row.setHeightInPoints(23);
        row2.setHeightInPoints(16);
        row4.setHeightInPoints(16);
        row5.setHeightInPoints(70);
        Cell cell = row.createCell(0);
        Cell cell2 = row2.createCell(0);
        Cell cell3 = row3.createCell(0);
        Cell cell4 = row3.createCell(1);
        Cell cell5 = row4.createCell(0);
        Cell cell6 = row4.createCell(16);
        Cell cell8 = row4.createCell(25);
        Cell cell13 = row4.createCell(32);
        Cell cell9 = row4.createCell(33);
        Cell cell10 = row4.createCell(36);
        Cell cell11 = row4.createCell(41);
        Cell cell12 = row4.createCell(44);
        Cell cell7;
        cell.setCellValue("PLAN ANUAL DE COMPRAS");
        cell2.setCellValue("Por favor no modifique la estructura del archivo para subir al sistema USHAY - Módulo Facilitador de Contratación Pública");
        cell3.setCellValue("RUC_ENTIDAD");
        cell4.setCellValue("0660001250001");
        cell5.setCellValue("INFORMACION DE LA PARTIDA PRESUPUESTARIA");
        cell6.setCellValue("INFORMACION DETALLADA DE LOS REQUERIMIENTOS");
        cell8.setCellValue("REFORMAS");
        cell9.setCellValue("FLUJO CUATRIMESTRAL");
        cell10.setCellValue("DATOS DEL PROYECTO");
        cell11.setCellValue("DATOS ADICIONALES");
        cell12.setCellValue("SEGUIMIENTO EJECUCIÓN");
        cell.setCellStyle(styles.get("title"));
        cell2.setCellStyle(styles.get("title2"));
        cell3.setCellStyle(styles.get("title2"));
        cell4.setCellStyle(styles.get("title2"));
        cell5.setCellStyle(styles.get("title3"));
        cell6.setCellStyle(styles.get("title4"));
        cell8.setCellStyle(styles.get("titlere"));
        cell9.setCellStyle(styles.get("title4"));
        cell10.setCellStyle(styles.get("title8"));
        cell11.setCellStyle(styles.get("title9"));
        cell12.setCellStyle(styles.get("titlere"));
        cell13.setCellStyle(styles.get("titleret"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AI$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AH$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$P$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$Q$4:$Y$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$Z$4:$AG$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AH$4:$AJ$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AK$4:$AO$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AP$4:$AR$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AS$4:$AZ$4"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A5:AZ5"));

        for (int i = 0; i < 16; i++) {
            cell7 = row5.createCell(i);
            cell7.setCellValue(titles[i]);
            cell7.setCellStyle(styles.get("title3"));
        }
        int p = 0;
        for (int j = 16; j < 25; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        int t = 4;
        for (int j = 25; j < 30; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[t]);
            cell7.setCellStyle(styles.get("titlere"));
            t++;
        }

        for (int j = 33; j < 36; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        cell7 = row5.createCell(30);
        cell7.setCellValue("TOTAL INCREMENTO / DISMINUCIÓN (Con IVA)");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(31);
        cell7.setCellValue("N° REFORMA");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(32);
        cell7.setCellValue("TOTAL PRESUPUESTO ANUAL");
        cell7.setCellStyle(styles.get("titleret"));
        cell7 = row5.createCell(36);
        cell7.setCellValue("UNIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(37);
        cell7.setCellValue("DEPENDENCIA");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(38);
        cell7.setCellValue("OEI");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(39);
        cell7.setCellValue("NOMBRE PROYECTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(40);
        cell7.setCellValue("NOMBRE ACTIVIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(41);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(42);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(43);
        cell7.setCellValue("AÑO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(44);
        cell7.setCellValue("TRÁMITE / JUSTIFICATIVO");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(45);
        cell7.setCellValue("ESTADO TRÁMITE");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(46);
        cell7.setCellValue("CÓDIGO TRAMITE");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(47);
        cell7.setCellValue("ESTADO PRESUPUESTARIO");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(48);
        cell7.setCellValue("VALOR");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(49);
        cell7.setCellValue("SALDO EJECUCIÓN");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(50);
        cell7.setCellValue("%");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(51);
        cell7.setCellValue("OBSERVACIÓN");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(52);
        cell7.setCellValue("SALVAGUARDADO");
        cell7.setCellStyle(styles.get("titlere"));

        int k = 0;
        int m = 5;
        int n = 0;
        int r = 0;
        try {
            while (k < re.size()) {
                row6 = sheet.createRow(m);
                //Ejercicio
                cell7 = row6.createCell(0);
                cell7.setCellValue(re.get(k).getPresupuesto_ejercicio());
                cell7.setCellStyle(styles.get("title5"));
                //Entidad
                cell7 = row6.createCell(1);
                cell7.setCellValue(re.get(k).getPresupuesto_entidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad ejecutora
                cell7 = row6.createCell(2);
                cell7.setCellValue(re.get(k).getPresupuesto_unidad_ejec());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad desconcentrada
                cell7 = row6.createCell(3);
                cell7.setCellValue(re.get(k).getPresupuesto_unidad_desc());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(4);
                cell7.setCellValue(re.get(k).getPresupuesto_programa());
                cell7.setCellStyle(styles.get("title5"));
                //Subprograma
                cell7 = row6.createCell(5);
                cell7.setCellValue(re.get(k).getPresupuesto_subprograma());
                cell7.setCellStyle(styles.get("title5"));
                //Proyecto
                cell7 = row6.createCell(6);
                cell7.setCellValue(re.get(k).getPresupuesto_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(7);
                cell7.setCellValue(re.get(k).getPresupuesto_actividad());
                cell7.setCellStyle(styles.get("title5"));
                //Obras
                cell7 = row6.createCell(8);
                cell7.setCellValue(re.get(k).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Geografico
                cell7 = row6.createCell(9);
                cell7.setCellValue(re.get(k).getPresupuesto_geografico());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo
                cell7 = row6.createCell(10);
                cell7.setCellValue(re.get(k).getPresupuesto_renglo());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo Auxiliar
                cell7 = row6.createCell(11);
                cell7.setCellValue(re.get(k).getPresupuesto_renglo_aux());
                cell7.setCellStyle(styles.get("title5"));
                //Fuente
                cell7 = row6.createCell(12);
                cell7.setCellValue(re.get(k).getPresupuesto_fuente());
                cell7.setCellStyle(styles.get("title5"));
                //Organismo
                cell7 = row6.createCell(13);
                cell7.setCellValue(re.get(k).getPresupuesto_organismo());
                cell7.setCellStyle(styles.get("title5"));
                //Correlativo
                cell7 = row6.createCell(14);
                cell7.setCellValue(re.get(k).getPresupuesto_correlativo());
                cell7.setCellStyle(styles.get("title5"));
                //Correlativo
                cell7 = row6.createCell(15);
                cell7.setCellValue(re.get(k).getPresupuesto_presupuesto());
                cell7.setCellStyle(styles.get("title5"));
                //CPC
                cell7 = row6.createCell(16);
                cell7.setCellValue(re.get(k).getReq_cpc());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo Compra
                cell7 = row6.createCell(17);
                cell7.setCellValue(re.get(k).getTc_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Detalle
                cell7 = row6.createCell(18);
                cell7.setCellValue(re.get(k).getReq_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento
                cell7 = row6.createCell(19);
                cell7.setCellValue(re.get(k).getReq_descripcion());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(20);
                cell7.setCellValue(re.get(k).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(21);
                cell7.setCellValue(re.get(k).getUnidad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(22);
                cell7.setCellValue(re.get(k).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(23);
                cell7.setCellValue(re.get(k).getReq_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva
                cell7 = row6.createCell(24);
                cell7.setCellValue(re.get(k).getReq_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad reforma
                cell7 = row6.createCell(25);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Unidad reforma
                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario reforma
                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva reforma
                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva reforma
                cell7 = row6.createCell(29);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Incremento / disminución
                cell7 = row6.createCell(30);
                cell7.setCellValue(re.get(k).getReq_incremento());
                cell7.setCellStyle(styles.get("title5"));
                //Reforma
                cell7 = row6.createCell(31);
                if (re.get(k).getReq_reforma() > re.get(k).getReq_reforma2()) {
                    if (re.get(k).getPresupuesto_reforma() > re.get(k).getReq_reforma()) {
                        cell7.setCellValue(re.get(k).getPresupuesto_reforma());
                    } else {
                        cell7.setCellValue(re.get(k).getReq_reforma());
                    }
                } else {
                    if (re.get(k).getPresupuesto_reforma() > re.get(k).getReq_reforma2()) {
                        cell7.setCellValue(re.get(k).getPresupuesto_reforma());
                    } else {
                        cell7.setCellValue(re.get(k).getReq_reforma2());
                    }
                }
                cell7.setCellStyle(styles.get("title5"));
                //Total presupuesto
                Double to = 0.0;
                cell7 = row6.createCell(32);
                if (re.get(k).getReq_anio() == 2021 || re.get(k).getReq_estado() == 3) {
                    to = 0.0;
                    cell7.setCellValue(to);
                } else {
                    to = re.get(k).getReq_costo_total() + re.get(k).getReq_incremento();
                    cell7.setCellValue(to);
                }
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                cell7 = row6.createCell(33);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(34);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(35);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                for (int c = 0; c < re.get(k).getCuatri().size(); c++) {
                    switch (re.get(k).getCuatri().get(c).getMes_id()) {
                        case 1:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(33);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(33);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 2:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(34);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(34);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 3:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(35);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(35);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                    }
                }
                //Unidad
                cell7 = row6.createCell(36);
                cell7.setCellValue(re.get(k).getAg_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Dependencia
                cell7 = row6.createCell(37);
                if (re.get(k).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                    cell7.setCellValue(re.get(k).getAg_nombre());
                } else {
                    cell7.setCellValue(re.get(k).getAg_alias());
                }
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Objetivo
                cell7 = row6.createCell(38);
                cell7.setCellValue("OEI-" + re.get(k).getPerspectiva_id());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Nombre del proyecto
                cell7 = row6.createCell(39);
                cell7.setCellValue(re.get(k).getProyecto_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Actividad nombre
                cell7 = row6.createCell(40);
                cell7.setCellValue(re.get(k).getActividad_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Tipo requerimiento
                try {
                    if (re.get(k).getUnidad_nombre().isEmpty()) {
                        cell7 = row6.createCell(41);
                        cell7.setCellValue("NO PAC");
                        if (re.get(k).getReq_estado() == 3) {
                            cell7.setCellStyle(styles.get("titlepre"));
                        } else {
                            cell7.setCellStyle(styles.get("title9"));
                        }
                    } else {
                        cell7 = row6.createCell(41);
                        cell7.setCellValue("PAC");
                        if (re.get(k).getReq_estado() == 3) {
                            cell7.setCellStyle(styles.get("titlepre"));
                        } else {
                            cell7.setCellStyle(styles.get("title9"));
                        }
                    }
                } catch (Exception e) {
                    cell7 = row6.createCell(41);
                    cell7.setCellValue("NO PAC");
                    if (re.get(k).getReq_estado() == 3) {
                        cell7.setCellStyle(styles.get("titlepre"));
                    } else {
                        cell7.setCellStyle(styles.get("title9"));
                    }
                }
                //ID requerimiento
                cell7 = row6.createCell(42);
                cell7.setCellValue(re.get(k).getReq_req_id());
                if (re.get(k).getReq_prioridad_fecha() == null) {
                    cell7.setCellStyle(styles.get("title9"));
                } else {
                    cell7.setCellStyle(styles.get("titlepr"));
                }
                //Año
                cell7 = row6.createCell(43);
                cell7.setCellValue(re.get(k).getReq_anio());
                cell7.setCellStyle(styles.get("title5"));
                //Justificativo
                cell7 = row6.createCell(44);
                if (re.get(k).getSolicitud_codigo() == null && re.get(k).getSolicitud_centro_costo() == null && re.get(k).getSolicitud_nombre() == null) {
                    cell7.setCellValue(" ");
                } else if (re.get(k).getSolicitud_codigo() == null && re.get(k).getSolicitud_centro_costo() == null) {
                    cell7.setCellValue(re.get(k).getSolicitud_nombre() + "-STP-2020");
                } else if (re.get(k).getSolicitud_codigo() == null && re.get(k).getSolicitud_nombre() == null) {
                    cell7.setCellValue(re.get(k).getSolicitud_centro_costo() + "-UCP-2020");
                } else if (re.get(k).getSolicitud_centro_costo() == null && re.get(k).getSolicitud_nombre() == null) {
                    cell7.setCellValue(re.get(k).getSolicitud_codigo() + "-UCP-2020");
                } else if (re.get(k).getSolicitud_centro_costo() == null && re.get(k).getSolicitud_nombre() != null && re.get(k).getSolicitud_codigo() != null) {
                    cell7.setCellValue(re.get(k).getSolicitud_codigo() + "-UCP-2020, " + re.get(k).getSolicitud_nombre() + " -STP-2020");
                }
                cell7.setCellStyle(styles.get("title5"));
                //cÓDIGO TRAMITE
                cell7 = row6.createCell(46);
                if (re.get(k).getCp_valor() == 0) {
                    cell7.setCellValue("----");
                } else {
                    cell7.setCellValue(re.get(k).getAe_observacion());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Estado Presupuestarop
                cell7 = row6.createCell(47);
                if (re.get(k).getCp_valor() == 0) {
                    cell7.setCellValue("VERIFICADO");
                } else {
                    cell7.setCellValue(re.get(k).getCp_tipo());
                }
                cell7.setCellStyle(styles.get("title5"));

                if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() > 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() == 0) {
                    //Estado
                    cell7 = row6.createCell(45);
                    if (re.get(k).getSolicitud_cargo() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolicitud_cargo()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Justificativo Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado Unificado IVA
                    cell7 = row6.createCell(48);
                    BigDecimal b = new BigDecimal(re.get(k).getVerificado_uni_iva());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(49);
                    BigDecimal c = new BigDecimal(to - re.get(k).getVerificado_uni_iva());
                    c = c.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(c.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observacion
                    cell7 = row6.createCell(51);
                    cell7.setCellValue(aReq.observacionJustificativoUnifEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() > 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() > 0) {
                    //Estado
                    cell7 = row6.createCell(45);
                    if (re.get(k).getSolicitud_cargo() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolicitud_cargo()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Justificativo Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado Unificado IVA
                    cell7 = row6.createCell(48);
                    BigDecimal b = new BigDecimal(re.get(k).getCp_valor());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(49);
                    BigDecimal c = new BigDecimal(to - re.get(k).getCp_valor());
                    c = c.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(c.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observacion
                    cell7 = row6.createCell(51);
                    cell7.setCellValue(aReq.observacionJustificativoUnifEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() > 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() > 0 && re.get(k).getCp_valor() == 0) {
                    //Estado
                    cell7 = row6.createCell(45);
                    if (re.get(k).getSolestado_observacion() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolestado_observacion()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Justificativo Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(48);
                    BigDecimal a = new BigDecimal(re.get(k).getVerificado_iva() + re.get(k).getVerificado_uni_npac());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(49);
                    BigDecimal b = new BigDecimal((to) - (re.get(k).getVerificado_iva() + re.get(k).getVerificado_uni_npac()));
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(51);
                    cell7.setCellValue(aReq.observacionJustificativoEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() > 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() > 0 && re.get(k).getCp_valor() > 0) {
                    //Estado
                    cell7 = row6.createCell(45);
                    if (re.get(k).getSolestado_observacion() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolestado_observacion()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Justificativo Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(48);
                    BigDecimal a = new BigDecimal(re.get(k).getCp_valor());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(49);
                    BigDecimal b = new BigDecimal((to) - (re.get(k).getCp_valor()));
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(51);
                    cell7.setCellValue(aReq.observacionJustificativoEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() > 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() == 0) {
                    //Estado
                    cell7 = row6.createCell(45);
                    if (re.get(k).getSolestado_observacion() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolestado_observacion()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Justificativo Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(48);
                    BigDecimal a = new BigDecimal(re.get(k).getVerificado_iva());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(49);
                    BigDecimal b = new BigDecimal(to - re.get(k).getVerificado_iva());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(51);
                    cell7.setCellValue(aReq.observacionJustificativoEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() > 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() > 0) {
                    //Estado
                    cell7 = row6.createCell(45);
                    if (re.get(k).getSolestado_observacion() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolestado_observacion()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Justificativo Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(48);
                    BigDecimal a = new BigDecimal(re.get(k).getCp_valor());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(49);
                    BigDecimal b = new BigDecimal(to - re.get(k).getCp_valor());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(51);
                    cell7.setCellValue(aReq.observacionJustificativoEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() > 0 && re.get(k).getCp_valor() == 0) {
                    //Estado
                    cell7 = row6.createCell(45);
                    cell7.setCellValue("En tramite");
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(48);
                    BigDecimal a = new BigDecimal(re.get(k).getVerificado_uni_npac());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(49);
                    BigDecimal b = new BigDecimal(to - re.get(k).getVerificado_uni_npac());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(51);
                    cell7.setCellValue("Solicitud NO PAC");
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() > 0 && re.get(k).getCp_valor() > 0) {
                    //Estado
                    cell7 = row6.createCell(45);
                    cell7.setCellValue("En tramite");
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(48);
                    BigDecimal a = new BigDecimal(re.get(k).getCp_valor());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(49);
                    BigDecimal b = new BigDecimal(to - re.get(k).getCp_valor());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(51);
                    cell7.setCellValue("Solicitud NO PAC");
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() == 0) {
                    //Estado
                    cell7 = row6.createCell(45);
                    cell7.setCellValue("Sin Acción");
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(48);
                    cell7.setCellValue(0);
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(49);
                    BigDecimal b = new BigDecimal(to);
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(51);
                    cell7.setCellValue("Sin Acción");
                    cell7.setCellStyle(styles.get("title5"));
                }
                //%
                cell7 = row6.createCell(50);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Salvaguardados
                cell7 = row6.createCell(52);
                if (re.get(k).getReq_salvaguardar()) {
                    cell7.setCellValue("SALVAGUARDADO");
                } else {
                    cell7.setCellValue("");
                }
                cell7.setCellStyle(styles.get("title5"));
                k++;
                m++;
            }
            //Deudas
            while (n < deudas.size()) {
                row6 = sheet.createRow(m);
                //Ejercicio
                cell7 = row6.createCell(0);
                cell7.setCellValue(deudas.get(n).getPresupuesto_ejercicio());
                cell7.setCellStyle(styles.get("title5"));
                //Entidad
                cell7 = row6.createCell(1);
                cell7.setCellValue(deudas.get(n).getPresupuesto_entidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad ejecutora
                cell7 = row6.createCell(2);
                cell7.setCellValue(deudas.get(n).getPresupuesto_unidad_ejec());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad desconcentrada
                cell7 = row6.createCell(3);
                cell7.setCellValue(deudas.get(n).getPresupuesto_unidad_desc());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(4);
                cell7.setCellValue(deudas.get(n).getPresupuesto_programa());
                cell7.setCellStyle(styles.get("title5"));
                //Subprograma
                cell7 = row6.createCell(5);
                cell7.setCellValue(deudas.get(n).getPresupuesto_subprograma());
                cell7.setCellStyle(styles.get("title5"));
                //Proyecto
                cell7 = row6.createCell(6);
                cell7.setCellValue(deudas.get(n).getPresupuesto_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(7);
                cell7.setCellValue(deudas.get(n).getPresupuesto_actividad());
                cell7.setCellStyle(styles.get("title5"));
                //Obras
                cell7 = row6.createCell(8);
                cell7.setCellValue(deudas.get(n).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Geografico
                cell7 = row6.createCell(9);
                cell7.setCellValue(deudas.get(n).getPresupuesto_geografico());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo
                cell7 = row6.createCell(10);
                cell7.setCellValue(deudas.get(n).getPresupuesto_renglo());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo Auxiliar
                cell7 = row6.createCell(11);
                cell7.setCellValue(deudas.get(n).getPresupuesto_renglo_aux());
                cell7.setCellStyle(styles.get("title5"));
                //Fuente
                cell7 = row6.createCell(12);
                cell7.setCellValue(deudas.get(n).getPresupuesto_fuente());
                cell7.setCellStyle(styles.get("title5"));
                //Organismo
                cell7 = row6.createCell(13);
                cell7.setCellValue(deudas.get(n).getPresupuesto_organismo());
                cell7.setCellStyle(styles.get("title5"));
                //Correlativo
                cell7 = row6.createCell(14);
                cell7.setCellValue(deudas.get(n).getPresupuesto_correlativo());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(15);
                cell7.setCellValue(deudas.get(n).getPresupuesto_presupuesto());
                cell7.setCellStyle(styles.get("title5"));
                //CPC
                cell7 = row6.createCell(16);
                cell7.setCellValue(deudas.get(n).getReq_cpc());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo Compra
                cell7 = row6.createCell(17);
                cell7.setCellValue(deudas.get(n).getTc_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Detalle
                cell7 = row6.createCell(18);
                if (deudas.get(n).getDeu_iva() == 1) {
                    cell7.setCellValue(deudas.get(n).getReq_nombre() + "(IVA)");
                } else {
                    cell7.setCellValue(deudas.get(n).getReq_nombre());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento
                cell7 = row6.createCell(19);
                cell7.setCellValue(deudas.get(n).getReq_descripcion());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(20);
                cell7.setCellValue(deudas.get(n).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(21);
                cell7.setCellValue(deudas.get(n).getUnidad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(22);
                cell7.setCellValue(deudas.get(n).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(23);
                cell7.setCellValue(deudas.get(n).getReq_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva
                cell7 = row6.createCell(24);
                cell7.setCellValue(deudas.get(n).getReq_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad reforma
                cell7 = row6.createCell(25);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Unidad reforma
                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario reforma
                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva reforma
                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva reforma
                cell7 = row6.createCell(29);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Incremento / disminución
                cell7 = row6.createCell(30);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Reforma
                cell7 = row6.createCell(31);
                cell7.setCellValue(deudas.get(n).getReq_reforma());
                cell7.setCellStyle(styles.get("title5"));
                //Total presupuesto
                Double to = 0.0;
                cell7 = row6.createCell(32);
                to = deudas.get(n).getReq_costo_total();
                cell7.setCellValue(to);
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                cell7 = row6.createCell(33);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(34);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(35);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                //Unidad
                cell7 = row6.createCell(36);
                cell7.setCellValue(deudas.get(n).getAg_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Dependencia
                cell7 = row6.createCell(37);
                if (deudas.get(n).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                    cell7.setCellValue(deudas.get(n).getAg_nombre());
                } else {
                    cell7.setCellValue(deudas.get(n).getAg_alias());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Objetivo
                cell7 = row6.createCell(38);
                cell7.setCellValue("OEI-" + deudas.get(n).getPerspectiva_id());
                cell7.setCellStyle(styles.get("title5"));
                //Nombre del proyecto
                cell7 = row6.createCell(39);
                cell7.setCellValue(deudas.get(n).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad nombre
                cell7 = row6.createCell(40);
                cell7.setCellValue(deudas.get(n).getActividad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                cell7 = row6.createCell(41);
                cell7.setCellValue("OBLIGACIONES PENDIENTES");
                cell7.setCellStyle(styles.get("title9"));
                //ID requerimiento
                cell7 = row6.createCell(42);
                cell7.setCellValue(deudas.get(n).getReq_id());
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(43);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Justificativo
                cell7 = row6.createCell(44);
                if (deudas.get(n).getSolicitud_codigo() == null) {
                    cell7.setCellValue(" ");
                } else {
                    cell7.setCellValue(deudas.get(n).getSolicitud_codigo() + "-STP-2020");
                }
                cell7.setCellStyle(styles.get("title5"));
                //Estado tramite
                cell7 = row6.createCell(45);
                if (deudas.get(n).getVerificado_iva() > 0 || deudas.get(n).getVerificado_uni_iva() > 0 || deudas.get(n).getVerificado_uni_npac() > 0) {
                    cell7.setCellValue("En tramite");
                } else {
                    cell7.setCellValue("");
                }
                cell7.setCellStyle(styles.get("title5"));
                //Estado Presupuestarop
                cell7 = row6.createCell(47);
                if (deudas.get(n).getVerificado_iva() > 0 || deudas.get(n).getVerificado_uni_iva() > 0 || deudas.get(n).getVerificado_uni_npac() > 0) {
                    cell7.setCellValue("VERIFICADO");
                } else {
                    cell7.setCellValue("");
                }
                cell7.setCellStyle(styles.get("title5"));
                //Valor
                cell7 = row6.createCell(48);
                double mo;
                if (deudas.get(n).getDeu_iva() == 1) {
                    mo = deudas.get(n).getVerificado_uni_iva();
                    cell7.setCellValue(deudas.get(n).getVerificado_uni_iva());
                } else {
                    mo = deudas.get(n).getVerificado_iva();
                    cell7.setCellValue(deudas.get(n).getVerificado_iva());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Saldo
                cell7 = row6.createCell(49);
                cell7.setCellValue(to - mo);
                cell7.setCellStyle(styles.get("title5"));
                //%
                cell7 = row6.createCell(50);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Observacion
                cell7 = row6.createCell(51);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Salvaguardado
                cell7 = row6.createCell(52);
                cell7.setCellValue("SALVAGUARDADO");
                cell7.setCellStyle(styles.get("title5"));
                m++;
                n++;
            }
            //Comprometidos
            while (r < comprometidos.size()) {
                row6 = sheet.createRow(m);
                //Ejercicio
                cell7 = row6.createCell(0);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_ejercicio());
                cell7.setCellStyle(styles.get("title5"));
                //Entidad
                cell7 = row6.createCell(1);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_entidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad ejecutora
                cell7 = row6.createCell(2);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_unidad_ejec());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad desconcentrada
                cell7 = row6.createCell(3);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_unidad_desc());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(4);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_programa());
                cell7.setCellStyle(styles.get("title5"));
                //Subprograma
                cell7 = row6.createCell(5);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_subprograma());
                cell7.setCellStyle(styles.get("title5"));
                //Proyecto
                cell7 = row6.createCell(6);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(7);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_actividad());
                cell7.setCellStyle(styles.get("title5"));
                //Obras
                cell7 = row6.createCell(8);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Geografico
                cell7 = row6.createCell(9);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_geografico());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo
                cell7 = row6.createCell(10);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_renglo());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo Auxiliar
                cell7 = row6.createCell(11);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_renglo_aux());
                cell7.setCellStyle(styles.get("title5"));
                //Fuente
                cell7 = row6.createCell(12);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_fuente());
                cell7.setCellStyle(styles.get("title5"));
                //Organismo
                cell7 = row6.createCell(13);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_organismo());
                cell7.setCellStyle(styles.get("title5"));
                //Correlativo
                cell7 = row6.createCell(14);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_correlativo());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(15);
                cell7.setCellValue(comprometidos.get(r).getPresupuesto_presupuesto());
                cell7.setCellStyle(styles.get("title5"));
                //CPC
                cell7 = row6.createCell(16);
                cell7.setCellValue(comprometidos.get(r).getReq_cpc());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo Compra
                cell7 = row6.createCell(17);
                cell7.setCellValue(comprometidos.get(r).getTc_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Detalle
                cell7 = row6.createCell(18);
                cell7.setCellValue(comprometidos.get(r).getReq_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento
                cell7 = row6.createCell(19);
                cell7.setCellValue(comprometidos.get(r).getReq_descripcion());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(20);
                cell7.setCellValue(comprometidos.get(r).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(21);
                cell7.setCellValue(comprometidos.get(r).getUnidad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(22);
                cell7.setCellValue(comprometidos.get(r).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(23);
                cell7.setCellValue(comprometidos.get(r).getReq_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva
                cell7 = row6.createCell(24);
                cell7.setCellValue(comprometidos.get(r).getReq_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad reforma
                cell7 = row6.createCell(25);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Unidad reforma
                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario reforma
                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva reforma
                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva reforma
                cell7 = row6.createCell(29);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Incremento / disminución
                cell7 = row6.createCell(30);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Reforma
                cell7 = row6.createCell(31);
                cell7.setCellValue(comprometidos.get(r).getReq_reforma());
                cell7.setCellStyle(styles.get("title5"));
                //Total presupuesto
                Double to = 0.0;
                cell7 = row6.createCell(32);
                to = comprometidos.get(r).getReq_costo_total();
                cell7.setCellValue(to);
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                cell7 = row6.createCell(33);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(34);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(35);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                //Unidad
                cell7 = row6.createCell(36);
                cell7.setCellValue(comprometidos.get(r).getAg_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Dependencia
                cell7 = row6.createCell(37);
                if (comprometidos.get(r).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                    cell7.setCellValue(comprometidos.get(r).getAg_nombre());
                } else {
                    cell7.setCellValue(comprometidos.get(r).getAg_alias());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Objetivo
                cell7 = row6.createCell(38);
                cell7.setCellValue("OEI-" + comprometidos.get(r).getPerspectiva_id());
                cell7.setCellStyle(styles.get("title5"));
                //Nombre del proyecto
                cell7 = row6.createCell(39);
                cell7.setCellValue(comprometidos.get(r).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad nombre
                cell7 = row6.createCell(40);
                cell7.setCellValue(comprometidos.get(r).getActividad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                cell7 = row6.createCell(41);
                cell7.setCellValue("COMPROMETIDOS");
                cell7.setCellStyle(styles.get("title9"));
                //ID requerimiento
                cell7 = row6.createCell(42);
                cell7.setCellValue(comprometidos.get(r).getReq_id());
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(43);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Justificativo
                cell7 = row6.createCell(44);
                if (comprometidos.get(r).getSolicitud_codigo() == null) {
                    cell7.setCellValue(" ");
                } else {
                    cell7.setCellValue(comprometidos.get(r).getSolicitud_codigo() + "-STP-2020");
                }
                cell7.setCellStyle(styles.get("title5"));
                //Estado tramite
                cell7 = row6.createCell(45);
                if (comprometidos.get(r).getVerificado_iva() > 0 || comprometidos.get(r).getVerificado_uni_iva() > 0 || comprometidos.get(r).getVerificado_uni_npac() > 0) {
                    cell7.setCellValue("En tramite");
                } else {
                    cell7.setCellValue("");
                }
                cell7.setCellStyle(styles.get("title5"));
                //Estado Presupuestarop
                cell7 = row6.createCell(47);
                if (comprometidos.get(r).getVerificado_iva() > 0 || comprometidos.get(r).getVerificado_uni_iva() > 0 || comprometidos.get(r).getVerificado_uni_npac() > 0) {
                    cell7.setCellValue("VERIFICADO");
                } else {
                    cell7.setCellValue("");
                }
                cell7.setCellStyle(styles.get("title5"));
                //Valor
                cell7 = row6.createCell(48);
                double mo;
                if (comprometidos.get(r).getPresupuesto_fuente().equals("998") && comprometidos.get(r).getVerificado_iva() > 0 && comprometidos.get(r).getVerificado_uni_npac() == 0) {
                    mo = comprometidos.get(r).getVerificado_iva();
                    cell7.setCellValue(comprometidos.get(r).getVerificado_iva());
                } else if (comprometidos.get(r).getPresupuesto_fuente().equals("998") && comprometidos.get(r).getVerificado_uni_npac() > 0) {
                    mo = comprometidos.get(r).getVerificado_uni_npac();
                    cell7.setCellValue(comprometidos.get(r).getVerificado_uni_npac());
                } else {
                    mo = comprometidos.get(r).getVerificado_iva();
                    cell7.setCellValue(comprometidos.get(r).getVerificado_iva());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Saldo
                cell7 = row6.createCell(49);
                cell7.setCellValue(to - mo);
                cell7.setCellStyle(styles.get("title5"));
                //%
                cell7 = row6.createCell(50);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Observacion
                cell7 = row6.createCell(51);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Salvaguardado
                cell7 = row6.createCell(52);
                cell7.setCellValue("SALVAGUARDADO");
                cell7.setCellStyle(styles.get("title5"));
                m++;
                r++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Write the output to a file
        try {
            // FileOutputStream fileOut = new FileOutputStream("requerimientos.xlsx");
            // wb.write(fileOut);
            //String path = "/var/www/html/ieb_docs/reportes/";
            wb.write(response.getOutputStream()); // Write workbook to response.
            wb.close();
            //fileOut.close();
//            File file = new File("requerimientos.xlsx");
////            // Abrir el archivo
//            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reporteExcelVerFinal(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=requerimientosEspochVer.xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> re = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> deudas = new ArrayList<cActividadRequerimiento>();
        String anio = request.getParameter("anioS");
        if (anio.equals("2021")) {
            re = aReq.ListarRequerimientosExcel21();
            deudas = aReq.ListarRequerimientosExcelDeudas21();
        } else {
            re = aReq.ListarRequerimientosExcel22();
            deudas = aReq.ListarRequerimientosExcelDeudas22(Integer.parseInt(anio));
        }
        Row row = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        Row row3 = sheet.createRow(2);
        Row row4 = sheet.createRow(3);
        Row row5 = sheet.createRow(4);
        Row row6 = sheet.createRow(5);
        row.setHeightInPoints(23);
        row2.setHeightInPoints(16);
        row4.setHeightInPoints(16);
        row5.setHeightInPoints(70);
        Cell cell = row.createCell(0);
        Cell cell2 = row2.createCell(0);
        Cell cell3 = row3.createCell(0);
        Cell cell4 = row3.createCell(1);
        Cell cell5 = row4.createCell(0);
        Cell cell6 = row4.createCell(17);
        Cell cell8 = row4.createCell(26);
        Cell cell13 = row4.createCell(32);
        Cell cell9 = row4.createCell(34);
        Cell cell10 = row4.createCell(37);
        Cell cell11 = row4.createCell(42);
        Cell cell12 = row4.createCell(45);
        Cell cell14 = row4.createCell(71);
        Cell cell15 = row4.createCell(84);
        Cell cell7;
        cell.setCellValue("PLAN ANUAL DE COMPRAS");
        cell2.setCellValue("Por favor no modifique la estructura del archivo para subir al sistema USHAY - Módulo Facilitador de Contratación Pública");
        cell3.setCellValue("RUC_ENTIDAD");
        cell4.setCellValue("0660001250001");
        cell5.setCellValue("INFORMACION DE LA PARTIDA PRESUPUESTARIA");
        cell6.setCellValue("INFORMACION DETALLADA DE LOS REQUERIMIENTOS");
        cell8.setCellValue("REFORMAS");
        cell9.setCellValue("FLUJO CUATRIMESTRAL");
        cell10.setCellValue("DATOS DEL PROYECTO");
        cell11.setCellValue("DATOS ADICIONALES");
        cell12.setCellValue("SEGUIMIENTO EJECUCIÓN PAC");
        cell14.setCellValue("SEGUIMIENTO EJECUCIÓN NO PAC");
        cell15.setCellValue("SEGUIMIENTO EJECUCIÓN");
        cell.setCellStyle(styles.get("title"));
        cell2.setCellStyle(styles.get("title2"));
        cell3.setCellStyle(styles.get("title2"));
        cell4.setCellStyle(styles.get("title2"));
        cell5.setCellStyle(styles.get("title3"));
        cell6.setCellStyle(styles.get("title4"));
        cell8.setCellStyle(styles.get("titlere"));
        cell9.setCellStyle(styles.get("title4"));
        cell10.setCellStyle(styles.get("title8"));
        cell11.setCellStyle(styles.get("title9"));
        cell12.setCellStyle(styles.get("verde"));
        cell15.setCellStyle(styles.get("titlere"));
        cell14.setCellStyle(styles.get("nopac"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AI$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AH$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$Q$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$R$4:$Z$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AA$4:$AH$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AI$4:$AK$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AL$4:$AP$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AQ$4:$AS$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AT$4:$BS$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$BT$4:$CF$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$CG$4:$CM$4"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A5:CM5"));

        for (int i = 0; i < 11; i++) {
            cell7 = row5.createCell(i);
            cell7.setCellValue(titles[i]);
            cell7.setCellStyle(styles.get("title3"));
        }
        cell7 = row5.createCell(11);
        cell7.setCellValue("GRUPO DE GASTO");
        cell7.setCellStyle(styles.get("title3"));

        int z = 12;
        for (int i = 11; i < 17; i++) {
            cell7 = row5.createCell(z);
            cell7.setCellValue(titles[i]);
            cell7.setCellStyle(styles.get("title3"));
            z++;
        }
        int p = 0;
        for (int j = 17; j < 26; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        int t = 4;
        for (int j = 26; j < 31; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[t]);
            cell7.setCellStyle(styles.get("titlere"));
            t++;
        }

        for (int j = 34; j < 37; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        cell7 = row5.createCell(31);
        cell7.setCellValue("TOTAL INCREMENTO / DISMINUCIÓN (Con IVA)");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(32);
        cell7.setCellValue("N° REFORMA");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(33);
        cell7.setCellValue("TOTAL PRESUPUESTO ANUAL");
        cell7.setCellStyle(styles.get("titleret"));
        cell7 = row5.createCell(37);
        cell7.setCellValue("UNIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(38);
        cell7.setCellValue("DEPENDENCIA");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(39);
        cell7.setCellValue("OEI");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(40);
        cell7.setCellValue("NOMBRE PROYECTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(41);
        cell7.setCellValue("NOMBRE ACTIVIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(42);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(43);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(44);
        cell7.setCellValue("AÑO");
        cell7.setCellStyle(styles.get("title9"));
        //Cambios desde esta columna
        cell7 = row5.createCell(45);
        cell7.setCellValue("FECHA ELABORACIÓN JUSTIFICATIVO DE REQUERIMIENTO");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(46);
        cell7.setCellValue("FECHA APROBACIÓN JUSTIFICATIVO DE REQUERIMIENTO POR UCP");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(47);
        cell7.setCellValue("No. JUSTIFICATIVO DE REQUERIMIENTO");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(48);
        cell7.setCellValue("MONTO DE JUSTIFICATIVO");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(49);
        cell7.setCellValue("FECHA DE VERIFICACIÓN Y APROBACIÓN UNIDAD DE COMPRAS PÚBLICAS");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(50);
        cell7.setCellValue("FECHA DE VERIFICACIÓN Y APROBACIÓN DIR. PLANIFICACIÓN");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(51);
        cell7.setCellValue("FECHA DE ENVÍO FINANCIERO");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(52);
        cell7.setCellValue("FECHA DE EMISIÓN CERTIFICACIÓN PRESUPUESTARIA");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(53);
        cell7.setCellValue("No. CERTIFICACIÓN");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(54);
        cell7.setCellValue("MONTO CERTIFICACIÓN");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(55);
        cell7.setCellValue("FECHA ENVÍO DE CERTIFICACIÓN A UCP");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(56);
        cell7.setCellValue("FECHA  DE INICIO DE PROCESO");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(57);
        cell7.setCellValue("TIPO PROCESO");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(58);
        cell7.setCellValue("ENCARGADO PROCESO");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(59);
        cell7.setCellValue("FECHA PUBLICACIÓN SERCOP");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(60);
        cell7.setCellValue("ESTADO SERCOP");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(61);
        cell7.setCellValue("FECHA DE ADJUDICACIÓN");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(62);
        cell7.setCellValue("PROVEEDOR");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(63);
        cell7.setCellValue("NO. CONTRATO / ORDEN DE PAGO");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(64);
        cell7.setCellValue("MONTO ADJUDICADO");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(65);
        cell7.setCellValue("FECHA DE PAGO ANTICIPO ");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(66);
        cell7.setCellValue("FECHA DE ENTREGA BIENES/ SERVICIOS");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(67);
        cell7.setCellValue("FECHA ESTIMADA DE PAGO");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(68);
        cell7.setCellValue("FECHA CUR DE PAGO");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(69);
        cell7.setCellValue("No. CUR DE PAGO");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(70);
        cell7.setCellValue("MONTO CUR DE PAGO");
        cell7.setCellStyle(styles.get("verde"));
        cell7 = row5.createCell(71);
        cell7.setCellValue("FECHA ELABORACIÓN STP");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(72);
        cell7.setCellValue("FECHA DE VERIFICACIÓN Y APROBACIÓN DIR. PLANIFICACIÓN STP");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(73);
        cell7.setCellValue("No. STP DIR PLANIFICACIÓN");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(74);
        cell7.setCellValue("MONTO STP");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(75);
        cell7.setCellValue("FECHA DE RECEPCIÓN Y APROBACIÓN PLANIFICACIÓN STP");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(76);
        cell7.setCellValue("FECHA DE CERTIFICACIÓN PRESUPUESTARIA");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(77);
        cell7.setCellValue("NO. CERTIFICACIÓN PRESUPUESTARIA");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(78);
        cell7.setCellValue("MONTO CERTIFICACIÓN PRESUPUESTARIA");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(79);
        cell7.setCellValue("ESTADO DE PROCESO");
        cell7.setCellStyle(styles.get("sinvalor"));
        cell7 = row5.createCell(80);
        cell7.setCellValue("FECHA CUR DE PAGO");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(81);
        cell7.setCellValue("No. CUR DE PAGO");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(82);
        cell7.setCellValue("MONTO CUR DE PAGO");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(83);
        cell7.setCellValue("SALDO CERTIFICACIÓN");
        cell7.setCellStyle(styles.get("nopac"));
        cell7 = row5.createCell(84);
        cell7.setCellValue("ESTADO TRÁMITE");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(85);
        cell7.setCellValue("ESTADO PRESUPUESTARIO");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(86);
        cell7.setCellValue("VALOR");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(87);
        cell7.setCellValue("SALDO EJECUCIÓN");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(88);
        cell7.setCellValue("%");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(89);
        cell7.setCellValue("OBSERVACIÓN");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(90);
        cell7.setCellValue("SALVAGUARDADO");
        cell7.setCellStyle(styles.get("titlere"));

        int k = 0;
        int m = 5;
        int n = 0;
        int r = 0;
        try {
            while (k < re.size()) {
                row6 = sheet.createRow(m);
                //Ejercicio
                cell7 = row6.createCell(0);
                cell7.setCellValue(re.get(k).getPresupuesto_ejercicio());
                cell7.setCellStyle(styles.get("title5"));
                //Entidad
                cell7 = row6.createCell(1);
                cell7.setCellValue(re.get(k).getPresupuesto_entidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad ejecutora
                cell7 = row6.createCell(2);
                cell7.setCellValue(re.get(k).getPresupuesto_unidad_ejec());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad desconcentrada
                cell7 = row6.createCell(3);
                cell7.setCellValue(re.get(k).getPresupuesto_unidad_desc());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(4);
                cell7.setCellValue(re.get(k).getPresupuesto_programa());
                cell7.setCellStyle(styles.get("title5"));
                //Subprograma
                cell7 = row6.createCell(5);
                cell7.setCellValue(re.get(k).getPresupuesto_subprograma());
                cell7.setCellStyle(styles.get("title5"));
                //Proyecto
                cell7 = row6.createCell(6);
                cell7.setCellValue(re.get(k).getPresupuesto_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(7);
                cell7.setCellValue(re.get(k).getPresupuesto_actividad());
                cell7.setCellStyle(styles.get("title5"));
                //Obras
                cell7 = row6.createCell(8);
                cell7.setCellValue(re.get(k).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Geografico
                cell7 = row6.createCell(9);
                cell7.setCellValue(re.get(k).getPresupuesto_geografico());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo
                cell7 = row6.createCell(10);
                cell7.setCellValue(re.get(k).getPresupuesto_renglo());
                cell7.setCellStyle(styles.get("title5"));
                //Grupo de gasto
                String sCadena = String.valueOf(re.get(k).getPresupuesto_renglo() + "00");
                String sSubCadena = sCadena.substring(0, 2);
                cell7 = row6.createCell(11);
                cell7.setCellValue(sSubCadena + "0000");
                cell7.setCellStyle(styles.get("title5"));
                //Renglo Auxiliar
                cell7 = row6.createCell(12);
                cell7.setCellValue(re.get(k).getPresupuesto_renglo_aux());
                cell7.setCellStyle(styles.get("title5"));
                //Fuente
                cell7 = row6.createCell(13);
                cell7.setCellValue(re.get(k).getPresupuesto_fuente());
                cell7.setCellStyle(styles.get("title5"));
                //Organismo
                cell7 = row6.createCell(14);
                cell7.setCellValue(re.get(k).getPresupuesto_organismo());
                cell7.setCellStyle(styles.get("title5"));
                //Correlativo
                cell7 = row6.createCell(15);
                cell7.setCellValue(re.get(k).getPresupuesto_correlativo());
                cell7.setCellStyle(styles.get("title5"));
                //Correlativo
                cell7 = row6.createCell(16);
                cell7.setCellValue(re.get(k).getPresupuesto_presupuesto());
                cell7.setCellStyle(styles.get("title5"));
                //CPC
                cell7 = row6.createCell(17);
                cell7.setCellValue(re.get(k).getReq_cpc());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo Compra
                cell7 = row6.createCell(18);
                cell7.setCellValue(re.get(k).getTc_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Detalle
                cell7 = row6.createCell(19);
                cell7.setCellValue(re.get(k).getReq_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento
                cell7 = row6.createCell(20);
                cell7.setCellValue(re.get(k).getReq_descripcion());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(21);
                cell7.setCellValue(re.get(k).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(22);
                cell7.setCellValue(re.get(k).getUnidad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(23);
                cell7.setCellValue(re.get(k).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(24);
                cell7.setCellValue(re.get(k).getReq_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva
                cell7 = row6.createCell(25);
                cell7.setCellValue(re.get(k).getReq_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad reforma
                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Unidad reforma
                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario reforma
                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva reforma
                cell7 = row6.createCell(29);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva reforma
                cell7 = row6.createCell(30);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Incremento / disminución
                cell7 = row6.createCell(31);
                cell7.setCellValue(re.get(k).getReq_incremento());
                cell7.setCellStyle(styles.get("title5"));
                //Reforma
                cell7 = row6.createCell(32);
                if (re.get(k).getReq_reforma() > re.get(k).getReq_reforma2()) {
                    if (re.get(k).getPresupuesto_reforma() > re.get(k).getReq_reforma()) {
                        cell7.setCellValue(re.get(k).getPresupuesto_reforma());
                    } else {
                        cell7.setCellValue(re.get(k).getReq_reforma());
                    }
                } else {
                    if (re.get(k).getPresupuesto_reforma() > re.get(k).getReq_reforma2()) {
                        cell7.setCellValue(re.get(k).getPresupuesto_reforma());
                    } else {
                        cell7.setCellValue(re.get(k).getReq_reforma2());
                    }
                }
                cell7.setCellStyle(styles.get("title5"));
                //Total presupuesto
                Double to = 0.0;
                cell7 = row6.createCell(33);
                if (re.get(k).getReq_estado() == 3) {
                    to = 0.0;
                    cell7.setCellValue(to);
                } else {
                    to = re.get(k).getReq_costo_total() + re.get(k).getReq_incremento();
                    cell7.setCellValue(to);
                }
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                cell7 = row6.createCell(34);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(35);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(36);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                for (int c = 0; c < re.get(k).getCuatri().size(); c++) {
                    switch (re.get(k).getCuatri().get(c).getMes_id()) {
                        case 1:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(34);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(34);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 2:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(35);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(35);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 3:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(36);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(36);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                    }
                }
                //Unidad
                cell7 = row6.createCell(37);
                cell7.setCellValue(re.get(k).getAg_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Dependencia
                cell7 = row6.createCell(38);
                if (re.get(k).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                    cell7.setCellValue(re.get(k).getAg_nombre());
                } else {
                    cell7.setCellValue(re.get(k).getAg_alias());
                }
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Objetivo
                cell7 = row6.createCell(39);
                cell7.setCellValue("OEI-" + re.get(k).getPerspectiva_id());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Nombre del proyecto
                cell7 = row6.createCell(40);
                cell7.setCellValue(re.get(k).getProyecto_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Actividad nombre
                cell7 = row6.createCell(41);
                cell7.setCellValue(re.get(k).getActividad_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Tipo requerimiento
                try {
                    if (re.get(k).getUnidad_nombre().isEmpty()) {
                        cell7 = row6.createCell(42);
                        cell7.setCellValue("NO PAC");
                        if (re.get(k).getReq_estado() == 3) {
                            cell7.setCellStyle(styles.get("titlepre"));
                        } else {
                            cell7.setCellStyle(styles.get("title9"));
                        }
                    } else {
                        cell7 = row6.createCell(42);
                        cell7.setCellValue("PAC");
                        if (re.get(k).getReq_estado() == 3) {
                            cell7.setCellStyle(styles.get("titlepre"));
                        } else {
                            cell7.setCellStyle(styles.get("title9"));
                        }
                    }
                } catch (Exception e) {
                    cell7 = row6.createCell(42);
                    cell7.setCellValue("NO PAC");
                    if (re.get(k).getReq_estado() == 3) {
                        cell7.setCellStyle(styles.get("titlepre"));
                    } else {
                        cell7.setCellStyle(styles.get("title9"));
                    }
                }
                //ID requerimiento
                cell7 = row6.createCell(43);
                cell7.setCellValue(re.get(k).getReq_req_id());
                if (re.get(k).getReq_prioridad_fecha() == null) {
                    cell7.setCellStyle(styles.get("title9"));
                } else {
                    cell7.setCellStyle(styles.get("titlepr"));
                }
                //Año
                cell7 = row6.createCell(44);
                cell7.setCellValue(re.get(k).getReq_anio());
                cell7.setCellStyle(styles.get("title5"));
                if (re.get(k).getActividad_eval().size() > 0) {
                    //Fecha envio
                    cell7 = row6.createCell(45);
                    cell7.setCellValue(re.get(k).getActividad_eval().get(0).getFecha_inicio());
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha aprobacón compras
                    cell7 = row6.createCell(46);
                    cell7.setCellValue(re.get(k).getActividad_eval().get(0).getFecha_fin());
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha recibido
                    cell7 = row6.createCell(49);
                    cell7.setCellValue(re.get(k).getActividad_eval().get(0).getReq_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha aprobación plani
                    cell7 = row6.createCell(50);
                    cell7.setCellValue(re.get(k).getActividad_eval().get(0).getReq_descripcion());
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha envio financiero
                    cell7 = row6.createCell(51);
                    cell7.setCellValue(re.get(k).getActividad_eval().get(0).getReq_cpc());
                    cell7.setCellStyle(styles.get("title5"));
                } else {
                    //Fecha envio
                    cell7 = row6.createCell(45);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha aprobacón compras
                    cell7 = row6.createCell(46);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha recibido
                    cell7 = row6.createCell(49);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha aprobación plani
                    cell7 = row6.createCell(50);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha envio financiero
                    cell7 = row6.createCell(51);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                }
                if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() == 0) {
                    //Justificatio
                    cell7 = row6.createCell(47);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Monto Justificatio
                    cell7 = row6.createCell(48);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() > 0 && re.get(k).getActividad_porcentaje() == 0) {
                    //Justificatio
                    cell7 = row6.createCell(47);
                    cell7.setCellValue(re.get(k).getSolicitud_codigo() + "-UCP-" + anio);
                    cell7.setCellStyle(styles.get("title5"));
                    //Justificatio Monto
                    cell7 = row6.createCell(48);
                    cell7.setCellValue(re.get(k).getVerificado_iva());
                    cell7.setCellStyle(styles.get("title5"));
                    //Justificatio estado
                    cell7 = row6.createCell(84);
                    cell7.setCellValue(re.get(k).getSolestado_observacion());
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() > 0) {
                    //Justificatio
                    cell7 = row6.createCell(47);
                    cell7.setCellValue(re.get(k).getSolicitud_centro_costo() + "-UCP-" + anio);
                    cell7.setCellStyle(styles.get("title5"));
                    //Justificatio Monto
                    cell7 = row6.createCell(48);
                    cell7.setCellValue(re.get(k).getVerificado_uni_iva());
                    cell7.setCellStyle(styles.get("title5"));
                    //Justificatio estado
                    cell7 = row6.createCell(84);
                    cell7.setCellValue(re.get(k).getSolicitud_cargo());
                    cell7.setCellStyle(styles.get("title5"));
                } else {
                    double monto = re.get(k).getVerificado_iva() + re.get(k).getVerificado_uni_iva();
                    //Justificatio
                    cell7 = row6.createCell(47);
                    cell7.setCellValue(re.get(k).getSolicitud_codigo() + ", " + re.get(k).getSolicitud_centro_costo() + "-UCP-" + anio);
                    cell7.setCellStyle(styles.get("title5"));
                    //Justificatio Monto
                    cell7 = row6.createCell(48);
                    cell7.setCellValue(monto);
                    cell7.setCellStyle(styles.get("title5"));
                    //Justificatio estado
                    cell7 = row6.createCell(84);
                    cell7.setCellValue(re.get(k).getSolestado_observacion() + ", " + re.get(k).getSolicitud_cargo());
                    cell7.setCellStyle(styles.get("title5"));
                }
                if (re.get(k).getMes().size() > 0) {
                    //Fecha certificación
                    cell7 = row6.createCell(52);
                    cell7.setCellValue(re.get(k).getMes().get(0).getFecha_inicio());
                    cell7.setCellStyle(styles.get("title5"));
                    //Código certificación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue(re.get(k).getMes().get(0).getSolicitud_codigo());
                    cell7.setCellStyle(styles.get("title5"));
                    //Monto Certificación
                    cell7 = row6.createCell(54);
                    cell7.setCellValue(re.get(k).getMes().get(0).getActividad_monto());
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha cur
                    cell7 = row6.createCell(68);
                    cell7.setCellValue(re.get(k).getMes().get(0).getFecha_fin());
                    cell7.setCellStyle(styles.get("title5"));
                    //Código cur
                    cell7 = row6.createCell(69);
                    cell7.setCellValue(re.get(k).getMes().get(0).getActividad_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Monto Certificación
                    cell7 = row6.createCell(70);
                    cell7.setCellValue(re.get(k).getMes().get(0).getAe_porcentaje());
                    cell7.setCellStyle(styles.get("title5"));
                } else {
                    //Fecha certificación
                    cell7 = row6.createCell(52);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Código certificación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Monto Certificación
                    cell7 = row6.createCell(54);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha cur
                    cell7 = row6.createCell(68);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Código cur
                    cell7 = row6.createCell(69);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Monto Certificación
                    cell7 = row6.createCell(70);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Valores que no descarga el sistema
                cell7 = row6.createCell(55);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(56);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(57);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(58);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(59);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(60);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(61);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(62);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(63);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(64);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(65);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(66);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Valores que no descarga el sistema
                cell7 = row6.createCell(67);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //STP
                if (re.get(k).getC1() > 0) {
                    if (re.get(k).getFechanopac().size() > 0) {
                        //Fecha envio
                        cell7 = row6.createCell(71);
                        cell7.setCellValue(re.get(k).getFechanopac().get(0).getFecha_inicio());
                        cell7.setCellStyle(styles.get("title5"));
                        //Fecha aprobación
                        cell7 = row6.createCell(72);
                        cell7.setCellValue(re.get(k).getFechanopac().get(0).getReq_descripcion());
                        cell7.setCellStyle(styles.get("title5"));
                        //Fecha recibido
                        cell7 = row6.createCell(75);
                        cell7.setCellValue(re.get(k).getFechanopac().get(0).getReq_cpc());
                        cell7.setCellStyle(styles.get("title5"));
                    } else {
                        //Fecha envio
                        cell7 = row6.createCell(71);
                        cell7.setCellValue("");
                        cell7.setCellStyle(styles.get("title5"));
                        //Fecha aprobación
                        cell7 = row6.createCell(72);
                        cell7.setCellValue("");
                        cell7.setCellStyle(styles.get("title5"));
                        //Fecha recibido
                        cell7 = row6.createCell(75);
                        cell7.setCellValue("");
                        cell7.setCellStyle(styles.get("title5"));
                    }
                    //Codigo stp
                    cell7 = row6.createCell(73);
                    cell7.setCellValue(re.get(k).getSolicitud_nombre()+"-STP-"+anio);
                    cell7.setCellStyle(styles.get("title5"));
                    //Monto stp
                    cell7 = row6.createCell(74);
                    cell7.setCellValue(re.get(k).getVerificado_uni_npac());
                    cell7.setCellStyle(styles.get("title5"));
                } else {
                    cell7 = row6.createCell(73);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    cell7 = row6.createCell(74);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    cell7 = row6.createCell(84);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                }
                if (re.get(k).getReq().size() > 0) {
                    //Fecha certificación
                    cell7 = row6.createCell(76);
                    cell7.setCellValue(re.get(k).getReq().get(0).getFecha_inicio());
                    cell7.setCellStyle(styles.get("title5"));
                    //Código certificación
                    cell7 = row6.createCell(77);
                    cell7.setCellValue(re.get(k).getReq().get(0).getSolicitud_codigo());
                    cell7.setCellStyle(styles.get("title5"));
                    //Monto Certificación
                    cell7 = row6.createCell(78);
                    cell7.setCellValue(re.get(k).getReq().get(0).getActividad_monto());
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha cur
                    cell7 = row6.createCell(80);
                    cell7.setCellValue(re.get(k).getReq().get(0).getFecha_fin());
                    cell7.setCellStyle(styles.get("title5"));
                    //Código cur
                    cell7 = row6.createCell(81);
                    cell7.setCellValue(re.get(k).getReq().get(0).getActividad_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Monto Certificación
                    cell7 = row6.createCell(82);
                    cell7.setCellValue(re.get(k).getReq().get(0).getAe_porcentaje());
                    cell7.setCellStyle(styles.get("title5"));
                } else {
                    //Fecha certificación
                    cell7 = row6.createCell(76);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Código certificación
                    cell7 = row6.createCell(77);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Monto Certificación
                    cell7 = row6.createCell(78);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Fecha cur
                    cell7 = row6.createCell(80);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Código cur
                    cell7 = row6.createCell(81);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Monto Certificación
                    cell7 = row6.createCell(82);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                }
                cell7.setCellStyle(styles.get("title5"));
                //Estado Presupuestarop
                cell7 = row6.createCell(83);
                if (re.get(k).getCp_valor() == 0) {
                    cell7.setCellValue("---");
                } else {
                    cell7.setCellValue(re.get(k).getCp_tipo());
                }
                cell7.setCellStyle(styles.get("title5"));
                //%
                cell7 = row6.createCell(84);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Salvaguardados
                cell7 = row6.createCell(85);
                if (re.get(k).getReq_salvaguardar()) {
                    cell7.setCellValue("SALVAGUARDADO");
                } else {
                    cell7.setCellValue("");
                }
                cell7.setCellStyle(styles.get("title5"));
                k++;
                m++;
            }
            //Deudas
            /*while (n < deudas.size()) {
                row6 = sheet.createRow(m);
                //Ejercicio
                cell7 = row6.createCell(0);
                cell7.setCellValue(deudas.get(n).getPresupuesto_ejercicio());
                cell7.setCellStyle(styles.get("title5"));
                //Entidad
                cell7 = row6.createCell(1);
                cell7.setCellValue(deudas.get(n).getPresupuesto_entidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad ejecutora
                cell7 = row6.createCell(2);
                cell7.setCellValue(deudas.get(n).getPresupuesto_unidad_ejec());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad desconcentrada
                cell7 = row6.createCell(3);
                cell7.setCellValue(deudas.get(n).getPresupuesto_unidad_desc());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(4);
                cell7.setCellValue(deudas.get(n).getPresupuesto_programa());
                cell7.setCellStyle(styles.get("title5"));
                //Subprograma
                cell7 = row6.createCell(5);
                cell7.setCellValue(deudas.get(n).getPresupuesto_subprograma());
                cell7.setCellStyle(styles.get("title5"));
                //Proyecto
                cell7 = row6.createCell(6);
                cell7.setCellValue(deudas.get(n).getPresupuesto_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(7);
                cell7.setCellValue(deudas.get(n).getPresupuesto_actividad());
                cell7.setCellStyle(styles.get("title5"));
                //Obras
                cell7 = row6.createCell(8);
                cell7.setCellValue(deudas.get(n).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Geografico
                cell7 = row6.createCell(9);
                cell7.setCellValue(deudas.get(n).getPresupuesto_geografico());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo
                cell7 = row6.createCell(10);
                cell7.setCellValue(deudas.get(n).getPresupuesto_renglo());
                cell7.setCellStyle(styles.get("title5"));
                //Grupo de gasto
                String sCadena = String.valueOf(deudas.get(n).getPresupuesto_renglo() + "00");
                String sSubCadena = sCadena.substring(0, 2);
                cell7 = row6.createCell(11);
                cell7.setCellValue(sSubCadena + "0000");
                cell7.setCellStyle(styles.get("title5"));
                //Renglo Auxiliar
                cell7 = row6.createCell(12);
                cell7.setCellValue(deudas.get(n).getPresupuesto_renglo_aux());
                cell7.setCellStyle(styles.get("title5"));
                //Fuente
                cell7 = row6.createCell(13);
                cell7.setCellValue(deudas.get(n).getPresupuesto_fuente());
                cell7.setCellStyle(styles.get("title5"));
                //Organismo
                cell7 = row6.createCell(14);
                cell7.setCellValue(deudas.get(n).getPresupuesto_organismo());
                cell7.setCellStyle(styles.get("title5"));
                //Correlativo
                cell7 = row6.createCell(15);
                cell7.setCellValue(deudas.get(n).getPresupuesto_correlativo());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(16);
                cell7.setCellValue(deudas.get(n).getPresupuesto_presupuesto());
                cell7.setCellStyle(styles.get("title5"));
                //CPC
                cell7 = row6.createCell(17);
                cell7.setCellValue(deudas.get(n).getReq_cpc());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo Compra
                cell7 = row6.createCell(18);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Detalle
                cell7 = row6.createCell(19);
                cell7.setCellValue(deudas.get(n).getReq_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento
                cell7 = row6.createCell(20);
                cell7.setCellValue(deudas.get(n).getReq_descripcion());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(21);
                cell7.setCellValue(deudas.get(n).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(22);
                cell7.setCellValue(deudas.get(n).getUnidad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(23);
                cell7.setCellValue(deudas.get(n).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(24);
                cell7.setCellValue(deudas.get(n).getReq_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva
                cell7 = row6.createCell(25);
                cell7.setCellValue(deudas.get(n).getReq_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad reforma
                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Unidad reforma
                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario reforma
                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva reforma
                cell7 = row6.createCell(29);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva reforma
                cell7 = row6.createCell(30);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Incremento / disminución
                cell7 = row6.createCell(31);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Reforma
                cell7 = row6.createCell(32);
                cell7.setCellValue(deudas.get(n).getReq_reforma());
                cell7.setCellStyle(styles.get("title5"));
                //Total presupuesto
                Double to = 0.0;
                cell7 = row6.createCell(33);
                to = deudas.get(n).getReq_costo_total();
                cell7.setCellValue(to);
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                cell7 = row6.createCell(34);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(35);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(36);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                //Unidad
                cell7 = row6.createCell(37);
                cell7.setCellValue(deudas.get(n).getAg_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Dependencia
                cell7 = row6.createCell(38);
                if (deudas.get(n).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                    cell7.setCellValue(deudas.get(n).getAg_nombre());
                } else {
                    cell7.setCellValue(deudas.get(n).getAg_alias());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Objetivo
                cell7 = row6.createCell(39);
                cell7.setCellValue("OEI-" + deudas.get(n).getPerspectiva_id());
                cell7.setCellStyle(styles.get("title5"));
                //Nombre del proyecto
                cell7 = row6.createCell(40);
                cell7.setCellValue(deudas.get(n).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad nombre
                cell7 = row6.createCell(41);
                cell7.setCellValue(deudas.get(n).getActividad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                cell7 = row6.createCell(42);
                cell7.setCellValue(deudas.get(n).getUsuario_titulo());
                cell7.setCellStyle(styles.get("title9"));
                //ID requerimiento
                cell7 = row6.createCell(43);
                cell7.setCellValue(deudas.get(n).getReq_id());
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(44);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Justificativo
                cell7 = row6.createCell(45);
                if (deudas.get(n).getSolicitud_codigo() == null) {
                    cell7.setCellValue(" ");
                } else {
                    cell7.setCellValue(deudas.get(n).getSolicitud_codigo() + "-STP-2021");
                }
                cell7.setCellStyle(styles.get("title5"));
                //Estado tramite
                cell7 = row6.createCell(46);
                if (((deudas.get(n).getDeu_iva() == 1 && deudas.get(n).getVerificado_iva() > 0) || (deudas.get(n).getDeu_iva() == 2 && deudas.get(n).getVerificado_uni_iva() > 0) || (deudas.get(n).getDeu_iva() == 3 && deudas.get(n).getVerificado_uni_npac() > 0)) && deudas.get(n).getSolicitud_codigo() != null) {
                    cell7.setCellValue("Dirección Financiera");
                } else if (deudas.get(n).getSolicitud_codigo() != null) {
                    cell7.setCellValue("Documento Aprobado");
                } else {
                    cell7.setCellValue("Sin Acción");
                }
                cell7.setCellStyle(styles.get("title5"));
                //Código tramite
                cell7 = row6.createCell(47);
                if (null == deudas.get(n).getSolicitud_nombre()) {
                    cell7.setCellValue("----");
                } else {
                    cell7.setCellValue(deudas.get(n).getSolicitud_nombre());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Estado Presupuestario
                cell7 = row6.createCell(48);
                if ((deudas.get(n).getVerificado_iva() > 0 || deudas.get(n).getVerificado_uni_iva() > 0) && deudas.get(n).getSolicitud_codigo() != null && null == deudas.get(n).getSolicitud_nombre()) {
                    cell7.setCellValue("VERIFICADO");
                } else if (deudas.get(n).getVerificado_uni_npac() > 0 && deudas.get(n).getSolicitud_codigo() != null && null == deudas.get(n).getSolicitud_nombre()) {
                    cell7.setCellValue("VERIFICADO");
                } else if (null != deudas.get(n).getSolicitud_nombre()) {
                    cell7.setCellValue(deudas.get(n).getTc_nombre());
                } else {
                    cell7.setCellValue("---");
                }
                cell7.setCellStyle(styles.get("title5"));
                //Valor
                cell7 = row6.createCell(49);
                double mo;
                if (null == deudas.get(n).getDeu_iva()) {
                    mo = deudas.get(n).getVerificado_iva();
                    cell7.setCellValue(deudas.get(n).getVerificado_iva());
                } else {
                    switch (deudas.get(n).getDeu_iva()) {
                        case 2:
                            mo = deudas.get(n).getVerificado_uni_iva();
                            cell7.setCellValue(deudas.get(n).getVerificado_uni_iva());
                            break;
                        case 3:
                            mo = deudas.get(n).getVerificado_uni_npac();
                            cell7.setCellValue(deudas.get(n).getVerificado_uni_npac());
                            break;
                        default:
                            mo = deudas.get(n).getVerificado_iva();
                            cell7.setCellValue(deudas.get(n).getVerificado_iva());
                            break;
                    }
                }
                cell7.setCellStyle(styles.get("title5"));
                //Saldo
                cell7 = row6.createCell(50);
                cell7.setCellValue(to - mo);
                cell7.setCellStyle(styles.get("title5"));
                //%
                cell7 = row6.createCell(51);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Observacion
                cell7 = row6.createCell(52);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Salvaguardado
                cell7 = row6.createCell(53);
                cell7.setCellValue("SALVAGUARDADO");
                cell7.setCellStyle(styles.get("title5"));
                m++;
                n++;
            }*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Write the output to a file
        try {
            // FileOutputStream fileOut = new FileOutputStream("requerimientos.xlsx");
            // wb.write(fileOut);
            //String path = "/var/www/html/ieb_docs/reportes/";
            wb.write(response.getOutputStream()); // Write workbook to response.
            wb.close();
            //fileOut.close();
//            File file = new File("requerimientos.xlsx");
////            // Abrir el archivo
//            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
