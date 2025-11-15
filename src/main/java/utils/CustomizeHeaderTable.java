
package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class CustomizeHeaderTable {
    
    public static void customizeTableHeader(JTable table) {
        if (table == null) return;
        int fontSize = 14;
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, fontSize));
        header.setBackground(Color.WHITE);

//        // Căn giữa tiêu đề
//        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
//        renderer.setHorizontalAlignment(JLabel.CENTER);
//        table.getTableHeader().setDefaultRenderer(renderer);

        // Tăng chiều cao header
        header.setPreferredSize(new Dimension(header.getWidth(), fontSize + 20));
        
//        table.setShowGrid(true); // Giữ tất cả đường kẻ (cả dọc và ngang)
    }
}
