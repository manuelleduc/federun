package fr.mleduc.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FederunUser extends PanacheEntity {
    public String login;
    public String email;
    public String password;
    @Column(length = 500)
    public String token;
}
