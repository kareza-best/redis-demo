package cn.kareza.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class RedisDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void redisConnect() {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        System.out.println("连接www.kareza.cn的redis成功");
        // 查看服务是否运行
        System.out.println("运行状态，PING指令返回 " + jedis.ping());
    }

    @Test
    void redisShutdown() {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        System.out.println("连接www.kareza.cn的redis成功");
        // 查看服务是否运行
        System.out.println("关闭redis服务");
        jedis.shutdown();
    }

}
