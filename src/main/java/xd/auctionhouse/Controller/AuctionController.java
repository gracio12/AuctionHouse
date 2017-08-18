package xd.auctionhouse.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xd.auctionhouse.Entity.Auction;
import xd.auctionhouse.Entity.User;
import xd.auctionhouse.Service.AuctionService;

/**
 * Created by OpartyOtaczki on 15.08.2017.
 */
@Controller
@RequestMapping("/auction")
@SessionAttributes({"name","User","adm"})
public class AuctionController {

    private AuctionService ah;


    @Autowired
    public AuctionController(AuctionService ah){this.ah=ah;}

    @GetMapping(value = "/search")
    public String aukcje(Model model){
        model.addAttribute("aukcje",ah.getAllAuction());
        return "auction";
    }
    @GetMapping(value = "/mysell")
    public String sprzedaz(Model model,@SessionAttribute("User")User user){
        model.addAttribute("sprzedaz",ah.getAllSell(user.getId_uzytk()));
        return "mysell";
    }
    @GetMapping(value = "/new")
    public String nowy(Model model,@SessionAttribute("User")User user){
        model.addAttribute("kategorie",ah.getAllCat());
        return "newauction";
    }

}
