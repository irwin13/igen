package com.irwin13.igen.vo;

/**
 * Created by irwin on 29/03/17.
 */
public class ColumnMetaData {

    private String columnName;
    private String columnType;
    private boolean nullable;
    private int columnSize;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    @Override
    public String toString() {
        return "ColumnMetaData{" +
                "columnName='" + columnName + '\'' +
                ", columnType='" + columnType + '\'' +
                ", nullable=" + nullable +
                ", columnSize=" + columnSize +
                '}';
    }
}
