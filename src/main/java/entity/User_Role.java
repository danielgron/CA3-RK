/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

/**
 *
 * @author Daniel
 */
@Entity
@NamedQuery(name="User_Role.findbyroleName",
                query="SELECT r FROM User_Role r WHERE r.roleName = :name")
public class User_Role implements Serializable {

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public User_Role() {
    }

    public User_Role(String roleName) {
        this.roleName = roleName;
    }

    private static final long serialVersionUID = 1L;
    @Id
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void getRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public void addUserToRole(User user){
        users.add(user);
    }
    
    public void removeUserFromRole(User user){
        users.remove(user);
    }
}
