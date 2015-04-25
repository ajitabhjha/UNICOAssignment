/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package au.com.unico.assignment.util;

/**
 *
 * @author Ajit
 */
public class Constants {
    //Constants for DB Error
    public static final String DB_ERROR= "Problem encountered during database communication. Check if Datasource has been created and Database server is up!!!";
    
    //Constants for JMS Service class
    public static final String JMS_PUSH_SUCCESS = "200 - Operands added to JMS queue.";
    public static final String JMS_PUSH_FAILURE = "404";
    public static final String EMPTY_JMS = "No parameters present in JMS to calculate GCD!!!";
    
    //JMS Helper Constants
    
    public static final String JNDI_JMS_CONNECTION_FACTORY = "jms/UnicoConnectionFactory";
    public static final String JNDI_JMS_QUEUE = "jms/unicoAssignmentQueue";
    
    //JDBC COnstants
    public static final String CONN_STR = "jdbc/UnicoDS";
    
    
}
