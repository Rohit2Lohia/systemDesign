package com.dbms.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class Table {
    private @Getter @Setter String tableName;
    private static @Getter Integer nextRowId;
    private @Getter @Setter List<Header> headerList;
    private List<Row> rowsList;
    private @Getter Timestamp createdAt;

    public Table(String tableName, List<Header> headerList) {
        this.tableName = tableName;
        this.headerList = headerList;
        this.rowsList = new ArrayList<>();
        nextRowId = 0;
        this.createdAt = new Timestamp(System.currentTimeMillis());
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
