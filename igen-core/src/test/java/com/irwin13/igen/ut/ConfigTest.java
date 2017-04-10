package com.irwin13.igen.ut;

import com.irwin13.igen.it.config.ConfigLoader;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by irwin on 10/04/17.
 */
public class ConfigTest {

    @Test
    public void shouldReadConfigSuccessfully() {
        ConfigLoader configLoader = mock(ConfigLoader.class);
        configLoader.loadConfig("config.yaml");
        verify(configLoader).loadConfig("config.yaml");
    }

    @Test
    public void shouldThrowRuntimeException() {
        ConfigLoader configLoader = mock(ConfigLoader.class);
        when(configLoader.loadConfig(anyString())).thenThrow(RuntimeException.class);
        verify(configLoader, times(0)).loadConfig(anyString());
    }
}
