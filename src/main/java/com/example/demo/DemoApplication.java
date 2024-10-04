package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.util.Iterator;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		//list all the caching provider
		Iterator<CachingProvider> iterator = Caching.getCachingProviders(Caching.getDefaultClassLoader()).iterator();
		while(iterator.hasNext()) {
			CachingProvider provider = iterator.next();
			System.out.println(provider.getCacheManager());
		}
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	CacheManager cacheManager;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(cacheManager.toString());
	}
}
