package com.ttc.app.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ObjectConverter implements AttributeConverter<Object, String> {

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        return String.valueOf(attribute);
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
