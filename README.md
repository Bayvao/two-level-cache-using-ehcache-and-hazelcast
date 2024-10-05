# **Two-Level Cache using Ehcache and Hazelcast**

**Overview**

This project demonstrates the implementation of a two-level caching mechanism in a Spring Boot application using Ehcache as the first-level cache and Hazelcast as the second-level distributed cache. By integrating these two caching solutions, the project provides an efficient mechanism for both local and distributed caching.

**Features**

Ehcache: Local, in-memory caching for quick access to frequently used data.
Hazelcast: Distributed cache for scaling and sharing data across multiple instances in a cluster.
Spring Boot: Easily integrated caching solution using Spring's caching abstraction.

**Project Structure**

src/main/java: Contains the main application code.
    com.example.cache: Core package with cache configuration and logic.
src/test/java: Contains unit and integration tests for the caching mechanism.
pom.xml: Maven configuration file, managing dependencies like Spring Boot, Ehcache, and Hazelcast.

**Prerequisites**

To run this project, ensure you have the following installed:
  JDK 11+
  Maven 3.6+
  A working Hazelcast server instance

**Configuration**

**Ehcache Configuration**

Ehcache is used for the first-level cache and is configured in src/main/resources/ehcache.xml. You can modify the configuration for cache regions, time-to-live (TTL), and other caching parameters.

**Hazelcast Configuration**

Hazelcast is used as a distributed cache, ensuring cache synchronization across nodes. The Hazelcast configuration is found in src/main/resources/hazelcast.xml. You can modify network settings, cluster members, and distributed cache properties as needed.
