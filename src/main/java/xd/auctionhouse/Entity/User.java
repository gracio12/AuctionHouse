package xd.auctionhouse.Entity;

/**
 * Created by OpartyOtaczki on 11.08.2017.
 */
public class User {
    private int id_uzytk;
    private String imie;
    private String nazwisko;
    private String login;
    private String haslo;
    private String email;
    private Boolean admin;

    public int getId_uzytk() {
        return id_uzytk;
    }

    public void setId_uzytk(int id_uzytk) {
        this.id_uzytk = id_uzytk;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public User(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }

    public User(String imie, String nazwisko, String login, String haslo, String email) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.login = login;
        this.haslo = haslo;
        this.email = email;
    }

    public User() {
    }

    public void copyUser(User user) {
        this.imie = user.imie;
        this.nazwisko = user.nazwisko;
        this.login = user.login;
        this.haslo = user.haslo;
        this.email = user.email;
    }
}
