package lk.ijse.carhire;

import lk.ijse.carhire.dao.SessionFactoryConfiguration;
import org.hibernate.Session;

public class LauncherWrapper {
    public static void main(String []args){
        Launcher.main(args);
        Session session = SessionFactoryConfiguration.getInstance().getSession();

    }
}
