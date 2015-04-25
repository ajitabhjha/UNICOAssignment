package au.com.unico.assignment.soap;

import au.com.unico.assignment.util.Constants;
import au.com.unico.assignment.util.JDBCHelper;
import au.com.unico.assignment.util.JMSHelper;
import au.com.unico.assignment.util.Utilities;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author Ajit
 */
@WebService(serviceName = "UnicoAssignmentSOAPWS")
public class UnicoAssignmentSOAPWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "gcd")
    public Integer gcd() throws Exception{
        int gcd = 1;
        JMSHelper jmsHelper = new JMSHelper();
        int i1 = jmsHelper.popFromQueue();
        int i2 = jmsHelper.popFromQueue();
        if(i1 <= 0 || i2 <=0)
        {
            throw new Exception(Constants.EMPTY_JMS);
        }
        gcd = Utilities.calculateGCD(i1, i2);
        JDBCHelper jdbcHelper = new JDBCHelper();
        try {
            jdbcHelper.insertIntoDB(i1, i2, gcd);
        } catch (SQLException ex) {
            Logger.getLogger(UnicoAssignmentSOAPWS.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(Constants.DB_ERROR);
        }
        return gcd;
    }

    @WebMethod(operationName = "gcdSum")
    public int gcdSum() throws Exception {
        int sum = 0;
        JDBCHelper jdbcHelper = new JDBCHelper();
        try {
            sum = jdbcHelper.getTotalGCD();
        } catch (SQLException ex) {
            Logger.getLogger(UnicoAssignmentSOAPWS.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(Constants.DB_ERROR);
        }
        return sum;
    }
}
