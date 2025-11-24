
package ui;

import dao.CorporateCustomerModify;
import dao.IndividualCustomerModify;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.CorporateCus;
import models.IndividualCus;
import utils.CustomizeHeaderTable;

public class CustomerFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomerFrame.class.getName());
    
    
    DefaultTableModel tableModelIC;
    List<IndividualCus> IClist;
    int ICPos = -1;
    
    DefaultTableModel tableModelCC;
    List<CorporateCus> CClist;
    int CCPos = -1;

    public CustomerFrame() {
        initComponents();
        setLocationRelativeTo(null);
        CustomizeHeaderTable.customizeTableHeader(ICTable);
        CustomizeHeaderTable.customizeTableHeader(CCTable);
        
        tableModelIC = (DefaultTableModel) ICTable.getModel();
        IClist = IndividualCustomerModify.getIndividualCusList(null);
        showDataIC();
        
        ICTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ICPos = ICTable.getSelectedRow();
                
                cusIDTxt.setText(IClist.get(ICPos).getCustomerID());
                nameTxt.setText(IClist.get(ICPos).getICName());
                genderTxt.setText(IClist.get(ICPos).getGender());
                phoneTxt.setText(IClist.get(ICPos).getPhone());
                emailTxt.setText(IClist.get(ICPos).getEmail());
                addressTxt.setText(IClist.get(ICPos).getAddress());
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
            
        });
        
        
        tableModelCC = (DefaultTableModel) CCTable.getModel();
        CClist = CorporateCustomerModify.getCorporCusList(null);
        showDataCC();
        
        CCTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CCPos = CCTable.getSelectedRow();

                CusIDTxt.setText(CClist.get(CCPos).getCustomerID());
                companyNameTxt.setText(CClist.get(CCPos).getCompanyName());
                taxCodeTxt.setText(CClist.get(CCPos).getTaxCode());
                contactPersonTxt.setText(CClist.get(CCPos).getContactPerson());
                CphoneTxt.setText(CClist.get(CCPos).getPhone());
                CemailTxt.setText(CClist.get(CCPos).getEmail());
                CaddressTxt.setText(CClist.get(CCPos).getAddress());
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });            
    }
    
    private void showDataIC() {
        tableModelIC.setRowCount(0);
        for(IndividualCus ic : IClist) {
            tableModelIC.addRow(new Object[] {
                tableModelIC.getRowCount() + 1,
                ic.getCustomerID(),
                ic.getICName(),
                ic.getGender(),
                ic.getPhone(),
                ic.getEmail(),
                ic.getAddress()
            });
        }
    }
    
    private void showDataCC() {
        tableModelCC.setRowCount(0);
        for(CorporateCus cc : CClist) {
            tableModelCC.addRow(new Object[]{
                tableModelCC.getRowCount() + 1,
                cc.getCustomerID(),
                cc.getCompanyName(),
                cc.getTaxCode(),
                cc.getContactPerson(),
                cc.getPhone(),
                cc.getEmail(),
                cc.getAddress()
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        delBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cusIDTxt = new javax.swing.JTextField();
        nameTxt = new javax.swing.JTextField();
        genderTxt = new javax.swing.JTextField();
        phoneTxt = new javax.swing.JTextField();
        emailTxt = new javax.swing.JTextField();
        addressTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ICTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        CusIDTxt = new javax.swing.JTextField();
        taxCodeTxt = new javax.swing.JTextField();
        companyNameTxt = new javax.swing.JTextField();
        contactPersonTxt = new javax.swing.JTextField();
        CphoneTxt = new javax.swing.JTextField();
        CemailTxt = new javax.swing.JTextField();
        CaddressTxt = new javax.swing.JTextField();
        SaveBtn = new javax.swing.JButton();
        SearchBtn = new javax.swing.JButton();
        DelBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        CCTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ KHÁCH HÀNG\n");

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        saveBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        saveBtn.setText("Lưu");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        searchBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        searchBtn.setText("Tìm Kiếm");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        delBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        delBtn.setText("Xoá");
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã khách hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Họ và tên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Giới tính");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Email");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Địa chỉ");

        cusIDTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        nameTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        genderTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        phoneTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        phoneTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneTxtActionPerformed(evt);
            }
        });

        emailTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        addressTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ICTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ICTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã khách hàng", "Họ và tên", "Giới tính", "Số điện thoại", "Email", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ICTable.setRowHeight(22);
        jScrollPane1.setViewportView(ICTable);
        if (ICTable.getColumnModel().getColumnCount() > 0) {
            ICTable.getColumnModel().getColumn(0).setMinWidth(40);
            ICTable.getColumnModel().getColumn(0).setMaxWidth(100);
            ICTable.getColumnModel().getColumn(1).setMinWidth(120);
            ICTable.getColumnModel().getColumn(1).setMaxWidth(200);
            ICTable.getColumnModel().getColumn(2).setMinWidth(150);
            ICTable.getColumnModel().getColumn(2).setMaxWidth(200);
            ICTable.getColumnModel().getColumn(3).setMinWidth(50);
            ICTable.getColumnModel().getColumn(3).setMaxWidth(100);
            ICTable.getColumnModel().getColumn(4).setMinWidth(120);
            ICTable.getColumnModel().getColumn(4).setMaxWidth(200);
            ICTable.getColumnModel().getColumn(5).setMinWidth(150);
            ICTable.getColumnModel().getColumn(6).setMinWidth(250);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                            .addComponent(emailTxt)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneTxt)
                            .addComponent(genderTxt)
                            .addComponent(nameTxt)
                            .addComponent(cusIDTxt))))
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(delBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cusIDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(genderTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("KHÁCH HÀNG CÁ NHÂN", jPanel1);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mã khách hàng");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Địa chỉ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Tên doanh nghiệp");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Mã số thuế");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Người đại diện");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Số điện thoại");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Email");

        CusIDTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        taxCodeTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        companyNameTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        contactPersonTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        CphoneTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        CemailTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CemailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CemailTxtActionPerformed(evt);
            }
        });

        CaddressTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        SaveBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SaveBtn.setText("Lưu");
        SaveBtn.setMaximumSize(new java.awt.Dimension(80, 30));
        SaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveBtnActionPerformed(evt);
            }
        });

        SearchBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SearchBtn.setText("Tìm kiếm");
        SearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtnActionPerformed(evt);
            }
        });

        DelBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DelBtn.setText("Xoá");
        DelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelBtnActionPerformed(evt);
            }
        });

        CCTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CCTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã khách hàng", "Tên doanh nghiệp", "Mã số thuế", "Người đại diện", "Số điện thoại", "Email", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CCTable.setRowHeight(22);
        jScrollPane2.setViewportView(CCTable);
        if (CCTable.getColumnModel().getColumnCount() > 0) {
            CCTable.getColumnModel().getColumn(0).setMinWidth(40);
            CCTable.getColumnModel().getColumn(0).setMaxWidth(50);
            CCTable.getColumnModel().getColumn(1).setMinWidth(120);
            CCTable.getColumnModel().getColumn(1).setMaxWidth(150);
            CCTable.getColumnModel().getColumn(2).setMinWidth(150);
            CCTable.getColumnModel().getColumn(3).setMinWidth(120);
            CCTable.getColumnModel().getColumn(3).setMaxWidth(160);
            CCTable.getColumnModel().getColumn(4).setMinWidth(150);
            CCTable.getColumnModel().getColumn(4).setMaxWidth(200);
            CCTable.getColumnModel().getColumn(5).setMinWidth(120);
            CCTable.getColumnModel().getColumn(5).setMaxWidth(200);
            CCTable.getColumnModel().getColumn(6).setMinWidth(200);
            CCTable.getColumnModel().getColumn(6).setMaxWidth(250);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CusIDTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                    .addComponent(companyNameTxt)
                    .addComponent(taxCodeTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(contactPersonTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CphoneTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CemailTxt)
                    .addComponent(CaddressTxt, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(94, 94, 94)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(SaveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SearchBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(149, 149, 149))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CusIDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(companyNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(taxCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contactPersonTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CphoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CemailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CaddressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(SearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(DelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("KHÁCH HÀNG DOANH NGHIỆP", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 813, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void phoneTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneTxtActionPerformed

        
    }//GEN-LAST:event_phoneTxtActionPerformed
                                    
    private void CemailTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CemailTxtActionPerformed

        
    }//GEN-LAST:event_CemailTxtActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        if (ICPos >= 0) {
            IClist.get(ICPos).setCustomerID(cusIDTxt.getText());
            IClist.get(ICPos).setICName(nameTxt.getText());
            IClist.get(ICPos).setICGender(genderTxt.getText());
            IClist.get(ICPos).setPhone(phoneTxt.getText());
            IClist.get(ICPos).setEmail(emailTxt.getText());
            IClist.get(ICPos).setAddress(addressTxt.getText());
            
            IndividualCustomerModify.update(IClist.get(ICPos));
            ICPos = -1;
        }
        else {
            IndividualCus ic = new IndividualCus(
                cusIDTxt.getText(),
                nameTxt.getText(),
                genderTxt.getText(),
                phoneTxt.getText(),
                emailTxt.getText(),
                addressTxt.getText()

            );
            IndividualCustomerModify.insert(ic);
            IClist = IndividualCustomerModify.getIndividualCusList(null);
        }
        showDataIC();
        cusIDTxt.setText("");
        nameTxt.setText("");
        genderTxt.setText("");
        phoneTxt.setText("");
        emailTxt.setText("");
        addressTxt.setText("");
    }//GEN-LAST:event_saveBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String s = JOptionPane.showInputDialog("Nhập tên khách hàng cần tìm");
        if(!s.isEmpty()) {
            s = "%"+s+"%";
        }
        IClist = IndividualCustomerModify.getIndividualCusList(s);
        showDataIC();
    }//GEN-LAST:event_searchBtnActionPerformed

    private void SaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveBtnActionPerformed
        if(CCPos >= 0) {
            CClist.get(CCPos).setCustomerID(CusIDTxt.getText());
            CClist.get(CCPos).setCompanyName(companyNameTxt.getText());
            CClist.get(CCPos).setTaxCode(taxCodeTxt.getText());
            CClist.get(CCPos).setContactPerson(contactPersonTxt.getText());
            CClist.get(CCPos).setPhone(CphoneTxt.getText());
            CClist.get(CCPos).setEmail(CemailTxt.getText());
            CClist.get(CCPos).setAddress(CaddressTxt.getText());
            
            CorporateCustomerModify.update(CClist.get(CCPos));
            CCPos = -1;
        }
        else {
            CorporateCus cc = new CorporateCus(
                CusIDTxt.getText(),
                companyNameTxt.getText(),
                taxCodeTxt.getText(),
                contactPersonTxt.getText(),
                CphoneTxt.getText(),
                CemailTxt.getText(),
                CaddressTxt.getText()

            );
            CorporateCustomerModify.insert(cc);
            CClist = CorporateCustomerModify.getCorporCusList(null);
        }
        showDataCC();
        CusIDTxt.setText("");
        companyNameTxt.setText("");
        taxCodeTxt.setText("");
        contactPersonTxt.setText("");
        CphoneTxt.setText("");
        CemailTxt.setText("");
        CaddressTxt.setText("");
    }//GEN-LAST:event_SaveBtnActionPerformed

    private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed

        if (ICPos == -1) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn khách hàng cần xoá, vui lòng kiểm tra lại!");
        }
        int option = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn xoá khách hàng này chứ?");
        if(option == 0) {
            IndividualCustomerModify.delete(IClist.get(ICPos).getCustomerID());
            ICPos = -1;
            IClist = IndividualCustomerModify.getIndividualCusList(null);
            showDataIC();
        }
        cusIDTxt.setText("");
        nameTxt.setText("");
        genderTxt.setText("");
        phoneTxt.setText("");
        emailTxt.setText("");
        addressTxt.setText("");
    }//GEN-LAST:event_delBtnActionPerformed

    private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtnActionPerformed
        String s = JOptionPane.showInputDialog("Nhập tên doanh nghiệp cần tìm");
        if(!s.isEmpty()) {
            s = "%"+s+"%";
        }
        CClist = CorporateCustomerModify.getCorporCusList(s);
        showDataCC();
    }//GEN-LAST:event_SearchBtnActionPerformed

    private void DelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelBtnActionPerformed
        if (CCPos == -1) {
            JOptionPane.showMessageDialog(rootPane, "Chưa chọn doanh nghiệp cần xoá, vui lòng kiểm tra lại!");
            return;
        }
        int option = JOptionPane.showConfirmDialog(rootPane, "Bạn chắc chắn muốn xoá doanh nghiệp này chứ?");
        if(option == 0) {
            CorporateCustomerModify.delete(CClist.get(CCPos).getCustomerID());
            CCPos = -1;
            CClist = CorporateCustomerModify.getCorporCusList(null);
            showDataCC();
        }
        CusIDTxt.setText("");
        companyNameTxt.setText("");
        taxCodeTxt.setText("");
        contactPersonTxt.setText("");
        CphoneTxt.setText("");
        CemailTxt.setText("");
        CaddressTxt.setText("");
    }//GEN-LAST:event_DelBtnActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CCTable;
    private javax.swing.JTextField CaddressTxt;
    private javax.swing.JTextField CemailTxt;
    private javax.swing.JTextField CphoneTxt;
    private javax.swing.JTextField CusIDTxt;
    private javax.swing.JButton DelBtn;
    private javax.swing.JTable ICTable;
    private javax.swing.JButton SaveBtn;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JTextField addressTxt;
    private javax.swing.JTextField companyNameTxt;
    private javax.swing.JTextField contactPersonTxt;
    private javax.swing.JTextField cusIDTxt;
    private javax.swing.JButton delBtn;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField genderTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JTextField phoneTxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField taxCodeTxt;
    // End of variables declaration//GEN-END:variables
}
