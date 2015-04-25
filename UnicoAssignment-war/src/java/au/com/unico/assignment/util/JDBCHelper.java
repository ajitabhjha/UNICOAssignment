package au.com.unico.assignment.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCHelper {

    private static DataSource datasource;

    private static final String DML_INSERT_GCD = "insert into gcd (i1,i2,gcd) values (?,?,?)";
    private static final String SQL_SELECT_GCDSUM = "select sum(gcd) as gcdSum from gcd";
    private static final String SQL_SELECT_ALL = "select i1, i2 from gcd order by creation_datetime asc";

    private Connection getDBConnection() throws SQLException {
        if (datasource == null) {
            try {
                Context jndiContext = new InitialContext();
                datasource = (DataSource) jndiContext.lookup(Constants.CONN_STR);
            } catch (Exception ex) {
                ex.printStackTrace();
                //logger can be implemented later
            }
        }
        return datasource.getConnection();
    }

    /**
     * this method will insert a pair of operands and final gcd calculated into
     * DB table GCD;
     *
     * @param i1
     * @param i2
     * @param gcd
     */
    public void insertIntoDB(int i1, int i2, int gcd) throws SQLException {
        Connection conn = getDBConnection();
        PreparedStatement pstmt = conn.prepareStatement(DML_INSERT_GCD);
        pstmt.setInt(1, i1);
        pstmt.setInt(2, i2);
        pstmt.setInt(3, gcd);
        pstmt.execute();
    }

    /**
     * this method returns a sum total of GCDs calculated and stored in DB.
     *
     * @return
     */
    public int getTotalGCD() throws SQLException {
        int sum = 0;
        Connection conn = getDBConnection();
        PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_GCDSUM);
        ResultSet rs = pstmt.executeQuery();
        if (rs != null && rs.next()) {
            sum = rs.getInt("gcdSum");
        }
        return sum;
    }

    /**
     * this method returns an ArrayList of Integer
     */
    public List<Integer> getAllNumbers() throws SQLException {
        List<Integer> returnList = new ArrayList<Integer>();
        Connection conn = getDBConnection();
        PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            returnList.add(rs.getInt("i1"));
            returnList.add(rs.getInt("i2"));
        }
        return returnList;
    }

}
