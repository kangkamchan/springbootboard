package net.fullstack7.springboot.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
@Log4j2
public class DataSourceTests {
    @Autowired
    private DataSource dataSource;

//    @Test
//    public void setDataSource() throws Exception {
//        @Cleanup Connection conn = dataSource.getConnection();
//        Assertions.assertNotNull(conn);
//        log.info(conn);
//    }
}
