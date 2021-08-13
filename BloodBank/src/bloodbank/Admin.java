
package bloodbank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Admin extends JFrame implements ActionListener{
    JButton EMPLOYEE,HOSPITAL;
    JPanel panel;
    JLabel label1,label2,label3,label4,label5;
    JTextField text1,text2,text3,text4,text5;
    
    protected Admin()
    {
        
        label1 = new JLabel();
        label1.setText("Employee Username");
        text1 = new JTextField(15);
        
        label2 = new JLabel();
        label2.setText("Employee Password");
        text2 = new JTextField(15);
        
        label3 = new JLabel();
        label3.setText("Hospital Username");
        text3 = new JTextField(15);
    
        label4 = new JLabel();
        label4.setText("Hospital Password");
        text4 = new JTextField(15);
    
        EMPLOYEE = new JButton("Register New Employee Login");
        HOSPITAL = new JButton("Register New Hospital Login");
        panel = new JPanel(new GridLayout(0,1));
        panel.add(label1);
        panel.add(text1);
        
        panel.add(label2);
        panel.add(text2);
        panel.add(EMPLOYEE);
        EMPLOYEE.addActionListener(this);
        
        panel.add(label3);
        panel.add(text3);

        panel.add(label4);
        panel.add(text4);
        
        panel.add(HOSPITAL);
        add(panel,BorderLayout.CENTER);
        
        HOSPITAL.addActionListener(this);
    }
        
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource()==EMPLOYEE){
                String empID = text1.getText();
                String empPass = text2.getText();
            
                try{
                    ConnectionManager Connection = new ConnectionManager();  
                    Connection conn = Connection.createConnection();
                    
                    CallableStatement cStmt = conn.prepareCall("{call addemp(?,?)}");
        
                    cStmt.setString(1, empID);
                    cStmt.setString(2, empPass);
                    cStmt.execute();
                    Connection.closeConnection();;
                    JOptionPane.showMessageDialog(null,"New Employee Credentials Succesfully Registered", "Registration Successful",JOptionPane.INFORMATION_MESSAGE);
      
                }
                catch(Exception e){     
                    JOptionPane.showMessageDialog(this,"Invalid Employee credentials, please make sure employee username is unique","Error",JOptionPane.ERROR_MESSAGE);
                }
                text1.setText("");
                text2.setText("");
            }
            else if(ae.getSource()==HOSPITAL){
                String hospID = text3.getText();
                String hospPass = text4.getText();
                
                try{
                    ConnectionManager Connection = new ConnectionManager();  
                    Connection conn = Connection.createConnection();
                    
                    CallableStatement cStmt = conn.prepareCall("{call addhosp(?,?)}");
        
                    cStmt.setString(1, hospID);
                    cStmt.setString(2, hospPass);
                    cStmt.execute();
                    Connection.closeConnection();
                    
                    JOptionPane.showMessageDialog(null,"New Hospital Credentials Succesfully Registered", "Registration Successful",JOptionPane.INFORMATION_MESSAGE);
      
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(this,"Invalid Hospital Credentials, please make sure Hospital username is unique","Error",JOptionPane.ERROR_MESSAGE);
                }
                
                text3.setText("");
                text4.setText("");
            }
        }
}
