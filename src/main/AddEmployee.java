/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author philc
 */
public class AddEmployee extends javax.swing.JFrame {

    /**
     * Creates new form AddEmployee
     */
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    int row = -1;
    boolean isEditActive = false;

    public AddEmployee() {

        initComponents();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee-database", "root", "root");
            System.out.print(conn);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        tbload();

        saveBtn.setEnabled(false);
        addBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        editBtn.setEnabled(false);
        clearBtn.setEnabled(false);
        cancelBtn.setEnabled(false);
    }

    public void tbload() {

        try {
            DefaultTableModel dt = (DefaultTableModel) employeeTable.getModel();

            dt.setRowCount(0);

            ps = conn.prepareStatement("SELECT * FROM employees");
            rs = ps.executeQuery();

            while (rs.next()) {

                Vector v = new Vector();

                v.add(rs.getString(2)); // employeeID
                v.add(rs.getString(3)); // lastname
                v.add(rs.getString(4)); //firstname
                v.add(rs.getString(5)); //position
                v.add(rs.getString(6)); //contact

                dt.addRow(v);

            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteRow(String id) {
        String query = "DELETE FROM `employees` WHERE employeeID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.execute();
            JOptionPane.showMessageDialog(this, "Deleted");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "" + e);
        }
    }

    public boolean contactClear() {
        try {
            contactField.setText("");
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDuplicate(String id) {
        String query = "SELECT employeeID FROM employees WHERE employeeID = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, id); // Set the parameter value
            rs = ps.executeQuery(); // Execute the query
            if (rs.next()) {
                return true;// Get the value from the result set
            } else {
                return false; // or any appropriate value if no result is found
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            return true;
        }

    }

    public boolean checkIfNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void checkFieldsIfEmpty() {
        if (employeeIDField.getText().equals("")
                || lastnameField.getText().equals("")
                || firstnameField.getText().equals("")
                || contactField.getText().length() != 9) {
            addBtn.setEnabled(false);
            saveBtn.setEnabled(false);
        } else if (isEditActive) {
            addBtn.setEnabled(false);
            saveBtn.setEnabled(true);
            clearBtn.setEnabled(true);
        } else {
            addBtn.setEnabled(true);
            if (isEditActive) {
                saveBtn.setEnabled(true);
            } else {
                saveBtn.setEnabled(false);
            }
        }
    }

    public int generateID() {
        Random random = new Random();
        int randomNumber = 101000000 + random.nextInt(1000000);
        return randomNumber;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        employeeIDField = new javax.swing.JTextField();
        lastnameField = new javax.swing.JTextField();
        firstnameField = new javax.swing.JTextField();
        contactField = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        positionField = new javax.swing.JComboBox<>();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        prevBtn = new javax.swing.JButton();
        contactField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EMPLOYEE INFORMATION");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel3.setText("Employee ID:");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Last name:");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("First name:");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Position:");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("-");

        employeeIDField.setEditable(false);
        employeeIDField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                employeeIDFieldFocusGained(evt);
            }
        });

        lastnameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lastnameFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lastnameFieldKeyReleased(evt);
            }
        });

        firstnameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                firstnameFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                firstnameFieldKeyReleased(evt);
            }
        });

        contactField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contactFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                contactFieldKeyReleased(evt);
            }
        });

        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        saveBtn.setText("SAVE");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        nextBtn.setText("NEXT");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        closeBtn.setText("CLOSE");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Last Name", "First Name", "Position", "Contact"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(employeeTable);

        positionField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jr. Engineer", "Mid Engineer", "Sr. Engineer", "Team Lead", "CEO" }));

        editBtn.setText("EDIT");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("DELETE");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        prevBtn.setText("PREV");
        prevBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtnActionPerformed(evt);
            }
        });

        contactField1.setEditable(false);
        contactField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contactField1.setText("09");
        contactField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                contactField1FocusGained(evt);
            }
        });
        contactField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contactField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                contactField1KeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Phone No:");

        cancelBtn.setText("CANCEL");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap(173, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(clearBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(saveBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(0, 5, Short.MAX_VALUE))
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel6)))))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(contactField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jLabel7)
                                                .addGap(0, 0, 0)
                                                .addComponent(contactField))
                                            .addComponent(firstnameField, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                                            .addComponent(lastnameField)
                                            .addComponent(employeeIDField)
                                            .addComponent(positionField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(prevBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(employeeIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lastnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(firstnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(positionField, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(contactField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contactField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prevBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private JFrame frame;

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed

        if (lastnameField.getText().length() <= 1) {

            JOptionPane.showMessageDialog(this, "Last name field must be 2 characters atleast!");

        } else if (firstnameField.getText().length() <= 1) {

            JOptionPane.showMessageDialog(this, "First name field must be 2 characters atleast!");

        } else {

            int addConfirmationChoice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to add this employee?", "Confirm employee details", JOptionPane.YES_NO_OPTION);
            if (addConfirmationChoice == JOptionPane.YES_OPTION) {

                DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
                model.setRowCount(0);
                String sql = "INSERT INTO employees(employeeID, lastname, firstname, position, contact) VALUES(?, ?, ?, ?, ?)";
                try {
                    ps = conn.prepareStatement(sql);

                    ps.setString(1, employeeIDField.getText());
                    ps.setString(2, lastnameField.getText());
                    ps.setString(3, firstnameField.getText());
                    ps.setString(4, (String) positionField.getSelectedItem());
                    ps.setString(5, "09" + contactField.getText());

                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(this, "New employee added!");
                    rs.close();
                    ps.close();

                    employeeIDField.setText(null);
                    lastnameField.setText(null);
                    firstnameField.setText(null);
                    positionField.setSelectedIndex(0);
                    contactField.setText(null);
                    addBtn.setEnabled(false);
                    clearBtn.setEnabled(false);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e);
                } finally {
                    try {
                        rs.close();
                        ps.close();
                    } catch (Exception e) {
                    }
                    tbload();
                }
            } else {
                // Close the dialog
                this.dispose();
            }

        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        if (lastnameField.getText().length() <= 1) {

            JOptionPane.showMessageDialog(this, "Last name field must be 2 characters atleast!");
            lastnameField.requestFocusInWindow();

        } else if (firstnameField.getText().length() <= 1) {

            JOptionPane.showMessageDialog(this, "First name field must be 2 characters atleast!");
            firstnameField.requestFocusInWindow();

        } else {

            int addConfirmationChoice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to edit " + lastnameField.getText() + "'s information?", "Confirm employee details", JOptionPane.YES_NO_OPTION);
            if (addConfirmationChoice == JOptionPane.YES_OPTION) {

                DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
                model.setRowCount(0);
                String sql = "UPDATE employees SET lastname='" + lastnameField.getText() + "', firstname='" + firstnameField.getText() + "', position='" + positionField.getSelectedItem() + "', contact='" + "09" + contactField.getText() + "' WHERE employeeID='" + employeeIDField.getText() + "' ";
                try {
                    ps = conn.prepareStatement(sql);

                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Employee Updated");
                    rs.close();
                    ps.close();

                    employeeIDField.setText(null);
                    lastnameField.setText(null);
                    firstnameField.setText(null);
                    positionField.setSelectedIndex(0);
                    contactField.setText(null);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                } finally {
                    try {
                        rs.close();
                        ps.close();
                    } catch (Exception e) {
                    }
                    tbload();

                    saveBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                    editBtn.setEnabled(false);
                    cancelBtn.setEnabled(false);
                    clearBtn.setEnabled(false);
                    employeeTable.setEnabled(true);
                    nextBtn.setEnabled(true);
                    prevBtn.setEnabled(true);
                    isEditActive = false;

                }
            } else {
                // Close the dialog
//                this.dispose();
            }

        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        int rowCount = employeeTable.getRowCount();
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        if (row == rowCount - 1) {
            row = 0;
        } else {
            row++;
        }

        employeeIDField.setFocusable(false);
        lastnameField.setFocusable(false);
        firstnameField.setFocusable(false);
        positionField.setEnabled(false);
        contactField.setFocusable(false);

        String contactFieldValue = model.getValueAt(row, 4).toString();
        if (contactFieldValue.length() > 2) {
            contactFieldValue = contactFieldValue.substring(2);
        }

        employeeTable.changeSelection(row, 0, false, false);
        employeeIDField.setText(model.getValueAt(row, 0).toString());
        lastnameField.setText(model.getValueAt(row, 1).toString());
        firstnameField.setText(model.getValueAt(row, 2).toString());
        positionField.setSelectedItem(model.getValueAt(row, 3).toString());
        contactField.setText(contactFieldValue);

        addBtn.setEnabled(false);
        clearBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        editBtn.setEnabled(true);

    }//GEN-LAST:event_nextBtnActionPerformed

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        frame = new JFrame("Exit");
        if (JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Confirm exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_closeBtnActionPerformed

    private void employeeIDFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_employeeIDFieldFocusGained
        if (employeeIDField.getText().equals("")) {
            String randomNumberString = "";
            boolean isDuplicate = true;
            while (isDuplicate == true) {
                randomNumberString = Integer.toString(generateID());
                isDuplicate = isDuplicate(randomNumberString);
                employeeIDField.setFocusable(false);
                lastnameField.setFocusable(false);
                firstnameField.setFocusable(false);
                positionField.setEnabled(false);
                contactField.setFocusable(false);
            }

            employeeIDField.setText(randomNumberString);
            employeeIDField.setFocusable(true);
            lastnameField.setFocusable(true);
            firstnameField.setFocusable(true);
            positionField.setEnabled(true);
            contactField.setFocusable(true);
            checkFieldsIfEmpty();
            clearBtn.setEnabled(true);

        }

    }//GEN-LAST:event_employeeIDFieldFocusGained

    private void employeeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeTableMouseClicked

        if (!isEditActive) {
            int rowClickedByMouse = employeeTable.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
            row = rowClickedByMouse;

            String contactFieldValue = model.getValueAt(rowClickedByMouse, 4).toString();
            if (contactFieldValue.length() > 2) {
                contactFieldValue = contactFieldValue.substring(2);
            }

            employeeIDField.setText(model.getValueAt(rowClickedByMouse, 0).toString());
            lastnameField.setText(model.getValueAt(rowClickedByMouse, 1).toString());
            firstnameField.setText(model.getValueAt(rowClickedByMouse, 2).toString());
            positionField.setSelectedItem(model.getValueAt(rowClickedByMouse, 3).toString());
            contactField.setText(contactFieldValue);

            employeeIDField.setFocusable(false);
            lastnameField.setFocusable(false);
            firstnameField.setFocusable(false);
            positionField.setEnabled(false);
            contactField.setFocusable(false);

            addBtn.setEnabled(false);
            clearBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            editBtn.setEnabled(true);
        }


    }//GEN-LAST:event_employeeTableMouseClicked

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed

        employeeTable.setEnabled(false);
        deleteBtn.setEnabled(false);
        addBtn.setEnabled(false);
        nextBtn.setEnabled(false);
        prevBtn.setEnabled(false);
        cancelBtn.setEnabled(true);
        isEditActive = true;
        checkFieldsIfEmpty();

        employeeIDField.setFocusable(true);
        lastnameField.setFocusable(true);
        firstnameField.setFocusable(true);
        positionField.setEnabled(true);
        contactField.setFocusable(true);
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();

        if (employeeTable.getSelectedRowCount() == 1) {
            int addConfirmationChoice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete " + lastnameField.getText() + "'s information?", "Confirm employee deletion", JOptionPane.YES_NO_OPTION);
            if (addConfirmationChoice == JOptionPane.YES_OPTION) {
                model.removeRow(employeeTable.getSelectedRow());

                deleteRow(employeeIDField.getText());

                employeeIDField.setFocusable(true);
                lastnameField.setFocusable(true);
                firstnameField.setFocusable(true);
                positionField.setEnabled(true);
                contactField.setFocusable(true);

                employeeIDField.setText(null);
                lastnameField.setText(null);
                firstnameField.setText(null);
                contactField.setText(null);
                employeeTable.clearSelection();

                clearBtn.setEnabled(false);
                editBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
                tbload();
            } else {
//               this.dispose();
            }
        } else if (employeeTable.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Select a row in the table");
        } else {
            JOptionPane.showMessageDialog(this, "Please just select a single row");
        }

    }//GEN-LAST:event_deleteBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed

        employeeIDField.setFocusable(true);
        lastnameField.setFocusable(true);
        firstnameField.setFocusable(true);
        positionField.setEnabled(true);
        contactField.setFocusable(true);

        if (isEditActive) {
            lastnameField.setText(null);
            firstnameField.setText(null);
            positionField.setSelectedIndex(0);
            contactField.setText(null);

            editBtn.setEnabled(true);
            deleteBtn.setEnabled(false);
        } else {
            employeeIDField.setText(null);
            lastnameField.setText(null);
            firstnameField.setText(null);
            positionField.setSelectedIndex(0);
            contactField.setText(null);
            employeeTable.clearSelection();
            row = -1;

            editBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }

        clearBtn.setEnabled(false);
        checkFieldsIfEmpty();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void prevBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBtnActionPerformed
        int rowCount = employeeTable.getRowCount();
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
        if (row == -1) {
            row++;
        } else if (row == 0) {
            row = rowCount - 1;
        } else {
            row--;
        }

        employeeIDField.setFocusable(false);
        lastnameField.setFocusable(false);
        firstnameField.setFocusable(false);
        positionField.setEnabled(false);
        contactField.setFocusable(false);

        String contactFieldValue = model.getValueAt(row, 4).toString();
        if (contactFieldValue.length() > 2) {
            contactFieldValue = contactFieldValue.substring(2);
        }

        employeeTable.changeSelection(row, 0, false, false);
        employeeIDField.setText(model.getValueAt(row, 0).toString());
        lastnameField.setText(model.getValueAt(row, 1).toString());
        firstnameField.setText(model.getValueAt(row, 2).toString());
        positionField.setSelectedItem(model.getValueAt(row, 3).toString());
        contactField.setText(contactFieldValue);

        addBtn.setEnabled(false);
        clearBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        editBtn.setEnabled(true);
    }//GEN-LAST:event_prevBtnActionPerformed

    private void contactFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactFieldKeyPressed
        // TODO add your handling code here:
        String contactFieldValue = contactField.getText();
        int contactFieldValueLength = contactFieldValue.length();
        char characterTyped = evt.getKeyChar();
        //numbers only
        if (characterTyped >= '0' && characterTyped <= '9') {
            //check length max 11 digit
            if (contactFieldValueLength < 9) {
                contactField.setEditable(true);
                clearBtn.setEnabled(true);
            } else {
                contactField.setEditable(false);
            }

        } else {
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                contactField.setEditable(true);
            } else {
                contactField.setEditable(false);
            }
        }
    }//GEN-LAST:event_contactFieldKeyPressed

    private void firstnameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstnameFieldKeyPressed
        // TODO add your handling code here:
        char characterTyped = evt.getKeyChar();

        if (Character.isLetter(characterTyped) || Character.isWhitespace(characterTyped) || Character.isISOControl(characterTyped)) {
            firstnameField.setEditable(true);
            clearBtn.setEnabled(true);
        } else {
            firstnameField.setEditable(false);
        }
    }//GEN-LAST:event_firstnameFieldKeyPressed

    private void lastnameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastnameFieldKeyPressed
        // TODO add your handling code here:
        char characterTyped = evt.getKeyChar();

        if (Character.isLetter(characterTyped) || Character.isWhitespace(characterTyped) || Character.isISOControl(characterTyped)) {
            lastnameField.setEditable(true);
            clearBtn.setEnabled(true);
        } else {
            lastnameField.setEditable(false);
        }
    }//GEN-LAST:event_lastnameFieldKeyPressed

    private void lastnameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastnameFieldKeyReleased
        // TODO add your handling code here:
        checkFieldsIfEmpty();
    }//GEN-LAST:event_lastnameFieldKeyReleased

    private void firstnameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstnameFieldKeyReleased
        // TODO add your handling code here:
        checkFieldsIfEmpty();
    }//GEN-LAST:event_firstnameFieldKeyReleased

    private void contactFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactFieldKeyReleased
        // TODO add your handling code here:
        checkFieldsIfEmpty();
    }//GEN-LAST:event_contactFieldKeyReleased

    private void contactField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactField1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_contactField1FocusGained

    private void contactField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactField1KeyPressed

    private void contactField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contactField1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_contactField1KeyReleased

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        saveBtn.setEnabled(false);
        deleteBtn.setEnabled(true);
        addBtn.setEnabled(false);
        cancelBtn.setEnabled(false);
        isEditActive = false;

        nextBtn.setEnabled(!isEditActive);
        prevBtn.setEnabled(!isEditActive);
        employeeTable.setEnabled(true);
        employeeIDField.setFocusable(false);
        lastnameField.setFocusable(false);
        firstnameField.setFocusable(false);
        positionField.setEnabled(false);
        contactField.setFocusable(false);

        int row = employeeTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();

        String contactFieldValue = model.getValueAt(row, 4).toString();
        if (contactFieldValue.length() > 2) {
            contactFieldValue = contactFieldValue.substring(2);
        }

        employeeIDField.setText(model.getValueAt(row, 0).toString());
        lastnameField.setText(model.getValueAt(row, 1).toString());
        firstnameField.setText(model.getValueAt(row, 2).toString());
        positionField.setSelectedItem(model.getValueAt(row, 3).toString());
        contactField.setText(contactFieldValue);

        employeeIDField.setFocusable(false);
        lastnameField.setFocusable(false);
        firstnameField.setFocusable(false);
        positionField.setEnabled(false);
        contactField.setFocusable(false);

        addBtn.setEnabled(false);
        clearBtn.setEnabled(true);
        deleteBtn.setEnabled(true);
        editBtn.setEnabled(true);
    }//GEN-LAST:event_cancelBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddEmployee().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JTextField contactField;
    private javax.swing.JTextField contactField1;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField employeeIDField;
    private javax.swing.JTable employeeTable;
    private javax.swing.JTextField firstnameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lastnameField;
    private javax.swing.JButton nextBtn;
    private javax.swing.JComboBox<String> positionField;
    private javax.swing.JButton prevBtn;
    private javax.swing.JButton saveBtn;
    // End of variables declaration//GEN-END:variables
}
