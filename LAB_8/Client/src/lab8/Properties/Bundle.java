package lab8.Properties;

import java.util.Locale;
import java.util.ResourceBundle;

public class Bundle {
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("lab8/Properties/resources",
            new Locale("ru", "RU"));

    public static void setResourceBundle(String language){
        if(language.equals("English")){
            resourceBundle = ResourceBundle.getBundle("lab8/Properties/resources",
                    new Locale("en", "UK"));

        }

        if(language.equals("Polish")){
            resourceBundle = ResourceBundle.getBundle("lab8/Properties/resources",
                    new Locale("pl", "POL"));

        }

        if(language.equals("Norwegian")){
            resourceBundle = ResourceBundle.getBundle("lab8/Properties/resources",
                    new Locale("no", "NOR"));

        }

        if(language.equals("Russian")){
            resourceBundle = ResourceBundle.getBundle("lab8/Properties/resources",
                    new Locale("ru", "RU"));
        }
    }

    public static ResourceBundle getResourceBundle(){
        return resourceBundle;
    }
}
