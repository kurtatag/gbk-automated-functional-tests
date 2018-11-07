package com.epam.sberbpoc.eks.appmanager;

import com.epam.sberbpoc.eks.model.Table;
import com.epam.sberbpoc.eks.model.TableRow;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCHelper {
    private ApplicationManager app;
    private Connection conn;

    public JDBCHelper(ApplicationManager app) {
        this.app = app;
    }

    private Connection getConnectionToDatabase() throws SQLException {
        if (this.conn == null) {
            String CONN_STRING = app.getProperty("hive.jdbc.url");
            this.conn = DriverManager.getConnection(CONN_STRING);
            System.out.println("Successfully connected to a database!");
        }
        return this.conn;
    }

    public void closeConnectionToDatabase() throws SQLException {
        if (this.conn != null) {
            this.conn.close();
            this.conn = null;
            System.out.println("Connection to the database is closed!");
        }
    }

    public Table executeQuery(String sqlString, String tableName, List<String> primaryKey) throws SQLException {

        Table table = new Table(tableName, primaryKey);

        Statement stmt = null;
        ResultSet res = null;

        try {
            conn = this.getConnectionToDatabase();

            stmt = conn.createStatement();
            stmt.setQueryTimeout(Integer.parseInt(app.getProperty("hive.jdbc.timeout")));

            System.out.println("Running: " + sqlString);
            res = stmt.executeQuery(sqlString);

            ResultSetMetaData rsmd = res.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            Map<String, String> columns = new HashMap<>();

            for (int i = 1; i <= columnsNumber; i++) {
                columns.put(rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
            }

            for (Map.Entry<String, String> entry: new HashMap<>(columns).entrySet()) {
                if (entry.getKey().contains("ctl_surrogatekey") || entry.getKey().contains("loaddate")){
                    columns.remove(entry.getKey());
                }
            }

            table.updateTableSchema(columns);

            while (res.next()) {
                Map row = new HashMap<String, String>();
                for (String colunmName: columns.keySet()) {
                    row.put(colunmName.toString(), (res.getObject(colunmName) == null ? "null" : res.getObject(colunmName).toString()));
                }
                table.addRow(new TableRow(row));
            }

        } catch (SQLException e) {
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Error code: " + e.getErrorCode());
            System.err.println("SQL State: " + e.getSQLState());
        } finally {
            if (res != null) {
                res.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return table;
    }


}
