package org.mysj;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializer.json;

@Configuration
class RedisConfig {

    @Bean
    CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        var builder = RedisCacheManager.builder(redisConnectionFactory);
        builder.withCacheConfiguration("patient",
                RedisCacheConfiguration.defaultCacheConfig()
                        .prefixCacheNameWith("p")
                        .entryTtl(Duration.ofSeconds(1000))
                        .computePrefixWith(CacheKeyPrefix.simple())
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(json()))
                        .disableCachingNullValues());
        return builder.build();
    }
}
