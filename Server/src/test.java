import Lab234.portret;
import com.jcraft.jsch.JSchException;
import server.CTestDriver;
import server.DatabaseProtocol;
import server.ORM;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws JSchException {
        String strSshUser = "s242461";                  // SSH loging username
        String strSshPassword = "ybb203";                   // SSH login password
        String strSshHost = "helios.cs.ifmo.ru";          // hostname or ip or SSH server
        int nSshPort = 2222;                                    // remote SSH host port number
        String strRemoteHost = "pg";  // hostname or ip of your database server
        int nLocalPort = 63333;                                // local port number use to bind SSH tunnel
        int nRemotePort = 5432;                               // remote port number of your database
        String strDbUser = "s242461";                    // database loging username
        String strDbPassword = "ybb203";                    // database login password

        CTestDriver.connectBd();


//        EntityManagerFactory portretUnit= Persistence.createEntityManagerFactory("1234");
//        EntityManager entityManager=portretUnit.createEntityManager();
//        EntityTransaction entityTransaction=entityManager.getTransaction();
//        entityTransaction.begin();
//        entityManager.persist(new portret("vasia",5,"loc","red",5,10,null));
//        entityTransaction.commit();
//        entityManager.clear();
//        portretUnit.close();

////        System.out.println(DatabaseProtocol.url+DatabaseProtocol.login+DatabaseProtocol.password);
        ORM managerORM= new ORM(portret.class, DatabaseProtocol.url,DatabaseProtocol.login,DatabaseProtocol.password,CTestDriver.statement );
         managerORM.create();
        try {
            ResultSet res = CTestDriver.statement.executeQuery("SELECT * FROM portrets;");
            res.next();
            portret portret=(Lab234.portret)managerORM.getElement(res);
            System.out.println(portret.NAME);

        } catch (SQLException e) {
            e.printStackTrace();
        }


//        managerORM.insert(new portret("2",10,"loc","red",5,10,null));
//        managerORM.update(new portret("2",11,"loc","red",5,10,null));
//           managerORM.getAll();
    }
}
