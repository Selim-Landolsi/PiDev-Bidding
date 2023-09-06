/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Bid;
import java.util.List;

/**
 *
 * @param <B>
 */
public interface IServiceBid<B> {
    
        
    public void ajouterBid(Bid b);
    public List<Bid> afficherBids();
    public void modifierBid(int id_bid, String type);
    public void supprimerBid(int id_bid);
    public boolean chercherBid(Bid b);
    public List<Bid> displayByID(int id_bid) ;
    public int calculateActiveDuration(int id_listing, int id_user) ;
    public int GetListingBuyoutPrice(int id_listing);
    
}
