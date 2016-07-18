package io.jul.boundary;

import io.jul.domain.Role;
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
@Named
@Stateful
@ConversationScoped
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RoleGateway  extends Gateway<Role>{
    @PostConstruct
    public void init(){
        this.clazz = Role.class;
        create();
    }
    
    
}
