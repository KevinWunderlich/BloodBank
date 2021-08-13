/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloodbank;
import javax.swing.*;

class BloodBank {
    public static void main(String[] args) {
        try
        {
            LoginForm frame = new LoginForm();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            frame.setTitle("Login");
            frame.setSize(400,200);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}
