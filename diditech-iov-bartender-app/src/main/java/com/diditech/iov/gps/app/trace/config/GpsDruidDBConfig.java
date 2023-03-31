package com.diditech.iov.gps.app.trace.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.diditech.iov.gps.app.core.config.BaseDBConfig;
import com.diditech.iov.gps.app.core.config.CommonDBConfig;
import lombok.Setter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Setter
@Configuration
@ConfigurationProperties("datasource.trace.mysql")
@ConditionalOnMissingBean(GpsCassandraConfig.class)
@MapperScan(basePackages = "com.diditech.iov.gps.app.trace.repository.impl",
        sqlSessionFactoryRef = "mysqlGpsSqlSessionFactory")
public class GpsDruidDBConfig extends BaseDBConfig {

    @Bean(name = "mysqlGpsDataSource")
    public DruidDataSource getDataSource(@Autowired CommonDBConfig commonDBConfig) {
        return buildDataSource(this.merge(commonDBConfig));
    }

    @Bean(name = "mysqlGpsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlGpsDataSource") DataSource dataSource) throws Exception {
        final String resource = "classpath*:/com/diditech/iov/gps/app/trace/*Mapper.xml";
        return buildSqlSessionFactory(resource, dataSource);
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mysqlGpsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return buildSqlSessionTemplate(sqlSessionFactory);
    }
}
