package org.ticketria.repository;

public interface ICacheService {

    void evictCacheValues(String cacheName);
}