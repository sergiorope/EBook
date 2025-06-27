package Control.controlPedido;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class FacturaPDF {

    public static void main(String[] args) {
        try (PDDocument documento = new PDDocument()) {
            PDPage pagina = new PDPage();
            documento.addPage(pagina);

            PDPageContentStream contenido = new PDPageContentStream(documento, pagina);
            contenido.setFont(PDType1Font.HELVETICA, 12);

            // Dibujar la imagen fuera del bloque de texto
            PDImageXObject logo = PDImageXObject.createFromFile("F:/TiendaCliente/src/main/resources/img/logo.png", documento);
            contenido.drawImage(logo, -50, 680, logo.getWidth() / 8, logo.getHeight() / 8);
            
            contenido.setFont(PDType1Font.HELVETICA_BOLD, 24); 
            contenido.beginText();
            contenido.newLineAtOffset(480, 765); // Ajustar la posición de acuerdo a la nueva fuente
            contenido.showText("FACTURA"); // Mostrar la palabra "Factura"
            contenido.newLine();
            contenido.endText();
            
          

            

            contenido.beginText();
            contenido.setFont(PDType1Font.HELVETICA, 12);
            contenido.newLineAtOffset(100, 750);
            contenido.setLeading(14.5f);

            // Información de la empresa
            contenido.newLineAtOffset(30, -50);
            contenido.showText("Tienda S.A");
            contenido.newLine();
            contenido.showText("CIF: " + "43434434");
            contenido.newLine();
            contenido.showText("Dirección: " + "ejemplo");
            contenido.newLineAtOffset(120, 42);
            contenido.newLine();
            contenido.showText("Provincia: " + "Zamora");
            contenido.newLine();
            contenido.showText("Teléfono: " + "978263273");
            contenido.newLine();
            contenido.showText("Destinatario: " + "pepe" + " " + "garcia");
            contenido.newLine();
            contenido.newLine();

            contenido.newLineAtOffset(-120, -10);
            contenido.showText("Factura de compra:");
            contenido.newLine();
            contenido.endText();

            // Dibujar una línea sólida
            contenido.moveTo(100, 625);
            contenido.lineTo(600, 625);
            contenido.setLineWidth(1); // Grosor de la línea
            contenido.stroke();

            contenido.beginText();
            contenido.newLineAtOffset(120, 590);
            contenido.setLeading(14.5f);// Comenzar un nuevo bloque de texto

            contenido.showText("Servicio de compra");          
            contenido.endText();
            
            
            contenido.beginText();
            contenido.newLineAtOffset(400, 600);
            contenido.setLeading(14.5f);// Comenzar un nuevo bloque de texto

            contenido.showText("libro");
            contenido.newLine();
            contenido.showText("Unidades: " + "4");
            contenido.showText("Total: " + "124€");
            contenido.newLine();
            contenido.newLine();
            contenido.endText();
            
        
          
            contenido.moveTo(100, 570);
            contenido.lineTo(600, 570);
            contenido.setLineWidth(1); // Grosor de la línea
            contenido.stroke();

            contenido.beginText();
            contenido.newLineAtOffset(400, 550);
            contenido.setLeading(14.5f);
            contenido.newLine();
            contenido.showText("Total Pedido: " + "129 (IVA incluido)");
            contenido.newLine();

            contenido.endText();
            
            PDImageXObject codebar = PDImageXObject.createFromFile("F:/TiendaCliente/src/main/resources/img/codebar.png", documento);
            contenido.drawImage(codebar, 240, 1, codebar.getWidth() / 5, codebar.getHeight() / 5);
            contenido.close();

            documento.save("ejemplo.pdf");
            System.out.println("Factura generada correctamente como ejemplo.pdf");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
