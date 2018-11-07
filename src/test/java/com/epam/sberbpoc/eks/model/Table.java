package com.epam.sberbpoc.eks.model;

import java.util.*;

public class Table {

    private String tableName;
    private ArrayList<TableRow> tableRows;
    private Map<String, String> tableSchema;
    private List<String> primaryKey;

    public Table(String tableName, List<String> primaryKey) {
        this.tableName = tableName;
        this.tableRows = new ArrayList<>();
        this.tableSchema = new HashMap<>();
        this.primaryKey = primaryKey;

    }

    public void updateTableSchema(Map columns) {
        this.tableSchema.putAll(columns);
    }

    public List<TableRow> getAllRows() {
        return new ArrayList<>(this.tableRows);
    }

    public void addRow(TableRow row) {
        this.tableRows.add(row);
    }

    public void removeRow(TableRow row) {
        if (this.tableRows.contains(row)) {
            this.tableRows.remove(row);
        }
    }

    public int numberOfRows() {
        return this.tableRows.size();
    }

    public String getTableName() {
        return this.tableName;
    }

    public List<String> getPrimaryKey() {
        return new ArrayList<>(primaryKey);
    }

    public Map<String, String> getTableSchema() {
        return new HashMap<>(tableSchema);
    }

    public String printTableSchema() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=======\n");
        sb.append("Schema For Table:\n");
        sb.append(this.tableName + "\n");
        sb.append("--------------------\n");
        sb.append("Field : Type\n");
        sb.append("--------------------\n");
        for (Map.Entry<String, String> entry: this.tableSchema.entrySet()) {
            sb.append(entry.getKey() + " : " + entry.getValue() + "\n");
        }
        return sb.toString();
    }

    public String printTable() {
        ArrayList<String> fields = new ArrayList<>(tableSchema.keySet());

        StringBuilder sb = new StringBuilder();

        sb.append("\n=======\n");
        sb.append("Table name:\n");
        sb.append(this.tableName + "\n");
        sb.append("-------\n");

        sb.append("| ");
        for (String field: fields) {
            sb.append(field + " | ");
        }
        sb.append("\n");

        sb.append("-------\n");

        for (TableRow row: this.tableRows) {
            sb.append("| ");
            for (String field: fields) {
                sb.append(row.getField(field) + " | ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public TableRow findRowByPrimaryKey(Map<String, String> rowPrimaryKey) {
        for (TableRow row: this.tableRows) {
            Map<String, String> currentRowPrimaryKey = row.getRowPrimaryKey(primaryKey);
            boolean primaryKeysAreEqual = true;
            for (String field: primaryKey) {
                primaryKeysAreEqual = (row.getField(field).equals(rowPrimaryKey.get(field))) && primaryKeysAreEqual;
            }
            if (primaryKeysAreEqual) {
                return row;
            }
        }
        return null;
    }
}
