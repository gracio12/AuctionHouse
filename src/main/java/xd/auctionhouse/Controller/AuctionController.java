package xd.auctionhouse.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import xd.auctionhouse.Entity.Auction;
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

}
