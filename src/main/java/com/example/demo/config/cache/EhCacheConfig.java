package com.example.demo.config.cache;

import org.ehcache.jsr107.EhcacheCachingProvider;
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
public class EhCacheConfig {


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

}