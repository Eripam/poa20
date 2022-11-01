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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PLANIFICACION
 */
public class formatoReporteV extends PdfPageEventHelper {

    private Image imagen;
    private Image barraespoch;
    private Image fondo;
    private String nombre_facultad;
    private Image imagenfacultad;
    private String nombre_espoch = "ESPOCH";
    private String nombre_espoch2 = "ESCUELA SUPERIOR POLITÉCNICA DE CHIMBORAZO";
    private String logo_espoch = "LOGO-ESPOCH.png";
    private String logo_fondo = "LOGO-ESPOCH-TRANSPARENTE.png";
    private String barra = "barra.png";
    private String direccion = "Dirección: Panámericana Sur km 1 1/2";
    private String telefono="Teléfono:(593)32998-200 ext 1042";
    private String codigo="Código Postal:EC060155";
    private String web = "www.espoch.edu.ec";
    private String ciudad = "Riobamba-Ecuador";
    private String correo = "planificacion@espoch.edu.ec";

    public formatoReporteV(String ruta, String titulo, String logo_facultad) {
        try {
            nombre_facultad = titulo;
            imagen = Image.getInstance(ruta + logo_espoch);
            imagen.scaleAbsoluteHeight(100);
            imagen.setAbsolutePosition(0, 692f);
            imagenfacultad = Image.getInstance(logo_facultad);
            imagenfacultad.scaleAbsoluteHeight(40);
            imagenfacultad.scaleAbsoluteWidth(40);
            imagenfacultad.setAbsolutePosition(525f, 725f);
            fondo = Image.getInstance(ruta + logo_fondo);
            barraespoch = Image.getInstance(ruta + barra);
            barraespoch.scaleAbsoluteWidth(435);
            barraespoch.scaleAbsoluteHeight(25);
            barraespoch.setAbsolutePosition(95, 690f);
            //celda2.setBorder(Rectangle.BOTTOM);
            //celda2.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
        } catch (Exception r) {
            System.err.println("Error al leer la imagen");
            System.err.println("La  ruta nueva es:" + ruta + logo_espoch);
            //System.err.println(nuevopath);
        }
    }

    /**
     * Manejador del evento onEndPage, usado para generar el encabezado
     *
     */
    public void onStartPage(PdfWriter writer, Document document) {
        try {
            document.add(imagen);
            document.add(barraespoch);
            document.add(imagenfacultad);
            Font fuenteespoch = new Font();
            fuenteespoch.setSize(20);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(nombre_espoch, fuenteespoch), 140f, 750f, 0);

            Font fuentee = new Font();
            fuentee.setSize(12);
            Font fuenteinf = new Font();
            fuenteinf.setSize(11);
            fuenteinf.setStyle("bold");
            Font fuentefacultad = new Font();
            fuentefacultad.setSize(12);
            Font fuente2 = new Font();
            fuente2.setSize(9);
            fuentefacultad.setColor(BaseColor.WHITE);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(nombre_espoch2, fuentee), 252f, 730f, 0);
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(nombre_facultad, fuentefacultad), 270f, 698f, 0);
        } catch (DocumentException ex) {
            Logger.getLogger(formatoReporteV.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("encabezao:" + ex.getMessage());
        }
    }

    public void onEndPage(PdfWriter writer, Document document) {
        Font fuente = new Font(Font.FontFamily.UNDEFINED, 8);
        Font fuente2 = new Font(Font.FontFamily.TIMES_ROMAN, 11, 1);
        fondo.setAbsolutePosition(0, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(" " + document.getPageNumber(), fuente2), 300f, 45f, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(telefono, fuente), 310f, 25f, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(direccion, fuente), 120f, 25f, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(codigo, fuente), 500f, 25f, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(correo, fuente), 310f, 15f, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(web, fuente), 120f, 15f, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(ciudad, fuente), 500f, 15f, 0);
        try {
            document.add(fondo);
        } catch (DocumentException ex) {
            Logger.getLogger(formatoReporteV.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("pie de página:" + ex.getMessage());
        }
    }

}
