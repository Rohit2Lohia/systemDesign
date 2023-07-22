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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Header header = (Header) o;

        if (name != null ? !name.equals(header.name) : header.name != null) return false;
        if (types != header.types) return false;
        if (isMandatory != null ? !isMandatory.equals(header.isMandatory) : header.isMandatory != null) return false;
        return constraints == header.constraints;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (types != null ? types.hashCode() : 0);
        result = 31 * result + (isMandatory != null ? isMandatory.hashCode() : 0);
        result = 31 * result + (constraints != null ? constraints.hashCode() : 0);
        return result;
    }
}
