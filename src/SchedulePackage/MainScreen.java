package SchedulePackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class MainScreen extends javax.swing.JFrame {

    public static String path = "";
    public static String vlcPath = "C:/Program Files/VideoLAN/VLC";
//player
    public static SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
    public static String stringDate2 = sdf2.format(new Date());
    public static Format formatterM = new SimpleDateFormat("MMMM");
    public static String month = formatterM.format(new Date());
    public static Format formatterD = new SimpleDateFormat("dd");
    public static String day = formatterD.format(new Date());
    public static Format formatterY = new SimpleDateFormat("yyyy");
    public static String year = formatterY.format(new Date());
    public static String dateToday = "";

//myCalendar-----------------------
    public static DateFormat dayFormat = new SimpleDateFormat("dd");
    public static DateFormat monthFormat = new SimpleDateFormat("MM");
    public static DateFormat yearFormat = new SimpleDateFormat("YYYY");
    public static Calendar cal1 = Calendar.getInstance();
    public static int dayToday = Integer.parseInt(dayFormat.format(cal1.getTime()));
    public static int monthToday = Integer.parseInt(monthFormat.format(cal1.getTime()));
    public static int yearToday = Integer.parseInt(yearFormat.format(cal1.getTime()));
//---------------------------------
    public static int calendarCtr = 0;
    public static JTextArea[] calBtn = new JTextArea[42];
    public static JPanel[] containerPnl = new JPanel[42];
    public static JLabel[] containerLbl = new JLabel[42];
    public static JButton[] dayBtn = new JButton[7];

    public static int addScheduleCtr = 1;
//timer

    Timer timer;
    Timer timer2;

    public static void mainScreenSelect() {
        VariableClass.mainScreenTbl = taskTable.getSelectedRow();

        if (VariableClass.mainScreenTbl == 0) {
            for (int x = 0, y = 0; x < VariableClass.sched.size(); x++) {
                if (dateToday.equals(VariableClass.sched.get(x).toString().trim())) {
                    VariableClass.mainScreenSelected = x;
                    break;
                }
            }
        } else {

            for (int x = 0, y = 0; x < VariableClass.sched.size(); x++) {
                if (dateToday.equals(VariableClass.sched.get(x).toString().trim())) {

                    System.out.println(taskTable.getSelectedRow() + "" + y);
                    if (y == taskTable.getSelectedRow()) {

                        VariableClass.mainScreenSelected = x;
                        break;
                    }
                    y++;
                }

            }
        }

    }

    public static void updateRowHeights() {
        for (int row = 0; row < taskTable.getRowCount(); row++) {
            int rowHeight = taskTable.getRowHeight();

            for (int column = 0; column < taskTable.getColumnCount(); column++) {
                Component comp = taskTable.prepareRenderer(taskTable.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

            taskTable.setRowHeight(row, rowHeight);
        }
    }

    public static void viewTaskRecords() {
        String col[] = new String[]{"Conducted By", "Type", "Equipment", "Equipment Location", "Detail", "Status"};
        DefaultTableModel model = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        Object rowData[] = new Object[6];
        for (int x = 0; x < VariableClass.task.size(); x++) {
            if (VariableClass.schedIdentifier.get(x).equals(VariableClass.username.get(VariableClass.userlog))) {
                if (dateToday.equals(VariableClass.sched.get(x).toString())) {

                    if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("All")) {
                        if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)) {
                            rowData[0] = VariableClass.conductedBy.get(x).toString();
                            rowData[1] = VariableClass.type.get(x).toString();
                            rowData[2] = VariableClass.equipment.get(x);
                            rowData[3] = VariableClass.equipmentLocation.get(x);
                            rowData[4] = VariableClass.task.get(x);
                            rowData[5] = VariableClass.status.get(x).toString();
                            model.addRow(rowData);
                        }
                    } else if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("CM")) {
                        if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year) && VariableClass.type.get(x).equals("CM")) {
                            rowData[0] = VariableClass.conductedBy.get(x).toString();
                            rowData[1] = VariableClass.type.get(x).toString();
                            rowData[2] = VariableClass.equipment.get(x);
                            rowData[3] = VariableClass.equipmentLocation.get(x);
                            rowData[4] = VariableClass.task.get(x);
                            rowData[5] = VariableClass.status.get(x).toString();
                            model.addRow(rowData);
                        }
                    } else if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("PM")) {
                        if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year) && VariableClass.type.get(x).equals("PM")) {
                            rowData[0] = VariableClass.conductedBy.get(x).toString();
                            rowData[1] = VariableClass.type.get(x).toString();
                            rowData[2] = VariableClass.equipment.get(x);
                            rowData[3] = VariableClass.equipmentLocation.get(x);
                            rowData[4] = VariableClass.task.get(x);
                            rowData[5] = VariableClass.status.get(x).toString();
                            model.addRow(rowData);
                        }
                    } else if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("Job Offer")) {
                        if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year) && VariableClass.type.get(x).equals("Job Offer")) {
                            rowData[0] = VariableClass.conductedBy.get(x).toString();
                            rowData[1] = VariableClass.type.get(x).toString();
                            rowData[2] = VariableClass.equipment.get(x);
                            rowData[3] = VariableClass.equipmentLocation.get(x);
                            rowData[4] = VariableClass.task.get(x);
                            rowData[5] = VariableClass.status.get(x).toString();
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
                                rowData[5] = VariableClass.status.get(x).toString();
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
                            rowData[5] = VariableClass.status.get(x).toString();
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
                            rowData[5] = VariableClass.status.get(x).toString();
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
                            rowData[5] = VariableClass.status.get(x).toString();
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
                            rowData[5] = VariableClass.status.get(x).toString();
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
                                rowData[5] = VariableClass.status.get(x).toString();
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
                            rowData[5] = VariableClass.status.get(x).toString();
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
                            rowData[5] = VariableClass.status.get(x).toString();
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
                            rowData[5] = VariableClass.status.get(x).toString();
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
                            rowData[5] = VariableClass.status.get(x).toString();
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
                                rowData[5] = VariableClass.status.get(x).toString();
                                model.addRow(rowData);
                            }

                        }
                    }

                }
            }

        }

        taskTable.setModel(model);
        TableColumnModel columnmodel = taskTable.getColumnModel();
        taskTable.setRowHeight(27);
        taskTable.setDefaultRenderer(Object.class, new MyRenderer());
        todayLabel.setText("Todays Task: As of " + dateToday);
        updateRowHeights();
        if (VariableClass.userlog == 0) {
            adminLog();

        } else if (taskTable.getRowCount() == 0) {
            jScrollPane2.setVisible(false);
            jPanel9.setVisible(true);
            jPanel7.setVisible(false);
            viewMoto.setEnabled(false);
            deleteMoto.setEnabled(false);
            changeMoto.setEnabled(false);
            editMoto.setEnabled(false);
        } else {
            location.setVisible(true);
            type.setVisible(true);
            jLabel2.setVisible(true);
            jLabel3.setVisible(true);
            jScrollPane2.setVisible(true);
            jPanel9.setVisible(false);
            viewMoto.setEnabled(false);
            deleteMoto.setEnabled(false);
            changeMoto.setEnabled(false);
            editMoto.setEnabled(false);
        }

    }

    public static void adminLog() {
        String col[] = new String[]{"Conducted By", "Type", "Equipment", "Equipment Location", "Detail", "Status"};
        DefaultTableModel model = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        Object rowData[] = new Object[6];
        for (int x = 0; x < VariableClass.task.size(); x++) {
            if (dateToday.equals(VariableClass.sched.get(x).toString())) {

                if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("All")) {
                    if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year)) {
                        rowData[0] = VariableClass.conductedBy.get(x).toString();
                        rowData[1] = VariableClass.type.get(x).toString();
                        rowData[2] = VariableClass.equipment.get(x);
                        rowData[3] = VariableClass.equipmentLocation.get(x);
                        rowData[4] = VariableClass.task.get(x);
                        rowData[5] = VariableClass.status.get(x).toString();
                        model.addRow(rowData);
                    }
                } else if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("CM")) {
                    if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year) && VariableClass.type.get(x).equals("CM")) {
                        rowData[0] = VariableClass.conductedBy.get(x).toString();
                        rowData[1] = VariableClass.type.get(x).toString();
                        rowData[2] = VariableClass.equipment.get(x);
                        rowData[3] = VariableClass.equipmentLocation.get(x);
                        rowData[4] = VariableClass.task.get(x);
                        rowData[5] = VariableClass.status.get(x).toString();
                        model.addRow(rowData);
                    }
                } else if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("PM")) {
                    if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year) && VariableClass.type.get(x).equals("PM")) {
                        rowData[0] = VariableClass.conductedBy.get(x).toString();
                        rowData[1] = VariableClass.type.get(x).toString();
                        rowData[2] = VariableClass.equipment.get(x);
                        rowData[3] = VariableClass.equipmentLocation.get(x);
                        rowData[4] = VariableClass.task.get(x);
                        rowData[5] = VariableClass.status.get(x).toString();
                        model.addRow(rowData);
                    }
                } else if (location.getSelectedItem().toString().trim().equals("All") && type.getSelectedItem().toString().trim().equals("Job Offer")) {
                    if (monthLbl.getText().trim().equals(month) && yearCombo.getSelectedItem().toString().trim().equals(year) && VariableClass.type.get(x).equals("Job Offer")) {
                        rowData[0] = VariableClass.conductedBy.get(x).toString();
                        rowData[1] = VariableClass.type.get(x).toString();
                        rowData[2] = VariableClass.equipment.get(x);
                        rowData[3] = VariableClass.equipmentLocation.get(x);
                        rowData[4] = VariableClass.task.get(x);
                        rowData[5] = VariableClass.status.get(x).toString();
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
                            rowData[5] = VariableClass.status.get(x).toString();
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
                        rowData[5] = VariableClass.status.get(x).toString();
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
                        rowData[5] = VariableClass.status.get(x).toString();
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
                        rowData[5] = VariableClass.status.get(x).toString();
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
                        rowData[5] = VariableClass.status.get(x).toString();
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
                            rowData[5] = VariableClass.status.get(x).toString();
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
                        rowData[5] = VariableClass.status.get(x).toString();
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
                        rowData[5] = VariableClass.status.get(x).toString();
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
                        rowData[5] = VariableClass.status.get(x).toString();
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
                        rowData[5] = VariableClass.status.get(x).toString();
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
                            rowData[5] = VariableClass.status.get(x).toString();
                            model.addRow(rowData);
                        }

                    }
                }

            }

        }

        taskTable.setModel(model);
        TableColumnModel columnmodel = taskTable.getColumnModel();

        taskTable.setDefaultRenderer(Object.class, new MyRenderer());
        todayLabel.setText("Todays Task: As of " + dateToday);
        updateRowHeights();
        if (taskTable.getRowCount() == 0) {
            jScrollPane2.setVisible(false);
            jPanel9.setVisible(true);
            jPanel7.setVisible(false);
            viewMoto.setEnabled(false);
            deleteMoto.setEnabled(false);
            changeMoto.setEnabled(false);
            editMoto.setEnabled(false);

        } else {
            location.setVisible(true);
            type.setVisible(true);
            jLabel2.setVisible(true);
            jLabel3.setVisible(true);
            jScrollPane2.setVisible(true);
            jPanel9.setVisible(false);
            viewMoto.setEnabled(false);
            deleteMoto.setEnabled(false);
            changeMoto.setEnabled(false);
            editMoto.setEnabled(false);
        }

    }
//class MyRenderer implements TableCellRenderer {
//  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//      boolean hasFocus, int row, int column) {

//  }
//}
    public static void myCalendar() {
        jPanel4.removeAll();
        jPanel5.removeAll();
        dayBtn[0] = new JButton("Sun");
        dayBtn[1] = new JButton("Mon");
        dayBtn[2] = new JButton("Tue");
        dayBtn[3] = new JButton("Wed");
        dayBtn[4] = new JButton("Thu");
        dayBtn[5] = new JButton("Fri");
        dayBtn[6] = new JButton("Sat");

        for (int x = 0; x < 7; x++) {
            int ctr = x;
            jPanel5.add(dayBtn[x]);

        }
        jPanel5.setLayout(new GridLayout(1, 7));
//----------------------------------------------------------
        yearToday = Integer.parseInt(yearCombo.getSelectedItem().toString());
        cal1.set(Calendar.DATE, dayToday);
        cal1.set(Calendar.MONTH, monthToday - 1);
        cal1.set(Calendar.YEAR, yearToday);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = cal1.getTime();
        DateFormat sdf1 = new SimpleDateFormat("EEEEEEEE");
        YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(yearCombo.getSelectedItem().toString()), monthToday);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        System.out.println(daysInMonth);
        System.out.println("First Day of Month: " + sdf1.format(firstDayOfMonth));//check if true
//calendar

//calendar number and cells        
        for (int x = 0, y = 1; x < calBtn.length; x++) {
            int z = y;
            containerPnl[x] = new JPanel();
            containerPnl[x].setLayout(null);
            calBtn[x] = new JTextArea();
            calBtn[x].setLineWrap(true);

            calBtn[x].setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            calBtn[x].setSize(29, 60);
            containerPnl[x].add(calBtn[x]);
            containerLbl[x] = new JLabel(y + "");
            containerPnl[x].add(containerLbl[x]);
            Insets insets = containerPnl[x].getInsets();
            Dimension size = containerLbl[x].getPreferredSize();
            size = calBtn[x].getPreferredSize();
            calBtn[x].setBounds(0 + insets.right, 0 + insets.top,
                    size.width + 74, size.height + 38);

            if (sdf1.format(firstDayOfMonth).equals("Sunday")) {
                if (x >= 0 && y <= daysInMonth) {
                    if (day.toString().charAt(0) == '0') {
                        String a = day.charAt(1) + "";
                        day = a;
                    }

                    if (VariableClass.monthName.get(monthToday - 1).toString().equals(month) && yearCombo.getItemAt(yearCombo.getSelectedIndex()).equals(year)) {
                        if (containerLbl[x].getText().equals(day)) {
                            calBtn[x].setBackground(new java.awt.Color(255, 204, 0));
                        }

                    }
                    containerLbl[x].setBounds(5 + insets.left, 40 + insets.bottom, size.width + 20, size.height);
                    containerPnl[x].add(containerLbl[x]);
                    calBtn[x].addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jTextArea1MouseClicked(evt);
                        }

                        private void jTextArea1MouseClicked(MouseEvent evt) {
                            calendarCtr = z;
                            viewCal.setVisible(true);
                            addCal.setVisible(true);
                            delCal.setVisible(true);
                            cancelBtn_Main.setVisible(true);
                            delCal1.setVisible(true);
                            if (VariableClass.theresLogin == 0) {
                                addCal.setEnabled(false);
                                delCal1.setEnabled(false);
                                delCal.setEnabled(false);

                            } else {
                                addCal.setEnabled(true);
                                delCal1.setEnabled(true);
                                delCal.setEnabled(true);
                            }
                        }
                    });
                    y++;
                } else {
                    calBtn[x].setBackground(Color.gray);
                }
            } else if (sdf1.format(firstDayOfMonth).equals("Monday")) {
                if (x >= 1 && y <= daysInMonth) {
                    if (day.toString().charAt(0) == '0') {
                        String a = day.charAt(1) + "";
                        day = a;
                    }

                    if (VariableClass.monthName.get(monthToday - 1).toString().equals(month) && yearCombo.getItemAt(yearCombo.getSelectedIndex()).equals(year)) {
                        if (containerLbl[x].getText().equals(day)) {
                            calBtn[x].setBackground(new java.awt.Color(255, 204, 0));
                        }

                    }
                    containerLbl[x].setBounds(5 + insets.left, 40 + insets.bottom, size.width + 20, size.height);
                    containerPnl[x].add(containerLbl[x]);
                    calBtn[x].addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jTextArea1MouseClicked(evt);
                        }

                        private void jTextArea1MouseClicked(MouseEvent evt) {
                            calendarCtr = z;
                            viewCal.setVisible(true);
                            addCal.setVisible(true);
                            delCal.setVisible(true);
                            delCal1.setVisible(true);
                            cancelBtn_Main.setVisible(true);
                            if (VariableClass.theresLogin == 0) {
                                addCal.setEnabled(false);
                                delCal1.setEnabled(false);
                                delCal.setEnabled(false);

                            } else {
                                addCal.setEnabled(true);
                                delCal1.setEnabled(true);
                                delCal.setEnabled(true);
                            }
                        }
                    });
                    y++;
                } else {
                    calBtn[x].setBackground(Color.gray);
                }
            } else if (sdf1.format(firstDayOfMonth).equals("Tuesday")) {
                if (x >= 2 && y <= daysInMonth) {
                    if (day.toString().charAt(0) == '0') {
                        String a = day.charAt(1) + "";
                        day = a;
                    }

                    if (VariableClass.monthName.get(monthToday - 1).toString().equals(month) && yearCombo.getItemAt(yearCombo.getSelectedIndex()).equals(year)) {
                        if (containerLbl[x].getText().equals(day)) {
                            calBtn[x].setBackground(new java.awt.Color(255, 204, 0));
                        }

                    }
                    containerLbl[x].setBounds(5 + insets.left, 40 + insets.bottom, size.width + 20, size.height);
                    containerPnl[x].add(containerLbl[x]);
                    calBtn[x].addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jTextArea1MouseClicked(evt);
                        }

                        private void jTextArea1MouseClicked(MouseEvent evt) {
                            calendarCtr = z;
                            viewCal.setVisible(true);
                            addCal.setVisible(true);
                            delCal.setVisible(true);
                            delCal1.setVisible(true);
                            cancelBtn_Main.setVisible(true);
                            if (VariableClass.theresLogin == 0) {
                                addCal.setEnabled(false);
                                delCal1.setEnabled(false);
                                delCal.setEnabled(false);

                            } else {
                                addCal.setEnabled(true);
                                delCal1.setEnabled(true);
                                delCal.setEnabled(true);
                            }
                        }
                    });
                    y++;
                } else {
                    calBtn[x].setBackground(Color.gray);
                }
            } else if (sdf1.format(firstDayOfMonth).equals("Wednesday")) {
                if (x >= 3 && y <= daysInMonth) {
                    if (day.toString().charAt(0) == '0') {
                        String a = day.charAt(1) + "";
                        day = a;
                    }

                    if (VariableClass.monthName.get(monthToday - 1).toString().equals(month) && yearCombo.getItemAt(yearCombo.getSelectedIndex()).equals(year)) {
                        if (containerLbl[x].getText().equals(day)) {
                            calBtn[x].setBackground(new java.awt.Color(255, 204, 0));
                        }

                    }
                    containerLbl[x].setBounds(5 + insets.left, 40 + insets.bottom, size.width + 20, size.height);
                    containerPnl[x].add(containerLbl[x]);
                    calBtn[x].addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jTextArea1MouseClicked(evt);
                        }

                        private void jTextArea1MouseClicked(MouseEvent evt) {
                            calendarCtr = z;
                            viewCal.setVisible(true);
                            addCal.setVisible(true);
                            delCal.setVisible(true);
                            delCal1.setVisible(true);
                            cancelBtn_Main.setVisible(true);
                            if (VariableClass.theresLogin == 0) {
                                addCal.setEnabled(false);
                                delCal1.setEnabled(false);
                                delCal.setEnabled(false);

                            } else {
                                addCal.setEnabled(true);
                                delCal1.setEnabled(true);
                                delCal.setEnabled(true);
                            }
                        }
                    });
                    y++;
                } else {
                    calBtn[x].setBackground(Color.gray);
                }
            } else if (sdf1.format(firstDayOfMonth).equals("Thursday")) {
                if (x >= 4 && y <= daysInMonth) {
                    if (day.toString().charAt(0) == '0') {
                        String a = day.charAt(1) + "";
                        day = a;
                    }

                    if (VariableClass.monthName.get(monthToday - 1).toString().equals(month) && yearCombo.getItemAt(yearCombo.getSelectedIndex()).equals(year)) {
                        if (containerLbl[x].getText().equals(day)) {
                            calBtn[x].setBackground(new java.awt.Color(255, 204, 0));
                        }

                    }
                    containerLbl[x].setBounds(5 + insets.left, 40 + insets.bottom, size.width + 20, size.height);
                    containerPnl[x].add(containerLbl[x]);
                    calBtn[x].addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jTextArea1MouseClicked(evt);
                        }

                        private void jTextArea1MouseClicked(MouseEvent evt) {
                            calendarCtr = z;
                            viewCal.setVisible(true);
                            delCal.setVisible(true);
                            addCal.setVisible(true);
                            cancelBtn_Main.setVisible(true);
                            delCal1.setVisible(true);
                            if (VariableClass.theresLogin == 0) {
                                addCal.setEnabled(false);
                                delCal1.setEnabled(false);
                                delCal.setEnabled(false);

                            } else {
                                addCal.setEnabled(true);
                                delCal1.setEnabled(true);
                                delCal.setEnabled(true);
                            }
                        }
                    });
                    y++;
                } else {
                    calBtn[x].setBackground(Color.gray);
                }
            } else if (sdf1.format(firstDayOfMonth).equals("Friday")) {
                if (x >= 5 && y <= daysInMonth) {
                    if (day.toString().charAt(0) == '0') {
                        String a = day.charAt(1) + "";
                        day = a;
                    }

                    if (VariableClass.monthName.get(monthToday - 1).toString().equals(month) && yearCombo.getItemAt(yearCombo.getSelectedIndex()).equals(year)) {
                        if (containerLbl[x].getText().equals(day)) {
                            calBtn[x].setBackground(new java.awt.Color(255, 204, 0));
                        }

                    }
                    containerLbl[x].setBounds(5 + insets.left, 40 + insets.bottom, size.width + 20, size.height);
                    containerPnl[x].add(containerLbl[x]);
                    calBtn[x].addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jTextArea1MouseClicked(evt);
                        }

                        private void jTextArea1MouseClicked(MouseEvent evt) {
                            calendarCtr = z;
                            viewCal.setVisible(true);
                            delCal.setVisible(true);
                            addCal.setVisible(true);
                            delCal1.setVisible(true);
                            cancelBtn_Main.setVisible(true);
                            if (VariableClass.theresLogin == 0) {
                                addCal.setEnabled(false);
                                delCal1.setEnabled(false);
                                delCal.setEnabled(false);

                            } else {
                                addCal.setEnabled(true);
                                delCal1.setEnabled(true);
                                delCal.setEnabled(true);
                            }
                        }
                    });
                    y++;
                } else {
                    calBtn[x].setBackground(Color.gray);
                }
            } else if (sdf1.format(firstDayOfMonth).equals("Saturday")) {
                if (x >= 6 && y <= daysInMonth) {
                    if (day.toString().charAt(0) == '0') {
                        String a = day.charAt(1) + "";
                        day = a;
                    }

                    if (VariableClass.monthName.get(monthToday - 1).toString().equals(month) && yearCombo.getItemAt(yearCombo.getSelectedIndex()).equals(year)) {
                        if (containerLbl[x].getText().equals(day)) {
                            calBtn[x].setBackground(new java.awt.Color(255, 204, 0));
                        }

                    }
                    containerLbl[x].setBounds(5 + insets.left, 40 + insets.bottom, size.width + 20, size.height);
                    containerPnl[x].add(containerLbl[x]);
                    calBtn[x].addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            jTextArea1MouseClicked(evt);
                        }

                        private void jTextArea1MouseClicked(MouseEvent evt) {
                            calendarCtr = z;
                            viewCal.setVisible(true);
                            delCal.setVisible(true);
                            addCal.setVisible(true);
                            delCal1.setVisible(true);
                            cancelBtn_Main.setVisible(true);
                            if (VariableClass.theresLogin == 0) {
                                addCal.setEnabled(false);
                                delCal1.setEnabled(false);
                                delCal.setEnabled(false);

                            } else {
                                addCal.setEnabled(true);
                                delCal1.setEnabled(true);
                                delCal.setEnabled(true);
                            }

                        }
                    });
                    y++;
                } else {
                    calBtn[x].setBackground(Color.gray);
                }
            }

            calBtn[x].setEditable(false);
            containerPnl[x].add(calBtn[x]);
            jPanel4.add(containerPnl[x]);
        }

        jPanel4.setLayout(new GridLayout(6, 7));
        monthLbl.setText(VariableClass.monthName.get(monthToday - 1).toString());
        //----------------------------------------------------------------------------------

        String splitDate[] = new String[4];
        splitDate[0] = "";
        splitDate[1] = "";
        splitDate[2] = "";
        splitDate[3] = "";

        for (int x = 0; x < VariableClass.sched.size(); x++) {

            String tempCutDate = VariableClass.sched.get(x).toString();
            String cutDate = "";

            for (int ctr = 0; ctr < tempCutDate.length(); ctr++) {
                if (tempCutDate.charAt(ctr) == ',') {

                    for (int ctr1 = ctr + 2; ctr1 < tempCutDate.length(); ctr1++) {
                        cutDate += tempCutDate.charAt(ctr1) + "";
                    }
                    break;
                }
            }
            for (int ctr = 0, ctr1 = 0; ctr < cutDate.length(); ctr++) {
                if (cutDate.charAt(ctr) == ',' || cutDate.charAt(ctr) == ' ') {

                    ctr1++;
                } else {

                    splitDate[ctr1] += cutDate.charAt(ctr);
                }
            }
            int y = 0;
            if (sdf1.format(firstDayOfMonth).equals("Sunday")) {
                y = 0;
            } else if (sdf1.format(firstDayOfMonth).equals("Monday")) {
                y = 1;
            } else if (sdf1.format(firstDayOfMonth).equals("Tuesday")) {
                y = 2;
            } else if (sdf1.format(firstDayOfMonth).equals("Wednesday")) {
                y = 3;
            } else if (sdf1.format(firstDayOfMonth).equals("Thursday")) {
                y = 4;
            } else if (sdf1.format(firstDayOfMonth).equals("Friday")) {
                y = 5;
            } else if (sdf1.format(firstDayOfMonth).equals("Saturday")) {
                y = 6;
            }
            for (; y < containerLbl.length; y++) {
                if (containerLbl[y].getText().equals(splitDate[1]) && yearCombo.getSelectedItem().equals(splitDate[3])
                        && monthLbl.getText().equals(splitDate[0])) {
                    calBtn[y].setText(VariableClass.title.get(x).toString());

                }
            }

            splitDate[0] = "";
            splitDate[1] = "";
            splitDate[2] = "";
            splitDate[3] = "";

        }

//        ---------------------------------------------
        jPanel4.revalidate();
        jPanel5.revalidate();
    }

    public static class MyRenderer implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JTextArea editor = new JTextArea();
            if (value != null) {
                String tempmyString = value.toString();
                String myString = "";
                int limit = 0;
                for (int x = 0; x < tempmyString.length(); x++, limit++) {
                    if (limit >= 15) {
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

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(imgLbl.getWidth(), imgLbl.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("NlexLogo1.png")));
    }

    public MainScreen() {
        initComponents();
        jButton1.setBackground(new java.awt.Color(240, 240, 240));
        jButton2.setBackground(new java.awt.Color(255, 204, 0));
        jButton3.setBackground(new java.awt.Color(240, 240, 240));
        this.setTitle("Traffic Systems Engineering v.0.1");
        setIcon();
        if (VariableClass.theresLogin == 1) {
            login.setVisible(false);
            editAcc.setVisible(true);
            logout.setVisible(true);
            registerAcc.setVisible(true);
            nameLbl.setText(VariableClass.fname.get(VariableClass.userlog).toString());
            jButton1.setVisible(true);
            imgLbl.setIcon(ResizeImage(VariableClass.imgUser.get(VariableClass.userlog).toString()));
            status.setVisible(false);

        } else if (VariableClass.theresLogin == 3) {
            login.setVisible(false);
            editAcc.setVisible(true);
            logout.setVisible(true);
            registerAcc.setVisible(true);
            nameLbl.setText(VariableClass.fname.get(VariableClass.userlog).toString());
            jButton1.setVisible(true);
            imgLbl.setIcon(ResizeImage(VariableClass.imgUser.get(VariableClass.userlog).toString()));
            status.setVisible(true);
        } else {
            login.setVisible(true);
            editAcc.setVisible(false);
            logout.setVisible(false);
            registerAcc.setVisible(true);
            nameLbl.setText("Please Login");
            imgLbl.setIcon(ResizeImage("Img\\" + "noImgSelect" + ".png"));
            status.setVisible(false);
            jButton1.setVisible(false);
            jMenu3.setVisible(false);
        }

        if (day.toString().charAt(0) == '0') {
            String a = day.charAt(1) + "";
            day = a;
        }
        dateToday = stringDate2 + ", " + month + " " + day + ", " + year;

        viewCal.setVisible(false);
        delCal.setVisible(false);
        addCal.setVisible(false);
        delCal1.setVisible(false);
        cancelBtn_Main.setVisible(false);

        if (VariableClass.sched.contains(dateToday)) {
            jScrollPane2.setVisible(true);
            jPanel9.setVisible(false);
            jPanel7.setVisible(false);
        } else {
            jScrollPane2.setVisible(false);
            jPanel9.setVisible(true);
            jPanel7.setVisible(false);
        }
        taskTable.getTableHeader().setReorderingAllowed(false);
        TableCellRenderer rendererFromHeader = taskTable.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        for (int year = 1800; year <= 2200; year++) {
            yearCombo.addItem(year + "");
        }
        Format formatterY1 = new SimpleDateFormat("yyyy");
        String year1 = formatterY1.format(new Date());
        yearCombo.setSelectedItem(year1.toString());

        jPanel4.removeAll();
        jPanel5.removeAll();

        myCalendar();
        viewTaskRecords();

    }

    public static void updateTable() {
        sdf2 = new SimpleDateFormat("EEEE");
        stringDate2 = sdf2.format(new Date());
        formatterM = new SimpleDateFormat("MMMM");
        month = formatterM.format(new Date());
        formatterD = new SimpleDateFormat("dd");
        day = formatterD.format(new Date());
        formatterY = new SimpleDateFormat("yyyy");
        year = formatterY.format(new Date());
        if (day.toString().charAt(0) == '0') {
            String a = day.charAt(1) + "";
            day = a;
        }
        dateToday = stringDate2 + ", " + month + " " + day + ", " + year;
        viewCal.setVisible(false);
        delCal.setVisible(false);
        delCal1.setVisible(false);
        addCal.setVisible(false);
        cancelBtn_Main.setVisible(false);
        jScrollPane2.setVisible(true);
        jPanel7.setVisible(false);
        if (!VariableClass.sched.contains(dateToday)) {
            jScrollPane2.setVisible(false);
            jPanel9.setVisible(true);
            jPanel7.setVisible(false);
        } else {
            jScrollPane2.setVisible(true);
            jPanel9.setVisible(false);
            jPanel7.setVisible(false);
        }
        viewTaskRecords();

    }

    /*
    class RemindTask extends TimerTask {

        boolean numWarningBeeps = false;

        public void run() {
            if (numWarningBeeps == false) {
               try{
                jPanel4.removeAll();
                jPanel5.removeAll();
                sdf2 = new SimpleDateFormat("EEEE");
                stringDate2 = sdf2.format(new Date());
                formatterM = new SimpleDateFormat("MMMM");
                month = formatterM.format(new Date());
                formatterD = new SimpleDateFormat("dd");
                day = formatterD.format(new Date());
                formatterY = new SimpleDateFormat("yyyy");
                year = formatterY.format(new Date());
                dateToday = stringDate2 + ", " + month + " " + day + ", " + year;
//myCalendar-----------------------
                
//---------------------------------
                calendarCtr = 0;
                calBtn = new JTextArea[42];
                containerPnl = new JPanel[42];
                containerLbl = new JLabel[42];
                dayBtn = new JButton[7];
                myCalendar();
                viewTaskRecords();
               }
               catch(Exception e){
                   System.out.println(e);
               }
               
            } else {
                toolkit.beep();
                System.out.println("Program Closed");
                //timer.cancel(); //Not necessary because we call System.exit
                //Stops the AWT thread (and everything else)
                System.exit(0);
            }
        }
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        type = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        location = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        type1 = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        editMoto = new javax.swing.JButton();
        deleteMoto = new javax.swing.JButton();
        changeMoto = new javax.swing.JButton();
        viewMoto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        taskTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        monthLbl = new javax.swing.JLabel();
        yearCombo = new javax.swing.JComboBox<>();
        jumpToday = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        viewCal = new javax.swing.JButton();
        addCal = new javax.swing.JButton();
        cancelBtn_Main = new javax.swing.JButton();
        delCal = new javax.swing.JButton();
        delCal1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        imgLbl = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        nameLbl = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        wigBtn = new javax.swing.JButton();
        todayLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        login = new javax.swing.JMenuItem();
        registerAcc = new javax.swing.JMenuItem();
        editAcc = new javax.swing.JMenuItem();
        logout = new javax.swing.JMenuItem();
        status = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));

        jPanel2.setBackground(new java.awt.Color(0, 51, 153));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Location:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

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
        jPanel3.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 70, 30));

        jLabel3.setText("Type:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 30, -1));

        location.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "NLEX", "SCTEX" }));
        location.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                locationMouseClicked(evt);
            }
        });
        location.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationActionPerformed(evt);
            }
        });
        jPanel3.add(location, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 70, 30));

        jLabel4.setText("Status:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 40, -1));

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
        jPanel3.add(type1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 80, 30));

        jPanel10.setBackground(java.awt.SystemColor.activeCaption);
        jPanel10.setOpaque(false);

        editMoto.setText("Edit");
        editMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMotoActionPerformed(evt);
            }
        });

        deleteMoto.setText("Delete");
        deleteMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMotoActionPerformed(evt);
            }
        });

        changeMoto.setText("Change Status");
        changeMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeMotoActionPerformed(evt);
            }
        });

        viewMoto.setText("View");
        viewMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(119, Short.MAX_VALUE)
                .addComponent(viewMoto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editMoto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteMoto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(changeMoto)
                .addGap(94, 94, 94))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {changeMoto, deleteMoto, editMoto, viewMoto});

        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editMoto)
                    .addComponent(deleteMoto)
                    .addComponent(changeMoto)
                    .addComponent(viewMoto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {changeMoto, deleteMoto, editMoto, viewMoto});

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, -1, -1));

        taskTable.setBackground(new java.awt.Color(0, 51, 153));
        taskTable.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        taskTable.setModel(new javax.swing.table.DefaultTableModel(
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
        taskTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taskTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(taskTable);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 690, 450));

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 690, 390));

        jPanel5.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 690, 40));

        jPanel6.setToolTipText("");

        jButton4.setText(">");
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

        monthLbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jumpToday, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(monthLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton4)
                .addGap(52, 52, 52)
                .addComponent(yearCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        jPanel7.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 590, -1));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 600));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("NOTHING TO SHOW");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel1)
                .addContainerGap(238, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel1)
                .addContainerGap(263, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 690, 540));

        viewCal.setText("View Schedule");
        viewCal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCalActionPerformed(evt);
            }
        });

        addCal.setText("Add Schedule");
        addCal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCalActionPerformed(evt);
            }
        });

        cancelBtn_Main.setText("Cancel");
        cancelBtn_Main.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtn_MainActionPerformed(evt);
            }
        });

        delCal.setText("Delete Schedule");
        delCal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delCalActionPerformed(evt);
            }
        });

        delCal1.setText("Set Title");
        delCal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delCal1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(viewCal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addCal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delCal)
                .addGap(5, 5, 5)
                .addComponent(delCal1)
                .addGap(46, 46, 46)
                .addComponent(cancelBtn_Main)
                .addGap(46, 46, 46))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addCal, delCal, delCal1, viewCal});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(viewCal)
                        .addComponent(addCal)
                        .addComponent(delCal)
                        .addComponent(delCal1))
                    .addComponent(cancelBtn_Main))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addCal, viewCal});

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 720, 40));

        jButton1.setText("Add Schedule");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Daily Task");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("View Calendar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(0, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imgLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(imgLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        nameLbl.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        nameLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLbl.setText("jLabel2");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setBackground(new java.awt.Color(0, 51, 153));
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(13, 13, 13)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        jPanel14.setBackground(new java.awt.Color(0, 51, 153));
        jPanel14.setForeground(new java.awt.Color(0, 51, 153));

        wigBtn.setText("Wig Presentation");
        wigBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wigBtnActionPerformed(evt);
            }
        });

        todayLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        todayLabel.setForeground(new java.awt.Color(255, 204, 0));
        todayLabel.setText("Todays Task: ");

        jLabel7.setForeground(new java.awt.Color(0, 51, 153));
        jLabel7.setText("jLabel7");

        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setText("jLabel8");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(todayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wigBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(todayLabel)
                    .addComponent(wigBtn)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu2.setText("Account");

        login.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.ALT_MASK));
        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        jMenu2.add(login);

        registerAcc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_MASK));
        registerAcc.setText("Register");
        registerAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerAccActionPerformed(evt);
            }
        });
        jMenu2.add(registerAcc);

        editAcc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.ALT_MASK));
        editAcc.setText("Edit Account");
        editAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAccActionPerformed(evt);
            }
        });
        jMenu2.add(editAcc);

        logout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.ALT_MASK));
        logout.setText("Log Out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jMenu2.add(logout);

        status.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.ALT_MASK));
        status.setText("Edit Status<admin>");
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });
        jMenu2.add(status);

        jMenuBar2.add(jMenu2);

        jMenu1.setText("Report");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Monthly Sched");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar2.add(jMenu1);

        jMenu3.setText("Location");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setText("Add");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar2.add(jMenu3);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton2.setBackground(new java.awt.Color(255, 204, 0));
        jButton3.setBackground(new java.awt.Color(240, 240, 240));
        type1.setVisible(false);
        jLabel4.setVisible(false);
        jPanel10.setVisible(false);
        jPanel7.setVisible(false);
        location.setVisible(false);
        type.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        viewMoto.setEnabled(false);
        deleteMoto.setEnabled(false);
        changeMoto.setEnabled(false);
        editMoto.setEnabled(false);
        addScheduleCtr = 1;
        viewCal.setVisible(false);
        delCal.setVisible(false);
        delCal1.setVisible(false);
        addCal.setVisible(false);
        cancelBtn_Main.setVisible(false);
        AddSchedule.shitCtr = 0;
        AddSchedule dialog = new AddSchedule(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
        AddSchedule.titleUpdate();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jButton3.setBackground(new java.awt.Color(255, 204, 0));
        jButton2.setBackground(new java.awt.Color(240, 240, 240));
        type1.setVisible(false);
        jLabel4.setVisible(false);
        jPanel10.setVisible(false);
        viewMoto.setEnabled(false);
        deleteMoto.setEnabled(false);
        changeMoto.setEnabled(false);
        editMoto.setEnabled(false);
        addScheduleCtr = 0;
        viewCal.setVisible(false);
        delCal.setVisible(false);
        delCal1.setVisible(false);
        addCal.setVisible(false);
        cancelBtn_Main.setVisible(false);
        jScrollPane2.setVisible(false);
        jPanel7.setVisible(true);
        location.setVisible(false);
        type.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jPanel8.setVisible(true);
        myCalendar();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton2.setBackground(new java.awt.Color(255, 204, 0));
        jButton3.setBackground(new java.awt.Color(240, 240, 240));
        jPanel10.setVisible(true);
        location.setVisible(true);
        location.setSelectedIndex(0);
        type.setVisible(true);
        type.setSelectedIndex(0);
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);
        viewMoto.setEnabled(false);
        deleteMoto.setEnabled(false);
        changeMoto.setEnabled(false);
        editMoto.setEnabled(false);
        type1.setVisible(true);
        jLabel4.setVisible(true);
        addScheduleCtr = 1;
        sdf2 = new SimpleDateFormat("EEEE");
        stringDate2 = sdf2.format(new Date());
        formatterM = new SimpleDateFormat("MMMM");
        month = formatterM.format(new Date());
        formatterD = new SimpleDateFormat("dd");
        day = formatterD.format(new Date());
        formatterY = new SimpleDateFormat("yyyy");
        year = formatterY.format(new Date());
        if (day.toString().charAt(0) == '0') {
            String a = day.charAt(1) + "";
            day = a;
        }
        dateToday = stringDate2 + ", " + month + " " + day + ", " + year;
        viewCal.setVisible(false);
        delCal.setVisible(false);
        delCal1.setVisible(false);
        addCal.setVisible(false);
        cancelBtn_Main.setVisible(false);
        jScrollPane2.setVisible(true);
        jPanel7.setVisible(false);
        if (!VariableClass.sched.contains(dateToday)) {
            jScrollPane2.setVisible(false);
            jPanel9.setVisible(true);
            jPanel7.setVisible(false);
        } else {
            jScrollPane2.setVisible(true);
            jPanel9.setVisible(false);
            jPanel7.setVisible(false);
        }
        viewTaskRecords();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        delCal.setVisible(false);
        delCal1.setVisible(false);

        System.out.println(monthToday);
        if (monthToday == 12) {
            monthToday = 1;
            yearCombo.setSelectedIndex(yearCombo.getSelectedIndex() + 1);
        } else {
            monthToday++;

        }

        cal1.set(Calendar.MONTH, monthToday - 1);
        jPanel4.removeAll();
        jPanel5.removeAll();
        myCalendar();
        jPanel4.revalidate();
        jPanel5.revalidate();
        viewCal.setVisible(false);
        addCal.setVisible(false);
        cancelBtn_Main.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        delCal.setVisible(false);
        delCal1.setVisible(false);

        System.out.println(monthToday);
        if (monthToday == 1) {
            monthToday = 12;
            yearCombo.setSelectedIndex(yearCombo.getSelectedIndex() - 1);

        } else {
            monthToday--;
        }

        cal1.set(Calendar.MONTH, monthToday - 1);
        jPanel4.removeAll();
        jPanel5.removeAll();
        myCalendar();
        jPanel4.revalidate();
        jPanel5.revalidate();
        viewCal.setVisible(false);
        addCal.setVisible(false);
        cancelBtn_Main.setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void yearComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_yearComboItemStateChanged
        jPanel4.removeAll();
        jPanel5.removeAll();
        myCalendar();
        jPanel4.revalidate();
        jPanel5.revalidate();
        viewCal.setVisible(false);
        addCal.setVisible(false);
        cancelBtn_Main.setVisible(false);
        delCal.setVisible(false);
        delCal1.setVisible(false);

// TODO add your handling code here:
    }//GEN-LAST:event_yearComboItemStateChanged

    private void jumpTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumpTodayActionPerformed
        delCal.setVisible(false);
        delCal1.setVisible(false);

        DateFormat df = new SimpleDateFormat("MM");
        Date dateobj = new Date();
        monthToday = Integer.parseInt(df.format(dateobj));
        Format formatterY1 = new SimpleDateFormat("yyyy");
        String year1 = formatterY1.format(new Date());
        yearCombo.setSelectedItem(year1.toString());
        System.out.println(Integer.parseInt(monthFormat.format(cal1.getTime())));
        jPanel4.removeAll();
        jPanel5.removeAll();
        myCalendar();
        jPanel4.revalidate();
        jPanel5.revalidate();
        viewCal.setVisible(false);
        addCal.setVisible(false);
        cancelBtn_Main.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jumpTodayActionPerformed

    private void viewCalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCalActionPerformed
        jButton2.setBackground(new java.awt.Color(255, 204, 0));
        jButton3.setBackground(new java.awt.Color(240, 240, 240));
        System.out.println(calendarCtr);
        int calendarCtr1 = calendarCtr;
        cal1.set(Calendar.DATE, calendarCtr);
        cal1.set(Calendar.MONTH, monthToday - 1);
        cal1.set(Calendar.YEAR, Integer.parseInt(yearCombo.getItemAt(yearCombo.getSelectedIndex())));

        if (cal1.get(Calendar.DAY_OF_WEEK) == 1) {
            stringDate2 = "Sunday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 2) {
            stringDate2 = "Monday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 3) {
            stringDate2 = "Tuesday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 4) {
            stringDate2 = "Wednesday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 5) {
            stringDate2 = "Thursday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 6) {
            stringDate2 = "Friday";
        } else {
            stringDate2 = "Saturday";
        }

        dateToday = stringDate2 + ", " + VariableClass.monthName.get(monthToday - 1).toString() + " " + calendarCtr1 + ", " + yearCombo.getItemAt(yearCombo.getSelectedIndex());

        if (VariableClass.sched.contains(dateToday)) {
            jScrollPane2.setVisible(true);
            jPanel9.setVisible(false);
            jPanel7.setVisible(false);

        } else {
            jScrollPane2.setVisible(false);
            jPanel9.setVisible(true);
            jPanel7.setVisible(false);
        }

        System.out.println(dateToday);
        type1.setVisible(true);
        jLabel4.setVisible(true);
        location.setVisible(true);
        jLabel2.setVisible(true);
        type.setVisible(true);
        jLabel3.setVisible(true);
        jPanel10.setVisible(true);

        viewMoto.setEnabled(false);
        editMoto.setEnabled(false);
        changeMoto.setEnabled(false);
        deleteMoto.setEnabled(false);
        jPanel8.setVisible(false);
        viewTaskRecords();
        // TODO add your handling code here:
    }//GEN-LAST:event_viewCalActionPerformed

    private void addCalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCalActionPerformed
        AddSchedule.shitCtr = 1;
        AddSchedule dialog = new AddSchedule(new javax.swing.JFrame(), true);
        dialog.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_addCalActionPerformed

    private void cancelBtn_MainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtn_MainActionPerformed
        viewCal.setVisible(false);
        addCal.setVisible(false);
        delCal.setVisible(false);
        delCal1.setVisible(false);
        cancelBtn_Main.setVisible(false);
        delCal1.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelBtn_MainActionPerformed

    private void delCalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delCalActionPerformed

        cal1.set(Calendar.DATE, calendarCtr);
        cal1.set(Calendar.MONTH, monthToday - 1);
        cal1.set(Calendar.YEAR, Integer.parseInt(yearCombo.getItemAt(yearCombo.getSelectedIndex())));

        if (cal1.get(Calendar.DAY_OF_WEEK) == 1) {
            stringDate2 = "Sunday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 2) {
            stringDate2 = "Monday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 3) {
            stringDate2 = "Tuesday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 4) {
            stringDate2 = "Wednesday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 5) {
            stringDate2 = "Thursday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 6) {
            stringDate2 = "Friday";
        } else {
            stringDate2 = "Saturday";
        }

        String choiceDate = stringDate2 + ", " + VariableClass.monthName.get(monthToday - 1).toString() + " " + calendarCtr + ", " + yearCombo.getItemAt(yearCombo.getSelectedIndex());
        System.out.println(choiceDate);

        try {

            if (VariableClass.sched.contains(choiceDate)) {
                int choice1 = JOptionPane.showConfirmDialog(null, "Delete Schedule?", "Notice", JOptionPane.YES_NO_OPTION);
                if (choice1 == 0) {

                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/trafficsystems";
                    Connection conn = DriverManager.getConnection(url, "root", "admin");

                    String query = "delete from sched where sched = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, choiceDate);
                    preparedStmt.execute();
                    conn.close();
                    VariableClass.loadSched();

                    myCalendar();
                }
            }

        } catch (Exception e) {

        }


    }//GEN-LAST:event_delCalActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        Login log = new Login();
        log.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_loginActionPerformed

    private void registerAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerAccActionPerformed
        VariableClass.theresLogin = 0;
        Register reg = new Register();
        reg.setVisible(true);
        dispose();
// TODO add your handling code here:
    }//GEN-LAST:event_registerAccActionPerformed

    private void wigBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wigBtnActionPerformed
        viewMoto.setEnabled(false);
        deleteMoto.setEnabled(false);
        changeMoto.setEnabled(false);
        editMoto.setEnabled(false);
        path = "";

        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        file.setPreferredSize(new Dimension(582, 382));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Videos", ".mp4", ".mkv", ".3gp");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);

        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            path = selectedFile.getAbsolutePath();

            Player p = new Player();
            p.setVisible(true);

        } //if the user click on save in Jfilechooser
        else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
        }                // TODO add your handling code here:
    }//GEN-LAST:event_wigBtnActionPerformed

    private void viewMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMotoActionPerformed

        mainScreenSelect();
        System.out.println(VariableClass.mainScreenSelected);
        ViewSched dialog = new ViewSched(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
        viewMoto.setEnabled(false);
        deleteMoto.setEnabled(false);
        changeMoto.setEnabled(false);
        editMoto.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_viewMotoActionPerformed

    private void changeMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeMotoActionPerformed

        viewMoto.setEnabled(false);
        deleteMoto.setEnabled(false);
        changeMoto.setEnabled(false);
        editMoto.setEnabled(false);
        mainScreenSelect();
        ChangeStatus dialog = new ChangeStatus(new javax.swing.JFrame(), true);
        dialog.setVisible(true);


    }//GEN-LAST:event_changeMotoActionPerformed

    private void deleteMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMotoActionPerformed
        viewMoto.setEnabled(false);
        deleteMoto.setEnabled(false);
        changeMoto.setEnabled(false);
        editMoto.setEnabled(false);
        mainScreenSelect();

        try {
            // create a java mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/trafficsystems";
            Connection conn = DriverManager.getConnection(url, "root", "admin");

            String query = "delete from sched where schedId = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, VariableClass.schedId.get(VariableClass.mainScreenSelected));
            preparedStmt.execute();
            conn.close();

            // create the java mysql update preparedstatement
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        VariableClass.loadSched();

        MainScreen.viewTaskRecords();

        // TODO add your handling code here:
    }//GEN-LAST:event_deleteMotoActionPerformed

    private void editMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMotoActionPerformed
        viewMoto.setEnabled(false);
        deleteMoto.setEnabled(false);
        changeMoto.setEnabled(false);
        editMoto.setEnabled(false);
        mainScreenSelect();
        EditMain dialog = new EditMain(new javax.swing.JFrame(), true);
        dialog.setVisible(true);


    }//GEN-LAST:event_editMotoActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed

        VariableClass.theresLogin = 0;
        dispose();
        MainScreen a = new MainScreen();
        a.setVisible(true);
        VariableClass.userlog = 0;
        viewTaskRecords();
        // TODO add your handling code here:
    }//GEN-LAST:event_logoutActionPerformed

    private void delCal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delCal1ActionPerformed
        cal1.set(Calendar.DATE, calendarCtr);
        cal1.set(Calendar.MONTH, monthToday - 1);
        cal1.set(Calendar.YEAR, Integer.parseInt(yearCombo.getItemAt(yearCombo.getSelectedIndex())));

        if (cal1.get(Calendar.DAY_OF_WEEK) == 1) {
            stringDate2 = "Sunday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 2) {
            stringDate2 = "Monday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 3) {
            stringDate2 = "Tuesday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 4) {
            stringDate2 = "Wednesday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 5) {
            stringDate2 = "Thursday";
        } else if (cal1.get(Calendar.DAY_OF_WEEK) == 6) {
            stringDate2 = "Friday";
        } else {
            stringDate2 = "Saturday";
        }
        String choiceDate = stringDate2 + ", " + VariableClass.monthName.get(monthToday - 1).toString() + " " + calendarCtr + ", " + yearCombo.getItemAt(yearCombo.getSelectedIndex());
        System.out.println(choiceDate);
        System.out.println(VariableClass.sched);
        if (VariableClass.sched.contains(choiceDate)) {

            setTitle dialog = new setTitle(new javax.swing.JFrame(), true);
            dialog.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Theres no schedule in this date", "", JOptionPane.INFORMATION_MESSAGE);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_delCal1ActionPerformed

    private void taskTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskTableMouseClicked

        if (!taskTable.getSelectionModel().isSelectionEmpty()) {
            if (VariableClass.theresLogin == 0) {
                viewMoto.setEnabled(true);
                editMoto.setEnabled(false);
                deleteMoto.setEnabled(false);
                changeMoto.setEnabled(false);
            } else {
                viewMoto.setEnabled(true);
                jPanel10.setVisible(true);
                editMoto.setEnabled(true);
                deleteMoto.setEnabled(true);
                changeMoto.setEnabled(true);
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_taskTableMouseClicked

    private void editAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAccActionPerformed
        VariableClass.theresLogin = 0;
        EditAccount a = new EditAccount();
        a.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_editAccActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        UserList dialog = new UserList(new javax.swing.JFrame(), true);
        dialog.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Report dialog = new Report(new javax.swing.JFrame(), true);
        dialog.setVisible(true);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Location dialog = new Location(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void locationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationActionPerformed
        viewTaskRecords();

        // TODO add your handling code here:
    }//GEN-LAST:event_locationActionPerformed

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        viewTaskRecords();
        // TODO add your handling code here:
    }//GEN-LAST:event_typeActionPerformed

    private void locationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_locationMouseClicked
        viewMoto.setEnabled(false);
        deleteMoto.setEnabled(false);
        changeMoto.setEnabled(false);
        editMoto.setEnabled(false);
        viewTaskRecords();
        // TODO add your handling code here:
    }//GEN-LAST:event_locationMouseClicked

    private void typeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_typeMouseClicked
        viewMoto.setEnabled(false);
        deleteMoto.setEnabled(false);
        changeMoto.setEnabled(false);
        editMoto.setEnabled(false);
        viewTaskRecords();// TODO add your handling code here:
    }//GEN-LAST:event_typeMouseClicked

    private void yearComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearComboActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_yearComboActionPerformed

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
        viewTaskRecords();
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        new MainScreen();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton addCal;
    public static javax.swing.JButton cancelBtn_Main;
    public static javax.swing.JButton changeMoto;
    public static javax.swing.JButton delCal;
    public static javax.swing.JButton delCal1;
    public static javax.swing.JButton deleteMoto;
    private javax.swing.JMenuItem editAcc;
    public static javax.swing.JButton editMoto;
    public static javax.swing.JLabel imgLbl;
    private javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel4;
    public static javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    public static javax.swing.JPanel jPanel7;
    public static javax.swing.JPanel jPanel8;
    public static javax.swing.JPanel jPanel9;
    public static javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jumpToday;
    public static javax.swing.JComboBox<String> location;
    private javax.swing.JMenuItem login;
    private javax.swing.JMenuItem logout;
    public static javax.swing.JLabel monthLbl;
    private javax.swing.JLabel nameLbl;
    private javax.swing.JMenuItem registerAcc;
    private javax.swing.JMenuItem status;
    public static javax.swing.JTable taskTable;
    public static javax.swing.JLabel todayLabel;
    public static javax.swing.JComboBox<String> type;
    public static javax.swing.JComboBox<String> type1;
    public static javax.swing.JButton viewCal;
    public static javax.swing.JButton viewMoto;
    public static javax.swing.JButton wigBtn;
    public static javax.swing.JComboBox<String> yearCombo;
    // End of variables declaration//GEN-END:variables
}
