package xd.auctionhouse.Model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

/**
 * Created by OpartyOtaczki on 11.08.2017.
 */
@Repository
public class DatabaseConnection {

    private JdbcTemplate jdbcTemplate;
    private DriverManagerDataSource dataSource;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public DriverManagerDataSource getDataSource() {
        return dataSource;
    }

    public DatabaseConnection(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://ec2-184-73-174-10.compute-1.amazonaws.com:5432/dau9lt8uhohvh1?sslmode=require");
        driverManagerDataSource.setUsername("hgjcnyasclkypk");
        driverManagerDataSource.setPassword("4a8368b4dbf9521532aee5dc5db2681076794afe6f5e8899acb5ddb23ae21a47");
        this.jdbcTemplate=new JdbcTemplate(driverManagerDataSource);
        this.dataSource = driverManagerDataSource;
    }
}
