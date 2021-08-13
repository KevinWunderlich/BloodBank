/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class LoginForm extends JFrame implements ActionListener {
    JButton ADMIN,EMPLOYEE,HOSPITAL;
    JPanel panel;
    JLabel label1,label2;
    JTextField text1,text2;
    String usern, passw;
    
    protected LoginForm()
    {
        label1 = new JLabel();
        label1.setText("Username");
        text1 = new JTextField(15);
        
        label2 = new JLabel();
        label2.setText("Password");
        text2 = new JPasswordField(15);
        
        ADMIN = new JButton("Login as Administrator");
        EMPLOYEE = new JButton("Login as Employee");
        HOSPITAL = new JButton("Login as Hospital");
        
        panel = new JPanel(new GridLayout(0,1));
        panel.add(label1);
        panel.add(text1);
        panel.add(label2);
        panel.add(text2);
        panel.add(ADMIN);
        panel.add(EMPLOYEE);
        panel.add(HOSPITAL);
        add(panel,BorderLayout.CENTER);
        ADMIN.addActionListener(this);
        EMPLOYEE.addActionListener(this);
        HOSPITAL.addActionListener(this);
        setTitle("Login Form");
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        usern = text1.getText();
        passw = text2.getText();
        
        if(ae.getSource()==ADMIN)
        {
            if (ValidateLogin.adminlogin(usern, passw)){
                Admin admin = new Admin();
                admin.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                admin.setTitle("Blood Bank Administration");
                admin.setSize(400,400);
                admin.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(this,"Incorrect Administration Credentials","Error",JOptionPane.ERROR_MESSAGE);
            }
                
        }
        else if(ae.getSource()==EMPLOYEE)
        {
            if(ValidateLogin.emplogin(usern, passw)){
                Employee emp = new Employee();
                emp.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                emp.setTitle("Blood Bank Employee Access");
                emp.setSize(400,400);
                emp.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(this,"Incorrect Employee Credentials","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(ae.getSource()==HOSPITAL)
        {
            if(ValidateLogin.hosplogin(usern,passw)){
                Hosp hosp = new Hosp();
                hosp.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                hosp.setTitle("Blood Bank Hospital Access");
                hosp.setSize(400,200);
                hosp.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(this,"Incorrect Hospital Credentials","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
       
    }
}


