package com.softcare.pockettrainer.utilerias;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString {
    //El formato de ser "dd-MM-yyyy hh:mm:ss"
    public static Date cadenaAFecha(String fecha, String pattern){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(fecha);

        }catch (ParseException e) {
            return null;
        }
    }
}
