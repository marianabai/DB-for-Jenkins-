package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//build a logic that reads the config file(properties file)
public class ConfigReader {

    private static Properties properties;

    //static initializer essentially helps initializes values in the objects (not a method)
    //it makes the code inside the scopes NOT reusable and runs it once for the whole project
    //instance initializer runs for every configurator object for the whole project
    static  {
        //filePath (location of your properties file) the directory of your properties file
        String filePath = "src/test/resources/properties/digitalbank.properties";

        //FileInputStream is a class that enables you to read files
        //it throws a checked exception (FileNotFoundException)
        //we use 1 catch because FileNotFoundException is the child exception of IOException
        FileInputStream input = null;
        try {
            input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);
            //input.close();   -> we put it in the finally
        } catch (IOException e) {
            System.out.println("File not found");
        }
        finally {
            try { //.close() is a clean up
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        System.out.println(properties.get("my_name"));
//        System.out.println(properties.get("browser"));
//        System.out.println(properties.get("environment"));
    }

    public static String getPropertiesValue(String key) {
        return properties.getProperty(key);
    }

}
