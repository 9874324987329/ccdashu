package com.chinaredstar.serivce.config;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;
import org.springframework.stereotype.Component;

/**
 * @author:杨果
 * @date:16/3/7 下午1:54
 * <p/>
 * Description:
 */
@Component("dbConfig")
@DisconfFile(filename = "db.properties")
public class DBConfig {
    public String masterConnection;
    public String masterUsername;
    public String masterPassword;

    public String slaveConnection;
    public String slaveUsername;
    public String slavePassword;

    public String mysqlDriver;
    public int maxActive;
    public int initialSize;
    public int minIdle;
    public String jdbcInterceptors;


    @DisconfFileItem(name="master.connection")
    public String getMasterConnection() {
        return masterConnection;
    }

    public void setMasterConnection(String masterConnection) {
        this.masterConnection = masterConnection;
    }
    @DisconfFileItem(name = "master.username")
    public String getMasterUsername() {
        return masterUsername;
    }

    public void setMasterUsername(String masterUsername) {
        this.masterUsername = masterUsername;
    }
    @DisconfFileItem(name = "master.password")
    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }
    @DisconfFileItem(name = "slave.connection")
    public String getSlaveConnection() {
        return slaveConnection;
    }

    public void setSlaveConnection(String slaveConnection) {
        this.slaveConnection = slaveConnection;
    }
    @DisconfFileItem(name = "slave.username")
    public String getSlaveUsername() {
        return slaveUsername;
    }

    public void setSlaveUsername(String slaveUsername) {
        this.slaveUsername = slaveUsername;
    }
    @DisconfFileItem(name = "slave.password")
    public String getSlavePassword() {
        return slavePassword;
    }

    public void setSlavePassword(String slavePassword) {
        this.slavePassword = slavePassword;
    }

    @DisconfFileItem(name = "jdbc.mysql.Driver")
    public String getMysqlDriver() {
        return mysqlDriver;
    }

    public void setMysqlDriver(String mysqlDriver) {
        this.mysqlDriver = mysqlDriver;
    }
    @DisconfFileItem(name = "tomcat.jdbc.pool.maxActive")
    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }
    @DisconfFileItem(name = "tomcat.jdbc.pool.initialSize")
    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    @DisconfFileItem(name = "tomcat.jdbc.pool.minIdle")
    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    @DisconfFileItem(name = "tomcat.jdbc.pool.jdbcInterceptors")
    public String getJdbcInterceptors() {
        return jdbcInterceptors;
    }

    public void setJdbcInterceptors(String jdbcInterceptors) {
        this.jdbcInterceptors = jdbcInterceptors;
    }
}
