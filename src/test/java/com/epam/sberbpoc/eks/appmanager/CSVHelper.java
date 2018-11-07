package com.epam.sberbpoc.eks.appmanager;

import com.epam.sberbpoc.eks.model.Table;
import com.epam.sberbpoc.eks.model.TableRow;
import com.opencsv.CSVReaderHeaderAware;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CSVHelper {
    private ApplicationManager app;

    public CSVHelper(ApplicationManager app) {
        this.app = app;
    }

    public Table readTableFromFile(String fileNameString, String tableName, List<String> primaryKey) throws IOException {

        Table table = new Table(tableName, primaryKey);

        FileReader fileReader = new FileReader(fileNameString);

        CSVReaderHeaderAware values = null;
        try {
            values = new CSVReaderHeaderAware(fileReader);

            Map<String, String> row = null;
            row = values.readMap();
            table.updateTableSchema(row);
            while (true) {
                row = values.readMap();
                if (row == null) break;
                table.addRow(new TableRow(row));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileReader.close();
        }

        return table;
    }
}
