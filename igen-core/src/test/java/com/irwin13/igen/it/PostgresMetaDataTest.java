package com.irwin13.igen.it;

import com.irwin13.igen.IntegrationTest;
import com.irwin13.igen.it.db.JdbcMetaDataReader;
import com.irwin13.igen.it.db.MetaDataReader;
import com.irwin13.igen.it.vo.ColumnMetaData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;

/**
 * Created by irwin on 29/03/17.
 */
@Category(IntegrationTest.class)
public class PostgresMetaDataTest {

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
            MetaDataReader jdbcMetaDataReader = new JdbcMetaDataReader(connection);
            List<String> tables = jdbcMetaDataReader.showTables("pglocal", "%");
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
            MetaDataReader jdbcMetaDataReader = new JdbcMetaDataReader(connection);
            List<ColumnMetaData> list = jdbcMetaDataReader.readColumnMetaData("pglocal", TABLE);
            Assert.assertEquals(5, list.size());
        } finally {
            connection.close();
        }
    }

}
