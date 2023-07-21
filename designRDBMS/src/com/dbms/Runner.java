package com.dbms;

import com.dbms.model.Header;
import com.dbms.model.Table;
import com.dbms.service.DatabaseMangementService;

import java.util.ArrayList;
import java.util.Arrays;

public class Runner {
    public static void main(String[] args) throws Exception {
        DatabaseMangementService dmservice = new DatabaseMangementService();

        ArrayList<Header> headers = new ArrayList<>(
                Arrays.asList(
                        new Header("Emp_ID", Header.DataType.INT, true, Header.Constraint.INT_RANGE_1024),
                        new Header("First_Name", Header.DataType.STRING, true, Header.Constraint.STR_CHAR_20),
                        new Header("Last_Name", Header.DataType.STRING, false, Header.Constraint.STR_CHAR_20),
                        new Header("Age", Header.DataType.INT, false, Header.Constraint.INT_RANGE_1024))
        );
        Table employeeDetails = dmservice.createTable("EmployeeDetails", headers);
        employeeDetails.insertRowIntoTable(Arrays.asList(1, null, "Lohia", 26));
        employeeDetails.insertRowIntoTable(Arrays.asList(2, "Mohit", "Lohia", 22));
        System.out.println("Table looks like: \n" + employeeDetails);
        employeeDetails.deleteRowWithRowId(1);
        System.out.println(employeeDetails.getRowsList());

    }
}
