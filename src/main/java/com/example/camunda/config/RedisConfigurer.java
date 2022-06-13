package com.example.camunda.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
@Slf4j
public class RedisConfigurer extends CachingConfigurerSupport {

    @Value("${spring.redis.database.cache}")
    private String databaseCache;
    @Value("${spring.redis.database.token}")
    private String databaseMr;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.lettuce.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int minIdle;

    @Autowired
    @Qualifier("factoryForCache")
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Bean
    public GenericObjectPoolConfig getRedisConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWaitMillis);
        poolConfig.setMinIdle(minIdle);
        return poolConfig;
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                // 设置 key为string序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置value为json序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(getSerializer()))
                // 不缓存空值
                .disableCachingNullValues();
        RedisCacheManager cacheManager = RedisCacheManager.builder(lettuceConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .transactionAware()
                .build();
        cacheManager.afterPropertiesSet();
        log.info("RedisCacheManager config success");
        return cacheManager;
    }

    @Bean(name = "springSessionDefaultRedisSerializer")
    public GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    /**
     * 缓存使用的redis
     *
     * @return
     */
    @Bean("factoryForCache")
    @Primary
    public LettuceConnectionFactory redisConnectionFactory() {
        return getRedisConnectionFactory(Integer.valueOf(databaseCache));
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        return getRedisTemplate(lettuceConnectionFactory);
    }

    private Jackson2JsonRedisSerializer getSerializer() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        //  设置自动key的生成规则，配置spring boot的注解，进行方法级别的缓存
        // 使用：进行分割，可以很多显示出层级关系
        // 这里其实就是new了一个KeyGenerator对象
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(":" + String.valueOf(obj));
            }
            String rsToUse = String.valueOf(sb);
            log.info("自动生成Redis Key -> [{}]", rsToUse);
            return rsToUse;
        };
    }

    /**
     * Token使用的redis
     *
     * @return
     */
    @Bean("factoryForToken")
    public LettuceConnectionFactory redisConnectionFactoryForToken() {
        return getRedisConnectionFactory(Integer.valueOf(databaseMr));
    }

    @Bean(name = "redisTemplateForToken")
    public RedisTemplate<String, Object> redisTemplateForToken(@Qualifier("factoryForToken") LettuceConnectionFactory factory) {
        return getRedisTemplate(factory);
    }

    /**
     * 表字段和注释使用的redis
     *
     * @return
     */
    @Bean("factoryForTable")
    public LettuceConnectionFactory redisConnectionFactoryForTable() {
        return getRedisConnectionFactory(Integer.valueOf(databaseMr));
    }

    @Bean(name = "redisTemplateForTable")
    public RedisTemplate<String, Object> redisTemplateForTable(@Qualifier("factoryForTable") LettuceConnectionFactory factory) {
        return getRedisTemplate(factory);
    }


    private LettuceConnectionFactory getRedisConnectionFactory(Integer database) {
        RedisStandaloneConfiguration connection = new RedisStandaloneConfiguration();
        connection.setHostName(host);
        connection.setPort(port);
        connection.setPassword(password);
        connection.setDatabase(database);
        GenericObjectPoolConfig poolConfig = getRedisConfig();
        LettuceClientConfiguration builder = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(timeout))
                .poolConfig(poolConfig)
                .shutdownTimeout(Duration.ZERO)
                .build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(connection, builder);
        return factory;
    }

    private RedisTemplate<String, Object> getRedisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // value值的序列化
        redisTemplate.setValueSerializer(getSerializer());
        redisTemplate.setHashValueSerializer(getSerializer());
        // key的序列化采用StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
 