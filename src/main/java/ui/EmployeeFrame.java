
package ui;

import dao.ManagerModify;
import dao.StaffModify;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Manager;
import models.Staff;
import utils.CustomizeHeaderTable;

public class EmployeeFrame extends javax.swing.JFrame {

    DefaultTableModel tableModelS;
    List<Staff> Slist;
    int SPos = -1;
    
    DefaultTableModel tableModelM;
    List<Manager> Mlist;
    int MPos = -1;

    public EmployeeFrame() {
        initComponents();
        setLocationRelativeTo(null);
        CustomizeHeaderTable.customizeTableHeader(staffTable);
        CustomizeHeaderTable.customizeTableHeader(managerTable);
        
        tableModelS = (DefaultTableModel) staffTable.getModel();
        Slist = StaffModify.getStaffList(null);
        showDataS();
        
        tableModelM = (DefaultTableModel) managerTable.getModel();
        Mlist = ManagerModify.getManagerList(null);
        showDataM();
    }
    
    private void showDataS() {
        tableModelS.setRowCount(0);
        for(Staff s : Slist) {
            tableModelS.addRow(new Object[] {
                tableModelS.getRowCount() + 1,
                s.getID(),
                s.getName(),
                s.getGender(),
                s.getPhone(),
                s.getEmail(),
                s.getSalary(),
                s.getHireDate()
            });
        }
    }
    
    private void showDataM() {
        tableModelM.setRowCount(0);
        for(Manager m : Mlist) {
            tableModelM.addRow(new Object[] {
                tableModelM.getRowCount() + 1,
                m.getID(),
                m.getName(),
                m.getGender(),
                m.getTitle(),
                m.getPhone(),
                m.getEmail(),
                m.getSalary()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        staffTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        managerTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("THÔNG TIN NHÂN SỰ");

        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        staffTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        staffTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nhân sự", "Họ và tên", "Giới tính", "Số điện thoại", "Email", "Lương", "Ngày vào làm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        staffTable.setRowHeight(22);
        jScrollPane1.setViewportView(staffTable);
        if (staffTable.getColumnModel().getColumnCount() > 0) {
            staffTable.getColumnModel().getColumn(0).setMinWidth(40);
            staffTable.getColumnModel().getColumn(0).setMaxWidth(100);
            staffTable.getColumnModel().getColumn(3).setMinWidth(50);
            staffTable.getColumnModel().getColumn(3).setMaxWidth(100);
            staffTable.getColumnModel().getColumn(5).setMinWidth(150);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Thông tin nhân viên", jPanel2);

        managerTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        managerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nhân sự", "Họ và tên", "Giới tính", "Chức vụ", "Số điện thoại", "Email", "Lương"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        managerTable.setRowHeight(22);
        jScrollPane2.setViewportView(managerTable);
        if (managerTable.getColumnModel().getColumnCount() > 0) {
            managerTable.getColumnModel().getColumn(0).setMinWidth(40);
            managerTable.getColumnModel().getColumn(0).setMaxWidth(100);
            managerTable.getColumnModel().getColumn(3).setMinWidth(50);
            managerTable.getColumnModel().getColumn(3).setMaxWidth(100);
            managerTable.getColumnModel().getColumn(4).setMinWidth(100);
            managerTable.getColumnModel().getColumn(6).setMinWidth(150);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Thông tin quản lý", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable managerTable;
    private javax.swing.JTable staffTable;
    // End of variables declaration//GEN-END:variables
}
