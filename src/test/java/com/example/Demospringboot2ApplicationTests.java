package com.example;

import com.example.beans.ItripHotelVO;
import com.example.beans.ItripUser;
import com.example.dao.ItripUserMapper;
import com.example.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.service.ItripUserService;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demospringboot2ApplicationTests {
    @Autowired
    private User user;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private DataSource dataSource;
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ItripUserMapper itripUserMapper;

    @Autowired
    private ItripUserService itripUserService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SolrClient solrClient;

    @Test
    public void testSolr() throws IOException, SolrServerException {
        //查询条件
        SolrQuery query=new SolrQuery("*:*");
        //修改查询条件
        query.setQuery("destination:北京");
        query.addFilterQuery("keyword:2003");

        //执行查询
        QueryResponse response = solrClient.query("hotel", query, SolrRequest.METHOD.GET);
        //查询结果总条目数
        System.out.println(response.getResults().getNumFound()+"-------总条数-------------");
        //查询结果封装成bean
        List<ItripHotelVO> hotelVOS = response.getBeans(ItripHotelVO.class);
        for (ItripHotelVO hotelVO : hotelVOS) {
            System.out.println(hotelVO);
        }
    }

    @Test
    public void testRedis(){
//        stringRedisTemplate.opsForValue().set("hello","java");
//        stringRedisTemplate.opsForValue().set("name","tom");
//        System.out.println(stringRedisTemplate.opsForValue().get("hello"));
//        stringRedisTemplate.expire("hello",30, TimeUnit.SECONDS);
//        System.out.println(stringRedisTemplate.getExpire("hello")+"-------有效期");
//        stringRedisTemplate.delete("hello");
//        redisTemplate.delete("hello");
        redisTemplate.opsForValue().set("name","tom");

//        new ObjectMapper().writeValueAsString(new Object());
    }

    @Test
    public void  testTx() throws Exception {
        ItripUser user = itripUserService.getItripUserById(29L);
        System.out.println(user.getId()+user.getUserName()+user.getCreationDate());
    }

    @Test
    public void testMapper() throws Exception {
        System.out.println(dataSource.getClass().getName());
        ItripUser user = itripUserMapper.getItripUserById(29L);
        System.out.println(user.getId()+user.getUserName()+user.getCreationDate());
    }

    @Test
    public void testJDBC() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/itripdb?serverTimezone=Asia/Shanghai",
                "root", "123");
        PreparedStatement ps = conn.prepareStatement("select * from itrip_user where id=?");
        ps.setLong(1,29L);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getLong("id")+"--"
                    +rs.getString("userName")+"--"
                    +rs.getTimestamp("creationDate"));
        }

    }

   /* @Test
    public void contextLoads() {
        System.out.println(user);
        System.out.println(dataSource.getClass().getName());
//        System.out.println(applicationContext);
//        System.out.println(applicationContext.getDisplayName());
        JdbcTemplate template = new JdbcTemplate(dataSource);
        List<Map<String, Object>> maps = template.queryForList("select * from itrip_user");
        System.out.println(maps.size());
        System.out.println(maps);
        System.out.println(new Date());
    }*/


}
