package com.bolsadeideas.springboot.backend.apirest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@Configuration
@EnableReactiveCassandraRepositories
@EnableCassandraRepositories
public class CassandraConfig extends AbstractReactiveCassandraConfiguration {

    @Override
    protected boolean getMetricsEnabled() { return false; }
    /*

     * Provide a contact point to the configuration.
     */
    public String getContactPoints() {
        return "127.0.0.1";
    }

    /*
     * Provide a keyspace name to the configuration.
     */
    public String getKeyspaceName() {
        return "db_springboot_backend";
    }
}
