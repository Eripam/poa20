/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.reportes.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PLANIFICACION
 */
public class formatoReporteH extends PdfPageEventHelper {

    private Image imagen;
    private Image imagencimogsys;
    private Image imagenfacultad;
    private Image barraespoch;
    private Image imagenplani;
    private Image fondo;
    private String nombre_facultad;
    private String nombre_espoch = "ESPOCH";
    private String nombre_espoch2 = "ESCUELA SUPERIOR POLITÉCNICA DE CHIMBORAZO";
    private String logo_espoch = "LOGO-ESPOCH.png";
    private String logo_cimogsys = "LOGO-CIMOGSYS.png";
    private String logo_fondo = "LOGO-ESPOCH-TRANSPARENTE.png";
    private String barra = "barra.png";
    private String imagen_plani = "DPlanificacion.png";
    private String direccion = "Dirección: Panámericana Sur km 1 1/2      Teléfono:(593)32998-200 ext 1042       Código Postal:EC060155";
    private String web = "www.espoch.edu.ec                     Riobamba-Ecuador";

    public formatoReporteH(String ruta, String titulo, String logo_facultad) {
        try {
            nombre_facultad = titulo;
            imagen = Image.getInstance(ruta + logo_espoch);
            imagencimogsys = Image.getInstance(ruta + logo_cimogsys);
            imagencimogsys.scaleAbsolute(78, 18);
            imagenplani = Image.getInstance(ruta + imagen_plani);
            imagenplani.scaleAbsolute(78, 35);
            imagenfacultad = Image.getInstance(logo_facultad);
            imagenfacultad.scaleAbsoluteHeight(50);
            imagenfacultad.scaleAbsoluteWidth(50);
            barraespoch = Image.getInstance(ruta + barra);
            fondo = Image.getInstance(ruta + logo_fondo);
            imagen.setAbsolutePosition(0, 495f);
            imagenfacultad.setAbsolutePosition(695f, 525f);
            barraespoch.scaleAbsoluteWidth(570);
            barraespoch.scaleAbsoluteHeight(25);
            barraespoch.setAbsolutePosition(100f, 492f);

            //celda2.setBorder(Rectangle.BOTTOM);
            //celda2.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
        } catch (Exception r) {
            System.err.println("Error al leer la imagen");
            System.err.println("La  ruta nueva es:" + ruta + logo_espoch);
            //System.err.println(nuevopath);
        }
    }

    public void onStartPage(PdfWriter writer, Document document) {
        try {
            document.add(imagen);
            document.add(imagenfacultad);
            document.add(barraespoch);
            Font fuenteespoch = new Font();
            fuenteespoch.setSize(25);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(nombre_espoch, fuenteespoch), 152f, 552f, 0);

            Font fuentee = new Font();
            fuentee.setSize(14);
            Font fuenteinf = new Font();
            fuenteinf.setSize(11);
            fuenteinf.setStyle("bold");
            Font fuentefacultad = new Font();
            fuentefacultad.setSize(12);
            Font fuente2 = new Font();
            fuente2.setSize(9);
            fuentefacultad.setColor(BaseColor.WHITE);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(nombre_espoch2, fuentee), 281f, 528f, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(nombre_facultad, fuentefacultad), 400f, 501f, 0);

        } catch (DocumentException ex) {
            Logger.getLogger(formatoReporteH.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("encabezado:" + ex.getMessage());
        }

    }

    public void onEndPage(PdfWriter writer, Document document) {
        Font fuente = new Font(Font.FontFamily.UNDEFINED, 9);
        Font fuente2 = new Font(Font.FontFamily.TIMES_ROMAN, 11, 1);
        fondo.setAbsolutePosition(0, 0);
        imagenplani.setAbsolutePosition(660f, 17f);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(direccion, fuente), 245f, 25f, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(web, fuente), 220f, 15f, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("" + document.getPageNumber(), fuente2), 400f, 50f, 0);

        try {
            document.add(imagenplani);
            document.add(fondo);
        } catch (DocumentException ex) {
            Logger.getLogger(formatoReporteH.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("pie de página:" + ex.getMessage());

        }
    }

}
