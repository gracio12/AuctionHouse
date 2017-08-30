package xd.auctionhouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xd.auctionhouse.Entity.User;
import xd.auctionhouse.Model.UserRepository;

import java.util.List;

/**
 * Created by OpartyOtaczki on 11.08.2017.
 */
@Service
public class UserService {
    private UserRepository repo;

    @Autowired
    UserService(UserRepository userRepository){
        this.repo=userRepository;
    }
    public boolean addUserToDataBase(User user) {
        List<User> list = this.repo.findUser(user);
        for (User us: list) {
            if(us.getLogin().equals(user.getLogin())){
                return false;
            }
        }
            if(user.getHaslo().length()<8){
                return false;
            }
            if(!user.getEmail().contains("@")){
                return false;
            }

        return repo.register(user);
    }
    public User login(User user){
        if(user.getHaslo()==null || user.getLogin()==null){
            user.setId_uzytk(-1);
            return user;}
        List<User> list = this.repo.findUser(user);
        for (User us: list) {
            if(us.getLogin().equals(user.getLogin())&&us.getHaslo().equals(this.repo.sha512(user.getHaslo(),"powodzenia"))){
                return us;
            }
        }
        return null;
    }
    public void updateUser(User user,User edit){
        user.copyUser(edit);
        this.repo.updateUser(user);
    }
}
