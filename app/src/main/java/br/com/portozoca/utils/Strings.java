package br.com.portozoca.utils;

public class Strings {

    private static final String FORMAT_HEX = "#%s%s%s" ;

    public static final String toHex(Integer r, Integer g, Integer b){
        return String.format(FORMAT_HEX,
                Integer.toHexString(r), Integer.toHexString(g), Integer.toHexString(b)).
                toUpperCase();
    }

}
