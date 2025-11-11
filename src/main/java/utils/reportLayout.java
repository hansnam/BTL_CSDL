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

            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // ======= Font Definitions =======
            Color primaryColor = new Color(0, 102, 204); // Blue
            Color accentColor = new Color(220, 230, 250); // Light blue for header
            Color darkGray = new Color(64, 64, 64);
            Color lightGray = new Color(240, 240, 240);
            
            Font fontTitle = new Font(Font.HELVETICA, 22, Font.BOLD, primaryColor);
            Font fontSubTitle = new Font(Font.HELVETICA, 14, Font.BOLD, darkGray);
            Font fontBold = new Font(Font.HELVETICA, 11, Font.BOLD, darkGray);
            Font fontNormal = new Font(Font.HELVETICA, 11, Font.NORMAL, darkGray);
            Font fontSmall = new Font(Font.HELVETICA, 10, Font.NORMAL, darkGray);

            // ======= 1. HEADER SECTION =======
            // Company Logo and Info
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new float[]{3, 1});
            headerTable.setSpacingAfter(15f);

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
            reportIDPara.setSpacingAfter(15f);
            document.add(reportIDPara);

            // ======= 3. DATE RANGE SECTION =======
            PdfPTable dateTable = new PdfPTable(2);
            dateTable.setWidthPercentage(80);
            dateTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            dateTable.setSpacingBefore(10f);
            dateTable.setSpacingAfter(15f);

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            PdfPCell startDateCell = createStyledCell("Ngày bắt đầu: " + startDate.format(dateFormat), fontNormal, Element.ALIGN_LEFT, Rectangle.NO_BORDER);
            PdfPCell endDateCell = createStyledCell("Ngày kết thúc: " + endDate.format(dateFormat), fontNormal, Element.ALIGN_RIGHT, Rectangle.NO_BORDER);

            dateTable.addCell(startDateCell);
            dateTable.addCell(endDateCell);
            document.add(dateTable);

            // ======= 4. DATA TABLE SECTION =======
            // Create table with optimized column widths
            PdfPTable table = new PdfPTable(4);
            float[] columnWidths = {0.8f, 3.5f, 1.5f, 2f};
            table.setWidths(columnWidths);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(25f);

            // Table Header
            String[] headers = {"STT", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "THÀNH TIỀN (VND)"};
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
                
                // STT - Center aligned
                table.addCell(createStyledCell(String.valueOf(i + 1), fontNormal, Element.ALIGN_CENTER, Rectangle.BOX));
                
                // Product Name - Left aligned
                table.addCell(createStyledCell(item[0].toString(), fontNormal, Element.ALIGN_LEFT, Rectangle.BOX));
                
                // Quantity - Right aligned
                table.addCell(createStyledCell(item[1].toString(), fontNormal, Element.ALIGN_RIGHT, Rectangle.BOX));
                
                // Total Price - Right aligned with formatting
                int totalPrice = Integer.parseInt(item[2].toString());
                table.addCell(createStyledCell(String.format("%,d", totalPrice), fontNormal, Element.ALIGN_RIGHT, Rectangle.BOX));
                
                total += totalPrice;
            }

            // Total Row
            PdfPCell emptyCell = createStyledCell("", fontNormal, Element.ALIGN_CENTER, Rectangle.NO_BORDER);
            emptyCell.setColspan(2);
            table.addCell(emptyCell);

            PdfPCell totalLabelCell = createStyledCell("TỔNG CỘNG:", fontBold, Element.ALIGN_RIGHT, Rectangle.NO_BORDER);
            totalLabelCell.setPadding(10);
            table.addCell(totalLabelCell);

            PdfPCell totalValueCell = createStyledCell(String.format("%,d VND", total), new Font(Font.HELVETICA, 12, Font.BOLD, primaryColor), Element.ALIGN_RIGHT, Rectangle.NO_BORDER);
            totalValueCell.setPadding(10);
            table.addCell(totalValueCell);

            document.add(table);

            // ======= 5. SIGNATURE AND DATE SECTION =======
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter vietnameseFormat = DateTimeFormatter.ofPattern("'Ngày' dd 'tháng' MM 'năm' yyyy");
            
            Paragraph dateParagraph = new Paragraph(currentDate.format(vietnameseFormat), fontBold);
            dateParagraph.setAlignment(Element.ALIGN_RIGHT);
            dateParagraph.setSpacingAfter(30f);
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
            
            Paragraph staffTitle = new Paragraph("NHÂN VIÊN", fontBold);
            staffTitle.setAlignment(Element.ALIGN_CENTER);
            staffCell.addElement(staffTitle);
            
            Paragraph staffNamePara = new Paragraph("(" + staffName + ")", new Font(Font.HELVETICA, 11, Font.ITALIC, darkGray));
            staffNamePara.setAlignment(Element.ALIGN_CENTER);
            staffNamePara.setSpacingBefore(40f);
            staffCell.addElement(staffNamePara);
            
            signTable.addCell(staffCell);

            // Manager Signature
            PdfPCell managerCell = new PdfPCell();
            managerCell.setBorder(Rectangle.NO_BORDER);
            managerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph managerTitle = new Paragraph("QUẢN LÝ", fontBold);
            managerTitle.setAlignment(Element.ALIGN_CENTER);
            managerCell.addElement(managerTitle);
            
            Paragraph managerNamePara = new Paragraph("(" + managerName + ")", new Font(Font.HELVETICA, 11, Font.ITALIC, darkGray));
            managerNamePara.setAlignment(Element.ALIGN_CENTER);
            managerNamePara.setSpacingBefore(40f);
            managerCell.addElement(managerNamePara);
            
            signTable.addCell(managerCell);

            document.add(signTable);

            // ======= 6. FOOTER =======
            Paragraph footer = new Paragraph("--- HẾT ---", new Font(Font.HELVETICA, 10, Font.ITALIC, Color.GRAY));
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setSpacingBefore(30f);
            document.add(footer);

            // ======= CLOSE DOCUMENT =======
            document.close();
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
        cell.setPaddingTop(10);
        cell.setPaddingBottom(10);
        return cell;
    }
}
