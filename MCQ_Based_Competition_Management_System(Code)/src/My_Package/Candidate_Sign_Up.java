/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Package;

import Mysql_Connection.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class Candidate_Sign_Up extends javax.swing.JFrame {

    /**
     * Creates new form Candidate_Sign_Up
     */
    public Candidate_Sign_Up() {
        initComponents();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 40)); // NOI18N
        jLabel1.setText("Sign-Up");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setText("Full Name:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, -1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setText("Email ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, -1, -1));

        jTextField1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 345, -1));

        jTextField2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 190, 345, -1));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setText("Ph NO:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, -1, -1));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel5.setText("Username");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, -1, -1));

        jPasswordField1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        getContentPane().add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 330, 345, -1));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel6.setText("Password:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, -1, -1));

        jTextField3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 345, -1));

        jTextField4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 345, -1));

        jButton1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/My_Package/Project_Images/Next.png"))); // NOI18N
        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 410, -1, -1));

        jCheckBox1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jCheckBox1.setText("Show Password");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, -1, -1));

        jButton2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/My_Package/Project_Images/Back.png"))); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected())
        {
            jPasswordField1.setEchoChar((char)0);
        }
        else
            jPasswordField1.setEchoChar('*');
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Candidate_Login().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        try
        {
            if(jTextField1.getText().length()==0)
            {
                JOptionPane.showMessageDialog(rootPane, "Name Field should not Empty !!!", "Validation Error", 2);
            }
            if(isValidEmailAddress(jTextField2.getText())==false)
            {
                JOptionPane.showMessageDialog(rootPane,"Incorrect Email Address !!!", "Error", 2);
            }
            if(isValidPhNumber(jTextField3.getText())==false)
            {
                JOptionPane.showMessageDialog(rootPane,"Incorrect Phone Number !!!", "Validation Error", 2);
            }
            if(jTextField4.getText().length()==0)
            {
                JOptionPane.showMessageDialog(rootPane, "Username Field should not Empty !!!", "Validation Error", 2);
            }
            
            if(jPasswordField1.getText().length()<6)
            {
                JOptionPane.showMessageDialog(rootPane, "Password should contain atleast 6 character !!!", "Validation Error", 2);
            }
            if(jTextField1.getText().length()!=0 && isValidEmailAddress(jTextField2.getText()) && isValidPhNumber(jTextField3.getText()) && jTextField4.getText().length()!=0  && jPasswordField1.getText().length()>6)
            {
                System.out.println("hii");
                Connection con=ConnectionProvider.getcon();
                PreparedStatement st=con.prepareStatement("insert into candidate(name,email,ph_no,username,password) values(?,?,?,?,?)");
            
                st.setString(1, jTextField1.getText());
                st.setString(2, jTextField2.getText());
                st.setString(3, jTextField3.getText());
                st.setString(4,jTextField4.getText());
                st.setString(5, jPasswordField1.getText());
                int cnt=0;
                cnt=st.executeUpdate();


                if(cnt>0)
                {
                    JOptionPane.showMessageDialog(rootPane, "Account Created Successfully","Sign-UP", 1);
                    setVisible(false);
                    new Candidate_Login().setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane,"Failed!!!! ","sign-UP Error",2);
                }
            }
            else
                System.out.println("Hii1");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane,e.getMessage(),"Sign-Up Error !!!",2);
  
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Candidate_Sign_Up.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Candidate_Sign_Up.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Candidate_Sign_Up.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Candidate_Sign_Up.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Candidate_Sign_Up().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

        private boolean isValidEmailAddress(String email) 
        {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                                "[a-zA-Z0-9_+&*-]+)*@" + 
                                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                                "A-Z]{2,7}$"; 

            Pattern pat = Pattern.compile(emailRegex); 
            if (email == null) 
                return false; 
            return pat.matcher(email).matches();  }

        private boolean isValidPhNumber(String s) 
        {
            if(s.length()==10)
            {
                Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 

                // Pattern class contains matcher() method 
                // to find matching between given number  
                // and regular expression 
                Matcher m = p.matcher(s); 
                return (m.find() && m.group().equals(s));

            }
            else 
                return false;

        }
    }
