package com.example.demo.config.cache;

import com.example.demo.config.serializer.EmployeeSerializer;
import com.example.demo.config.serializer.ObjectSerializer;
import com.example.demo.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastCacheConfig {

    private final ObjectMapper objectMapper;

    @Autowired
    public HazelcastCacheConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public CacheManager hazelcastCacheManager(@Qualifier("hazelcastInstance")
                                                                        HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }


    @Bean
    public HazelcastInstance hazelcastInstance() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setInstanceName("hazelcast-client-instance");
        clientConfig.getSerializationConfig()
                .addSerializerConfig(
                        new SerializerConfig()
                                .setTypeClass(Employee.class)
                                .setImplementation(new EmployeeSerializer(objectMapper)))
                .addSerializerConfig(
                        new SerializerConfig()
                                .setTypeClass(Object.class)
                                .setImplementation(new ObjectSerializer(objectMapper)));

        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
        networkConfig.addAddress("127.0.0.1:5701");
        networkConfig.addAddress("127.0.0.1:5702");
        networkConfig.addAddress("127.0.0.1:5703");
        clientConfig.setNetworkConfig(networkConfig);

        return HazelcastClient.newHazelcastClient(clientConfig);
    }

}
