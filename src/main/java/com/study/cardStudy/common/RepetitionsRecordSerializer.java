package com.study.cardStudy.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class RepetitionsRecordSerializer extends StdSerializer<RepetitionsRecord> {

    public RepetitionsRecordSerializer(){
        this(null);
    }
   public RepetitionsRecordSerializer(Class<RepetitionsRecord> t){
        super(t);
   }

   @Override
    public void serialize(
            RepetitionsRecord value, JsonGenerator jgen, SerializerProvider provider)
           throws IOException, JsonProcessingException{
        jgen.writeNumber(value.repetitions());
   }
}
