package com.irwin13.igen.it;

import com.irwin13.igen.it.config.ConfigLoader;
import com.irwin13.igen.it.config.IgenConfig;
import com.irwin13.igen.it.config.YamlConfigLoader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Created by irwin on 24/03/17.
 */
public class ConfigTest {

    @Test(groups = "IntegrationTest")
    public void shouldReadConfigSuccessfully() {
        ConfigLoader configLoader = new YamlConfigLoader();
        IgenConfig config = configLoader.loadConfig("test-config.yaml");

        Assert.assertNotNull(config);
        Assert.assertEquals("igen-example", config.getAppName());
        Assert.assertEquals("1.0", config.getAppVersion());
        Assert.assertEquals("postgresql", config.getDbVendor());
        Assert.assertEquals("pg", config.getDbDriver());
        Assert.assertEquals("db", config.getDbUrl());
        Assert.assertEquals("pglocal", config.getDbUser());
        Assert.assertEquals("pglocal", config.getDbPass());
        Assert.assertEquals(2, config.getTableList().size());
        Assert.assertTrue(config.getTableList().containsAll(Arrays.asList("USER", "ORG")));
        Assert.assertEquals("template", config.getTemplateFolder());
        Assert.assertEquals("freemarker", config.getTemplateEngine());
    }

    @Test(expectedExceptions = RuntimeException.class, groups = "IntegrationTest")
    public void shouldThrowRuntimeException() {
        ConfigLoader configLoader = new YamlConfigLoader();
        IgenConfig config = configLoader.loadConfig("not-exists.yaml");
        Assert.assertNull(config);
    }
}
