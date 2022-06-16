package website;

import lombok.SneakyThrows;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class LocationDatabaseFixture {

    private MariaDbDataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @SneakyThrows
    public LocationDatabaseFixture() {
        dataSource = new MariaDbDataSource("jdbc:mariadb://localhost/locations");
        dataSource.setUser("locations");
        dataSource.setPassword("locations");
        jdbcTemplate = new JdbcTemplate(dataSource);

    }
    public  void createlocation(String name, double lat, double lon) {
        jdbcTemplate.update("insert into location(name, lat, lon) values (?,?,?"), name, lat, lon);
    }
}
