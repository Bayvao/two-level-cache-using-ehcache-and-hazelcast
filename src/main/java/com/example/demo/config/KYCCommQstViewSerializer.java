package com.example.demo.config;

import com.example.demo.model.KYCCommQstView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.nio.serialization.ByteArraySerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class KYCCommQstViewSerializer implements ByteArraySerializer<KYCCommQstView> {

    private static final Logger LOG = LoggerFactory.getLogger(KYCCommQstViewSerializer.class);
    private final ObjectMapper objectMapper;

    public KYCCommQstViewSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public byte[] write(KYCCommQstView object) throws IOException {
        try {
            LOG.info("Serializing data to bytes");
            return objectMapper.writeValueAsBytes(object);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public KYCCommQstView read(byte[] bytes) throws IOException {
        LOG.info("DeSerializing data from bytes");

        try {
           return objectMapper.readValue(bytes, KYCCommQstView.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public int getTypeId() {
        return 2;
    }
}