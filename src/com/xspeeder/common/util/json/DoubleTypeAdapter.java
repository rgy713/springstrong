
package com.xspeeder.common.util.json;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.text.ParseException;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

public final class DoubleTypeAdapter implements JsonSerializer<Double>, JsonDeserializer<Double> {
	 
    private final NumberFormat moneyFormat;
 
    public DoubleTypeAdapter() {
        moneyFormat = NumberFormat.getNumberInstance();
        moneyFormat.setMaximumFractionDigits(2);
        moneyFormat.setMinimumFractionDigits(0);
        moneyFormat.setGroupingUsed(false);
    }
 
    public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
    	String formatFormatAsString = moneyFormat.format(src);
        return new JsonPrimitive(formatFormatAsString);
    }
 
    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a string value");
        }
        Double dbl = deserializeToDouble(json);
        return dbl;
    }
 
    private Double deserializeToDouble(JsonElement json) {
        try {
            return moneyFormat.parse(json.getAsString()).doubleValue();
        } catch (ParseException e) {
            throw new JsonSyntaxException(json.getAsString(), e);
        }
    }
}