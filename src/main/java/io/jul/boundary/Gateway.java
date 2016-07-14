package io.jul.boundary;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author dieter
 */

public abstract class Gateway<T> {
       

    @PersistenceContext(type =PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
    protected Class<T> clazz;
    
    
    protected T current;
    
    public List<T> findAll(){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);        
        return getEntityManager().createQuery(cq).getResultList();
    }
    public void create(){
        try{
            current = clazz.newInstance();        
            getEntityManager().persist(current);
        }catch(Exception e){
            Logger.getLogger("Gateway").log(Level.SEVERE, "error creating new instance of class" + clazz);            
        }
    }
   
    public void edit(Long id){
        current = getEntityManager().find(clazz, id);
    }
    public void delete(){
        getEntityManager().remove(current);
    }
       
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void save(){
        
    }

    private T find(Long id) {
        return getEntityManager().find(clazz, id);
    }
    

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }
    
    
}
