package com.shermanventures.offerwalltest;

import com.google.gson.Gson;

/**
 * Created by aris on 15/12/2014.
 */
public class model {

    private String clientId;

    private String registrationId;

    private String campaingId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCampaingId() {
        return campaingId;
    }

    public void setCampaingId(String campaingId) {
        this.campaingId = campaingId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String serializeObject(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    private static model deserialie(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, model.class);
    }
}
