package lab8.Client;

import lab8.Data.Country;
import lab8.Data.Person;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Reader {
    public static boolean doesThisCountryExist(String nationality){
        return Arrays.stream(Country.values()).anyMatch(c1 -> c1.name().equals(nationality));
    }

    public static Country readNationality(String args){
        String nationality = args.trim();
        if(!doesThisCountryExist(nationality) || nationality.equals("")){
            while(!doesThisCountryExist(nationality)) return null;
        }
        return Enum.valueOf(Country.class, nationality);
    }
    
    public static String readName(String args){
        String name = args.trim();
        if(name.equals("")) return null;
        return name;
    }

    public static Double readX(String args) {
        String sx = args.trim();
        double resX;
        while(true){
            try{
                resX = Double.parseDouble(sx);
                if(resX >= -801) return resX;
                else return null;
            } catch (NumberFormatException e){
                return null;
            }
        }
    }

    public static Double readY(String args) {
        String sy = args.trim();
        double resY;
        while(true){
            try{
                resY = Double.parseDouble(sy);
                if(resY <= 687) return resY;
                else return null;
            } catch (NumberFormatException e){
                return null;
            }
        }
    }

    public static Integer readHeight(String args) {
        String sHeight = args.trim();
        int resHeight;
        while(true){
            try{
                resHeight = Integer.parseInt(sHeight);
                if(resHeight >= 0) return resHeight;
                else return null;
            } catch(NumberFormatException e){
                return null;
            }
        }
    }

    public static String readBirthday(String args) {
        String resBirthday = args.trim();
        if(resBirthday.equals("")) return null;
        return resBirthday;
    }

    public static Integer readWeight(String args) {
        String sWeight = args.trim();
        int resWeight;
        while(true){
            try{
                resWeight = Integer.parseInt(sWeight);
                if(resWeight >= 0) return resWeight;
                else return null;
            } catch(NumberFormatException e){
                return null;
            }
        }
    }

    public static Integer readXLocation(String args) {
        String sXLocation = args.trim();
        int resXLocation;
        while(true){
            try{
                resXLocation = Integer.parseInt(sXLocation);
                return resXLocation;
            } catch(NumberFormatException e){
                return null;
            }
        }
    }

    public static Long readYLocation(String args) {
        String sYLocation = args.trim();
        long resYLocation;
        while(true){
            try{
                resYLocation = Long.parseLong(sYLocation);
                return resYLocation;
            } catch(NumberFormatException e){
                return null;
            }
        }
    }

    public static String readNameLocation(String args) {
        String resNameLocation = args.trim();
        if(resNameLocation.equals("")) return null;
        return resNameLocation;
    }

}
