package com.epam.sberbpoc.eks.appmanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class ApplicationManager {

    private final Properties properties;

    private CSVHelper csvHelper = null;
    private JDBCHelper jdbcHelper = null;
    private AnalyticsHelper analyticsHelper = null;

    public ApplicationManager() {
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() throws SQLException {
        if (jdbcHelper != null) {
            jdbcHelper.closeConnectionToDatabase();
        }
    }

    public JDBCHelper jdbc() {
        if (jdbcHelper == null) {
            jdbcHelper = new JDBCHelper(this);
        }
        return jdbcHelper;
    }

    public CSVHelper csv() {
        if (csvHelper == null) {
            csvHelper = new CSVHelper(this);
        }
        return csvHelper;
    }

    public AnalyticsHelper analytics() {
        if (analyticsHelper == null) {
            analyticsHelper = new AnalyticsHelper(this);
        }
        return analyticsHelper;
    }

    public String getProperty(String key) {
        String value = System.getProperty(key);
        if (value == null) {
            value = properties.getProperty(key);
        }
        return value;
    }

}
