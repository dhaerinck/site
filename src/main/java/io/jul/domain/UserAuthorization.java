/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jul.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author dieter
 */
@Entity
public class UserAuthorization {
    @Id
    @GeneratedValue
    private Long id;
    
    private String userid;    
       
    @OneToMany
    private List<Role> roles = new ArrayList<Role>();
    
    
    public void setUserid(String userid){
        this.userid = userid;
    }
    
    public void addRole(Role role){
        roles.add(role);
    }
    public void removeRole(Role role){
        roles.remove(role);
    }
    
    public boolean has(Role role){
        return roles.contains(role);
    }
}
