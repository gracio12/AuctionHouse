package xd.auctionhouse.Entity;

import org.springframework.stereotype.Repository;

/**
 * Created by OpartyOtaczki on 18.08.2017.
 */
@Repository
public class Category {
    private int id_kat;
    private String opis;

    public int getId_kat() {
        return id_kat;
    }

    public void setId_kat(int id_kat) {
        this.id_kat = id_kat;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Category(int id_kat, String opis) {

        this.id_kat = id_kat;
        this.opis = opis;
    }

    public Category() {

    }
}
