/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jul.boundary;

import io.jul.domain.News;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 *
 * @author dieter
 */
@Stateful
@ConversationScoped
@Named
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class NewsGateway extends Gateway<News>{
    
    @PostConstruct
    public void init(){
        this.clazz = News.class;        
        create();
    }
        
}
