package excelsupport;


import org.apache.commons.lang3.ArrayUtils;
import org.testng.Assert;

import java.util.Arrays;


public class ExcelRow {

    private Object[] rowData;
    String rowHeader[];

    public ExcelRow(Object[] rowData, String[] rowHeader) {
        this.rowData = rowData;
        this.rowHeader = rowHeader;
    }

    public String get_column_string_value_by_header(String header) {
        int position = Arrays.asList(rowHeader).indexOf(header);
        validate_column_position(position);
        return (String) rowData[position];
    }

    public int get_column_int_value_by_header(String header) {
        int position = Arrays.asList(rowHeader).indexOf(header);
        validate_column_position(position);
        return ((Double) rowData[position]).intValue();
    }

    public boolean get_column_boolean_value_by_header(String header) {
        int position = Arrays.asList(rowHeader).indexOf(header);
        validate_column_position(position);
        return (Boolean) rowData[position];
    }

    public int get_column_int_value(int position) {
        validate_column_position(position);
        return ((Double) rowData[--position]).intValue();
    }

    public int[] get_column_int_array_value(int position) {
        validate_column_position(position);
        Object value = rowData[position - 1];

        if (value instanceof Double) {
            int[] valuesArray = new int[1];
            valuesArray[0] = get_column_int_value(position);
            return valuesArray;
        } else if (value instanceof String) {
            String stringValue = get_column_string_value(position);
            return Arrays.asList(stringValue.split(","))
                         .stream()
                         .map(String::trim)
                         .mapToInt(Integer::parseInt).toArray();
        }

        return ArrayUtils.EMPTY_INT_ARRAY;
    }


    public String get_column_string_value(int position) {
        validate_column_position(position);
        return (String) rowData[--position];
    }

    public String get_column_raw_value_as_string(int position) {
        validate_column_position(position);
        return "" + rowData[--position];
    }

    public boolean get_column_boolean_value(int position) {
        validate_column_position(position);
        return (boolean) rowData[--position];
    }

    public int get_total_no_of_columns() {
        return rowData.length;
    }

    public boolean does_column_contain_value(int position) {
        if (position > rowData.length || rowData[--position] == null)
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

    private void validate_column_position(int position) {
        if (position > rowData.length) {
            Assert.fail( "column referred does not exist in the excel sheet, column position is: " + position);
        }
    }

}

