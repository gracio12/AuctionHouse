package xd.auctionhouse.Entity;

/**
 * Created by OpartyOtaczki on 15.08.2017.
 */
public class Auction {
    private int id_aukcji;
    private String nazwa;
    private String opis;
    private double cena_aktualna;

    public Auction() {
    }

    public Auction(int id_aukcji, String nazwa, String opis, double cena_aktualna) {
        this.id_aukcji = id_aukcji;
        this.nazwa = nazwa;
        this.opis = opis;
        this.cena_aktualna = cena_aktualna;
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
}
