package com.irwin13.igen.it.db;

import com.irwin13.igen.it.vo.ColumnMetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by irwin on 29/03/17.
 */
public class JdbcMetaDataReader implements MetaDataReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcMetaDataReader.class);

    private final Connection connection;

    public JdbcMetaDataReader(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<String> showTables(String schemaPattern, String tablePattern) throws SQLException {

        List<String> tables = new LinkedList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet rs = metaData.getTables(null, schemaPattern, tablePattern, null);
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME");
            tables.add(tableName);
        }
        LOGGER.debug("tables = {}", tables);
        return tables;
    }

    @Override
    public List<ColumnMetaData> readColumnMetaData(String schemaPattern, String table) throws SQLException {
        List<ColumnMetaData> list = new LinkedList<>();

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getColumns(null, schemaPattern, table, null);

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

        LOGGER.debug("columnMetaData list = {}", list);
        return list;
    }
}
