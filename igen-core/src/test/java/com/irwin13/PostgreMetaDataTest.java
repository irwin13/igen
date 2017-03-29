package com.irwin13;

import com.irwin13.dto.ColumnMetaData;
import com.irwin13.jdbc.MetaDataUtil;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;

/**
 * Created by irwin on 29/03/17.
 */
public class PostgreMetaDataTest {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "pglocal";
    private static final String PASS = "pglocal";

    private static final String TABLE = "igen_tab1";

    @Test
    public void shouldShowTableName() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        try {
            MetaDataUtil metaDataUtil = new MetaDataUtil(connection);
            metaDataUtil.setSchemaPattern("pglocal");
            metaDataUtil.setTablePattern("%"); // all tables

            List<String> tables = metaDataUtil.showTables();
            Assert.assertEquals(1, tables.size());
            Assert.assertTrue(tables.containsAll(Arrays.asList(TABLE)));
        } finally {
            connection.close();
        }
    }

    @Test
    public void shouldShowColumnMetaData() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        try {
            MetaDataUtil metaDataUtil = new MetaDataUtil(connection);
            metaDataUtil.setSchemaPattern("pglocal");

            List<ColumnMetaData> list = metaDataUtil.columnMetaData(TABLE);
            System.out.println(list);
            Assert.assertEquals(5, list.size());
        } finally {
            connection.close();
        }
    }

}
