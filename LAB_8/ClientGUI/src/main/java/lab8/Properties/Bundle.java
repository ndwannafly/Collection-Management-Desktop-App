package lab8.Properties;

import java.util.Locale;
import java.util.ResourceBundle;

public class Bundle {
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("resources",
            new Locale("en", "UK"));

    public static void setResourceBundle(String language){
        if(language.equals("English")){
            resourceBundle = ResourceBundle.getBundle("resources",
                    new Locale("en", "CA"));

        }

        if(language.equals("Slovak")){
            resourceBundle = ResourceBundle.getBundle("resources",
                    new Locale("sk", "SK"));

        }

        if(language.equals("Albanian")){
            resourceBundle = ResourceBundle.getBundle("resources",
                    new Locale("sq", "AL"));

        }

        if(language.equals("Russian")){
            resourceBundle = ResourceBundle.getBundle("resources",
                    new Locale("ru", "RU"));
        }
    }

    public static ResourceBundle getResourceBundle(){
        return resourceBundle;
    }
}
