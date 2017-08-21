package xd.auctionhouse.Entity;

import java.util.Date;

/**
 * Created by OpartyOtaczki on 15.08.2017.
 */
public class Auction {
    private int id_aukcji;
    private String nazwa;
    private String opis;
    private double cena_aktualna;
    private int do_konca;
    private boolean zakonczona;
    private int ilosc;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Auction() {
    }

    public Auction(int id_aukcji, String nazwa, String opis, double cena_aktualna, int do_konca, boolean zakonczona, int ilosc, String data) {
        this.id_aukcji = id_aukcji;
        this.nazwa = nazwa;
        this.opis = opis;
        this.cena_aktualna = cena_aktualna;
        this.do_konca = do_konca;
        this.zakonczona = zakonczona;
        this.ilosc = ilosc;
        this.data = data;
    }

    public int getId_aukcji() {

        return id_aukcji;
    }

    public void setId_aukcji(int id_aukcji) {
        this.id_aukcji = id_aukcji;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena_aktualna() {
        return cena_aktualna;
    }

    public void setCena_aktualna(double cena_aktualna) {
        this.cena_aktualna = cena_aktualna;
    }

    public int getDo_konca() {
        return do_konca;
    }

    public void setDo_konca(int do_konca) {
        this.do_konca = do_konca;
    }

    public boolean isZakonczona() {
        return zakonczona;
    }

    public void setZakonczona(boolean zakonczona) {
        this.zakonczona = zakonczona;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
}
