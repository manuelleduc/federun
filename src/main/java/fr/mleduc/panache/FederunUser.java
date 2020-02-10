package fr.mleduc.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class FederunUser extends PanacheEntity {
    public String login;
    public String password;
    public String role;
}
