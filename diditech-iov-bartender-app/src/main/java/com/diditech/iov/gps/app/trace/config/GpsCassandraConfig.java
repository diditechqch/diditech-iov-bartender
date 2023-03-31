package com.diditech.iov.gps.app.trace.config;

import com.datastax.driver.core.*;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Setter
@Configuration
@ConfigurationProperties("datasource.trace.cassandra")
@ConditionalOnExpression("!T(org.springframework.util.StringUtils).isEmpty('${datasource.trace.cassandra.keyspace-name:}')")
@EnableCassandraRepositories(basePackages = "com.diditech.iov.gps.app.trace.repository.impl")
public class GpsCassandraConfig extends AbstractCassandraConfiguration {

    private String keyspaceName;
    private String contactPoints;
    private int port;
    private String username;
    private String password;

    private PoolingOptions poolingOptions() {
        PoolingOptions poolingOptions = new PoolingOptions();
        poolingOptions.setMaxRequestsPerConnection(HostDistance.REMOTE, 5);
        poolingOptions.setCoreConnectionsPerHost(HostDistance.REMOTE, 1);
        poolingOptions.setMaxConnectionsPerHost(HostDistance.REMOTE, 2);
        return poolingOptions;
    }

    private SocketOptions socketOptions() {
        SocketOptions options = new SocketOptions();
        options.setConnectTimeoutMillis(30000);
        options.setReadTimeoutMillis(300000);
        options.setTcpNoDelay(true);
        return options;
    }

    private QueryOptions queryOptions() {
        QueryOptions options = new QueryOptions();
        options.setConsistencyLevel(ConsistencyLevel.QUORUM);
        return options;
    }

    @Bean
    @Override
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(contactPoints);
        cluster.setPort(port);
        cluster.setPoolingOptions(poolingOptions());
        cluster.setSocketOptions(socketOptions());
        cluster.setQueryOptions(queryOptions());
        cluster.setUsername(username);
        cluster.setPassword(password);
        return cluster;
    }

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

}