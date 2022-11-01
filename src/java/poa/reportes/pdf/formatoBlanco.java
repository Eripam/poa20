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
public class formatoBlanco extends PdfPageEventHelper {

    private String nombre_espoch2 = "ESCUELA SUPERIOR POLITÉCNICA DE CHIMBORAZO";

    public formatoBlanco() {
      
    }

    /**
     * Manejador del evento onEndPage, usado para generar el encabezado
     *
     */
    public void onStartPage(PdfWriter writer, Document document) {
        Font fuenteespoch = new Font();
        fuenteespoch.setSize(20);
        Font fuentee = new Font();
        fuentee.setSize(12);
        fuentee.setColor(BaseColor.WHITE);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase(nombre_espoch2, fuentee), 252f, 730f, 0);
    }

}
