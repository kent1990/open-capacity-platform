package com.open.capacity.redis.config.util;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    private static GenericJackson2JsonRedisSerializer redisObjectSerializer = new GenericJackson2JsonRedisSerializer();
    @Resource
    private StringRedisTemplate stringRedisTemplate;
//    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }


//=============================common============================

    /**
     *
     * @param key
     * @return
     */
    public Object sGet_s(String key) {
        return key == null ? null : stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
    	return redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				long rawTimeout = TimeoutUtils.toMillis(time,  TimeUnit.SECONDS);
				try {
					return connection.pExpire(key.getBytes(), rawTimeout);
				} catch (Exception e) {
					// Driver may not support pExpire or we may be running on Redis 2.4
					return connection.expire(key.getBytes(), TimeoutUtils.toSeconds(rawTimeout,  TimeUnit.SECONDS));
				}
			}
		});
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
    	
    	
    	return redisTemplate.execute(new RedisCallback<Long>() {

			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				
				try {
					return connection.pTtl(key.getBytes(), TimeUnit.SECONDS);
				} catch (Exception e) {
					// Driver may not support pTtl or we may be running on Redis 2.4
					return connection.ttl(key.getBytes(), TimeUnit.SECONDS);
				}
			}
		});
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
    	 return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.exists(key.getBytes()));
    }

     
    //============================Object=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
    	
    	
    	Object value = redisTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				
				// redis info
				byte[] temp = null ;
				temp = connection.get( key .getBytes()) ;
				connection.close();

				return  redisObjectSerializer.deserialize(temp);
			}
		});
    	
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {

        	redisTemplate.execute((RedisCallback<Long>) connection -> {
            	// redis info
            	byte[] values = redisObjectSerializer.serialize(value);
				connection.set(key.getBytes(), values);
				connection.close();
				
                return 1L;
            });
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                
                redisTemplate.execute((RedisCallback<Long>) connection -> {
                	// redis info
                	byte[] values = redisObjectSerializer.serialize(value);
    				connection.set(key.getBytes(), values);
    				connection.expire(key.getBytes(), 60*time);
    				connection.close();
                    return 1L;
                });
                
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key 键
     * @param delta  要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            
            return connection.incrBy(key.getBytes(), delta) ;
        });
    }

    /**
     * 递减
     *
     * @param key 键
     * @param delta  要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            
            return connection.incrBy(key.getBytes(), -delta) ;
        });
        
    } //================================Map=================================

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    
}
