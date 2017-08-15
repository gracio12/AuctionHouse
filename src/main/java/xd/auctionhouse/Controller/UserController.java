package xd.auctionhouse.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import xd.auctionhouse.Entity.User;
import xd.auctionhouse.Service.UserService;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by OpartyOtaczki on 11.08.2017.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes({"name","User","adm"})
public class UserController {
    private UserService userService;


    @Autowired
    UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping(value="/register.try")
    public String register(Model model, User register){
        if(userService.addUserToDataBase(register)){

            return "redirect:/";
        }
        else{
            model.addAttribute("reg",true);
            return "register";
        }
    }

    @PostMapping(value = "/login")
    public String login(Model model, User user){
        User us=userService.login(user);
        if(us!=null){
            model.addAttribute("name",us.getImie()+ " "+ us.getNazwisko());
            model.addAttribute("User",us);
            return "redirect:/user/panel";
        }
        else
        {
            model.addAttribute("log",true);
            return "login";
        }
    }
    @GetMapping(value = "/logout")
    public String logout(@ModelAttribute User user, WebRequest request, SessionStatus status) {
        status.setComplete();
        request.removeAttribute("user", WebRequest.SCOPE_SESSION);
        request.removeAttribute("name", WebRequest.SCOPE_SESSION);
        return "redirect:/";
    }
    @GetMapping(value = "/panel")
    public String panel(Model model){
        if(model.containsAttribute("User")) {
            User user = (User) model.asMap().get("User");
            model.addAttribute("adm", user.getAdmin());
            return "index";
        }
        else
            return "error";
    }


}
