package com.example.applicationandroid.helper;

import android.content.Context;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static int getDrawableResourceId(Context context, String resourceName) {
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }

    public static String parsePhoto(String image){
        if(image!=null && !image.isEmpty()){
            return image;
        }
        return Constante.DEFAULT_IMAGE;
    }

    public static String formatDate(Date date,String format,Locale locale) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, locale);
        return dateFormat.format(date);
    }


}
