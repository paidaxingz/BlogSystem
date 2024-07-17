package com.example.blogmanagersystem.Config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class MyBatisConfig {

    @Bean
    public PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        // 配置其他 PageHelper 的属性，如 dialect
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, PageInterceptor pageInterceptor) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // 设置 PageHelper 拦截器
        Interceptor[] interceptors = new Interceptor[]{pageInterceptor};
        sessionFactory.setPlugins(interceptors);

        return sessionFactory.getObject();
    }
}
