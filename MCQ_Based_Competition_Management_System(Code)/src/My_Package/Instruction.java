/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Package;

import Mysql_Connection.ConnectionProvider;
import static java.awt.image.ImageObserver.HEIGHT;
import java.sql.*;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class Instruction extends javax.swing.JFrame {
    public static int Cur_ID,Cur_CompId;
    /**
     * Creates new form Instruction
     */
    public Instruction() 
    {
        //jTextArea1.setEditable(false);
       //System.out.println("default");
        initComponents();
        
        
        
    }
    public Instruction(int ID,int dur,String Comp_Name,int Comp_Id) 
    {
        //System.out.println("Para");
        initComponents();
        jTextArea1.setEditable(false);
        Cur_ID=ID;
        Cur_CompId=Comp_Id;
        jLabel3.setText(Integer.toString(dur));
        jLabel5.setText(Comp_Name);
        try
        {
            Connection con=ConnectionProvider.getcon();
            PreparedStatement st6=con.prepareStatement("select min(Question_Id),max(Question_Id) from "
                    + "questions_table where comp_Id=?");
            st6.setInt(1, Comp_Id);
            ResultSet rs6=st6.executeQuery();
            if(rs6.first())
            {
                jLabel7.setText(Integer.toString(rs6.getInt(1)));
                jLabel9.setText(Integer.toString(rs6.getInt(2)));
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", 2);
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel2.setText("Instructions");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 90, 1380, 10));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Basic Instructions for Online Examinations:\n\nA. General information:\n\n1. The examination will comprise of Objective type Multiple Choice Questions (MCQs)\n2. All questions are compulsory and each carries One mark.\n3. The total number of questions, duration of examination, will be different based on\nthe course, the detail is available on your screen.\n4. The Subjects or topics covered in the exam will be as per the Syllabus.\n5. There will be NO NEGATIVE MARKING for the wrong answers.\n\nB. Information & Instructions:\n\n1. The examination does not require using any paper, pen, pencil and calculator.\n2. Every student will take the examination on a Laptop/Desktop/Smart Phone\n3. On computer screen every student will be given objective type type Multiple Choice\nQuestions (MCQs).\n4. Each student will get questions and answers in different order selected randomly\nfrom a fixed Question Databank.\n5. The students just need to click on the Right Choice / Correct option from the\nmultiple choices /options given with each question. For Multiple Choice Questions,\neach question has four options, and the candidate has to click the appropriate\noption.");
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 91, 1390, 480));

        jButton1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jButton1.setText("Start Exam");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 639, -1, -1));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("Duration:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 60, -1, -1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setText("10");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setText("min");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 60, -1, -1));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel5.setText("Comp_Title");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel6.setText("Question ID is from ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, -1, -1));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel7.setText("j7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 10, -1, -1));

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel8.setText("to");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 10, -1, -1));

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel9.setText("j9");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 10, -1, -1));

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel10.setText("Note:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new Exam(Cur_ID,Cur_CompId).setVisible(true);
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
            java.util.logging.Logger.getLogger(Instruction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Instruction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Instruction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Instruction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Instruction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}