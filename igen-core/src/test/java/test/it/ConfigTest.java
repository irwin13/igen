package test.it;

import com.irwin13.igen.config.ConfigLoader;
import com.irwin13.igen.config.IgenConfig;
import com.irwin13.igen.config.YamlConfigLoader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;

/**
 * Created by irwin on 24/03/17.
 */
@Category(IntegrationTest.class)
public class ConfigTest {

    // @Test(groups = "IntegrationTest")
    @Test
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

    // @Test(expectedExceptions = RuntimeException.class, groups = "IntegrationTest")
    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeException() {
        ConfigLoader configLoader = new YamlConfigLoader();
        IgenConfig config = configLoader.loadConfig("not-exists.yaml");
        Assert.assertNull(config);
    }
}
