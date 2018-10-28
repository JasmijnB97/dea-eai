package nl.han.dea.jasmijn.datasource;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {
    private final Logger LOGGER = Logger.getLogger(getClass().getName());
    private Properties properties;

    public DatabaseProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Can't access property file database.properties", e);
        }
    }

    public String getDriver()
    {
        return properties.getProperty("driver");
    }

    public String connectionString()
    {
        return properties.getProperty("connectionString");
    }



}