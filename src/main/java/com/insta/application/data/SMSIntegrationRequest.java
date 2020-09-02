
package com.insta.application.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sender",
    "route",
    "country",
    "sms"
})
public class SMSIntegrationRequest {

    @JsonProperty("sender")
    private String sender;
    @JsonProperty("route")
    private String route;
    @JsonProperty("country")
    private String country;
    @JsonProperty("sms")
    private List<SMSData> sms = null;
    
    @JsonProperty("sender")
    public String getSender() {
        return sender;
    }

    @JsonProperty("sender")
    public void setSender(String sender) {
        this.sender = sender;
    }

    @JsonProperty("route")
    public String getRoute() {
        return route;
    }

    @JsonProperty("route")
    public void setRoute(String route) {
        this.route = route;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("sms")
    public List<SMSData> getSms() {
        return sms;
    }

    @JsonProperty("sms")
    public void setSms(List<SMSData> sms) {
        this.sms = sms;
    }

   
}
