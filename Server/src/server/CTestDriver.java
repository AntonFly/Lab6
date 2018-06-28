package server;

import java.sql.*;
import java.sql.DriverManager;
import java.util.Properties;

import Lab234.portret;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.postgresql.util.PSQLException;

public class CTestDriver
{
    public static Connection con;
    public static Statement statement;
    public static void doSshTunnel(String strSshUser, String strSshPassword, String strSshHost, int nSshPort, String strRemoteHost, int nLocalPort, int nRemotePort) throws JSchException
    {
        final JSch jsch = new JSch();
        Session session = jsch.getSession( strSshUser, strSshHost, 2222 );
        session.setPassword( strSshPassword );

        final Properties config = new Properties();
        config.put( "StrictHostKeyChecking", "no" );
        session.setConfig( config );

        session.connect();
        System.out.println("tonnel created");
        session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
    }
    public static void addString(portret portret) throws SQLException {
            statement.execute("INSERT INTO collection values('"+portret.NAME+"',) ");
    }
    public static String getPassword(String login) throws SQLException {
        String password;
        Statement statement=con.createStatement();
        ResultSet res=statement.executeQuery("SELECT password FROM users WHERE login=\'"+login+"\';");

        res.next();
        password=res.getString(1);
//        while (res.next())
//            System.out.println(res.getString(1));
        statement.close();
        return password;
    }
    static boolean isBan(String name){
        try {
            Statement statement=con.createStatement();
            ResultSet res=statement.executeQuery("SELECT ban FROM users WHERE login='"+name+"'; ");

            res.next();
            boolean bool=res.getBoolean(1);
            res.getBoolean(1);
            return bool;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    static void ban(String name){
        try {
            Statement statement=con.createStatement();
            ResultSet res=statement.executeQuery("UPDATE users SET ban=TRUE WHERE login='"+name+"'; ");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static boolean checkExist(String name) throws SQLException {
        boolean result;
        Statement statement=con.createStatement();
        ResultSet res=statement.executeQuery("SELECT count(login) FROM users WHERE login='"+name+"';");
        res.next();
             if(Integer.valueOf(res.getString(1))==0){
                 statement.close();
                 return false;}
             else{
                 statement.close();
                 return true;}

    }
    static void createUser(String l,String p)  {
        try {
            Statement statement=con.createStatement();
            statement.execute("INSERT INTO users VALUES('"+l+"','"+p+"');");
            statement.close();
        } catch (PSQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException {
        connectBd();
        System.out.println(getPassword("admin"));
        System.exit(0);

    }
    public static Statement connectBd()
    {
        try
        {
            String strSshUser = "s242461";                  // SSH loging username
            String strSshPassword = "ybb203";                   // SSH login password
            String strSshHost = "helios.cs.ifmo.ru";          // hostname or ip or SSH server
            int nSshPort = 2222;                                    // remote SSH host port number
            String strRemoteHost = "pg";  // hostname or ip of your database server
            int nLocalPort = 63333;                                // local port number use to bind SSH tunnel
            int nRemotePort = 5432;                               // remote port number of your database
            String strDbUser = "s242461";                    // database loging username
            String strDbPassword = "ybb203";                    // database login password

            CTestDriver.doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);

            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:63333/studs", strDbUser, strDbPassword);
                statement= con.createStatement();


        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        finally {
            return statement;
        }

    }
}