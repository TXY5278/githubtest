package com.it.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/*
druid 连接池工具类
 */
public class DruidUtils {
    /**
     *
     * @return
     */
    public static DataSource getDatasource() {
        //1.读取配置文件
        Thread thread = Thread.currentThread();
        //获取类加载对象
        ClassLoader classLoader = thread.getContextClassLoader();
        //通过类加载器，调用getResourceAsStream方法获取类路径下的配置文件，并返回输入流
        InputStream input = classLoader.getResourceAsStream("druid.properties");
        //创建属性对象
        Properties pop = new Properties();

        DataSource dataSource = null;
        try {
            //将读取到的配置信息存放到属性对象中
            pop.load(input);
            //通过配置文件创建数据库连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(pop);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return getDatasource().getConnection();
    }
}
