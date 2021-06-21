package oga.stage.product_management.services;


import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import oga.stage.product_management.entities.Product;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ExportProductService {

    public static ByteArrayInputStream productsPDFExport(List<Product> products){
        Document document = new Document() ;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            PdfWriter.getInstance(document, out);
            document.open();
            com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 14 , BaseColor.BLACK);
            Paragraph para =new Paragraph("Products list " ,font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para) ;
            document.add(Chunk.NEWLINE);
            PdfPTable table = new PdfPTable(5) ;
            Stream.of("dateCreation" , "disponible", "quantity", "nom", "DateModif").forEach(headerTitle->{
                    PdfPCell header = new PdfPCell();
                    com.itextpdf.text.Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(headerTitle, headFont));
                    table.addCell(header);
            });
            for(Product prod:products){
              /*  PdfPCell idcell = new PdfPCell(new Phrase(prod.getId()));
                idcell.setPaddingLeft(1);
                idcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idcell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(idcell);*/


                PdfPCell datecreationcell = new PdfPCell(new Phrase(String.valueOf(prod.getDateCreation())));
                datecreationcell.setPaddingLeft(1);
                datecreationcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                datecreationcell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(datecreationcell);

                PdfPCell disponiblecell = new PdfPCell(new Phrase(String.valueOf(prod.isDisponible())));
                disponiblecell.setPaddingLeft(1);
                disponiblecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                disponiblecell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(disponiblecell);

                PdfPCell quantitycell = new PdfPCell(new Phrase(String.valueOf(prod.getQuantity())));
                quantitycell.setPaddingLeft(1);
                quantitycell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                quantitycell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(quantitycell);


                PdfPCell nomcell = new PdfPCell(new Phrase(prod.getNom()));
                nomcell.setPaddingLeft(1);
                nomcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                nomcell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(nomcell);



                PdfPCell DateModifcell = new PdfPCell(new Phrase(String.valueOf(prod.getDateModif())));
                DateModifcell.setPaddingLeft(1);
                DateModifcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                DateModifcell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(DateModifcell);

            }
            document.add(table) ;
            document.close();
        }
        catch (DocumentException e ){
e.printStackTrace();
        }
        return  new ByteArrayInputStream(out.toByteArray());

    }
    public static ByteArrayInputStream productsExcelExport(List<Product> products) throws IOException {

          String[] columns = {"dateCreation" , "disponible", "quantity", "nom", "DateModif"};
          try(Workbook workbook = new XSSFWorkbook();
              ByteArrayOutputStream out = new ByteArrayOutputStream();) {
              CreationHelper creationHelper = workbook.getCreationHelper();
              Sheet sheet = workbook.createSheet();
              sheet.autoSizeColumn(columns.length);

              System.out.println("heloooooo");
               org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
               headerFont.setColor(IndexedColors.BLUE1.index);
              CellStyle cellStyle = workbook.createCellStyle();
              cellStyle.setFont((org.apache.poi.ss.usermodel.Font) headerFont);

              Row headerRow = sheet.createRow(0);
              for (int col = 0; col < columns.length; col++) {
                  Cell cell = headerRow.createCell(col);
                  cell.setCellValue(columns[col]);
                  cell.setCellStyle(cellStyle);
              }
              CellStyle cellStyle1 = workbook.createCellStyle();
              cellStyle1.setDataFormat(creationHelper.createDataFormat().getFormat("#"));

              int rowIndex = 1;
              for (Product product : products) {
                  Row row = sheet.createRow(rowIndex++);
                  row.createCell(0).setCellValue(product.getDateCreation());
                  row.createCell(1).setCellValue(product.isDisponible());
                  row.createCell(2).setCellValue(product.getQuantity());
                  row.createCell(3).setCellValue(product.getNom());
                  row.createCell(4).setCellValue(product.getDateModif());

              }
              workbook.write(out);
              return new ByteArrayInputStream(out.toByteArray());

          }


    }
}
