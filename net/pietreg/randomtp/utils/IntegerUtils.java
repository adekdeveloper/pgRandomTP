package net.pietreg.randomtp.utils;

public final class IntegerUtils {

    private IntegerUtils(){
    }

    public static boolean isInt(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
