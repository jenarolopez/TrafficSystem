/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulePackage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Jen
 */
public class Report extends javax.swing.JDialog {

    public static int monthCtr = 0;

    public static void viewTaskRecord() {

        String col[] = new String[]{"Conducted By", "Type", "Equipment", "Equipment Location", "Detail"};
        DefaultTableModel model = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        Object rowData[] = new Object[5];

        for (int x = 0; x < VariableClass.sched.size(); x++) {
            String a = VariableClass.sched.get(x).toString();
            String month = "";
            String year = "";

            for (int t = 0; t < a.length(); t++) {

                if (a.charAt(t) == ' ') {

                    for (int u = t + 1; u < a.length(); u++) {

                        if (a.charAt(u) == ' ') {
                            u = a.length();
                        } else {
                            month += a.charAt(u) + "";
                        }

                    }
                    t = a.length();
                }

            }
            year = a.substring(a.length() - 4, a.length());
            month.trim();
            year.trim();
            if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("All")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("CM")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year) && VariableClass.type.get(x).equals("CM")) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("PM")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year) && VariableClass.type.get(x).equals("PM")) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("Job Offer")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year) && VariableClass.type.get(x).equals("Job Offer")) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("Other")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)) {

                    if (VariableClass.type.get(x).equals("PM") || VariableClass.type.get(x).equals("CM") || VariableClass.type.get(x).equals("Job Offer")) {

                    } else {
                        rowData[0] = VariableClass.conductedBy.get(x).toString();
                        rowData[1] = VariableClass.type.get(x).toString();
                        rowData[2] = VariableClass.equipment.get(x);
                        rowData[3] = VariableClass.equipmentLocation.get(x);
                        rowData[4] = VariableClass.task.get(x);

                        model.addRow(rowData);
                    }

                }
            } else if (location.getSelectedItem().toString().trim().equals("NLEX") && type.getSelectedItem().toString().trim().equals("All")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)
                        && VariableClass.location.get(x).equals("NLEX")) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("NLEX") && type.getSelectedItem().toString().trim().equals("CM")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)
                        && VariableClass.location.get(x).equals("NLEX") && VariableClass.type.get(x).equals("CM")) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("NLEX") && type.getSelectedItem().toString().trim().equals("PM")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)
                        && VariableClass.location.get(x).equals("NLEX") && VariableClass.type.get(x).equals("PM")) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("NLEX") && type.getSelectedItem().toString().trim().equals("Job Offer")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)
                        && VariableClass.location.get(x).equals("NLEX") && VariableClass.type.get(x).equals("Job Offer")) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("NLEX") && type.getSelectedItem().toString().trim().equals("Other")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)
                        && VariableClass.location.get(x).equals("NLEX")) {
                    if (VariableClass.type.get(x).equals("PM") || VariableClass.type.get(x).equals("CM") || VariableClass.type.get(x).equals("Job Offer")) {

                    } else {
                        rowData[0] = VariableClass.conductedBy.get(x).toString();
                        rowData[1] = VariableClass.type.get(x).toString();
                        rowData[2] = VariableClass.equipment.get(x);
                        rowData[3] = VariableClass.equipmentLocation.get(x);
                        rowData[4] = VariableClass.task.get(x);

                        model.addRow(rowData);
                    }

                }
            } else if (location.getSelectedItem().toString().trim().equals("SCTEX") && type.getSelectedItem().toString().trim().equals("All")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)
                        && VariableClass.location.get(x).equals("SCTEX")) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("SCTEX") && type.getSelectedItem().toString().trim().equals("CM")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)
                        && VariableClass.location.get(x).equals("SCTEX") && VariableClass.type.get(x).equals("CM")) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("SCTEX") && type.getSelectedItem().toString().trim().equals("PM")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)
                        && VariableClass.location.get(x).equals("SCTEX") && VariableClass.type.get(x).equals("PM")) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("SCTEX") && type.getSelectedItem().toString().trim().equals("Job Offer")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)
                        && VariableClass.location.get(x).equals("SCTEX") && VariableClass.type.get(x).equals("Job Offer")) {
                    rowData[0] = VariableClass.conductedBy.get(x).toString();
                    rowData[1] = VariableClass.type.get(x).toString();
                    rowData[2] = VariableClass.equipment.get(x);
                    rowData[3] = VariableClass.equipmentLocation.get(x);
                    rowData[4] = VariableClass.task.get(x);

                    model.addRow(rowData);
                }
            } else if (location.getSelectedItem().toString().trim().equals("SCTEX") && type.getSelectedItem().toString().trim().equals("Other")) {
                if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)
                        && VariableClass.location.get(x).equals("SCTEX")) {
                    if (VariableClass.type.get(x).equals("PM") || VariableClass.type.get(x).equals("CM") || VariableClass.type.get(x).equals("Job Offer")) {

                    } else {
                        rowData[0] = VariableClass.conductedBy.get(x).toString();
                        rowData[1] = VariableClass.type.get(x).toString();
                        rowData[2] = VariableClass.equipment.get(x);
                        rowData[3] = VariableClass.equipmentLocation.get(x);
                        rowData[4] = VariableClass.task.get(x);

                        model.addRow(rowData);
                    }

                }
            }

        }

        jTable1.setModel(model);
        jTable1.setDefaultRenderer(Object.class, new MyRenderer());

        TableColumnModel columnmodel = jTable1.getColumnModel();

        updateRowHeights();

    }

    public static void updateRowHeights() {
        for (int row = 0; row < jTable1.getRowCount(); row++) {
            int rowHeight = jTable1.getRowHeight();

            for (int column = 0; column < jTable1.getColumnCount(); column++) {
                Component comp = jTable1.prepareRenderer(jTable1.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

            jTable1.setRowHeight(row, rowHeight);
        }
    }

    public static class MyRenderer implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JTextArea editor = new JTextArea();
            if (value != null) {
                String tempmyString = value.toString();
                String myString = "";
                int limit = 0;
                for (int x = 0; x < tempmyString.length(); x++, limit++) {
                    if (limit >= 16) {
                        if (tempmyString.charAt(x) == ' ') {
                            limit = 0;
                            myString += "\n";
                        } else if (limit == 19) {
                            myString += "\n";
                            limit = 0;
                        }
                    }

                    myString += tempmyString.charAt(x);

                }

                editor.setText(myString);
            }

            if (isSelected == true) {
                editor.setBackground(Color.LIGHT_GRAY);
            } else {
                editor.setBackground((row % 2 == 0) ? Color.white : Color.white);
            }

            return editor;
        }

    }

    public Report(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setSize(770, 640);
        if (VariableClass.theresLogin == 1) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //mysql connector
                String company_db = "jdbc:mysql://localhost:3306/trafficsystems?user=root&password=admin";
                Connection connection = DriverManager.getConnection(company_db);
                String select = "\"";
                select += VariableClass.username.get(VariableClass.userlog);
                select += "\"";
                String mysql_query = "SELECT * FROM sched where schedIdentifier = " + select + "";

                Statement st = connection.createStatement(); // create the java statement
                ResultSet rs = st.executeQuery(mysql_query); // execute the query, and get a java resultset

                VariableClass.schedId.removeAll(VariableClass.schedId);
                VariableClass.sched.removeAll(VariableClass.sched);
                VariableClass.task.removeAll(VariableClass.task);
                VariableClass.title.removeAll(VariableClass.title);
                VariableClass.type.removeAll(VariableClass.type);
                VariableClass.conductedBy.removeAll(VariableClass.conductedBy);
                VariableClass.status.removeAll(VariableClass.status);
                VariableClass.schedIdentifier.removeAll(VariableClass.schedIdentifier);
                VariableClass.equipment.removeAll(VariableClass.equipment);
                VariableClass.location.removeAll(VariableClass.location);
                VariableClass.equipmentLocation.removeAll(VariableClass.equipmentLocation);
                VariableClass.imageSched.removeAll(VariableClass.imageSched);

                int count = 0;
                while (rs.next()) {
                    ++count; //count the results (rows)

                    VariableClass.schedId.add(rs.getInt("schedId"));
                    VariableClass.sched.add(rs.getString("sched"));
                    VariableClass.task.add(rs.getString("task"));
                    VariableClass.title.add(rs.getString("title"));
                    VariableClass.type.add(rs.getString("type"));
                    VariableClass.conductedBy.add(rs.getString("conductedBy"));
                    VariableClass.status.add(rs.getString("status"));
                    VariableClass.schedIdentifier.add(rs.getString("schedIdentifier"));
                    VariableClass.equipment.add(rs.getString("equipment"));
                    VariableClass.location.add(rs.getString("location"));
                    VariableClass.equipmentLocation.add(rs.getString("equipmentLocation"));
                    VariableClass.imageSched.add(rs.getString("imageSched"));

                }
                if (count == 0) {
                    System.out.println("No sched found!");
                }
                st.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Something wrong happened! ");
                System.out.println(e.getMessage());
            }

        } else {
            VariableClass.loadSched();
        }

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("NlexLogo1.png")));
        this.setTitle("Traffic Systems Engineering v.0.1");
        for (int year = 1800; year <= 2200; year++) {
            yearCombo.addItem(year + "");
        }
        Format formatterY1 = new SimpleDateFormat("yyyy");
        String year1 = formatterY1.format(new Date());
        yearCombo.setSelectedItem(year1.toString());
        Format format = new SimpleDateFormat("MM");
        String month = format.format(new Date());
        monthCtr = Integer.parseInt(month) - 1;
        monthLbl.setText(VariableClass.monthName.get(monthCtr).toString());
        viewTaskRecord();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        monthLbl = new javax.swing.JLabel();
        yearCombo = new javax.swing.JComboBox<>();
        jumpToday = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        location = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        type = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        type1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));

        jTable1.setBackground(new java.awt.Color(0, 51, 153));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jPanel6.setBackground(new java.awt.Color(0, 51, 153));

        jButton4.setText(">");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("<");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        monthLbl.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        monthLbl.setForeground(new java.awt.Color(255, 204, 0));
        monthLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        monthLbl.setText("jLabel1");

        yearCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                yearComboItemStateChanged(evt);
            }
        });
        yearCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearComboActionPerformed(evt);
            }
        });

        jumpToday.setText("Today");
        jumpToday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumpTodayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jumpToday, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(monthLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton4, jButton5});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(monthLbl)
                    .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jumpToday))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(0, 51, 153));

        location.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "NLEX", "SCTEX" }));
        location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 0));
        jLabel1.setText("Location:");

        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "CM", "PM", "Job Offer", "Other" }));
        type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                typeMouseClicked(evt);
            }
        });
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 0));
        jLabel2.setText("Type:");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 204, 0));
        jLabel3.setText("Status:");

        type1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Ongoing", "Complete" }));
        type1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                type1ItemStateChanged(evt);
            }
        });
        type1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                type1MouseClicked(evt);
            }
        });
        type1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(type1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(type1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (monthCtr == 11) {
            monthCtr = 0;

        } else {
            monthCtr++;
        }
        monthLbl.setText(VariableClass.monthName.get(monthCtr).toString());
        System.out.println(monthCtr);
        viewTaskRecord();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if (monthCtr == 0) {
            monthCtr = 11;

        } else {
            monthCtr--;
        }
        monthLbl.setText(VariableClass.monthName.get(monthCtr).toString());
        System.out.println(monthCtr);
        viewTaskRecord();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void yearComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_yearComboItemStateChanged

        // TODO add your handling code here:
    }//GEN-LAST:event_yearComboItemStateChanged

    private void jumpTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpTodayActionPerformed
        Format formatterY1 = new SimpleDateFormat("yyyy");
        String year1 = formatterY1.format(new Date());
        yearCombo.setSelectedItem(year1.toString());
        Format format = new SimpleDateFormat("MM");
        String month = format.format(new Date());
        monthCtr = Integer.parseInt(month) - 1;
        monthLbl.setText(VariableClass.monthName.get(monthCtr).toString());
        viewTaskRecord();
    }//GEN-LAST:event_jumpTodayActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void yearComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearComboActionPerformed
        viewTaskRecord();
        // TODO add your handling code here:
    }//GEN-LAST:event_yearComboActionPerformed

    private void locationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationActionPerformed
        viewTaskRecord();

        // TODO add your handling code here:
    }//GEN-LAST:event_locationActionPerformed

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        viewTaskRecord(); // TODO add your handling code here:
    }//GEN-LAST:event_typeActionPerformed

    private void typeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeMouseClicked
        viewTaskRecord();  // TODO add your handling code here:
    }//GEN-LAST:event_typeMouseClicked

    private void type1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_type1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_type1MouseClicked

    private void type1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_type1ActionPerformed

    private void type1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_type1ItemStateChanged
        if (type1.getSelectedItem().toString().trim().equals("Ongoing")) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //mysql connector
                String company_db = "jdbc:mysql://localhost:3306/trafficsystems?user=root&password=admin";
                Connection connection = DriverManager.getConnection(company_db);
                String mysql_query = "SELECT * FROM sched where status = \"Ongoing\"";

                if (VariableClass.theresLogin == 1) {
                    String select = "\"";
                    select += VariableClass.username.get(VariableClass.userlog);
                    select += "\"";
                    mysql_query = "SELECT * FROM sched where status = \"Ongoing\" AND schedIdentifier = " + select + " ";

                } else {
                    mysql_query = "SELECT * FROM sched where status = \"Ongoing\"";

                }

                Statement st = connection.createStatement(); // create the java statement
                ResultSet rs = st.executeQuery(mysql_query); // execute the query, and get a java resultset

                VariableClass.schedId.removeAll(VariableClass.schedId);
                VariableClass.sched.removeAll(VariableClass.sched);
                VariableClass.task.removeAll(VariableClass.task);
                VariableClass.title.removeAll(VariableClass.title);
                VariableClass.type.removeAll(VariableClass.type);
                VariableClass.conductedBy.removeAll(VariableClass.conductedBy);
                VariableClass.status.removeAll(VariableClass.status);
                VariableClass.schedIdentifier.removeAll(VariableClass.schedIdentifier);
                VariableClass.equipment.removeAll(VariableClass.equipment);
                VariableClass.location.removeAll(VariableClass.location);
                VariableClass.equipmentLocation.removeAll(VariableClass.equipmentLocation);
                VariableClass.imageSched.removeAll(VariableClass.imageSched);

                int count = 0;
                while (rs.next()) {
                    ++count; //count the results (rows)

                    VariableClass.schedId.add(rs.getInt("schedId"));
                    VariableClass.sched.add(rs.getString("sched"));
                    VariableClass.task.add(rs.getString("task"));
                    VariableClass.title.add(rs.getString("title"));
                    VariableClass.type.add(rs.getString("type"));
                    VariableClass.conductedBy.add(rs.getString("conductedBy"));
                    VariableClass.status.add(rs.getString("status"));
                    VariableClass.schedIdentifier.add(rs.getString("schedIdentifier"));
                    VariableClass.equipment.add(rs.getString("equipment"));
                    VariableClass.location.add(rs.getString("location"));
                    VariableClass.equipmentLocation.add(rs.getString("equipmentLocation"));
                    VariableClass.imageSched.add(rs.getString("imageSched"));

                }
                if (count == 0) {
                    System.out.println("No sched found!");
                }
                st.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Something wrong happened! ");
                System.out.println(e.getMessage());
            }

        } else if (type1.getSelectedItem().toString().trim().equals("Complete")) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //mysql connector
                String company_db = "jdbc:mysql://localhost:3306/trafficsystems?user=root&password=admin";
                Connection connection = DriverManager.getConnection(company_db);
                String mysql_query = "SELECT * FROM sched where status = \"Complete\"";
                if (VariableClass.theresLogin == 1) {
                    String select = "\"";
                    select += VariableClass.username.get(VariableClass.userlog);
                    select += "\"";
                    mysql_query = "SELECT * FROM sched where status = \"Complete\" AND schedIdentifier = " + select + " ";

                } else {
                    mysql_query = "SELECT * FROM sched where status = \"Complete\"";

                }
                Statement st = connection.createStatement(); // create the java statement
                ResultSet rs = st.executeQuery(mysql_query); // execute the query, and get a java resultset

                VariableClass.schedId.removeAll(VariableClass.schedId);
                VariableClass.sched.removeAll(VariableClass.sched);
                VariableClass.task.removeAll(VariableClass.task);
                VariableClass.title.removeAll(VariableClass.title);
                VariableClass.type.removeAll(VariableClass.type);
                VariableClass.conductedBy.removeAll(VariableClass.conductedBy);
                VariableClass.status.removeAll(VariableClass.status);
                VariableClass.schedIdentifier.removeAll(VariableClass.schedIdentifier);
                VariableClass.equipment.removeAll(VariableClass.equipment);
                VariableClass.location.removeAll(VariableClass.location);
                VariableClass.equipmentLocation.removeAll(VariableClass.equipmentLocation);
                VariableClass.imageSched.removeAll(VariableClass.imageSched);

                int count = 0;
                while (rs.next()) {
                    ++count; //count the results (rows)

                    VariableClass.schedId.add(rs.getInt("schedId"));
                    VariableClass.sched.add(rs.getString("sched"));
                    VariableClass.task.add(rs.getString("task"));
                    VariableClass.title.add(rs.getString("title"));
                    VariableClass.type.add(rs.getString("type"));
                    VariableClass.conductedBy.add(rs.getString("conductedBy"));
                    VariableClass.status.add(rs.getString("status"));
                    VariableClass.schedIdentifier.add(rs.getString("schedIdentifier"));
                    VariableClass.equipment.add(rs.getString("equipment"));
                    VariableClass.location.add(rs.getString("location"));
                    VariableClass.equipmentLocation.add(rs.getString("equipmentLocation"));
                    VariableClass.imageSched.add(rs.getString("imageSched"));

                }
                if (count == 0) {
                    System.out.println("No sched found!");
                }
                st.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Something wrong happened! ");
                System.out.println(e.getMessage());
            }

        } else if (type1.getSelectedItem().toString().trim().equals("All")) {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //mysql connector
                String company_db = "jdbc:mysql://localhost:3306/trafficsystems?user=root&password=admin";
                Connection connection = DriverManager.getConnection(company_db);
                String mysql_query = "SELECT * FROM sched where status = \"Complete\"";
                if (VariableClass.theresLogin == 1) {
                    String select = "\"";
                    select += VariableClass.username.get(VariableClass.userlog);
                    select += "\"";
                    mysql_query = "SELECT * FROM sched where schedIdentifier = " + select + " ";

                } else {
                    mysql_query = "SELECT * FROM sched";

                }
                Statement st = connection.createStatement(); // create the java statement
                ResultSet rs = st.executeQuery(mysql_query); // execute the query, and get a java resultset

                VariableClass.schedId.removeAll(VariableClass.schedId);
                VariableClass.sched.removeAll(VariableClass.sched);
                VariableClass.task.removeAll(VariableClass.task);
                VariableClass.title.removeAll(VariableClass.title);
                VariableClass.type.removeAll(VariableClass.type);
                VariableClass.conductedBy.removeAll(VariableClass.conductedBy);
                VariableClass.status.removeAll(VariableClass.status);
                VariableClass.schedIdentifier.removeAll(VariableClass.schedIdentifier);
                VariableClass.equipment.removeAll(VariableClass.equipment);
                VariableClass.location.removeAll(VariableClass.location);
                VariableClass.equipmentLocation.removeAll(VariableClass.equipmentLocation);
                VariableClass.imageSched.removeAll(VariableClass.imageSched);

                int count = 0;
                while (rs.next()) {
                    ++count; //count the results (rows)

                    VariableClass.schedId.add(rs.getInt("schedId"));
                    VariableClass.sched.add(rs.getString("sched"));
                    VariableClass.task.add(rs.getString("task"));
                    VariableClass.title.add(rs.getString("title"));
                    VariableClass.type.add(rs.getString("type"));
                    VariableClass.conductedBy.add(rs.getString("conductedBy"));
                    VariableClass.status.add(rs.getString("status"));
                    VariableClass.schedIdentifier.add(rs.getString("schedIdentifier"));
                    VariableClass.equipment.add(rs.getString("equipment"));
                    VariableClass.location.add(rs.getString("location"));
                    VariableClass.equipmentLocation.add(rs.getString("equipmentLocation"));
                    VariableClass.imageSched.add(rs.getString("imageSched"));

                }
                if (count == 0) {
                    System.out.println("No sched found!");
                }
                st.close();
                connection.close();
            } catch (Exception e) {
                System.out.println("Something wrong happened! ");
                System.out.println(e.getMessage());
            }

        }
        viewTaskRecord(); // TODO add your handling code here:
    }//GEN-LAST:event_type1ItemStateChanged

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
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Report dialog = new Report(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JButton jumpToday;
    public static javax.swing.JComboBox<String> location;
    public static javax.swing.JLabel monthLbl;
    public static javax.swing.JComboBox<String> type;
    public static javax.swing.JComboBox<String> type1;
    public static javax.swing.JComboBox<String> yearCombo;
    // End of variables declaration//GEN-END:variables
}
