package com.example.demo.config;


import com.example.demo.model.Employee;
import com.example.demo.model.KYCCommQstView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.SerializerConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.util.Iterator;


@Configuration
@EnableCaching
public class CacheConfig {

    private final ObjectMapper objectMapper;

    @Autowired
    public CacheConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    @Primary
    public org.springframework.cache.CacheManager ehCacheManager() throws Exception {
        Iterable<CachingProvider> cachingProviders = Caching.getCachingProviders();
        Iterator<CachingProvider> iterator = cachingProviders.iterator();
        CachingProvider cachingProvider = null;
        while (iterator.hasNext()) {
            CachingProvider provider = iterator.next();

            if (provider instanceof EhcacheCachingProvider) {
                cachingProvider = provider;
                break;
            }
        }

        assert cachingProvider != null;
        CacheManager cacheManger = cachingProvider.getCacheManager(getClass().getResource("/ehcache.xml").toURI(),
                cachingProvider.getDefaultClassLoader());
        return new JCacheCacheManager(cacheManger);

    }


    @Bean
    public org.springframework.cache.CacheManager hazelcastCacheManager(@Qualifier("hazelcastInstance")
                                                                            HazelcastInstance hazelcastInstance) {
        return new HazelcastCacheManager(hazelcastInstance);
    }

//    @Bean
//    @Primary
//    public org.springframework.cache.CacheManager compositeCacheManager(JCacheCacheManager jCacheCacheManager,
//                                                               HazelcastCacheManager hazelcastCacheManager) {
//       CompositeCacheManager cacheManager = new CompositeCacheManager(jCacheCacheManager, hazelcastCacheManager);
//       cacheManager.setFallbackToNoOpCache(false);
//       return cacheManager;
//    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setInstanceName("hazelcast-client-instance");
        clientConfig.getSerializationConfig()
                .addSerializerConfig(
                        new SerializerConfig()
                                .setTypeClass(KYCCommQstView.class)
                                .setImplementation(new KYCCommQstViewSerializer(objectMapper)))
                .addSerializerConfig(
                        new SerializerConfig()
                                .setTypeClass(Employee.class)
                                .setImplementation(new EmployeeSerializer(objectMapper)));

        ClientNetworkConfig networkConfig = clientConfig.getNetworkConfig();
        networkConfig.addAddress("127.0.0.1:5701");
        networkConfig.addAddress("127.0.0.1:5702");
        networkConfig.addAddress("127.0.0.1:5703");
        clientConfig.setNetworkConfig(networkConfig);

        return HazelcastClient.newHazelcastClient(clientConfig);
    }

//    @Bean
//    public CacheInterceptor cacheInterceptor(org.springframework.cache.CacheManager ehCacheManager,
//                                             CacheOperationSource cacheOperationSource) {
//        CacheInterceptor interceptor = new MultiCacheInterceptor(ehCacheManager);
//        interceptor.setCacheOperationSources(cacheOperationSource);
//        return interceptor;
//    }
//
//    @Bean
//    public CacheOperationSource cacheOperationSource() {
//        return new AnnotationCacheOperationSource();
//    }

}