package com.progettoingsw19.covid19.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TypeConverter implements AttributeConverter<Type, String>{

    @Override
    public String convertToDatabaseColumn(Type type) {
        if (type == null) {
            return null;
        }
        String ris= type.name();
        ris=ris.charAt(0)+ris.substring(1,ris.length()).toLowerCase();
        return ris;
    }

    @Override
    public Type convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        if (Type.values()[0].toString().equalsIgnoreCase(value))
            return Type.HOTEL;
        else  if (Type.values()[1].toString().equalsIgnoreCase(value))
            return Type.RESTOURANT;
        else
            return Type.ATTRACTION;
    }
}
