package com.example.demo.config.serializer;

import com.example.demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.nio.serialization.ByteArraySerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EmployeeSerializer implements ByteArraySerializer<Employee> {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeSerializer.class);
    private final ObjectMapper objectMapper;

    public EmployeeSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public byte[] write(Employee object) throws IOException {
        try {
            LOG.info("Serializing data to bytes");
            return objectMapper.writeValueAsBytes(object);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Employee read(byte[] bytes) throws IOException {
        LOG.info("DeSerializing data from bytes");

        try {
           return objectMapper.readValue(bytes, Employee.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public int getTypeId() {
        return 10;
    }
}