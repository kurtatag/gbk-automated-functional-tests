package com.epam.sberbpoc.eks.appmanager;

import com.epam.sberbpoc.eks.model.Table;
import com.epam.sberbpoc.eks.model.TableRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsHelper {
    private ApplicationManager app;

    public AnalyticsHelper(ApplicationManager app) {
        this.app = app;
    }

    // public List compareRows(TableRow expectedRow, TableRow actualRow) {
    //     ArrayList result = new ArrayList();
    //     Boolean resultStatus = true;
    //     StringBuilder resultMessage = new StringBuilder();
    //
    //     if ( expectedRow.numberOfColumns() != actualRow.numberOfColumns() ) {
    //         resultStatus = false;
    //         resultMessage.append("-------\n");
    //         resultMessage.append(String.format("Expected row numberOfColumns:\t %d fields\n", expectedRow.numberOfColumns()));
    //         resultMessage.append(String.format("Actual row numberOfColumns:\t %d fields\n", actualRow.numberOfColumns()));
    //     }
    //
    //     for (HashMap.Entry<String, String> expectedField: expectedRow.getAllFields().entrySet()) {
    //         if ( !actualRow.containsField(expectedField.getKey()) ) {
    //             if (resultStatus == true) {
    //                 resultStatus = false;
    //             }
    //             resultMessage.append("-------\n");
    //             resultMessage.append("Actual row should contain field \"%s\", but the field is not there\n");
    //             continue;
    //         }
    //
    //         if (!(expectedField.getValue().equals(actualRow.getField(expectedField.getKey())))) {
    //             if (resultStatus == true) {
    //                 resultStatus = false;
    //             }
    //             resultMessage.append("-------\n");
    //             resultMessage.append(String.format("Expected value for field \"%s\":\t %s\n",
    //                                                expectedField.getKey(),
    //                                                expectedField.getValue()));
    //             resultMessage.append(String.format("Actual value for field \"%s\":\t %s\n",
    //                     expectedField.getKey(),
    //                     actualRow.getField(expectedField.getKey())));
    //         }
    //     }
    //
    //     if (resultStatus == true) {
    //         resultMessage.append("Rows match!");
    //     }
    //
    //     result.add(0, resultStatus);
    //     result.add(1, resultMessage.toString());
    //
    //     return result;
    // }
    //
    // public List compareTables(Table expectedTable, Table actualTable) {
    //
    //     // Some preparations for future comparison results
    //     ArrayList result = new ArrayList();
    //     Boolean resultStatus = true;
    //     StringBuilder resultMessage = new StringBuilder();
    //
    //     // Check the table from CSV for duplicates
    //     List duplicates = expectedTable.checkDuplicates();
    //     if ( duplicates.size() != 0 ) {
    //         resultStatus = false;
    //         resultMessage.append("-------\n");
    //         resultMessage.append(String.format("Table \"%s\" from csv has duplicates\n", expectedTable.getTableName()));
    //     }
    //
    //     // Check the table from HIVE for duplicates
    //     duplicates = actualTable.checkDuplicates();
    //     if ( duplicates.size() != 0 ) {
    //         resultStatus = false;
    //         resultMessage.append("-------\n");
    //         resultMessage.append(String.format("Table \"%s\" from HIVE has duplicates\n",
    //                                             actualTable.getTableName()));
    //     }
    //
    //     // If there duplicates in either version of the table, the comparison stops
    //     if (resultStatus == false) {
    //         result.add(resultStatus);
    //         result.add(resultMessage.toString());
    //         return result;
    //     }
    //
    //     // Now we just load rows from both tables to corresponding lists
    //     List<TableRow> expectedTableRows = expectedTable.getAllRows();
    //     List<TableRow> actualTableRows = actualTable.getAllRows();
    //
    //     // The following loop actually does the comparison.
    //     // The idea is compare only those couple of rows (one from each table)
    //     // that have matching primary keys.
    //     // So we first locate those two matching rows and then compare them in separate function.
    //     List rowComparisonResult = null;
    //     for (TableRow expectedRow: expectedTableRows) {
    //         secondTable: for (TableRow actualRow: actualTableRows) {
    //             boolean correspondingFieldFound = true;
    //             for (String pkField: expectedTable.getPrimaryKey()) {
    //                 if ( !(expectedRow.getField(pkField).equals(actualRow.getField(pkField))) ) {
    //                     correspondingFieldFound = false;
    //                     break;
    //                 }
    //             }
    //             if (correspondingFieldFound == true) {
    //                 rowComparisonResult = this.compareRows(expectedRow, actualRow);
    //                 break secondTable;
    //             }
    //         }
    //         if (rowComparisonResult != null) {
    //             if ((boolean) rowComparisonResult.get(0) == false) {
    //                 if (resultStatus == true) {
    //                     resultStatus = false;
    //                 }
    //                 resultMessage.append("\n=======\n");
    //                 resultMessage.append("Rows do not match!\n");
    //                 resultMessage.append("Table:\t" + actualTable.getTableName() + "\n");
    //                 resultMessage.append("Primary Key:\t");
    //                 resultMessage.append(printPrimaryKey(actualTable.getPrimaryKey(), expectedRow) + "\n");
    //                 resultMessage.append((String) rowComparisonResult.get(1));
    //             }
    //         }
    //         rowComparisonResult = null;
    //     }
    //
    //     if (resultStatus == true) {
    //         resultMessage.append("Tables match!");
    //     }
    //
    //     result.add(0, resultStatus);
    //     result.add(1, resultMessage.toString());
    //
    //     return result;
    //
    // }

    private String printPrimaryKey(List<String> pk, TableRow row) {
        StringBuilder sb = new StringBuilder();
        for (String field: pk) {
            sb.append(field + "=" + row.getField(field) + " ");
        }
        return sb.toString();
    }

    public List checkTableSchemaTheSame(Table table1, Table table2) {

        List result = new ArrayList();
        boolean resultStatus = true;
        StringBuilder resultMessage = new StringBuilder();

        Map<String, String> schema1 = table1.getTableSchema();
        Map<String, String> schema2 = table2.getTableSchema();

        boolean problem = false;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\nThe following fields are present in table \"%s\", but not found in table \"%s\":\n",
                table1.getTableName(),
                table2.getTableName()));
        sb.append("-------\n");
        sb.append("fieldName : fieldType\n");
        sb.append("-------\n");
        for (String key: schema1.keySet()) {
            if (schema1.get(key).equals(schema2.get(key))) {
                continue;
            } else {
                problem = true;
                resultStatus = false;
                sb.append(key + " : " + schema1.get(key) + "\n");
            }
        }
        if (problem) {
            resultMessage.append(sb.toString());
        }

        problem = false;
        sb = new StringBuilder();
        sb.append(String.format("\nThe following fields are present in table \"%s\", but not found in table \"%s\":\n",
                table2.getTableName(),
                table1.getTableName()));
        sb.append("-------\n");
        sb.append("fieldName : fieldType\n");
        sb.append("-------\n");
        for (String key: schema2.keySet()) {
            if (schema2.get(key).equals(schema1.get(key))) {
                continue;
            } else {
                problem = true;
                resultStatus = false;
                sb.append(key + " : " + schema2.get(key) + "\n");
            }
        }
        if (problem) {
            resultMessage.append(sb.toString());
        }

        result.add(resultStatus);
        result.add(resultMessage.toString());
        return result;
    }

    public boolean checkRowsNumberTheSame(Table table1, Table table2) {
        return table1.numberOfRows() == table2.numberOfRows();
    }

    public List checkTableHasNoDuplicates(Table table) {
        List result = new ArrayList();
        boolean resultStatus = true;
        StringBuilder resultMessage = new StringBuilder();

        List<List<TableRow>> duplicates = new ArrayList<>();

        // discover duplicates and put them in separate backets within "duplicates container (the variable above)"
        for (TableRow row: table.getAllRows()) {
            boolean theRowIsDuplicate = false;
            for (List<TableRow> bucket: duplicates) {
                List<String> fieldsToCheck = new ArrayList<>(table.getTableSchema().keySet());
                if ( checkRowsAreDuplicates(bucket.get(0), row, fieldsToCheck) ) {
                    bucket.add(row);
                    theRowIsDuplicate = true;
                    break;
                }
            }
            if (!theRowIsDuplicate) {
                List<TableRow> newBucket = new ArrayList<>();
                newBucket.add(row);
                duplicates.add(newBucket);
            }
        }

        // process "duplicates container" and create "resultMessage"
        for (List<TableRow> bucket: duplicates) {
            if (bucket.size() > 1) {
                resultStatus = false;
                Table tempTable = new Table(table.getTableName(), table.getPrimaryKey());
                tempTable.updateTableSchema(table.getTableSchema());
                for (TableRow row: bucket) {
                    tempTable.addRow(row);
                }
                resultMessage.append(tempTable.printTable());
            }
        }

        result.add(resultStatus);
        result.add(resultMessage.toString());
        return result;
    }

    public boolean checkRowsAreDuplicates(TableRow row1, TableRow row2, List<String> fieldsToCheck) {
        boolean rowsAreDuplicates = true;
        for (String field: fieldsToCheck) {
            if ( !( row1.getField(field).equals(row2.getField(field)) ) ) {
                rowsAreDuplicates = false;
                break;
            }
        }
        return rowsAreDuplicates;
    }

    public List checkTablesAreEqual(Table table1, Table table2) {
        ArrayList result = new ArrayList();
        boolean resultStatus = true;
        StringBuilder resultMessage = new StringBuilder();

        resultMessage.append("\nData in table is not correct!\n");

        // Cycle one to check presence of rows from table1 in table2
        boolean problem = false;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\nThe following rows are present in table \"%s\", but not found in table \"%s\":",
                table1.getTableName(),
                table2.getTableName()));

        Table tempTable = new Table(table1.getTableName(), table1.getPrimaryKey());
        tempTable.updateTableSchema(table1.getTableSchema());

        for (TableRow rowFromTable1: table1.getAllRows()) {
            boolean rowProblem = true;
            for (TableRow rowFromTable2: table2.getAllRows()) {
                List<String> fieldsToCheck = new ArrayList<>(table1.getTableSchema().keySet());
                if (checkRowsAreDuplicates(rowFromTable1, rowFromTable2, fieldsToCheck)) {
                    rowProblem = false;
                    break;
                }
            }

            if (rowProblem) {
                problem = true;
                resultStatus = false;
                tempTable.addRow(rowFromTable1);
            }

        }

        if (problem) {
            sb.append(tempTable.printTable());
            resultMessage.append(sb.toString());
        }


        // Cycle two to check presence of rows from table2 in table1
        problem = false;
        sb = new StringBuilder();
        sb.append(String.format("\nThe following rows are present in table \"%s\", but not found in table \"%s\":",
                table2.getTableName(),
                table1.getTableName()));

        tempTable = new Table(table2.getTableName(), table2.getPrimaryKey());
        tempTable.updateTableSchema(table2.getTableSchema());

        for (TableRow rowFromTable2: table2.getAllRows()) {
            boolean rowProblem = true;
            for (TableRow rowFromTable1: table1.getAllRows()) {
                List<String> fieldsToCheck = new ArrayList<>(table2.getTableSchema().keySet());
                if (checkRowsAreDuplicates(rowFromTable2, rowFromTable1, fieldsToCheck)) {
                    rowProblem = false;
                    break;
                }
            }

            if (rowProblem) {
                problem = true;
                resultStatus = false;
                tempTable.addRow(rowFromTable2);
            }

        }

        if (problem) {
            sb.append(tempTable.printTable());
            resultMessage.append(sb.toString());
        }


        result.add(resultStatus);
        result.add(resultMessage.toString());
        return result;
    }

}
