package xd.auctionhouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xd.auctionhouse.Entity.Auction;
import xd.auctionhouse.Entity.Category;
import xd.auctionhouse.Entity.Parameter;
import xd.auctionhouse.Model.AuctionRepository;

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
    public List<Category> getAllCat(){return this.arepo.getAllCat();}
    public List<Parameter> getAllCatParam(int id){return this.arepo.getAllCatParam(id);}
    public void addNewAuction(Auction auction,int i,int id){this.arepo.addNewAuction(auction,i,id);}
}
