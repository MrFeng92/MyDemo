package com.easymall.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropUtils
{
    private static Properties prop = null;

    public PropUtils()
    {
    }

    static
    {
        try
        {
            prop = new Properties();
            prop.load(new FileInputStream(PropUtils.class.getClassLoader()
                    .getResource("config.properties").getPath()));
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Properties getProp()
    {
        return prop;
    }

    public static String getProp(String key)
    {
        return prop.getProperty(key);
    }

}
