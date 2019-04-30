package xin.stxkfzx.weekend.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * redis 工具，存取
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/30
 */
@Configuration
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 存数据
     *
     * @param key   key
     * @param value value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存数据
     *
     * @param key   键
     * @param value 值
     * @param exp   过期时间 默认秒
     * @param s     使用 时还是分还是秒，默认秒
     */
    public void set(String key, Object value, int exp, TimeUnit s) {
        if (s == null) {
            s = TimeUnit.SECONDS;
        }
        redisTemplate.opsForValue().set(key, value, exp, s);
    }

    /**
     * 获取数据
     *
     * @param key key
     * @return key
     */
    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

}