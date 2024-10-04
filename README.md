# **Two-Level Cache using Ehcache and Hazelcast**

This project contains demo code for implementing a two level cache using Ehcache and Hazelcast.

Ehcache is an in-memory cache where cached data is lost when application is shutdown / restarted.
Hazelcast in a distributed cache where application state does not affect the cached data.

The project contains Custom configuration for Ehcache and Hazelcast cache including setting up custom serializer / de-serializer
