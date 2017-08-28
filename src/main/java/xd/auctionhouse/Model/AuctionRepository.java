package xd.auctionhouse.Model;

import org.springframework.stereotype.Repository;
import xd.auctionhouse.Entity.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * Created by OpartyOtaczki on 15.08.2017.
 */
@Repository
public class AuctionRepository {

    private DatabaseConnection data;


    public AuctionRepository(DatabaseConnection databaseConnection) {
        this.data = databaseConnection;
    }

    public List<Auction> getAllAuction() {
        String query = "select id_aukcji, nazwa, opis, cena_aktualna,obrazek from aukcja;";
        List<Auction> ListaObiektow = new ArrayList<Auction>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        for (Map tabWierszy : wiersze) {
            Auction obiekt = new Auction();
            obiekt.setId_aukcji(Integer.parseInt(String.valueOf(tabWierszy.get("id_aukcji"))));
            obiekt.setNazwa(String.valueOf(tabWierszy.get("nazwa")));
            obiekt.setOpis(String.valueOf((tabWierszy.get("opis"))));
            obiekt.setCena_aktualna(Double.parseDouble((String.valueOf(tabWierszy.get("cena_aktualna")))));
            obiekt.setObrazek(String.valueOf(tabWierszy.get("obrazek")));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }

    public List<Auction> getAllSell(int g) {
        String query = "select id_aukcji, nazwa, opis, do_konca,cena_aktualna,obrazek from aukcja where id_uzytk=" + g + ";";
        List<Auction> ListaObiektow = new ArrayList<Auction>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        for (Map tabWierszy : wiersze) {
            Auction obiekt = new Auction();
            obiekt.setId_aukcji(Integer.parseInt(String.valueOf(tabWierszy.get("id_aukcji"))));
            obiekt.setNazwa(String.valueOf(tabWierszy.get("nazwa")));
            obiekt.setOpis(String.valueOf((tabWierszy.get("opis"))));
            obiekt.setData(String.valueOf((tabWierszy.get("do_konca"))));
            obiekt.setCena_aktualna(Double.parseDouble((String.valueOf(tabWierszy.get("cena_aktualna")))));
            obiekt.setObrazek(String.valueOf(tabWierszy.get("obrazek")));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }

    public List<Auction> getAllBuy(int g) {
        String query = "select a.id_aukcji, a.nazwa, a.opis, a.do_konca,a.cena_aktualna,a.ilosc,a.obrazek from aukcja a\n" +
                "JOIN oferta o ON o.id_aukcji=a.id_aukcji\n" +
                "where o.id_uzytk=" + g + ";";
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
            obiekt.setObrazek(String.valueOf(tabWierszy.get("obrazek")));
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
        String query = "select id_param,nazwa from parametr where id_kat=" + id + ";";
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

    public List<Off> getAllCateg(int aukcji) {
//        String sql = "select kategoria from aukcja where id_aukcji="+aukcji+";";
//        int id_kat=data.getJdbcTemplate().queryForObject(sql, Integer.class);
        String query = "select po.nazwa,p.opis from podkategoria p\n" +
                "LEFT JOIN parametr po on p.id_par = po.id_param\n" +
                "where id_auk=" + aukcji + ";";
        List<Off> ListaObiektow = new ArrayList<Off>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        for (Map tabWierszy : wiersze) {
            Off obiekt = new Off();
//            obiekt.setId_oferty(Integer.parseInt(String.valueOf(tabWierszy.get("id_param"))));
            obiekt.setData_oferty(String.valueOf((tabWierszy.get("nazwa"))));
            obiekt.setLogin(String.valueOf((tabWierszy.get("opis"))));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }



    public int addNewAuction(Auction auction, int i, int id) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_WEEK, auction.getDo_konca());
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        data.getJdbcTemplate().update("INSERT into aukcja (nazwa,opis,cena_aktualna,do_konca,ilosc,id_uzytk,kategoria,obrazek,licytacja) values (?,?,?,?,?,?,?,?,?)"
                , auction.getNazwa(), auction.getOpis(), auction.getCena_aktualna(), timestamp, auction.getIlosc(), i, id,auction.getObrazek(),auction.isLicytacja());
        String sql = "select MAX(id_aukcji) from aukcja;";
        return data.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    public void addNewAuctionParam(Parameter param, String kater, int aukcja) {
        data.getJdbcTemplate().update("INSERT into podkategoria (id_par,id_auk,opis) values (?,?,?)"
                , param.getId_param(), aukcja, kater);
    }

    public List<Off> getAllOff(int aukcja) {
        String query = "select o.id_uzytk,o.data_oferty,u.login,o.kwota,o.ilosc from oferta o\n" +
                "LEFT JOIN uzytkownik u on o.id_uzytk = u.id_uzytk\n" +
                "where o.id_aukcji=" + aukcja + ";";
        List<Off> ListaObiektow = new ArrayList<>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for (Map tabWierszy : wiersze) {
            Off obiekt = new Off();
            obiekt.setId_uzytk(Integer.parseInt(String.valueOf(tabWierszy.get("id_uzytk"))));
            obiekt.setData_oferty(dateFormat.format((tabWierszy.get("data_oferty"))));
            obiekt.setLogin(String.valueOf(tabWierszy.get("login")));
            obiekt.setKwota(Double.parseDouble((String.valueOf(tabWierszy.get("kwota")))));
            obiekt.setIlosc(Integer.parseInt(String.valueOf(tabWierszy.get("ilosc"))));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }

    public List<Auction> getAuction(int id) {
        String query = "select a.id_aukcji, a.nazwa, a.opis, a.do_konca,a.cena_aktualna,a.ilosc,a.zakonczona,a.licytacja,a.obrazek from aukcja a\n" +
                "where a.id_aukcji=" + id + ";";
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
            obiekt.setZakonczona(Boolean.parseBoolean(String.valueOf(tabWierszy.get("zakonczona"))));
            obiekt.setLicytacja(Boolean.parseBoolean(String.valueOf(tabWierszy.get("licytacja"))));
            obiekt.setObrazek(String.valueOf(tabWierszy.get("obrazek")));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }

    public void addNewOffer(int id_uzytk, int ilosc, int id_aukcji) {
        String sql = "select ilosc from aukcja where id_aukcji=" + id_aukcji + ";";
        int ilosc2 = data.getJdbcTemplate().queryForObject(sql, Integer.class);
        int roznica = ilosc2 - ilosc;
        if (roznica > 0) {
            sql = "insert into oferta (id_uzytk, id_aukcji, ilosc) VALUES (?,?,?)";
            data.getJdbcTemplate().update(sql, id_uzytk, id_aukcji, ilosc);
            sql = "update aukcja set ilosc=? where id_aukcji=?";
            data.getJdbcTemplate().update(sql, roznica, id_aukcji);
        } else if (roznica == 0) {
            sql = "insert into oferta (id_uzytk, id_aukcji, ilosc) VALUES (?,?,?)";
            data.getJdbcTemplate().update(sql, id_uzytk, id_aukcji, ilosc);
            sql = "update aukcja set ilosc=? where id_aukcji=?";
            data.getJdbcTemplate().update(sql, roznica, id_aukcji);
            sql = "update aukcja set zakonczona=? where id_aukcji=?";
            data.getJdbcTemplate().update(sql, true, id_aukcji);
        } else if (roznica < 0) {
        }

    }

    public void addNewLic(int id_uzytk,int ilosc,double kwota,int id_aukcji){
        double max_kwota;
        String sql = "select max(kwota) from oferta where id_aukcji="+id_aukcji+";";
        String max_kwotaa = data.getJdbcTemplate().queryForObject(sql, String.class);
        if(max_kwotaa==null){max_kwota=1;}else{
        sql = "select max(kwota) from oferta where id_aukcji="+id_aukcji+";";
        max_kwota = data.getJdbcTemplate().queryForObject(sql, Double.class);}


        if(kwota>=max_kwota){
            sql = "update aukcja set cena_aktualna=? where id_aukcji=?";
            data.getJdbcTemplate().update(sql, kwota, id_aukcji);

            sql="select\n" +
                    "  CASE when\n" +
                    "    exists(SELECT *\n" +
                    "           from oferta\n" +
                    "           where id_aukcji="+id_aukcji+"\n" +
                    "                 and id_uzytk="+id_uzytk+")\n" +
                    "    then '1' else '0'\n" +
                    "  end;";
            int k=data.getJdbcTemplate().queryForObject(sql, int.class);




            if(k==1){
                sql = "update oferta set kwota=? where id_aukcji=? and id_uzytk=?";
                data.getJdbcTemplate().update(sql, kwota, id_aukcji,id_uzytk);
            }
            else{
                sql = "insert into oferta (id_uzytk, id_aukcji, kwota) VALUES (?,?,?)";
                data.getJdbcTemplate().update(sql, id_uzytk, id_aukcji, kwota);

            }
        }

    }

    public String getObrazek(int aukcja){
        String sql = "select obrazek from aukcja where id_aukcji="+aukcja+";";
        String obrazek = data.getJdbcTemplate().queryForObject(sql, String.class);
        return obrazek;
    }
    public List<Off> getAllOff2(int aukcja) {
        String query = "select o.id_uzytk,o.data_oferty,u.login,o.kwota,o.ilosc from oferta o\n" +
                "LEFT JOIN uzytkownik u on o.id_uzytk = u.id_uzytk\n" +
                "where o.id_aukcji=" + aukcja + " ORDER BY o.kwota DESC;";
        List<Off> ListaObiektow = new ArrayList<>();
        List<Map<String, Object>> wiersze = data.getJdbcTemplate().queryForList(query);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        for (Map tabWierszy : wiersze) {
            Off obiekt = new Off();
            obiekt.setId_uzytk(Integer.parseInt(String.valueOf(tabWierszy.get("id_uzytk"))));
            obiekt.setData_oferty(dateFormat.format((tabWierszy.get("data_oferty"))));
            obiekt.setLogin(String.valueOf(tabWierszy.get("login")));
            obiekt.setKwota(Double.parseDouble((String.valueOf(tabWierszy.get("kwota")))));
            obiekt.setIlosc(Integer.parseInt(String.valueOf(tabWierszy.get("ilosc"))));
            ListaObiektow.add(obiekt);
        }
        return ListaObiektow;
    }
    public void updateZakonczenia(){
        String sql="update aukcja set zakonczona=true where now()>do_konca;";
        data.getJdbcTemplate().update(sql);
    }

}
