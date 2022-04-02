package lab8.Data;

import java.io.Serializable;

public enum Country implements Serializable {
    GERMANY("GERMANY"),
    FRANCE("FRANCE"),
    SPAIN("SPAIN"),
    CHINA("CHINA"),
    ITALY("ITALY");

    private final String string;

    Country(String string){
        this.string = string;
    }

    public String getString(){
        return string;
    }
}