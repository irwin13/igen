package com.irwin13.igen.it;

import com.irwin13.igen.IntegrationTest;
import com.irwin13.igen.it.db.JdbcMetaDataReader;
import com.irwin13.igen.it.db.MetaDataReader;
import com.irwin13.igen.it.vo.ColumnMetaData;
import org.h2.tools.Server;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;

/**
 * Created by irwin on 07/04/17.
 */
@Category(IntegrationTest.class)
public class H2MetaDataTest {

    private Server server;

    private static final String DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "";
    private static final String PASS = "";

    private static final String TABLE = "PET";

    @Before
    public void before() throws Exception {
        server = Server.createTcpServer().start();
        Class.forName(DRIVER);
        createTable();
    }

    @Test
    public void shouldShowTableName() throws Exception {
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        try {
            MetaDataReader jdbcMetaDataReader = new JdbcMetaDataReader(connection);
            List<String> tables = jdbcMetaDataReader.showTables(null, TABLE);
            Assert.assertEquals(1, tables.size());
            Assert.assertTrue(tables.containsAll(Arrays.asList(TABLE)));
        } finally {
            connection.close();
        }
    }

    // @Test(groups = "IntegrationTest")
    @Test
    public void shouldShowColumnMetaData() throws Exception {
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        try {
            MetaDataReader jdbcMetaDataReader = new JdbcMetaDataReader(connection);
            List<ColumnMetaData> list = jdbcMetaDataReader.readColumnMetaData(null, TABLE);
            Assert.assertEquals(5, list.size());
        } finally {
            connection.close();
        }
    }

    @After
    public void after() throws Exception {
        dropTable();
        server.stop();
    }

    private void createTable() throws Exception {
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        try {
            connection.setAutoCommit(false);

            String createSql = "CREATE TABLE PET (id int primary key, " +
                    "name varchar(255), " +
                    "age int, " +
                    "birth_date date, " +
                    "create_date timestamp)";

            PreparedStatement statement = connection.prepareStatement(createSql);
            statement.execute();
            statement.close();
            connection.commit();
        } finally {
            connection.close();
        }
    }

    private void dropTable() throws Exception {
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        try {
            connection.setAutoCommit(false);

            String dropSql = "DROP TABLE PET CASCADE";

            PreparedStatement statement = connection.prepareStatement(dropSql);
            statement.execute();
            statement.close();
            connection.commit();
        } finally {
            connection.close();
        }
    }

}
