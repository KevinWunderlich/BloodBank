
package bloodbank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

class Employee extends JFrame implements ActionListener{
    JButton SUBMIT,LIST;
    JPanel panel;
    JLabel label2,label3,label4,label5,label6;
    JTextField text2,text3,text4,text5,text6;
    JComboBox bloodlist;
    final String key = AES.getKey(); 
    String fnEncrypt,lnEncrypt,addEncrypt,bType;
    float quant;
    String fname[];
    String lname[];
    String add[];
    String type[];
    Float table_quant[];
    int maxID;
    
    protected Employee()
    {
        String[] bTypes = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        
        label2 = new JLabel();
        label2.setText("First Name");
        text2 = new JTextField(50);
        
        label3 = new JLabel();
        label3.setText("Last Name");
        text3 = new JTextField(50);
        
        label4 = new JLabel();
        label4.setText("Address");
        text4 = new JTextField(50);
        
        bloodlist = new JComboBox(bTypes);
        label5 = new JLabel("Blood Type");
        
        label6 = new JLabel();
        label6.setText("Quantity");
        text5 = new JTextField(15);
        
        SUBMIT = new JButton("Submit");
        LIST = new JButton("View List of Donations");
        
        panel = new JPanel(new GridLayout(0,1));
        
        panel.add(label2);
        panel.add(text2);
        
        panel.add(label3);
        panel.add(text3);
        
        panel.add(label4);
        panel.add(text4);
        
        panel.add(label5);
        panel.add(bloodlist);
        
        panel.add(label6);
        panel.add(text5);
        
        panel.add(SUBMIT);
        SUBMIT.addActionListener(this);
        panel.add(LIST);
        LIST.addActionListener(this);
        
        add(panel,BorderLayout.CENTER);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()== SUBMIT){
        
            fnEncrypt = AES.encrypt(text2.getText(), key);
            lnEncrypt = AES.encrypt(text3.getText(), key);
            addEncrypt = AES.encrypt(text4.getText(), key);
            bType = bloodlist.getSelectedItem().toString();
            quant =  Float.parseFloat(text5.getText());
        
            try{  
                ConnectionManager Connection = new ConnectionManager();  
                Connection conn = Connection.createConnection();
            
                CallableStatement idStmt = conn.prepareCall("{call maxid(?)}");
                CallableStatement cStmt = conn.prepareCall("{call donate(?,?,?,?,?,?)}");
                    
                idStmt.registerOutParameter(1,Types.INTEGER);
                idStmt.execute();
                int donationID = idStmt.getInt(1) + 1; 
        
                cStmt.setInt(1, donationID);
                cStmt.setString(2, fnEncrypt);
                cStmt.setString(3, lnEncrypt);
                cStmt.setString(4, addEncrypt);
                cStmt.setString(5, bType);
                cStmt.setFloat(6, quant);
                cStmt.execute();
                Connection.closeConnection();
                JOptionPane.showMessageDialog(null,"Donation Accepted. Generated new Donation ID " + donationID, "Donation Accepted",JOptionPane.INFORMATION_MESSAGE);
            }
            catch(Exception e){       
            }
        
            text2.setText("");
            text3.setText("");
            text4.setText("");
            text5.setText("");
        }
        else if(ae.getSource()==LIST){
            try{
                
                ConnectionManager Connection = new ConnectionManager();  
                Connection conn = Connection.createConnection();

                CallableStatement idStmt = conn.prepareCall("{call maxid(?)}");
                CallableStatement cStmt = conn.prepareCall("{call donationlist(?,?,?,?,?,?)}");
        
                idStmt.registerOutParameter(1,Types.INTEGER);
                idStmt.execute();
                maxID = idStmt.getInt(1);
                fname = new String[maxID+1];
                lname = new String[maxID+1];
                add = new String[maxID+1];
                type = new String[maxID+1];
                table_quant = new Float[maxID+1];
        
                JFrame frame = new JFrame("List of Donations");
                JTable table = new JTable();
                DefaultTableModel model = new DefaultTableModel(new String[]{"Donation ID", "First Name", "Last Name" , "Address", "Blood Type", "Quantity"}, 0);
                for(int i=1;i <=maxID; i++){
                    cStmt.setInt(1,i);
                    cStmt.registerOutParameter(2,Types.VARCHAR);
                    cStmt.registerOutParameter(3, Types.VARCHAR);
                    cStmt.registerOutParameter(4, Types.VARCHAR);
                    cStmt.registerOutParameter(5, Types.VARCHAR);
                    cStmt.registerOutParameter(6, Types.FLOAT);
                    cStmt.execute();
                    fname[i] = AES.decrypt(cStmt.getString(2), key);
                    lname[i] = AES.decrypt(cStmt.getString(3), key);
                    add[i] = AES.decrypt(cStmt.getString(4), key);
                    type[i] = cStmt.getString(5);
                    table_quant[i] = cStmt.getFloat(6);
            
                    Object[] row = {i, fname[i], lname[i], add[i], type[i], table_quant[i]};
                    model.addRow(row);
                }
                table.setModel(model);
                frame.add(new JScrollPane(table));
                frame.setSize(400,400);
                frame.setVisible(true);
                }
            catch(Exception e){       
                e.printStackTrace();
            }          
        }
    }  
}

