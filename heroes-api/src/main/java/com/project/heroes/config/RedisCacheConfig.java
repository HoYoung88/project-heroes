package com.project.heroes.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class RedisCacheConfig {

    @Bean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer(ObjectMapper mapper) {
        mapper = mapper.copy();
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);
        serializer.configure((objectMapper) -> {
            objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        });
        return serializer;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory,
                                          GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer) {

        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer));

        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(cacheConfig)
            .build();
    }

//
//    @Bean
//    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(
//        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer) {
//        return builder -> builder.cacheDefaults(
//            RedisCacheConfiguration.defaultCacheConfig()
//                .serializeKeysWith(
//                    RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
//                )
//                .serializeValuesWith(
//                    RedisSerializationContext.SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer)
//                ));
//    }
/*

//                .withCacheConfiguration("heroes.character",
//                RedisCacheConfiguration.defaultCacheConfig()
//                    .computePrefixWith(cacheName -> "prefix::" + cacheName + "::")
//                    .entryTtl(Duration.ofSeconds(120))
//                    .disableCachingNullValues()
 */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
//
//        RedisCacheConfiguration heroesCharacterConfig = RedisCacheConfiguration.defaultCacheConfig()
//            .entryTtl(Duration.ofMinutes(1))
//            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper)));
//
//        cacheConfigurations.put("heroes.character", heroesCharacterConfig);
//
//        return RedisCacheManager.builder(redisConnectionFactory)
//                .withInitialCacheConfigurations(cacheConfigurations)
//                .build();
//    }


}
