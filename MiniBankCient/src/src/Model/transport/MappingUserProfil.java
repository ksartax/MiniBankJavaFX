/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Model.transport;

import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import src.Model.Entity.User;

/**
 *
 * @author Damian Stępniak
 */
public class MappingUserProfil {

    private User user;
    private ObjectMapper mapper;
    private JsonNode actualObj;

    public MappingUserProfil() {
        user = new User();
        mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public User mappeUser(String data) throws IOException {
        actualObj = mapper.readValue(data, JsonNode.class);
        user = mapper.readValue(actualObj.get("optionalObject"), User.class);
        return user;
    }
    
    public List<User> mappeUsers(String data) throws IOException {
        actualObj = mapper.readValue(data, JsonNode.class);
        List<User> users;
        users = mapper.readValue(actualObj, mapper.getTypeFactory().constructCollectionType(List.class, User.class));
        return users;
    }

}
