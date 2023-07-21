package com.dbms.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Getter
public class Row {
    private Integer rowId;
    private @Setter List<Object> rowItems;
    private @Getter Timestamp createdAt;
    private @Getter @Setter Timestamp updatedAt;

    public Row(Integer rowId, List<Object> rowItems) {
        this.rowId = rowId;
        this.rowItems = rowItems;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "\nRow{" +
                "rowID=" + rowId +
                ", rowItems=" + rowItems +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                "}";
    }
}
