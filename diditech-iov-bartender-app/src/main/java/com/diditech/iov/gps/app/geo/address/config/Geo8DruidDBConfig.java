package com.diditech.iov.gps.app.geo.address.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.diditech.iov.gps.app.core.config.BaseDBConfig;
import com.diditech.iov.gps.app.core.config.CommonDBConfig;
import lombok.Setter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 逆地理编码库，存储百度返回结果，一定程度上解决KEY资源问题
 *
 * @author zhjd <br>
 * @date 2023/3/7 <br>
 */
@Setter
@Configuration
@ConditionalOnExpression("!T(org.springframework.util.StringUtils).isEmpty('${datasource.geo-library.url:}')")
@ConfigurationProperties("datasource.geo-library")
@MapperScan(basePackages = "com.diditech.iov.gps.app.geo.address.repository",
        sqlSessionFactoryRef = "geoSqlSessionFactory")
public class Geo8DruidDBConfig extends BaseDBConfig {

    @Bean(name = "geoDataSource")
    public DruidDataSource getDataSource(@Autowired CommonDBConfig commonDBConfig) {
        return buildDataSource(commonDBConfig);
    }

    @Bean(name = "geoSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("geoDataSource") DataSource dataSource) throws Exception {
        final String resource = "classpath*:/com/diditech/iov/gps/app/geo/repository/*Mapper.xml";
        return buildSqlSessionFactory(resource, dataSource);
    }

    @Bean(name = "geoSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("geoSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return buildSqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "geoSqlTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("geoDataSource") DataSource dataSource) {
        return buildTransactionManager(dataSource);
    }

}
