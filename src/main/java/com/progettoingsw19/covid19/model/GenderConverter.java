package com.progettoingsw19.covid19.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, String> {


    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        String ris= gender.name();
        ris=ris.charAt(0)+ris.substring(1,ris.length()).toLowerCase();
        return ris;
    }

    @Override
    public Gender convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        if (Gender.values()[0].toString().equalsIgnoreCase(value))
            return Gender.MALE;
        else
            return Gender.FEMALE;
    }
}


