package com.epam.sberbpoc.eks.tests.old_eks;

import com.epam.sberbpoc.eks.model.Table;
import com.epam.sberbpoc.eks.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SchemaCorrectnessTestt extends BaseTest {

    @Test
    public void testSchemaIsCorrectForTable_z_pr_cred() throws SQLException, IOException {
        // Specify the target database table
        String databaseName = app.getProperty("hive.database.eks");
        String tableName = databaseName + ".z_pr_cred";

        // Specify primary key fields for the target database table
        List<String> primaryKey = new ArrayList<>();
        primaryKey.add("z_pr_cred.id");
        primaryKey.add("z_pr_cred.ctl_validfrom");

        // Specify sql string for your query
        String sql = String.format("SELECT * FROM %s", tableName);

        // Specify csv file with use case data
        String useCase = app.getProperty("useCase");
        String csv = String.format("src/test/resources/csv/%s/%s.csv",useCase, tableName);

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
    public void testSchemaIsCorrectForTable_z_cred_in_port() throws SQLException, IOException {
        // Specify the target database table
        String databaseName = app.getProperty("hive.database.eks");
        String tableName = databaseName + ".z_cred_in_port";

        // Specify primary key fields for the target database table
        List<String> primaryKey = new ArrayList<>();
        primaryKey.add("z_cred_in_port.c_pr_cred");
        primaryKey.add("z_cred_in_port.c_date_begin");
        primaryKey.add("z_cred_in_port.ctl_validfrom");

        // Specify sql string for your query
        String sql = String.format("SELECT * FROM %s", tableName);

        // Specify csv file with use case data
        String useCase = app.getProperty("useCase");
        String csv = String.format("src/test/resources/csv/%s/%s.csv",useCase, tableName);

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
    public void testSchemaIsCorrectForTable_t_productparty_bt() throws SQLException, IOException {
        // Specify the target database table
        String databaseName = app.getProperty("hive.database.did");
        String tableName = databaseName + ".t_productparty_bt";

        // Specify primary key fields for the target database table
        List<String> primaryKey = new ArrayList<>();
        primaryKey.add("t_productparty_bt.ctl_objectid");

        // Specify sql string for your query
        String sql = String.format("SELECT * FROM %s", tableName);

        // Specify csv file with use case data
        String useCase = app.getProperty("useCase");
        String csv = String.format("src/test/resources/csv/%s/%s.csv",useCase, tableName);

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
