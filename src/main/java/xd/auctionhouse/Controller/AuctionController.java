package xd.auctionhouse.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xd.auctionhouse.Entity.Auction;
import xd.auctionhouse.Entity.Parameter;
import xd.auctionhouse.Entity.User;
import xd.auctionhouse.Service.AuctionService;

/**
 * Created by OpartyOtaczki on 15.08.2017.
 */
@Controller
@RequestMapping("/auction")
@SessionAttributes({"name", "User", "adm"})
public class AuctionController {

    private AuctionService ah;
    private int id_kat;
    private int aukcja;

    @Autowired
    public AuctionController(AuctionService ah) {
        this.ah = ah;
    }

    @GetMapping(value = "/search")
    public String aukcje(Model model) {
        model.addAttribute("aukcje", ah.getAllAuction());
        return "auction";
    }

    @GetMapping(value = "/mysell")
    public String sprzedaz(Model model, @SessionAttribute("User") User user) {
        if (model.containsAttribute("User")) {
            model.addAttribute("sprzedaz", ah.getAllSell(user.getId_uzytk()));
            return "mysell";
        } else return "redirect:/user/login";
    }

    @GetMapping(value = "/new")
    public String nowy(Model model, @SessionAttribute("User") User user) {
        if (model.containsAttribute("User")) {
            model.addAttribute("kategorie", ah.getAllCat());
            return "newauction";
        } else return "redirect:/user/login";
    }

    @RequestMapping(value = "/new/{id}")
    public String nowyPoKategori(Model model, @PathVariable int id, @SessionAttribute("User") User user) {
        if (model.containsAttribute("User")) {
            model.addAttribute("id_kat", id);
            id_kat = id;
            model.addAttribute("aukcja", new Auction());
            return "newauctionadd";
        } else return "redirect:/user/login";
    }

    @PostMapping(value = "/add")
    public String nowyPoKategoriAdd(Model model, @ModelAttribute("aukcja") Auction auction, @SessionAttribute("User") User user) {
        if (model.containsAttribute("User")) {
            aukcja = ah.addNewAuction(auction, user.getId_uzytk(), id_kat);
            return "redirect:/auction/addparam";
        } else return "redirect:/user/login";
    }

    @GetMapping(value = "/addparam")
    public String nowyPoKategoriAddParam(Model model, @SessionAttribute("User") User user) {
        if (model.containsAttribute("User")) {
            model.addAttribute("parametry", ah.getAllCatParam(id_kat));
            return "newauctionparam";
        } else return "redirect:/user/login";
    }

    @PostMapping(value = "/addparam")
    public String nowyPoKategoriAddParamm(Model model, @ModelAttribute("parametry") Parameter param, @SessionAttribute("User") User user) {
        if (model.containsAttribute("User")) {
            ah.addNewAuctionParam(param, id_kat, aukcja);
            return "redirect:/auction/mysell";
        } else return "redirect:/user/login";
    }

}
