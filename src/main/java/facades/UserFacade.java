package facades;

import security.IUserFacade;
import entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import security.IUser;
import security.PasswordStorage;

public class UserFacade implements IUserFacade {

    /*When implementing your own database for this seed, you should NOT touch any of the classes in the security folder
    Make sure your new facade implements IUserFacade and keeps the name UserFacade, and that your Entity User class implements 
    IUser interface, then security should work "out of the box" with users and roles stored in your database */
    EntityManagerFactory emf;
    private final Map<String, IUser> users = new HashMap<>();

    public UserFacade() {
        //Test Users
        if(isDatabaseUsersEmpty()){
        insertTestUsers();
        }
        User user = new User("user", "test");
        user.addRole("User");
        users.put(user.getUserName(), user);
        User admin = new User("admin", "test");
        admin.addRole("Admin");
        users.put(admin.getUserName(), admin);

        User both = new User("user_admin", "test");
        both.addRole("User");
        both.addRole("Admin");
        users.put(both.getUserName(), both);
    }

    @Override
    public IUser getUserByUserId(String id) {
        return users.get(id);
    }

    /*
  Return the Roles if users could be authenticated, otherwise null
     */
    @Override
    public List<String> authenticateUser(String userName, String password) {
        try {
            EntityManager em = getEntityManager();
            TypedQuery<User> q = em.createQuery("select u from User u where u.userName=:name",User.class);
            q.setParameter("name", userName);
            User user = q.getSingleResult();
            if(PasswordStorage.verifyPassword(password, user.getPassword())){
               return user.getRolesAsStrings();
            }
            else{
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("pu_local");
        }
        return emf.createEntityManager();
    }

    private void insertTestUsers() {
        EntityManager em = getEntityManager();
        User user = new User("user", "test");
        user.addRole("User");
        User admin = new User("admin", "test");
        admin.addRole("Admin");

        User both = new User("user_admin", "test");
        both.addRole("User");
        both.addRole("Admin");

    
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.persist(admin);
            em.persist(both);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error!!!" + e.getMessage());
        }
        finally{
            em.close();
        }
    }

    private boolean isDatabaseUsersEmpty() {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("select u from User u ", User.class);
        List<User> us = q.getResultList();
        return us.isEmpty();
    }

}
