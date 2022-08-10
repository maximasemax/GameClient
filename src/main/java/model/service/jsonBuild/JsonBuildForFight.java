package model.service.jsonBuild;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import model.impl.FighterConfiguration;

public class JsonBuildForFight {

    public String parserFighter(FighterConfiguration fighterConfiguration) {
        return new Gson().toJson(fighterConfiguration);
    }

    public FighterConfiguration fromJsonFighter(String jasonString){
        return new Gson().fromJson(jasonString, FighterConfiguration.class);
    }
}
