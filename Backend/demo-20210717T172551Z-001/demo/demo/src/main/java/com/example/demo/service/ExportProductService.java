package com.example.demo.service;

import com.example.demo.models.product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ExportProductService {

    private ExportProductService() {

    }

    public static ByteArrayInputStream productsPDFExport(List<product> products){
        var document = new Document() ;
        var out = new ByteArrayOutputStream();
        try{
            PdfWriter.getInstance(document, out);
            document.open();
            var font = FontFactory.getFont(FontFactory.COURIER, 14 , BaseColor.BLACK);
            var para =new Paragraph("Products list " ,font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para) ;
            document.add(Chunk.NEWLINE);
            var table = new PdfPTable(5) ;
            Stream.of("dateCreation" , "disponible", "quantity", "nom", "DateModif").forEach(headerTitle->{
                var header = new PdfPCell();
                var headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setBorderWidth(1);
                header.setPhrase(new Phrase(headerTitle, headFont));
                table.addCell(header);
            });
            for(product prod:products){

                var datecreationcell = new PdfPCell(new Phrase(String.valueOf(prod.getDate_creation())));
                datecreationcell.setPaddingLeft(1);
                datecreationcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                datecreationcell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(datecreationcell);

                var disponiblecell = new PdfPCell(new Phrase(String.valueOf(prod.isDisponble())));
                disponiblecell.setPaddingLeft(1);
                disponiblecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                disponiblecell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(disponiblecell);

                var quantitycell = new PdfPCell(new Phrase(String.valueOf(prod.getQuantite())));
                quantitycell.setPaddingLeft(1);
                quantitycell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                quantitycell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(quantitycell);


                var nomcell = new PdfPCell(new Phrase(prod.getNom()));
                nomcell.setPaddingLeft(1);
                nomcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                nomcell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(nomcell);



                var dateModif = new PdfPCell(new Phrase(String.valueOf(prod.getDate_modif())));
                dateModif.setPaddingLeft(1);
                dateModif.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dateModif.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(dateModif);

            }
            document.add(table) ;
            document.close();
        }
        catch (DocumentException e ){
            Logger.getLogger("My Message");

        }
        return  new ByteArrayInputStream(out.toByteArray());

    }
    public static ByteArrayInputStream productsExcelExport(List<product> products) throws IOException {

        String[] columns = {"dateCreation" , "disponible", "quantity", "nom", "DateModif"};
        try(var workbook = new XSSFWorkbook();
            var out = new ByteArrayOutputStream()) {
            var creationHelper = workbook.getCreationHelper();
            var sheet = workbook.createSheet();
            sheet.autoSizeColumn(columns.length);

            var headerFont = workbook.createFont();
            headerFont.setColor(IndexedColors.BLUE1.index);
            var cellStyle = workbook.createCellStyle();
            cellStyle.setFont(headerFont);

            var headerRow = sheet.createRow(0);
            for (var col = 0; col < columns.length; col++) {
                var cell = headerRow.createCell(col);
                cell.setCellValue(columns[col]);
                cell.setCellStyle(cellStyle);
            }
            var cellStyle1 = workbook.createCellStyle();
            cellStyle1.setDataFormat(creationHelper.createDataFormat().getFormat("#"));

            var rowIndex = 1;
            for (product product : products) {
                var row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(product.getDate_creation());
                row.createCell(1).setCellValue(product.isDisponble());
                row.createCell(2).setCellValue(product.getQuantite());
                row.createCell(3).setCellValue(product.getNom());
                row.createCell(4).setCellValue(product.getDate_modif());

            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }



    }}
