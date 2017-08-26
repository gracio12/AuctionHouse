package xd.auctionhouse.Model;

import org.springframework.stereotype.Repository;
import xd.auctionhouse.Entity.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
        String query = "select id_aukcji, nazwa, opis, do_konca,cena_aktualna from aukcja where id_uzytk="+g+";";
        List<Auction> ListaObiektow = new ArrayList<Auction>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        for (Map tabWierszy : wiersze) {
            Auction obiekt = new Auction();
            obiekt.setId_aukcji(Integer.parseInt(String.valueOf(tabWierszy.get("id_aukcji"))));
            obiekt.setNazwa(String.valueOf(tabWierszy.get("nazwa")));
            obiekt.setOpis(String.valueOf((tabWierszy.get("opis"))));
            obiekt.setData(String.valueOf((tabWierszy.get("do_konca"))));
            obiekt.setCena_aktualna(Double.parseDouble((String.valueOf(tabWierszy.get("cena_aktualna")))));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }
    public List<Auction> getAllBuy(int g) {
        String query = "select a.id_aukcji, a.nazwa, a.opis, a.do_konca,a.cena_aktualna,a.ilosc from aukcja a\n" +
                "JOIN oferta o ON o.id_aukcji=a.id_aukcji\n" +
                "where o.id_uzytk="+g+";";
        List<Auction> ListaObiektow = new ArrayList<Auction>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for (Map tabWierszy : wiersze) {
            Auction obiekt = new Auction();
            obiekt.setId_aukcji(Integer.parseInt(String.valueOf(tabWierszy.get("id_aukcji"))));
            obiekt.setNazwa(String.valueOf(tabWierszy.get("nazwa")));
            obiekt.setOpis(String.valueOf((tabWierszy.get("opis"))));
            obiekt.setData(dateFormat.format(tabWierszy.get("do_konca")));
            obiekt.setCena_aktualna(Double.parseDouble((String.valueOf(tabWierszy.get("cena_aktualna")))));
            obiekt.setIlosc(Integer.parseInt(String.valueOf(tabWierszy.get("ilosc"))));
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
            obiekt.setNazwa(String.valueOf((tabWierszy.get("nazwa"))));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }
    public int addNewAuction(Auction auction,int i,int id){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_WEEK, auction.getDo_konca() );
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        data.getJdbcTemplate().update("INSERT into aukcja (nazwa,opis,cena_aktualna,do_konca,ilosc,id_uzytk,kategoria) values (?,?,?,?,?,?,?)"
                , auction.getNazwa(),auction.getOpis(),auction.getCena_aktualna(),timestamp,auction.getIlosc(),i,id);
        String sql = "select MAX(id_aukcji) from aukcja;";
        return data.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    public void addNewAuctionParam(Parameter param,String kater,int aukcja){
        data.getJdbcTemplate().update("INSERT into podkategoria (id_par,id_auk,opis) values (?,?,?)"
                , param.getId_param(),aukcja,kater);
    }

    public List<Off> getAllOff(int aukcja){
        String query = "select o.id_uzytk,o.data_oferty,u.login from oferta o\n" +
                "LEFT JOIN uzytkownik u on o.id_uzytk = u.id_uzytk\n" +
                "where o.id_aukcji="+aukcja+";";
        List<Off> ListaObiektow = new ArrayList<>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for (Map tabWierszy : wiersze) {
            Off obiekt = new Off();
            obiekt.setId_uzytk(Integer.parseInt(String.valueOf(tabWierszy.get("id_uzytk"))));
            obiekt.setData_oferty(dateFormat.format((tabWierszy.get("data_oferty"))));
            obiekt.setLogin(String.valueOf(tabWierszy.get("login")));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }
    public List<Auction> getAuction(int id){
        String query = "select a.id_aukcji, a.nazwa, a.opis, a.do_konca,a.cena_aktualna,a.ilosc from aukcja a\n" +
                "where a.id_aukcji="+id+";";
        List<Auction> ListaObiektow = new ArrayList<Auction>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for (Map tabWierszy : wiersze) {
            Auction obiekt = new Auction();
            obiekt.setId_aukcji(Integer.parseInt(String.valueOf(tabWierszy.get("id_aukcji"))));
            obiekt.setNazwa(String.valueOf(tabWierszy.get("nazwa")));
            obiekt.setOpis(String.valueOf((tabWierszy.get("opis"))));
            obiekt.setData(dateFormat.format(tabWierszy.get("do_konca")));
            obiekt.setCena_aktualna(Double.parseDouble((String.valueOf(tabWierszy.get("cena_aktualna")))));
            obiekt.setIlosc(Integer.parseInt(String.valueOf(tabWierszy.get("ilosc"))));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }
}
