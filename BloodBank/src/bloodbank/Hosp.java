/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Hosp extends JFrame implements ActionListener {
    JButton SUBMIT;
    JComboBox bloodlist;
    JPanel panel;
    JLabel label1;
    String bType;
    
    protected Hosp()
    {
        String[] bTypes = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        
        bloodlist = new JComboBox(bTypes);
        label1 = new JLabel("Choose Blood Type: ");
        SUBMIT = new JButton("Submit");
        
        panel = new JPanel();
        panel.add(label1);
        panel.add(bloodlist);
        panel.add(SUBMIT);
        add(panel,BorderLayout.CENTER);
        SUBMIT.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        bType = bloodlist.getSelectedItem().toString();
        
        try{
            ConnectionManager Connection = new ConnectionManager();  
            Connection conn = Connection.createConnection();
            
            CallableStatement Stmt = conn.prepareCall("{call bCount(?,?)}");
            Stmt.setString(1,bType);
            Stmt.registerOutParameter(2,Types.INTEGER);
            Stmt.execute();
            float bCount = Stmt.getFloat(2);
            Connection.closeConnection();
            
            JOptionPane.showMessageDialog(null, "There are " + bCount + " pints of blood type " + bType + " currently available.","Quantity Available",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e){
        }
    
    }
}
