package xd.auctionhouse.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;
import xd.auctionhouse.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by OpartyOtaczki on 11.08.2017.
 */

@Repository
public class UserRepository {
    private DatabaseConnection data;

    @Autowired
    public UserRepository(DatabaseConnection databaseConnection){
        this.data=databaseConnection;
    }
    public List<User> findUser(User user){
        return data.getJdbcTemplate().query("select id_uzytk, login, haslo, imie, nazwisko, email, admin from uzytkownik u where u.login='"+ user.getLogin()+"';",new RowMapper<User>(){
            public User mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                User user = new User();
                user.setId_uzytk(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setHaslo(rs.getString(3));
                user.setImie(rs.getString(4));
                user.setNazwisko(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setAdmin(rs.getBoolean(7));
                return user;
            }
        });
    }
    public boolean register(User user){
        if(this.findUser(user)!=null) {
            data.getJdbcTemplate().update("INSERT into uzytkownik (login,haslo, imie, nazwisko,email,admin) values (?,?,?,?,?,FALSE)"
                    , user.getLogin(), user.getHaslo(), user.getImie(), user.getNazwisko(), user.getEmail());
            return true;
        }
        return false;
    }
}
