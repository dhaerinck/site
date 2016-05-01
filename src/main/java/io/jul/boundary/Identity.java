/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jul.boundary;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author dieter
 */

@Named
@Stateful
@SessionScoped
public class Identity implements Serializable{
    
    private String token;
    private Profile profile;  
    private final static String CLIENT_KEY = "251955809562-ckcvfv17t697ieav8mf0u571jjqb0s4e.apps.googleusercontent.com";
    private final static String CLIENT_SECRET = "cPoaVm0fg10zR4uVxqx43-4w";
    private final static String RETURN_URL = "http://localhost:8080/GradleWeb/userinfo.xhtml";

    private final OAuth20Service service = new ServiceBuilder()
            .apiKey(CLIENT_KEY)
            .apiSecret(CLIENT_SECRET)
            .callback(RETURN_URL)
            .scope("https://www.googleapis.com/auth/userinfo.email")
            .build(GoogleApi20.instance());

    public Profile getProfile() {
        return profile;
    }
    public boolean isAdmin(){
        return true;
    }
    public void redirectToGoogleAuthorization() throws IOException  {
        String url = service.getAuthorizationUrl();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }


    private Profile authenticationCallback() {
        profile = Profile.getBuilder().buildFromJson(sendRequest()).build();
        return profile;
    }

    public JsonObject sendRequest(){
        OAuthRequest req = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v2/userinfo?alt=json", service);
        service.signRequest(service.refreshAccessToken(token), req);
        Response res = req.send();
        JsonReader reader = Json.createReader(new ByteArrayInputStream(
                res.getBody().getBytes()));        
        return reader.readObject();
    }     
    public void setCode(String code) {
        if(token != null || code == null || code.isEmpty())return;
        token = service.getAccessToken(code).getAccessToken();
        authenticationCallback();
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "/index.xhtml");
    }
   
}