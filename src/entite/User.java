/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package entite;

import java.util.Date;

public class User {
    private int id;
    private String email;
    private String password;
    private String fullname;
    private String adresse;
    private String num;
    private String image;
    private Date date_inscrit_u;
    private String desc_u;
    private String code;
    private String psudo;
    private String token;
    private String roles; 

    public User() {}

    public User(int id, String email, String password, String fullname, String adresse, String num, String image,
                Date date_inscrit_u, String desc_u, String code, String psudo, String token, String roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.adresse = adresse;
        this.num = num;
        this.image = image;
        this.date_inscrit_u = date_inscrit_u;
        this.desc_u = desc_u;
        this.code = code;
        this.psudo = psudo;
        this.token = token;
        this.roles = roles;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    @Override
    public String toString() {
        return
                "id :" + id +
                ", fullname :" + fullname ;
    }
}
