package xd.auctionhouse.Entity;

/**
 * Created by OpartyOtaczki on 20.08.2017.
 */
public class Parameter {
    private int id_param;
    private String nazwa;
    private int id_kat;
    private int id_aukcji;

    public int getId_kat() {
        return id_kat;
    }

    public void setId_kat(int id_kat) {
        this.id_kat = id_kat;
    }

    public int getId_aukcji() {
        return id_aukcji;
    }

    public void setId_aukcji(int id_aukcji) {
        this.id_aukcji = id_aukcji;
    }

    public Parameter(int id_param, String nazwa, int id_kat, int id_aukcji) {

        this.id_param = id_param;
        this.nazwa = nazwa;
        this.id_kat = id_kat;
        this.id_aukcji = id_aukcji;
    }

    public Parameter() {
    }

    public int getId_param() {
        return id_param;
    }

    public void setId_param(int id_param) {
        this.id_param = id_param;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
