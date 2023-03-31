package com.diditech.iov.gps.app.gpslog.config;

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

import javax.sql.DataSource;

@Setter
@Configuration
@ConfigurationProperties("datasource.gpslog")
@MapperScan(basePackages = "com.diditech.iov.gps.app.gpslog.repository",
        sqlSessionFactoryRef = "mysqlGpsLogSqlSessionFactory")
public class GpsLogDruidDBConfig extends BaseDBConfig {

    @Bean(name = "gpsLogDataSource")
    public DruidDataSource getDataSource(@Autowired CommonDBConfig commonDBConfig) {
        return buildDataSource(commonDBConfig);
    }

    @Bean(name = "mysqlGpsLogSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("gpsLogDataSource") DataSource dataSource) throws Exception {
        final String resource = "classpath*:/com/diditech/iov/gps/app/gpslog/*Mapper.xml";
        return buildSqlSessionFactory(resource, dataSource);
    }

    @Bean(name = "mysqlGpsLogSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mysqlGpsLogSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return buildSqlSessionTemplate(sqlSessionFactory);
    }
}
