/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entite.Exchange;

import java.sql.SQLException;
import java.util.List;



public interface IServiceExchange<E> {
    
    public void ajouterExchange(Exchange p);
    public List<Exchange> afficherExchange() throws SQLException;
    public void modifierExchange (Exchange p) throws SQLException;
    public void supprimerExchange(int id_p) throws SQLException;
    public Exchange getExchangeById(int exchangeId);
    //public void addExchangeOffer(int id_listing_1, int id_listing_2, int user_id_1, int user_id_2) throws SQLException;
    
}
