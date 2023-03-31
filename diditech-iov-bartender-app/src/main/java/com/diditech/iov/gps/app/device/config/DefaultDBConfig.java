package com.diditech.iov.gps.app.device.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.diditech.iov.gps.app.core.config.BaseDBConfig;
import com.diditech.iov.gps.app.core.config.CommonDBConfig;
import lombok.Setter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 默认数据源配置核心业务数据库
 *
 * @author zhjd
 * @date 2020-06-18
 */
@Setter
@Configuration("defaultDruidDBConfig")
@ConfigurationProperties("datasource.default")
@MapperScan(basePackages = {
        "com.diditech.iov.gps.app.core.repository",
        "com.diditech.iov.gps.app.device.repository",
        "com.diditech.iov.gps.app.obd.repository",
        "com.diditech.iov.gps.app.rules.repository",
        "com.diditech.iov.gps.app.stop.repository",
        "com.diditech.iov.gps.app.report.repository"},
        sqlSessionFactoryRef = "defaultSqlSessionFactory")
public class DefaultDBConfig extends BaseDBConfig {

    @Primary
    @Bean(name = "defaultDataSource")
    public DruidDataSource getDataSource(@Autowired CommonDBConfig commonDBConfig) {
        return buildDataSource(commonDBConfig);
    }

    @Bean(name = "defaultSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("defaultDataSource") DataSource dataSource) throws Exception {
        final String resource = "classpath*:/com/diditech/iov/gps/app/position/*Mapper.xml";
        return buildSqlSessionFactory(resource, dataSource);
    }

    @Bean(name = "defaultSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("defaultSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return buildSqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean(name = "defaultSqlTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("defaultDataSource") DataSource dataSource) {
        return buildTransactionManager(dataSource);
    }
}  
