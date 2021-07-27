package com.lagou.test;

import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    /*
        快速入门测试方法
     */
    @Test
    public void mybatisQuickStart() throws IOException {
        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2.获取sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.执行sql 参数: statement : 由namespace.id
        List<User> users = sqlSession.selectList("UserMapper.findAll");

        //5.遍历打印结果
        for (User user : users) {
            System.out.println(user);
        }

        //6.释放资源
        sqlSession.close();
    }

    //测试新增用户
    @Test
    public void testSave() throws IOException {
        // 加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(is);
        // 获取SqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 执行sql
        User user = new User();
        user.setUsername("自动提交");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("北京海淀");
        sqlSession.insert("UserMapper.save", user);
        // DML语句，手动提交事务
        //sqlSession.commit();
        // 释放资源
        sqlSession.close();

    }

    //测试更新用户
    @Test
    public void testUpdate() throws IOException {
        // 加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(is);
        // 获取SqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行sql
        User user = new User();
        user.setId(4);
        user.setUsername("lucy");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("北京海淀");
        sqlSession.update("UserMapper.update", user);
        // DML语句，手动提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();

    }

    //测试删除用户
    @Test
    public void testDelete() throws IOException {
        // 加载核心配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new
                SqlSessionFactoryBuilder().build(is);
        // 获取SqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行sql

        sqlSession.delete("UserMapper.delete", 5);
        // DML语句，手动提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();

    }
}
