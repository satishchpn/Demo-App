package com.zycus.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@ComponentScan("com.zycus")
public class RedisConfig {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<Long, Object> redisTemplate() {
		final RedisTemplate<Long, Object> template = new RedisTemplate<Long, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	  public CacheManager cacheManager(RedisTemplate redisTemplate) {
	    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

	    // Number of seconds before expiration. Defaults to unlimited (0)
	    cacheManager.setDefaultExpiration(300);
	    return cacheManager;
	  }
}
