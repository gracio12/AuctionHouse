package xd.auctionhouse.Entity;

/**
 * Created by OpartyOtaczki on 25.08.2017.
 */
public class Podkategoria {
    private int id_podk;
    private int id_par;
    private String opis;
    private int id_auk;

    public int getId_podk() {
        return id_podk;
    }

    public void setId_podk(int id_podk) {
        this.id_podk = id_podk;
    }

    public int getId_par() {
        return id_par;
    }

    public void setId_par(int id_par) {
        this.id_par = id_par;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getId_auk() {
        return id_auk;
    }

    public void setId_auk(int id_auk) {
        this.id_auk = id_auk;
    }

    public Podkategoria(int id_podk, int id_par, String opis, int id_auk) {

        this.id_podk = id_podk;
        this.id_par = id_par;
        this.opis = opis;
        this.id_auk = id_auk;
    }

    public Podkategoria() {

    }
}
