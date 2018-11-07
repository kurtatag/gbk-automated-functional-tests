package com.epam.sberbpoc.eks.tests;

import com.epam.sberbpoc.eks.model.Table;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EKSDM_BALANCE_DataCorrectnessTest extends BaseTest {

    @Test
    public void testNoDuplicatesInTable__eksdm_balance_csv() throws SQLException, IOException {
        // Specify the target database table
        String databaseName = app.getProperty("hive.database.gbk");
        String tableName = databaseName + ".eksdm_balance";

        // Specify primary key fields for the target database table
        List<String> primaryKey = new ArrayList<>();
        primaryKey.add("eksdm_balance.reportdate");
        primaryKey.add("eksdm_balance.coa_num_1_3");
        primaryKey.add("eksdm_balance.coa_num_4_2");
        primaryKey.add("eksdm_balance.coa_num_6_3");
        primaryKey.add("eksdm_balance.coa_num_14_2");
        primaryKey.add("eksdm_balance.coa_num_16_2");

        // Specify csv file with use case data
        String useCase = app.getProperty("useCase");
        String csv = String.format("src/test/resources/csv_gbk/%s/%s.csv",useCase, tableName);

        // Load data from target table: CSV(data described in use cases)
        Table tableFromCSV = app.csv().readTableFromFile(csv, tableName, primaryKey);

        List testResult = app.analytics().checkTableHasNoDuplicates(tableFromCSV);

        // Prepare Error Message
        String errorMessage = "\nTABLE HAS DUPLICATES (CSV):\n" +  (String) testResult.get(1);

        Assert.assertTrue(errorMessage, (boolean) testResult.get(0));
    }

    @Test
    public void testNoDuplicatesInTable__eksdm_balance_hive() throws SQLException, IOException {
        // Specify the target database table
        String databaseName = app.getProperty("hive.database.gbk");
        String tableName = databaseName + ".eksdm_balance";

        // Specify primary key fields for the target database table
        List<String> primaryKey = new ArrayList<>();
        primaryKey.add("eksdm_balance.reportdate");
        primaryKey.add("eksdm_balance.coa_num_1_3");
        primaryKey.add("eksdm_balance.coa_num_4_2");
        primaryKey.add("eksdm_balance.coa_num_6_3");
        primaryKey.add("eksdm_balance.coa_num_14_2");
        primaryKey.add("eksdm_balance.coa_num_16_2");

        // Specify sql string for your query
        String sql = String.format("SELECT * FROM %s", tableName);

        // Load data from target table: from Hive(actual data)
        Table tableFromHive = app.jdbc().executeQuery(sql, tableName, primaryKey);

        List testResult = app.analytics().checkTableHasNoDuplicates(tableFromHive);

        // Prepare Error Message
        String errorMessage = "\nTABLE HAS DUPLICATES (HIVE):\n" +  (String) testResult.get(1);

        Assert.assertTrue(errorMessage, (boolean) testResult.get(0));
    }

    @Test
    public void testRowsNumberIsCorrectInTable__eksdm_balance() throws SQLException, IOException {
        // Specify the target database table
        String databaseName = app.getProperty("hive.database.gbk");
        String tableName = databaseName + ".eksdm_balance";

        // Specify primary key fields for the target database table
        List<String> primaryKey = new ArrayList<>();
        primaryKey.add("eksdm_balance.reportdate");
        primaryKey.add("eksdm_balance.coa_num_1_3");
        primaryKey.add("eksdm_balance.coa_num_4_2");
        primaryKey.add("eksdm_balance.coa_num_6_3");
        primaryKey.add("eksdm_balance.coa_num_14_2");
        primaryKey.add("eksdm_balance.coa_num_16_2");

        // Specify sql string for your query
        String sql = String.format("SELECT * FROM %s", tableName);

        // Specify csv file with use case data
        String useCase = app.getProperty("useCase");
        String csv = String.format("src/test/resources/csv_gbk/%s/%s.csv",useCase, tableName);

        // Load data from target table:  both from Hive(actual data) and CSV(data described in use cases)
        Table tableFromHive = app.jdbc().executeQuery(sql, tableName, primaryKey);
        Table tableFromCSV = app.csv().readTableFromFile(csv, tableName, primaryKey);

        // Prepare Error Message
        String message = "\nNumber of rows in table \"" + tableFromHive.getTableName() + "\" is not correct!\n"
                       + "\nEXPECTED RESULT (CSV): " + tableFromCSV.numberOfRows() + " rows"
                       + "\nACTUAL RESULT (HIVE): "   + tableFromHive.numberOfRows() + " rows\n";

        Assert.assertTrue(message, app.analytics().checkRowsNumberTheSame(tableFromCSV, tableFromHive));
    }

    @Test
    public void testDataIsCorrectInTable__eksdm_balance() throws SQLException, IOException {
        // Specify the target database table
        String databaseName = app.getProperty("hive.database.gbk");
        String tableName = databaseName + ".eksdm_balance";

        // Specify primary key fields for the target database table
        List<String> primaryKey = new ArrayList<>();
        primaryKey.add("eksdm_balance.reportdate");
        primaryKey.add("eksdm_balance.coa_num_1_3");
        primaryKey.add("eksdm_balance.coa_num_4_2");
        primaryKey.add("eksdm_balance.coa_num_6_3");
        primaryKey.add("eksdm_balance.coa_num_14_2");
        primaryKey.add("eksdm_balance.coa_num_16_2");

        // Specify sql string for your query
        String sql = String.format("SELECT * FROM %s", tableName);

        // Specify csv file with use case data
        String useCase = app.getProperty("useCase");
        String csv = String.format("src/test/resources/csv_gbk/%s/%s.csv",useCase, tableName);

        // Load data from target table:  both from Hive(actual data) and CSV(data described in use cases)
        Table tableFromHive = app.jdbc().executeQuery(sql, tableName + " (HIVE)", primaryKey);
        Table tableFromCSV = app.csv().readTableFromFile(csv, tableName + " (CSV)", primaryKey);

        // Compare actual data from Hive against data in CSV (use cases)
        List comparisonResult = app.analytics().checkTablesAreEqual(tableFromCSV, tableFromHive);

        // Check the comparison results and report them
        Assert.assertTrue((String) comparisonResult.get(1), (Boolean) comparisonResult.get(0));
    }

}
