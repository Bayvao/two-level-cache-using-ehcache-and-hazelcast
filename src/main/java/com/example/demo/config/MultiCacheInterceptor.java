//package com.example.demo.config;
//
//import com.hazelcast.spring.cache.HazelcastCache;
//import com.hazelcast.spring.cache.HazelcastCacheManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.interceptor.CacheInterceptor;
//import org.springframework.stereotype.Component;
//
//
//public class MultiCacheInterceptor extends CacheInterceptor {
//
//    private static final Logger LOG = LoggerFactory.getLogger(MultiCacheInterceptor.class);
//
//    private final CacheManager ehCacheManager;
//
//    public MultiCacheInterceptor(CacheManager ehCacheManager) {
//        this.ehCacheManager = ehCacheManager;
//    }
//
//    @Override
//    protected Cache.ValueWrapper doGet(Cache cache, Object key) {
//        //Get item from cache
//        var superGetResult = super.doGet(cache, key);
//
//        if (superGetResult == null) {
//            return superGetResult;
//        }
//
//        //If retrieved from Redis, check if it's missing from caffeine on local and add it
//        if (cache.getClass() == HazelcastCache.class) {
//            var ehCache = ehCacheManager.getCache(cache.getName());
//
//            if (ehCache != null) {
//                LOG.info("Adding data in ehCache");
//                ehCache.putIfAbsent(key, superGetResult.get());
//            }
//        }
//
//        return superGetResult;
//    }
//}