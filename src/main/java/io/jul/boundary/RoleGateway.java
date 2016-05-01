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
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author dieter
 */
@Named
@Stateful
@ConversationScoped
public class RoleGateway {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    private Role role;

    public List<Role> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
        Root<Role> root = cq.from(Role.class);
        TypedQuery<Role> tq = entityManager.createQuery(cq);
        return tq.getResultList();
    }
    
    public void create(){
        role = new Role();
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

    @Remove
    public void save() {
        if (exists(role.getId())) {
            entityManager.merge(role);
        }else{
            entityManager.persist(role);
        }
        role = null;
    }

    private Role find(Long id){
        return entityManager.find(Role.class, id);
    }
    private boolean exists(Long id) {
        Role x = entityManager.find(Role.class, id);        
        return x != null;                        
    }

}
