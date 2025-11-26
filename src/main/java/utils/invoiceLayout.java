package utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import com.lowagie.text.pdf.draw.VerticalPositionMark;
import java.io.FileOutputStream;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;

public class invoiceLayout {

    public static void exportPDF(String orderId, String customerId, String customerName, String address, String orderDate, List<Object[]> details) {
        String dirPath = "save_file/Hoá Đơn";
        String filePath = dirPath + "/HoaDon_" + orderId + ".pdf";

        try {
            Files.createDirectories(Paths.get(dirPath));

            Document document = new Document(PageSize.A4, 40, 40, 40, 40);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // ======= Font =======
            //Font fontTitle = new Font(Font.HELVETICA, 20, Font.BOLD, new Color(0, 102, 204));
            Font fontTitle = new Font(Font.HELVETICA, 20, Font.BOLD, Color.BLUE);

            Font fontSub = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font fontNormal = new Font(Font.HELVETICA, 11);
            Font fontBold = new Font(Font.HELVETICA, 11, Font.BOLD);

            // ======= 1. TIÊU ĐỀ =======
            Paragraph title = new Paragraph("HOÁ ĐƠN BÁN HÀNG", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Font fontOrderId = new Font(Font.HELVETICA, 12, Font.BOLD, Color.BLACK);
            Paragraph orderIdPara = new Paragraph("Mã đơn hàng: " + orderId, fontOrderId);
            orderIdPara.setAlignment(Element.ALIGN_CENTER);
            orderIdPara.setSpacingAfter(10f); // khoảng cách bên dưới
            document.add(orderIdPara);

            addSeparatorLine(document);

            // ======= 2. THÔNG TIN CÔNG TY (companyTable) =======
            PdfPTable companyTable = new PdfPTable(2);
            companyTable.setWidthPercentage(100);
            companyTable.setWidths(new float[]{6, 2});

            PdfPCell companyInfo = new PdfPCell();
            companyInfo.setBorder(Rectangle.NO_BORDER);
            companyInfo.addElement(new Paragraph("CÔNG TY TNHH NHÓM 13", fontSub));
            companyInfo.addElement(new Paragraph("Mã số thuế: 0101234567", fontNormal));
            companyInfo.addElement(new Paragraph("Địa chỉ: Số 96A Trần Phú, Hà Đông, Hà Nội", fontNormal));
            companyInfo.addElement(new Paragraph("Số tài khoản: 123456789 - Vietcombank", fontNormal));
            companyTable.addCell(companyInfo);

            // Logo góc phải — nếu không tìm thấy logo sẽ thêm ô rỗng (không lỗi)
            try {
                File logoFile = new File("image/logo.png");
                if (logoFile.exists()) {
                    Image logo = Image.getInstance(logoFile.getAbsolutePath());
                    logo.scaleAbsolute(70, 70);
                    PdfPCell logoCell = new PdfPCell(logo, false);
                    logoCell.setBorder(Rectangle.NO_BORDER);
                    logoCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    logoCell.setPaddingRight(20f);
                    companyTable.addCell(logoCell);
                } else {
                    PdfPCell logoCell = new PdfPCell(new Phrase(" ", fontNormal));
                    logoCell.setBorder(Rectangle.NO_BORDER);
                    companyTable.addCell(logoCell);
                }
            } catch (BadElementException | IOException e) {
                // Nếu có lỗi khi load logo vẫn không làm crash — thêm ô rỗng
                PdfPCell logoCell = new PdfPCell(new Phrase(" ", fontNormal));
                logoCell.setBorder(Rectangle.NO_BORDER);
                companyTable.addCell(logoCell);
            }
            document.add(companyTable);

            // Dòng kẻ 2
            addSeparatorLine(document);

            // ======= 3. THÔNG TIN KHÁCH HÀNG =======
            PdfPTable infoTable = new PdfPTable(1);
            infoTable.setWidthPercentage(100);
            //infoTable.setWidths(new float[]{2.5f, 5f});

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
            String formattedOrderDate;

            try {
                LocalDateTime dateTime = LocalDateTime.parse(orderDate, inputFormatter);

                formattedOrderDate = dateTime.format(outputFormatter);
            } catch (Exception e) {
                formattedOrderDate = orderDate;
                System.err.println("Lỗi định dạng ngày đặt hàng: " + e.getMessage());
            }

            // infoTable.addCell(createNoBorderCell("Mã đơn hàng: " + orderId, fontNormal));
            infoTable.addCell(createNoBorderCell("Mã khách hàng: " + customerId, fontNormal));
            infoTable.addCell(createNoBorderCell("Tên khách hàng: " + customerName, fontNormal));
            infoTable.addCell(createNoBorderCell("Hình thức thanh toán: Chuyển khoản", fontNormal));
            infoTable.addCell(createNoBorderCell("Ngày đặt hàng: " + formattedOrderDate, fontNormal));
            infoTable.addCell(createNoBorderCell("Ngày xuất hoá đơn: " + LocalDateTime.now().format(formatter), fontNormal));
            infoTable.addCell(createNoBorderCell("Địa chỉ giao hàng: " + address, fontNormal));

            document.add(infoTable);

            // Dòng kẻ 3
            addSeparatorLine(document);

            // ======= 4. BẢNG SẢN PHẨM =======
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 3, 2, 2, 2});

            String[] headers = {"STT", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
            for (String headerTitle : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(headerTitle, fontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(220, 230, 250));
                cell.setPadding(6);
                table.addCell(cell);
            }

            int total = 0;
            for (int i = 0; i < details.size(); i++) {
                Object[] item = details.get(i);
                table.addCell(new Phrase(String.valueOf(i + 1), fontNormal));
                table.addCell(new Phrase(item[1].toString(), fontNormal));
                table.addCell(new Phrase(item[3].toString(), fontNormal));
                table.addCell(new Phrase(item[2].toString(), fontNormal));
                table.addCell(new Phrase(item[4].toString(), fontNormal));

                total += Integer.parseInt(item[4].toString());
            }

            // Thêm hàng tổng tiền
            PdfPCell totalLabel = new PdfPCell(new Phrase("TỔNG CỘNG", fontBold));
            totalLabel.setColspan(4);
            totalLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalLabel.setPadding(6);
            totalLabel.setBorder(Rectangle.NO_BORDER);
            table.addCell(totalLabel);

            PdfPCell totalValue = new PdfPCell(new Phrase(String.format("%,d VND", total), fontBold));
            totalValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalValue.setPadding(6);
            totalValue.setBorder(Rectangle.NO_BORDER);
            table.addCell(totalValue);

            document.add(table);
            document.add(new Paragraph("\n\n"));

            // ======= 6. LỜI CẢM ƠN =======
            Paragraph thank = new Paragraph("Xin cảm ơn quý khách!",
            new Font(Font.HELVETICA, 18, Font.ITALIC, Color.BLACK));

            thank.setAlignment(Element.ALIGN_CENTER);

            document.add(Chunk.NEWLINE);
            document.add(new Chunk(new VerticalPositionMark()));
            document.add(thank);

            // ======= KẾT THÚC =======
            document.close();
            JOptionPane.showMessageDialog(null, "Đã xuất hoá đơn PDF thành công!\n" + filePath);
            java.awt.Desktop.getDesktop().open(new File(filePath));

        } catch (DocumentException | HeadlessException | IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ======= HÀM PHỤ =======
    private static void addSeparatorLine(Document document) throws DocumentException {
        LineSeparator line = new LineSeparator();
        line.setLineColor(Color.GRAY);
        line.setLineWidth(1f);
        document.add(new Chunk(line));
        document.add(new Paragraph("\n"));
    }

    private static PdfPCell createNoBorderCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }
}
