/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulePackage;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jen
 */
public class EditAccount extends javax.swing.JFrame {

    public static void copyFile_Java7(String origin, String destination) throws IOException {
        Path FROM = Paths.get(origin);
        Path TO = Paths.get(destination);
        //overwrite the destination file if it exists, and copy
        // the file attributes, including the rwx permissions
        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(FROM, TO, options);
    }

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(imageLbl.getWidth(), imageLbl.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    public static String path = "";
    public static JFileChooser file1 = new JFileChooser();

    public EditAccount() {
        initComponents();
        this.setResizable(false);
        this.setSize(475, 505);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("NlexLogo1.png")));
        this.setTitle("Traffic Systems Engineering v.0.1");
        usernameField.setText(VariableClass.username.get(VariableClass.userlog).toString());
        passwordField.setText(VariableClass.password.get(VariableClass.userlog).toString());
        repasswordField.setText(VariableClass.password.get(VariableClass.userlog).toString());
        emailField.setText(VariableClass.email.get(VariableClass.userlog).toString());
        contactField.setText(VariableClass.contact.get(VariableClass.userlog).toString());
        imageLbl.setIcon(ResizeImage(VariableClass.imgUser.get(VariableClass.userlog).toString()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        imageLbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        contactField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        repasswordField = new javax.swing.JPasswordField();
        passwordField = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setOpaque(false);

        jButton1.setForeground(new java.awt.Color(0, 51, 255));
        jButton1.setText("Choose Image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Profile Picture", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Century Gothic", 1, 14), new java.awt.Color(255, 204, 0))); // NOI18N

        imageLbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 200, -1));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setText("Username");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 65, 24));

        usernameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(usernameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 180, 35));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 0));
        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 65, 24));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 0));
        jLabel3.setText("Re-Enter Password");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 121, 24));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 153, 0));
        jLabel8.setText("Email");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 121, 24));

        emailField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(emailField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 377, 35));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 153, 0));
        jLabel9.setText("Contact Number (11 digit)");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 205, 24));

        contactField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(contactField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 377, 35));

        jButton2.setForeground(new java.awt.Color(0, 51, 255));
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 70, -1));

        jButton3.setForeground(new java.awt.Color(0, 51, 255));
        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, -1, -1));

        repasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(repasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, 180, 40));

        passwordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(passwordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 180, 40));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SchedulePackage/Untitled-2.png"))); // NOI18N
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png");
        file.addChoosableFileFilter(filter);
        file.setPreferredSize(new Dimension(582, 382));
        int result = file.showSaveDialog(null);
        //if the user click on save in Jfilechooser
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            imageLbl.setIcon(ResizeImage(path));

        } //if the user click on save in Jfilechooser
        else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
        }        // TODO add your handling code here:
        file1 = file;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String repassword = repasswordField.getText().trim();

        String email = emailField.getText().trim();
        String contact = contactField.getText().trim();

        if (username.equals("") || password.equals("") || repassword.equals("") || email.equals("") || Pattern.matches("[a-zA-Z]+", contactField.getText().trim())
                || contactField.getText().trim().length() != 11) {
            if (username.equals("") || password.equals("") || repassword.equals("") || email.equals("")) {
                JOptionPane.showMessageDialog(null, "Must Fill up all Required Fields", "Notice", JOptionPane.OK_OPTION);
            } else if (Pattern.matches("[a-zA-Z]+", contactField.getText().trim()) || contactField.getText().trim().length() != 11) {
                JOptionPane.showMessageDialog(null, "Number not valid", "Notice", JOptionPane.OK_OPTION);
            }

        } else {

            //            dispose();
            //            Login log = new Login();
            //            log.setVisible(true);
            if ((VariableClass.username.contains(username) && !VariableClass.username.get(VariableClass.userlog).equals(username))
                    || (VariableClass.email.contains(email) && !VariableClass.email.get(VariableClass.userlog).equals(email))
                    || (VariableClass.contact.contains(contact) && !VariableClass.contact.get(VariableClass.userlog).equals(contact))
                    || !password.equals(repassword)) {
                if (VariableClass.username.contains(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exist.", "Notice", JOptionPane.OK_OPTION);
                } else if (!password.equals(repassword)) {
                    JOptionPane.showMessageDialog(null, "Password do not match.", "Notice", JOptionPane.OK_OPTION);
                } else if (VariableClass.email.contains(email)) {
                    JOptionPane.showMessageDialog(null, "Email already exist.", "Notice", JOptionPane.OK_OPTION);
                } else if (VariableClass.contact.contains(contact)) {
                    JOptionPane.showMessageDialog(null, "Contact number already exist.", "Notice", JOptionPane.OK_OPTION);
                }

            } else {

                VariableClass.username.set(VariableClass.userlog, username);
                VariableClass.password.set(VariableClass.userlog, password);

                VariableClass.email.set(VariableClass.userlog, email);
                if (path.trim().equals("")) {
                    path = "Img\\" + "noImgSelect" + ".png";
                }
                String imgUser = "Img\\" + username + ".png";
                try {
                    copyFile_Java7(path, imgUser);
                } catch (Exception e) {
                    System.out.println(e);
                }
                VariableClass.imgUser.set(VariableClass.userlog, imgUser);
             

                try {
                    // create a java mysql database connection
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/trafficsystems";
                    Connection conn = DriverManager.getConnection(url, "root", "admin");

                    // create the java mysql update preparedstatement
                    String query = "update user set username = ?,password = ?,contact = ?,email = ?,imgUser = ? where userId = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(query);
                    preparedStmt.setString(1, username);
                    preparedStmt.setString(2, password);
                    preparedStmt.setString(3, contact);
                    preparedStmt.setString(4, email);
                    preparedStmt.setString(5, imgUser);
                    preparedStmt.setInt(6, VariableClass.userId.get(VariableClass.userlog));

                    // execute the java preparedstatement
                    preparedStmt.executeUpdate();

                    conn.close();
                } catch (Exception e) {
                    System.err.println("Got an exception! ");
                    System.err.println(e.getMessage());
                }
                VariableClass.loadUser();
                Login log = new Login();
                log.setVisible(true);
                dispose();
                System.out.println(imgUser);
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();

        Login log = new Login();
        log.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(EditAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField contactField;
    public static javax.swing.JTextField emailField;
    private javax.swing.JLabel imageLbl;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField repasswordField;
    public static javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}