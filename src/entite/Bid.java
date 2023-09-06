
package entite;
import java.sql.Date;
import java.sql.Time;


public class Bid  {
    private int id_bid;
    private int id_user;
    private int id_listing;
    private int bid_amount;
    private Date entry_date;
    private Time entry_time;
    private int active_duration;
    private String type;


    public Bid() {
    }

    public Bid(int id_user, int id_listing, int bid_amount, String type) {
        this.id_user = id_user;
        this.id_listing = id_listing;
        this.bid_amount = bid_amount;
        this.type = type;
    }
    public Bid(int bid_amount, String type) {
        this.bid_amount = bid_amount;
        this.type = type;
    }
    

    public int getId_bid() {
        return id_bid;
    }

    public int getBid_amount() {
        return bid_amount;
    }

    public String getType() {
        return type;
    }

    public void setId_bid(int id_bid) {
        this.id_bid = id_bid;
    }

    public void setBid_amount(int bid_amount) {
        this.bid_amount = bid_amount;
    }
    
    public Date getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public Time getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Time entry_time) {
        this.entry_time = entry_time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_listing() {
        return id_listing;
    }

    public void setId_listing(int id_listing) {
        this.id_listing = id_listing;
    }

    public int getActive_duration() {
        return active_duration;
    }

    public void setActive_duration(int active_duration) {
        this.active_duration = active_duration;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id_bid=" + id_bid +
                ", id_user=" + id_user +
                ", id_listing=" + id_listing +
                ", bid_amount=" + bid_amount +
                ", entry_date=" + entry_date +
                ", entry_time=" + entry_time +
                ", type='" + type + '\'' +
                '}';
    }


}
