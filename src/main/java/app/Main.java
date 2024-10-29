package app;

import app.config.AppConfig;
import app.config.DoctorPopulator;
import app.daos.MockUp.DoctorMockDAO;

public class Main
{
    public static void main(String[] args)
    {
        DoctorMockDAO doctorMockDAO = new DoctorMockDAO();
        DoctorPopulator.populateDoctorList(doctorMockDAO);
        AppConfig.startServer();
    }
}
