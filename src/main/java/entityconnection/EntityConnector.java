/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityconnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Daniel
 */
public class EntityConnector {
    
    private static EntityManagerFactory emf;
    private static final String persistanceUnit = "pu_local";
    
    private EntityConnector(){
    }
    
    public static EntityManager getEntityManager(){
        if(emf == null){
            emf = Persistence.createEntityManagerFactory(persistanceUnit);
        }
        return emf.createEntityManager();
    }
    
    public static void createEntityManagerFactory(){
        emf = Persistence.createEntityManagerFactory(persistanceUnit);
    }
    
}
