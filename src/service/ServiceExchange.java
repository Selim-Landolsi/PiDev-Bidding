/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Exchange;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;
import static java.lang.Math.abs;

public class ServiceExchange implements IServiceExchange<Exchange>{
    private Connection conn;
    PreparedStatement ste;
    public ServiceExchange() {
        conn = DataSource.getInstance().getCnx();
    }



    public void ajouterExchange(Exchange exchange) {
        try {
         ste = conn.prepareStatement(
                "INSERT INTO exchange (id_listing_1, id_listing_2, id_user_1, id_user_2, offer_status, date_created) VALUES (?, ?, ?, ?, ?, ?)"
        );


        ste.setInt(1, exchange.getId_listing_1());
        ste.setInt(2, exchange.getId_listing_2());
        ste.setInt(3, exchange.getId_user_1());
        ste.setInt(4, exchange.getId_user_2());
        ste.setString(5, exchange.getOffer_status());
        ste.setDate(6, exchange.getDate_created());


            ste.executeUpdate();
            ste.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Read a single exchange by ID
    public Exchange getExchangeById(int exchangeId)  {
        Exchange ex = new Exchange();
        try {
            ste = conn.prepareStatement(
                    "SELECT * FROM exchange WHERE id_exchange = ?"
            );

        ste.setInt(1, exchangeId);
        ResultSet rs = ste.executeQuery();

        if (!rs.next()) {
            return null;
        }

        Exchange exchange = new Exchange(

                rs.getInt("id_listing_1"),
                rs.getInt("id_listing_2"),
                rs.getInt("id_user_1"),
                rs.getInt("id_user_2"),
                rs.getString("offer_status"),
                CalculateValueDiff(rs.getInt("id_listing_1"),rs.getInt("id_listing_2"))
        );
            ex = exchange;
        ste.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ex;

    }


    public List<Exchange> afficherExchange() throws SQLException {
        ste = conn.prepareStatement(
                "SELECT * FROM exchange"
        );
        ResultSet rs = ste.executeQuery();
        List<Exchange> exchanges = new ArrayList<>();

        while (rs.next()) {
            Exchange exchange = new Exchange(

                    rs.getInt("id_listing_1"),
                    rs.getInt("id_listing_2"),
                    rs.getInt("id_user_1"),
                    rs.getInt("id_user_2"),
                    rs.getString("offer_status"),
                    CalculateValueDiff(rs.getInt("id_listing_1"),rs.getInt("id_listing_2"))
            );
            exchanges.add(exchange);
        }

        ste.close();
        return exchanges;
    }


    public void modifierExchange(Exchange exchange) throws SQLException {
        ste = conn.prepareStatement(
                "UPDATE exchange SET id_listing_1 = ?, id_listing_2 = ?, id_user_1 = ?, id_user_2 = ?, offer_status = ?, date_created = ?, value_1 = ?, value_2 = ? WHERE id_exchange = ?"
        );
        ste.setInt(1, exchange.getId_listing_1());
        ste.setInt(2, exchange.getId_listing_2());
        ste.setInt(3, exchange.getId_user_1());
        ste.setInt(4, exchange.getId_user_2());
        ste.setString(5, exchange.getOffer_status());
        ste.setDate(6, exchange.getDate_created());
        ste.setInt(7, exchange.getId_exchange());
        ste.executeUpdate();
        ste.close();
    }


    public void supprimerExchange(int exchangeId) throws SQLException {
        ste = conn.prepareStatement(
                "DELETE FROM exchange WHERE id_exchange = ?"
        );
        ste.setInt(1, exchangeId);
        ste.executeUpdate();
        ste.close();
    }

    //


    public String CalculateValueDiff(int id_listing_1,int id_listing_2) throws SQLException{
        int value_diff;
        String VD;
        ste = conn.prepareStatement("SELECT inital_value FROM listing where id_listing= ?");
        ste.setInt(1, id_listing_1);
        ResultSet rs1 = ste.executeQuery();
        ste = conn.prepareStatement("SELECT inital_value FROM listing where id_listing= ?");
        ste.setInt(1, id_listing_2);
        ResultSet rs2 = ste.executeQuery();
        value_diff = Integer.parseInt(String.valueOf(rs1)) - Integer.parseInt(String.valueOf(rs2));
        if(value_diff >0){
            VD ="listing 1 is " + abs(value_diff) + "dt more";
        }
        else {
            VD ="listing 2 is " + abs(value_diff) + "dt more";
        }
        return VD ;
    }


}
