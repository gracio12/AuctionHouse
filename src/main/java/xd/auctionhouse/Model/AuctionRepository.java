package xd.auctionhouse.Model;

import org.springframework.stereotype.Repository;
import xd.auctionhouse.Entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by OpartyOtaczki on 15.08.2017.
 */
@Repository
public class AuctionRepository {

    private DatabaseConnection data;

    public AuctionRepository(DatabaseConnection databaseConnection){
        this.data=databaseConnection;
    }

    public List<Auction> getAllAuction() {
        String query = "select id_aukcji, nazwa, opis, cena_aktualna from aukcja;";
        List<Auction> ListaObiektow = new ArrayList<Auction>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        for (Map tabWierszy : wiersze) {
            Auction obiekt = new Auction();
            obiekt.setId_aukcji(Integer.parseInt(String.valueOf(tabWierszy.get("id_aukcji"))));
            obiekt.setNazwa(String.valueOf(tabWierszy.get("nazwa")));
            obiekt.setOpis(String.valueOf((tabWierszy.get("opis"))));
            obiekt.setCena_aktualna(Double.parseDouble((String.valueOf(tabWierszy.get("cena_aktualna")))));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }

}