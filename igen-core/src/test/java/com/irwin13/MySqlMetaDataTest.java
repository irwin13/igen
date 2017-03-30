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
 * Created by irwin on 30/03/17.
 */
public class MySqlMetaDataTest {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/latihan?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static final String TABLE = "pet";

    @Test
    public void shouldShowTableName() throws Exception {
        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        try {
            MetaDataUtil metaDataUtil = new MetaDataUtil(connection);
            metaDataUtil.setSchemaPattern("latihan");
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
            metaDataUtil.setSchemaPattern("latihan");

            List<ColumnMetaData> list = metaDataUtil.columnMetaData(TABLE);
            System.out.println(list);
            Assert.assertEquals(5, list.size());
        } finally {
            connection.close();
        }
    }

}
