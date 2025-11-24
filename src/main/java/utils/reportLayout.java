package utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import java.io.FileOutputStream;
import java.nio.file.*;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class reportLayout {

    public static void exportPDF(String reportID, String managerName, String staffName, LocalDate startDate, LocalDate endDate, List<Object[]> details) {
        String dirPath = "save_file/Báo cáo";
        String filePath = dirPath + "/BaoCao_" + reportID + ".pdf";

        try {
            Files.createDirectories(Paths.get(dirPath));

            try (Document document = new Document(PageSize.A4, 50, 50, 50, 50)) {
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();
                // ======= Font Definitions =======
                Color primaryColor = new Color(0, 102, 204); // Blue
                Color accentColor = new Color(220, 230, 250); // Light blue for header
                Color darkGray = new Color(64, 64, 64);
                Font fontTitle = new Font(Font.HELVETICA, 22, Font.BOLD, primaryColor);
                Font fontSubTitle = new Font(Font.HELVETICA, 14, Font.BOLD, darkGray);
                Font fontBold = new Font(Font.HELVETICA, 13, Font.BOLD, darkGray);
                Font fontNormal = new Font(Font.HELVETICA, 12, Font.NORMAL, darkGray);
                Font fontSmall = new Font(Font.HELVETICA, 10, Font.NORMAL, darkGray);
                // ======= 1. HEADER SECTION =======
                // Company Logo and Info
                PdfPTable headerTable = new PdfPTable(2);
                headerTable.setWidthPercentage(100);
                headerTable.setWidths(new float[]{3, 1});
                headerTable.setSpacingAfter(10f);
                // Company Info
                PdfPCell companyCell = new PdfPCell();
                companyCell.setBorder(Rectangle.NO_BORDER);
                companyCell.setPadding(5);
                Paragraph companyName = new Paragraph("CÔNG TY TNHH NHÓM 13", new Font(Font.HELVETICA, 16, Font.BOLD, primaryColor));
                companyName.setSpacingAfter(3f);
                Paragraph companyDetails = new Paragraph();
                companyDetails.add(new Chunk("Mã số thuế: 0101234567\n", fontSmall));
                companyDetails.add(new Chunk("Địa chỉ: Số 96A Trần Phú, Hà Đông, Hà Nội\n", fontSmall));
                companyDetails.add(new Chunk("Số tài khoản: 123456789 - Vietcombank", fontSmall));
                companyCell.addElement(companyName);
                companyCell.addElement(companyDetails);
                headerTable.addCell(companyCell);
                // Logo
                PdfPCell logoCell = new PdfPCell();
                logoCell.setBorder(Rectangle.NO_BORDER);
                logoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                logoCell.setPadding(5);
                try {
                    File logoFile = new File("image/logo.png");
                    if (logoFile.exists()) {
                        Image logo = Image.getInstance(logoFile.getAbsolutePath());
                        logo.scaleAbsolute(80, 80);
                        logoCell.addElement(logo);
                    } else {
                        // Placeholder if no logo
                        Paragraph placeholder = new Paragraph("[LOGO]", new Font(Font.HELVETICA, 10, Font.ITALIC, Color.GRAY));
                        placeholder.setAlignment(Element.ALIGN_RIGHT);
                        logoCell.addElement(placeholder);
                    }
                } catch (Exception e) {
                    Paragraph placeholder = new Paragraph("[LOGO]", new Font(Font.HELVETICA, 10, Font.ITALIC, Color.GRAY));
                    placeholder.setAlignment(Element.ALIGN_RIGHT);
                    logoCell.addElement(placeholder);
                }
                headerTable.addCell(logoCell);
                document.add(headerTable);
                // Decorative Line
                addSeparatorLine(document, primaryColor, 2f);
                // ======= 2. REPORT TITLE SECTION =======
                Paragraph title = new Paragraph("BÁO CÁO DOANH THU", fontTitle);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(5f);
                document.add(title);
                Paragraph reportIDPara = new Paragraph("Mã báo cáo: " + reportID, fontSubTitle);
                reportIDPara.setAlignment(Element.ALIGN_CENTER);
//                reportIDPara.setSpacingAfter(15f);
                document.add(reportIDPara);
                // ======= 3. DATE RANGE SECTION =======
                PdfPTable dateTable = new PdfPTable(2);
                dateTable.setWidthPercentage(80);
                dateTable.setHorizontalAlignment(Element.ALIGN_CENTER);
//            dateTable.setSpacingBefore(10f);
//            dateTable.setSpacingAfter(15f);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                PdfPCell startDateCell = createStyledCell("Ngày bắt đầu: " + startDate.format(dateFormat), fontNormal, Element.ALIGN_LEFT, Rectangle.NO_BORDER);
                PdfPCell endDateCell = createStyledCell("Ngày kết thúc: " + endDate.format(dateFormat), fontNormal, Element.ALIGN_RIGHT, Rectangle.NO_BORDER);
                dateTable.addCell(startDateCell);
                dateTable.addCell(endDateCell);
                document.add(dateTable);
//  add total amount order
                Paragraph orderPara = new Paragraph("Tổng số đơn hàng đã thanh toán: " + details.size(), fontSubTitle);
                orderPara.setAlignment(Element.ALIGN_CENTER);
                orderPara.setSpacingAfter(10f);
                document.add(orderPara);
// ======= 4. DATA TABLE SECTION =======
                PdfPTable table = new PdfPTable(5);
                float[] columnWidths = {1f, 3.5f, 1.5f, 1.5f, 2f};
                table.setWidths(columnWidths);
                table.setWidthPercentage(100);
                table.setSpacingBefore(5f);
//            table.setSpacingAfter(10f);
// Table Header
                String[] headers = {"STT", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ (VND)", "THÀNH TIỀN (VND)"};
                for (String header : headers) {
                    PdfPCell headerCell = createStyledCell(header, fontBold, Element.ALIGN_CENTER, Rectangle.BOX);
                    headerCell.setBackgroundColor(accentColor);
                    headerCell.setPadding(10);
                    headerCell.setPaddingTop(12);
                    headerCell.setPaddingBottom(12);
                    table.addCell(headerCell);
                }
// Table Data
                int total = 0;
                for (int i = 0; i < details.size(); i++) {
                    Object[] item = details.get(i);
                    if (item == null || item.length < 4) {
                        System.err.println("Lỗi dữ liệu: Hàng " + (i + 1) + " thiếu thông tin.");
                        continue; // Bỏ qua hàng dữ liệu lỗi
                    }

                    try {
                        int unitPrice = Integer.parseInt(item[2].toString());
                        int totalPrice = Integer.parseInt(item[3].toString());

                        table.addCell(createStyledCell(String.valueOf(i + 1), fontNormal, Element.ALIGN_CENTER, Rectangle.BOX));

                        table.addCell(createStyledCell(item[0].toString(), fontNormal, Element.ALIGN_LEFT, Rectangle.BOX));

                        table.addCell(createStyledCell(item[1].toString(), fontNormal, Element.ALIGN_RIGHT, Rectangle.BOX));

                        table.addCell(createStyledCell(String.format("%,d", unitPrice), fontNormal, Element.ALIGN_RIGHT, Rectangle.BOX));

                        table.addCell(createStyledCell(String.format("%,d", totalPrice), fontNormal, Element.ALIGN_RIGHT, Rectangle.BOX));

                        total += totalPrice;
                    } catch (NumberFormatException e) {
                        System.err.println("Lỗi định dạng số ở hàng " + (i + 1) + ": " + e.getMessage());
                        table.addCell(createStyledCell(String.valueOf(i + 1), fontNormal, Element.ALIGN_CENTER, Rectangle.BOX));
                        table.addCell(createStyledCell("[LỖI DỮ LIỆU]", fontNormal, Element.ALIGN_LEFT, Rectangle.BOX));
                        table.addCell(createStyledCell("0", fontNormal, Element.ALIGN_RIGHT, Rectangle.BOX));
                        table.addCell(createStyledCell("0", fontNormal, Element.ALIGN_RIGHT, Rectangle.BOX));
                        table.addCell(createStyledCell("0", fontNormal, Element.ALIGN_RIGHT, Rectangle.BOX));
                    }
                }

                final double TAX_RATE = 0.10;
                int subTotal = total; 
                int taxAmount = (int) Math.round(subTotal * TAX_RATE);
                int grandTotal = subTotal - taxAmount; 
// HÀNG TỔNG TIỀN TRƯỚC THUẾ
                PdfPCell subTotalEmptyCell = createStyledCell("", fontNormal, Element.ALIGN_CENTER, Rectangle.NO_BORDER);
                subTotalEmptyCell.setColspan(2); 
                table.addCell(subTotalEmptyCell);

                PdfPCell subTotalLabelCell = createStyledCell("TỔNG TIỀN:", fontBold, Element.ALIGN_RIGHT, Rectangle.NO_BORDER);
                subTotalLabelCell.setColspan(2);
                subTotalLabelCell.setPadding(10);
                table.addCell(subTotalLabelCell);

                PdfPCell subTotalValueCell = createStyledCell(String.format("%,d", subTotal), fontNormal, Element.ALIGN_RIGHT, Rectangle.NO_BORDER);
                subTotalValueCell.setPadding(10);
                table.addCell(subTotalValueCell); 
// HÀNG THUẾ (TAX 10%)
                PdfPCell taxEmptyCell = createStyledCell("", fontNormal, Element.ALIGN_CENTER, Rectangle.NO_BORDER);
                taxEmptyCell.setColspan(2); 
                table.addCell(taxEmptyCell);

                PdfPCell taxLabelCell = createStyledCell("THUẾ VAT 10%:", fontBold, Element.ALIGN_RIGHT, Rectangle.NO_BORDER);
                taxLabelCell.setColspan(2); 
                taxLabelCell.setPadding(10);
                table.addCell(taxLabelCell);

                PdfPCell taxValueCell = createStyledCell(String.format("%,d", taxAmount), fontNormal, Element.ALIGN_RIGHT, Rectangle.NO_BORDER);
                taxValueCell.setPadding(10);
                table.addCell(taxValueCell); 

// HÀNG TỔNG DOANH THU 
                Font fontGrandTotal = new Font(Font.HELVETICA, 13, Font.BOLD, primaryColor);

                PdfPCell grandTotalEmptyCell = createStyledCell("", fontNormal, Element.ALIGN_CENTER, Rectangle.NO_BORDER);
                grandTotalEmptyCell.setColspan(2); 
                table.addCell(grandTotalEmptyCell);

                PdfPCell grandTotalLabelCell = createStyledCell("TỔNG DOANH THU:", fontBold, Element.ALIGN_RIGHT, Rectangle.NO_BORDER);
                grandTotalLabelCell.setColspan(2); 
                grandTotalLabelCell.setPadding(10);
                table.addCell(grandTotalLabelCell);

                PdfPCell grandTotalValueCell = createStyledCell(String.format("%,d VND", grandTotal), fontGrandTotal, Element.ALIGN_RIGHT, Rectangle.NO_BORDER);
                grandTotalValueCell.setPadding(10);
                table.addCell(grandTotalValueCell); 

                document.add(table);
                document.add(new Paragraph("\n\n"));
                
      // ======= 5. SIGNATURE AND DATE SECTION =======
                LocalDate currentDate = LocalDate.now();
                DateTimeFormatter vietnameseFormat = DateTimeFormatter.ofPattern("'Hà Nội, Ngày 'dd' tháng 'MM' năm 'yyyy");
                Paragraph dateParagraph = new Paragraph(currentDate.format(vietnameseFormat), fontBold);
                dateParagraph.setAlignment(Element.ALIGN_RIGHT);
                dateParagraph.setSpacingAfter(10f);
                document.add(dateParagraph);                
// Signature Table
                PdfPTable signTable = new PdfPTable(2);
                signTable.setWidthPercentage(80);
                signTable.setHorizontalAlignment(Element.ALIGN_CENTER);
                signTable.setSpacingBefore(20f);
// Staff Signature
                PdfPCell staffCell = new PdfPCell();
                staffCell.setBorder(Rectangle.NO_BORDER);
                staffCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                staffCell.setPaddingRight(20f);
                Paragraph staffTitle = new Paragraph("Người làm báo cáo", fontBold);
                staffTitle.setAlignment(Element.ALIGN_CENTER);
                staffCell.addElement(staffTitle);
                Paragraph staffNamePara = new Paragraph(staffName, new Font(Font.HELVETICA, 11, Font.ITALIC, darkGray));
                staffNamePara.setAlignment(Element.ALIGN_CENTER);
                staffNamePara.setSpacingBefore(5f);
                staffCell.addElement(staffNamePara);
                signTable.addCell(staffCell);
// Manager Signature
                PdfPCell managerCell = new PdfPCell();
                managerCell.setBorder(Rectangle.NO_BORDER);
                managerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                managerCell.setPaddingLeft(20f);
                Paragraph managerTitle = new Paragraph("Quản lý", fontBold);
                managerTitle.setAlignment(Element.ALIGN_CENTER);
                managerCell.addElement(managerTitle);
                Paragraph managerNamePara = new Paragraph(managerName, new Font(Font.HELVETICA, 11, Font.ITALIC, darkGray));
                managerNamePara.setAlignment(Element.ALIGN_CENTER);
                managerNamePara.setSpacingBefore(5f);
                managerCell.addElement(managerNamePara);
                signTable.addCell(managerCell);
                document.add(signTable);
                
                
//            // ======= 6. FOOTER =======
//            Paragraph footer = new Paragraph("--- HẾT ---", new Font(Font.HELVETICA, 10, Font.ITALIC, Color.GRAY));
//            footer.setAlignment(Element.ALIGN_CENTER);
//            footer.setSpacingBefore(20f);
//            document.add(footer);
                // ======= CLOSE DOCUMENT =======
            }
            JOptionPane.showMessageDialog(null, "Đã xuất báo cáo PDF thành công!\n" + filePath);

            // Auto open the PDF file
            java.awt.Desktop.getDesktop().open(new File(filePath));

        } catch (DocumentException | HeadlessException | IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ======= HELPER METHODS =======
    private static void addSeparatorLine(Document document, Color color, float lineWidth) throws DocumentException {
        LineSeparator line = new LineSeparator();
        line.setLineColor(color);
        line.setLineWidth(lineWidth);
        document.add(new Chunk(line));
        document.add(Paragraph.getInstance("\n"));
    }

    private static void addSeparatorLine(Document document) throws DocumentException {
        addSeparatorLine(document, Color.GRAY, 1f);
    }

    private static PdfPCell createStyledCell(String content, Font font, int alignment, int border) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(border);
        cell.setPadding(8);
        cell.setPaddingTop(8);
        cell.setPaddingBottom(8);
        return cell;
    }
}
