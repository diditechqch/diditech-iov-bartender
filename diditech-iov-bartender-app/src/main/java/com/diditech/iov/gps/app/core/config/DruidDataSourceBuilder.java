package com.diditech.iov.gps.app.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Druid数据源构建类
 *
 * @author zhjd <br>
 * @date 2023/3/7 <br>
 */
@UtilityClass
public class DruidDataSourceBuilder {

    public DruidDataSource build(BaseDBConfig config) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.configFromPropety(config.toProps());
        return datasource;
    }

    private SqlSessionFactoryBean buildSqlSessionFactoryBean(String resource, DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setFailFast(true);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperLocations = resolver.getResources(resource);
        sqlSessionFactoryBean.setMapperLocations(mapperLocations);
        return sqlSessionFactoryBean;
    }

    public SqlSessionFactory buildSqlSessionFactory(String resource, DataSource dataSource) throws Exception {
        return buildSqlSessionFactoryBean(resource, dataSource).getObject();
    }

    public SqlSessionTemplate buildSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    public PlatformTransactionManager buildTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
