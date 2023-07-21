package com.dbms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Header {
    public enum DataType{
            STRING("String"),
            INT("Integer");
        public final String label;
        private DataType(String label) {
            this.label = label;
        }
    }
    public enum Constraint {
        NONE, STR_CHAR_20, INT_RANGE_1024;
    }

    private String name;
    private DataType types;
    private Boolean isMandatory;
    private Constraint constraints;

    @Override
    public String toString() {
        return name;
    }
}
