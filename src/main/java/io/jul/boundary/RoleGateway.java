/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jul.boundary;

import io.jul.domain.Role;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author dieter
 */
@Named
@Stateful
@ConversationScoped
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RoleGateway {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private Role role;

    public List<Role> findAll() {        
        Query tq = entityManager.createQuery("select role from Role role", Role.class);
        return tq.getResultList();
    }
    
    public void create(){        
        role = new Role();
        entityManager.persist(role);
    }

    public void edit(Long id) {
        role = entityManager.find(Role.class, id);
    }
    @Remove
    public void delete(Long id){
        entityManager.remove(find(id));
    }

    public Role getCurrent() {
        return role;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void save() {
       
    }

    private Role find(Long id){
        return entityManager.find(Role.class, id);
    }
    

}
