package com.change_vision.astah.lab.plugin.miro.authorization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Authorization {
    private String id;

    private List<String> scopes;

    private Map<String, String> team;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public Map<String, String> getTeam() {
        return team;
    }

    public void setTeam(Map<String, String> team) {
        this.team = team;
    }
}
