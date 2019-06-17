/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulePackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class AddSchedule extends javax.swing.JDialog {

    public static ArrayList sched = new ArrayList();
    public static ArrayList task = new ArrayList();
    public static ArrayList title = new ArrayList();
    public static ArrayList type = new ArrayList();
    int titleCtr = 0;
    public static int shitCtr = 0;

    public AddSchedule(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setSize(1246, 615);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("NlexLogo1.png")));
        this.setTitle("Traffic Systems Engineering v.0.1");
        pleaseLbl.setVisible(true);

        for (int x = 0; x < VariableClass.equipmentList.size(); x++) {
            jComboBox4.addItem(VariableClass.equipmentList.get(x).toString());
            pleaseLbl.setVisible(false);

        }
        viewSchedRecords();
        if (VariableClass.sched.contains(dateChooser1.getText())) {
            titleField.setText("" + VariableClass.title.get(VariableClass.sched.indexOf(dateChooser1.getText())));
        } else {
            titleField.setText("");
        }
        System.out.println(shitCtr);
        if (shitCtr == 1) {
            Calendar cal1 = Calendar.getInstance();
            cal1.set(Calendar.DATE, MainScreen.calendarCtr);
            cal1.set(Calendar.MONTH, MainScreen.monthToday - 1);
            cal1.set(Calendar.YEAR, Integer.parseInt(MainScreen.yearCombo.getItemAt(MainScreen.yearCombo.getSelectedIndex())));
            AddSchedule.dateChooser1.setSelectedDate(cal1);
            AddSchedule.titleUpdate();

        }

        typeField.setEnabled(false);
        typeTxt.setEnabled(false);
        this.setLocationRelativeTo(null);
        addTaskArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter day = DateTimeFormatter.ofPattern("dd");
        LocalDateTime now = LocalDateTime.now();
        viewSchedRecords();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Component frame = null;
                int x = JOptionPane.showConfirmDialog(frame,
                        "Close this window?", "Notice!",
                        JOptionPane.YES_NO_OPTION);

                if (x == 0) {

                    dispose();
                    MainScreen.viewCal.setVisible(false);
                    MainScreen.delCal.setVisible(false);
                    MainScreen.delCal.setVisible(false);
                    MainScreen.addCal.setVisible(false);
                    MainScreen.cancelBtn_Main.setVisible(false);
                    sched.removeAll(sched);
                    task.removeAll(task);
                    title.removeAll(title);
                    MainScreen.viewTaskRecords();

                }

            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

    }

    public static void equipmentAdd() {
        pleaseLbl.setVisible(true);
        for (int x = 0; x < VariableClass.equipmentList.size(); x++) {
            jComboBox4.addItem(VariableClass.equipmentList.get(x).toString());
            pleaseLbl.setVisible(false);

        }

    }

    public static void titleUpdate() {

        viewSchedRecords();

        if (VariableClass.sched.contains(dateChooser1.getText())) {
            titleField.setText("" + VariableClass.title.get(VariableClass.sched.indexOf(dateChooser1.getText())));
        } else {
            titleField.setText("");
        }
    }

    public static void viewSchedRecords() {
        String col[] = new String[]{"Date", "Type", "Task"};
        DefaultTableModel model = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        Object rowData[] = new Object[5];

        for (int x = 0; x < task.size(); x++) {

            String tempCutDate = sched.get(x).toString();
            String cutDate = "";
            for (int ctr = 0; ctr < tempCutDate.length(); ctr++) {
                if (tempCutDate.charAt(ctr) == ',') {

                    for (int ctr1 = ctr + 2; ctr1 < tempCutDate.length(); ctr1++) {
                        cutDate += tempCutDate.charAt(ctr1) + "";
                    }
                    break;
                }
            }

            rowData[0] = cutDate;
            rowData[1] = type.get(x).toString();
            rowData[2] = task.get(x).toString();
            model.addRow(rowData);

        }

        schedTable.setModel(model);
        schedTable.setDefaultRenderer(Object.class, new MyRenderer());

        TableColumnModel columnmodel = schedTable.getColumnModel();

        updateRowHeights();

        jComboBox3.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        if (VariableClass.equipmentList.size() == 0) {

        } else {
            jComboBox4.setSelectedIndex(0);
        }
        jComboBox2.setSelectedIndex(0);
        typeField1.setEnabled(false);
        typeField.setEnabled(false);
        typeField1.setText("");
        typeField.setText("");

    }

    public static void updateRowHeights() {
        for (int row = 0; row < schedTable.getRowCount(); row++) {
            int rowHeight = schedTable.getRowHeight();

            for (int column = 0; column < schedTable.getColumnCount(); column++) {
                Component comp = schedTable.prepareRenderer(schedTable.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

            schedTable.setRowHeight(row, rowHeight);
        }
    }
//    public static class MultipleLine extends JTextArea implements TableCellRenderer {
//
//        public MultipleLine() {
//            setLineWrap(true);
//            setWrapStyleWord(true);
//            setOpaque(true);
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            
//            
//            setEditable(false);
//            if (isSelected) {
//                this.setBackground(Color.LIGHT_GRAY);
//            } else {
//                this.setBackground(Color.white);
//            }
//
//            return this;
//
//        }
//
//    }

    public static class MyRenderer implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JTextArea editor = new JTextArea();
            if (value != null) {
                String tempmyString = value.toString();
                String myString = "";
                int limit = 0;
                for (int x = 0; x < tempmyString.length(); x++, limit++) {
                    if (limit == 25) {
                        limit = 0;
                        myString += "\n";
                    }
                    myString += tempmyString.charAt(x);

                }

                editor.setText(myString);
            }

            if (isSelected == true) {
                editor.setBackground(Color.LIGHT_GRAY);
            } else {
                editor.setBackground((row % 2 == 0) ? Color.white : Color.white );
            }

            return editor;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        birthLabel1 = new javax.swing.JLabel();
        dateChooser1 = new datechooser.beans.DateChooserCombo();
        jPanel3 = new javax.swing.JPanel();
        pane1 = new javax.swing.JScrollPane();
        addTaskArea = new javax.swing.JTextArea();
        typeField = new javax.swing.JTextField();
        birthLabel3 = new javax.swing.JLabel();
        typeTxt = new javax.swing.JLabel();
        titleTxtCount = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        birthLabel5 = new javax.swing.JLabel();
        birthLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        birthLabel7 = new javax.swing.JLabel();
        birthLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        typeTxt1 = new javax.swing.JLabel();
        typeField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        jComboBox6 = new javax.swing.JComboBox<>();
        locationLbl = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        birthLabel9 = new javax.swing.JLabel();
        pleaseLbl = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        schedTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));
        jPanel1.setMinimumSize(new java.awt.Dimension(405, 628));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        birthLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        birthLabel1.setForeground(new java.awt.Color(255, 204, 0));
        birthLabel1.setText("Date:");
        jPanel1.add(birthLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 50, -1));

        dateChooser1.setCalendarPreferredSize(new java.awt.Dimension(350, 280));
        dateChooser1.setNothingAllowed(false);
        dateChooser1.setFormat(0);
        dateChooser1.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dateChooser1OnSelectionChange(evt);
            }
        });
        dateChooser1.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dateChooser1OnCommit(evt);
            }
        });
        jPanel1.add(dateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 250, 40));

        jPanel3.setBackground(new java.awt.Color(0, 51, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "New Task", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14), new java.awt.Color(255, 204, 0))); // NOI18N

        addTaskArea.setColumns(20);
        addTaskArea.setLineWrap(true);
        addTaskArea.setRows(5);
        addTaskArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addTaskAreaMouseClicked(evt);
            }
        });
        pane1.setViewportView(addTaskArea);

        typeField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                typeFieldMouseClicked(evt);
            }
        });
        typeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeFieldActionPerformed(evt);
            }
        });
        typeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                typeFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                typeFieldKeyReleased(evt);
            }
        });

        birthLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        birthLabel3.setForeground(new java.awt.Color(255, 204, 0));
        birthLabel3.setText("Description:");

        typeTxt.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        typeTxt.setForeground(new java.awt.Color(255, 204, 0));
        typeTxt.setText("Specify:");

        titleTxtCount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        titleTxtCount.setForeground(new java.awt.Color(255, 204, 0));
        titleTxtCount.setText("20/20");

        titleField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                titleFieldMouseClicked(evt);
            }
        });
        titleField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                titleFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                titleFieldKeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CM", "PM", "Job Offer", "Other" }));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        birthLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        birthLabel5.setForeground(new java.awt.Color(255, 204, 0));
        birthLabel5.setText("Title:");

        birthLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        birthLabel6.setForeground(new java.awt.Color(255, 204, 0));
        birthLabel6.setText("Type:");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ongoing", "Compete" }));
        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        birthLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        birthLabel7.setForeground(new java.awt.Color(255, 204, 0));
        birthLabel7.setText("Status:");

        birthLabel8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        birthLabel8.setForeground(new java.awt.Color(255, 204, 0));
        birthLabel8.setText("Equipment :");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Traffic & Tollplaza", "Tollbooth & Auxiliary", "ODS and VMS", "CS & AVLS", "Speed", "ECB", "Other" }));
        jComboBox3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox3MouseClicked(evt);
            }
        });
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        typeTxt1.setBackground(new java.awt.Color(255, 204, 0));
        typeTxt1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        typeTxt1.setForeground(new java.awt.Color(255, 204, 0));
        typeTxt1.setText("Specify:");
        typeTxt1.setEnabled(false);

        typeField1.setEnabled(false);
        typeField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                typeField1MouseClicked(evt);
            }
        });
        typeField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeField1ActionPerformed(evt);
            }
        });
        typeField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                typeField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                typeField1KeyReleased(evt);
            }
        });

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 60, -1));

        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        jPanel2.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 60, -1));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NLEX", "SCTEX" }));
        jComboBox6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox6MouseClicked(evt);
            }
        });
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        locationLbl.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        locationLbl.setForeground(new java.awt.Color(255, 204, 0));
        locationLbl.setText("Location:");

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox4MouseClicked(evt);
            }
        });
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        birthLabel9.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        birthLabel9.setForeground(new java.awt.Color(255, 204, 0));
        birthLabel9.setText("Equipment Location:");

        pleaseLbl.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        pleaseLbl.setForeground(new java.awt.Color(255, 204, 0));
        pleaseLbl.setText("*Please add Location");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(birthLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titleTxtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(birthLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pane1)))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(birthLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pleaseLbl)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(birthLabel8)
                                    .addComponent(birthLabel7))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(typeTxt1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(typeField1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(birthLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(locationLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(typeTxt)
                                .addGap(10, 10, 10)
                                .addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthLabel5)
                    .addComponent(titleTxtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthLabel6)
                    .addComponent(typeTxt)
                    .addComponent(typeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthLabel8)
                    .addComponent(typeTxt1)
                    .addComponent(typeField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthLabel9)
                    .addComponent(pleaseLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(birthLabel3)
                        .addGap(71, 71, 71))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(pane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 560, 480));

        jPanel4.setBackground(new java.awt.Color(0, 51, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Task to add", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 14), new java.awt.Color(255, 204, 0))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(255, 204, 0));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        schedTable.setBackground(new java.awt.Color(0, 51, 153));
        schedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        schedTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        schedTable.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                schedTableHierarchyChanged(evt);
            }
        });
        schedTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                schedTableFocusLost(evt);
            }
        });
        schedTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                schedTableMouseClicked(evt);
            }
        });
        schedTable.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                schedTableVetoableChange(evt);
            }
        });
        jScrollPane2.setViewportView(schedTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 520));

        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 550, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1246, 586));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        MainScreen.viewCal.setVisible(false);
        MainScreen.delCal.setVisible(false);
        MainScreen.addCal.setVisible(false);
        MainScreen.delCal1.setVisible(false);
        MainScreen.cancelBtn_Main.setVisible(false);
        sched.removeAll(sched);
        task.removeAll(task);
        title.removeAll(title);

        MainScreen.myCalendar();

        System.out.println(MainScreen.addScheduleCtr);
        if (MainScreen.addScheduleCtr == 1) {
            MainScreen.jButton2.doClick();
            MainScreen.updateTable();
            MainScreen.viewTaskRecords();
        }

        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void schedTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_schedTableMouseClicked

        //schedTable.clearSelection();
    }//GEN-LAST:event_schedTableMouseClicked

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        
        if (titleField.getText().trim().equals("") || addTaskArea.getText().trim().equals("") || VariableClass.task.contains(addTaskArea)
                || VariableClass.equipmentList.size() == 0) {
            if (titleField.getText().trim().equals("") || addTaskArea.getText().trim().equals("") || VariableClass.task.contains(addTaskArea)) {
                JOptionPane.showMessageDialog(null, "There are still empty field..", "Notice", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Equipment Location still empty\nPlease add..", "Notice", JOptionPane.INFORMATION_MESSAGE);

            }
        } else {
            int titleFlag = 0;
            int titleFlag1 = 0;
            int titleFlag3 = 0;
            if (VariableClass.sched.contains(dateChooser1.getText())) {
                for (int ctr = 0; ctr < VariableClass.sched.size(); ctr++) {
                    if (dateChooser1.getText().equals(VariableClass.sched.get(ctr).toString())) {
                        if (titleField.getText().equals(VariableClass.title.get(ctr).toString())) {
                            titleFlag = 0;

                        } else {
                            titleFlag = 1;
                            titleFlag1 = ctr;
                            break;
                        }
                    }
                }
                System.out.println("gumana 0");
            } else {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/trafficsystems";
                    Connection conn = DriverManager.getConnection(url, "root", "admin");

                    Statement statement = conn.createStatement();
                    statement = conn.createStatement();
                    String sql = "INSERT INTO sched (schedId, sched,task,title,type,conductedBy,status,schedIdentifier,equipment,location,equipmentLocation,imageSched)"
                            + "VALUES (?, ? , ?, ? ,? , ? , ? , ? , ? , ? ,?,?)";

                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setInt(1, 0);

                    sched.add(dateChooser1.getText());
                    preparedStatement.setString(2, dateChooser1.getText());

                    title.add(titleField.getText());
                    preparedStatement.setString(4, titleField.getText());

                    task.add(addTaskArea.getText());
                    preparedStatement.setString(3, addTaskArea.getText());

                    if (jComboBox1.getSelectedIndex() == 3) {
                        type.add(typeField.getText());
                        preparedStatement.setString(5, typeField.getText());

                    } else {
                        type.add(jComboBox1.getSelectedItem());
                        preparedStatement.setString(5, jComboBox1.getSelectedItem().toString());
                    }

                    VariableClass.conductedBy.add(VariableClass.initials.get(VariableClass.userlog));
                    preparedStatement.setString(6, VariableClass.initials.get(VariableClass.userlog).toString());

                    VariableClass.status.add(jComboBox2.getSelectedItem() + "");
                    preparedStatement.setString(7, jComboBox2.getSelectedItem().toString());

                    VariableClass.schedIdentifier.add(VariableClass.username.get(VariableClass.userlog) + "");
                    preparedStatement.setString(8, VariableClass.username.get(VariableClass.userlog).toString());

                    if (jComboBox3.getSelectedIndex()
                            == 6) {
                        VariableClass.equipment.add(typeField1.getText());
                        preparedStatement.setString(9, typeField1.getText());
                    } else {
                        VariableClass.equipment.add(jComboBox3.getSelectedItem().toString());
                        preparedStatement.setString(9, jComboBox3.getSelectedItem().toString());
                    }

                    VariableClass.equipmentLocation.add(jComboBox4.getSelectedItem().toString());
                    preparedStatement.setString(11, jComboBox4.getSelectedItem().toString());

                    VariableClass.location.add(jComboBox6.getSelectedItem() + "");
                    preparedStatement.setString(10, jComboBox6.getSelectedItem().toString());

                    VariableClass.imageSched.add(VariableClass.imgUser.get(VariableClass.userlog).toString());
                    preparedStatement.setString(12, VariableClass.imgUser.get(VariableClass.userlog).toString());

                    addTaskArea.setText(
                            "");
                    titleField.setText(
                            "");
                    typeField.setText(
                            "");
                    jComboBox1.setSelectedIndex(
                            0);

                    viewSchedRecords();

                    System.out.println(
                            "gumana 1");
                    titleFlag = 3;

                    preparedStatement.executeUpdate();
                    conn.close();
                    VariableClass.loadSched();
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

            if (titleFlag == 0) {

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/trafficsystems";
                    Connection conn = DriverManager.getConnection(url, "root", "admin");

                    Statement statement = conn.createStatement();
                    statement = conn.createStatement();
                    String sql = "INSERT INTO sched (schedId, sched,task,title,type,conductedBy,status,schedIdentifier,equipment,location,equipmentLocation,imageSched)"
                            + "VALUES (?, ? , ?, ? ,? , ? , ? , ? , ? , ? ,?,?)";

                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setInt(1, 0);

                    sched.add(dateChooser1.getText());
                    preparedStatement.setString(2, dateChooser1.getText());
                    title.add(titleField.getText());
                    preparedStatement.setString(4, titleField.getText());
                    task.add(addTaskArea.getText());
                    preparedStatement.setString(3, addTaskArea.getText());
                    if (jComboBox1.getSelectedIndex() == 3) {
                        type.add(typeField.getText());
                        preparedStatement.setString(5, typeField.getText());
                    } else {
                        type.add(jComboBox1.getSelectedItem());
                        preparedStatement.setString(5, jComboBox1.getSelectedItem().toString());
                    }

                    VariableClass.conductedBy.add(VariableClass.initials.get(VariableClass.userlog));
                    preparedStatement.setString(6, VariableClass.initials.get(VariableClass.userlog).toString());
                    VariableClass.status.add(jComboBox2.getSelectedItem() + "");
                    preparedStatement.setString(7, jComboBox2.getSelectedItem().toString());
                    VariableClass.schedIdentifier.add(VariableClass.username.get(VariableClass.userlog) + "");
                    preparedStatement.setString(8, VariableClass.username.get(VariableClass.userlog).toString());
                    if (jComboBox3.getSelectedIndex() == 6) {
                        VariableClass.equipment.add(typeField1.getText());
                        preparedStatement.setString(9, typeField1.getText());
                    } else {
                        VariableClass.equipment.add(jComboBox3.getSelectedItem().toString());
                        preparedStatement.setString(9, jComboBox3.getSelectedItem().toString());
                    }

                    VariableClass.equipmentLocation.add(jComboBox4.getSelectedItem().toString());
                    preparedStatement.setString(11, jComboBox4.getSelectedItem().toString());

                    VariableClass.location.add(jComboBox6.getSelectedItem() + "");
                    preparedStatement.setString(10, jComboBox6.getSelectedItem().toString());

                    VariableClass.imageSched.add(VariableClass.imgUser.get(VariableClass.userlog).toString());
                    preparedStatement.setString(12, VariableClass.imgUser.get(VariableClass.userlog).toString());

                    addTaskArea.setText("");
                    titleField.setText("");
                    typeField.setText("");
                    jComboBox1.setSelectedIndex(0);

                    viewSchedRecords();
                    System.out.println("gumana 2");
                    preparedStatement.executeUpdate();
                    conn.close();
                    VariableClass.loadSched();
                } catch (Exception e) {
                    System.out.println(e);
                }

            } else if (titleFlag == 3) {
                System.out.println("a");
            } else {
                int choice = JOptionPane.showConfirmDialog(null, "Do you want to change " + VariableClass.title.get(titleFlag1).toString()
                        + " (old title) to " + titleField.getText() + " (new title)?", "Notice!", JOptionPane.YES_NO_CANCEL_OPTION);
                if (choice == 0) {
                    for (int ctr = 0; ctr < VariableClass.title.size(); ctr++) {
                        if (dateChooser1.getText().equals(VariableClass.sched.get(ctr).toString())) {
                            VariableClass.title.set(ctr, titleField.getText() + "");
                            try {

                                Class.forName("com.mysql.jdbc.Driver");
                                String url = "jdbc:mysql://localhost:3306/trafficsystems";
                                Connection conn = DriverManager.getConnection(url, "root", "admin");

                                String query = "update sched set title = ? where schedId = ?";
                                PreparedStatement preparedStmt = conn.prepareStatement(query);
                                preparedStmt.setString(1, titleField.getText());
                                preparedStmt.setInt(2, VariableClass.schedId.get(ctr));

                                // execute the java preparedstatement
                                preparedStmt.executeUpdate();

                                conn.close();
                                VariableClass.loadSched();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }

                    }
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/trafficsystems";
                        Connection conn = DriverManager.getConnection(url, "root", "admin");

                        Statement statement = conn.createStatement();
                        statement = conn.createStatement();
                        String sql = "INSERT INTO sched (schedId, sched,task,title,type,conductedBy,status,schedIdentifier,equipment,location,equipmentLocation,imageSched)"
                                + "VALUES (?, ? , ?, ? ,? , ? , ? , ? , ? , ? ,?,?)";

                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setInt(1, 0);

                        sched.add(dateChooser1.getText());
                        preparedStatement.setString(2, dateChooser1.getText());

                        title.add(titleField.getText());
                        preparedStatement.setString(4, titleField.getText());

                        task.add(addTaskArea.getText());
                        preparedStatement.setString(3, addTaskArea.getText());

                        if (jComboBox1.getSelectedIndex()
                                == 3) {
                            type.add(typeField.getText());
                            preparedStatement.setString(5, typeField.getText());

                        } else {
                            type.add(jComboBox1.getSelectedItem());
                            preparedStatement.setString(5, jComboBox1.getSelectedItem().toString());
                        }

                        VariableClass.conductedBy.add(VariableClass.initials.get(VariableClass.userlog));
                        preparedStatement.setString(6, VariableClass.initials.get(VariableClass.userlog).toString());

                        VariableClass.status.add(jComboBox2.getSelectedItem() + "");
                        preparedStatement.setString(7, jComboBox2.getSelectedItem().toString());

                        VariableClass.schedIdentifier.add(VariableClass.username.get(VariableClass.userlog) + "");
                        preparedStatement.setString(8, VariableClass.username.get(VariableClass.userlog).toString());

                        if (jComboBox3.getSelectedIndex()
                                == 6) {
                            VariableClass.equipment.add(typeField1.getText());
                            preparedStatement.setString(9, typeField1.getText());
                        } else {
                            VariableClass.equipment.add(jComboBox3.getSelectedItem().toString());
                            preparedStatement.setString(9, jComboBox3.getSelectedItem().toString());
                        }

                        VariableClass.equipmentLocation.add(jComboBox4.getSelectedItem().toString());
                        preparedStatement.setString(11, jComboBox4.getSelectedItem().toString());

                        VariableClass.location.add(jComboBox6.getSelectedItem() + "");
                        preparedStatement.setString(10, jComboBox6.getSelectedItem().toString());

                        VariableClass.imageSched.add(VariableClass.imgUser.get(VariableClass.userlog).toString());
                        preparedStatement.setString(12, VariableClass.imgUser.get(VariableClass.userlog).toString());

                        addTaskArea.setText("");
                        titleField.setText("");
                        typeField.setText("");
                        jComboBox1.setSelectedIndex(0);
                        viewSchedRecords();

                        System.out.println("gumana 1");
                        titleFlag = 3;

                        preparedStatement.executeUpdate();
                        conn.close();
                        VariableClass.loadSched();
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
                System.out.println("gumana 3");
                viewSchedRecords();
            }

            viewSchedRecords();

            if (VariableClass.sched.contains(dateChooser1.getText())) {
                titleField.setText("" + VariableClass.title.get(VariableClass.sched.indexOf(dateChooser1.getText())));
            } else {
                titleField.setText("");
            }
        }
        equipmentAdd();
       
        VariableClass.loadSched();
    }//GEN-LAST:event_addBtnActionPerformed

    private void typeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeFieldActionPerformed

    private void typeFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeFieldKeyPressed

        // TODO add your handling code here:
    }//GEN-LAST:event_typeFieldKeyPressed

    private void typeFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeFieldKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_typeFieldKeyReleased

    private void titleFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titleFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleFieldKeyPressed

    private void titleFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_titleFieldKeyReleased
        String titleStr = titleField.getText();
        int titleCount = titleField.getText().length();

        int titleCtr = 20 - titleCount;
        if (titleCtr <= -1) {
            titleField.setText(titleStr.substring(0, 20));
        } else {
            titleTxtCount.setText(titleCtr + "/20");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_titleFieldKeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedIndex() == 3) {
            typeField.setEnabled(true);
            typeTxt.setEnabled(true);
            typeField.setText("");
        } else {
            typeField.setEnabled(false);
            typeTxt.setEnabled(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void schedTableHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_schedTableHierarchyChanged

    }//GEN-LAST:event_schedTableHierarchyChanged

    private void schedTableVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_schedTableVetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_schedTableVetoableChange

    private void schedTableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_schedTableFocusLost

        // TODO add your handling code here:
    }//GEN-LAST:event_schedTableFocusLost

    private void titleFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titleFieldMouseClicked
        schedTable.clearSelection();

        addBtn.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_titleFieldMouseClicked

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        schedTable.clearSelection();

        addBtn.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void typeFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeFieldMouseClicked
        schedTable.clearSelection();

        addBtn.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_typeFieldMouseClicked

    private void addTaskAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addTaskAreaMouseClicked
        schedTable.clearSelection();

        addBtn.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_addTaskAreaMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        schedTable.clearSelection();

        addBtn.setVisible(true);

        addTaskArea.setText("");
        titleField.setText("");
        jComboBox1.setSelectedIndex(0);

        if (VariableClass.sched.contains(dateChooser1.getText())) {
            titleField.setText("" + VariableClass.title.get(VariableClass.sched.indexOf(dateChooser1.getText())));
        } else {
            titleField.setText("");
        }

/// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void dateChooser1OnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dateChooser1OnCommit

        viewSchedRecords();

        if (VariableClass.sched.contains(dateChooser1.getText())) {
            titleField.setText("" + VariableClass.title.get(VariableClass.sched.indexOf(dateChooser1.getText())));
        } else {
            titleField.setText("");
        }

    }//GEN-LAST:event_dateChooser1OnCommit

    private void dateChooser1OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooser1OnSelectionChange
        addBtn.setVisible(true);

        addBtn.setVisible(true);


    }//GEN-LAST:event_dateChooser1OnSelectionChange

    private void jComboBox3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3MouseClicked

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed

        if (jComboBox3.getSelectedIndex() == 6) {
            typeField1.setEnabled(true);
            typeTxt1.setEnabled(true);
        } else {
            typeField1.setEnabled(false);
            typeTxt1.setEnabled(false);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void typeField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeField1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_typeField1MouseClicked

    private void typeField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeField1ActionPerformed

    private void typeField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeField1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeField1KeyPressed

    private void typeField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_typeField1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_typeField1KeyReleased

    private void jComboBox6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6MouseClicked

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jComboBox4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4MouseClicked

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

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
            java.util.logging.Logger.getLogger(AddSchedule.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSchedule.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSchedule.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSchedule.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddSchedule dialog = new AddSchedule(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton addBtn;
    public static javax.swing.JTextArea addTaskArea;
    private javax.swing.JLabel birthLabel1;
    public static javax.swing.JLabel birthLabel3;
    public static javax.swing.JLabel birthLabel5;
    public static javax.swing.JLabel birthLabel6;
    public static javax.swing.JLabel birthLabel7;
    public static javax.swing.JLabel birthLabel8;
    public static javax.swing.JLabel birthLabel9;
    public static datechooser.beans.DateChooserCombo dateChooser1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JComboBox<String> jComboBox1;
    public static javax.swing.JComboBox<String> jComboBox2;
    public static javax.swing.JComboBox<String> jComboBox3;
    public static javax.swing.JComboBox<String> jComboBox4;
    public static javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel locationLbl;
    public static javax.swing.JScrollPane pane1;
    public static javax.swing.JLabel pleaseLbl;
    public static javax.swing.JTable schedTable;
    public static javax.swing.JTextField titleField;
    public static javax.swing.JLabel titleTxtCount;
    public static javax.swing.JTextField typeField;
    public static javax.swing.JTextField typeField1;
    public static javax.swing.JLabel typeTxt;
    public static javax.swing.JLabel typeTxt1;
    // End of variables declaration//GEN-END:variables
}
