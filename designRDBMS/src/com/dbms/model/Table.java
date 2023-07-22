package com.dbms.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Table {
    private @Getter @Setter String tableName;
    private static @Getter Integer nextRowId;
    private @Getter @Setter List<Header> headerList;
    private List<Row> rowsList;
    private @Getter Timestamp createdAt;

    private @Getter Map<Header, Map<Object, Integer>> indexes;

    public Table(String tableName, List<Header> headerList) {
        this.tableName = tableName;
        if(headerList.stream().map(h->h.getName()).collect(Collectors.toSet()).size() != headerList.size() )
            throw new RuntimeException("Duplicate header name exists");
        this.headerList = headerList;
        this.rowsList = new ArrayList<>();
        nextRowId = 0;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.indexes = new HashMap<>();
    }

    public int insertRowIntoTable(@NonNull List<Object> row) throws Exception {
        validateRow(row);
        nextRowId++;
        Row newRow = new Row(nextRowId, row);
        rowsList.add(newRow);
        return nextRowId;
    }

    public boolean deleteRowWithRowId (@NonNull final Integer rowId) {
        Optional<Row> rowTBD = rowsList.stream().filter(v -> v.getRowId() == rowId).findAny();
        if(!rowTBD.isPresent()) {
            return false;
        }
        rowsList.remove(rowTBD.get());
        return true;
    }

    private void validateRow(List<Object> row) throws Exception {
        if(row.size() != this.headerList.size()) {
            throw new Exception("Input column mismatch");
        }
        for(int i = 0; i < row.size(); i++) {
            Header header = this.headerList.get(i);
            Object current = row.get(i);
            if(current == null) {
                if(header.getIsMandatory())
                    throw new RuntimeException(header.getName() + " is a mandatory field");
            } else {
                switch (header.getConstraints()) {
                    case NONE:
                        break;
                    case STR_CHAR_20:
                        String s = String.valueOf(current);
                        if(s.length() > 20)
                            throw new RuntimeException("String Length greater then 20");
                        break;
                    case INT_RANGE_1024:
                        Integer val = (Integer) current;
                        if(val>1024 || val<-1024)
                            throw new RuntimeException("String Length greater then 20");
                        break;
                    default:
                        throw new RuntimeException("Data inconsistent");

                }
            }
        }
    }

    public void createIndex(String headerName) {
        if(indexes.containsKey(headerName))
            throw new RuntimeException("Index already exists");
        int index=-1;
        for (int i = 0; i < headerList.size(); i++) {
            if(headerList.get(i).getName() == headerName) {
                index = i;
                break;
            }
        }
        if(index != -1) {
            Header header = headerList.get(index);
            if(!header.getIsMandatory())
                throw new RuntimeException("Can't create index for non mandatory headers");
            HashMap<Object, Integer> rowIndex = new HashMap<>();
            for (int i = 0; i < rowsList.size(); i++) {
                rowIndex.put(rowsList.get(i).getRowItems().get(index), rowsList.get(i).getRowId());
            }
            indexes.put(header, rowIndex);
        } else {
            throw new RuntimeException(headerName + " header doesn't exist in " + this.tableName);
        }
    }

    @Override
    public String toString() {
        return "Table{" +
                "\n\ttableName='" + tableName + '\'' +
                "\n\theaderList=" + headerList +
                "\n\trowsList=" + rowsList +
                "\n\tcreatedAt=" + createdAt +
                "\n}";
    }
}
