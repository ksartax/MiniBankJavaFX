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
import src.Model.Entity.Deposit_Into_Account;
import src.Model.Entity.Finance_Account_User;

/**
 *
 * @author Damian Stępniak
 */
public class MappingDeposit extends Request {
    private Finance_Account_User deposit_Into_Account;
    private ObjectMapper mapper;
    private JsonNode actualObj;
    
    public MappingDeposit(){
        deposit_Into_Account = new Finance_Account_User();
         mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    public Finance_Account_User mappeUser(String json) throws IOException {
        actualObj = mapper.readValue(json, JsonNode.class);
        deposit_Into_Account = mapper.readValue(actualObj.get("optionalObject"), Finance_Account_User.class);
        return deposit_Into_Account;
    }
    
}
