package test.ut;

import com.irwin13.igen.config.ConfigLoader;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by irwin on 10/04/17.
 */
public class ConfigTest {

    @Test
    public void shouldReadConfigSuccessfully() {
        ConfigLoader configLoader = mock(ConfigLoader.class);
        configLoader.loadConfig(anyString());
        verify(configLoader).loadConfig(anyString());
    }

    @Test
    public void shouldThrowRuntimeException() {
        ConfigLoader configLoader = mock(ConfigLoader.class);
        when(configLoader.loadConfig(anyString())).thenThrow(RuntimeException.class);
        verify(configLoader, times(0)).loadConfig(anyString());
    }
}
