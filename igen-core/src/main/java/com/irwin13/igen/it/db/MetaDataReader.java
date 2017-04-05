package com.irwin13.igen.it.db;

import com.irwin13.igen.it.vo.ColumnMetaData;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by irwin on 05/04/17.
 */
public interface MetaDataReader {

    List<String> showTables(String schemaPattern, String tablePattern) throws SQLException;
    List<ColumnMetaData> readColumnMetaData(String schema, String table) throws SQLException;


}
