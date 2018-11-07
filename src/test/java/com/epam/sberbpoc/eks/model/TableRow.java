package com.epam.sberbpoc.eks.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableRow {

    private Map<String, String> row;

    public TableRow() {
        this.row = new HashMap<>();
    }

    public TableRow(Map<String, String > mapRow) {
        this.row = new HashMap<String, String>();
        for (Map.Entry entry: mapRow.entrySet()) {
            this.row.put(entry.getKey().toString(), entry.getValue().toString());
        }
    }

    public void addField(String fieldName, String fieldValue) {
        this.row.put(fieldName, fieldValue);
    }

    public String getField(String fieldName) {
        if ( this.row.containsKey(fieldName) ) {
            return this.row.get(fieldName);
        }
        return null;
    }

    public Map<String, String> getAllFields() {
        return new HashMap<>(this.row);
    }

    public boolean containsField(String fieldName) {
        return this.row.containsKey(fieldName);
    }

    public int numberOfColumns() {
        return this.row.size();
    }

    public String printRow() {
        ArrayList<String> fields = new ArrayList<>(this.row.keySet());

        StringBuilder sb = new StringBuilder();
        sb.append("| ");
        for (String field: fields) {
            sb.append(field + " | ");
        }
        sb.append("\n");

        sb.append("| ");
        for (String field: fields) {
            sb.append(row.get(field) + " | ");
        }
        sb.append("\n");

        return sb.toString();
    }

    public HashMap<String, String> getRowPrimaryKey(List<String> primaryKey) {
        HashMap<String, String> rowPrimaryKey = new HashMap<>();
        for (String field: primaryKey) {
            rowPrimaryKey.put(field, this.row.get(field));
        }
        return rowPrimaryKey;
    }

}
