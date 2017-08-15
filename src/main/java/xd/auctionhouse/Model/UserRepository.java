package xd.auctionhouse.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;
import xd.auctionhouse.Entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.io.*;


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

    public String sha512(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }


    public boolean register(User user){
        if(this.findUser(user)!=null) {
            data.getJdbcTemplate().update("INSERT into uzytkownik (login,haslo, imie, nazwisko,email,admin) values (?,?,?,?,?,FALSE)"
                    , user.getLogin(), sha512(user.getHaslo(),"powodzenia"), user.getImie(), user.getNazwisko(), user.getEmail());
            return true;
        }
        return false;
    }
}
