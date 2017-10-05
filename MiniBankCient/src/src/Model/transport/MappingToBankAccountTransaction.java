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
import src.Model.Entity.*;

/**
 *
 * @author Damian Stępniak
 */
public class MappingToBankAccountTransaction extends Request {

    private Finance_Account_User to_Bank_Account_Transaction;
    private ObjectMapper mapper;
    private JsonNode actualObj;

    public MappingToBankAccountTransaction() {
        to_Bank_Account_Transaction = new Finance_Account_User();
        mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Finance_Account_User mappeUser(String data) throws IOException {
        actualObj = mapper.readValue(data, JsonNode.class);
        to_Bank_Account_Transaction = mapper.readValue(actualObj.get("optionalObject"), Finance_Account_User.class);
        return to_Bank_Account_Transaction;
    }

}
