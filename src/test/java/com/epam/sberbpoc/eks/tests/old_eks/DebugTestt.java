package com.epam.sberbpoc.eks.tests.old_eks;

import com.epam.sberbpoc.eks.model.Table;
import com.epam.sberbpoc.eks.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DebugTestt extends BaseTest {

    @Test
    public void test2() {
        System.out.println("Hello from test2");
        System.out.println(app.getProperty("useCase"));
    }

    // @Test
    // public void testSchemaIsCorrectForTable_z_pr_cred() throws SQLException, IOException {
    //     // Specify the target database table
    //     String databaseName = app.getProperty("hive.database.eks");
    //     String tableName = databaseName + ".z_pr_cred";
    //
    //     // Specify primary key fields for the target database table
    //     List<String> primaryKey = new ArrayList<>();
    //     primaryKey.add("z_pr_cred.id");
    //     primaryKey.add("z_pr_cred.ctl_validfrom");
    //
    //     // Specify sql string for your query
    //     String sql = String.format("SELECT * FROM %s", tableName);
    //
    //     // Specify csv file with use case data
    //     // String csv = String.format("src/test/resources/csv/%s.csv", tableName);
    //     String csv = "src/test/resources/csv/debug/ess_aux_stream_rv_mario.z_pr_cred3.csv";
    //
    //     // Load data from target table:  both from Hive(actual data) and CSV(data described in use cases)
    //     Table tableFromHive = app.jdbc().executeQuery(sql, tableName + " (HIVE)", primaryKey);
    //     Table tableFromCSV = app.csv().readTableFromFile(csv, tableName + " (CSV)", primaryKey);
    //
    //     List testResult = app.analytics().checkTableSchemaTheSame(tableFromCSV, tableFromHive);
    //
    //     // Prepare Error Message
    //     String errorMessage = "\nTable schema is not correct!\n" + (String) testResult.get(1);
    //
    //     // Check the comparison results and report them
    //     Assert.assertTrue(errorMessage, (boolean) testResult.get(0));
    // }
    //
    // @Test
    // public void testNoDuplicatesInTable__z_pr_cred_csv() throws SQLException, IOException {
    //     // Specify the target database table
    //     String databaseName = app.getProperty("hive.database.eks");
    //     String tableName = databaseName + ".z_pr_cred";
    //
    //     // Specify primary key fields for the target database table
    //     List<String> primaryKey = new ArrayList<>();
    //     primaryKey.add("z_pr_cred.id");
    //     primaryKey.add("z_pr_cred.ctl_validfrom");
    //
    //     // Specify csv file with use case data
    //     String csv = "src/test/resources/csv/debug/ess_aux_stream_rv_mario.z_pr_cred5.csv";
    //
    //     // Load data from target table: CSV(data described in use cases)
    //     Table tableFromCSV = app.csv().readTableFromFile(csv, tableName, primaryKey);
    //
    //     List testResult = app.analytics().checkTableHasNoDuplicates(tableFromCSV);
    //
    //     // Prepare Error Message
    //     String errorMessage = "\nTABLE HAS DUPLICATES (CSV):\n" +  (String) testResult.get(1);
    //
    //     Assert.assertTrue(errorMessage, (boolean) testResult.get(0));
    // }
    //
    // @Test
    // public void testRowsNumberIsCorrectInTable__z_pr_cred() throws SQLException, IOException {
    //     // Specify the target database table
    //     String databaseName = app.getProperty("hive.database.eks");
    //     String tableName = databaseName + ".z_pr_cred";
    //
    //     // Specify primary key fields for the target database table
    //     List<String> primaryKey = new ArrayList<>();
    //     primaryKey.add("z_pr_cred.id");
    //     primaryKey.add("z_pr_cred.ctl_validfrom");
    //
    //     // Specify sql string for your query
    //     String sql = String.format("SELECT * FROM %s", tableName);
    //
    //     // Specify csv file with use case data
    //     String csv = "src/test/resources/csv/debug/ess_aux_stream_rv_mario.z_pr_cred4.csv";
    //
    //     // Load data from target table:  both from Hive(actual data) and CSV(data described in use cases)
    //     Table tableFromHive = app.jdbc().executeQuery(sql, tableName, primaryKey);
    //     Table tableFromCSV = app.csv().readTableFromFile(csv, tableName, primaryKey);
    //
    //     // Prepare Error Message
    //     String message = "\nNumber of rows in table \"" + tableFromHive.getTableName() + "\" is not correct!\n"
    //                    + "\nEXPECTED RESULT (CSV): " + tableFromCSV.numberOfRows() + " rows"
    //                    + "\nACTUAL RESULT (HIVE): "   + tableFromHive.numberOfRows() + " rows\n";
    //
    //     Assert.assertTrue(message, app.analytics().checkRowsNumberTheSame(tableFromCSV, tableFromHive));
    // }
    //
    // @Test
    // public void testDataIsCorrectInTable__z_pr_cred() throws SQLException, IOException {
    //     // Specify the target database table
    //     String databaseName = app.getProperty("hive.database.eks");
    //     String tableName = databaseName + ".z_pr_cred";
    //
    //     // Specify primary key fields for the target database table
    //     List<String> primaryKey = new ArrayList<>();
    //     primaryKey.add("z_pr_cred.id");
    //     primaryKey.add("z_pr_cred.ctl_validfrom");
    //
    //     // Specify sql string for your query
    //     String sql = String.format("SELECT * FROM %s", tableName);
    //
    //     // Specify csv file with use case data
    //     String csv = "src/test/resources/csv/debug/ess_aux_stream_rv_mario.z_pr_cred2.csv";
    //
    //     // Load data from target table:  both from Hive(actual data) and CSV(data described in use cases)
    //     Table tableFromHive = app.jdbc().executeQuery(sql, tableName + " (HIVE)", primaryKey);
    //     Table tableFromCSV = app.csv().readTableFromFile(csv, tableName + " (CSV)", primaryKey);
    //
    //     // Compare actual data from Hive against data in CSV (use cases)
    //     List comparisonResult = app.analytics().checkTablesAreEqual(tableFromCSV, tableFromHive);
    //
    //     // Check the comparison results and report them
    //     Assert.assertTrue((String) comparisonResult.get(1), (Boolean) comparisonResult.get(0));
    // }
    //
    // @Test
    // public void testSchemaIsCorrectForTable_z_cred_in_port() throws SQLException, IOException {
    //     // Specify the target database table
    //     String databaseName = app.getProperty("hive.database.eks");
    //     String tableName = databaseName + ".z_cred_in_port";
    //
    //     // Specify primary key fields for the target database table
    //     List<String> primaryKey = new ArrayList<>();
    //     primaryKey.add("z_cred_in_port.c_pr_cred");
    //     primaryKey.add("z_cred_in_port.c_date_begin");
    //     primaryKey.add("z_cred_in_port.ctl_validfrom");
    //
    //     // Specify sql string for your query
    //     String sql = String.format("SELECT * FROM %s", tableName);
    //
    //     // Specify csv file with use case data
    //     // String csv = String.format("src/test/resources/csv/%s.csv", tableName);
    //     String csv = "src/test/resources/csv/debug/ess_aux_stream_rv_mario.z_cred_in_port2.csv";
    //
    //     // Load data from target table:  both from Hive(actual data) and CSV(data described in use cases)
    //     Table tableFromHive = app.jdbc().executeQuery(sql, tableName + " (HIVE)", primaryKey);
    //     Table tableFromCSV = app.csv().readTableFromFile(csv, tableName + " (CSV)", primaryKey);
    //
    //     List testResult = app.analytics().checkTableSchemaTheSame(tableFromCSV, tableFromHive);
    //
    //     // Prepare Error Message
    //     String errorMessage = "\nTable schema is not correct!\n" + (String) testResult.get(1);
    //
    //     // Check the comparison results and report them
    //     Assert.assertTrue(errorMessage, (boolean) testResult.get(0));
    // }
    //
    // @Test
    // public void testSchemaIsCorrectForTable_t_productparty_bt() throws SQLException, IOException {
    //     // Specify the target database table
    //     String databaseName = app.getProperty("hive.database.did");
    //     String tableName = databaseName + ".t_productparty_bt";
    //
    //     // Specify primary key fields for the target database table
    //     List<String> primaryKey = new ArrayList<>();
    //     primaryKey.add("z_cred_in_port.ctl_objectid");
    //
    //     // Specify sql string for your query
    //     String sql = String.format("SELECT * FROM %s", tableName);
    //
    //     // Specify csv file with use case data
    //     // String csv = String.format("src/test/resources/csv/%s.csv", tableName);
    //     String csv = "src/test/resources/csv/debug/ess_aux_did_stream_rv_mario.t_productparty_bt2.csv";
    //
    //
    //     // Load data from target table:  both from Hive(actual data) and CSV(data described in use cases)
    //     Table tableFromHive = app.jdbc().executeQuery(sql, tableName + " (HIVE)", primaryKey);
    //     Table tableFromCSV = app.csv().readTableFromFile(csv, tableName + " (CSV)", primaryKey);
    //
    //     List testResult = app.analytics().checkTableSchemaTheSame(tableFromCSV, tableFromHive);
    //
    //     // Prepare Error Message
    //     String errorMessage = "\nTable schema is not correct!\n" + (String) testResult.get(1);
    //
    //     // Check the comparison results and report them
    //     Assert.assertTrue(errorMessage, (boolean) testResult.get(0));
    // }
    //
    // @Test
    // public void test() throws SQLException, IOException {
    //     // Specify the target database table
    //     String databaseName = app.getProperty("hive.database.did");
    //     String tableName = databaseName + ".t_productparty_bt";
    //
    //     // Specify primary key fields for the target database table
    //     List<String> primaryKey = new ArrayList<>();
    //     primaryKey.add("z_cred_in_port.ctl_objectid");
    //
    //     // Specify sql string for your query
    //     String sql = String.format("SELECT * FROM %s", tableName);
    //
    //     // Specify csv file with use case data
    //     String csv = String.format("src/test/resources/csv/%s.csv", tableName);
    //     // String csv = "src/test/resources/csv/debug/ess_aux_did_stream_rv_mario.t_productparty_bt2.csv";
    //
    //
    //     // Load data from target table:  both from Hive(actual data) and CSV(data described in use cases)
    //     Table tableFromHive = app.jdbc().executeQuery(sql, tableName, primaryKey);
    //     Table tableFromCSV = app.csv().readTableFromFile(csv, tableName, primaryKey);
    //
    //     System.out.println(tableFromCSV.printTable());
    //     System.out.println(tableFromHive.printTable());
    //
    //     // // Prepare Error Message
    //     // String message = "\nEXPECTED RESULT (CSV):"
    //     //         + tableFromCSV.printTableSchema()
    //     //         + "\nACTUAL RESULT (HIVE):"
    //     //         + tableFromHive.printTableSchema();
    //     //
    //     // // Check the comparison results and report them
    //     // Assert.assertTrue(message, app.analytics().checkTableSchemaTheSame(tableFromCSV, tableFromHive));
    // }

}
