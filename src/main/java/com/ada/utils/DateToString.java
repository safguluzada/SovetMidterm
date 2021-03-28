package com.ada.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateToString {

    public static String dateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static LocalDate stringToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        return LocalDate.parse(date,formatter);
    }

}
