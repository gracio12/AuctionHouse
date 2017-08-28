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
        driverManagerDataSource.setUrl("jdbc:postgresql://tantor.db.elephantsql.com:5432/yhehdrtj");
        driverManagerDataSource.setUsername("yhehdrtj");
        driverManagerDataSource.setPassword("0pYOcXgX797ufEgwPObHO1Kh7TcOb6m-");
        this.jdbcTemplate=new JdbcTemplate(driverManagerDataSource);
        this.dataSource = driverManagerDataSource;
    }
}
