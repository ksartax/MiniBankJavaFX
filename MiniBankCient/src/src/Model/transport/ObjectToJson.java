/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model.transport;

import java.io.IOException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import src.Model.Entity.User;

/**
 *
 * @author Damian Stępniak
 */
public class ObjectToJson<T> {

    private ObjectMapper mapper;

    public ObjectToJson() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public String objectToJson(T object) throws IOException {
        return mapper.writeValueAsString(object);
    }
}
