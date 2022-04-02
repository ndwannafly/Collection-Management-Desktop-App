package lab8.Bundles;

import java.util.Locale;
import java.util.ResourceBundle;

public class Bundle {
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("lab8.Bundles.gui",
            new Locale("en", "CA"));

    public static void setResourceBundle(String language){
        if(language.equals("English")){
            resourceBundle = ResourceBundle.getBundle("lab8.Bundles.gui",
                    new Locale("en", "CA"));

        }

        if(language.equals("Slovak")){
            resourceBundle = ResourceBundle.getBundle("lab8.Bundles.gui",
                    new Locale("sk", "SK"));

        }

        if(language.equals("Albanian")){
            resourceBundle = ResourceBundle.getBundle("lab8.Bundles.gui",
                    new Locale("sq", "AL"));

        }

        if(language.equals("Russian")){
            resourceBundle = ResourceBundle.getBundle("lab8.Bundles.gui",
                    new Locale("ru", "RU"));
        }
    }

    public static ResourceBundle getResourceBundle(){
        return resourceBundle;
    }
}