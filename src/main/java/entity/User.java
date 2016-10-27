package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import security.IUser;
import security.PasswordStorage;

@Entity
public class User implements IUser, Serializable{
  
  private String password;  //Pleeeeease dont store me in plain text
  @Id
  private String userName;
  List<String> roles = new ArrayList();

//   List<Role> roles = new ArrayList();
    public User() {
    }

  public User(String userName, String password) {
    this.userName = userName;
      try {
          String hashPassword = PasswordStorage.createHash(password);
          this.password = hashPassword;
      } catch (PasswordStorage.CannotPerformOperationException ex) {
          System.out.println("EROROR!!!!");
          this.password = "failed!";
      }
  }
  
  public User(String userName, String password,List<String> roles) {
    this.userName = userName;
    this.password = password;
    this.roles = roles;
//    List<Role> temp = new ArrayList<>();
//      for (String role : roles) {
//          temp.add(new Role(role));
//      }
//     this.roles = temp;
  }
  
  public void addRole(String role){
    roles.add(role);
//    roles.add(new Role(role));
  }
    
  @Override
  public List<String> getRolesAsStrings() {
   return roles;
//   List<String> temp = new ArrayList<>();
//      for (Role role : roles) {
//          temp.add(role.getRoleName());
//      }
//      return temp;
//  }
      }
 
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

 
          
}
