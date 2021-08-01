/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My_Package;

import java.sql.*;
import Mysql_Connection.ConnectionProvider;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.time.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class Exam extends javax.swing.JFrame {

    /**
     * Creates new form Exam
     */
    public String Correct_Answer;
    public static int Cur_QId;
    public int min;
    public int sec;
    public int marks;
    public int First_Question=1;
    public int duration;
    public static int Cur_CandiId,Cur_CompId,Cur_Qmark,Qualification_Marks,First_Q,Last_Q,Q_Number,Comp_Dur;
    Connection con=ConnectionProvider.getcon();
    Timer time;
    
    public void Answer_Check()
    {
        System.out.println("2");
        String Student_Answer="";
        if(jRadioButton1.isSelected())
        {
            Student_Answer=jRadioButton1.getText();
        }
        
        else if(jRadioButton2.isSelected())
        {
            Student_Answer=jRadioButton2.getText();
        }
        
        else if(jRadioButton3.isSelected())
        {
            Student_Answer=jRadioButton3.getText();
        }
        
        else
        {
            Student_Answer=jRadioButton4.getText();
        }
        
        try
        { 
            PreparedStatement st1=con.prepareStatement("insert into Answer values(?,?,?,?)");
            st1.setInt(1, Cur_CandiId);
            st1.setInt(2, Cur_CompId);
            st1.setString(3, Student_Answer);
            st1.setInt(4, Cur_QId);
            st1.executeUpdate();
              
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", 2);
            
        }
        if(Student_Answer.equals(Correct_Answer))
        {
            marks=marks+Cur_Qmark;
        }
        //System.out.println("marks"+marks);
        //question id change
        //int questionID1=Integer.parseInt(Cur_QId);
        
        //Cur_QId=String.valueOf(Cur_QId);
        
        //clear Option
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jRadioButton4.setSelected(false);
        
        //last question hide next button
        
        //System.out.println(Last_Q);
        if(Cur_QId==Last_Q)
        {
            jButton2.setVisible(false);
            submit();
        }
        Cur_QId=Cur_QId+1;
        Q_Number=Q_Number+1;
    }
    public void question()
    {
        try
        {
            
            
            PreparedStatement st1=con.prepareStatement("select * from questions_table where question_ID=? and comp_Id=?");
            st1.setInt(1, Cur_QId);
            st1.setInt(2, Cur_CompId);
            ResultSet rs1=st1.executeQuery();
                    
            if(rs1.first())
            {
                jLabel28.setText(rs1.getString(10));
                jLabel24.setText(Integer.toString(Q_Number));
                jLabel14.setText(Integer.toString(Cur_QId));
                jLabel15.setText(rs1.getString(3));
                jRadioButton1.setText(rs1.getString(4));
                jRadioButton2.setText(rs1.getString(5));
                jRadioButton3.setText(rs1.getString(6));
                jRadioButton4.setText(rs1.getString(7));
                Cur_Qmark=rs1.getInt(9);
                jLabel23.setText(Integer.toString(Cur_Qmark));
                Correct_Answer=rs1.getString(8);
            }
            
            
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", 2);
            
        }
        
    }
    
    public void submit()
    {
        System.out.println("res");
        String C_ID=jLabel11.getText();
        try
        {
            time.stop();
            PreparedStatement st3=con.prepareStatement("insert into result values(?,?,?,?)");
            st3.setInt(1, Cur_CandiId);
            st3.setInt(2, Cur_CompId);
            st3.setInt(3, marks);
            if(marks>=Qualification_Marks)
            {
                st3.setString(4, "Yes");
            }
            else
                st3.setString(4, "No");
            
            st3.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Response Submitted Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            new Candidate_Home().setVisible(true);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(rootPane, "Result is not Updated", "Error !!!", 2);
        }
        
    }
    
    public Exam() 
    {
        initComponents();
    } 
    
    public Exam(int Cur_ID,int Comp_Id)
    {
        
        initComponents();
        Q_Number=1;
        jLabel24.setText(Integer.toString(Q_Number));
        Cur_CompId=Comp_Id;
        Cur_CandiId=Cur_ID;
        
        
        
        //date
        SimpleDateFormat dFormat=new SimpleDateFormat("dd-MM-yyyy");
        Date date=new Date();
        jLabel18.setText(dFormat.format(date));
        
        jLabel13.setText(Integer.toString(Last_Q));
        
        try
        {
            
            PreparedStatement st6=con.prepareStatement("select min(Question_Id),max(Question_Id),count(question_Id) from "
                    + "questions_table where comp_Id=?");
            st6.setInt(1, Cur_CompId);
            ResultSet rs6=st6.executeQuery();
            if(rs6.first())
            {
                First_Q=rs6.getInt(1);
                jLabel11.setText(Integer.toString(First_Q));
                jLabel13.setText(Integer.toString(rs6.getInt(3)));
                Last_Q=rs6.getInt(2);
                Cur_QId=First_Q;
            }
            
            
            PreparedStatement st=con.prepareStatement("select * from Candidate where Candidate_ID=?");
            st.setInt(1, Cur_CandiId);
            
            PreparedStatement st4=con.prepareStatement("select * from questions_table where question_ID=? and comp_Id=?");
            st4.setInt(1, Cur_QId);
            st4.setInt(2, Cur_CompId);
            
            PreparedStatement st5=con.prepareStatement("select * from competition where Comp_Id=?");
            st5.setInt(1, Cur_CompId);
            ResultSet rs5=st5.executeQuery();
            ResultSet rs4=st4.executeQuery();
            if(rs5.first() && rs4.first())
            {
                jLabel1.setText(rs5.getString(3));
                jLabel20.setText(rs5.getString(4));
                jLabel16.setText(Integer.toString(rs5.getInt(5)));
                Qualification_Marks=rs5.getInt(6);
                Comp_Dur=rs5.getInt(5);
            }
            
            
            
            ResultSet rs=st.executeQuery();
            if(rs.first())
            {
                jLabel11.setText(Integer.toString(Cur_ID));
                jLabel12.setText(rs.getString(2));
            
            }
            PreparedStatement st1=con.prepareStatement("select * from questions_table where Question_Id=?");
            st1.setInt(1, Cur_QId);
            ResultSet rs1=st1.executeQuery();
                    
            if(rs1.first())
            {
                System.out.println("1");
                jLabel28.setText(rs1.getString(10));
                jLabel15.setText(rs1.getString(3));
                jLabel14.setText(Integer.toString(Cur_QId));
                jRadioButton1.setText(rs1.getString(4));
                jRadioButton2.setText(rs1.getString(5));
                jRadioButton3.setText(rs1.getString(6));
                jRadioButton4.setText(rs1.getString(7));
                Cur_Qmark=rs1.getInt(9);
                jLabel23.setText(Integer.toString(Cur_Qmark));
                Correct_Answer=rs1.getString(8);
            }
            
            
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, e.getMessage(), "Error", 2);
            System.out.println(e.getMessage());
        }
        //timer
       // setLocationRelativeTO(this);
        time=new Timer(1000,new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                jLabel17.setText(String.valueOf(min));
                jLabel26.setText(String.valueOf(sec));
                
                if(sec==60)
                {
                    sec=0;
                    min++;
                
                    if(min==Comp_Dur)
                    {
                        time.stop();
                        Answer_Check();
                        submit();
                  
                    }
                }
          
                sec++;
            }    
        });
        time.start();
         
    }
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    
    //@SuppressWarnings("unchecked");
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/My_Package/Project_Images/index student.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel1.setText("Title");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 6, -1, -1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel3.setText("Date:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 72, -1, -1));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel4.setText("Total TIme:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel5.setText("Time Taken:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 60, -1, -1));

        jButton3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/My_Package/Project_Images/Close.png"))); // NOI18N
        jButton3.setText("End Exam");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, -1, -1));

        jLabel16.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel16.setText("jLabel16");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, -1, -1));

        jLabel17.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel17.setText("00");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 60, -1, -1));

        jLabel18.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel18.setText("jLabel18");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 72, -1, -1));

        jLabel19.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel19.setText("Round:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 105, -1, -1));

        jLabel20.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel20.setText("jLabel20");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 105, -1, -1));

        jLabel21.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jLabel25.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel25.setText(":");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 60, -1, -1));

        jLabel26.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel26.setText("00");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 60, -1, -1));

        jLabel29.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel29.setText("min");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1433, 130));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel6.setText("Candidate ID:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 55, -1, -1));

        jLabel7.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel7.setText("Name:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 92, -1, -1));

        jLabel8.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel8.setText("Total Questions:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 129, -1, -1));

        jLabel11.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel11.setText("jLabel11");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 55, -1, -1));

        jLabel12.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel12.setText("jLabel12");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 92, -1, -1));

        jLabel13.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel13.setText("jLabel13");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel9.setText("Question_Id:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel14.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel14.setText("jLabel14");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 280, 640));

        jLabel10.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel10.setText("Question Number:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(284, 150, -1, -1));

        jRadioButton1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jRadioButton1.setText("Option1");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 266, -1, -1));

        jRadioButton2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jRadioButton2.setText("Option2");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 314, -1, -1));

        jRadioButton3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jRadioButton3.setText("Option3");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 369, -1, -1));

        jRadioButton4.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jRadioButton4.setText("Option4");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 419, -1, -1));

        jButton2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/My_Package/Project_Images/Next.png"))); // NOI18N
        jButton2.setText("Submit & Next");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 500, 220, 46));

        jLabel15.setFont(new java.awt.Font("sansserif", 1, 20)); // NOI18N
        jLabel15.setText("jLabel15");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 199, -1, -1));

        jLabel22.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel22.setText("Marks:");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 150, -1, -1));

        jLabel23.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel23.setText("jLabel23");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 150, -1, -1));

        jLabel24.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel24.setText("jLabel24");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, -1));

        jLabel27.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel27.setText("HInt:");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 180, -1, -1));

        jLabel28.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel28.setText("jLabel28");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 210, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //System.out.println("45");
        Answer_Check();
        question();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int a=JOptionPane.showConfirmDialog(null, "Do You really Want to End Exam and Submit your response","Select",JOptionPane.YES_NO_OPTION);
        if(a==0)
        {
            Answer_Check();
            submit();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton1.isSelected())
        {
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton2.isSelected())
        {
            jRadioButton1.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton3.isSelected())
        {
            jRadioButton2.setSelected(false);
            jRadioButton1.setSelected(false);
            jRadioButton4.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton4.isSelected())
        {
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            jRadioButton1.setSelected(false);
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Exam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Exam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    // End of variables declaration//GEN-END:variables
}
