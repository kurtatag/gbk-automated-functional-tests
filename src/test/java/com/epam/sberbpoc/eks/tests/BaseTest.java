package com.epam.sberbpoc.eks.tests;

import com.epam.sberbpoc.eks.appmanager.ApplicationManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;
import java.sql.SQLException;

public class BaseTest {

    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeClass
    public static void setUp() throws IOException {
        app.init();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        app.stop();
    }

}
