package xd.auctionhouse.Controller;


import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xd.auctionhouse.Entity.User;

import xd.auctionhouse.Service.UserService;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AndroidController {

    private UserService userService;

    @Autowired
    AndroidController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    @ResponseBody
    public String logowanie(String haslo, String email) throws JSONException {

        User user = new User();
        user.setHaslo(haslo);
        user.setLogin(email);
        user = userService.login(user);
        Map m = new HashMap();

            if(user.getId_uzytk()==-1){
            m.put("success", "false");
            JSONObject json = JSONObject.fromObject(m);
            String mess = json.toString();
            return mess;
        }

        m.put("success", "true");
        m.put("id_uzytkownika", user.getId_uzytk());
        m.put("nazwisko", user.getNazwisko());
        m.put("imie", user.getImie());
        m.put("email", user.getEmail());

        JSONObject json = JSONObject.fromObject(m);

        String mess = json.toString();
        return mess;
    }
}
