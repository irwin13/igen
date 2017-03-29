package com.irwin13.jdbc;

import com.irwin13.dto.ColumnMetaData;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by irwin on 29/03/17.
 */
public class MetaDataUtil {

    private final Connection connection;

    public MetaDataUtil(Connection connection) {
        this.connection = connection;
    }

    private String catalog;
    private String schemaPattern;
    private String tablePattern;
    private String[] types;

    public List<String> showTables() throws SQLException {
        List<String> tables = new LinkedList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getTables(catalog, schemaPattern, tablePattern, types);
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            tables.add(tableName);
        }
        return tables;
    }

    public List<ColumnMetaData> columnMetaData(String table) throws SQLException {
        List<ColumnMetaData> list = new LinkedList<>();

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getColumns(catalog, schemaPattern, table, null);

        while (resultSet.next()) {
            ColumnMetaData columnMetaData = new ColumnMetaData();

            String columnName = resultSet.getString("COLUMN_NAME");
            columnMetaData.setColumnName(columnName);

            String columnType = resultSet.getString("TYPE_NAME");
            columnMetaData.setColumnType(columnType);

            int size = resultSet.getInt("COLUMN_SIZE");
            columnMetaData.setColumnSize(size);

            int nullable = resultSet.getInt("NULLABLE");
            if (nullable == DatabaseMetaData.columnNullable) {
                columnMetaData.setNullable(true);
            }

            list.add(columnMetaData);
        }

        return list;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public void setSchemaPattern(String schemaPattern) {
        this.schemaPattern = schemaPattern;
    }

    public void setTablePattern(String tablePattern) {
        this.tablePattern = tablePattern;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
