package com.example.demo.config.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.nio.serialization.ByteArraySerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ObjectSerializer implements ByteArraySerializer<Object> {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectSerializer.class);
    private final ObjectMapper objectMapper;

    public ObjectSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public byte[] write(Object object) throws IOException {
        try {
            LOG.info("Serializing data to bytes");
            return objectMapper.writeValueAsBytes(object);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Object read(byte[] bytes) throws IOException {
        LOG.info("DeSerializing data from bytes");

        try {
           return objectMapper.readValue(bytes, Object.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public int getTypeId() {
        return 2;
    }
}