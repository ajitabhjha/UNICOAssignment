package au.com.unico.assignment.rest;

import au.com.unico.assignment.util.Constants;
import au.com.unico.assignment.util.JDBCHelper;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import au.com.unico.assignment.util.JMSHelper;
import au.com.unico.assignment.util.Utilities;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//Sets the path to base URL + /jms
@Path("/jms")
public class RestJMSService {


    @POST
    @Path("/push")
    @Produces("text/plain")
    public String push(@FormParam("i1") String i1, @FormParam("i2") String i2) {
        boolean pushSuccess = false;
        System.out.println(i1 + " " + i2);
        JMSHelper jmsHelper = new JMSHelper();
        int op1 = Utilities.getIntFromString(i1);
        int op2 = Utilities.getIntFromString(i2);
        System.out.println(op1 + " " + op2);
        if(op1 > 0 && op2 > 0)
        {    
            pushSuccess = jmsHelper.pushToQueue(op1);
            if (pushSuccess) {
                pushSuccess = jmsHelper.pushToQueue(op2);
            }
        }
        if (pushSuccess) {
            return Constants.JMS_PUSH_SUCCESS;
        } else {
            return Constants.JMS_PUSH_FAILURE + " - Error adding operands to JMS Queue. \nMake sure you are entering Non-Negative and Non-Zero integers";
            //code may be written to roll back the first add operation to queue, depending on requirement.
        }
    }

    @GET
    @Path("/pop")
    @Produces({"application/json"})
    public String list() throws Exception {
        JDBCHelper jdbcHelper = new JDBCHelper();
        List<Integer> returnList = new ArrayList<Integer>();
        String response = "";
        try {
            returnList = jdbcHelper.getAllNumbers();
            response = Utilities.arrayListToJson(returnList);
        } catch (SQLException ex) {
            Logger.getLogger(RestJMSService.class.getName()).log(Level.SEVERE, null, ex);
            response = Utilities.strToJson(Constants.DB_ERROR);
        }
        return response;
    }

}
