package com.diplom.utils;

import com.diplom.enums.Eating;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class EnumConverter implements Converter<String, Eating> {

    @Override
    public Eating convert(String source) {
        try {
            return Eating.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    //@Override
    //public void setAsText(final String text){
    //    setValue(Eating.valueOf(text.toUpperCase()));
    //}
}
