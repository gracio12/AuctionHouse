package xd.auctionhouse.Model;

import org.springframework.stereotype.Repository;
import xd.auctionhouse.Entity.*;

import java.sql.Timestamp;
import java.util.*;

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

    public List<Auction> getAllSell(int g) {
        String query = "select id_aukcji, nazwa, opis, cena_aktualna from aukcja where id_uzytk="+g+";";
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
    public List<Category> getAllCat() {
        String query = "select id_kat,opis from kategorie;";
        List<Category> ListaObiektow = new ArrayList<Category>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        for (Map tabWierszy : wiersze) {
            Category obiekt = new Category();
            obiekt.setId_kat(Integer.parseInt(String.valueOf(tabWierszy.get("id_kat"))));
            obiekt.setOpis(String.valueOf((tabWierszy.get("opis"))));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }
    public List<Parameter> getAllCatParam(int id) {
        String query = "select id_param,nazwa from parametr where id_kat="+id+";";
        List<Parameter> ListaObiektow = new ArrayList<Parameter>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        for (Map tabWierszy : wiersze) {
            Parameter obiekt = new Parameter();
            obiekt.setId_param(Integer.parseInt(String.valueOf(tabWierszy.get("id_param"))));
            obiekt.setNazwa(String.valueOf((tabWierszy.get("opis"))));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }
    public void addNewAuction(Auction auction,int i,int id){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_WEEK, 7);
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        data.getJdbcTemplate().update("INSERT into aukcja (nazwa,opis,cena_aktualna,do_konca,ilosc,id_uzytk,kategoria) values (?,?,?,?,?,?,?)"
                , auction.getNazwa(),auction.getOpis(),auction.getCena_aktualna(),timestamp,auction.getIlosc(),i,id);
    }

}
