package xd.auctionhouse.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import xd.auctionhouse.Service.AuctionService;

/**
 * Created by OpartyOtaczki on 11.08.2017.
 */
@Controller
@SessionAttributes({"name","User"})
@RequestMapping("/")
public class ViewController {

    private AuctionService ah;

    @Autowired
    ViewController(AuctionService ah){this.ah=ah;}

    @GetMapping
    public String index(Model model){
        ah.updateZakonczona();
        return "index";
    }
    @GetMapping(path="/index")
    public String index2(Model model){
        ah.updateZakonczona();
        return "index";
    }
    @GetMapping(path="/login")
    public String login(Model model){
        ah.updateZakonczona();
        return "login";
    }
    @GetMapping(path="/register")
    public String register(Model model){
        return "register";
    }
    @GetMapping(path="/home_z")
    public String zalogowano(Model model){
        return "home_z";
    }

}
