package xd.auctionhouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xd.auctionhouse.Entity.*;
import xd.auctionhouse.Model.AuctionRepository;

import java.io.File;
import java.util.List;

/**
 * Created by OpartyOtaczki on 15.08.2017.
 */
@Service
public class AuctionService {
    private AuctionRepository arepo;

    @Autowired
    AuctionService(AuctionRepository repo){this.arepo=repo;}

    public List<Auction> getAllAuction(){return this.arepo.getAllAuction();}
    public List<Auction> getAllSell(int g){return this.arepo.getAllSell(g);}
    public List<Auction> getAllBuy(int g){return this.arepo.getAllBuy(g);}
    public List<Category> getAllCat(){return this.arepo.getAllCat();}
    public List<Parameter> getAllCatParam(int id){return this.arepo.getAllCatParam(id);}
    public int addNewAuction(Auction auction,int i,int id){return this.arepo.addNewAuction(auction,i,id);}
    public void addNewAuctionParam(Parameter parameter, String kater, int aukcja){this.arepo.addNewAuctionParam(parameter,kater,aukcja);}
    public List<Off> getAllOff(int aukcja){return this.arepo.getAllOff(aukcja);}
    public List<Auction> getAuction(int id){return this.arepo.getAuction(id);}
    public List<Off> getAllCateg(int aukcji){return this.arepo.getAllCateg(aukcji);}
    public void addNewOffer(int id_uzytk,int ilosc,int id_aukcji){this.arepo.addNewOffer(id_uzytk,ilosc,id_aukcji);}
    public void addNewLic(int id_uzytk,int ilosc,double ilosc2,int id_aukcji){this.arepo.addNewLic(id_uzytk,ilosc,ilosc2,id_aukcji);}
    public String getObrazek(int aukcja){return this.arepo.getObrazek(aukcja);}
    public List<Off> getAllOff2(int aukcja){return this.arepo.getAllOff2(aukcja);}
    public void updateZakonczona(){this.arepo.updateZakonczenia();}
}
