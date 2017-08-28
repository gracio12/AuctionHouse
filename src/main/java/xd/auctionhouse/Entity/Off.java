package xd.auctionhouse.Entity;

import java.security.Timestamp;
import java.sql.Date;

/**
 * Created by OpartyOtaczki on 25.08.2017.
 */
public class Off {
    private int id_oferty;
    private int id_uzytk;
    private int id_aukcji;
    private String data_oferty;
    private String login;
    private double kwota;
    private int ilosc;
    private String najwieksza;

    public String getNajwieksza() {
        return najwieksza;
    }

    public void setNajwieksza(String najwieksza) {
        this.najwieksza = najwieksza;
    }

    public int getId_oferty() {
        return id_oferty;
    }

    public void setId_oferty(int id_oferty) {
        this.id_oferty = id_oferty;
    }

    public int getId_uzytk() {
        return id_uzytk;
    }

    public void setId_uzytk(int id_uzytk) {
        this.id_uzytk = id_uzytk;
    }

    public int getId_aukcji() {
        return id_aukcji;
    }

    public void setId_aukcji(int id_aukcji) {
        this.id_aukcji = id_aukcji;
    }

    public String getData_oferty() {
        return data_oferty;
    }

    public void setData_oferty(String data_oferty) {
        this.data_oferty = data_oferty;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public Off(int id_oferty, int id_uzytk, int id_aukcji, String data_oferty, String login, double kwota, int ilosc, String najwieksza) {
        this.id_oferty = id_oferty;
        this.id_uzytk = id_uzytk;
        this.id_aukcji = id_aukcji;
        this.data_oferty = data_oferty;
        this.login = login;
        this.kwota = kwota;
        this.ilosc = ilosc;
        this.najwieksza = najwieksza;
    }

    public Off() {

    }
}
