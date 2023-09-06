/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

/**
 *
 * @author ASUS
 */
public class Listing {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Listing(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Listing() {
    }
    @Override
    public String toString() {
        return
                "id :" + id +
                ", name :" + name ;
    }
}
