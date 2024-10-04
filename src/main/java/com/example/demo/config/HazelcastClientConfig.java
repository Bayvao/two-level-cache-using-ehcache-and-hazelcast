//package com.example.demo.config;
//
//import com.hazelcast.client.HazelcastClient;
//import com.hazelcast.client.config.ClientConfig;
//import com.hazelcast.client.config.ClientNetworkConfig;
//import com.hazelcast.core.HazelcastInstance;
//import com.hazelcast.spring.cache.HazelcastCacheManager;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class HazelcastClientConfig extends CachingConfigurerSupport {
//    @Override
//    public CacheManager cacheManager() {
//        return new HazelcastCacheManager(hazelcastInstance());
//    }
//
//    @Bean
//    public HazelcastInstance hazelcastInstance() {
//        ClientConfig clientConfig = new ClientConfig();
//
//        clientConfig.setInstanceName("hazelcast-client-instance");
//
//        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
//        networkConfig.addAddress("127.0.0.1:5701");
//        networkConfig.addAddress("127.0.0.1:5703");
//        networkConfig.addAddress("127.0.0.1:5705");
//
//        return HazelcastClient.newHazelcastClient(clientConfig);
//
//    }
//}
