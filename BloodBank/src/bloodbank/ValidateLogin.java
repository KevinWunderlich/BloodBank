package bloodbank;

import java.sql.*;

class ValidateLogin {
    protected static boolean adminlogin(String usern, String passw){
        try{
            ConnectionManager Connection = new ConnectionManager();  
            Connection conn = Connection.createConnection();
                    
            CallableStatement logStmt = conn.prepareCall("{call adminlogin(?,?,?,?)}");
            logStmt.setString(1,usern);
            logStmt.setString(2, passw);
            logStmt.registerOutParameter(3,Types.VARCHAR);
            logStmt.registerOutParameter(4, Types.VARCHAR);
            logStmt.execute();
            String user = logStmt.getString(3);
            String pass = logStmt.getString(4);
            Connection.closeConnection();
            
            if(user.equals(usern) && pass.equals(passw))
                return true;
            else
                return false;
        }
        catch(Exception e){
            return false;
        }
    }
    protected static boolean emplogin(String usern, String passw){
        try{
            ConnectionManager Connection = new ConnectionManager();  
            Connection conn = Connection.createConnection();
            
            CallableStatement logStmt = conn.prepareCall("{call emplogin(?,?,?,?)}");
            logStmt.setString(1,usern);
            logStmt.setString(2, passw);
            logStmt.registerOutParameter(3,Types.VARCHAR);
            logStmt.registerOutParameter(4, Types.VARCHAR);
            logStmt.execute();
            String user = logStmt.getString(3);
            String pass = logStmt.getString(4);
            Connection.closeConnection();
            
            if(user.equals(usern) && pass.equals(passw))
                return true;
            else
                return false;
        }
        catch(Exception e){
            return false;
        }
    }
    protected static boolean hosplogin(String usern, String passw){
        try{
            ConnectionManager Connection = new ConnectionManager();  
            Connection conn = Connection.createConnection();
            
            CallableStatement logStmt = conn.prepareCall("{call hosplogin(?,?,?,?)}");
            logStmt.setString(1,usern);
            logStmt.setString(2, passw);
            logStmt.registerOutParameter(3,Types.VARCHAR);
            logStmt.registerOutParameter(4, Types.VARCHAR);
            logStmt.execute();
            String user = logStmt.getString(3);
            String pass = logStmt.getString(4);
            Connection.closeConnection();
            
            if(user.equals(usern) && pass.equals(passw))
                return true;
            else
                return false;
        }
        catch(Exception e){
            return false;
        }
    }
}
