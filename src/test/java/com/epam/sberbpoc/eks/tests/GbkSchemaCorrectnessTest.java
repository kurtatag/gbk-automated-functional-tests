package com.epam.sberbpoc.eks.tests;

import com.epam.sberbpoc.eks.model.Table;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GbkSchemaCorrectnessTest extends BaseTest {

    @Test
    public void testSchemaIsCorrectForTable_z_ac_fin() throws SQLException, IOException {
        // Specify the target database table
        String databaseName = app.getProperty("hive.database.gbk");
        String tableName = databaseName + ".z_ac_fin";

        // Specify primary key fields for the target database table
        List<String> primaryKey = new ArrayList<>();
        primaryKey.add("z_ac_fin.id");

        // Specify sql string for your query
        String sql = String.format("SELECT * FROM %s", tableName);

        // Specify csv file with use case data
        String useCase = app.getProperty("useCase");
        String csv = String.format("src/test/resources/csv_gbk/%s/%s.csv",useCase, tableName);

        // Load data from target table:  both from Hive(actual data) and CSV(data described in use cases)
        Table tableFromHive = app.jdbc().executeQuery(sql, tableName + " (HIVE)", primaryKey);
        Table tableFromCSV = app.csv().readTableFromFile(csv, tableName + " (CSV)", primaryKey);

        List testResult = app.analytics().checkTableSchemaTheSame(tableFromCSV, tableFromHive);

        // Prepare Error Message
        String errorMessage = "\nTable schema is not correct!\n" + (String) testResult.get(1);

        // Check the comparison results and report them
        Assert.assertTrue(errorMessage, (boolean) testResult.get(0));
    }

    @Test
    public void testSchemaIsCorrectForTable_z_records() throws SQLException, IOException {
        // Specify the target database table
        String databaseName = app.getProperty("hive.database.gbk");
        String tableName = databaseName + ".z_records";

        // Specify primary key fields for the target database table
        List<String> primaryKey = new ArrayList<>();
        primaryKey.add("z_records.id");

        // Specify sql string for your query
        String sql = String.format("SELECT * FROM %s", tableName);

        // Specify csv file with use case data
        String useCase = app.getProperty("useCase");
        String csv = String.format("src/test/resources/csv_gbk/%s/%s.csv",useCase, tableName);

        // Load data from target table:  both from Hive(actual data) and CSV(data described in use cases)
        Table tableFromHive = app.jdbc().executeQuery(sql, tableName + " (HIVE)", primaryKey);
        Table tableFromCSV = app.csv().readTableFromFile(csv, tableName + " (CSV)", primaryKey);

        List testResult = app.analytics().checkTableSchemaTheSame(tableFromCSV, tableFromHive);

        // Prepare Error Message
        String errorMessage = "\nTable schema is not correct!\n" + (String) testResult.get(1);

        // Check the comparison results and report them
        Assert.assertTrue(errorMessage, (boolean) testResult.get(0));
    }

    @Test
    public void testSchemaIsCorrectForTable_eksdm_balance() throws SQLException, IOException {
        // Specify the target database table
        String databaseName = app.getProperty("hive.database.gbk");
        String tableName = databaseName + ".eksdm_balance";

        // Specify primary key fields for the target database table
        List<String> primaryKey = new ArrayList<>();
        primaryKey.add("eksdm_balance.ctl_objectid");

        // Specify sql string for your query
        String sql = String.format("SELECT * FROM %s", tableName);

        // Specify csv file with use case data
        String useCase = app.getProperty("useCase");
        String csv = String.format("src/test/resources/csv_gbk/%s/%s.csv",useCase, tableName);

        // Load data from target table:  both from Hive(actual data) and CSV(data described in use cases)
        Table tableFromHive = app.jdbc().executeQuery(sql, tableName + " (HIVE)", primaryKey);
        Table tableFromCSV = app.csv().readTableFromFile(csv, tableName + " (CSV)", primaryKey);

        List testResult = app.analytics().checkTableSchemaTheSame(tableFromCSV, tableFromHive);

        // Prepare Error Message
        String errorMessage = "\nTable schema is not correct!\n" + (String) testResult.get(1);

        // Check the comparison results and report them
        Assert.assertTrue(errorMessage, (boolean) testResult.get(0));
    }

}
