package com.diditech.iov.gps.app.core.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * db基础配置项
 *
 * @author hefan
 * @date 2023/3/7 0007 13:48
 */
@Data
public class BaseDBConfig {
    protected String url;
    protected String username;
    protected String password;
    protected String driverClassName;
    protected int initialSize;
    protected int minIdle;
    protected int maxWait;
    protected int maxActive;
    protected int timeBetweenEvictionRunsMillis;
    protected int minEvictableIdleTimeMillis;
    protected String validationQuery;
    protected boolean testWhileIdle;
    protected boolean testOnBorrow;
    protected boolean testOnReturn;
    protected boolean poolPreparedStatements;
    protected int maxPoolPreparedStatementPerConnectionSize;
    protected String filters;
    protected String connectionProperties;
    protected final boolean removeAbandoned = true;
    protected final int removeAbandonedTimeout = 120;
    protected final boolean logAbandoned = true;

    /**
     * 忽略空值配置
     */
    protected final CopyOptions MERGE_OPTIONS = CopyOptions.create().setIgnoreNullValue(true);

    /**
     * 合并配置
     *
     * @author hefan
     * @date 2023/3/7 0007 14:26
     */
    public BaseDBConfig merge(BaseDBConfig source) {
        BeanUtil.copyProperties(source, this, MERGE_OPTIONS);
        return this;
    }

    /**
     * 转换为Properties
     * 会附加druid前缀
     *
     * @author hefan
     * @date 2023/3/7 0007 14:26
     */
    public Properties toProps() {
        Properties properties = new Properties();
        Map<String, Object> map = BeanUtil.beanToMap(this, new LinkedHashMap<>(),
                false, key -> "druid." + key);
        properties.putAll(map);
        return properties;
    }

    public DruidDataSource buildDataSource(BaseDBConfig config) {
        BaseDBConfig mergedConfig = this.merge(config);
        DruidDataSource datasource = new DruidDataSource();
        datasource.configFromPropety(mergedConfig.toProps());
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
