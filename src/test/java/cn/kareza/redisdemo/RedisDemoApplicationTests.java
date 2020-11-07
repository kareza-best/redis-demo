package cn.kareza.redisdemo;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONTokener;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.sql.Struct;
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
    @Test
    void delBatchString() {
        delString("fuchainbak");
        delString("esbak");
    }

    // 批量添加
    @Test
    void addBatchString() {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");

        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("kareza", "超喜欢超越妹妹");
        jsonObject.putOpt("kareza", "超喜欢超越妹妹");
        jsonObject.putOpt("kareza", "超喜欢超越妹妹");

        jedis.lpush("test", jsonObject.toString());

        jedis.get("test");


//        addString("杨超越创造101之旅（上）", "https://www.douban.com/note/697029175/");
//        addString("杨超越创造101之旅（中）", "https://www.douban.com/note/719198219/");
//        addString("杨超越创造101之旅（下）", "https://www.douban.com/note/697039027/");
    }

    // 添加列表
    @Test
    void addList() {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
//        // 存储数据到列表中
//        jedis.lpush("创时期", "杨超越创造101之旅（上）");
//        jedis.lpush("创时期", "杨超越创造101之旅（中）");
//        jedis.lpush("创时期", "杨超越创造101之旅（下）");
        // 获取列表数据并输出
//        String result = "";
        List<String> list = jedis.lrange("ycy", 0, 5);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
//            result = list.get(i);
//            System.out.println(result);
//            result = JSONUtil.quote(result);
//            System.out.println(result);
//            JSONObject jsonObject = JSONUtil.parseObj(result);
//            System.out.println(jsonObject);
//            result = "";
        }
    }

    @Test
    void getList() {
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        List<String> list = jedis.lrange("esbak", 0, 1500);
//        for (int i = 0; i < list.size(); i++) {
//            String testStr = list.get(i);
////            System.out.println(testStr);
//            testStr = StrUtil.removePrefix(testStr, "\"");
////            System.out.println(testStr);
//            testStr = StrUtil.removeSuffix(testStr, "\"");
////            System.out.println(testStr);
//            testStr = StrUtil.removeAll(testStr, "\\");
////            System.out.println(testStr);
//            JSONObject jsonObject = new JSONObject(testStr);
//            System.out.println(jsonObject);
//        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    void brpoplpush() {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        String result = jedis.brpoplpush("ycy", "ycyBak", 500);
        System.out.println(result);
    }

    @Test
    void lrem() {
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        String result = jedis.brpoplpush("ycyBak", "ycy", 500);
        System.out.println(result);
        jedis.lrem("ycyBak", 0, result);
    }

    // 获取所有keys
    @Test
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
    @Test
    void chineseGetList() {
        // 实例化连接对象
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        // 获取列表数据并输出
        List<String> list = jedis.lrange("ycy", 0, 5);
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

    @Test
    void testJSON() {
        String str = "{\"table_34b4ad3c3ea8c0c2ded510e795fe77e7\":{\"signature\":{},\"body\":{\"create_time\":1600514933000,\"end_time\":1524576000000,\"top_ranking\":\"25\",\"version\":\"1\",\"start_time\":1524543000000,\"entries\":\"杨超越\",\"update_time\":1600514933000,\"deleted\":\"0\",\"id\":\"1307149113942347777\",\"remarks\":\"《创造101》第1期\",\"start_date\":\"2018-04-23\"},\"head\":{\"dataHash\":\"34b4ad3c3ea8c0c2ded510e795fe77e7\",\"dataType\":\"table\",\"dataCrypt\":\"md5\",\"dataName\":\"ycy_site_trending\",\"dataFlag\":\"agent\",\"dataTime\":\"2020-09-30 16:18:34\",\"dataUser\":\"kareza\",\"dataIndex\":{\"entries\":\"杨超越\",\"id\":\"1307149113942347777\",\"start_date\":\"2018-04-23\"},\"dataLevel\":0}}}";
        JSONObject jsonObject = JSONUtil.parseObj(str);
        System.out.println(jsonObject);
    }

    @Test
    void testJSON2() {
        String str = "\"{\"table_34b4ad3c3ea8c0c2ded510e795fe77e7\":{\"signature\":{},\"body\":{\"create_time\":1600514933000,\"end_time\":1524576000000,\"top_ranking\":\"25\",\"version\":\"1\",\"start_time\":1524543000000,\"entries\":\"杨超越\",\"update_time\":1600514933000,\"deleted\":\"0\",\"id\":\"1307149113942347777\",\"remarks\":\"《创造101》第1期\",\"start_date\":\"2018-04-23\"},\"head\":{\"dataHash\":\"34b4ad3c3ea8c0c2ded510e795fe77e7\",\"dataType\":\"table\",\"dataCrypt\":\"md5\",\"dataName\":\"ycy_site_trending\",\"dataFlag\":\"agent\",\"dataTime\":\"2020-09-30 16:18:34\",\"dataUser\":\"kareza\",\"dataIndex\":{\"entries\":\"杨超越\",\"id\":\"1307149113942347777\",\"start_date\":\"2018-04-23\"},\"dataLevel\":0}}}\"";
        str = StrUtil.removePrefix(str, "\"");
        System.out.println(str);
        str = StrUtil.removeSuffix(str, "\"");
        System.out.println(str);
        JSONObject jsonObject = JSONUtil.parseObj(str);
        System.out.println(jsonObject);
    }

    @Test
    void testJSON3() {
        String str = "kareza";
        String newStr = StrUtil.removePrefix(str, "kare");
        System.out.println(newStr);
    }

    @Test
    void testPipeline() {
        Jedis jedis = new Jedis("www.kareza.cn", 6379);
        jedis.auth("kareza");
        Pipeline pipeline = jedis.pipelined();
        pipeline.set("ccyy", "O.O");
        pipeline.sync();
        getKeys();
    }

}
