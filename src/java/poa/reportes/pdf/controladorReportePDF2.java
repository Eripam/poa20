/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.reportes.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import poa.acceso.adAreaGestion;
import poa.acceso.adTecho;
import poa.clases.cAreaGestion;
import poa.clases.cTecho;

/**
 *
 * @author EriPam
 */
public class controladorReportePDF2 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {

        if (request.getParameter("accion") != null) {

            String strAccion = request.getParameter("accion");

            switch (strAccion) {
                case "reportePresupuestoPriorizado": {
                    try {
                        reportePresupuestoPriorizado(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(controladorReportePDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "reportePresupuestoPriorizado20": {
                    try {
                        reportePresupuestoPriorizado20(request, response);
                    } catch (DocumentException ex) {
                        Logger.getLogger(controladorReportePDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }
    }

    private void reportePresupuestoPriorizado(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        //Document document = new Document(PageSize.LETTER, 25, 25, 140, 50);
        Document document = new Document(PageSize.LETTER.rotate(), 39, 36, 132, 67);
        DecimalFormat formateador = new DecimalFormat("####,###,##0.00");
        try {
            String path = getServletContext().getRealPath("/") + "img/reporte/";
            String pathimg = getServletContext().getRealPath("/") + "img/reporte/";
// Paso 2: Establecemos el ContentType y creamos una instancia del Writer
            response.setContentType("application/pdf");
            String anio = request.getParameter("anio");
            String titulo = "DIRECCIÓN DE PLANIFICACIÓN - " + anio;
            OutputStream file = new FileOutputStream(new File(path + "reporte_POA_techos.pdf"));
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            //PdfWriter writer2 = PdfWriter.getInstance(document, file);
            formatoReporteH eventos = new formatoReporteH(path, titulo, pathimg + "logo-plan.png");

            writer.setPageEvent(eventos);
            //writer2.setPageEvent(eventos);
// Paso 3
            document.open();
// Paso 4
            document.addTitle("Presupuesto Institucional");

            Date date = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat();
            String today = formatter.format(date);

            //Lista de presupuesto
            List<cTecho> resultpres = new ArrayList<cTecho>();
            List<cTecho> resultpresfac = new ArrayList<cTecho>();
            List<cTecho> resultpresauto = new ArrayList<cTecho>();
            List<cTecho> resultpresobli = new ArrayList<cTecho>();
            List<cTecho> resultprespluri = new ArrayList<cTecho>();
            adTecho aTecho = new adTecho();
            resultpres = aTecho.ListarPresupuestoPriorizado(Integer.parseInt(anio));
            double tasig, tacad, tinv, tvin, tges, total, tdispo, tplan, totalde;

            Font fuente = new Font();
            fuente.setSize(10);
            Font fuen = new Font(Font.FontFamily.TIMES_ROMAN, 14, 1);
            Font fuente2 = new Font(Font.FontFamily.TIMES_ROMAN, 9, 1, BaseColor.WHITE);
            Font fuente3 = new Font(Font.FontFamily.TIMES_ROMAN, 7, 0, BaseColor.BLACK);
            Font fuente3ne = new Font(Font.FontFamily.TIMES_ROMAN, 7, 1, BaseColor.BLACK);

            BaseColor azul = WebColors.getRGBColor("#133351");

            //Total de facultades
            Chunk parte2 = new Chunk("PRESUPUESTO FACULTADES Y SEDES", fuen);
            Paragraph para2 = new Paragraph();
            para2.add(parte2);
            para2.setAlignment(Element.ALIGN_CENTER);
            document.add(para2);
            document.add(new Paragraph(" "));

            PdfPTable tablef = new PdfPTable(8);
            tablef.setTotalWidth(new float[]{120, 70, 90, 90, 90, 90, 90, 90});
            tablef.setLockedWidth(true);
            PdfPCell cell;

            cell = new PdfPCell(new Paragraph("Facultad / Sede", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("Asignado", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("Planificación Propuesta", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("Académico", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("Investigación", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("Vinculación", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("Valores Pend. de pago años anteriores", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("Total planificación priorizada", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

//            cell = new PdfPCell(new Paragraph("DISPONIBLE", fuente2));
//            cell.setPadding(5);
//            cell.setBackgroundColor(azul);
//            cell.setBorderWidthLeft(1);
//            cell.setBorderColorLeft(BaseColor.WHITE);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            tablef.addCell(cell);
            resultpresfac = aTecho.ListarPresupuestoPriorizadoporUnidadesR(1, 1, Integer.parseInt(anio), "CORRIENTE");
            tasig = 0;
            tacad = 0;
            tinv = 0;
            tvin = 0;
            tges = 0;
            total = 0;
            tdispo = 0;
            tplan = 0;
            totalde = 0;
            for (int i = 0; i < resultpresfac.size(); i++) {
                cell = new PdfPCell(new Paragraph(resultpresfac.get(i).getAg_nombre(), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tablef.addCell(cell);

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getTecho_total()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                tasig += resultpresfac.get(i).getTecho_total();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getTecho_planificado() + aTecho.DeudaPlanificada(resultpresfac.get(i).getAg_nombre(), Integer.parseInt(anio))), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                tplan += resultpresfac.get(i).getTecho_planificado() + aTecho.DeudaPlanificada(resultpresfac.get(i).getAg_nombre(), Integer.parseInt(anio));

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_aca()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                tacad += resultpresfac.get(i).getPresupuesto_aca();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_inv()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                tinv += resultpresfac.get(i).getPresupuesto_inv();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_vin()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                tvin += resultpresfac.get(i).getPresupuesto_vin();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getTecho_reforma()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                totalde += resultpresfac.get(i).getTecho_reforma();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_total() + resultpresfac.get(i).getTecho_reforma()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                total += resultpresfac.get(i).getPresupuesto_total() + resultpresfac.get(i).getTecho_reforma();

//                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getTecho_inicial() - resultpresfac.get(i).getTecho_reforma()), fuente3));
//                cell.setBackgroundColor(BaseColor.WHITE);
//                cell.setPadding(5);
//                cell.setBorderWidth(1);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                tablef.addCell(cell);
//                tdispo += (resultpresfac.get(i).getTecho_inicial() - resultpresfac.get(i).getTecho_reforma());
            }
            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tasig), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tplan), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tacad), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tinv), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tvin), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalde), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(total), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

//            cell = new PdfPCell(new Paragraph("$" + formateador.format(tdispo), fuente3ne));
//            cell.setBackgroundColor(BaseColor.WHITE);
//            cell.setPadding(5);
//            cell.setBorderWidth(1);
//            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            tablef.addCell(cell);
            document.add(tablef);
            document.newPage();

            //Total de UNIDADES
            Chunk parte3 = new Chunk("PRESUPUESTO UNIDADES ADMINISTRATIVAS", fuen);
            Paragraph para3 = new Paragraph();
            para3.add(parte3);
            para3.setAlignment(Element.ALIGN_CENTER);
            document.add(para3);
            document.add(new Paragraph(" "));
            tasig = 0;
            tacad = 0;
            tinv = 0;
            tvin = 0;
            tges = 0;
            total = 0;
            totalde = 0;
            tdispo = 0;

            PdfPTable tableU = new PdfPTable(7);
            tableU.setTotalWidth(new float[]{160, 90, 90, 90, 90, 90, 90});
            tableU.setLockedWidth(true);

            cell = new PdfPCell(new Paragraph("Unidades Administrativas", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("Planificación Propuesta", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("Académico", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("Investigación", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("Vinculación", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("Gestión", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("Total planificación priorizada", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            resultpresfac = aTecho.ListarPresupuestoPriorizadoporUnidadesR(2, 1, Integer.parseInt(anio), "CORRIENTE");
            for (int i = 0; i < resultpresfac.size(); i++) {
                cell = new PdfPCell(new Paragraph(resultpresfac.get(i).getAg_nombre(), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableU.addCell(cell);

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getTecho_planificado()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                tplan += resultpresfac.get(i).getTecho_planificado();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_aca()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                tacad += resultpresfac.get(i).getPresupuesto_aca();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_inv()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                tinv += resultpresfac.get(i).getPresupuesto_inv();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_vin()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                tvin += resultpresfac.get(i).getPresupuesto_vin();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_ges()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                tges += resultpresfac.get(i).getPresupuesto_ges();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_total()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                total += resultpresfac.get(i).getPresupuesto_total();
            }
            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tplan), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tacad), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tinv), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tvin), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tges), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(total), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            document.add(tableU);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Chunk parte5 = new Chunk("OBLIGACIONES PENDIENTES DE PAGO AÑOS ANTERIORES Y SALDOS COMPROMETIDOS NO DEVENGADOS", fuen);
            Paragraph para5 = new Paragraph();
            para5.add(parte5);
            para5.setAlignment(Element.ALIGN_CENTER);
            document.add(para5);
            document.add(new Paragraph(" "));

            PdfPTable tableO = new PdfPTable(7);
            tableO.setTotalWidth(new float[]{140, 95, 95, 95, 90, 90, 95});
            tableO.setLockedWidth(true);

            cell = new PdfPCell(new Paragraph("Unidades Académicas / Administrativas", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("Académico", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("Investigación", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("Vinculación", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("Gestión", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("FUENTE 998", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("Total", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            double totalobliaca = 0, totalobliinv = 0, totaloblivin = 0, totalobliges = 0, totalobli = 0, totaluni = 0, totalanticipo = 0;
            List<cAreaGestion> cAg = new ArrayList<cAreaGestion>();
            adAreaGestion adAg = new adAreaGestion();
            cAg = adAg.obtenerAreasGestionUnidadesEval();
            for (int i = 0; i < cAg.size(); i++) {
                if (aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "82", 1) > 0 || aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "83", 1) > 0 || aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "84", 1) > 0 || aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "01", 1) > 0 || aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "0", 2) > 0) {
                    cell = new PdfPCell(new Paragraph(cAg.get(i).getAg_nombre(), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    tableO.addCell(cell);

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "82", 1)), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableO.addCell(cell);
                    totalobliaca += aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "82", 1);

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "83", 1)), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableO.addCell(cell);
                    totalobliinv += aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "83", 1);

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "84", 1)), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableO.addCell(cell);
                    totaloblivin += aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "84", 1);

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "01", 1)), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableO.addCell(cell);
                    totalobliges += aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "01", 1);

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "0", 2)), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableO.addCell(cell);
                    totalanticipo += aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "0", 2);

                    totaluni = aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "82", 1) + aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "83", 1) + aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "84", 1) + aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "01", 1) + aTecho.ListarPresupuestoObligacionesA(Integer.parseInt(anio), cAg.get(i).getAg_id(), "0", 2);
                    cell = new PdfPCell(new Paragraph("$" + formateador.format(totaluni), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableO.addCell(cell);
                    totalobli += totaluni;
                }
            }
            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalobliaca), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalobliinv), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totaloblivin), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalobliges), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalanticipo), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalobli), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableO.addCell(cell);

            document.add(tableO);
            document.add(new Paragraph(" "));
            document.newPage();
            Chunk parte4 = new Chunk("PRESUPUESTO UNIDADES AUTO GESTIÓN", fuen);
            Paragraph para4 = new Paragraph();
            para4.add(parte4);
            para4.setAlignment(Element.ALIGN_CENTER);
            document.add(para4);
            document.add(new Paragraph(" "));

            PdfPTable tableA = new PdfPTable(7);
            tableA.setTotalWidth(new float[]{160, 90, 90, 90, 90, 90, 90});
            tableA.setLockedWidth(true);

            cell = new PdfPCell(new Paragraph("Unidades Académicass / Administrativas", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("Planificación propuesta", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("Académico", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("Investigación", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("Vinculación", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("Gestión", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("Total Planificación Priorizada", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            double totalautoaca = 0, totalautoinv = 0, totalautovin = 0, totalautoges = 0, totalau = 0, totalautoplan = 0;
            resultpresauto = aTecho.ListarPresupuestoPriorizadoporUnidadesAuto(Integer.parseInt(anio));
            for (int i = 0; i < resultpresauto.size(); i++) {
                if (resultpresauto.get(i).getPresupuesto_total() > 0) {
                    cell = new PdfPCell(new Paragraph(resultpresauto.get(i).getAg_nombre(), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    tableA.addCell(cell);

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getTecho_planificado()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalautoplan += resultpresauto.get(i).getTecho_planificado();

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getPresupuesto_aca()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalautoaca += resultpresauto.get(i).getPresupuesto_aca();

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getPresupuesto_inv()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalautoinv += resultpresauto.get(i).getPresupuesto_inv();

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getPresupuesto_vin()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalautovin += resultpresauto.get(i).getPresupuesto_vin();

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getPresupuesto_ges()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalautoges += resultpresauto.get(i).getPresupuesto_ges();

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getPresupuesto_total()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalau += resultpresauto.get(i).getPresupuesto_total();
                }
            }
            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalautoplan), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalautoaca), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalautoinv), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalautovin), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalautoges), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalau), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            document.add(tableA);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Chunk parte6;
            Integer an = Integer.parseInt(anio) + 1;
            parte6 = new Chunk("PLANIFICACIÒN PLURIANUAL AÑOS SIGUIENTES", fuen);
            Paragraph para6 = new Paragraph();
            para6.add(parte6);
            para6.setAlignment(Element.ALIGN_CENTER);
            document.add(para6);
            document.add(new Paragraph(" "));

            PdfPTable tableP = new PdfPTable(2);
            tableP.setTotalWidth(new float[]{400, 300});
            tableP.setLockedWidth(true);

            cell = new PdfPCell(new Paragraph("Unidades Académicas / Administrativas", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell);

            cell = new PdfPCell(new Paragraph("Total", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell);

            double totalpluri = 0;
            resultprespluri = aTecho.ListarPresupuestoPlurianuales(Integer.parseInt(anio));
            for (int i = 0; i < resultprespluri.size(); i++) {
                cell = new PdfPCell(new Paragraph(resultprespluri.get(i).getAg_nombre(), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableP.addCell(cell);

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultprespluri.get(i).getPresupuesto_total()), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableP.addCell(cell);
                totalpluri += resultprespluri.get(i).getPresupuesto_total();
            }
            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalpluri), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableP.addCell(cell);

            document.add(tableP);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Chunk parteo = new Chunk("PLANIFICACIÓN POR FUNCIÓN SUSTANTIVA " + anio + "", fuen);
            Paragraph paraoei = new Paragraph();
            paraoei.add(parteo);
            paraoei.setAlignment(Element.ALIGN_CENTER);
            document.add(paraoei);
            document.add(new Paragraph(" "));

            PdfPTable tableoei = new PdfPTable(5);
            tableoei.setTotalWidth(new float[]{140, 140, 140, 140, 140});
            tableoei.setLockedWidth(true);

            cell = new PdfPCell(new Paragraph("Académico-OEI-01-PROG-82", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableoei.addCell(cell);

            cell = new PdfPCell(new Paragraph("Investigación-OEI-02-PROG-83", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableoei.addCell(cell);

            cell = new PdfPCell(new Paragraph("Vinculación-OEI-03-PROG-84", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableoei.addCell(cell);

            cell = new PdfPCell(new Paragraph("Gestión-OEI-04-PROG-01", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableoei.addCell(cell);

            cell = new PdfPCell(new Paragraph("Total POA y Presupuesto", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableoei.addCell(cell);

            double tp82 = aTecho.ListarPresupuestoOEI("82", Integer.parseInt(anio)), tp83 = aTecho.ListarPresupuestoOEI("83", Integer.parseInt(anio)), tp84 = aTecho.ListarPresupuestoOEI("84", Integer.parseInt(anio)), tp01 = aTecho.ListarPresupuestoOEI("01", Integer.parseInt(anio));
            cell = new PdfPCell(new Paragraph("$" + formateador.format(tp82), fuente3));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableoei.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tp83), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableoei.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tp84), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableoei.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tp01), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableoei.addCell(cell);

            double totalpresoei = tp82 + tp83 + tp84 + tp01;
            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalpresoei), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableoei.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getPresupuesto_aca()), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableoei.addCell(cell);

            document.add(tableoei);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Chunk parte1 = new Chunk("TOTAL POA Y PRESUPUESTO " + anio + "", fuen);
            Paragraph para = new Paragraph();
            para.add(parte1);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(7);
            table.setTotalWidth(new float[]{120, 80, 80, 110, 100, 120, 90});
            table.setLockedWidth(true);

            cell = new PdfPCell(new Paragraph("Presupuesto Institucional", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Auto Gestión", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Otras fuentes", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Obligaciones pendientes años anteriores", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
             cell = new PdfPCell(new Paragraph("FUENTE 998", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Total POA y Presupuesto", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Planificación Plurianual", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getTecho_inicial()), fuente3));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getTecho_planificado()), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getTecho_reforma()), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getPresupuesto_ges()), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getPresupuesto_inv()), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            double totalpres = resultpres.get(0).getTecho_inicial() + resultpres.get(0).getTecho_planificado() + resultpres.get(0).getTecho_reforma() + resultpres.get(0).getPresupuesto_ges()+resultpres.get(0).getPresupuesto_inv();
            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalpres), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getPresupuesto_aca()), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            //document.add(new Paragraph("Fecha de descargo: " + today));
        } catch (DocumentException de) {
            de.printStackTrace();
            System.err.println("document: " + de.getMessage());
            System.err.println("error: " + de.getMessage());
        }
// Paso: Cerramos el ‘document (el ‘outputstream’ se cierra automáticamente internamente)
        document.close();
    }

    private void reportePresupuestoPriorizado20(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException, DocumentException {
        //Document document = new Document(PageSize.LETTER, 25, 25, 140, 50);
        Document document = new Document(PageSize.LETTER.rotate(), 39, 36, 132, 67);
        DecimalFormat formateador = new DecimalFormat("####,###,##0.00");
        try {
            String path = getServletContext().getRealPath("/") + "img/reporte/";
            String pathimg = getServletContext().getRealPath("/") + "img/reporte/";
// Paso 2: Establecemos el ContentType y creamos una instancia del Writer
            response.setContentType("application/pdf");
            String titulo = "DIRECCIÓN DE PLANIFICACIÓN - 2020";
            OutputStream file = new FileOutputStream(new File(path + "reporte_POA_techos.pdf"));
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            //PdfWriter writer2 = PdfWriter.getInstance(document, file);
            formatoReporteH eventos = new formatoReporteH(path, titulo, pathimg + "logo-plan.png");

            writer.setPageEvent(eventos);
            //writer2.setPageEvent(eventos);
// Paso 3
            document.open();
// Paso 4
            document.addTitle("Presupuesto Institucional");

//            Date date = Calendar.getInstance().getTime();
//            DateFormat formatter = new SimpleDateFormat("07/01/2020, 05:45:54 a");
//            String today = formatter.format(date);
            //Lista de presupuesto
            List<cTecho> resultpres = new ArrayList<cTecho>();
            List<cTecho> resultpresfac = new ArrayList<cTecho>();
            List<cTecho> resultpresauto = new ArrayList<cTecho>();
            List<cTecho> resultpresobli = new ArrayList<cTecho>();
            List<cTecho> resultprespluri = new ArrayList<cTecho>();
            adTecho aTecho = new adTecho();
            resultpres = aTecho.ListarPresupuestoPriorizado(2020);
            double tasig, tacad, tinv, tvin, tges, total, tdispo, tplan;

            Font fuente = new Font();
            fuente.setSize(10);
            Font fuen = new Font(Font.FontFamily.TIMES_ROMAN, 14, 1);
            Font fuente2 = new Font(Font.FontFamily.TIMES_ROMAN, 9, 1, BaseColor.WHITE);
            Font fuente3 = new Font(Font.FontFamily.TIMES_ROMAN, 7, 0, BaseColor.BLACK);
            Font fuente3ne = new Font(Font.FontFamily.TIMES_ROMAN, 7, 1, BaseColor.BLACK);

            BaseColor azul = WebColors.getRGBColor("#133351");

            //Total de facultades
            Chunk parte2 = new Chunk("PRESUPUESTO FACULTADES Y EXTENSIONES", fuen);
            Paragraph para2 = new Paragraph();
            para2.add(parte2);
            para2.setAlignment(Element.ALIGN_CENTER);
            document.add(para2);
            document.add(new Paragraph(" "));

            PdfPTable tablef = new PdfPTable(8);
            tablef.setTotalWidth(new float[]{125, 75, 75, 90, 90, 90, 80, 70});
            tablef.setLockedWidth(true);
            PdfPCell cell;

            cell = new PdfPCell(new Paragraph("FACULTAD / EXTENSIÓN", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("ASIGNADO", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("PLANIFICADO", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("ACADÉMICO", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("INVESTIGACIÓN", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("VINCULACIÓN", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("TOTAL PRIORIZADO", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("DISPONIBLE", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            resultpresfac = aTecho.ListarPresupuestoPriorizadoporUnidades(1, 1);
            tasig = 0;
            tacad = 0;
            tinv = 0;
            tvin = 0;
            tges = 0;
            total = 0;
            tdispo = 0;
            tplan = 0;
            for (int i = 0; i < resultpresfac.size(); i++) {
                cell = new PdfPCell(new Paragraph(resultpresfac.get(i).getAg_nombre(), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tablef.addCell(cell);

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getTecho_total()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                tasig += resultpresfac.get(i).getTecho_total();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getTecho_planificado()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                tplan += resultpresfac.get(i).getTecho_planificado();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_aca()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                tacad += resultpresfac.get(i).getPresupuesto_aca();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_inv()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                tinv += resultpresfac.get(i).getPresupuesto_inv();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_vin()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                tvin += resultpresfac.get(i).getPresupuesto_vin();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_total()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                total += resultpresfac.get(i).getPresupuesto_total();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getTecho_inicial()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tablef.addCell(cell);
                tdispo += resultpresfac.get(i).getTecho_inicial();
            }
            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tasig), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tplan), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tacad), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tinv), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tvin), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(total), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tdispo), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablef.addCell(cell);
            document.add(tablef);
            document.add(new Paragraph(" "));

            //Total de UNIDADES
            Chunk parte3 = new Chunk("PRESUPUESTO UNIDADES ADMINISTRATIVAS", fuen);
            Paragraph para3 = new Paragraph();
            para3.add(parte3);
            para3.setAlignment(Element.ALIGN_CENTER);
            document.add(para3);
            document.add(new Paragraph(" "));
            tasig = 0;
            tacad = 0;
            tinv = 0;
            tvin = 0;
            tges = 0;
            total = 0;
            tdispo = 0;

            PdfPTable tableU = new PdfPTable(7);
            tableU.setTotalWidth(new float[]{160, 90, 90, 90, 90, 90, 90});
            tableU.setLockedWidth(true);

            cell = new PdfPCell(new Paragraph("UNIDADES ADMINISTRATIVAS", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("PLANIFICADO", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("ACADÉMICO", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("INVESTIGACIÓN", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("VINCULACIÓN", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("GESTIÓN", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("TOTAL PRIORIZADO", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            resultpresfac = aTecho.ListarPresupuestoPriorizadoporUnidades(2, 1);
            for (int i = 0; i < resultpresfac.size(); i++) {
                cell = new PdfPCell(new Paragraph(resultpresfac.get(i).getAg_nombre(), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableU.addCell(cell);

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getTecho_planificado()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                tplan += resultpresfac.get(i).getTecho_planificado();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_aca()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                tacad += resultpresfac.get(i).getPresupuesto_aca();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_inv()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                tinv += resultpresfac.get(i).getPresupuesto_inv();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_vin()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                tvin += resultpresfac.get(i).getPresupuesto_vin();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_ges()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                tges += resultpresfac.get(i).getPresupuesto_ges();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresfac.get(i).getPresupuesto_total()), fuente3));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableU.addCell(cell);
                total += resultpresfac.get(i).getPresupuesto_total();
            }
            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tplan), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tacad), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tinv), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tvin), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tges), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(total), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(tdispo), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableU.addCell(cell);

            document.add(tableU);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Chunk parte4 = new Chunk("PRESUPUESTO UNIDADES AUTO GESTIÓN", fuen);
            Paragraph para4 = new Paragraph();
            para4.add(parte4);
            para4.setAlignment(Element.ALIGN_CENTER);
            document.add(para4);
            document.add(new Paragraph(" "));

            PdfPTable tableA = new PdfPTable(7);
            tableA.setTotalWidth(new float[]{160, 90, 90, 90, 90, 90, 90});
            tableA.setLockedWidth(true);

            cell = new PdfPCell(new Paragraph("UNIDADES ACADÉMICAS / ADMINISTRATIVAS", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("PLANIFICADO", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("ACADÉMICO", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("INVESTIGACIÓN", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("VINCULACIÓN", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("GESTIÓN", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("TOTAL PRIORIZADO", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            double totalautoaca = 0, totalautoinv = 0, totalautovin = 0, totalautoges = 0, totalau = 0, totalautoplan = 0;
            resultpresauto = aTecho.ListarPresupuestoPriorizadoporUnidadesAuto(2020);
            for (int i = 0; i < resultpresauto.size(); i++) {
                if (resultpresauto.get(i).getPresupuesto_total() > 0) {
                    cell = new PdfPCell(new Paragraph(resultpresauto.get(i).getAg_nombre(), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    tableA.addCell(cell);

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getTecho_planificado()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalautoplan += resultpresauto.get(i).getTecho_planificado();

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getPresupuesto_aca()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalautoaca += resultpresauto.get(i).getPresupuesto_aca();

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getPresupuesto_inv()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalautoinv += resultpresauto.get(i).getPresupuesto_inv();

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getPresupuesto_vin()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalautovin += resultpresauto.get(i).getPresupuesto_vin();

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getPresupuesto_ges()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalautoges += resultpresauto.get(i).getPresupuesto_ges();

                    cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresauto.get(i).getPresupuesto_total()), fuente3ne));
                    cell.setBackgroundColor(BaseColor.WHITE);
                    cell.setPadding(5);
                    cell.setBorderWidth(1);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tableA.addCell(cell);
                    totalau += resultpresauto.get(i).getPresupuesto_total();
                }
            }
            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalautoplan), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalautoaca), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalautoinv), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalautovin), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalautoges), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalau), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableA.addCell(cell);

            document.add(tableA);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Chunk parte5 = new Chunk("OBLIGACIONES PENDIENTES AÑOS ANTERIORES", fuen);
            Paragraph para5 = new Paragraph();
            para5.add(parte5);
            para5.setAlignment(Element.ALIGN_CENTER);
            document.add(para5);
            document.add(new Paragraph(" "));

            PdfPTable tableO = new PdfPTable(6);
            tableO.setTotalWidth(new float[]{175, 105, 105, 105, 105, 105});
            tableO.setLockedWidth(true);

            cell = new PdfPCell(new Paragraph("UNIDADES ACADÉMICAS / ADMINISTRATIVAS", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("ACADÉMICO", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("INVESTIGACIÓN", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("VINCULACIÓN", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("GESTIÓN", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            double totalobliaca = 0, totalobliinv = 0, totaloblivin = 0, totalobliges = 0, totalobli = 0;
            resultpresobli = aTecho.ListarPresupuestoObligaciones20();
            for (int i = 0; i < resultpresobli.size(); i++) {
                cell = new PdfPCell(new Paragraph(resultpresobli.get(i).getAg_nombre(), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableO.addCell(cell);

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresobli.get(i).getPresupuesto_aca()), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableO.addCell(cell);
                totalobliaca += resultpresobli.get(i).getPresupuesto_aca();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresobli.get(i).getPresupuesto_inv()), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableO.addCell(cell);
                totalobliinv += resultpresobli.get(i).getPresupuesto_inv();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresobli.get(i).getPresupuesto_vin()), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableO.addCell(cell);
                totaloblivin += resultpresobli.get(i).getPresupuesto_vin();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresobli.get(i).getPresupuesto_ges()), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableO.addCell(cell);
                totalobliges += resultpresobli.get(i).getPresupuesto_ges();

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpresobli.get(i).getPresupuesto_aca() + resultpresobli.get(i).getPresupuesto_inv() + resultpresobli.get(i).getPresupuesto_vin() + resultpresobli.get(i).getPresupuesto_ges()), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableO.addCell(cell);
                totalobli += resultpresobli.get(i).getPresupuesto_aca() + resultpresobli.get(i).getPresupuesto_inv() + resultpresobli.get(i).getPresupuesto_vin() + resultpresobli.get(i).getPresupuesto_ges();
            }
            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalobliaca), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalobliinv), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totaloblivin), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalobliges), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableO.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalobli), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableO.addCell(cell);

            document.add(tableO);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Chunk parte6 = new Chunk("PLANIFICACIÒN PLURIANUAL AÑO 2021 - EQUIPAMENTO PARA ACADEMIA E INVESTIGACIÒN", fuen);
            Paragraph para6 = new Paragraph();
            para6.add(parte6);
            para6.setAlignment(Element.ALIGN_CENTER);
            document.add(para6);
            document.add(new Paragraph(" "));

            PdfPTable tableP = new PdfPTable(2);
            tableP.setTotalWidth(new float[]{400, 300});
            tableP.setLockedWidth(true);

            cell = new PdfPCell(new Paragraph("UNIDADES ACADÉMICAS / ADMINISTRATIVAS", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell);

            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell);

            double totalpluri = 0;
            resultprespluri = aTecho.ListarPresupuestoPlurianuales(2020);
            for (int i = 0; i < resultprespluri.size(); i++) {
                cell = new PdfPCell(new Paragraph(resultprespluri.get(i).getAg_nombre(), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tableP.addCell(cell);

                cell = new PdfPCell(new Paragraph("$" + formateador.format(resultprespluri.get(i).getPresupuesto_total()), fuente3ne));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setPadding(5);
                cell.setBorderWidth(1);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tableP.addCell(cell);
                totalpluri += resultprespluri.get(i).getPresupuesto_total();
            }
            cell = new PdfPCell(new Paragraph("TOTAL", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableP.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalpluri), fuente3ne));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tableP.addCell(cell);

            document.add(tableP);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            Chunk parte1 = new Chunk("PRESUPUESTO INSTITUCIONAL TOTAL", fuen);
            Paragraph para = new Paragraph();
            para.add(parte1);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(7);
            table.setTotalWidth(new float[]{100, 90, 90, 100, 110, 100, 100});
            table.setLockedWidth(true);

            cell = new PdfPCell(new Paragraph("PRESUPUESTO INSTITUCIONAL", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setBorderWidthRight(1);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setBorderColorLeft(azul);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("AUTO GESTIÓN", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthRight(1);
            cell.setBorderColorRight(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("OTRAS FUENTES", fuente2));
            cell.setBackgroundColor(azul);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("OBLIGACIONES PENDIENTES AÑOS ANTERIORES", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("COMPROMETIDOS NO DEVENGADOS", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("TOTAL PRESUPUESTO", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("PLANIFICACIÒN PLURIANUAL 2021", fuente2));
            cell.setPadding(5);
            cell.setBackgroundColor(azul);
            cell.setBorderWidthLeft(1);
            cell.setBorderColorLeft(BaseColor.WHITE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getTecho_inicial()), fuente3));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setPadding(5);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getTecho_planificado()), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getTecho_reforma()), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getPresupuesto_ges()), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getPresupuesto_vin()), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            double totalpres = resultpres.get(0).getTecho_inicial() + resultpres.get(0).getTecho_planificado() + resultpres.get(0).getTecho_reforma() + resultpres.get(0).getPresupuesto_ges() + +resultpres.get(0).getPresupuesto_vin();
            cell = new PdfPCell(new Paragraph("$" + formateador.format(totalpres), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("$" + formateador.format(resultpres.get(0).getPresupuesto_aca()), fuente3));
            cell.setPadding(5);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            //document.add(new Paragraph("Fecha de descargo: " + today));
        } catch (DocumentException de) {
            de.printStackTrace();
            System.err.println("document: " + de.getMessage());
            System.err.println("error: " + de.getMessage());
        }
// Paso: Cerramos el ‘document (el ‘outputstream’ se cierra automáticamente internamente)
        document.close();
    }

}
