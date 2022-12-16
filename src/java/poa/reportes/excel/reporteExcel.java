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
import static poa.acceso.adTransaccion.ingresarTransaccion;
import poa.clases.cActividadRequerimiento;
import poa.clases.cProyecto;
import poa.clases.cTransaccion;

/**
 *
 * @author Erika Arévalo
 */
@MultipartConfig
public class reporteExcel extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {
        if (request.getParameter("accion") != null) {
            String strAccion = request.getParameter("accion");
            switch (strAccion) {
                case "reporteExcel": {
                    try {
                        reporteExcel(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteExcel21": {
                    try {
                        reporteExcel21(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteExcelCompras": {
                    try {
                        reporteExcelCompras(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteExcelVer": {
                    try {
                        reporteExcelVer(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteExcelVer21": {
                    try {
                        reporteExcelVer21(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteExcelUnidades": {
                    try {
                        reporteExcelUnidades(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteExcelUnidades21": {
                    try {
                        reporteExcelUnidades21(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteExcelDeudas": {
                    try {
                        reporteExcelDeudas(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteExcelComprometidos": {
                    try {
                        reporteExcelComprometidos(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteJustificativoExcel": {
                    try {
                        reporteJustificativoExcel(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteEvaluacion": {
                    try {
                        reporteEvaluacion(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reporteEvaluacion2": {
                    try {
                        reporteEvaluacion2(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "subirExcelPresupuesto": {
                    try {
                        subirExcelPresupuesto(request, response);
                    } catch (InvalidFormatException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "subirExcelPresupuesto21": {
                    try {
                        subirExcelPresupuesto21(request, response);
                    } catch (InvalidFormatException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "subirExcelPresupuestoDeudas": {
                    try {
                        subirExcelPresupuestoDeudas(request, response);
                    } catch (InvalidFormatException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "subirExcelComprometidos": {
                    try {
                        subirExcelComprometidos(request, response);
                    } catch (InvalidFormatException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "subirExcelRe": {
                    try {
                        subirExcelRe(request, response);
                    } catch (InvalidFormatException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "subirExcelSaldos": {
                    try {
                        subirExcelSaldos(request, response);
                    } catch (InvalidFormatException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "subirExcelReformaP": {
                    try {
                        subirExcelReformaP(request, response);
                    } catch (InvalidFormatException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "subirExcelComprometidos21": {
                    try {
                        subirExcelComprometidos21(request, response);
                    } catch (InvalidFormatException ex) {
                        Logger.getLogger(reporteExcel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "subirExcelComprometidos22": {
                    try {
                        subirExcelComprometidos22(request, response);
                    } catch (InvalidFormatException ex) {
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

    private void reporteExcel(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=requerimientosEspoch.xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> re = new ArrayList<cActividadRequerimiento>();
        re = aReq.ListarRequerimientosExcel();
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
        Cell cell7;
        cell.setCellValue("PLAN ANUAL DE COMPRAS");
        cell2.setCellValue("Por favor no modifique la estructura del archivo para subir al sistema USHAY - Módulo Facilitador de Contratación Pública");
        cell3.setCellValue("RUC_ENTIDAD");
        cell4.setCellValue("0660001250001");
        cell5.setCellValue("INFORMACION DE LA PARTIDA PRESUPUESTARIA");
        cell6.setCellValue("INFORMACION DETALLADA DE LOS PRODUCTOS");
        cell.setCellStyle(styles.get("title"));
        cell2.setCellStyle(styles.get("title2"));
        cell3.setCellStyle(styles.get("title2"));
        cell4.setCellStyle(styles.get("title2"));
        cell5.setCellStyle(styles.get("title3"));
        cell6.setCellStyle(styles.get("title4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AI$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AH$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$P$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$Q$4:$AI$4"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A5:AS5"));

        for (int i = 0; i < 16; i++) {
            cell7 = row5.createCell(i);
            cell7.setCellValue(titles[i]);
            cell7.setCellStyle(styles.get("title3"));
        }
        int p = 0;
        for (int j = 16; j < 23; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        for (int j = 23; j < 25; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title8"));
            p++;
        }

        for (int j = 25; j < 35; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        cell7 = row5.createCell(35);
        cell7.setCellValue("UNIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(36);
        cell7.setCellValue("DEPENDENCIA");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(37);
        cell7.setCellValue("OEI");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(38);
        cell7.setCellValue("NOMBRE PROYECTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(39);
        cell7.setCellValue("NOMBRE ACTIVIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(40);
        cell7.setCellValue("FINANCIAMIENTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(41);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(42);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(43);
        cell7.setCellValue("N° REFORMA");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(44);
        cell7.setCellValue("AÑO");
        cell7.setCellStyle(styles.get("title9"));

        int k = 0;
        int m = 5;
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
                //Presupuesto
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
                //Cuatrimestres
                cell7 = row6.createCell(25);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                for (int c = 0; c < re.get(k).getCuatri().size(); c++) {
                    switch (re.get(k).getCuatri().get(c).getMes_id()) {
                        case 1:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(25);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(25);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 2:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(26);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(26);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 3:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(27);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(27);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                    }
                }
                //Tipo producto
                cell7 = row6.createCell(28);
                cell7.setCellValue(re.get(k).getPac_tipo_producto());
                cell7.setCellStyle(styles.get("title5"));
                //Catalogo
                cell7 = row6.createCell(29);
                cell7.setCellValue(re.get(k).getPac_catalogo());
                cell7.setCellStyle(styles.get("title5"));
                //Procedimiento Sugerido
                cell7 = row6.createCell(30);
                cell7.setCellValue(re.get(k).getPac_procedimiento_sug());
                cell7.setCellStyle(styles.get("title5"));
                //Fondos
                cell7 = row6.createCell(31);
                cell7.setCellValue(re.get(k).getPac_fondo_bid());
                cell7.setCellStyle(styles.get("title5"));
                //Numero codigo
                cell7 = row6.createCell(32);
                cell7.setCellValue(re.get(k).getPac_num_operacion());
                cell7.setCellStyle(styles.get("title5"));
                //Numero proyecto
                cell7 = row6.createCell(33);
                cell7.setCellValue(re.get(k).getPac_codigo_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo regimen
                cell7 = row6.createCell(34);
                cell7.setCellValue(re.get(k).getPac_tipo_regimen());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(35);
                cell7.setCellValue(re.get(k).getAg_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Dependencia
                cell7 = row6.createCell(36);
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
                cell7 = row6.createCell(37);
                cell7.setCellValue("OEI-" + re.get(k).getPerspectiva_id());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Nombre del proyecto
                cell7 = row6.createCell(38);
                cell7.setCellValue(re.get(k).getProyecto_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Actividad nombre
                cell7 = row6.createCell(39);
                cell7.setCellValue(re.get(k).getActividad_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Presupuesto
                cell7 = row6.createCell(40);
                cell7.setCellValue(re.get(k).getFinanciamiento_nombre());
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
                //Reforma
                cell7 = row6.createCell(43);
                if (re.get(k).getReq_reforma() > 0) {
                    cell7.setCellValue("RA-" + re.get(k).getReq_reforma() + "-ESPOCH");
                } else {
                    cell7.setCellValue(" ");
                }
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(44);
                cell7.setCellValue(re.get(k).getReq_anio());
                cell7.setCellStyle(styles.get("title5"));
                k++;
                m++;
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

    private void reporteExcel21(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=requerimientosEspoch.xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> re = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> re2 = new ArrayList<cActividadRequerimiento>();
        String anio = request.getParameter("anioS");
        if (anio.equals("2021")) {
            re = aReq.ListarRequerimientosExcel21();
        } else if (anio.equals("2022")) {
            re = aReq.ListarRequerimientosExcel22();
        } else {
            re = aReq.ListarRequerimientosExcel23();
        }
        re2 = aReq.ListarRequerimientosExcelDeudas2(Integer.parseInt(anio));
        Row row = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        Row row3 = sheet.createRow(2);
        Row row4 = sheet.createRow(3);
        Row row5 = sheet.createRow(5);
        Row row6 = sheet.createRow(6);
        Row row8 = sheet.createRow(4);
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
        Cell cell8 = row8.createCell(4);
        Cell cell9 = row4.createCell(36);
        Cell cell10 = row4.createCell(42);
        Cell cell7;
        cell.setCellValue("PLAN ANUAL DE COMPRAS");
        cell2.setCellValue("Por favor no modifique la estructura del archivo para subir al sistema USHAY - Módulo Facilitador de Contratación Pública");
        cell3.setCellValue("RUC_ENTIDAD");
        cell4.setCellValue("0660001250001");
        cell5.setCellValue("ESTRUCTURA PRESUPUESTARIA");
        cell6.setCellValue("INFORMACION DETALLADA DE LOS PRODUCTOS");
        cell5.setCellValue("ESTRUCTURA PRESUPUESTARIA");
        cell9.setCellValue("DATOS DEL PROYECTO");
        cell10.setCellValue("DATOS ADICIONALES");
        cell.setCellStyle(styles.get("title"));
        cell2.setCellStyle(styles.get("title2"));
        cell3.setCellStyle(styles.get("title2"));
        cell4.setCellStyle(styles.get("title2"));
        cell5.setCellStyle(styles.get("title3"));
        cell6.setCellStyle(styles.get("title4"));
        cell8.setCellStyle(styles.get("titleret"));
        cell9.setCellStyle(styles.get("title8"));
        cell10.setCellStyle(styles.get("title9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AT$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AT$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$Q$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$R$4:$AJ$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AK$4:$AP$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AQ$4:$AT$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$E$5:$K$5"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A6:AT6"));

        for (int i = 0; i < 11; i++) {
            cell7 = row5.createCell(i);
            cell7.setCellValue(titles[i]);
            cell7.setCellStyle(styles.get("title3"));
        }

        cell7 = row5.createCell(11);
        cell7.setCellValue("GRUPO DE GASTO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(12);
        cell7.setCellValue("RENGLON AUXILIAR");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(13);
        cell7.setCellValue("FUENTE");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(14);
        cell7.setCellValue("ORGANISMO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(15);
        cell7.setCellValue("CORRELATIVO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(16);
        cell7.setCellValue("TIPO DE PRESUPUESTO");
        cell7.setCellStyle(styles.get("title3"));

        int p = 0;
        for (int j = 17; j < 24; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        for (int j = 24; j < 26; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title8"));
            p++;
        }

        for (int j = 26; j < 36; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

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
        cell7.setCellValue("FINANCIAMIENTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(42);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(43);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(44);
        cell7.setCellValue("N° REFORMA");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(45);
        cell7.setCellValue("AÑO");
        cell7.setCellStyle(styles.get("title9"));

        int k = 0;
        int m = 6;
        int n = 0;
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
                //Obra
                cell7 = row6.createCell(8);
                cell7.setCellValue(re.get(k).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Geográfico
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
                //Renglo auxiliar
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
                cell7.setCellValue(re.get(k).getPresupuesto_organismo());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo presupuesto
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
                //Cuatrimestres
                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                for (int c = 0; c < re.get(k).getCuatri().size(); c++) {
                    switch (re.get(k).getCuatri().get(c).getMes_id()) {
                        case 1:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(26);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(26);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 2:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(27);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(27);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 3:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(28);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(28);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                    }
                }
                //Tipo producto
                cell7 = row6.createCell(29);
                cell7.setCellValue(re.get(k).getPac_tipo_producto());
                cell7.setCellStyle(styles.get("title5"));
                //Catalogo
                cell7 = row6.createCell(30);
                cell7.setCellValue(re.get(k).getPac_catalogo());
                cell7.setCellStyle(styles.get("title5"));
                //Procedimiento Sugerido
                cell7 = row6.createCell(31);
                cell7.setCellValue(re.get(k).getPac_procedimiento_sug());
                cell7.setCellStyle(styles.get("title5"));
                //Fondos
                cell7 = row6.createCell(32);
                cell7.setCellValue(re.get(k).getPac_fondo_bid());
                cell7.setCellStyle(styles.get("title5"));
                //Numero codigo
                cell7 = row6.createCell(33);
                cell7.setCellValue(re.get(k).getPac_num_operacion());
                cell7.setCellStyle(styles.get("title5"));
                //Numero proyecto
                cell7 = row6.createCell(34);
                cell7.setCellValue(re.get(k).getPac_codigo_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo regimen
                cell7 = row6.createCell(35);
                cell7.setCellValue(re.get(k).getPac_tipo_regimen());
                cell7.setCellStyle(styles.get("title5"));
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
                //Presupuesto
                cell7 = row6.createCell(41);
                cell7.setCellValue(re.get(k).getFinanciamiento_nombre());
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
                //Reforma
                cell7 = row6.createCell(44);
                if (re.get(k).getReq_reforma() > 0) {
                    cell7.setCellValue("RA-" + re.get(k).getReq_reforma() + "-ESPOCH");
                } else {
                    cell7.setCellValue(" ");
                }
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(45);
                cell7.setCellValue(re.get(k).getReq_anio());
                cell7.setCellStyle(styles.get("title5"));
                k++;
                m++;
            }

            k = 0;
            while (k < re2.size()) {
                row6 = sheet.createRow(m);
                //Ejercicio
                cell7 = row6.createCell(0);
                cell7.setCellValue(re2.get(k).getPresupuesto_ejercicio());
                cell7.setCellStyle(styles.get("title5"));
                //Entidad
                cell7 = row6.createCell(1);
                cell7.setCellValue(re2.get(k).getPresupuesto_entidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad ejecutora
                cell7 = row6.createCell(2);
                cell7.setCellValue(re2.get(k).getPresupuesto_unidad_ejec());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad desconcentrada
                cell7 = row6.createCell(3);
                cell7.setCellValue(re2.get(k).getPresupuesto_unidad_desc());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(4);
                cell7.setCellValue(re2.get(k).getPresupuesto_programa());
                cell7.setCellStyle(styles.get("title5"));
                //Subprograma
                cell7 = row6.createCell(5);
                cell7.setCellValue(re2.get(k).getPresupuesto_subprograma());
                cell7.setCellStyle(styles.get("title5"));
                //Proyecto
                cell7 = row6.createCell(6);
                cell7.setCellValue(re2.get(k).getPresupuesto_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(7);
                cell7.setCellValue(re2.get(k).getPresupuesto_actividad());
                cell7.setCellStyle(styles.get("title5"));
                //Obras
                cell7 = row6.createCell(8);
                cell7.setCellValue(re2.get(k).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Geografico
                cell7 = row6.createCell(9);
                cell7.setCellValue(re2.get(k).getPresupuesto_geografico());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo
                cell7 = row6.createCell(10);
                cell7.setCellValue(re2.get(k).getPresupuesto_renglo());
                cell7.setCellStyle(styles.get("title5"));
                //Grupo de gasto
                String sCadena = String.valueOf(re2.get(k).getPresupuesto_renglo() + "000");
                String sSubCadena = sCadena.substring(0, 2);
                cell7 = row6.createCell(11);
                cell7.setCellValue(sSubCadena + "0000");
                cell7.setCellStyle(styles.get("title5"));
                //Renglo auxiliar
                cell7 = row6.createCell(12);
                cell7.setCellValue(re2.get(k).getPresupuesto_renglo_aux());
                cell7.setCellStyle(styles.get("title5"));
                //Fuente
                cell7 = row6.createCell(13);
                cell7.setCellValue(re2.get(k).getPresupuesto_fuente());
                cell7.setCellStyle(styles.get("title5"));
                //Organismo
                cell7 = row6.createCell(14);
                cell7.setCellValue(re2.get(k).getPresupuesto_organismo());
                cell7.setCellStyle(styles.get("title5"));
                //Correlativo
                cell7 = row6.createCell(15);
                cell7.setCellValue(re2.get(k).getPresupuesto_correlativo());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo presupuesto
                cell7 = row6.createCell(16);
                cell7.setCellValue(re2.get(k).getPresupuesto_presupuesto());
                cell7.setCellStyle(styles.get("title5"));
                //CPC
                cell7 = row6.createCell(17);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Tipo Compra
                cell7 = row6.createCell(18);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Detalle
                cell7 = row6.createCell(19);
                cell7.setCellValue(re2.get(k).getReq_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento
                cell7 = row6.createCell(20);
                cell7.setCellValue(re2.get(k).getReq_descripcion());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(21);
                cell7.setCellValue(re2.get(k).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(22);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(23);
                cell7.setCellValue(re2.get(k).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(24);
                cell7.setCellValue(re2.get(k).getReq_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva
                cell7 = row6.createCell(25);
                cell7.setCellValue(re2.get(k).getReq_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                //Tipo producto
                cell7 = row6.createCell(29);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Catalogo
                cell7 = row6.createCell(30);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Procedimiento Sugerido
                cell7 = row6.createCell(31);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Fondos
                cell7 = row6.createCell(32);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Numero codigo
                cell7 = row6.createCell(33);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Numero proyecto
                cell7 = row6.createCell(34);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Tipo regimen
                cell7 = row6.createCell(35);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(36);
                cell7.setCellValue(re2.get(k).getAg_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Dependencia
                cell7 = row6.createCell(37);
                if (re2.get(k).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                    cell7.setCellValue(re2.get(k).getAg_nombre());
                } else {
                    cell7.setCellValue(re2.get(k).getAg_alias());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Objetivo
                cell7 = row6.createCell(38);
                cell7.setCellValue("OEI-" + re2.get(k).getPerspectiva_id());
                cell7.setCellStyle(styles.get("title5"));
                //Nombre del proyecto
                cell7 = row6.createCell(39);
                cell7.setCellValue(re2.get(k).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad nombre
                cell7 = row6.createCell(40);
                cell7.setCellValue(re2.get(k).getActividad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(41);
                cell7.setCellValue(re2.get(k).getFinanciamiento_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                cell7 = row6.createCell(42);
                cell7.setCellValue(re2.get(k).getActividad_responsable());
                cell7.setCellStyle(styles.get("title9"));
                //ID requerimiento
                cell7 = row6.createCell(43);
                cell7.setCellValue(re2.get(k).getReq_id());
                cell7.setCellStyle(styles.get("title9"));
                //Reforma
                cell7 = row6.createCell(44);
                if (re2.get(k).getReq_reforma() > 0) {
                    cell7.setCellValue("RA-" + re2.get(k).getReq_reforma() + "-ESPOCH");
                } else {
                    cell7.setCellValue(" ");
                }
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(45);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                k++;
                m++;
            }
//            sheet.autoSizeColumn(3);
//            sheet.setColumnWidth(5, 20 * 200);
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

    private void reporteExcel21NSis(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=requerimientosEspoch.xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> re = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> re2 = new ArrayList<cActividadRequerimiento>();
        String anio = request.getParameter("anioS");
        if (anio.equals("2021")) {
            re = aReq.ListarRequerimientosExcel21();
        } else {
            re = aReq.ListarRequerimientosExcel22();
        }
        re2 = aReq.ListarRequerimientosExcelDeudas2(Integer.parseInt(anio));
        Row row = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        Row row3 = sheet.createRow(2);
        Row row4 = sheet.createRow(3);
        Row row5 = sheet.createRow(5);
        Row row6 = sheet.createRow(6);
        Row row8 = sheet.createRow(4);
        row.setHeightInPoints(23);
        row2.setHeightInPoints(16);
        row4.setHeightInPoints(16);
        row5.setHeightInPoints(70);
        Cell cell = row.createCell(0);
        Cell cell2 = row2.createCell(0);
        Cell cell3 = row3.createCell(0);
        Cell cell4 = row3.createCell(1);
        Cell cell5 = row4.createCell(0);
        Cell cell6 = row4.createCell(14);
        Cell cell8 = row8.createCell(4);
        Cell cell7;
        cell.setCellValue("PLAN ANUAL DE COMPRAS");
        cell2.setCellValue("Por favor no modifique la estructura del archivo para subir al sistema USHAY - Módulo Facilitador de Contratación Pública");
        cell3.setCellValue("RUC_ENTIDAD");
        cell4.setCellValue("0660001250001");
        cell5.setCellValue("ESTRUCTURA PRESUPUESTARIA");
        cell6.setCellValue("INFORMACION DETALLADA DE LOS PRODUCTOS");
        cell8.setCellValue("POSICIÓN PRESUPUESTARIA");
        cell.setCellStyle(styles.get("title"));
        cell2.setCellStyle(styles.get("title2"));
        cell3.setCellStyle(styles.get("title2"));
        cell4.setCellStyle(styles.get("title2"));
        cell5.setCellStyle(styles.get("title3"));
        cell6.setCellStyle(styles.get("title4"));
        cell8.setCellStyle(styles.get("titleret"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AI$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AH$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$N$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$O$4:$AG$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$E$5:$K$5"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A6:AQ6"));

        cell7 = row5.createCell(0);
        cell7.setCellValue("EJERCICIO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(1);
        cell7.setCellValue("UDAF");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(2);
        cell7.setCellValue("ORGANO GESTOR");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(3);
        cell7.setCellValue("CENTRO GESTOR");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(4);
        cell7.setCellValue("TIPO PRESUPUESTO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(5);
        cell7.setCellValue("PRODUCTO INSTITUCIONAL");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(6);
        cell7.setCellValue("ACTIVIDAD");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(7);
        cell7.setCellValue("OBRA/TAREA");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(8);
        cell7.setCellValue("ENLACE PTO-CONT");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(9);
        cell7.setCellValue("ITEM");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(10);
        cell7.setCellValue("GRUPO PRESUPUESTARIO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(11);
        cell7.setCellValue("AREA FUNCIONAL");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(12);
        cell7.setCellValue("FONDO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(13);
        cell7.setCellValue("PROYECTO");
        cell7.setCellStyle(styles.get("title3"));

        int p = 0;
        for (int j = 14; j < 21; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        for (int j = 21; j < 23; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title8"));
            p++;
        }

        for (int j = 23; j < 33; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        cell7 = row5.createCell(33);
        cell7.setCellValue("UNIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(34);
        cell7.setCellValue("DEPENDENCIA");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(35);
        cell7.setCellValue("OEI");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(36);
        cell7.setCellValue("NOMBRE PROYECTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(37);
        cell7.setCellValue("NOMBRE ACTIVIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(38);
        cell7.setCellValue("FINANCIAMIENTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(39);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(40);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(41);
        cell7.setCellValue("N° REFORMA");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(42);
        cell7.setCellValue("AÑO");
        cell7.setCellStyle(styles.get("title9"));

        int k = 0;
        int m = 6;
        int n = 0;
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
                cell7.setCellValue(re.get(k).getPresupuesto_geografico());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad desconcentrada
                cell7 = row6.createCell(3);
                cell7.setCellValue(re.get(k).getPresupuesto_renglo_aux());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(4);
                cell7.setCellValue("G");
                cell7.setCellStyle(styles.get("title5"));
                //Producto
                cell7 = row6.createCell(5);
                cell7.setCellValue(re.get(k).getPresupuesto_unidad_ejec());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(6);
                cell7.setCellValue(re.get(k).getPresupuesto_actividad());
                cell7.setCellStyle(styles.get("title5"));
                //Obra
                cell7 = row6.createCell(7);
                cell7.setCellValue(re.get(k).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(8);
                cell7.setCellValue(re.get(k).getPresupuesto_programa());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo
                cell7 = row6.createCell(9);
                cell7.setCellValue(re.get(k).getPresupuesto_renglo());
                cell7.setCellStyle(styles.get("title5"));
                //Grupo Presupuestario
                String sCadena = String.valueOf(re.get(k).getPresupuesto_renglo() + "00");
                String sSubCadena = sCadena.substring(0, 2);
                cell7 = row6.createCell(10);
                cell7.setCellValue(sSubCadena + "0000");
                cell7.setCellStyle(styles.get("title5"));
                //area funcional
                cell7 = row6.createCell(11);
                cell7.setCellValue(re.get(k).getPresupuesto_unidad_desc());
                cell7.setCellStyle(styles.get("title5"));
                //Fondo
                cell7 = row6.createCell(12);
                cell7.setCellValue(re.get(k).getPresupuesto_fuente());
                cell7.setCellStyle(styles.get("title5"));
                //Proyecto
                cell7 = row6.createCell(13);
                cell7.setCellValue(re.get(k).getPresupuesto_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //CPC
                cell7 = row6.createCell(14);
                cell7.setCellValue(re.get(k).getReq_cpc());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo Compra
                cell7 = row6.createCell(15);
                cell7.setCellValue(re.get(k).getTc_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Detalle
                cell7 = row6.createCell(16);
                cell7.setCellValue(re.get(k).getReq_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento
                cell7 = row6.createCell(17);
                cell7.setCellValue(re.get(k).getReq_descripcion());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(18);
                cell7.setCellValue(re.get(k).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(19);
                cell7.setCellValue(re.get(k).getUnidad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(20);
                cell7.setCellValue(re.get(k).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(21);
                cell7.setCellValue(re.get(k).getReq_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva
                cell7 = row6.createCell(22);
                cell7.setCellValue(re.get(k).getReq_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                cell7 = row6.createCell(23);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(24);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(25);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                for (int c = 0; c < re.get(k).getCuatri().size(); c++) {
                    switch (re.get(k).getCuatri().get(c).getMes_id()) {
                        case 1:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(23);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(23);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 2:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(24);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(24);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 3:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(25);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(25);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                    }
                }
                //Tipo producto
                cell7 = row6.createCell(26);
                cell7.setCellValue(re.get(k).getPac_tipo_producto());
                cell7.setCellStyle(styles.get("title5"));
                //Catalogo
                cell7 = row6.createCell(27);
                cell7.setCellValue(re.get(k).getPac_catalogo());
                cell7.setCellStyle(styles.get("title5"));
                //Procedimiento Sugerido
                cell7 = row6.createCell(28);
                cell7.setCellValue(re.get(k).getPac_procedimiento_sug());
                cell7.setCellStyle(styles.get("title5"));
                //Fondos
                cell7 = row6.createCell(29);
                cell7.setCellValue(re.get(k).getPac_fondo_bid());
                cell7.setCellStyle(styles.get("title5"));
                //Numero codigo
                cell7 = row6.createCell(30);
                cell7.setCellValue(re.get(k).getPac_num_operacion());
                cell7.setCellStyle(styles.get("title5"));
                //Numero proyecto
                cell7 = row6.createCell(31);
                cell7.setCellValue(re.get(k).getPac_codigo_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo regimen
                cell7 = row6.createCell(32);
                cell7.setCellValue(re.get(k).getPac_tipo_regimen());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(33);
                cell7.setCellValue(re.get(k).getAg_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Dependencia
                cell7 = row6.createCell(34);
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
                cell7 = row6.createCell(35);
                cell7.setCellValue("OEI-" + re.get(k).getPerspectiva_id());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Nombre del proyecto
                cell7 = row6.createCell(36);
                cell7.setCellValue(re.get(k).getProyecto_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Actividad nombre
                cell7 = row6.createCell(37);
                cell7.setCellValue(re.get(k).getActividad_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Presupuesto
                cell7 = row6.createCell(38);
                cell7.setCellValue(re.get(k).getFinanciamiento_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Tipo requerimiento
                try {
                    if (re.get(k).getUnidad_nombre().isEmpty()) {
                        cell7 = row6.createCell(39);
                        cell7.setCellValue("NO PAC");
                        if (re.get(k).getReq_estado() == 3) {
                            cell7.setCellStyle(styles.get("titlepre"));
                        } else {
                            cell7.setCellStyle(styles.get("title9"));
                        }
                    } else {
                        cell7 = row6.createCell(39);
                        cell7.setCellValue("PAC");
                        if (re.get(k).getReq_estado() == 3) {
                            cell7.setCellStyle(styles.get("titlepre"));
                        } else {
                            cell7.setCellStyle(styles.get("title9"));
                        }
                    }
                } catch (Exception e) {
                    cell7 = row6.createCell(39);
                    cell7.setCellValue("NO PAC");
                    if (re.get(k).getReq_estado() == 3) {
                        cell7.setCellStyle(styles.get("titlepre"));
                    } else {
                        cell7.setCellStyle(styles.get("title9"));
                    }
                }
                //ID requerimiento
                cell7 = row6.createCell(40);
                cell7.setCellValue(re.get(k).getReq_req_id());
                if (re.get(k).getReq_prioridad_fecha() == null) {
                    cell7.setCellStyle(styles.get("title9"));
                } else {
                    cell7.setCellStyle(styles.get("titlepr"));
                }
                //Reforma
                cell7 = row6.createCell(41);
                if (re.get(k).getReq_reforma() > 0) {
                    cell7.setCellValue("RA-" + re.get(k).getReq_reforma() + "-ESPOCH");
                } else {
                    cell7.setCellValue(" ");
                }
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(42);
                cell7.setCellValue(re.get(k).getReq_anio());
                cell7.setCellStyle(styles.get("title5"));
                k++;
                m++;
            }

            k = 0;
            while (k < re2.size()) {
                row6 = sheet.createRow(m);
                //Ejercicio
                cell7 = row6.createCell(0);
                cell7.setCellValue(re2.get(k).getPresupuesto_ejercicio());
                cell7.setCellStyle(styles.get("title5"));
                //Entidad
                cell7 = row6.createCell(1);
                cell7.setCellValue(re2.get(k).getPresupuesto_entidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad ejecutora
                cell7 = row6.createCell(2);
                cell7.setCellValue(re2.get(k).getPresupuesto_geografico());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad desconcentrada
                cell7 = row6.createCell(3);
                cell7.setCellValue(re2.get(k).getPresupuesto_renglo_aux());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(4);
                cell7.setCellValue("G");
                cell7.setCellStyle(styles.get("title5"));
                //Producto
                cell7 = row6.createCell(5);
                cell7.setCellValue(re2.get(k).getPresupuesto_unidad_ejec());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(6);
                cell7.setCellValue(re2.get(k).getPresupuesto_actividad());
                cell7.setCellStyle(styles.get("title5"));
                //Obra
                cell7 = row6.createCell(7);
                cell7.setCellValue(re2.get(k).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(8);
                cell7.setCellValue(re2.get(k).getPresupuesto_programa());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo
                cell7 = row6.createCell(9);
                cell7.setCellValue(re2.get(k).getPresupuesto_renglo());
                cell7.setCellStyle(styles.get("title5"));
                //Grupo Presupuestario
                String sCadena = String.valueOf(re2.get(k).getPresupuesto_renglo() + "000");
                String sSubCadena = sCadena.substring(0, 2);
                cell7 = row6.createCell(10);
                cell7.setCellValue(sSubCadena + "0000");
                cell7.setCellStyle(styles.get("title5"));
                //area funcional
                cell7 = row6.createCell(11);
                cell7.setCellValue(re2.get(k).getPresupuesto_unidad_desc());
                cell7.setCellStyle(styles.get("title5"));
                //Fondo
                cell7 = row6.createCell(12);
                cell7.setCellValue(re2.get(k).getPresupuesto_fuente());
                cell7.setCellStyle(styles.get("title5"));
                //Proyecto
                cell7 = row6.createCell(13);
                cell7.setCellValue(re2.get(k).getPresupuesto_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //CPC
                cell7 = row6.createCell(14);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Tipo Compra
                cell7 = row6.createCell(15);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Detalle
                cell7 = row6.createCell(16);
                cell7.setCellValue(re2.get(k).getReq_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento
                cell7 = row6.createCell(17);
                cell7.setCellValue(re2.get(k).getReq_descripcion());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(18);
                cell7.setCellValue(re2.get(k).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(19);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(20);
                cell7.setCellValue(re2.get(k).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(21);
                cell7.setCellValue(re2.get(k).getReq_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva
                cell7 = row6.createCell(22);
                cell7.setCellValue(re2.get(k).getReq_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                cell7 = row6.createCell(23);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(24);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(25);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                //Tipo producto
                cell7 = row6.createCell(26);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Catalogo
                cell7 = row6.createCell(27);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Procedimiento Sugerido
                cell7 = row6.createCell(28);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Fondos
                cell7 = row6.createCell(29);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Numero codigo
                cell7 = row6.createCell(30);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Numero proyecto
                cell7 = row6.createCell(31);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Tipo regimen
                cell7 = row6.createCell(32);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(33);
                cell7.setCellValue(re2.get(k).getAg_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Dependencia
                cell7 = row6.createCell(34);
                if (re2.get(k).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                    cell7.setCellValue(re2.get(k).getAg_nombre());
                } else {
                    cell7.setCellValue(re2.get(k).getAg_alias());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Objetivo
                cell7 = row6.createCell(35);
                cell7.setCellValue("OEI-" + re2.get(k).getPerspectiva_id());
                cell7.setCellStyle(styles.get("title5"));
                //Nombre del proyecto
                cell7 = row6.createCell(36);
                cell7.setCellValue(re2.get(k).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad nombre
                cell7 = row6.createCell(37);
                cell7.setCellValue(re2.get(k).getActividad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(38);
                cell7.setCellValue(re2.get(k).getFinanciamiento_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                cell7 = row6.createCell(39);
                cell7.setCellValue(re2.get(k).getActividad_responsable());
                cell7.setCellStyle(styles.get("title9"));
                //ID requerimiento
                cell7 = row6.createCell(40);
                cell7.setCellValue(re2.get(k).getReq_id());
                cell7.setCellStyle(styles.get("title9"));
                //Reforma
                cell7 = row6.createCell(41);
                if (re2.get(k).getReq_reforma() > 0) {
                    cell7.setCellValue("RA-" + re2.get(k).getReq_reforma() + "-ESPOCH");
                } else {
                    cell7.setCellValue(" ");
                }
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(42);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                k++;
                m++;
            }
            sheet.autoSizeColumn(3);
            sheet.setColumnWidth(5, 20 * 200);
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

    private void reporteExcelCompras(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=requerimientosEspochPAC.xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> re = new ArrayList<cActividadRequerimiento>();
        re = aReq.ListarRequerimientosExcelCompras();
        Row row = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        Row row3 = sheet.createRow(2);
        Row row4 = sheet.createRow(3);
        Row row5 = sheet.createRow(4);
        Row row6 = sheet.createRow(6);
        Row row8 = sheet.createRow(4);
        row.setHeightInPoints(23);
        row2.setHeightInPoints(16);
        row4.setHeightInPoints(16);
        row5.setHeightInPoints(70);
        Cell cell = row.createCell(0);
        Cell cell2 = row2.createCell(0);
        Cell cell3 = row3.createCell(0);
        Cell cell4 = row3.createCell(1);
        Cell cell5 = row4.createCell(0);
        Cell cell6 = row4.createCell(8);
        Cell cell7;
        cell.setCellValue("PLAN ANUAL DE COMPRAS");
        cell2.setCellValue("Por favor no modifique la estructura del archivo para subir al sistema USHAY - Módulo Facilitador de Contratación Pública");
        cell3.setCellValue("RUC_ENTIDAD");
        cell4.setCellValue("0660001250001");
        cell5.setCellValue("ESTRUCTURA PRESUPUESTARIA");
        cell6.setCellValue("INFORMACION DETALLADA DE LOS PRODUCTOS");
        cell.setCellStyle(styles.get("title"));
        cell2.setCellStyle(styles.get("title2"));
        cell3.setCellStyle(styles.get("title2"));
        cell4.setCellStyle(styles.get("title2"));
        cell5.setCellStyle(styles.get("title3"));
        cell6.setCellStyle(styles.get("title4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AI$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AH$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$P$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$Q$4:$AG$4"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A5:AQ5"));

        for (int i = 0; i < 11; i++) {
            cell7 = row5.createCell(i);
            cell7.setCellValue(titles[i]);
            cell7.setCellStyle(styles.get("title3"));
        }

        cell7 = row5.createCell(11);
        cell7.setCellValue("GRUPO DE GASTO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(12);
        cell7.setCellValue("RENGLON AUXILIAR");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(13);
        cell7.setCellValue("FUENTE");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(14);
        cell7.setCellValue("ORGANISMO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(15);
        cell7.setCellValue("CORRELATIVO");
        cell7.setCellStyle(styles.get("title3"));

        int p = 0;
        for (int j = 16; j < 23; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        for (int j = 23; j < 25; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title8"));
            p++;
        }

        for (int j = 25; j < 35; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        cell7 = row5.createCell(35);
        cell7.setCellValue("UNIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(36);
        cell7.setCellValue("DEPENDENCIA");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(37);
        cell7.setCellValue("OEI");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(38);
        cell7.setCellValue("NOMBRE PROYECTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(39);
        cell7.setCellValue("NOMBRE ACTIVIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(40);
        cell7.setCellValue("FINANCIAMIENTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(41);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(42);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(43);
        cell7.setCellValue("N° REFORMA");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(44);
        cell7.setCellValue("AÑO");
        cell7.setCellStyle(styles.get("title9"));

        int k = 0;
        int m = 5;
        try {
            while (k < re.size()) {
                if (re.get(k).getPresupuesto_proyecto().equals("000")) {
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
                    //Obra
                    cell7 = row6.createCell(8);
                    cell7.setCellValue(re.get(k).getPresupuesto_obra());
                    cell7.setCellStyle(styles.get("title5"));
                    //Geográfico
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
                    //Renglo auxiliar
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
                    cell7.setCellValue(re.get(k).getPresupuesto_organismo());
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
                    //Cuatrimestres
                    cell7 = row6.createCell(25);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(26);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(27);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    for (int c = 0; c < re.get(k).getCuatri().size(); c++) {
                        switch (re.get(k).getCuatri().get(c).getMes_id()) {
                            case 1:
                                if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                    cell7 = row6.createCell(25);
                                    cell7.setCellValue("S");
                                    cell7.setCellStyle(styles.get("title5"));
                                } else {
                                    cell7 = row6.createCell(25);
                                    cell7.setCellValue("");
                                    cell7.setCellStyle(styles.get("title5"));
                                }
                                break;
                            case 2:
                                if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                    cell7 = row6.createCell(26);
                                    cell7.setCellValue("S");
                                    cell7.setCellStyle(styles.get("title5"));
                                } else {
                                    cell7 = row6.createCell(26);
                                    cell7.setCellValue("");
                                    cell7.setCellStyle(styles.get("title5"));
                                }
                                break;
                            case 3:
                                if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                    cell7 = row6.createCell(27);
                                    cell7.setCellValue("S");
                                    cell7.setCellStyle(styles.get("title5"));
                                } else {
                                    cell7 = row6.createCell(27);
                                    cell7.setCellValue("");
                                    cell7.setCellStyle(styles.get("title5"));
                                }
                                break;
                        }
                    }
                    //Tipo producto
                    cell7 = row6.createCell(28);
                    cell7.setCellValue(re.get(k).getPac_tipo_producto());
                    cell7.setCellStyle(styles.get("title5"));
                    //Catalogo
                    cell7 = row6.createCell(29);
                    cell7.setCellValue(re.get(k).getPac_catalogo());
                    cell7.setCellStyle(styles.get("title5"));
                    //Procedimiento Sugerido
                    cell7 = row6.createCell(30);
                    cell7.setCellValue(re.get(k).getPac_procedimiento_sug());
                    cell7.setCellStyle(styles.get("title5"));
                    //Fondos
                    cell7 = row6.createCell(31);
                    cell7.setCellValue(re.get(k).getPac_fondo_bid());
                    cell7.setCellStyle(styles.get("title5"));
                    //Numero codigo
                    cell7 = row6.createCell(32);
                    cell7.setCellValue(re.get(k).getPac_num_operacion());
                    cell7.setCellStyle(styles.get("title5"));
                    //Numero proyecto
                    cell7 = row6.createCell(33);
                    cell7.setCellValue(re.get(k).getPac_codigo_proyecto());
                    cell7.setCellStyle(styles.get("title5"));
                    //Tipo regimen
                    cell7 = row6.createCell(34);
                    cell7.setCellValue(re.get(k).getPac_tipo_regimen());
                    cell7.setCellStyle(styles.get("title5"));
                    //Unidad
                    cell7 = row6.createCell(35);
                    cell7.setCellValue(re.get(k).getAg_nombre());
                    if (re.get(k).getReq_estado() == 3) {
                        cell7.setCellStyle(styles.get("titlepre"));
                    } else {
                        cell7.setCellStyle(styles.get("title5"));
                    }
                    //Dependencia
                    cell7 = row6.createCell(36);
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
                    cell7 = row6.createCell(37);
                    cell7.setCellValue("OEI-" + re.get(k).getPerspectiva_id());
                    if (re.get(k).getReq_estado() == 3) {
                        cell7.setCellStyle(styles.get("titlepre"));
                    } else {
                        cell7.setCellStyle(styles.get("title5"));
                    }
                    //Nombre del proyecto
                    cell7 = row6.createCell(38);
                    cell7.setCellValue(re.get(k).getProyecto_nombre());
                    if (re.get(k).getReq_estado() == 3) {
                        cell7.setCellStyle(styles.get("titlepre"));
                    } else {
                        cell7.setCellStyle(styles.get("title5"));
                    }
                    //Actividad nombre
                    cell7 = row6.createCell(39);
                    cell7.setCellValue(re.get(k).getActividad_nombre());
                    if (re.get(k).getReq_estado() == 3) {
                        cell7.setCellStyle(styles.get("titlepre"));
                    } else {
                        cell7.setCellStyle(styles.get("title5"));
                    }
                    //Presupuesto
                    cell7 = row6.createCell(40);
                    cell7.setCellValue(re.get(k).getFinanciamiento_nombre());
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
                    //Reforma
                    cell7 = row6.createCell(43);
                    if (re.get(k).getReq_reforma() > 0) {
                        cell7.setCellValue("RA-" + re.get(k).getReq_reforma() + "-ESPOCH");
                    } else {
                        cell7.setCellValue(" ");
                    }
                    cell7.setCellStyle(styles.get("title9"));
                    //Año
                    cell7 = row6.createCell(44);
                    cell7.setCellValue(re.get(k).getReq_anio());
                    cell7.setCellStyle(styles.get("title5"));
                    m++;
                }
                k++;
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

    private void reporteExcelVer21(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
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
        } else if (anio.equals("2022")) {
            re = aReq.ListarRequerimientosExcel22();
            deudas = aReq.ListarRequerimientosExcelDeudas22(Integer.parseInt(anio));
        } else {
            re = aReq.ListarRequerimientosExcel23();
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
        Cell cell11 = row4.createCell(43);
        Cell cell12 = row4.createCell(46);
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
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$Q$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$R$4:$Z$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AA$4:$AH$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AI$4:$AK$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AL$4:$AQ$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AR$4:$AT$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AU$4:$BC$4"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A5:BC5"));

        for (int i = 0; i < 11; i++) {
            cell7 = row5.createCell(i);
            cell7.setCellValue(titles[i]);
            cell7.setCellStyle(styles.get("title3"));
        }
        cell7 = row5.createCell(11);
        cell7.setCellValue("GRUPO DE GASTO");
        cell7.setCellStyle(styles.get("title3"));

        int z = 12;
        for (int i = 11; i < 16; i++) {
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
        cell7.setCellValue("FINANCIAMIENTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(43);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(44);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(45);
        cell7.setCellValue("AÑO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(46);
        cell7.setCellValue("TRÁMITE / JUSTIFICATIVO");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(47);
        cell7.setCellValue("ESTADO TRÁMITE");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(48);
        cell7.setCellValue("CÓDIGO TRAMITE");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(49);
        cell7.setCellValue("ESTADO PRESUPUESTARIO");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(50);
        cell7.setCellValue("VALOR");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(51);
        cell7.setCellValue("SALDO EJECUCIÓN");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(52);
        cell7.setCellValue("%");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(53);
        cell7.setCellValue("OBSERVACIÓN");
        cell7.setCellStyle(styles.get("titlere"));
        cell7 = row5.createCell(54);
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
                //Financiamiento
                cell7 = row6.createCell(42);
                cell7.setCellValue(re.get(k).getFinanciamiento_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                try {
                    if (re.get(k).getUnidad_nombre().isEmpty()) {
                        cell7 = row6.createCell(43);
                        cell7.setCellValue("NO PAC");
                        if (re.get(k).getReq_estado() == 3) {
                            cell7.setCellStyle(styles.get("titlepre"));
                        } else {
                            cell7.setCellStyle(styles.get("title9"));
                        }
                    } else {
                        cell7 = row6.createCell(43);
                        cell7.setCellValue("PAC");
                        if (re.get(k).getReq_estado() == 3) {
                            cell7.setCellStyle(styles.get("titlepre"));
                        } else {
                            cell7.setCellStyle(styles.get("title9"));
                        }
                    }
                } catch (Exception e) {
                    cell7 = row6.createCell(43);
                    cell7.setCellValue("NO PAC");
                    if (re.get(k).getReq_estado() == 3) {
                        cell7.setCellStyle(styles.get("titlepre"));
                    } else {
                        cell7.setCellStyle(styles.get("title9"));
                    }
                }
                //ID requerimiento
                cell7 = row6.createCell(44);
                cell7.setCellValue(re.get(k).getReq_req_id());
                if (re.get(k).getReq_prioridad_fecha() == null) {
                    cell7.setCellStyle(styles.get("title9"));
                } else {
                    cell7.setCellStyle(styles.get("titlepr"));
                }
                //Año
                cell7 = row6.createCell(45);
                cell7.setCellValue(re.get(k).getReq_anio());
                cell7.setCellStyle(styles.get("title5"));
                //Justificativo
                cell7 = row6.createCell(46);
                if (re.get(k).getSolicitud_codigo() == null && re.get(k).getSolicitud_centro_costo() == null && re.get(k).getSolicitud_nombre() == null) {
                    cell7.setCellValue(" ");
                } else if (re.get(k).getSolicitud_codigo() == null && re.get(k).getSolicitud_centro_costo() == null) {
                    cell7.setCellValue(re.get(k).getSolicitud_nombre() + "-STP-" + anio);
                } else if (re.get(k).getSolicitud_codigo() == null && re.get(k).getSolicitud_nombre() == null) {
                    cell7.setCellValue(re.get(k).getSolicitud_centro_costo() + "-UCP-" + anio);
                } else if (re.get(k).getSolicitud_centro_costo() == null && re.get(k).getSolicitud_nombre() == null) {
                    cell7.setCellValue(re.get(k).getSolicitud_codigo() + "-UCP-" + anio);
                } else if (re.get(k).getSolicitud_centro_costo() == null && re.get(k).getSolicitud_nombre() != null && re.get(k).getSolicitud_codigo() != null) {
                    cell7.setCellValue(re.get(k).getSolicitud_codigo() + "-UCP-" + anio + ", " + re.get(k).getSolicitud_nombre() + " -STP-" + anio);
                } else if (re.get(k).getSolicitud_centro_costo() != null && re.get(k).getSolicitud_nombre() == null && re.get(k).getSolicitud_codigo() != null) {
                    cell7.setCellValue(re.get(k).getSolicitud_codigo() + ", " + re.get(k).getSolicitud_centro_costo() + " -UCP-" + anio);
                }
                cell7.setCellStyle(styles.get("title5"));
                //cÓDIGO TRAMITE
                cell7 = row6.createCell(48);
                if (re.get(k).getCp_valor() == 0) {
                    cell7.setCellValue("----");
                } else {
                    cell7.setCellValue(re.get(k).getAe_observacion());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Estado Presupuestarop
                cell7 = row6.createCell(49);
                if (re.get(k).getCp_valor() == 0) {
                    cell7.setCellValue("---");
                } else {
                    cell7.setCellValue(re.get(k).getCp_tipo());
                }
                cell7.setCellStyle(styles.get("title5"));

                if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() > 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() == 0) {
                    //Estado
                    cell7 = row6.createCell(47);
                    if (re.get(k).getSolicitud_cargo() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolicitud_cargo()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Documento Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            case "Enviado por Devolución Compras":
                                cell7.setCellValue("Devuelto a la unidad");
                                break;
                            case "Anulado":
                                cell7.setCellValue("Anulado");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado Unificado IVA
                    cell7 = row6.createCell(50);
                    BigDecimal b = new BigDecimal(re.get(k).getVerificado_uni_iva());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal c = new BigDecimal(to - re.get(k).getVerificado_uni_iva());
                    c = c.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(c.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observacion
                    cell7 = row6.createCell(53);
                    cell7.setCellValue(aReq.observacionJustificativoUnifEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() > 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() > 0) {
                    //Estado
                    cell7 = row6.createCell(47);
                    if (re.get(k).getSolicitud_cargo() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolicitud_cargo()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Documento Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            case "Enviado por Devolución Compras":
                                cell7.setCellValue("Devuelto a la unidad");
                                break;
                            case "Anulado":
                                cell7.setCellValue("Anulado");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado Unificado IVA
                    cell7 = row6.createCell(50);
                    BigDecimal b = new BigDecimal(re.get(k).getCp_valor());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal c = new BigDecimal(to - re.get(k).getCp_valor());
                    c = c.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(c.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observacion
                    cell7 = row6.createCell(53);
                    cell7.setCellValue(aReq.observacionJustificativoUnifEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() > 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() > 0 && re.get(k).getCp_valor() == 0) {
                    //Estado
                    cell7 = row6.createCell(47);
                    if (re.get(k).getSolestado_observacion() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolestado_observacion()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Documento Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            case "Enviado por Devolución Compras":
                                cell7.setCellValue("Devuelto a la unidad");
                                break;
                            case "Anulado":
                                cell7.setCellValue("Anulado");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(50);
                    BigDecimal a = new BigDecimal(re.get(k).getVerificado_iva() + re.get(k).getVerificado_uni_npac());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal b = new BigDecimal((to) - (re.get(k).getVerificado_iva() + re.get(k).getVerificado_uni_npac()));
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue(aReq.observacionJustificativoEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() > 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() > 0 && re.get(k).getCp_valor() > 0) {
                    //Estado
                    cell7 = row6.createCell(47);
                    if (re.get(k).getSolestado_observacion() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolestado_observacion()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Documento Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            case "Enviado por Devolución Compras":
                                cell7.setCellValue("Devuelto a la unidad");
                                break;
                            case "Anulado":
                                cell7.setCellValue("Anulado");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(50);
                    BigDecimal a = new BigDecimal(re.get(k).getCp_valor());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal b = new BigDecimal((to) - (re.get(k).getCp_valor()));
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue(aReq.observacionJustificativoEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() > 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() == 0) {
                    //Estado
                    cell7 = row6.createCell(47);
                    if (re.get(k).getSolestado_observacion() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolestado_observacion()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Documento Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            case "Enviado por Devolución Compras":
                                cell7.setCellValue("Devuelto a la unidad");
                                break;
                            case "Anulado":
                                cell7.setCellValue("Anulado");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(50);
                    BigDecimal a = new BigDecimal(re.get(k).getVerificado_iva());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal b = new BigDecimal(to - re.get(k).getVerificado_iva());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue(aReq.observacionJustificativoEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() > 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() > 0) {
                    //Estado
                    cell7 = row6.createCell(47);
                    if (re.get(k).getSolestado_observacion() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolestado_observacion()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Documento Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            case "Enviado por Devolución Compras":
                                cell7.setCellValue("Devuelto a la unidad");
                                break;
                            case "Anulado":
                                cell7.setCellValue("Anulado");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(50);
                    BigDecimal a = new BigDecimal(re.get(k).getCp_valor());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal b = new BigDecimal(to - re.get(k).getCp_valor());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue(aReq.observacionJustificativoEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() > 0 && re.get(k).getActividad_porcentaje() > 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() > 0) {
                    //Estado
                    cell7 = row6.createCell(47);
                    if (re.get(k).getSolestado_observacion() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolestado_observacion()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Documento Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            case "Enviado por Devolución Compras":
                                cell7.setCellValue("Devuelto a la unidad");
                                break;
                            case "Anulado":
                                cell7.setCellValue("Anulado");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(50);
                    BigDecimal a = new BigDecimal(re.get(k).getCp_valor());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal b = new BigDecimal(to - re.get(k).getCp_valor());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue(aReq.observacionJustificativoEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() > 0 && re.get(k).getActividad_porcentaje() > 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() == 0) {
                    //Estado
                    cell7 = row6.createCell(47);
                    if (re.get(k).getSolestado_observacion() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolestado_observacion()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Documento Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            case "Enviado por Devolución Compras":
                                cell7.setCellValue("Devuelto a la unidad");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(50);
                    BigDecimal a = new BigDecimal(re.get(k).getCp_valor());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal b = new BigDecimal(to - re.get(k).getCp_valor());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue(aReq.observacionJustificativoEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() == 0 && re.get(k).getSolicitud_codigo() != null) {
                    //Estado
                    cell7 = row6.createCell(47);
                    if (re.get(k).getSolestado_observacion() == null) {
                        cell7.setCellValue("");
                    } else {
                        switch (re.get(k).getSolestado_observacion()) {
                            case "Entregado Financiero":
                                cell7.setCellValue("Dirección Financiera");
                                break;
                            case "Aprobado Compras":
                                cell7.setCellValue("Documento Aprobado");
                                break;
                            case "Recibido":
                                cell7.setCellValue("Compras Públicas");
                                break;
                            case "Recibido Planificación":
                                cell7.setCellValue("Dirección de Planificación");
                                break;
                            case "Enviado por Devolución Compras":
                                cell7.setCellValue("Devuelto a la unidad");
                                break;
                            default:
                                cell7.setCellValue("");
                                break;
                        }
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(50);
                    BigDecimal a = new BigDecimal(re.get(k).getCp_valor());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal b = new BigDecimal(to - re.get(k).getCp_valor());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue(aReq.observacionJustificativoEstado(re.get(k).getReq_id()));
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() > 0 && re.get(k).getCp_valor() == 0) {
                    //Estado
                    cell7 = row6.createCell(47);
                    cell7.setCellValue("Dirección Financiera");
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(50);
                    BigDecimal a = new BigDecimal(re.get(k).getVerificado_uni_npac());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal b = new BigDecimal(to - re.get(k).getVerificado_uni_npac());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue("Solicitud NO PAC");
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() > 0 && re.get(k).getCp_valor() > 0) {
                    //Estado
                    cell7 = row6.createCell(47);
                    cell7.setCellValue("Dirección Financiera");
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(50);
                    BigDecimal a = new BigDecimal(re.get(k).getCp_valor());
                    a = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(a.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal b = new BigDecimal(to - re.get(k).getCp_valor());
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue("Solicitud NO PAC");
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() == 0 && re.get(k).getSolicitud_nombre() != null) {
                    //Estado
                    cell7 = row6.createCell(47);
                    cell7.setCellValue("Documento Aprobado");
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(50);
                    cell7.setCellValue(0);
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal b = new BigDecimal(to);
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue("Solicitud NO PAC");
                    cell7.setCellStyle(styles.get("title5"));
                } else if (re.get(k).getActividad_monto() == 0 && re.get(k).getActividad_porcentaje() == 0 && re.get(k).getC1() == 0 && re.get(k).getCp_valor() == 0 && re.get(k).getSolicitud_nombre() == null) {
                    //Estado
                    cell7 = row6.createCell(47);
                    cell7.setCellValue("Sin Acción");
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Verificado IVA
                    cell7 = row6.createCell(50);
                    cell7.setCellValue(0);
                    cell7.setCellStyle(styles.get("title5"));
                    //Saldo ejecución
                    cell7 = row6.createCell(51);
                    BigDecimal b = new BigDecimal(to);
                    b = b.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    cell7.setCellValue(b.doubleValue());
                    cell7.setCellStyle(styles.get("title5"));
                    //Estado Observación
                    cell7 = row6.createCell(53);
                    cell7.setCellValue("Sin Acción");
                    cell7.setCellStyle(styles.get("title5"));
                }
                //%
                cell7 = row6.createCell(52);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Salvaguardados
                cell7 = row6.createCell(54);
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
                //Financiamiento
                cell7 = row6.createCell(42);
                cell7.setCellValue(deudas.get(n).getFinanciamiento_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                cell7 = row6.createCell(43);
                cell7.setCellValue(deudas.get(n).getUsuario_titulo());
                cell7.setCellStyle(styles.get("title9"));
                //ID requerimiento
                cell7 = row6.createCell(44);
                cell7.setCellValue(deudas.get(n).getReq_id());
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(45);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Justificativo
                cell7 = row6.createCell(46);
                if (deudas.get(n).getSolicitud_codigo() == null) {
                    cell7.setCellValue(" ");
                } else {
                    cell7.setCellValue(deudas.get(n).getSolicitud_codigo() + "-STP-" + anio);
                }
                cell7.setCellStyle(styles.get("title5"));
                //Estado tramite
                cell7 = row6.createCell(47);
                if (((deudas.get(n).getDeu_iva() == 1 && deudas.get(n).getVerificado_iva() > 0) || (deudas.get(n).getDeu_iva() == 2 && deudas.get(n).getVerificado_uni_iva() > 0) || (deudas.get(n).getDeu_iva() == 3 && deudas.get(n).getVerificado_uni_npac() > 0)) && deudas.get(n).getSolicitud_codigo() != null) {
                    cell7.setCellValue("Dirección Financiera");
                } else if (deudas.get(n).getSolicitud_codigo() != null) {
                    cell7.setCellValue("Documento Aprobado");
                } else {
                    cell7.setCellValue("Sin Acción");
                }
                cell7.setCellStyle(styles.get("title5"));
                //Código tramite
                cell7 = row6.createCell(48);
                if (null == deudas.get(n).getSolicitud_nombre()) {
                    cell7.setCellValue("----");
                } else {
                    cell7.setCellValue(deudas.get(n).getSolicitud_nombre());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Estado Presupuestario
                cell7 = row6.createCell(49);
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
                cell7 = row6.createCell(50);
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
                cell7 = row6.createCell(51);
                cell7.setCellValue(to - mo);
                cell7.setCellStyle(styles.get("title5"));
                //%
                cell7 = row6.createCell(52);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Observacion
                cell7 = row6.createCell(53);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Salvaguardado
                cell7 = row6.createCell(54);
                cell7.setCellValue("SALVAGUARDADO");
                cell7.setCellStyle(styles.get("title5"));
                m++;
                n++;
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

    private void reporteExcelUnidades(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        String alias = request.getParameter("agtitulopoaEx");
        String ag = request.getParameter("agidpoaEx");
        String tag = request.getParameter("tipoAgEx");
        String anio = request.getParameter("anioExcel");
        response.setHeader("Content-Disposition", "attachment; filename=requerimientosEspoch-" + alias + ".xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> re = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> deudas = new ArrayList<cActividadRequerimiento>();
        Integer agp;
        if (!tag.equals("4")) {
            agp = Integer.parseInt(ag);
        } else {
            agp = 0;
        }
        re = aReq.ListarRequerimientosExcelUnidad(Integer.parseInt(ag), agp, Integer.parseInt(anio));
        deudas = aReq.ListarRequerimientosExcelDeudasUnidades(Integer.parseInt(ag));
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
        Cell cell7;
        cell.setCellValue("PLAN ANUAL DE COMPRAS");
        cell2.setCellValue("Por favor no modifique la estructura del archivo para subir al sistema USHAY - Módulo Facilitador de Contratación Pública");
        cell3.setCellValue("RUC_ENTIDAD");
        cell4.setCellValue("0660001250001");
        cell5.setCellValue("INFORMACION DE LA PARTIDA PRESUPUESTARIA");
        cell6.setCellValue("INFORMACION DETALLADA DE LOS PRODUCTOS");
        cell.setCellStyle(styles.get("title"));
        cell2.setCellStyle(styles.get("title2"));
        cell3.setCellStyle(styles.get("title2"));
        cell4.setCellStyle(styles.get("title2"));
        cell5.setCellStyle(styles.get("title3"));
        cell6.setCellStyle(styles.get("title4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AI$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AH$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$P$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$Q$4:$AI$4"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A5:AS5"));

        for (int i = 0; i < 16; i++) {
            cell7 = row5.createCell(i);
            cell7.setCellValue(titles[i]);
            cell7.setCellStyle(styles.get("title3"));
        }
        int p = 0;
        for (int j = 16; j < 23; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        for (int j = 23; j < 25; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title8"));
            p++;
        }

        for (int j = 25; j < 35; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        cell7 = row5.createCell(35);
        cell7.setCellValue("UNIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(36);
        cell7.setCellValue("DEPENDENCIA");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(37);
        cell7.setCellValue("OEI");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(38);
        cell7.setCellValue("NOMBRE PROYECTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(39);
        cell7.setCellValue("NOMBRE ACTIVIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(40);
        cell7.setCellValue("FINANCIAMIENTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(41);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(42);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(43);
        cell7.setCellValue("N° REFORMA");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(44);
        cell7.setCellValue("AÑO");
        cell7.setCellStyle(styles.get("title9"));

        int k = 0;
        int n = 0;
        int m = 5;
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
                //Presupuesto
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
                //Cuatrimestres
                cell7 = row6.createCell(25);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                for (int c = 0; c < re.get(k).getCuatri().size(); c++) {
                    switch (re.get(k).getCuatri().get(c).getMes_id()) {
                        case 1:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(25);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(25);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 2:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(26);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(26);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 3:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(27);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(27);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                    }
                }
                //Tipo producto
                cell7 = row6.createCell(28);
                cell7.setCellValue(re.get(k).getPac_tipo_producto());
                cell7.setCellStyle(styles.get("title5"));
                //Catalogo
                cell7 = row6.createCell(29);
                cell7.setCellValue(re.get(k).getPac_catalogo());
                cell7.setCellStyle(styles.get("title5"));
                //Procedimiento Sugerido
                cell7 = row6.createCell(30);
                cell7.setCellValue(re.get(k).getPac_procedimiento_sug());
                cell7.setCellStyle(styles.get("title5"));
                //Fondos
                cell7 = row6.createCell(31);
                cell7.setCellValue(re.get(k).getPac_fondo_bid());
                cell7.setCellStyle(styles.get("title5"));
                //Numero codigo
                cell7 = row6.createCell(32);
                cell7.setCellValue(re.get(k).getPac_num_operacion());
                cell7.setCellStyle(styles.get("title5"));
                //Numero proyecto
                cell7 = row6.createCell(33);
                cell7.setCellValue(re.get(k).getPac_codigo_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo regimen
                cell7 = row6.createCell(34);
                cell7.setCellValue(re.get(k).getPac_tipo_regimen());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(35);
                cell7.setCellValue(re.get(k).getAg_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Dependencia
                cell7 = row6.createCell(36);
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
                cell7 = row6.createCell(37);
                cell7.setCellValue("OEI-" + re.get(k).getPerspectiva_id());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Nombre del proyecto
                cell7 = row6.createCell(38);
                cell7.setCellValue(re.get(k).getProyecto_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Actividad nombre
                cell7 = row6.createCell(39);
                cell7.setCellValue(re.get(k).getActividad_nombre());
                if (re.get(k).getReq_estado() == 3) {
                    cell7.setCellStyle(styles.get("titlepre"));
                } else {
                    cell7.setCellStyle(styles.get("title5"));
                }
                //Presupuesto
                cell7 = row6.createCell(40);
                cell7.setCellValue(re.get(k).getFinanciamiento_nombre());
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
                //Reforma
                cell7 = row6.createCell(43);
                if (re.get(k).getReq_reforma() > 0) {
                    cell7.setCellValue("RA-" + re.get(k).getReq_reforma() + "-ESPOCH");
                } else {
                    cell7.setCellValue(" ");
                }
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(44);
                cell7.setCellValue(re.get(k).getReq_anio());
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
                //Cuatrimestres
                cell7 = row6.createCell(25);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(29);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(30);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(31);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(32);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(33);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(34);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(35);
                cell7.setCellValue(deudas.get(n).getAg_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Dependencia
                cell7 = row6.createCell(36);
                if (deudas.get(n).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                    cell7.setCellValue(deudas.get(n).getAg_nombre());
                } else {
                    cell7.setCellValue(deudas.get(n).getAg_alias());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Objetivo
                cell7 = row6.createCell(37);
                cell7.setCellValue("OEI-" + deudas.get(n).getPerspectiva_id());
                cell7.setCellStyle(styles.get("title5"));
                //Nombre del proyecto
                cell7 = row6.createCell(38);
                cell7.setCellValue(deudas.get(n).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad nombre
                cell7 = row6.createCell(39);
                cell7.setCellValue(deudas.get(n).getActividad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(40);
                cell7.setCellValue(deudas.get(n).getFinanciamiento_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                cell7 = row6.createCell(41);
                cell7.setCellValue("OBLIGACIONES PENDIENTES");
                cell7.setCellStyle(styles.get("title9"));
                //ID requerimiento
                cell7 = row6.createCell(42);
                cell7.setCellValue(deudas.get(n).getReq_id());
                cell7.setCellStyle(styles.get("title9"));

                cell7 = row6.createCell(43);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(44);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                m++;
                n++;
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

    private void reporteExcelUnidades21(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        String alias = request.getParameter("agtitulopoaEx");
        String ag = request.getParameter("agidpoaEx");
        String tag = request.getParameter("tipoAgEx");
        String anio = request.getParameter("anioExcel");
        response.setHeader("Content-Disposition", "attachment; filename=requerimientosEspoch-" + alias + ".xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> re = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> re2 = new ArrayList<cActividadRequerimiento>();
        Integer agp;
        if (!tag.equals("4")) {
            agp = Integer.parseInt(ag);
        } else {
            agp = 0;
        }
        re = aReq.ListarRequerimientosExcelUnidad(Integer.parseInt(ag), agp, Integer.parseInt(anio));
        re2 = aReq.ListarRequerimientosExcelDeudas21(Integer.parseInt(ag), Integer.parseInt(anio));
        Row row = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        Row row3 = sheet.createRow(2);
        Row row4 = sheet.createRow(3);
        Row row5 = sheet.createRow(5);
        Row row6 = sheet.createRow(6);
        Row row8 = sheet.createRow(4);
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
        Cell cell8 = row8.createCell(4);
        Cell cell9 = row4.createCell(36);
        Cell cell10 = row4.createCell(42);
        Cell cell7;
        cell.setCellValue("PLAN ANUAL DE COMPRAS");
        cell2.setCellValue("Por favor no modifique la estructura del archivo para subir al sistema USHAY - Módulo Facilitador de Contratación Pública");
        cell3.setCellValue("RUC_ENTIDAD");
        cell4.setCellValue("0660001250001");
        cell5.setCellValue("ESTRUCTURA PRESUPUESTARIA");
        cell6.setCellValue("INFORMACION DETALLADA DE LOS PRODUCTOS");
        cell5.setCellValue("ESTRUCTURA PRESUPUESTARIA");
        cell9.setCellValue("DATOS DEL PROYECTO");
        cell10.setCellValue("DATOS ADICIONALES");
        cell.setCellStyle(styles.get("title"));
        cell2.setCellStyle(styles.get("title2"));
        cell3.setCellStyle(styles.get("title2"));
        cell4.setCellStyle(styles.get("title2"));
        cell5.setCellStyle(styles.get("title3"));
        cell6.setCellStyle(styles.get("title4"));
        cell8.setCellStyle(styles.get("titleret"));
        cell9.setCellStyle(styles.get("title8"));
        cell10.setCellStyle(styles.get("title9"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AT$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AT$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$Q$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$R$4:$AJ$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AK$4:$AP$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$AQ$4:$AT$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$E$5:$K$5"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A6:AT6"));

        for (int i = 0; i < 11; i++) {
            cell7 = row5.createCell(i);
            cell7.setCellValue(titles[i]);
            cell7.setCellStyle(styles.get("title3"));
        }

        cell7 = row5.createCell(11);
        cell7.setCellValue("GRUPO DE GASTO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(12);
        cell7.setCellValue("RENGLON AUXILIAR");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(13);
        cell7.setCellValue("FUENTE");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(14);
        cell7.setCellValue("ORGANISMO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(15);
        cell7.setCellValue("CORRELATIVO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row5.createCell(16);
        cell7.setCellValue("TIPO DE PRESUPUESTO");
        cell7.setCellStyle(styles.get("title3"));

        int p = 0;
        for (int j = 17; j < 24; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        for (int j = 24; j < 26; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title8"));
            p++;
        }

        for (int j = 26; j < 36; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

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
        cell7.setCellValue("FINANCIAMIENTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(42);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(43);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(44);
        cell7.setCellValue("N° REFORMA");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(45);
        cell7.setCellValue("AÑO");
        cell7.setCellStyle(styles.get("title9"));

        int k = 0;
        int n = 0;
        int m = 6;
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
                //Presupuesto
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
                //Cuatrimestres
                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                for (int c = 0; c < re.get(k).getCuatri().size(); c++) {
                    switch (re.get(k).getCuatri().get(c).getMes_id()) {
                        case 1:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(26);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(26);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 2:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(27);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(27);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                        case 3:
                            if (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0.0) {
                                cell7 = row6.createCell(28);
                                cell7.setCellValue("S");
                                cell7.setCellStyle(styles.get("title5"));
                            } else {
                                cell7 = row6.createCell(28);
                                cell7.setCellValue("");
                                cell7.setCellStyle(styles.get("title5"));
                            }
                            break;
                    }
                }
                //Tipo producto
                cell7 = row6.createCell(29);
                cell7.setCellValue(re.get(k).getPac_tipo_producto());
                cell7.setCellStyle(styles.get("title5"));
                //Catalogo
                cell7 = row6.createCell(30);
                cell7.setCellValue(re.get(k).getPac_catalogo());
                cell7.setCellStyle(styles.get("title5"));
                //Procedimiento Sugerido
                cell7 = row6.createCell(31);
                cell7.setCellValue(re.get(k).getPac_procedimiento_sug());
                cell7.setCellStyle(styles.get("title5"));
                //Fondos
                cell7 = row6.createCell(32);
                cell7.setCellValue(re.get(k).getPac_fondo_bid());
                cell7.setCellStyle(styles.get("title5"));
                //Numero codigo
                cell7 = row6.createCell(33);
                cell7.setCellValue(re.get(k).getPac_num_operacion());
                cell7.setCellStyle(styles.get("title5"));
                //Numero proyecto
                cell7 = row6.createCell(34);
                cell7.setCellValue(re.get(k).getPac_codigo_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo regimen
                cell7 = row6.createCell(35);
                cell7.setCellValue(re.get(k).getPac_tipo_regimen());
                cell7.setCellStyle(styles.get("title5"));
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
                //Presupuesto
                cell7 = row6.createCell(41);
                cell7.setCellValue(re.get(k).getFinanciamiento_nombre());
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
                //Reforma
                cell7 = row6.createCell(44);
                if (re.get(k).getReq_reforma() > 0) {
                    cell7.setCellValue("RA-" + re.get(k).getReq_reforma() + "-ESPOCH");
                } else {
                    cell7.setCellValue(" ");
                }
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(45);
                cell7.setCellValue(re.get(k).getReq_anio());
                cell7.setCellStyle(styles.get("title5"));
                k++;
                m++;
            }
            //Deudas
            k = 0;
            while (k < re2.size()) {
                row6 = sheet.createRow(m);
                //Ejercicio
                cell7 = row6.createCell(0);
                cell7.setCellValue(re2.get(k).getPresupuesto_ejercicio());
                cell7.setCellStyle(styles.get("title5"));
                //Entidad
                cell7 = row6.createCell(1);
                cell7.setCellValue(re2.get(k).getPresupuesto_entidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad ejecutora
                cell7 = row6.createCell(2);
                cell7.setCellValue(re2.get(k).getPresupuesto_unidad_ejec());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad desconcentrada
                cell7 = row6.createCell(3);
                cell7.setCellValue(re2.get(k).getPresupuesto_unidad_desc());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(4);
                cell7.setCellValue(re2.get(k).getPresupuesto_programa());
                cell7.setCellStyle(styles.get("title5"));
                //Subprograma
                cell7 = row6.createCell(5);
                cell7.setCellValue(re2.get(k).getPresupuesto_subprograma());
                cell7.setCellStyle(styles.get("title5"));
                //Proyecto
                cell7 = row6.createCell(6);
                cell7.setCellValue(re2.get(k).getPresupuesto_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(7);
                cell7.setCellValue(re2.get(k).getPresupuesto_actividad());
                cell7.setCellStyle(styles.get("title5"));
                //Obras
                cell7 = row6.createCell(8);
                cell7.setCellValue(re2.get(k).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Geografico
                cell7 = row6.createCell(9);
                cell7.setCellValue(re2.get(k).getPresupuesto_geografico());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo
                cell7 = row6.createCell(10);
                cell7.setCellValue(re2.get(k).getPresupuesto_renglo());
                cell7.setCellStyle(styles.get("title5"));
                //Grupo de gasto
                String sCadena = String.valueOf(re2.get(k).getPresupuesto_renglo() + "00");
                String sSubCadena = sCadena.substring(0, 2);
                cell7 = row6.createCell(11);
                cell7.setCellValue(sSubCadena + "0000");
                cell7.setCellStyle(styles.get("title5"));
                //Renglo auxiliar
                cell7 = row6.createCell(12);
                cell7.setCellValue(re2.get(k).getPresupuesto_renglo_aux());
                cell7.setCellStyle(styles.get("title5"));
                //Fuente
                cell7 = row6.createCell(13);
                cell7.setCellValue(re2.get(k).getPresupuesto_fuente());
                cell7.setCellStyle(styles.get("title5"));
                //Organismo
                cell7 = row6.createCell(14);
                cell7.setCellValue(re2.get(k).getPresupuesto_organismo());
                cell7.setCellStyle(styles.get("title5"));
                //Correlativo
                cell7 = row6.createCell(15);
                cell7.setCellValue(re2.get(k).getPresupuesto_correlativo());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(16);
                cell7.setCellValue(re2.get(k).getPresupuesto_presupuesto());
                cell7.setCellStyle(styles.get("title5"));
                //CPC
                cell7 = row6.createCell(17);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Tipo Compra
                cell7 = row6.createCell(18);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Detalle
                cell7 = row6.createCell(19);
                cell7.setCellValue(re2.get(k).getReq_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento
                cell7 = row6.createCell(20);
                cell7.setCellValue(re2.get(k).getReq_descripcion());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(21);
                cell7.setCellValue(re2.get(k).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(22);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(23);
                cell7.setCellValue(re2.get(k).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(24);
                cell7.setCellValue(re2.get(k).getReq_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva
                cell7 = row6.createCell(25);
                cell7.setCellValue(re2.get(k).getReq_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                //Tipo producto
                cell7 = row6.createCell(29);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Catalogo
                cell7 = row6.createCell(30);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Procedimiento Sugerido
                cell7 = row6.createCell(31);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Fondos
                cell7 = row6.createCell(32);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Numero codigo
                cell7 = row6.createCell(33);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Numero proyecto
                cell7 = row6.createCell(34);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Tipo regimen
                cell7 = row6.createCell(35);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(36);
                cell7.setCellValue(re2.get(k).getAg_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Dependencia
                cell7 = row6.createCell(37);
                if (re2.get(k).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                    cell7.setCellValue(re2.get(k).getAg_nombre());
                } else {
                    cell7.setCellValue(re2.get(k).getAg_alias());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Objetivo
                cell7 = row6.createCell(38);
                cell7.setCellValue("OEI-" + re2.get(k).getPerspectiva_id());
                cell7.setCellStyle(styles.get("title5"));
                //Nombre del proyecto
                cell7 = row6.createCell(39);
                cell7.setCellValue(re2.get(k).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad nombre
                cell7 = row6.createCell(40);
                cell7.setCellValue(re2.get(k).getActividad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(41);
                cell7.setCellValue(re2.get(k).getFinanciamiento_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                cell7 = row6.createCell(42);
                cell7.setCellValue(re2.get(k).getActividad_responsable());
                cell7.setCellStyle(styles.get("title9"));
                //ID requerimiento
                cell7 = row6.createCell(43);
                cell7.setCellValue(re2.get(k).getReq_id());
                cell7.setCellStyle(styles.get("title9"));
                //Reforma
                cell7 = row6.createCell(44);
                if (re2.get(k).getReq_reforma() > 0) {
                    cell7.setCellValue("RA-" + re2.get(k).getReq_reforma() + "-ESPOCH");
                } else {
                    cell7.setCellValue(" ");
                }
                cell7.setCellStyle(styles.get("title9"));
                //Año
                cell7 = row6.createCell(45);
                cell7.setCellValue(" ");
                cell7.setCellStyle(styles.get("title5"));
                k++;
                m++;
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

    private void reporteExcelDeudas(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=requerimientosEspochDeudas.xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> deudas = new ArrayList<cActividadRequerimiento>();
        deudas = aReq.ListarRequerimientosExcelDeudas(2020);
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
        Cell cell7;
        cell.setCellValue("PLAN ANUAL DE COMPRAS");
        cell2.setCellValue("Por favor no modifique la estructura del archivo para subir al sistema USHAY - Módulo Facilitador de Contratación Pública");
        cell3.setCellValue("RUC_ENTIDAD");
        cell4.setCellValue("0660001250001");
        cell5.setCellValue("INFORMACION DE LA PARTIDA PRESUPUESTARIA");
        cell6.setCellValue("INFORMACION DETALLADA DE LOS PRODUCTOS");
        cell.setCellStyle(styles.get("title"));
        cell2.setCellStyle(styles.get("title2"));
        cell3.setCellStyle(styles.get("title2"));
        cell4.setCellStyle(styles.get("title2"));
        cell5.setCellStyle(styles.get("title3"));
        cell6.setCellStyle(styles.get("title4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AI$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AH$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$P$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$Q$4:$AI$4"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A5:AS5"));

        for (int i = 0; i < 16; i++) {
            cell7 = row5.createCell(i);
            cell7.setCellValue(titles[i]);
            cell7.setCellStyle(styles.get("title3"));
        }
        int p = 0;
        for (int j = 16; j < 23; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        for (int j = 23; j < 25; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title8"));
            p++;
        }

        for (int j = 25; j < 35; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        cell7 = row5.createCell(35);
        cell7.setCellValue("UNIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(36);
        cell7.setCellValue("DEPENDENCIA");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(37);
        cell7.setCellValue("OEI");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(38);
        cell7.setCellValue("NOMBRE PROYECTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(39);
        cell7.setCellValue("NOMBRE ACTIVIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(40);
        cell7.setCellValue("FINANCIAMIENTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(41);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(42);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(43);
        cell7.setCellValue("N° REFORMA");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(44);
        cell7.setCellValue("AÑO");
        cell7.setCellStyle(styles.get("title9"));

        int k = 0;
        int m = 5;
        try {
            //Deudas
            while (k < deudas.size()) {
                row6 = sheet.createRow(m);
                //Ejercicio
                cell7 = row6.createCell(0);
                cell7.setCellValue(deudas.get(k).getPresupuesto_ejercicio());
                cell7.setCellStyle(styles.get("title5"));
                //Entidad
                cell7 = row6.createCell(1);
                cell7.setCellValue(deudas.get(k).getPresupuesto_entidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad ejecutora
                cell7 = row6.createCell(2);
                cell7.setCellValue(deudas.get(k).getPresupuesto_unidad_ejec());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad desconcentrada
                cell7 = row6.createCell(3);
                cell7.setCellValue(deudas.get(k).getPresupuesto_unidad_desc());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(4);
                cell7.setCellValue(deudas.get(k).getPresupuesto_programa());
                cell7.setCellStyle(styles.get("title5"));
                //Subprograma
                cell7 = row6.createCell(5);
                cell7.setCellValue(deudas.get(k).getPresupuesto_subprograma());
                cell7.setCellStyle(styles.get("title5"));
                //Proyecto
                cell7 = row6.createCell(6);
                cell7.setCellValue(deudas.get(k).getPresupuesto_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(7);
                cell7.setCellValue(deudas.get(k).getPresupuesto_actividad());
                cell7.setCellStyle(styles.get("title5"));
                //Obras
                cell7 = row6.createCell(8);
                cell7.setCellValue(deudas.get(k).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Geografico
                cell7 = row6.createCell(9);
                cell7.setCellValue(deudas.get(k).getPresupuesto_geografico());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo
                cell7 = row6.createCell(10);
                cell7.setCellValue(deudas.get(k).getPresupuesto_renglo());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo Auxiliar
                cell7 = row6.createCell(11);
                cell7.setCellValue(deudas.get(k).getPresupuesto_renglo_aux());
                cell7.setCellStyle(styles.get("title5"));
                //Fuente
                cell7 = row6.createCell(12);
                cell7.setCellValue(deudas.get(k).getPresupuesto_fuente());
                cell7.setCellStyle(styles.get("title5"));
                //Organismo
                cell7 = row6.createCell(13);
                cell7.setCellValue(deudas.get(k).getPresupuesto_organismo());
                cell7.setCellStyle(styles.get("title5"));
                //Correlativo
                cell7 = row6.createCell(14);
                cell7.setCellValue(deudas.get(k).getPresupuesto_correlativo());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(15);
                cell7.setCellValue(deudas.get(k).getPresupuesto_presupuesto());
                cell7.setCellStyle(styles.get("title5"));
                //CPC
                cell7 = row6.createCell(16);
                cell7.setCellValue(deudas.get(k).getReq_cpc());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo Compra
                cell7 = row6.createCell(17);
                cell7.setCellValue(deudas.get(k).getTc_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Detalle
                cell7 = row6.createCell(18);
                if (deudas.get(k).getDeu_iva() == 1) {
                    cell7.setCellValue(deudas.get(k).getReq_nombre() + "(IVA)");
                } else {
                    cell7.setCellValue(deudas.get(k).getReq_nombre());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento
                cell7 = row6.createCell(19);
                cell7.setCellValue(deudas.get(k).getReq_descripcion());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(20);
                cell7.setCellValue(deudas.get(k).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(21);
                cell7.setCellValue(deudas.get(k).getUnidad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(22);
                cell7.setCellValue(deudas.get(k).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(23);
                cell7.setCellValue(deudas.get(k).getReq_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva
                cell7 = row6.createCell(24);
                cell7.setCellValue(deudas.get(k).getReq_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                cell7 = row6.createCell(25);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(29);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(30);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(31);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(32);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(33);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(34);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(35);
                cell7.setCellValue(deudas.get(k).getAg_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Dependencia
                cell7 = row6.createCell(36);
                if (deudas.get(k).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                    cell7.setCellValue(deudas.get(k).getAg_nombre());
                } else {
                    cell7.setCellValue(deudas.get(k).getAg_alias());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Objetivo
                cell7 = row6.createCell(37);
                cell7.setCellValue("OEI-" + deudas.get(k).getPerspectiva_id());
                cell7.setCellStyle(styles.get("title5"));
                //Nombre del proyecto
                cell7 = row6.createCell(38);
                cell7.setCellValue(deudas.get(k).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad nombre
                cell7 = row6.createCell(39);
                cell7.setCellValue(deudas.get(k).getActividad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(40);
                cell7.setCellValue(deudas.get(k).getFinanciamiento_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                cell7 = row6.createCell(41);
                cell7.setCellValue("OBLIGACIONES PENDIENTES");
                cell7.setCellStyle(styles.get("title9"));
                //ID requerimiento
                cell7 = row6.createCell(42);
                cell7.setCellValue(deudas.get(k).getReq_id());
                cell7.setCellStyle(styles.get("title9"));

                cell7 = row6.createCell(43);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(44);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                m++;
                k++;
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

    private void reporteExcelComprometidos(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=requerimientosEspochComprometidos.xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> deudas = new ArrayList<cActividadRequerimiento>();
        deudas = aReq.ListarRequerimientosExcelCompr();
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
        Cell cell7;
        cell.setCellValue("PLAN ANUAL DE COMPRAS");
        cell2.setCellValue("Por favor no modifique la estructura del archivo para subir al sistema USHAY - Módulo Facilitador de Contratación Pública");
        cell3.setCellValue("RUC_ENTIDAD");
        cell4.setCellValue("0660001250001");
        cell5.setCellValue("INFORMACION DE LA PARTIDA PRESUPUESTARIA");
        cell6.setCellValue("INFORMACION DETALLADA DE LOS PRODUCTOS");
        cell.setCellStyle(styles.get("title"));
        cell2.setCellStyle(styles.get("title2"));
        cell3.setCellStyle(styles.get("title2"));
        cell4.setCellStyle(styles.get("title2"));
        cell5.setCellStyle(styles.get("title3"));
        cell6.setCellStyle(styles.get("title4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AI$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AH$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$P$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$Q$4:$AI$4"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A5:AS5"));

        for (int i = 0; i < 16; i++) {
            cell7 = row5.createCell(i);
            cell7.setCellValue(titles[i]);
            cell7.setCellStyle(styles.get("title3"));
        }
        int p = 0;
        for (int j = 16; j < 23; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        for (int j = 23; j < 25; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title8"));
            p++;
        }

        for (int j = 25; j < 35; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        cell7 = row5.createCell(35);
        cell7.setCellValue("UNIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(36);
        cell7.setCellValue("DEPENDENCIA");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(37);
        cell7.setCellValue("OEI");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(38);
        cell7.setCellValue("NOMBRE PROYECTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(39);
        cell7.setCellValue("NOMBRE ACTIVIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(40);
        cell7.setCellValue("FINANCIAMIENTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(41);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(42);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(43);
        cell7.setCellValue("N° REFORMA");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(44);
        cell7.setCellValue("AÑO");
        cell7.setCellStyle(styles.get("title9"));

        int k = 0;
        int m = 5;
        try {
            //Deudas
            while (k < deudas.size()) {
                row6 = sheet.createRow(m);
                //Ejercicio
                cell7 = row6.createCell(0);
                cell7.setCellValue(deudas.get(k).getPresupuesto_ejercicio());
                cell7.setCellStyle(styles.get("title5"));
                //Entidad
                cell7 = row6.createCell(1);
                cell7.setCellValue(deudas.get(k).getPresupuesto_entidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad ejecutora
                cell7 = row6.createCell(2);
                cell7.setCellValue(deudas.get(k).getPresupuesto_unidad_ejec());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad desconcentrada
                cell7 = row6.createCell(3);
                cell7.setCellValue(deudas.get(k).getPresupuesto_unidad_desc());
                cell7.setCellStyle(styles.get("title5"));
                //Programa
                cell7 = row6.createCell(4);
                cell7.setCellValue(deudas.get(k).getPresupuesto_programa());
                cell7.setCellStyle(styles.get("title5"));
                //Subprograma
                cell7 = row6.createCell(5);
                cell7.setCellValue(deudas.get(k).getPresupuesto_subprograma());
                cell7.setCellStyle(styles.get("title5"));
                //Proyecto
                cell7 = row6.createCell(6);
                cell7.setCellValue(deudas.get(k).getPresupuesto_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(7);
                cell7.setCellValue(deudas.get(k).getPresupuesto_actividad());
                cell7.setCellStyle(styles.get("title5"));
                //Obras
                cell7 = row6.createCell(8);
                cell7.setCellValue(deudas.get(k).getPresupuesto_obra());
                cell7.setCellStyle(styles.get("title5"));
                //Geografico
                cell7 = row6.createCell(9);
                cell7.setCellValue(deudas.get(k).getPresupuesto_geografico());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo
                cell7 = row6.createCell(10);
                cell7.setCellValue(deudas.get(k).getPresupuesto_renglo());
                cell7.setCellStyle(styles.get("title5"));
                //Renglo Auxiliar
                cell7 = row6.createCell(11);
                cell7.setCellValue(deudas.get(k).getPresupuesto_renglo_aux());
                cell7.setCellStyle(styles.get("title5"));
                //Fuente
                cell7 = row6.createCell(12);
                cell7.setCellValue(deudas.get(k).getPresupuesto_fuente());
                cell7.setCellStyle(styles.get("title5"));
                //Organismo
                cell7 = row6.createCell(13);
                cell7.setCellValue(deudas.get(k).getPresupuesto_organismo());
                cell7.setCellStyle(styles.get("title5"));
                //Correlativo
                cell7 = row6.createCell(14);
                cell7.setCellValue(deudas.get(k).getPresupuesto_correlativo());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(15);
                cell7.setCellValue(deudas.get(k).getPresupuesto_presupuesto());
                cell7.setCellStyle(styles.get("title5"));
                //CPC
                cell7 = row6.createCell(16);
                cell7.setCellValue(deudas.get(k).getReq_cpc());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo Compra
                cell7 = row6.createCell(17);
                cell7.setCellValue(deudas.get(k).getTc_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Detalle
                cell7 = row6.createCell(18);
                cell7.setCellValue(deudas.get(k).getReq_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento
                cell7 = row6.createCell(19);
                cell7.setCellValue(deudas.get(k).getReq_descripcion());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(20);
                cell7.setCellValue(deudas.get(k).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(21);
                cell7.setCellValue(deudas.get(k).getUnidad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                double pendiente = (deudas.get(k).getReq_costo_unitario() + deudas.get(k).getReq_costo_sin_iva()) - deudas.get(k).getReq_costo_total();
                cell7 = row6.createCell(22);
                cell7.setCellValue(deudas.get(k).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(23);
                cell7.setCellValue(deudas.get(k).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo total iva
                cell7 = row6.createCell(24);
                cell7.setCellValue(deudas.get(k).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                cell7 = row6.createCell(25);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(26);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(27);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(28);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(29);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(30);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(31);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(32);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(33);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(34);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(35);
                cell7.setCellValue(deudas.get(k).getAg_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Dependencia
                cell7 = row6.createCell(36);
                if (deudas.get(k).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                    cell7.setCellValue(deudas.get(k).getAg_nombre());
                } else {
                    cell7.setCellValue(deudas.get(k).getAg_alias());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Objetivo
                cell7 = row6.createCell(37);
                cell7.setCellValue("OEI-" + deudas.get(k).getPerspectiva_id());
                cell7.setCellStyle(styles.get("title5"));
                //Nombre del proyecto
                cell7 = row6.createCell(38);
                cell7.setCellValue(deudas.get(k).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad nombre
                cell7 = row6.createCell(39);
                cell7.setCellValue("COMPROMETIDOS NO DEVENGADOS");
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(40);
                cell7.setCellValue(deudas.get(k).getFinanciamiento_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                cell7 = row6.createCell(41);
                cell7.setCellValue("NO PAC");
                cell7.setCellStyle(styles.get("title9"));
                //ID requerimiento
                cell7 = row6.createCell(42);
                cell7.setCellValue(deudas.get(k).getReq_id());
                cell7.setCellStyle(styles.get("title9"));

                cell7 = row6.createCell(43);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));

                cell7 = row6.createCell(44);
                cell7.setCellValue("");
                cell7.setCellStyle(styles.get("title5"));
                m++;

                if (deudas.get(k).getReq_costo_sin_iva() > 0) {
                    row6 = sheet.createRow(m);
                    //Ejercicio
                    cell7 = row6.createCell(0);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_ejercicio());
                    cell7.setCellStyle(styles.get("title5"));
                    //Entidad
                    cell7 = row6.createCell(1);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_entidad());
                    cell7.setCellStyle(styles.get("title5"));
                    //Unidad ejecutora
                    cell7 = row6.createCell(2);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_unidad_ejec());
                    cell7.setCellStyle(styles.get("title5"));
                    //Unidad desconcentrada
                    cell7 = row6.createCell(3);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_unidad_desc());
                    cell7.setCellStyle(styles.get("title5"));
                    //Programa
                    cell7 = row6.createCell(4);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_programa());
                    cell7.setCellStyle(styles.get("title5"));
                    //Subprograma
                    cell7 = row6.createCell(5);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_subprograma());
                    cell7.setCellStyle(styles.get("title5"));
                    //Proyecto
                    cell7 = row6.createCell(6);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_proyecto());
                    cell7.setCellStyle(styles.get("title5"));
                    //Actividad
                    cell7 = row6.createCell(7);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_actividad());
                    cell7.setCellStyle(styles.get("title5"));
                    //Obras
                    cell7 = row6.createCell(8);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_obra());
                    cell7.setCellStyle(styles.get("title5"));
                    //Geografico
                    cell7 = row6.createCell(9);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_geografico());
                    cell7.setCellStyle(styles.get("title5"));
                    //Renglo
                    cell7 = row6.createCell(10);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_renglo());
                    cell7.setCellStyle(styles.get("title5"));
                    //Renglo Auxiliar
                    cell7 = row6.createCell(11);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_renglo_aux());
                    cell7.setCellStyle(styles.get("title5"));
                    //Fuente
                    cell7 = row6.createCell(12);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_fuente());
                    cell7.setCellStyle(styles.get("title5"));
                    //Organismo
                    cell7 = row6.createCell(13);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_organismo());
                    cell7.setCellStyle(styles.get("title5"));
                    //Correlativo
                    cell7 = row6.createCell(14);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_correlativo());
                    cell7.setCellStyle(styles.get("title5"));
                    //Presupuesto
                    cell7 = row6.createCell(15);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_presupuesto());
                    cell7.setCellStyle(styles.get("title5"));
                    //CPC
                    cell7 = row6.createCell(16);
                    cell7.setCellValue(deudas.get(k).getReq_cpc());
                    cell7.setCellStyle(styles.get("title5"));
                    //Tipo Compra
                    cell7 = row6.createCell(17);
                    cell7.setCellValue(deudas.get(k).getTc_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Detalle
                    cell7 = row6.createCell(18);
                    cell7.setCellValue(deudas.get(k).getReq_nombre() + "(IVA)");
                    cell7.setCellStyle(styles.get("title5"));
                    //Descripcion Requerimiento
                    cell7 = row6.createCell(19);
                    cell7.setCellValue(deudas.get(k).getReq_descripcion() + "(IVA)");
                    cell7.setCellStyle(styles.get("title5"));
                    //Cantidad
                    cell7 = row6.createCell(20);
                    cell7.setCellValue(deudas.get(k).getReq_cantidad());
                    cell7.setCellStyle(styles.get("title5"));
                    //Unidad
                    cell7 = row6.createCell(21);
                    cell7.setCellValue(deudas.get(k).getUnidad_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Unitario
                    cell7 = row6.createCell(22);
                    cell7.setCellValue(deudas.get(k).getReq_costo_sin_iva());
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Total sin iva
                    cell7 = row6.createCell(23);
                    cell7.setCellValue(deudas.get(k).getReq_costo_sin_iva());
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo total iva
                    cell7 = row6.createCell(24);
                    cell7.setCellValue(deudas.get(k).getReq_costo_sin_iva());
                    cell7.setCellStyle(styles.get("title5"));
                    //Cuatrimestres
                    cell7 = row6.createCell(25);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(26);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(27);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(28);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(29);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(30);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(31);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(32);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(33);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(34);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Unidad
                    cell7 = row6.createCell(35);
                    cell7.setCellValue(deudas.get(k).getAg_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Dependencia
                    cell7 = row6.createCell(36);
                    if (deudas.get(k).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                        cell7.setCellValue(deudas.get(k).getAg_nombre());
                    } else {
                        cell7.setCellValue(deudas.get(k).getAg_alias());
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Objetivo
                    cell7 = row6.createCell(37);
                    cell7.setCellValue("OEI-" + deudas.get(k).getPerspectiva_id());
                    cell7.setCellStyle(styles.get("title5"));
                    //Nombre del proyecto
                    cell7 = row6.createCell(38);
                    cell7.setCellValue(deudas.get(k).getProyecto_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Actividad nombre
                    cell7 = row6.createCell(39);
                    cell7.setCellValue("COMPROMETIDOS NO DEVENGADOS");
                    cell7.setCellStyle(styles.get("title5"));
                    //Presupuesto
                    cell7 = row6.createCell(40);
                    cell7.setCellValue(deudas.get(k).getFinanciamiento_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Tipo requerimiento
                    cell7 = row6.createCell(41);
                    cell7.setCellValue("NO PAC");
                    cell7.setCellStyle(styles.get("title9"));
                    //ID requerimiento
                    cell7 = row6.createCell(42);
                    cell7.setCellValue(deudas.get(k).getReq_id());
                    cell7.setCellStyle(styles.get("title9"));

                    cell7 = row6.createCell(43);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(44);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    m++;
                }
                if (deudas.get(k).getReq_costo_total() > 0) {
                    row6 = sheet.createRow(m);
                    //Ejercicio
                    cell7 = row6.createCell(0);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_ejercicio());
                    cell7.setCellStyle(styles.get("title5"));
                    //Entidad
                    cell7 = row6.createCell(1);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_entidad());
                    cell7.setCellStyle(styles.get("title5"));
                    //Unidad ejecutora
                    cell7 = row6.createCell(2);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_unidad_ejec());
                    cell7.setCellStyle(styles.get("title5"));
                    //Unidad desconcentrada
                    cell7 = row6.createCell(3);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_unidad_desc());
                    cell7.setCellStyle(styles.get("title5"));
                    //Programa
                    cell7 = row6.createCell(4);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_programa());
                    cell7.setCellStyle(styles.get("title5"));
                    //Subprograma
                    cell7 = row6.createCell(5);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_subprograma());
                    cell7.setCellStyle(styles.get("title5"));
                    //Proyecto
                    cell7 = row6.createCell(6);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_proyecto());
                    cell7.setCellStyle(styles.get("title5"));
                    //Actividad
                    cell7 = row6.createCell(7);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_actividad());
                    cell7.setCellStyle(styles.get("title5"));
                    //Obras
                    cell7 = row6.createCell(8);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_obra());
                    cell7.setCellStyle(styles.get("title5"));
                    //Geografico
                    cell7 = row6.createCell(9);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_geografico());
                    cell7.setCellStyle(styles.get("title5"));
                    //Renglo
                    cell7 = row6.createCell(10);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_renglo());
                    cell7.setCellStyle(styles.get("title5"));
                    //Renglo Auxiliar
                    cell7 = row6.createCell(11);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_renglo_aux());
                    cell7.setCellStyle(styles.get("title5"));
                    //Fuente
                    cell7 = row6.createCell(12);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_fuente());
                    cell7.setCellStyle(styles.get("title5"));
                    //Organismo
                    cell7 = row6.createCell(13);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_organismo());
                    cell7.setCellStyle(styles.get("title5"));
                    //Correlativo
                    cell7 = row6.createCell(14);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_correlativo());
                    cell7.setCellStyle(styles.get("title5"));
                    //Presupuesto
                    cell7 = row6.createCell(15);
                    cell7.setCellValue(deudas.get(k).getPresupuesto_presupuesto());
                    cell7.setCellStyle(styles.get("title5"));
                    //CPC
                    cell7 = row6.createCell(16);
                    cell7.setCellValue(deudas.get(k).getReq_cpc());
                    cell7.setCellStyle(styles.get("title5"));
                    //Tipo Compra
                    cell7 = row6.createCell(17);
                    cell7.setCellValue(deudas.get(k).getTc_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Detalle
                    cell7 = row6.createCell(18);
                    cell7.setCellValue(deudas.get(k).getReq_nombre() + "(ANTICIPO)");
                    cell7.setCellStyle(styles.get("title5"));
                    //Descripcion Requerimiento
                    cell7 = row6.createCell(19);
                    cell7.setCellValue(deudas.get(k).getReq_descripcion() + "(ANTICIPO)");
                    cell7.setCellStyle(styles.get("title5"));
                    //Cantidad
                    cell7 = row6.createCell(20);
                    cell7.setCellValue(deudas.get(k).getReq_cantidad());
                    cell7.setCellStyle(styles.get("title5"));
                    //Unidad
                    cell7 = row6.createCell(21);
                    cell7.setCellValue(deudas.get(k).getUnidad_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Unitario
                    cell7 = row6.createCell(22);
                    cell7.setCellValue(deudas.get(k).getReq_costo_total());
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo Total sin iva
                    cell7 = row6.createCell(23);
                    cell7.setCellValue(deudas.get(k).getReq_costo_total());
                    cell7.setCellStyle(styles.get("title5"));
                    //Costo total iva
                    cell7 = row6.createCell(24);
                    cell7.setCellValue(deudas.get(k).getReq_costo_total());
                    cell7.setCellStyle(styles.get("title5"));
                    //Cuatrimestres
                    cell7 = row6.createCell(25);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(26);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(27);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(28);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(29);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(30);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(31);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(32);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(33);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(34);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    //Unidad
                    cell7 = row6.createCell(35);
                    cell7.setCellValue(deudas.get(k).getAg_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Dependencia
                    cell7 = row6.createCell(36);
                    if (deudas.get(k).getAg_alias().equals("ESCUELA SUPERIOR POLITECNICA DE CHIMBORAZO")) {
                        cell7.setCellValue(deudas.get(k).getAg_nombre());
                    } else {
                        cell7.setCellValue(deudas.get(k).getAg_alias());
                    }
                    cell7.setCellStyle(styles.get("title5"));
                    //Objetivo
                    cell7 = row6.createCell(37);
                    cell7.setCellValue("OEI-" + deudas.get(k).getPerspectiva_id());
                    cell7.setCellStyle(styles.get("title5"));
                    //Nombre del proyecto
                    cell7 = row6.createCell(38);
                    cell7.setCellValue(deudas.get(k).getProyecto_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Actividad nombre
                    cell7 = row6.createCell(39);
                    cell7.setCellValue("COMPROMETIDOS NO DEVENGADOS");
                    cell7.setCellStyle(styles.get("title5"));
                    //Presupuesto
                    cell7 = row6.createCell(40);
                    cell7.setCellValue(deudas.get(k).getFinanciamiento_nombre());
                    cell7.setCellStyle(styles.get("title5"));
                    //Tipo requerimiento
                    cell7 = row6.createCell(41);
                    cell7.setCellValue("NO PAC");
                    cell7.setCellStyle(styles.get("title9"));
                    //ID requerimiento
                    cell7 = row6.createCell(42);
                    cell7.setCellValue(deudas.get(k).getReq_id());
                    cell7.setCellStyle(styles.get("title9"));

                    cell7 = row6.createCell(43);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));

                    cell7 = row6.createCell(44);
                    cell7.setCellValue("");
                    cell7.setCellStyle(styles.get("title5"));
                    m++;
                }
                k++;
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

    private void reporteJustificativoExcel(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=justificativorequerimientos.xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> re = new ArrayList<cActividadRequerimiento>();
        re = aReq.ListarRequerimientosExcelPacSolicitud(Integer.parseInt(request.getParameter("idjustificativo")));
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
        Cell cell7;
        cell.setCellValue("PLAN ANUAL DE COMPRAS");
        cell2.setCellValue("Por favor no modifique la estructura del archivo para subir al sistema USHAY - Módulo Facilitador de Contratación Pública");
        cell3.setCellValue("RUC_ENTIDAD");
        cell4.setCellValue("0660001250001");
        cell5.setCellValue("INFORMACION DE LA PARTIDA PRESUPUESTARIA");
        cell6.setCellValue("INFORMACION DETALLADA DE LOS PRODUCTOS");
        cell.setCellStyle(styles.get("title"));
        cell2.setCellStyle(styles.get("title2"));
        cell3.setCellStyle(styles.get("title2"));
        cell4.setCellStyle(styles.get("title2"));
        cell5.setCellStyle(styles.get("title3"));
        cell6.setCellStyle(styles.get("title4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$AI$1"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$AH$2"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$4:$P$4"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$Q$4:$AI$4"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A5:AW5"));

        for (int i = 0; i < 16; i++) {
            cell7 = row5.createCell(i);
            cell7.setCellValue(titlesj[i]);
            cell7.setCellStyle(styles.get("title3"));
        }
        int p = 0;
        for (int j = 16; j < 26; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2j[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        for (int j = 26; j < 30; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2j[p]);
            cell7.setCellStyle(styles.get("title8"));
            p++;
        }

        for (int j = 30; j < 41; j++) {
            cell7 = row5.createCell(j);
            cell7.setCellValue(titles2j[p]);
            cell7.setCellStyle(styles.get("title4"));
            p++;
        }

        cell7 = row5.createCell(41);
        cell7.setCellValue("UNIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(42);
        cell7.setCellValue("DEPENDENCIA");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(43);
        cell7.setCellValue("OEI");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(44);
        cell7.setCellValue("NOMBRE PROYECTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(45);
        cell7.setCellValue("NOMBRE ACTIVIDAD");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(46);
        cell7.setCellValue("FINANCIAMIENTO");
        cell7.setCellStyle(styles.get("title8"));
        cell7 = row5.createCell(47);
        cell7.setCellValue("TIPO DE REQUERIMIENTO (PAC/NO PAC)");
        cell7.setCellStyle(styles.get("title9"));
        cell7 = row5.createCell(48);
        cell7.setCellValue("NO MODIFICAR COD. REQUERIMIENTO");
        cell7.setCellStyle(styles.get("title9"));

        int k = 0;
        int m = 5;
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
                //Presupuesto
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
                cell7.setCellValue(re.get(k).getReqestado_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(21);
                cell7.setCellValue(re.get(k).getUnidad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(22);
                cell7.setCellValue(re.get(k).getReqestado_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(23);
                cell7.setCellValue(re.get(k).getReqestado_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total con iva
                cell7 = row6.createCell(24);
                if (re.get(k).getReqestado_iva() == 1) {
                    BigDecimal big = new BigDecimal(re.get(k).getReqestado_costo_total() * 1.12);
                    big = big.setScale(2, RoundingMode.HALF_UP);
                    cell7.setCellValue(big.doubleValue());
                } else {
                    cell7.setCellValue(re.get(k).getReqestado_costo_total());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Descripcion Requerimiento Unificado
                cell7 = row6.createCell(25);
                cell7.setCellValue(re.get(k).getReqeje_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Cantidad
                cell7 = row6.createCell(26);
                cell7.setCellValue(re.get(k).getReqeje_cantidad_anual());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Unitario
                cell7 = row6.createCell(27);
                cell7.setCellValue(re.get(k).getReqeje_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Costo Total sin iva
                cell7 = row6.createCell(28);
                cell7.setCellValue(re.get(k).getReqeje_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //Justificativo
                cell7 = row6.createCell(29);
                cell7.setCellValue(re.get(k).getSolicitud_codigo() + "-UCP-2020");
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestres
                for (int c = 0; c < re.get(k).getCuatri().size(); c++) {
                    if ((re.get(k).getCuatri().get(c).getMes_id() == 1) && (re.get(k).getCuatri().get(c).getActividad_porcentaje() < 1)) {
                        cell7 = row6.createCell(30);
                        cell7.setCellValue("");
                        cell7.setCellStyle(styles.get("title5"));
                    } else if ((re.get(k).getCuatri().get(c).getMes_id() == 1) && (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0)) {
                        cell7 = row6.createCell(30);
                        cell7.setCellValue("S");
                        cell7.setCellStyle(styles.get("title5"));
                    } else {
                        cell7 = row6.createCell(30);
                        cell7.setCellValue("");
                        cell7.setCellStyle(styles.get("title5"));
                    }

                    if ((re.get(k).getCuatri().get(c).getMes_id() == 2) && (re.get(k).getCuatri().get(c).getActividad_porcentaje() < 1)) {
                        cell7 = row6.createCell(31);
                        cell7.setCellValue("");
                        cell7.setCellStyle(styles.get("title5"));
                    } else if ((re.get(k).getCuatri().get(c).getMes_id() == 2) && (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0)) {
                        cell7 = row6.createCell(31);
                        cell7.setCellValue("S");
                        cell7.setCellStyle(styles.get("title5"));
                    } else {
                        cell7 = row6.createCell(31);
                        cell7.setCellValue("");
                        cell7.setCellStyle(styles.get("title5"));
                    }

                    if ((re.get(k).getCuatri().get(c).getMes_id() == 3) && (re.get(k).getCuatri().get(c).getActividad_porcentaje() < 1)) {
                        cell7 = row6.createCell(32);
                        cell7.setCellValue("");
                        cell7.setCellStyle(styles.get("title5"));
                    } else if ((re.get(k).getCuatri().get(c).getMes_id() == 3) && (re.get(k).getCuatri().get(c).getActividad_porcentaje() > 0)) {
                        cell7 = row6.createCell(32);
                        cell7.setCellValue("S");
                        cell7.setCellStyle(styles.get("title5"));
                    } else {
                        cell7 = row6.createCell(32);
                        cell7.setCellValue("");
                        cell7.setCellStyle(styles.get("title5"));
                    }
                }
                //Tipo producto
                cell7 = row6.createCell(33);
                cell7.setCellValue(re.get(k).getPac_tipo_producto());
                cell7.setCellStyle(styles.get("title5"));
                //Catalogo
                cell7 = row6.createCell(34);
                cell7.setCellValue(re.get(k).getPac_catalogo());
                cell7.setCellStyle(styles.get("title5"));
                //Procedimiento Sugerido
                cell7 = row6.createCell(35);
                cell7.setCellValue(re.get(k).getPac_procedimiento_sug());
                cell7.setCellStyle(styles.get("title5"));
                //Fondos
                cell7 = row6.createCell(36);
                cell7.setCellValue(re.get(k).getPac_fondo_bid());
                cell7.setCellStyle(styles.get("title5"));
                //Numero codigo
                cell7 = row6.createCell(37);
                cell7.setCellValue(re.get(k).getPac_num_operacion());
                cell7.setCellStyle(styles.get("title5"));
                //Numero proyecto
                cell7 = row6.createCell(38);
                cell7.setCellValue(re.get(k).getPac_codigo_proyecto());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo regimen
                cell7 = row6.createCell(39);
                cell7.setCellValue(re.get(k).getPac_tipo_regimen());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(40);
                cell7.setCellValue(re.get(k).getPresupuesto_presupuesto());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(41);
                cell7.setCellValue(re.get(k).getAg_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Dependencia
                cell7 = row6.createCell(42);
                if (re.get(k).getAg_alias().equals("Escuela Superior Politécnica de Chimborazo")) {
                    cell7.setCellValue(re.get(k).getAg_nombre());
                } else {
                    cell7.setCellValue(re.get(k).getAg_alias());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Objetivo
                cell7 = row6.createCell(43);
                cell7.setCellValue("OEI-" + re.get(k).getPerspectiva_id());
                cell7.setCellStyle(styles.get("title5"));
                //Nombre del proyecto
                cell7 = row6.createCell(44);
                cell7.setCellValue(re.get(k).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad nombre
                cell7 = row6.createCell(45);
                cell7.setCellValue(re.get(k).getActividad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Presupuesto
                cell7 = row6.createCell(46);
                cell7.setCellValue(re.get(k).getFinanciamiento_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Tipo requerimiento
                try {
                    if (re.get(k).getUnidad_nombre().isEmpty()) {
                        cell7 = row6.createCell(47);
                        cell7.setCellValue("NO PAC");
                        if (re.get(k).getReq_prioridad_fecha() == null) {
                            cell7.setCellStyle(styles.get("title9"));
                        } else {
                            cell7.setCellStyle(styles.get("titlepr"));
                        }
                    } else {
                        cell7 = row6.createCell(47);
                        cell7.setCellValue("PAC");
                        if (re.get(k).getReq_prioridad_fecha() == null) {
                            cell7.setCellStyle(styles.get("title9"));
                        } else {
                            cell7.setCellStyle(styles.get("titlepr"));
                        }
                    }
                } catch (Exception e) {
                    cell7 = row6.createCell(47);
                    cell7.setCellValue("NO PAC");
                    if (re.get(k).getReq_prioridad_fecha() == null) {
                        cell7.setCellStyle(styles.get("title9"));
                    } else {
                        cell7.setCellStyle(styles.get("titlepr"));
                    }
                }
                //ID requerimiento
                cell7 = row6.createCell(48);
                cell7.setCellValue(re.get(k).getReq_id());
                if (re.get(k).getReq_prioridad_fecha() == null) {
                    cell7.setCellStyle(styles.get("title9"));
                } else {
                    cell7.setCellStyle(styles.get("titlepr"));
                }
                k++;
                m++;
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

    private void reporteEvaluacion(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        String cuat = request.getParameter("cuatrimestre");
        String anio = request.getParameter("selectanio1");
        response.setHeader("Content-Disposition", "attachment; filename=evaluacion_" + cuat + "_" + anio + ".xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cProyecto> re = new ArrayList<cProyecto>();
        re = aReq.ListarEvaluacionExcel(Integer.parseInt(cuat), Integer.parseInt(anio));
        Row row = sheet.createRow(0);
        Row row3 = sheet.createRow(1);
        Row row4 = sheet.createRow(3);
        Row row6 = sheet.createRow(5);
        row.setHeightInPoints(23);
        row4.setHeightInPoints(16);
        Cell cell = row.createCell(0);
        Cell cell7;
        cell.setCellValue("EVALUACIÓN " + cuat + " CUATRIMESTRE");
        cell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$L$1"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A2:L2"));

        cell7 = row3.createCell(0);
        cell7.setCellValue("DEPENDENCIA");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(1);
        cell7.setCellValue("UNIDAD");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(2);
        cell7.setCellValue("OEI");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(3);
        cell7.setCellValue("PROYECTOS PLANIFICADOS");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(4);
        cell7.setCellValue("PROYECTOS CUATRIMESTRE");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(5);
        cell7.setCellValue("PROYECTOS ENVIADOS");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(6);
        cell7.setCellValue("PROYECTOS EVALUADOS");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(7);
        cell7.setCellValue("EFICACIA");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(8);
        cell7.setCellValue("EFICIENCIA");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(9);
        cell7.setCellValue("EFECTIVIDAD");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(10);
        cell7.setCellValue("PLANIFICADO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(11);
        cell7.setCellValue("EJECUCION");
        cell7.setCellStyle(styles.get("title3"));

        int k = 0;
        int m = 2;
        try {
            while (k < re.size()) {
                row6 = sheet.createRow(m);
                //Dependencia
                cell7 = row6.createCell(0);
                cell7.setCellValue(re.get(k).getProyecto_acciones());
                cell7.setCellStyle(styles.get("title5"));
                //Unidad
                cell7 = row6.createCell(1);
                cell7.setCellValue(re.get(k).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //OEI
                cell7 = row6.createCell(2);
                cell7.setCellValue(re.get(k).getDeudas_proceso());
                cell7.setCellStyle(styles.get("title5"));
                //Planificados
                cell7 = row6.createCell(3);
                cell7.setCellValue(re.get(k).getProyecto_planificados());
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestre
                cell7 = row6.createCell(4);
                cell7.setCellValue(re.get(k).getProy_cuatrimestre());
                cell7.setCellStyle(styles.get("title5"));
                //Enviado
                cell7 = row6.createCell(5);
                cell7.setCellValue(re.get(k).getProy_enviados());
                cell7.setCellStyle(styles.get("title5"));
                //Evaluados
                cell7 = row6.createCell(6);
                cell7.setCellValue(re.get(k).getProy_evaluados());
                cell7.setCellStyle(styles.get("title5"));
                //Eficacia
                cell7 = row6.createCell(7);
                cell7.setCellValue(re.get(k).getPe_eficacia());
                cell7.setCellStyle(styles.get("title5"));
                //Eficiencia
                cell7 = row6.createCell(8);
                cell7.setCellValue(re.get(k).getPe_eficiencia());
                cell7.setCellStyle(styles.get("title5"));
                //Efectividad
                cell7 = row6.createCell(9);
                cell7.setCellValue(re.get(k).getPe_efectividad());
                cell7.setCellStyle(styles.get("title5"));
                //Planificado
                cell7 = row6.createCell(10);
                cell7.setCellValue(re.get(k).getProyecto_monto());
                cell7.setCellStyle(styles.get("title5"));
                //Ejecución
                cell7 = row6.createCell(11);
                cell7.setCellValue(re.get(k).getPe_ejecucion());
                cell7.setCellStyle(styles.get("title5"));
                k++;
                m++;
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

    private void reporteEvaluacion2(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=reporteevaluacion.xls");
        Workbook wb = new HSSFWorkbook(); //or new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");
        Map<String, CellStyle> styles = createStyles(wb);
        adActividadRequerimiento aReq = new adActividadRequerimiento();
        List<cActividadRequerimiento> re = new ArrayList<cActividadRequerimiento>();
        String cuat = request.getParameter("cuatrimestre2");
        String anio = request.getParameter("selectanio2");
        re = aReq.ListarEvaluacionExcel2(Integer.parseInt(anio), Integer.parseInt(cuat));
        Row row = sheet.createRow(0);
        Row row3 = sheet.createRow(1);
        Row row2 = sheet.createRow(2);
        Row row4 = sheet.createRow(3);
        Row row6 = sheet.createRow(5);
        row.setHeightInPoints(23);
        row4.setHeightInPoints(16);
        Cell cell = row.createCell(0);
        Cell cell7;
        if (cuat.equals("0")) {
            cell.setCellValue("EVALUACIÓN FINAL");
        } else {
            cell.setCellValue("EVALUACIÓN " + cuat + " CUATRIMESTRE");
        }
        cell.setCellStyle(styles.get("title"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$Y$1"));
        sheet.setAutoFilter(CellRangeAddress.valueOf("A3:Y3"));

        cell7 = row3.createCell(0);
        cell7.setCellValue("OEI");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$A$3"));

        cell7 = row3.createCell(1);
        cell7.setCellValue("OBJETIVO OPERATIVO");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$B$2:$B$3"));

        cell7 = row3.createCell(2);
        cell7.setCellValue("DEPENDENCIA");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$C$2:$C$3"));

        cell7 = row3.createCell(3);
        cell7.setCellValue("UNIDAD");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$D$2:$D$3"));

        cell7 = row3.createCell(4);
        cell7.setCellValue("PROYECTO");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$E$2:$E$3"));

        cell7 = row3.createCell(5);
        cell7.setCellValue("EFICACIA");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$F$2:$F$3"));

        cell7 = row3.createCell(6);
        cell7.setCellValue("EFICIENCIA");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$G$2:$G$3"));

        cell7 = row3.createCell(7);
        cell7.setCellValue("EFECTIVIDAD");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$H$2:$H$3"));

        cell7 = row3.createCell(8);
        cell7.setCellValue("COMPONENTE");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$I$2:$I$3"));

        cell7 = row3.createCell(9);
        cell7.setCellValue("LOGROS ALCANZADOS");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$J$2:$J$3"));

        cell7 = row3.createCell(10);
        cell7.setCellValue("NUDOS CRITICOS");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$K$2:$K$3"));

        cell7 = row3.createCell(11);
        cell7.setCellValue("META");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$L$2:$R$2"));

        cell7 = row2.createCell(11);
        cell7.setCellValue("NOMBRE META");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(12);
        cell7.setCellValue("EFICACIA");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(13);
        cell7.setCellValue("EFICIENCIA");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(14);
        cell7.setCellValue("EFECTIVIDAD");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(15);
        cell7.setCellValue("PLANIFICADO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(16);
        cell7.setCellValue("EJECUCION");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(17);
        cell7.setCellValue("INDICADOR");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row3.createCell(18);
        cell7.setCellValue("ACTIVIDAD");
        cell7.setCellStyle(styles.get("title3"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$S$2:$Y$2"));

        cell7 = row2.createCell(18);
        cell7.setCellValue("ACTIVIDAD");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(19);
        cell7.setCellValue("EFICACIA");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(20);
        cell7.setCellValue("EFICIENCIA");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(21);
        cell7.setCellValue("EFECTIVIDAD");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(22);
        cell7.setCellValue("MONTO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(23);
        cell7.setCellValue("MONTO EJECUTADO");
        cell7.setCellStyle(styles.get("title3"));

        cell7 = row2.createCell(24);
        cell7.setCellValue("OBSERVACIÓN");
        cell7.setCellStyle(styles.get("title3"));

        int k = 0;
        int m = 3;
        try {
            while (k < re.size()) {
                row6 = sheet.createRow(m);
                //Unidad
                cell7 = row6.createCell(0);
                cell7.setCellValue(re.get(k).getTc_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Planificados
                cell7 = row6.createCell(1);
                cell7.setCellValue(re.get(k).getObjetivo_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Cuatrimestre
                cell7 = row6.createCell(2);
                cell7.setCellValue(re.get(k).getAg_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Enviado
                cell7 = row6.createCell(3);
                cell7.setCellValue(re.get(k).getAg_alias());
                cell7.setCellStyle(styles.get("title5"));
                //Evaluados
                cell7 = row6.createCell(4);
                cell7.setCellValue(re.get(k).getProyecto_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Eficacia
                cell7 = row6.createCell(5);
                cell7.setCellValue(re.get(k).getPe_eficacia());
                cell7.setCellStyle(styles.get("title5"));
                //Eficiencia
                cell7 = row6.createCell(6);
                cell7.setCellValue(re.get(k).getPe_eficiencia());
                cell7.setCellStyle(styles.get("title5"));
                //Efectividad
                cell7 = row6.createCell(7);
                cell7.setCellValue(re.get(k).getPe_efectividad());
                cell7.setCellStyle(styles.get("title5"));
                //Ejecución
                cell7 = row6.createCell(8);
                cell7.setCellValue(re.get(k).getFinanciamiento_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Logros
                cell7 = row6.createCell(9);
                cell7.setCellValue(re.get(k).getLogro_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Nudos
                cell7 = row6.createCell(10);
                cell7.setCellValue(re.get(k).getNudo_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Meta
                cell7 = row6.createCell(11);
                cell7.setCellValue(re.get(k).getAre_archivo());
                cell7.setCellStyle(styles.get("title5"));
                //Eficacia
                cell7 = row6.createCell(12);
                cell7.setCellValue(re.get(k).getReq_cantidad());
                cell7.setCellStyle(styles.get("title5"));
                //Eficiencia
                cell7 = row6.createCell(13);
                cell7.setCellValue(re.get(k).getReq_costo_unitario());
                cell7.setCellStyle(styles.get("title5"));
                //Efectividad
                cell7 = row6.createCell(14);
                cell7.setCellValue(re.get(k).getReq_costo_sin_iva());
                cell7.setCellStyle(styles.get("title5"));
                //pLANIFICADO META
                cell7 = row6.createCell(15);
                cell7.setCellValue(re.get(k).getSueldo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Ejecucion
                cell7 = row6.createCell(16);
                cell7.setCellValue(re.get(k).getReq_costo_total());
                cell7.setCellStyle(styles.get("title5"));
                //Indicador
                cell7 = row6.createCell(17);
                cell7.setCellValue(re.get(k).getUnidad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Actividad
                cell7 = row6.createCell(18);
                cell7.setCellValue(re.get(k).getActividad_nombre());
                cell7.setCellStyle(styles.get("title5"));
                //Eficacia
                cell7 = row6.createCell(19);
                cell7.setCellValue(re.get(k).getAe_autoeval());
                cell7.setCellStyle(styles.get("title5"));
                //Eficiencia
                cell7 = row6.createCell(20);
                if (re.get(k).getAe_eval() < 0) {
                    cell7.setCellValue("---");
                } else {
                    cell7.setCellValue(re.get(k).getAe_eval());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Efectividad
                cell7 = row6.createCell(21);
                if (re.get(k).getAe_evaluacion() < 0) {
                    cell7.setCellValue("---");
                } else {
                    cell7.setCellValue(re.get(k).getAe_evaluacion());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Monto
                cell7 = row6.createCell(22);
                cell7.setCellValue(re.get(k).getActividad_monto());
                cell7.setCellStyle(styles.get("title5"));
                //Monto ejecucion
                cell7 = row6.createCell(23);
                if (re.get(k).getAe_ejecucion() < 0) {
                    cell7.setCellValue(0);
                } else {
                    cell7.setCellValue(re.get(k).getAe_ejecucion());
                }
                cell7.setCellStyle(styles.get("title5"));
                //Observacion
                cell7 = row6.createCell(24);
                cell7.setCellValue(re.get(k).getAe_observacion());
                cell7.setCellStyle(styles.get("title5"));
                k++;
                m++;
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

    public void subirExcelPresupuesto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvalidFormatException {
        Part filePart = request.getPart("excel");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String da = formatter.format(date);
        String cedula = request.getParameter("cedulaexcel");
        String img = da + "-" + fileName;
        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "formulacion" + File.separator + "presupuesto";
        File uploads = new File(path); //Carpeta donde se guardan los archivos
        uploads.mkdirs(); //Crea los directorios necesarios
        File file = new File(uploads, img);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        //File fil = new File("C:/var/www/html/sip/formulacion/presupuesto/" + img);
        File fil = new File("/var/www/html/sip/formulacion/presupuesto/" + img);
        //FileInputStream isr = new FileInputStream(fil);

        //org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(isr);
        Workbook wb = WorkbookFactory.create(fil);
        //XSSFWorkbook wb = new XSSFWorkbook(isr);
        //XSSFSheet sheet = wb.getSheetAt(0);
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        int numFilas = sheet.getLastRowNum();
        cActividadRequerimiento cRequ = new cActividadRequerimiento();
        adActividadRequerimiento aRequ = new adActividadRequerimiento();
        int sum = 0;
        String result = "";
        for (int a = 6; a <= numFilas; a++) {
            Row filar = sheet.getRow(a);
            Integer id;
            String c1, c2, c3;
            //Ejercicio
            switch (filar.getCell(0).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_ejercicio((int) (filar.getCell(0).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_ejercicio(Integer.parseInt(filar.getCell(0).getStringCellValue()));
                    break;
            }
            //Entidad
            switch (filar.getCell(1).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_entidad((int) (filar.getCell(1).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_entidad(Integer.parseInt(filar.getCell(1).getStringCellValue()));
                    break;
            }
            //Unidad ejecutora
            switch (filar.getCell(2).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_ejec((int) (filar.getCell(2).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_ejec(Integer.getInteger(filar.getCell(2).getStringCellValue()));
                    break;
            }
            //Unidad desconcentrada
            switch (filar.getCell(3).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_desc(String.valueOf((int) filar.getCell(3).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_desc(filar.getCell(3).getStringCellValue());
                    break;
            }
            //Programa
            switch (filar.getCell(4).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_programa(String.valueOf((int) filar.getCell(4).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_programa(filar.getCell(4).getStringCellValue());
                    break;
            }
            //Subprograma
            switch (filar.getCell(5).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_subprograma(String.valueOf((int) filar.getCell(5).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_subprograma(filar.getCell(5).getStringCellValue());
                    break;
            }
            //Proyecto
            switch (filar.getCell(6).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_proyecto(String.valueOf((int) filar.getCell(6).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_proyecto(filar.getCell(6).getStringCellValue());
                    break;
            }
            //Actividad
            switch (filar.getCell(7).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_actividad(String.valueOf((int) filar.getCell(7).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_actividad(filar.getCell(7).getStringCellValue());
                    break;
            }
            //Obra
            switch (filar.getCell(8).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_obra(String.valueOf((int) filar.getCell(8).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_obra(filar.getCell(8).getStringCellValue());
                    break;
            }
            //Geografico
            switch (filar.getCell(9).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_geografico(String.valueOf((int) filar.getCell(9).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_geografico(filar.getCell(9).getStringCellValue());
                    break;
            }
            //Renglo
            switch (filar.getCell(10).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo((int) (filar.getCell(10).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo(Integer.parseInt(filar.getCell(10).getStringCellValue()));
                    break;
            }
            //Renglo Auxiliar
            switch (filar.getCell(11).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo_aux(String.valueOf((int) filar.getCell(11).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo_aux(filar.getCell(11).getStringCellValue());
                    break;
            }
            //Fuente
            switch (filar.getCell(12).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_fuente(String.valueOf((int) filar.getCell(12).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_fuente(filar.getCell(12).getStringCellValue());
                    break;
            }
            //Organismo
            switch (filar.getCell(13).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_organismo(String.valueOf((int) filar.getCell(13).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_organismo(filar.getCell(13).getStringCellValue());
                    break;
            }
            //Correlativo
            switch (filar.getCell(14).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_correlativo(String.valueOf((int) filar.getCell(14).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_correlativo(filar.getCell(14).getStringCellValue());
                    break;
            }
            //Presupuesto
            switch (filar.getCell(15).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_presupuesto(String.valueOf(filar.getCell(15).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_presupuesto(filar.getCell(15).getStringCellValue());
                    break;
            }

            //Nombre
            switch (filar.getCell(18).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_nombre(String.valueOf(filar.getCell(18).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_nombre(filar.getCell(18).getStringCellValue());
                    break;
            }

            //Descripcion
            switch (filar.getCell(19).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_descripcion(String.valueOf(filar.getCell(19).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_descripcion(filar.getCell(19).getStringCellValue());
                    break;
            }

            //Cantidad anual
            switch (filar.getCell(20).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_cantidad(filar.getCell(20).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_cantidad(Double.parseDouble(filar.getCell(20).getStringCellValue()));
                    break;
            }

            //Costo unitario
            switch (filar.getCell(22).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_unitario(filar.getCell(22).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_costo_unitario(Double.parseDouble(filar.getCell(22).getStringCellValue()));
                    break;
            }

            //Costo sin iva
            switch (filar.getCell(23).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_sin_iva(filar.getCell(23).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_costo_sin_iva(Double.parseDouble(filar.getCell(23).getStringCellValue()));
                    break;
            }

            //Costo total
            switch (filar.getCell(24).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_total(filar.getCell(24).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_costo_total(Double.parseDouble(filar.getCell(24).getStringCellValue()));
                    break;
            }

            //Financiamiento
            switch (filar.getCell(40).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setFinanciamiento_nombre(String.valueOf(filar.getCell(40).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setFinanciamiento_nombre(filar.getCell(40).getStringCellValue());
                    break;
            }

            //ID Requerimiento
            switch (filar.getCell(42).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    id = (int) filar.getCell(42).getNumericCellValue();
                    cRequ.setReq_id((int) filar.getCell(42).getNumericCellValue());
                    break;
                case "STRING":
                    id = Integer.parseInt(filar.getCell(42).getStringCellValue());
                    cRequ.setReq_id(Integer.parseInt(filar.getCell(42).getStringCellValue()));
                    break;
                default:
                    id = 0;
                    cRequ.setReq_id(0);
            }
            Integer r = aRequ.IngresarPresupuesto(cRequ);
            if (r > 0) {
                result = result + "," + r;
                sum++;
            }
        }

        cTransaccion objTransaccion = new cTransaccion();
        objTransaccion.setTransaccion_descripcion("La matriz: \"" + img + "\" subió, " + sum + " requerimientos que son" + result);
        objTransaccion.setTransaccion_cedula(cedula);
        objTransaccion.setTransaccion_ag(1);
        objTransaccion.setTransaccion_tipo(1);
        ingresarTransaccion(objTransaccion);

        String json = new Gson().toJson(sum + " requerimientos, los mismos que son: " + result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void subirExcelPresupuesto21(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvalidFormatException {
        Part filePart = request.getPart("excel");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String da = formatter.format(date);
        String img = da + "-" + fileName;
        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "formulacion" + File.separator + "presupuesto";
        File uploads = new File(path); //Carpeta donde se guardan los archivos
        uploads.mkdirs(); //Crea los directorios necesarios
        File file = new File(uploads, img);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        //File fil = new File("http://cimogsys.espoch.edu.ec/spoa/formulacion/presupuesto/" + img);
        File fil = new File("/var/www/html/sip/formulacion/presupuesto/" + img);
        //FileInputStream isr = new FileInputStream(fil);

        //org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(isr);
        Workbook wb = WorkbookFactory.create(fil);
        //XSSFWorkbook wb = new XSSFWorkbook(isr);
        //XSSFSheet sheet = wb.getSheetAt(0);
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        int numFilas = sheet.getLastRowNum();
        cActividadRequerimiento cRequ = new cActividadRequerimiento();
        adActividadRequerimiento aRequ = new adActividadRequerimiento();
        for (int a = 6; a <= numFilas; a++) {
            String result;
            Row filar = sheet.getRow(a);
            Integer id;
            String c1, c2, c3;
            //Ejercicio
            switch (filar.getCell(0).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_ejercicio((int) (filar.getCell(0).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_ejercicio(Integer.parseInt(filar.getCell(0).getStringCellValue()));
                    break;
            }
            //Entidad
            switch (filar.getCell(1).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_entidad((int) (filar.getCell(1).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_entidad(Integer.parseInt(filar.getCell(1).getStringCellValue()));
                    break;
            }
            //Organo Gestor
            switch (filar.getCell(2).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_geografico(String.valueOf((int) (filar.getCell(2).getNumericCellValue())));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_geografico((filar.getCell(2).getStringCellValue()));
                    break;
            }
            //Centro gestor
            switch (filar.getCell(3).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo_aux(String.valueOf((int) filar.getCell(3).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo_aux(filar.getCell(3).getStringCellValue());
                    break;
            }
            //Subprograma
            switch (filar.getCell(5).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_ejec((int) filar.getCell(5).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_ejec(Integer.parseInt(filar.getCell(5).getStringCellValue()));
                    break;
            }
            //Actividad
            switch (filar.getCell(6).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_actividad(String.valueOf((int) filar.getCell(6).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_actividad(filar.getCell(6).getStringCellValue());
                    break;
            }
            //Obra/tarea
            switch (filar.getCell(7).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_obra(String.valueOf((int) filar.getCell(7).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_obra(filar.getCell(7).getStringCellValue());
                    break;
            }
            //Enlace
            switch (filar.getCell(8).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_programa(String.valueOf((int) filar.getCell(8).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_programa(filar.getCell(8).getStringCellValue());
                    break;
            }
            //Renglo
            switch (filar.getCell(9).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo((int) (filar.getCell(9).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo(Integer.parseInt(filar.getCell(9).getStringCellValue()));
                    break;
            }
            //area funcional
            switch (filar.getCell(10).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_desc(String.valueOf((int) filar.getCell(10).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_desc(filar.getCell(10).getStringCellValue());
                    break;
            }
            //Fondo
            switch (filar.getCell(11).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_fuente(String.valueOf((int) filar.getCell(11).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_fuente(filar.getCell(11).getStringCellValue());
                    break;
            }
            //Proyecto
            switch (filar.getCell(12).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_proyecto(String.valueOf((int) filar.getCell(12).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_proyecto(filar.getCell(12).getStringCellValue());
                    break;
            }
            //Presupuesto
            switch (filar.getCell(13).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_presupuesto(String.valueOf(filar.getCell(13).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_presupuesto(filar.getCell(13).getStringCellValue());
                    break;
            }

            //Nombre
            switch (filar.getCell(16).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_nombre(String.valueOf(filar.getCell(16).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_nombre(filar.getCell(16).getStringCellValue());
                    break;
            }

            //Descripcion
            switch (filar.getCell(17).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_descripcion(String.valueOf(filar.getCell(17).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_descripcion(filar.getCell(17).getStringCellValue());
                    break;
            }

            //Cantidad anual
            switch (filar.getCell(18).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_cantidad(filar.getCell(18).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_cantidad(Double.parseDouble(filar.getCell(18).getStringCellValue()));
                    break;
            }

            //Costo unitario
            switch (filar.getCell(20).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_unitario(filar.getCell(20).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_costo_unitario(Double.parseDouble(filar.getCell(20).getStringCellValue()));
                    break;
            }

            //Costo sin iva
            switch (filar.getCell(21).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_sin_iva(filar.getCell(21).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_costo_sin_iva(Double.parseDouble(filar.getCell(21).getStringCellValue()));
                    break;
            }

            //Costo total
            switch (filar.getCell(22).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_total(filar.getCell(22).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_costo_total(Double.parseDouble(filar.getCell(22).getStringCellValue()));
                    break;
            }

            //Financiamiento
            switch (filar.getCell(38).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setFinanciamiento_nombre(String.valueOf(filar.getCell(38).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setFinanciamiento_nombre(filar.getCell(38).getStringCellValue());
                    break;
            }

            //ID Requerimiento
            switch (filar.getCell(40).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    id = (int) filar.getCell(40).getNumericCellValue();
                    cRequ.setReq_id((int) filar.getCell(40).getNumericCellValue());
                    break;
                case "STRING":
                    id = Integer.parseInt(filar.getCell(40).getStringCellValue());
                    cRequ.setReq_id(Integer.parseInt(filar.getCell(40).getStringCellValue()));
                    break;
                default:
                    id = 0;
                    cRequ.setReq_id(0);
            }
            Integer r = aRequ.IngresarPresupuesto(cRequ);
        }
    }

    public void subirExcelPresupuestoDeudas(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvalidFormatException {
        Part filePart = request.getPart("excel");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String da = formatter.format(date);
        String cedula = request.getParameter("cedulaexceld");
        String img = da + "-" + fileName;
        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "formulacion" + File.separator + "presupuesto";
        File uploads = new File(path); //Carpeta donde se guardan los archivos
        uploads.mkdirs(); //Crea los directorios necesarios
        File file = new File(uploads, img);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        //File fil = new File("http://cimogsys.espoch.edu.ec/spoa/formulacion/presupuesto/" + img);
        File fil = new File("/var/www/html/sip/formulacion/presupuesto/" + img);
        //FileInputStream isr = new FileInputStream(fil);

        //org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(isr);
        Workbook wb = WorkbookFactory.create(fil);
        //XSSFWorkbook wb = new XSSFWorkbook(isr);
        //XSSFSheet sheet = wb.getSheetAt(0);
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        int numFilas = sheet.getLastRowNum();
        cActividadRequerimiento cRequ = new cActividadRequerimiento();
        adActividadRequerimiento aRequ = new adActividadRequerimiento();
        adProyecto aP = new adProyecto();
        cProyecto cPro = new cProyecto();
        int sum = 0;
        String result;
        for (int a = 6; a <= numFilas; a++) {
            Row filar = sheet.getRow(a);
            Integer id;
            //Ejercicio
            switch (filar.getCell(0).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_ejercicio((int) (filar.getCell(0).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_ejercicio(Integer.parseInt(filar.getCell(0).getStringCellValue()));
                    break;
            }
            //Entidad
            switch (filar.getCell(1).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_entidad((int) (filar.getCell(1).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_entidad(Integer.parseInt(filar.getCell(1).getStringCellValue()));
                    break;
            }
            //Unidad ejecutora
            switch (filar.getCell(2).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_ejec((int) filar.getCell(2).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_ejec(Integer.parseInt(filar.getCell(2).getStringCellValue()));
                    break;
            }
            //UNIDAD DESCONCENTRADA
            switch (filar.getCell(3).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_desc(String.valueOf((int) filar.getCell(3).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_desc(filar.getCell(3).getStringCellValue());
                    break;
            }
            //PROGRAMA
            switch (filar.getCell(4).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_programa(String.valueOf((int) filar.getCell(4).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_programa(filar.getCell(4).getStringCellValue());
                    break;
            }
            //SUBPROGRAMA
            switch (filar.getCell(5).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_subprograma(String.valueOf((int) filar.getCell(5).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_subprograma(filar.getCell(5).getStringCellValue());
                    break;
            }
            //PROYECTO
            switch (filar.getCell(6).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_proyecto(String.valueOf((int) filar.getCell(6).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_proyecto(filar.getCell(6).getStringCellValue());
                    break;
            }
            //ACTIVIDAD
            switch (filar.getCell(7).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_actividad(String.valueOf((int) filar.getCell(7).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_actividad(filar.getCell(7).getStringCellValue());
                    break;
            }
            //OBRAS
            switch (filar.getCell(8).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_obra(String.valueOf((int) filar.getCell(8).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_obra(filar.getCell(8).getStringCellValue());
                    break;
            }
            //GEOGRAFICO
            switch (filar.getCell(9).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_geografico(String.valueOf((int) filar.getCell(9).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_geografico(filar.getCell(9).getStringCellValue());
                    break;
            }
            //RENGLO
            switch (filar.getCell(10).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo((int) filar.getCell(10).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo(Integer.parseInt(filar.getCell(10).getStringCellValue()));
                    break;
            }
            //RENGLON AUXILIAR
            switch (filar.getCell(11).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo_aux(String.valueOf((int) (filar.getCell(11).getNumericCellValue())));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo_aux(filar.getCell(11).getStringCellValue());
                    break;
            }
            //FUENTE
            switch (filar.getCell(12).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_fuente(String.valueOf((int) filar.getCell(12).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_fuente(filar.getCell(12).getStringCellValue());
                    break;
            }
            //ORGANISMO
            switch (filar.getCell(13).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_organismo(String.valueOf((int) filar.getCell(13).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_organismo(filar.getCell(13).getStringCellValue());
                    break;
            }
            //CORRELATIVO
            switch (filar.getCell(14).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_correlativo(String.valueOf((int) filar.getCell(14).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_correlativo(filar.getCell(14).getStringCellValue());
                    break;
            }

            //PRESUPUESTO
            switch (filar.getCell(15).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_presupuesto(String.valueOf((int) filar.getCell(15).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_presupuesto(filar.getCell(15).getStringCellValue());
                    break;
            }

            //Nombre
            switch (filar.getCell(18).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_nombre(String.valueOf((int) filar.getCell(18).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_nombre(filar.getCell(18).getStringCellValue());
                    break;
            }

            //Total
            switch (filar.getCell(24).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_total(filar.getCell(24).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_costo_total(Double.parseDouble(filar.getCell(18).getStringCellValue()));
                    break;
            }

            //Unidad
            switch (filar.getCell(35).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setAg_nombre(String.valueOf((int) filar.getCell(35).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setAg_nombre(filar.getCell(35).getStringCellValue());
                    break;
            }

            //OEI
            switch (filar.getCell(37).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setTc_nombre(String.valueOf((int) filar.getCell(37).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setTc_nombre(filar.getCell(37).getStringCellValue());
                    break;
            }

            //Proyecto nombre
            switch (filar.getCell(38).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setProyecto_nombre(String.valueOf((int) filar.getCell(38).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setProyecto_nombre(filar.getCell(38).getStringCellValue());
                    break;
            }

            //Financiamiento nombre
            switch (filar.getCell(40).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setFinanciamiento_nombre(String.valueOf((int) filar.getCell(40).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setFinanciamiento_nombre(filar.getCell(40).getStringCellValue());
                    break;
            }

            //ID Requerimiento
            switch (filar.getCell(42).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    id = (int) filar.getCell(42).getNumericCellValue();
                    cRequ.setReq_id((int) filar.getCell(42).getNumericCellValue());
                    break;
                case "STRING":
                    id = Integer.parseInt(filar.getCell(42).getStringCellValue());
                    cRequ.setReq_id(Integer.parseInt(filar.getCell(42).getStringCellValue()));
                    break;
                default:
                    id = 0;
            }

            //Tipo
            int tipo = 0;
            switch (filar.getCell(43).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setTc_id((int) filar.getCell(43).getNumericCellValue());
                    tipo = (int) filar.getCell(43).getNumericCellValue();
                    break;
                case "STRING":
                    cRequ.setTc_id(Integer.parseInt(filar.getCell(43).getStringCellValue()));
                    tipo = Integer.parseInt(filar.getCell(43).getStringCellValue());
                    break;
            }

            String r;
            if (id == 0) {
                id = aRequ.ingresoDeudas(cRequ);
                cRequ.setReq_id(id);
                cPro.setEstado_id(22);
                cPro.setProyecto_id(id);
                cPro.setUsuario_nombre("0202104717");
                r = aP.EnviarDeuda(cPro);
            }
            if (aRequ.DeudasEstructura(id, tipo)) {
                r = aRequ.UpdateComprometidosestructura(cRequ);
            } else {
                r = aRequ.IngresaComprometidosestructura(cRequ);
            }
            if (r.equals("Correcto")) {
                sum++;
            }
        }

        cTransaccion objTransaccion = new cTransaccion();
        objTransaccion.setTransaccion_descripcion("La matriz: \"" + img + "\" subió, " + sum + " requerimientos.");
        objTransaccion.setTransaccion_cedula(cedula);
        objTransaccion.setTransaccion_ag(1);
        objTransaccion.setTransaccion_tipo(1);
        ingresarTransaccion(objTransaccion);

        String json = new Gson().toJson(sum);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void subirExcelPresupuestoDeudasNSis(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvalidFormatException {
        Part filePart = request.getPart("excel");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String da = formatter.format(date);
        String img = da + "-" + fileName;
        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "formulacion" + File.separator + "presupuesto";
        File uploads = new File(path); //Carpeta donde se guardan los archivos
        uploads.mkdirs(); //Crea los directorios necesarios
        File file = new File(uploads, img);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        //File fil = new File("http://cimogsys.espoch.edu.ec/spoa/formulacion/presupuesto/" + img);
        File fil = new File("/var/www/html/sip/formulacion/presupuesto/" + img);
        //FileInputStream isr = new FileInputStream(fil);

        //org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(isr);
        Workbook wb = WorkbookFactory.create(fil);
        //XSSFWorkbook wb = new XSSFWorkbook(isr);
        //XSSFSheet sheet = wb.getSheetAt(0);
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        int numFilas = sheet.getLastRowNum();
        cActividadRequerimiento cRequ = new cActividadRequerimiento();
        adActividadRequerimiento aRequ = new adActividadRequerimiento();
        for (int a = 6; a <= numFilas; a++) {
            String result;
            Row filar = sheet.getRow(a);
            Integer id;
            //Ejercicio
            switch (filar.getCell(0).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_ejercicio((int) (filar.getCell(0).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_ejercicio(Integer.parseInt(filar.getCell(0).getStringCellValue()));
                    break;
            }
            //Entidad
            switch (filar.getCell(1).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_entidad((int) (filar.getCell(1).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_entidad(Integer.parseInt(filar.getCell(1).getStringCellValue()));
                    break;
            }
            //Organo gestor
            switch (filar.getCell(2).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_geografico(String.valueOf((int) filar.getCell(2).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_geografico(filar.getCell(2).getStringCellValue());
                    break;
            }
            //Centro gestor
            switch (filar.getCell(3).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo_aux(String.valueOf((int) filar.getCell(3).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo_aux(filar.getCell(3).getStringCellValue());
                    break;
            }
            //Producto Institucional
            switch (filar.getCell(5).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_ejec((int) filar.getCell(5).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_ejec(Integer.parseInt(filar.getCell(5).getStringCellValue()));
                    break;
            }
            //Actividad
            switch (filar.getCell(6).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_actividad(String.valueOf((int) filar.getCell(6).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_actividad(filar.getCell(6).getStringCellValue());
                    break;
            }
            //Obra/tarea
            switch (filar.getCell(7).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_obra(String.valueOf((int) filar.getCell(7).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_obra(filar.getCell(7).getStringCellValue());
                    break;
            }
            //Enlace
            switch (filar.getCell(8).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_programa(String.valueOf((int) filar.getCell(8).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_programa(filar.getCell(8).getStringCellValue());
                    break;
            }
            //Renglo
            switch (filar.getCell(9).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo((int) filar.getCell(9).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo(Integer.parseInt(filar.getCell(9).getStringCellValue()));
                    break;
            }
            //Area funcional
            switch (filar.getCell(10).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_desc(String.valueOf((int) filar.getCell(10).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_desc(filar.getCell(10).getStringCellValue());
                    break;
            }
            //Fuente
            switch (filar.getCell(11).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_fuente(String.valueOf((int) (filar.getCell(11).getNumericCellValue())));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_fuente(filar.getCell(11).getStringCellValue());
                    break;
            }
            //Proyecto
            switch (filar.getCell(12).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_proyecto(String.valueOf((int) filar.getCell(12).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_proyecto(filar.getCell(12).getStringCellValue());
                    break;
            }
            //Organismo
            cRequ.setPresupuesto_organismo("0000");
            //Correlativo
            cRequ.setPresupuesto_correlativo("0000");
            cRequ.setPresupuesto_subprograma("00");

            //ID Requerimiento
            switch (filar.getCell(40).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    id = (int) filar.getCell(40).getNumericCellValue();
                    cRequ.setReq_id((int) filar.getCell(40).getNumericCellValue());
                    break;
                case "STRING":
                    id = Integer.parseInt(filar.getCell(40).getStringCellValue());
                    cRequ.setReq_id(Integer.parseInt(filar.getCell(40).getStringCellValue()));
                    break;
                default:
                    id = 0;
                    cRequ.setReq_id(0);
            }

            //Tipo
            int tipo = 0;
            switch (filar.getCell(41).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setTc_id((int) filar.getCell(41).getNumericCellValue());
                    tipo = (int) filar.getCell(41).getNumericCellValue();
                    break;
                case "STRING":
                    cRequ.setTc_id(Integer.parseInt(filar.getCell(41).getStringCellValue()));
                    tipo = Integer.parseInt(filar.getCell(41).getStringCellValue());
                    break;
            }

            String r;
            if (aRequ.DeudasEstructura(id, tipo)) {
                r = aRequ.UpdateComprometidosestructura(cRequ);
            } else {
                r = aRequ.IngresaComprometidosestructura(cRequ);
            }
            System.out.println(r);
        }
    }

    public void subirExcelComprometidos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvalidFormatException {
        Part filePart = request.getPart("excel");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String da = formatter.format(date);
        String img = da + "-" + fileName;
        String cedula = request.getParameter("cedulaexcelc");
        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "formulacion" + File.separator + "presupuesto";
        File uploads = new File(path); //Carpeta donde se guardan los archivos
        uploads.mkdirs(); //Crea los directorios necesarios
        File file = new File(uploads, img);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        //File fil = new File("http://cimogsys.espoch.edu.ec/spoa/formulacion/presupuesto/" + img);
        File fil = new File("/var/www/html/sip/formulacion/presupuesto/" + img);
        //FileInputStream isr = new FileInputStream(fil);

        //org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(isr);
        Workbook wb = WorkbookFactory.create(fil);
        //XSSFWorkbook wb = new XSSFWorkbook(isr);
        //XSSFSheet sheet = wb.getSheetAt(0);
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        int numFilas = sheet.getLastRowNum();
        cActividadRequerimiento cRequ = new cActividadRequerimiento();
        adActividadRequerimiento aRequ = new adActividadRequerimiento();
        for (int a = 1; a <= numFilas; a++) {
            String result;
            Row filar = sheet.getRow(a);
            Integer id;
            String c1, c2, c3;
            //ID
            switch (filar.getCell(0).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_id((int) (filar.getCell(0).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_id(Integer.parseInt(filar.getCell(0).getStringCellValue()));
                    break;
            }
            //Unidad/Dependencia
            switch (filar.getCell(1).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setAg_id((int) (filar.getCell(1).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setAg_id(Integer.parseInt(filar.getCell(1).getStringCellValue()));
                    break;
            }
            //OEI
            switch (filar.getCell(2).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPerspectiva_id((int) (filar.getCell(2).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPerspectiva_id(Integer.parseInt(filar.getCell(2).getStringCellValue()));
                    break;
            }
            //Proyecto nombre
            switch (filar.getCell(3).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setProyecto_nombre(String.valueOf(filar.getCell(3).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setProyecto_nombre(filar.getCell(3).getStringCellValue());
                    break;
            }
            //Contrato
            switch (filar.getCell(4).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setActividad_nombre(String.valueOf(filar.getCell(4).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setActividad_nombre(filar.getCell(4).getStringCellValue());
                    break;
            }
            //Objeto
            switch (filar.getCell(5).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_nombre(String.valueOf(filar.getCell(5).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_nombre(filar.getCell(5).getStringCellValue());
                    break;
            }
            //Programa
            switch (filar.getCell(7).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_programa(String.valueOf((int) filar.getCell(7).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_programa(filar.getCell(7).getStringCellValue());
                    break;
            }
            //Subprograma
            switch (filar.getCell(8).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_subprograma(String.valueOf((int) filar.getCell(8).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_subprograma(filar.getCell(8).getStringCellValue());
                    break;
            }
            //Proyecto
            switch (filar.getCell(9).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_proyecto(String.valueOf((int) filar.getCell(9).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_proyecto(filar.getCell(9).getStringCellValue());
                    break;
            }
            //Actividad
            switch (filar.getCell(10).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_actividad(String.valueOf((int) filar.getCell(10).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_actividad(filar.getCell(10).getStringCellValue());
                    break;
            }
            //Renglo
            switch (filar.getCell(11).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo((int) (filar.getCell(11).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo(Integer.parseInt(filar.getCell(11).getStringCellValue()));
                    break;
            }
            //Geografico
            switch (filar.getCell(12).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_geografico(String.valueOf((int) filar.getCell(12).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_geografico(filar.getCell(12).getStringCellValue());
                    break;
            }
            //Fuente
            switch (filar.getCell(13).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_fuente(String.valueOf((int) filar.getCell(13).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_fuente(filar.getCell(13).getStringCellValue());
                    break;
            }
            //Organismo
            switch (filar.getCell(14).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_organismo(String.valueOf((int) filar.getCell(14).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_organismo(filar.getCell(14).getStringCellValue());
                    break;
            }
            //Correlativo
            switch (filar.getCell(15).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_correlativo(String.valueOf((int) filar.getCell(15).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_correlativo(filar.getCell(15).getStringCellValue());
                    break;
            }
            //Ejercicio
            switch (filar.getCell(16).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_ejercicio((int) (filar.getCell(16).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_ejercicio(Integer.parseInt(filar.getCell(16).getStringCellValue()));
                    break;
            }
            //Entidad
            switch (filar.getCell(17).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_entidad((int) (filar.getCell(17).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_entidad(Integer.parseInt(filar.getCell(17).getStringCellValue()));
                    break;
            }
            //Unidad desconcentrada
            switch (filar.getCell(18).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_desc(String.valueOf((int) filar.getCell(18).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_desc(filar.getCell(18).getStringCellValue());
                    break;
            }
            //Unidad ejecutora
            switch (filar.getCell(19).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_ejec((int) (filar.getCell(19).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_ejec(Integer.getInteger(filar.getCell(19).getStringCellValue()));
                    break;
            }
            //Obra
            switch (filar.getCell(20).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_obra(String.valueOf((int) filar.getCell(20).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_obra(filar.getCell(20).getStringCellValue());
                    break;
            }
            //Renglo Auxiliar
            switch (filar.getCell(21).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo_aux(String.valueOf((int) filar.getCell(21).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo_aux(filar.getCell(21).getStringCellValue());
                    break;
            }
            //Monto
            switch (filar.getCell(22).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_total((double) (filar.getCell(22).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_costo_total(Double.parseDouble(filar.getCell(22).getStringCellValue()));
                    break;
            }
            //Anticipo
            switch (filar.getCell(23).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_sin_iva((double) (filar.getCell(23).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_costo_sin_iva(Double.parseDouble(filar.getCell(23).getStringCellValue()));
                    break;
            }
            //Presupuesto
            switch (filar.getCell(24).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_presupuesto(String.valueOf(filar.getCell(24).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_presupuesto(filar.getCell(24).getStringCellValue());
                    break;
            }
            String r = aRequ.IngresaComprometidos(cRequ, 2020);
            if (r.equals("Correcto")) {
                String rp = aRequ.IngresaComprometidosestructura(cRequ);
                if (!rp.equals("Correcto")) {
                    break;
                }
            }
        }
        cTransaccion objTransaccion = new cTransaccion();
        objTransaccion.setTransaccion_descripcion("La matriz: \"" + img + "\" se subió correctamente.");
        objTransaccion.setTransaccion_cedula(cedula);
        objTransaccion.setTransaccion_ag(1);
        objTransaccion.setTransaccion_tipo(1);
        ingresarTransaccion(objTransaccion);
    }

    public void subirExcelComprometidos22(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvalidFormatException {
        Part filePart = request.getPart("excel");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String da = formatter.format(date);
        String cedula = request.getParameter("cedulaexcelc");
        String img = da + "-" + fileName;
        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "formulacion" + File.separator + "presupuesto";
        File uploads = new File(path); //Carpeta donde se guardan los archivos
        uploads.mkdirs(); //Crea los directorios necesarios
        File file = new File(uploads, img);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        //File fil = new File("http://cimogsys.espoch.edu.ec/spoa/formulacion/presupuesto/" + img);
        File fil = new File("/var/www/html/sip/formulacion/presupuesto/" + img);
        //FileInputStream isr = new FileInputStream(fil);

        //org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(isr);
        Workbook wb = WorkbookFactory.create(fil);
        //XSSFWorkbook wb = new XSSFWorkbook(isr);
        //XSSFSheet sheet = wb.getSheetAt(0);
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        int numFilas = sheet.getLastRowNum();
        cActividadRequerimiento cRequ = new cActividadRequerimiento();
        adActividadRequerimiento aRequ = new adActividadRequerimiento();
        int sum = 0;
        for (int a = 1; a <= numFilas; a++) {
            String result;
            Row filar = sheet.getRow(a);
            Integer id;
            String c1, c2, c3;
            //ID
            switch (filar.getCell(0).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_id((int) (filar.getCell(0).getNumericCellValue()));
                    id = (int) (filar.getCell(0).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_id(Integer.parseInt(filar.getCell(0).getStringCellValue()));
                    id = Integer.parseInt(filar.getCell(0).getStringCellValue());
                    break;
                default:
                    id = 0;
            }
            //Unidad/Dependencia
            switch (filar.getCell(1).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setAg_id((int) (filar.getCell(1).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setAg_id(Integer.parseInt(filar.getCell(1).getStringCellValue()));
                    break;
            }
            //OEI
            switch (filar.getCell(2).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPerspectiva_id((int) (filar.getCell(2).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPerspectiva_id(Integer.parseInt(filar.getCell(2).getStringCellValue()));
                    break;
            }
            //Proyecto nombre
            switch (filar.getCell(3).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setProyecto_nombre(String.valueOf(filar.getCell(3).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setProyecto_nombre(filar.getCell(3).getStringCellValue());
                    break;
            }
            //Contrato
            switch (filar.getCell(4).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setActividad_nombre(String.valueOf(filar.getCell(4).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setActividad_nombre(filar.getCell(4).getStringCellValue());
                    break;
            }
            //Objeto
            switch (filar.getCell(5).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_nombre(String.valueOf(filar.getCell(5).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_nombre(filar.getCell(5).getStringCellValue());
                    break;
            }
            //Mono contrato
            switch (filar.getCell(6).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_unitario((double) filar.getCell(6).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_costo_unitario(Double.parseDouble(filar.getCell(6).getStringCellValue()));
                    break;
            }
            //Programa
            switch (filar.getCell(7).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_programa(String.valueOf((int) filar.getCell(7).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_programa(filar.getCell(7).getStringCellValue());
                    break;
            }
            //Subprograma
            switch (filar.getCell(8).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_subprograma(String.valueOf((int) filar.getCell(8).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_subprograma(filar.getCell(8).getStringCellValue());
                    break;
            }
            //Proyecto
            switch (filar.getCell(9).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_proyecto(String.valueOf((int) filar.getCell(9).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_proyecto(filar.getCell(9).getStringCellValue());
                    break;
            }
            //Actividad
            switch (filar.getCell(10).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_actividad(String.valueOf((int) filar.getCell(10).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_actividad(filar.getCell(10).getStringCellValue());
                    break;
            }
            //Renglo
            switch (filar.getCell(11).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo((int) (filar.getCell(11).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo(Integer.parseInt(filar.getCell(11).getStringCellValue()));
                    break;
            }
            //Geografico
            switch (filar.getCell(12).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_geografico(String.valueOf((int) filar.getCell(12).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_geografico(filar.getCell(12).getStringCellValue());
                    break;
            }
            //Fuente
            switch (filar.getCell(13).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_fuente(String.valueOf((int) filar.getCell(13).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_fuente(filar.getCell(13).getStringCellValue());
                    break;
            }
            //Organismo
            switch (filar.getCell(14).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_organismo(String.valueOf((int) filar.getCell(14).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_organismo(filar.getCell(14).getStringCellValue());
                    break;
            }
            //Correlativo
            switch (filar.getCell(15).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_correlativo(String.valueOf((int) filar.getCell(15).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_correlativo(filar.getCell(15).getStringCellValue());
                    break;
            }
            //Ejercicio
            switch (filar.getCell(16).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_ejercicio((int) (filar.getCell(16).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_ejercicio(Integer.parseInt(filar.getCell(16).getStringCellValue()));
                    break;
            }
            //Entidad
            switch (filar.getCell(17).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_entidad((int) (filar.getCell(17).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_entidad(Integer.parseInt(filar.getCell(17).getStringCellValue()));
                    break;
            }
            //Unidad desconcentrada
            switch (filar.getCell(18).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_desc(String.valueOf((int) filar.getCell(18).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_desc(filar.getCell(18).getStringCellValue());
                    break;
            }
            //Unidad ejecutora
            switch (filar.getCell(19).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_ejec((int) (filar.getCell(19).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_ejec(Integer.getInteger(filar.getCell(19).getStringCellValue()));
                    break;
            }
            //Obra
            switch (filar.getCell(20).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_obra(String.valueOf((int) filar.getCell(20).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_obra(filar.getCell(20).getStringCellValue());
                    break;
            }
            //Renglo Auxiliar
            switch (filar.getCell(21).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo_aux(String.valueOf((int) filar.getCell(21).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo_aux(filar.getCell(21).getStringCellValue());
                    break;
            }
            //Monto
            switch (filar.getCell(22).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_total((double) (filar.getCell(22).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_costo_total(Double.parseDouble(filar.getCell(22).getStringCellValue()));
                    break;
            }
            //Anticipo
            switch (filar.getCell(23).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_sin_iva((double) (filar.getCell(23).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_costo_sin_iva(Double.parseDouble(filar.getCell(23).getStringCellValue()));
                    break;
            }
            //Presupuesto
            switch (filar.getCell(24).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_presupuesto(String.valueOf(filar.getCell(24).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_presupuesto(filar.getCell(24).getStringCellValue());
                    break;
            }
            //Monto iva
            cRequ.setReq_cantidad(0.0);
            String r = aRequ.IngresaComprometidos(cRequ, 2022);
            if (r.equals("Correcto")) {
                cRequ.setTc_id(0);
                r = aRequ.IngresaComprometidosestructura(cRequ);
                if (!r.equals("Correcto")) {
                    break;
                } else if (cRequ.getReq_costo_sin_iva() > 0) {
                    cRequ.setTc_id(2);
                    cRequ.setPresupuesto_fuente("998");
                    r = aRequ.IngresaComprometidosestructura(cRequ);
                    if (r.equals("Correcto")) {
                        adProyecto aProy = new adProyecto();
                        cProyecto cProy = new cProyecto();

                        cProy.setProyecto_id(id);
                        cProy.setEstado_id(22);
                        cProy.setUsuario_nombre("0202104717");
                        result = aProy.EnviarDeuda(cProy);
                    }
                } else {
                    if (r.equals("Correcto")) {
                        adProyecto aProy = new adProyecto();
                        cProyecto cProy = new cProyecto();

                        cProy.setProyecto_id(id);
                        cProy.setEstado_id(22);
                        cProy.setUsuario_nombre("0202104717");
                        result = aProy.EnviarDeuda(cProy);
                    }
                }
            }
            if (r.equals("Correcto")) {
                sum++;
            }
        }

        cTransaccion objTransaccion = new cTransaccion();
        objTransaccion.setTransaccion_descripcion("La matriz: \"" + img + "\" subió, " + sum + " requerimientos.");
        objTransaccion.setTransaccion_cedula(cedula);
        objTransaccion.setTransaccion_ag(1);
        objTransaccion.setTransaccion_tipo(1);
        ingresarTransaccion(objTransaccion);

        String json = new Gson().toJson(sum);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void subirExcelComprometidos21(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvalidFormatException {
        Part filePart = request.getPart("excel");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String cedula = request.getParameter("cedulaexcelc");
        String da = formatter.format(date);
        String img = da + "-" + fileName;
        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "formulacion" + File.separator + "presupuesto";
        File uploads = new File(path); //Carpeta donde se guardan los archivos
        uploads.mkdirs(); //Crea los directorios necesarios
        File file = new File(uploads, img);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        //File fil = new File("http://cimogsys.espoch.edu.ec/spoa/formulacion/presupuesto/" + img);
        File fil = new File("C:/var/www/html/sip/formulacion/presupuesto/" + img);
        //FileInputStream isr = new FileInputStream(fil);

        //org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(isr);
        Workbook wb = WorkbookFactory.create(fil);
        //XSSFWorkbook wb = new XSSFWorkbook(isr);
        //XSSFSheet sheet = wb.getSheetAt(0);
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        int numFilas = sheet.getLastRowNum();
        cActividadRequerimiento cRequ = new cActividadRequerimiento();
        adActividadRequerimiento aRequ = new adActividadRequerimiento();
        for (int a = 1; a <= numFilas; a++) {
            String result;
            Row filar = sheet.getRow(a);
            Integer id;
            String c1, c2, c3;
            //ID
            switch (filar.getCell(0).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_id((int) (filar.getCell(0).getNumericCellValue()));
                    id = (int) (filar.getCell(0).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_id(Integer.parseInt(filar.getCell(0).getStringCellValue()));
                    id = Integer.parseInt(filar.getCell(0).getStringCellValue());
                    break;
                default:
                    id = 0;
            }
            //Unidad/Dependencia
            switch (filar.getCell(1).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setAg_id((int) (filar.getCell(1).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setAg_id(Integer.parseInt(filar.getCell(1).getStringCellValue()));
                    break;
            }
            //OEI
            switch (filar.getCell(2).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPerspectiva_id((int) (filar.getCell(2).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPerspectiva_id(Integer.parseInt(filar.getCell(2).getStringCellValue()));
                    break;
            }
            //Proyecto nombre
            switch (filar.getCell(3).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setProyecto_nombre(String.valueOf(filar.getCell(3).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setProyecto_nombre(filar.getCell(3).getStringCellValue());
                    break;
            }
            //Contrato
            switch (filar.getCell(4).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setActividad_nombre(String.valueOf(filar.getCell(4).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setActividad_nombre(filar.getCell(4).getStringCellValue());
                    break;
            }
            //Objeto
            switch (filar.getCell(5).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_nombre(String.valueOf(filar.getCell(5).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_nombre(filar.getCell(5).getStringCellValue());
                    break;
            }
            //Monto
            switch (filar.getCell(9).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_unitario((double) (filar.getCell(9).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_costo_unitario(Double.parseDouble(filar.getCell(9).getStringCellValue()));
                    break;
            }
            //Anticipo
            switch (filar.getCell(19).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_sin_iva((double) (filar.getCell(19).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_costo_sin_iva(Double.parseDouble(filar.getCell(19).getStringCellValue()));
                    break;
            }
            //Monto pendiente
            switch (filar.getCell(13).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_total((double) (filar.getCell(13).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_costo_total(Double.parseDouble(filar.getCell(13).getStringCellValue()));
                    break;
            }
            //Presupuesto
            switch (filar.getCell(21).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_presupuesto(String.valueOf(filar.getCell(21).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_presupuesto(filar.getCell(21).getStringCellValue());
                    break;
            }
            //Monto iva
            cRequ.setReq_cantidad(0.0);
            String r = aRequ.IngresaComprometidos(cRequ, 2021);
            System.out.println(r);
            if (r.equals("Correcto")) {
                adProyecto aProy = new adProyecto();
                cProyecto cProy = new cProyecto();

                cProy.setProyecto_id(id);
                cProy.setEstado_id(22);
                cProy.setUsuario_nombre("0601974363");
                result = aProy.EnviarDeuda(cProy);
                System.out.println(result);
            }
        }

        cTransaccion objTransaccion = new cTransaccion();
        objTransaccion.setTransaccion_descripcion("La matriz: \"" + img + "\" se subió correctamente.");
        objTransaccion.setTransaccion_cedula(cedula);
        objTransaccion.setTransaccion_ag(1);
        objTransaccion.setTransaccion_tipo(1);
        ingresarTransaccion(objTransaccion);
    }

    public void subirExcelRe(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvalidFormatException {
        Part filePart = request.getPart("excelRe");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String da = formatter.format(date);
        String img = da + "-" + fileName;
        String cedula = request.getParameter("cedulaexcelr");
        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "reformas";
        File uploads = new File(path); //Carpeta donde se guardan los archivos
        uploads.mkdirs(); //Crea los directorios necesarios
        File file = new File(uploads, img);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        File fil = new File("/var/www/html/sip/reformas/" + img);
        //File fil = new File("C:/var/www/html/sip/reformas/" + img);
        //FileInputStream isr = new FileInputStream(fil);

        //org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(isr);
        Workbook wb = WorkbookFactory.create(fil);
        //XSSFWorkbook wb = new XSSFWorkbook(isr);
        //XSSFSheet sheet = wb.getSheetAt(0);
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        int numFilas = sheet.getLastRowNum();
        cActividadRequerimiento cRequ = new cActividadRequerimiento();
        adActividadRequerimiento aRequ = new adActividadRequerimiento();

        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        int sum = 0;
        String val = "";
        for (int a = 6; a <= numFilas; a++) {
            String result;
            Row filar = sheet.getRow(a);
            Integer id;
            Double c1, c2, c3;
            //Ejercicio
            switch (filar.getCell(0).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_ejercicio((int) (filar.getCell(0).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_ejercicio(Integer.parseInt(filar.getCell(0).getStringCellValue()));
                    break;
            }
            //Entidad
            switch (filar.getCell(1).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_entidad((int) (filar.getCell(1).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_entidad(Integer.parseInt(filar.getCell(1).getStringCellValue()));
                    break;
            }
            //Unidad ejecutora
            switch (filar.getCell(2).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_ejec((int) (filar.getCell(2).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_ejec(Integer.getInteger(filar.getCell(2).getStringCellValue()));
                    break;
            }
            //Unidad desconcentrada
            switch (filar.getCell(3).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_desc(String.valueOf((int) filar.getCell(3).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_desc(filar.getCell(3).getStringCellValue());
                    break;
            }
            //Programa
            switch (filar.getCell(4).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_programa(String.valueOf((int) filar.getCell(4).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_programa(filar.getCell(4).getStringCellValue());
                    break;
            }
            //Subprograma
            switch (filar.getCell(5).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_subprograma(String.valueOf((int) filar.getCell(5).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_subprograma(filar.getCell(5).getStringCellValue());
                    break;
            }
            //Proyecto
            switch (filar.getCell(6).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_proyecto(String.valueOf((int) filar.getCell(6).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_proyecto(filar.getCell(6).getStringCellValue());
                    break;
            }
            //Actividad
            switch (filar.getCell(7).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_actividad(String.valueOf((int) filar.getCell(7).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_actividad(filar.getCell(7).getStringCellValue());
                    break;
            }
            //Obra
            switch (filar.getCell(8).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_obra(String.valueOf((int) filar.getCell(8).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_obra(filar.getCell(8).getStringCellValue());
                    break;
            }
            //Geografico
            switch (filar.getCell(9).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_geografico(String.valueOf((int) filar.getCell(9).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_geografico(filar.getCell(9).getStringCellValue());
                    break;
            }
            //Renglo
            switch (filar.getCell(10).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo((int) (filar.getCell(10).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo(Integer.parseInt(filar.getCell(10).getStringCellValue()));
                    break;
            }
            //Renglo Auxiliar
            switch (filar.getCell(11).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo_aux(String.valueOf((int) filar.getCell(11).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo_aux(filar.getCell(11).getStringCellValue());
                    break;
            }
            //Fuente
            switch (filar.getCell(12).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_fuente(String.valueOf((int) filar.getCell(12).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_fuente(filar.getCell(12).getStringCellValue());
                    break;
            }
            //Organismo
            switch (filar.getCell(13).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_organismo(String.valueOf((int) filar.getCell(13).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_organismo(filar.getCell(13).getStringCellValue());
                    break;
            }
            //Correlativo
            switch (filar.getCell(14).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_correlativo(String.valueOf((int) filar.getCell(14).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_correlativo(filar.getCell(14).getStringCellValue());
                    break;
            }
            //Presupuesto
            switch (filar.getCell(15).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_presupuesto(String.valueOf(filar.getCell(15).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_presupuesto(filar.getCell(15).getStringCellValue());
                    break;
            }

            //CPC
            switch (filar.getCell(16).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_cpc(String.valueOf(filar.getCell(16).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_cpc(filar.getCell(16).getStringCellValue());
                    break;
            }

            //Tipo de Compra
            Integer tp;
            String tipoc;
            switch (filar.getCell(17).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    tipoc = String.valueOf(filar.getCell(17).getNumericCellValue());
                    break;
                case "STRING":
                    tipoc = filar.getCell(17).getStringCellValue();
                    break;
                default:
                    tipoc = null;
            }
            tp = aRequ.idcodigocompra(tipoc);
            cRequ.setTc_id(tp);

            //Nombre
            switch (filar.getCell(18).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_nombre(String.valueOf(filar.getCell(18).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_nombre(filar.getCell(18).getStringCellValue());
                    break;
            }

            //Descripcion
            switch (filar.getCell(19).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_descripcion(String.valueOf(filar.getCell(19).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_descripcion(filar.getCell(19).getStringCellValue());
                    break;
            }

            Integer t = 0;
            //Cantidad anual
            switch (filar.getCell(25).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    if (filar.getCell(25).getNumericCellValue() == 0) {
                        t = 2;
                        cRequ.setReq_cantidad(filar.getCell(20).getNumericCellValue());
                    } else {
                        cRequ.setReq_cantidad(filar.getCell(25).getNumericCellValue());
                    }
                    break;
                case "STRING":
                    if (filar.getCell(25).getStringCellValue().equals("0")) {
                        t = 2;
                        cRequ.setReq_cantidad(filar.getCell(20).getNumericCellValue());
                    } else {
                        cRequ.setReq_cantidad(Double.parseDouble(filar.getCell(25).getStringCellValue()));
                    }
                    break;
                case "BLANK":
                    t = 2;
                    cRequ.setReq_cantidad(filar.getCell(20).getNumericCellValue());
                    break;
            }

            if (t > 0) {
                //Unidad
                Integer uni;
                String unidad;
                switch (filar.getCell(21).getCellTypeEnum().toString()) {
                    case "NUMERIC":
                        unidad = String.valueOf(filar.getCell(21).getNumericCellValue());
                        break;
                    case "STRING":
                        unidad = filar.getCell(21).getStringCellValue();
                        break;
                    default:
                        unidad = null;
                }
                uni = aRequ.idunidad(unidad);
                cRequ.setUnidad_id(uni);

                //Costo unitario
                switch (filar.getCell(22).getCellTypeEnum().toString()) {
                    case "NUMERIC":
                        cRequ.setReq_costo_unitario(filar.getCell(22).getNumericCellValue());
                        break;
                    case "STRING":
                        cRequ.setReq_costo_unitario(Double.parseDouble(filar.getCell(22).getStringCellValue()));
                        break;
                }

                //Costo sin iva
                switch (filar.getCell(23).getCellTypeEnum().toString()) {
                    case "NUMERIC":
                        cRequ.setReq_costo_sin_iva(filar.getCell(23).getNumericCellValue());
                        break;
                    case "STRING":
                        cRequ.setReq_costo_sin_iva(Double.parseDouble(filar.getCell(23).getStringCellValue()));
                        break;
                }
                //Costo sin iva formula 
                switch (evaluator.evaluateFormulaCell(filar.getCell(23))) {
                    case NUMERIC:
                        cRequ.setReq_costo_sin_iva(filar.getCell(23).getNumericCellValue());
                        break;
                    case STRING:
                        cRequ.setReq_costo_sin_iva(Double.parseDouble(filar.getCell(23).getStringCellValue()));
                        break;
                }

                //Costo total
                switch (filar.getCell(24).getCellTypeEnum().toString()) {
                    case "NUMERIC":
                        cRequ.setReq_costo_total(filar.getCell(24).getNumericCellValue());
                        break;
                    case "STRING":
                        cRequ.setReq_costo_total(Double.parseDouble(filar.getCell(24).getStringCellValue()));
                        break;
                }

                //Costo total
                switch (evaluator.evaluateFormulaCell(filar.getCell(24))) {
                    case NUMERIC:
                        cRequ.setReq_costo_total(filar.getCell(24).getNumericCellValue());
                        break;
                    case STRING:
                        cRequ.setReq_costo_total(Double.parseDouble(filar.getCell(24).getStringCellValue()));
                        break;
                }
                cRequ.setReq_verificacion(2);
            } else {
                //Unidad
                Integer uni;
                String unidad;
                switch (filar.getCell(26).getCellTypeEnum().toString()) {
                    case "NUMERIC":
                        unidad = String.valueOf(filar.getCell(26).getNumericCellValue());
                        break;
                    case "STRING":
                        unidad = filar.getCell(26).getStringCellValue();
                        break;
                    default:
                        unidad = null;
                }
                uni = aRequ.idunidad(unidad);
                cRequ.setUnidad_id(uni);

                //Costo unitario
                switch (filar.getCell(27).getCellTypeEnum().toString()) {
                    case "NUMERIC":
                        cRequ.setReq_costo_unitario(filar.getCell(27).getNumericCellValue());
                        break;
                    case "STRING":
                        cRequ.setReq_costo_unitario(Double.parseDouble(filar.getCell(27).getStringCellValue()));
                        break;
                }

//            //Costo sin iva
                switch (filar.getCell(28).getCellTypeEnum().toString()) {
                    case "NUMERIC":
                        cRequ.setReq_costo_sin_iva(filar.getCell(28).getNumericCellValue());
                        break;
                    case "STRING":
                        cRequ.setReq_costo_sin_iva(Double.parseDouble(filar.getCell(28).getStringCellValue()));
                        break;
                }
                //Costo sin iva formula 
                switch (evaluator.evaluateFormulaCell(filar.getCell(28))) {
                    case NUMERIC:
                        cRequ.setReq_costo_sin_iva(filar.getCell(28).getNumericCellValue());
                        break;
                    case STRING:
                        cRequ.setReq_costo_sin_iva(Double.parseDouble(filar.getCell(28).getStringCellValue()));
                        break;
                }

                //Costo total
                switch (filar.getCell(29).getCellTypeEnum().toString()) {
                    case "NUMERIC":
                        cRequ.setReq_costo_total(filar.getCell(29).getNumericCellValue());
                        break;
                    case "STRING":
                        cRequ.setReq_costo_total(Double.parseDouble(filar.getCell(29).getStringCellValue()));
                        break;
                }

                //Costo total
                switch (evaluator.evaluateFormulaCell(filar.getCell(29))) {
                    case NUMERIC:
                        cRequ.setReq_costo_total(filar.getCell(29).getNumericCellValue());
                        break;
                    case STRING:
                        cRequ.setReq_costo_total(Double.parseDouble(filar.getCell(29).getStringCellValue()));
                        break;
                }
                cRequ.setReq_verificacion(1);
            }

            //I Cuatrimestre
            switch (filar.getCell(31).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    c1 = filar.getCell(31).getNumericCellValue();
                    break;
                case "STRING":
                    c1 = Double.parseDouble(filar.getCell(31).getStringCellValue());
                    break;
                default:
                    c1 = 0.0;
                    break;
            }

            //II Cuatrimestre
            switch (filar.getCell(32).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    c2 = filar.getCell(32).getNumericCellValue();
                    break;
                case "STRING":
                    c2 = Double.parseDouble(filar.getCell(32).getStringCellValue());
                    break;
                default:
                    c2 = 0.0;
                    break;
            }

            //III Cuatrimestre
            switch (filar.getCell(33).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    c3 = filar.getCell(33).getNumericCellValue();
                    break;
                case "STRING":
                    c3 = Double.parseDouble(filar.getCell(33).getStringCellValue());
                    break;
                default:
                    c3 = 0.0;
                    break;
            }

            //Tipod de Producto
            switch (filar.getCell(34).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPac_tipo_producto(String.valueOf((int) filar.getCell(34).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPac_tipo_producto(filar.getCell(34).getStringCellValue());
                    break;
            }

            //Catalogo
            switch (filar.getCell(35).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPac_catalogo(String.valueOf((int) filar.getCell(35).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPac_catalogo(filar.getCell(35).getStringCellValue());
                    break;
            }

            //Procedimiento sugerido
            switch (filar.getCell(36).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPac_procedimiento_sug(String.valueOf(filar.getCell(36).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPac_procedimiento_sug(filar.getCell(36).getStringCellValue());
                    break;
            }

            //Fondos
            switch (filar.getCell(37).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPac_fondo_bid(String.valueOf(filar.getCell(37).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPac_fondo_bid(filar.getCell(37).getStringCellValue());
                    break;
            }

            //Numero codigo de operacion
            switch (filar.getCell(38).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPac_num_operacion((int) filar.getCell(38).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setPac_num_operacion(Integer.parseInt(filar.getCell(38).getStringCellValue()));
                    break;
            }

            //Numero codigo de proyecto
            switch (filar.getCell(39).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPac_codigo_proyecto((int) filar.getCell(39).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setPac_codigo_proyecto(Integer.parseInt(filar.getCell(39).getStringCellValue()));
                    break;
            }

            //Tipo de regimen
            switch (filar.getCell(40).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPac_tipo_regimen(String.valueOf(filar.getCell(40).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPac_tipo_regimen(filar.getCell(40).getStringCellValue());
                    break;
            }

            //Area gestion
            Integer ag;
            String area;
            switch (filar.getCell(41).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    area = String.valueOf(filar.getCell(41).getNumericCellValue());
                    break;
                case "STRING":
                    area = filar.getCell(41).getStringCellValue();
                    break;
                default:
                    area = null;
            }
            ag = aRequ.idAg(area);
            cRequ.setAg_id(ag);

            //Proyecto
            switch (filar.getCell(44).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setProyecto_nombre(String.valueOf(filar.getCell(44).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setProyecto_nombre(filar.getCell(44).getStringCellValue());
                    break;
            }

            //Actividad
            switch (filar.getCell(45).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setActividad_nombre(String.valueOf(filar.getCell(45).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setActividad_nombre(filar.getCell(45).getStringCellValue());
                    break;
            }

            //Financiamiento
            switch (filar.getCell(46).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setFinanciamiento_nombre(String.valueOf(filar.getCell(46).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setFinanciamiento_nombre(filar.getCell(46).getStringCellValue());
                    break;
            }

            //TIPO DE REQUERIMIENTO
            Integer tipo;
            if (filar.getCell(47).getStringCellValue().equals("PAC")) {
                tipo = 1;
            } else {
                tipo = 0;
            }
            cRequ.setReq_tipo(tipo);

            //ID Requerimiento
            switch (filar.getCell(48).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    id = (int) filar.getCell(48).getNumericCellValue();
                    cRequ.setReq_id((int) filar.getCell(48).getNumericCellValue());
                    break;
                case "STRING":
                    id = Integer.parseInt(filar.getCell(48).getStringCellValue());
                    cRequ.setReq_id(Integer.parseInt(filar.getCell(48).getStringCellValue()));
                    break;
                default:
                    id = 0;
                    cRequ.setReq_id(0);
            }

            //Reforma
            switch (filar.getCell(49).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_reforma((int) (filar.getCell(49).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_reforma(Integer.parseInt(filar.getCell(49).getStringCellValue()));
                    break;
            }

            Integer r = aRequ.IngresarReqReforma(cRequ);

            result = "Correcto";
            //Ingresar cuatrimestres
            if (c1 != 0.0) {
                cRequ.setReq_id(r);
                cRequ.setMes_id(1);
                cRequ.setActividad_porcentaje(c1);
                result = aRequ.IngresarRequerimientoCuatrimestre(cRequ);
            }
            if (c2 != 0.0) {
                cRequ.setReq_id(r);
                cRequ.setMes_id(2);
                cRequ.setActividad_porcentaje(c2);
                result = aRequ.IngresarRequerimientoCuatrimestre(cRequ);
            }
            if (c3 != 0.0) {
                cRequ.setReq_id(r);
                cRequ.setMes_id(3);
                cRequ.setActividad_porcentaje(c3);
                result = aRequ.IngresarRequerimientoCuatrimestre(cRequ);
            }
            if (result.equals("Correcto")) {
                val = val + "," + r;
                sum++;
            }
        }

        cTransaccion objTransaccion = new cTransaccion();
        objTransaccion.setTransaccion_descripcion("La matriz: \"" + img + "\" subió, " + sum + " requerimientos que son" + val);
        objTransaccion.setTransaccion_cedula(cedula);
        objTransaccion.setTransaccion_ag(1);
        objTransaccion.setTransaccion_tipo(1);
        ingresarTransaccion(objTransaccion);

        String json = new Gson().toJson(sum + " requerimientos, los mismos que son: " + val);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void subirExcelSaldos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvalidFormatException {
        Part filePart = request.getPart("excelS");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String cedula = request.getParameter("cedulaexcels");
        String da = formatter.format(date);
        String img = da + "-" + fileName;
        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "reformas";
        File uploads = new File(path); //Carpeta donde se guardan los archivos
        uploads.mkdirs(); //Crea los directorios necesarios
        File file = new File(uploads, img);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        File fil = new File("/var/www/html/sip/reformas/" + img);
        //File fil = new File("C:/var/www/html/sip/reformas/" + img);
        //FileInputStream isr = new FileInputStream(fil);

        //org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(isr);
        Workbook wb = WorkbookFactory.create(fil);
        //XSSFWorkbook wb = new XSSFWorkbook(isr);
        //XSSFSheet sheet = wb.getSheetAt(0);
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        int numFilas = sheet.getLastRowNum();
        cActividadRequerimiento cRequ = new cActividadRequerimiento();
        adActividadRequerimiento aRequ = new adActividadRequerimiento();

        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        int sum = 0;
        for (int a = 6; a <= numFilas; a++) {
            Row filar = sheet.getRow(a);
            Integer id;
            //Total incremento o disminución
            switch (filar.getCell(30).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_costo_total(filar.getCell(30).getNumericCellValue());
                    break;
                case "STRING":
                    cRequ.setReq_costo_total(Double.parseDouble(filar.getCell(30).getStringCellValue()));
                    break;
            }
            //ID Requerimiento
            switch (filar.getCell(48).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    id = (int) filar.getCell(48).getNumericCellValue();
                    cRequ.setReq_id((int) filar.getCell(48).getNumericCellValue());
                    break;
                case "STRING":
                    id = Integer.parseInt(filar.getCell(48).getStringCellValue());
                    cRequ.setReq_id(Integer.parseInt(filar.getCell(48).getStringCellValue()));
                    break;
                default:
                    id = 0;
                    cRequ.setReq_id(0);
            }

            //Reforma
            switch (filar.getCell(49).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_reforma((int) (filar.getCell(49).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_reforma(Integer.parseInt(filar.getCell(49).getStringCellValue()));
                    break;
            }

            String r = aRequ.IngresaSaldos(cRequ);
            if (r.equals("Correcto")) {
                sum++;
            }
        }

        cTransaccion objTransaccion = new cTransaccion();
        objTransaccion.setTransaccion_descripcion("La matriz: \"" + img + "\" subió, " + sum + " requerimientos");
        objTransaccion.setTransaccion_cedula(cedula);
        objTransaccion.setTransaccion_ag(1);
        objTransaccion.setTransaccion_tipo(1);
        ingresarTransaccion(objTransaccion);

        String json = new Gson().toJson(sum + " requerimientos");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void subirExcelReformaP(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvalidFormatException {
        Part filePart = request.getPart("excelSP");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String cedula = request.getParameter("cedulaexcelp");
        String da = formatter.format(date);
        String img = da + "-" + fileName;
        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "reformas";
        File uploads = new File(path); //Carpeta donde se guardan los archivos
        uploads.mkdirs(); //Crea los directorios necesarios
        File file = new File(uploads, img);
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        File fil = new File("/var/www/html/sip/reformas/" + img);
        //File fil = new File("C:/var/www/html/sip/reformas/" + img);
        //FileInputStream isr = new FileInputStream(fil);

        //org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(isr);
        Workbook wb = WorkbookFactory.create(fil);
        //XSSFWorkbook wb = new XSSFWorkbook(isr);
        //XSSFSheet sheet = wb.getSheetAt(0);
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
        int numFilas = sheet.getLastRowNum();
        cActividadRequerimiento cRequ = new cActividadRequerimiento();
        adActividadRequerimiento aRequ = new adActividadRequerimiento();

        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
        int sum = 0;
        String result = "";
        for (int a = 6; a <= numFilas; a++) {
            Row filar = sheet.getRow(a);
            Integer id;
            //Ejercicio
            switch (filar.getCell(0).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_ejercicio((int) (filar.getCell(0).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_ejercicio(Integer.parseInt(filar.getCell(0).getStringCellValue()));
                    break;
            }
            //Entidad
            switch (filar.getCell(1).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_entidad((int) (filar.getCell(1).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_entidad(Integer.parseInt(filar.getCell(1).getStringCellValue()));
                    break;
            }
            //Unidad ejecutora
            switch (filar.getCell(2).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_ejec((int) (filar.getCell(2).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_ejec(Integer.getInteger(filar.getCell(2).getStringCellValue()));
                    break;
            }
            //Unidad desconcentrada
            switch (filar.getCell(3).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_unidad_desc(String.valueOf((int) filar.getCell(3).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_unidad_desc(filar.getCell(3).getStringCellValue());
                    break;
            }
            //Programa
            switch (filar.getCell(4).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_programa(String.valueOf((int) filar.getCell(4).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_programa(filar.getCell(4).getStringCellValue());
                    break;
            }
            //Subprograma
            switch (filar.getCell(5).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_subprograma(String.valueOf((int) filar.getCell(5).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_subprograma(filar.getCell(5).getStringCellValue());
                    break;
            }
            //Proyecto
            switch (filar.getCell(6).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_proyecto(String.valueOf((int) filar.getCell(6).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_proyecto(filar.getCell(6).getStringCellValue());
                    break;
            }
            //Actividad
            switch (filar.getCell(7).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_actividad(String.valueOf((int) filar.getCell(7).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_actividad(filar.getCell(7).getStringCellValue());
                    break;
            }
            //Obra
            switch (filar.getCell(8).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_obra(String.valueOf((int) filar.getCell(8).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_obra(filar.getCell(8).getStringCellValue());
                    break;
            }
            //Geografico
            switch (filar.getCell(9).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_geografico(String.valueOf((int) filar.getCell(9).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_geografico(filar.getCell(9).getStringCellValue());
                    break;
            }
            //Renglo
            switch (filar.getCell(10).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo((int) (filar.getCell(10).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo(Integer.parseInt(filar.getCell(10).getStringCellValue()));
                    break;
            }
            //Renglo Auxiliar
            switch (filar.getCell(11).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_renglo_aux(String.valueOf((int) filar.getCell(11).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_renglo_aux(filar.getCell(11).getStringCellValue());
                    break;
            }
            //Fuente
            switch (filar.getCell(12).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_fuente(String.valueOf((int) filar.getCell(12).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_fuente(filar.getCell(12).getStringCellValue());
                    break;
            }
            //Organismo
            switch (filar.getCell(13).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_organismo(String.valueOf((int) filar.getCell(13).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_organismo(filar.getCell(13).getStringCellValue());
                    break;
            }
            //Correlativo
            switch (filar.getCell(14).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_correlativo(String.valueOf((int) filar.getCell(14).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_correlativo(filar.getCell(14).getStringCellValue());
                    break;
            }
            //Presupuesto
            switch (filar.getCell(15).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setPresupuesto_presupuesto(String.valueOf(filar.getCell(15).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setPresupuesto_presupuesto(filar.getCell(15).getStringCellValue());
                    break;
            }
            //ID Requerimiento
            switch (filar.getCell(42).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    id = (int) filar.getCell(42).getNumericCellValue();
                    cRequ.setReq_id((int) filar.getCell(42).getNumericCellValue());
                    break;
                case "STRING":
                    id = Integer.parseInt(filar.getCell(42).getStringCellValue());
                    cRequ.setReq_id(Integer.parseInt(filar.getCell(42).getStringCellValue()));
                    break;
                default:
                    id = 0;
                    cRequ.setReq_id(0);
            }

            //Reforma
            switch (filar.getCell(43).getCellTypeEnum().toString()) {
                case "NUMERIC":
                    cRequ.setReq_nombre(String.valueOf(filar.getCell(43).getNumericCellValue()));
                    break;
                case "STRING":
                    cRequ.setReq_nombre(filar.getCell(43).getStringCellValue());
                    break;
            }

            Integer r = aRequ.IngresarReqReformaPres(cRequ);
            if (r > 0) {
                result = "," + r;
                sum++;
            }
        }

        cTransaccion objTransaccion = new cTransaccion();
        objTransaccion.setTransaccion_descripcion("La matriz: \"" + img + "\" subió, " + sum + " requerimientos que son" + result);
        objTransaccion.setTransaccion_cedula(cedula);
        objTransaccion.setTransaccion_ag(1);
        objTransaccion.setTransaccion_tipo(1);
        ingresarTransaccion(objTransaccion);

        String json = new Gson().toJson(sum + " requerimientos, los mismos que son: " + result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
