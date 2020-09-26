package cn.kareza.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

@SpringBootTest
class RedisDemoApplicationTests {

    @Test
    void redisConnect() {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        System.out.println("连接www.kareza.cn的redis成功");
        // 查看服务是否运行
        System.out.println("运行状态，PING指令返回 " + jedis.ping());
    }

    // 添加字符串
    void addString(String key, String value) {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        // 设置redis字符串数据
        jedis.set(key, value);
        // 获取存储的数据并输出
        jedis.get(key);
    }

    // 删除字符串
    void delString(String key) {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        // 根据key删除字符串
        System.out.println("删除返回结果 " + jedis.del(key));
    }

    // 批量删除
    void delBatchString() {
        delString("创时期");
    }

    // 批量添加
    void addBatchString() {
        addString("杨超越创造101之旅（上）", "https://www.douban.com/note/697029175/");
        addString("杨超越创造101之旅（中）", "https://www.douban.com/note/719198219/");
        addString("杨超越创造101之旅（下）", "https://www.douban.com/note/697039027/");
    }

    // 添加列表
    @Test
    void addList() {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        // 存储数据到列表中
        jedis.lpush("创时期", "杨超越创造101之旅（上）");
        jedis.lpush("创时期", "杨超越创造101之旅（中）");
        jedis.lpush("创时期", "杨超越创造101之旅（下）");
        // 获取列表数据并输出
        List<String> list = jedis.lrange("创时期", 0, 5);
        System.out.println("创时期列表如下：");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    // 获取keys
    void getKeys() {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        // 获取keys列表数据并输出
        Set<String> set = jedis.keys("*");
        System.out.println("redis所有key如下：");
        for (String str : set) {
            System.out.println(str);
        }
    }

    // 用中文key获取列表数据
    void chineseGetList() {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        // 获取列表数据并输出
        List<String> list = jedis.lrange("创时期", 0, 5);
        System.out.println("创时期列表如下：");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    // 关闭redis服务
    void redisShutdown() {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        // 实例化连接对象
        jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("******");
        System.out.println("连接www.kareza.cn的redis成功");
        // 查看服务是否运行
        System.out.println("关闭redis服务");
        jedis.shutdown();
    }

}
