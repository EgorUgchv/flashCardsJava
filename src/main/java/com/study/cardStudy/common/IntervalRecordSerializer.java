package com.study.cardStudy.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class IntervalRecordSerializer extends StdSerializer<IntervalRecord> {

    public IntervalRecordSerializer() {
        this(null);
    }
    public IntervalRecordSerializer(Class<IntervalRecord> t) {
        super(t);
    }
    @Override
    public void serialize(
            IntervalRecord value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException {
       jgen.writeNumber(value.interval());
    }
}
