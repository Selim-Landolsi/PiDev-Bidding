/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Bid;
import entite.User;
import entite.Listing;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;


public class ServiceBid implements IServiceBid<Bid> {
    
    
    private Connection conn;
    PreparedStatement ste;

    public ServiceBid() {
         conn = DataSource.getInstance().getCnx();
    }

    public int calculateActiveDuration(int id_listing, int id_bid) {
        String req = "SELECT DATEDIFF((SELECT exp_date FROM listing WHERE id=?), (SELECT entry_date FROM bid WHERE id_bid=?))";

        int active_duration = 0;
        try {
            ste = conn.prepareStatement(req);
            ste.setInt(1, id_listing);
            ste.setInt(2, id_bid);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
                active_duration = rs.getInt(1);
            }
            rs.close();
            ste.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return active_duration;
    }
 // ---------------------------------- Ajouter Bid ---------------------------------------//
    
    public void ajouterBid(Bid b) {
        
        String sql ="insert into bid (id_user,id_listing,bid_amount,type) Values(?,?,?,?)";
        
        try
        {
           ste=conn.prepareStatement(sql);
           ste.setInt(1, b.getId_user());
           ste.setInt (2,b.getId_listing());
           ste.setInt(3, b.getBid_amount());
           ste.setString(4, b.getType());
           ste.executeUpdate();
           System.out.println("Bid Effectué");
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }         
    }
    
    
        //--------------------------------------- Afficher Bid ------------------------------------------------//
    
    public List<Bid> afficherBids() {
        
     List<Bid> bids =  new ArrayList<>();

      String sql="select * from bid";
      
      try
      {
          ste=conn.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                      Bid b = new Bid();
                      b.setId_bid(rs.getInt("id_bid"));
                      b.setId_user(rs.getInt("id_user"));
                      b.setId_listing(rs.getInt("id_listing"));
                      b.setBid_amount(rs.getInt("bid_amount"));
                      b.setEntry_date(rs.getDate("entry_date"));
                      b.setEntry_time(rs.getTime("entry_time"));
                      b.setActive_duration(calculateActiveDuration(rs.getInt("id_listing"),rs.getInt("id_bid") ));
                      b.setType(rs.getString("type"));
                      
                      bids.add(b) ;
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return bids;
    }
    //--------------------------------------- Afficher user ------------------------------------------------//
    
    public List<String> afficherUsers() {
        
     List<String> Users =  new ArrayList<>();

      String sql="select id,fullname from user";
      
      try
      {
          ste=conn.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                      User U = new User();
                      U.setId(rs.getInt("id"));
                      U.setFullname(rs.getString("fullname"));
                    
                      Users.add(U.toString()) ;
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return Users;
    }
    //--------------------------------------- Afficher user ------------------------------------------------//
    
    public List<String> afficherListings() {
        
     List<String> Listings =  new ArrayList<>();

      String sql="select id,name from listing";
      
      try
      {
          ste=conn.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                      Listing L = new Listing();
                      L.setId(rs.getInt("id"));
                      L.setName(rs.getString("name"));
                    
                      Listings.add(L.toString()) ;
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return Listings;
    }
 // ---------------------------------- Modifier Bid ---------------------------------------//

    
    @Override
    public void modifierBid(int id_bid, String type) {
        
       String sql= "UPDATE bid SET type=?  where id_bid =?";
       try{
          ste = conn.prepareStatement(sql);
          

           ste.setString (1,type);
           ste.setInt(2, id_bid);
          
          ste.execute();
          
           System.out.println(" Bid modifiée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
      
        }
       
       
       
       
        
        
        
    }
 // ---------------------------------- Supprimer Bid ---------------------------------------//

    
    public void supprimerBid(int id_bid) {
       String sql = "DELETE from bid where id_bid = '"+id_bid+"' ";
        try{
           Statement st= conn.createStatement();
           st.executeUpdate(sql);
           System.out.println("Bid supprimée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
 // ---------------------------------- chercher Bid ---------------------------------------//

    public boolean chercherBid(Bid b) {
        boolean x=false;
        Statement ste= null;
        try {
            ste = conn.createStatement();

        ResultSet rs = ste.executeQuery("SELECT * FROM bid WHERE id_user = " + b.getId_user() + " AND id_listing = " + b.getId_listing() + " AND bid_amount > " + HighestBid(b.getId_listing()) );

        if (rs.next()) {

            System.out.println("Bid found:");
            System.out.println("id_bid: " + rs.getInt("id_bid"));
            System.out.println("id_user: " + rs.getInt("id_user"));
            System.out.println("id_listing: " + rs.getInt("id_listing"));
            System.out.println("bid_amount: " + rs.getInt("bid_amount"));
            x=true;
        } else {
            System.out.println("Bid not found.");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return x;

    }

    
    public List<Bid> displayByID(int id_user) {
        
            List <Bid> myList= new ArrayList<>();

    String req="select * from bid where id_user='"+id_user+"' ";

    try {
        Statement st = conn.createStatement();
        ResultSet rs=st.executeQuery(req);
        while(rs.next()){
        
                      Bid b = new Bid();
                      b.setId_bid(rs.getInt("id_bid"));
                      b.setId_user(rs.getInt("id_user"));
                      b.setId_user(rs.getInt("id_listing"));
                      b.setBid_amount(rs.getInt("bid_amount"));
                      b.setEntry_date( rs.getDate("entry_date"));
                      b.setEntry_time( rs.getTime("entry_time"));
                      b.setType(rs.getString("type"));
                      myList.add(b);

        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return myList;
        
        
    }



    public int HighestBid(int id_listing){
        int highestBid = 0;
        String req = "SELECT MAX(bid_amount) AS highest_bid FROM bid WHERE id_listing = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, id_listing);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                highestBid = rs.getInt("highest_bid");
                System.out.println(highestBid);}
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return highestBid;
    }


    public int GetListingBuyoutPrice(int id_listing){
        int buyout=0;
        ResultSet rs = null;
    String req ="select buyout_price from listing where id = '"+id_listing+"' ";
        try {
            ste = conn.prepareStatement("SELECT buyout_price FROM listing WHERE id = ?");
            ste.setInt(1, id_listing);
            rs = ste.executeQuery();

            if (rs.next()) {
                buyout = rs.getInt("buyout_price");
        }
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());

    }return buyout;
}

public boolean Bought_out(int id_listing) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    boolean hasBuyout = false;
    try {

        // prepare the SQL statement
        stmt = conn.prepareStatement("SELECT * FROM bid WHERE id_listing = ? AND type = 'buyout'");
        stmt.setInt(1, id_listing);

        // execute the SQL statement
        rs = stmt.executeQuery();

        // check if any rows were returned
        hasBuyout = rs.next();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return hasBuyout;
}


}
 
        
        
    

 

