package de.c24.finacc.klt.config;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RefreshScope
@EnableCaching
public class EhCacheConfig {

    @Value("${feign.cache.max.entries}")
    private long maxEntries;

    @Value("${feign.cache.ttl}")
    private long timeToLive;

    @Value("${feign.cache.tti}")
    private long timeToIdle;

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setName("ExchangeRatesApiCache");
        cacheConfiguration.setMemoryStoreEvictionPolicy(
                MemoryStoreEvictionPolicy.MemoryStoreEvictionPolicyEnum.LRU.name());
        cacheConfiguration.setMaxEntriesLocalHeap(maxEntries);
        cacheConfiguration.setTimeToLiveSeconds(timeToLive);
        cacheConfiguration.setTimeToIdleSeconds(timeToIdle);
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(cacheConfiguration);

        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

}