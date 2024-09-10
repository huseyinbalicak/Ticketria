package org.ticketria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ticketria.repository.ICacheService;
import org.ticketria.util.CacheNames;

@Profile("!prod")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cache")
public class CacheController {

    private final ICacheService cacheService;

    @DeleteMapping("/names/evict")
    public Boolean evictCountryNamesCache() {
        cacheService.evictCacheValues(CacheNames.TRIPS);
        return true;
    }
}