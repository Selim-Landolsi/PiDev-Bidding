package entite;

import java.sql.Date;
import java.sql.Timestamp;

public class Exchange {
    private int id_exchange;
    private int id_listing_1;
    private int id_listing_2;
    private int id_user_1;
    private int id_user_2;
    private String offer_status;
    private Date date_created;
    private String value_diff;

    public Exchange() {}

    public Exchange(int id_listing_1, int id_listing_2, int id_user_1, int id_user_2, String offer_status,String value_diff) {

        this.id_listing_1 = id_listing_1;
        this.id_listing_2 = id_listing_2;
        this.id_user_1 = id_user_1;
        this.id_user_2 = id_user_2;
        this.offer_status = offer_status;
        this.value_diff = value_diff;

    }

    public Exchange(int id_listing_1, int id_listing_2, int id_user_1, int id_user_2, String offer_status) {

        this.id_listing_1 = id_listing_1;
        this.id_listing_2 = id_listing_2;
        this.id_user_1 = id_user_1;
        this.id_user_2 = id_user_2;
        this.offer_status = offer_status;

    }



    public int getId_exchange() {
        return id_exchange;
    }

    public void setId_exchange(int id_exchange) {
        this.id_exchange = id_exchange;
    }

    public int getId_listing_1() {
        return id_listing_1;
    }

    public void setId_listing_1(int id_listing_1) {
        this.id_listing_1 = id_listing_1;
    }

    public int getId_listing_2() {
        return id_listing_2;
    }

    public void setId_listing_2(int id_listing_2) {
        this.id_listing_2 = id_listing_2;
    }

    public int getId_user_1() {
        return id_user_1;
    }

    public void setId_user_1(int id_user_1) {
        this.id_user_1 = id_user_1;
    }

    public int getId_user_2() {
        return id_user_2;
    }

    public void setId_user_2(int id_user_2) {
        this.id_user_2 = id_user_2;
    }

    public String getOffer_status() {
        return offer_status;
    }

    public void setOffer_status(String offer_status) {
        this.offer_status = offer_status;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getValue_diff() {
        return value_diff;
    }

    public void setValue_diff(String value_diff) {
        this.value_diff = value_diff;
    }


    @Override
    public String toString() {
        return "Exchange{" +
                "id_exchange=" + id_exchange +
                ", id_listing_1=" + id_listing_1 +
                ", id_listing_2=" + id_listing_2 +
                ", id_user_1=" + id_user_1 +
                ", id_user_2=" + id_user_2 +
                ", offer_status='" + offer_status + '\'' +
                ", date_created=" + date_created +
                ", value_diff=" + value_diff +
                '}';
    }
}
