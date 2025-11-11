
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

        saveBtn.setText("Lưu");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        searchBtn.setText("Tìm Kiếm");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        delBtn.setText("Xoá");
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã khách hàng");

        jLabel2.setText("Họ và tên");

        jLabel3.setText("Giới tính");

        jLabel4.setText("Số điện thoại");

        jLabel5.setText("Email");

        jLabel6.setText("Địa chỉ");

        phoneTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneTxtActionPerformed(evt);
            }
        });

        ICTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã khách hàng", "Họ và tên", "Giới tính", "Số điện thoại", "Email", "Địa chỉ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ICTable);
        if (ICTable.getColumnModel().getColumnCount() > 0) {
            ICTable.getColumnModel().getColumn(0).setMinWidth(40);
            ICTable.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(88, 88, 88)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressTxt)
                            .addComponent(emailTxt)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(phoneTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                            .addComponent(genderTxt)
                            .addComponent(nameTxt)
                            .addComponent(cusIDTxt))))
                .addGap(117, 117, 117)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(126, 126, 126))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(saveBtn)
                        .addGap(58, 58, 58)
                        .addComponent(searchBtn)
                        .addGap(56, 56, 56)
                        .addComponent(delBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cusIDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(genderTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(phoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(addressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        jTabbedPane1.addTab("KHÁCH HÀNG CÁ NHÂN", jPanel1);

        jLabel7.setText("Mã khách hàng");

        jLabel8.setText("Địa chỉ");

        jLabel9.setText("Tên doanh nghiệp");

        jLabel10.setText("Mã số thuế");

        jLabel11.setText("Người đại diện");

        jLabel12.setText("Số điện thoại");

        jLabel13.setText("Email");

        taxCodeTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taxCodeTxtActionPerformed(evt);
            }
        });

        CphoneTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CphoneTxtActionPerformed(evt);
            }
        });

        CemailTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CemailTxtActionPerformed(evt);
            }
        });

        SaveBtn.setText("Lưu");
        SaveBtn.setMaximumSize(new java.awt.Dimension(80, 30));
        SaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveBtnActionPerformed(evt);
            }
        });

        SearchBtn.setText("Tìm kiếm");
        SearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtnActionPerformed(evt);
            }
        });

        DelBtn.setText("Xoá");
        DelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelBtnActionPerformed(evt);
            }
        });

        CCTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã khách hàng", "Tên doanh nghiệp", "Mã số thuế", "Người đại diện", "Số điện thoại", "Email", "Địa chỉ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(CCTable);
        if (CCTable.getColumnModel().getColumnCount() > 0) {
            CCTable.getColumnModel().getColumn(0).setMinWidth(40);
            CCTable.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CusIDTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                            .addComponent(companyNameTxt)
                            .addComponent(taxCodeTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(contactPersonTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CphoneTxt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CemailTxt)
                            .addComponent(CaddressTxt, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(124, 124, 124)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SaveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(105, 105, 105))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(CusIDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(companyNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SaveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(1, 1, 1)
                        .addComponent(SearchBtn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(taxCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(contactPersonTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(CphoneTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(DelBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(CemailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(CaddressTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jTabbedPane1.addTab("KHÁCH HÀNG DOANH NGHIỆP", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jTabbedPane1)
                .addGap(55, 55, 55))
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

    private void taxCodeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taxCodeTxtActionPerformed

        
    }//GEN-LAST:event_taxCodeTxtActionPerformed

    private void CphoneTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CphoneTxtActionPerformed

    }//GEN-LAST:event_CphoneTxtActionPerformed

    
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
