package com.dbms.service;

import com.dbms.model.Header;
import com.dbms.model.Row;
import com.dbms.model.Table;
import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatabaseMangementService {
    private Map<String, Table> tables;

    public DatabaseMangementService() {
        this.tables = new HashMap<>();
    }

    public Table createTable(@NonNull final String name, @NonNull List<Header> headerList) throws Exception {
        if(tables.containsKey(name)) {
            throw new Exception("Table name already existss");
        }
        Table newTable = new Table(name, headerList);
        tables.put(name, newTable);
        return newTable;
    }

    public void deleteTable(@NonNull final String name) throws Exception {
        if(!tables.containsKey(name)) {
            throw new Exception("Table name doesn't exists");
        }
        tables.remove(name);
        return;
    }

    public int insertRowIntoTable(@NonNull final String name, @NonNull final List<Object> row) throws Exception {
        if(!tables.containsKey(name)) {
            throw new Exception("Table name doesn't exists");
        }
        return tables.get(name).insertRowIntoTable(row);
    }

    public boolean deleteRowFromTable(@NonNull final String name, @NonNull final Integer rowId) throws Exception {
        if(!tables.containsKey(name)) {
            throw new Exception("Table name doesn't exists");
        }
        return tables.get(name).deleteRowWithRowId(rowId);
    }

    public List<String> listOfTables() {
        return tables.entrySet().stream().map(k-> k.getKey()).collect(Collectors.toList());
    }

    public List<Header> listOfTableHeaderDetails(@NonNull final String name) throws Exception {
        if(!tables.containsKey(name)) {
            throw new Exception("Table name doesn't exists");
        }
        return tables.get(name).getHeaderList();
    }

    public List<Row> listOfTableRowDetails(@NonNull final String name) throws Exception {
        if(!tables.containsKey(name)) {
            throw new Exception("Table name doesn't exists");
        }
        return tables.get(name).getRowsList();
    }

    public void createIndex(String table, String header) throws Exception {
        if(!tables.containsKey(table)) {
            throw new Exception("Table name doesn't exists");
        }
        tables.get(table).createIndex(header);
    }

    public Map<Header, Map<Object, Integer>> getAllIndexes(@NonNull final String table) throws Exception {
        if(!tables.containsKey(table)) {
            throw new Exception("Table name doesn't exists");
        }
        return tables.get(table).getIndexes();
    }

}
