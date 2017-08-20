package xd.auctionhouse.Entity;

/**
 * Created by OpartyOtaczki on 20.08.2017.
 */
public class Parameter {
    private int id_param;
    private String nazwa;

    public Parameter() {
    }

    public Parameter(int id_param, String nazwa) {
        this.id_param = id_param;
        this.nazwa = nazwa;
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
