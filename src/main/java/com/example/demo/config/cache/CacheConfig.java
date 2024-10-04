package com.example.demo.config.cache;

import com.example.demo.config.interceptor.MultiCacheInterceptor;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.AnnotationCacheOperationSource;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.cache.interceptor.CacheOperationSource;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {


    @Bean
    public org.springframework.cache.CacheManager compositeCacheManager(
            @Qualifier("ehCacheManager") JCacheCacheManager ehCacheManager,
            CacheManager hazelcastCacheManager) {
       CompositeCacheManager cacheManager = new CompositeCacheManager(ehCacheManager, hazelcastCacheManager);
       cacheManager.setFallbackToNoOpCache(false);
       return cacheManager;
    }


    @Bean
    public CacheInterceptor cacheInterceptor(org.springframework.cache.CacheManager ehCacheManager,
                                             CacheOperationSource cacheOperationSource) {
        CacheInterceptor interceptor = new MultiCacheInterceptor(ehCacheManager);
        interceptor.setCacheOperationSources(cacheOperationSource);
        return interceptor;
    }

    @Bean
    public CacheOperationSource cacheOperationSource() {
        return new AnnotationCacheOperationSource();
    }

}
