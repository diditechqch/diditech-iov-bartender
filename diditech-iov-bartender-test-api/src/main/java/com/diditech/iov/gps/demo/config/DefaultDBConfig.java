package com.diditech.iov.gps.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 默认数据源配置 dd_monitor数据库
 * @author zhjd
 * @date 2020-06-18
 */
@Configuration("defaultDruidDBConfig")
@MapperScan(basePackages = {"com.diditech.iov.gps.*.repository"}, sqlSessionFactoryRef = "defaultSqlSessionFactory")
public class DefaultDBConfig extends AbstractDbConfig {

    @Autowired
    @Override
    public final void setDbUrl(@Value("${datasource.default.url}") String dbUrl) {
        super.setDbUrl(dbUrl);
    }

    @Autowired
    @Override
    public final void setUsername(@Value("${datasource.default.username}") String username) {
        super.setUsername(username);
    }

    @Autowired
    @Override
    public final void setPassword(@Value("${datasource.default.password}") String password) {
        super.setPassword(password);
    }

    @Autowired
    @Override
    public final void setDriverClassName(@Value("${datasource.common.driverClassName}") String driverClassName) {
        super.setDriverClassName(driverClassName);
    }

    @Autowired
    @Override
    public final void setInitialSize(@Value("${datasource.common.initialSize}") int initialSize) {
        super.setInitialSize(initialSize);
    }

    @Autowired
    @Override
    public final void setMinIdle(@Value("${datasource.common.minIdle}") int minIdle) {
        super.setMinIdle(minIdle);
    }

    @Autowired
    @Override
    public final void setMaxActive(@Value("${datasource.common.maxActive}") int maxActive) {
        super.setMaxActive(maxActive);
    }

    @Autowired
    @Override
    public final void setMaxWait(@Value("${datasource.common.maxWait}") int maxWait) {
        super.setMaxWait(maxWait);
    }

    @Autowired
    @Override
    public final void setTimeBetweenEvictionRunsMillis(@Value("${datasource.common.timeBetweenEvictionRunsMillis}") int timeBetweenEvictionRunsMillis) {
        super.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    }

    @Autowired
    @Override
    public final void setMinEvictableIdleTimeMillis(@Value("${datasource.common.minEvictableIdleTimeMillis}") int minEvictableIdleTimeMillis) {
        super.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    }

    @Autowired
    @Override
    public final void setValidationQuery(@Value("${datasource.common.validationQuery}") String validationQuery) {
        super.setValidationQuery(validationQuery);
    }

    @Autowired
    @Override
    public final void setTestWhileIdle(@Value("${datasource.common.testWhileIdle}") boolean testWhileIdle) {
        super.setTestWhileIdle(testWhileIdle);
    }

    @Autowired
    @Override
    public final void setTestOnBorrow(@Value("${datasource.common.testOnBorrow}") boolean testOnBorrow) {
        super.setTestOnBorrow(testOnBorrow);
    }

    @Autowired
    @Override
    public final void setTestOnReturn(@Value("${datasource.common.testOnReturn}") boolean testOnReturn) {
        super.setTestOnReturn(testOnReturn);
    }

    @Autowired
    @Override
    public final void setPoolPreparedStatements(@Value("${datasource.common.poolPreparedStatements}") boolean statements) {
        super.setPoolPreparedStatements(statements);
    }

    @Autowired
    @Override
    public final void setMaxPoolPreparedStatementPerConnectionSize(@Value("${datasource.common.maxPoolPreparedStatementPerConnectionSize}") int size) {
        super.setMaxPoolPreparedStatementPerConnectionSize(size);
    }

    @Autowired
    @Override
    public final void setFilters(@Value("${datasource.common.filters}") String filters) {
        super.setFilters(filters);
    }

    @Autowired
    @Override
    public final void setConnectionProperties(@Value("${datasource.common.connectionProperties}") String connectionProperties) {
        super.setConnectionProperties(connectionProperties);
    }

    @Primary
    @Bean(name = "defaultDataSource")
    @Override
    public DataSource dataSource() {
        return super.dataSource();
    }

    @Bean(name = "defaultSqlSessionFactory")
    @Override
    public SqlSessionFactory sqlSessionFactory(@Qualifier("defaultDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = super.sqlSessionFactoryBean(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperLocations = resolver.getResources("classpath*:/com/diditech/iov/gps/demo/repository/*Mapper.xml");
        sqlSessionFactoryBean.setMapperLocations(mapperLocations);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "defaultSqlSessionTemplate")
    @Override
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("defaultSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return super.sqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean(name = "defaultSqlTransactionManager")
    @Override
    public PlatformTransactionManager transactionManager(@Qualifier("defaultDataSource") DataSource dataSource) {
        return super.transactionManager(dataSource);
    }
}  
