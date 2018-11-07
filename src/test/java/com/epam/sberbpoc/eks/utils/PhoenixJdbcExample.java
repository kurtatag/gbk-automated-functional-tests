package com.epam.sberbpoc.eks.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PhoenixJdbcExample {

    static final String JDBC_DRIVER = "org.apache.phoenix.queryserver.client.Driver";
    static final String HOST = "ecsa00400926.epam.com";
    static final String PORT = "8765";

    static final String DB_URL = "jdbc:phoenix:thin:url=http://" + HOST + ":" + PORT + ";serialization=PROTOBUF";

    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;

        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database..");

            conn = DriverManager.getConnection(DB_URL);

            System.out.println("Creating statement...");

            st = conn.createStatement();
            String sql;
            sql = "SELECT count(1) FROM khetag_gbk_1000.z_records";
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                String did = rs.getString(1);
                System.out.println("Did found: " + did);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (st != null)
                    st.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");
    }
}
