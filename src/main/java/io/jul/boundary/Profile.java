/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.jul.boundary;

import javax.json.JsonObject;

/**
 *
 * @author dieter
 */
public class Profile {
    
    private Long id;
    private String userid;
    private String fullName;
    private String givenName;
    private String familyName;
    private String email;
    private String image;
    private String profileUrl;

    public String getFullName() {
        return fullName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getUserid() {
        return userid;
    }

    


    public static ProfileBuilder getBuilder(){
        return new ProfileBuilder();
    }

    public static class ProfileBuilder {
        private Profile profile = new Profile();

        public ProfileBuilder buildFromJson(JsonObject json){
            profile.email = json.getString("email");
            profile.fullName = json.getString("name");
            profile.givenName = json.getString("given_name");
            profile.image = json.getString("picture");
            profile.profileUrl = json.getString("link");
            profile.familyName = json.getString("family_name");
            profile.userid = json.getString("id");
            return this;
        }
        public Profile build(){
            return profile;
        }
    }    
}
