package xd.auctionhouse.Controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xd.auctionhouse.Entity.Auction;
import xd.auctionhouse.Entity.Parameter;
import xd.auctionhouse.Entity.Podkategoria;
import xd.auctionhouse.Entity.User;
import xd.auctionhouse.Service.AuctionService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

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
    private List<Parameter> param;
    private int id_a;

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
        } else return "redirect:/login";
    }
    @GetMapping(value = "/mybuy")
    public String kupno(Model model, @SessionAttribute("User") User user) {
        if (model.containsAttribute("User")) {
            model.addAttribute("kupno", ah.getAllBuy(user.getId_uzytk()));
            return "mybuy";
        } else return "redirect:/login";
    }

    @GetMapping(value = "/new")
    public String nowy(Model model) {
        if (model.containsAttribute("User")) {
            model.addAttribute("kategorie", ah.getAllCat());
            return "newauction";
        } else return "redirect:/login";
    }

    @RequestMapping(value = "/new/{id}")
    public String nowyPoKategori(Model model, @PathVariable int id) {
        if (model.containsAttribute("User")) {
            model.addAttribute("id_kat", id);
            id_kat = id;
            model.addAttribute("aukcja", new Auction());
            return "newauctionadd";
        } else return "redirect:/login";
    }

    @PostMapping(value = "/add")
    public String nowyPoKategoriAdd(Model model, @ModelAttribute("aukcja") Auction auction,
                                    @SessionAttribute("User") User user,@RequestParam("logo")MultipartFile logo) throws IOException {
        if (model.containsAttribute("User")) {

            byte[] bytes = logo.getBytes();
            String base64 = new String(Base64.encodeBase64(bytes), "ISO-8859-2");
            auction.setObrazek(base64);
            aukcja = ah.addNewAuction(auction, user.getId_uzytk(), id_kat);

            return "redirect:/auction/addparam";
        } else return "redirect:/login";
    }

    @GetMapping(value = "/addparam")
    public String nowyPoKategoriAddParam(Model model, @SessionAttribute("User") User user) {
        if (model.containsAttribute("User")) {
            model.addAttribute("parametry", ah.getAllCatParam(id_kat));
            param = ah.getAllCatParam(id_kat);
            //model.addAttribute("kater",new Podkategoria());
            return "newauctionparam";
        } else return "redirect:/login";
    }

    @PostMapping(value = "/addparam")
    public String nowyPoKategoriAddParamm(Model model, @RequestParam("kat")List<String> to){//@ModelAttribute("kater")Podkategoria kater) {
        if (model.containsAttribute("User")) {
            int tosize=to.size();
            for(int i=0;i<tosize;i++)
            ah.addNewAuctionParam(param.get(i), to.get(i), aukcja);
            return "redirect:/auction/mysell";
        } else return "redirect:/login";
    }

    @GetMapping(value = "/item/{id}")
    public String stronaItem(Model model, @PathVariable int id) {
            id_a=id;
            model.addAttribute("auk", ah.getAuction(id));
            model.addAttribute("oferty", ah.getAllOff(id));
            model.addAttribute("oferty2", ah.getAllOff2(id));
            model.addAttribute("kat",ah.getAllCateg(id));
            model.addAttribute("jpg",ah.getObrazek(id));
            return "item";
    }

    @PostMapping(value = "/addoff")
    public String dodajoferte(Model model, @RequestParam("oferta")int ilosc, @SessionAttribute("User") User user){
        if (model.containsAttribute("User")) {
               ah.addNewOffer(user.getId_uzytk(),ilosc,id_a);
            return "redirect:/auction/item/"+id_a;
        } else return "redirect:/login";
    }
    @PostMapping(value = "/licytacja")
    public String dodajlicyt(Model model, @RequestParam("licytacja")double ilosc2, @SessionAttribute("User") User user){
        if (model.containsAttribute("User")) {
            ah.addNewLic(user.getId_uzytk(),1,ilosc2,id_a);
            return "redirect:/auction/item/"+id_a;
        } else return "redirect:/login";
    }

}
