package au.com.unico.assignment.util;

import java.util.List;

public class Utilities {
	/**
	 * converts a string to integer
	 * @param str
	 * @return
	 */
	public static int getIntFromString(String str) {
		int i=0;
		try {
			i = Integer.parseInt(str);
		}
		catch(Exception e) {
			i = 0;
		}
		return i;
	}
        
        /**
         * this method accepts two integers and calculates GCD.
         * @param i1
         * @param i2
         * @return 
         */
        public static int calculateGCD(int i1, int i2) {
            int gcd = 1;
            int maxGCD = 1;
            // restrict the calculation to the least of two numbers as maxGCD can be one of them
            if(i1 >= i2) {
                maxGCD = i2;
            }else {
                maxGCD = i1;
            }
            //start from max to 2 and whenever an absolute divisor found, break;
            for(int i=maxGCD; i >= 2; i--) {
                if( (i1 % i == 0) && (i2 % i == 0) ) {
                    gcd = i;
                    break;
                }
            }   
            return gcd;
        }
        
        
        public static void main(String [] args) {
            System.out.println(calculateGCD(16, 20));
        }
        
        /**
         * utility method to convert array of Integers to JSON notation. Generally we can use one of the industry standard
         * libraries
         * @param list
         * @return 
         */
        public static String arrayListToJson(List<Integer> list) {
            StringBuffer json = new StringBuffer("");
            json.append("{ \"elements\":[");
            for(int count =0; count< list.size(); count++) {
                json.append("\"");
                json.append(list.get(count));
                json.append("\"");
                if(count < list.size()-1) {
                    json.append(", ");    
                }
            }
            
            json.append("]}");
            return json.toString();
        }
        /**
         * utility method to convert a message to JSON notation. Generally we can use one of the industry standard
         * libraries
         * @param list
         * @return 
         */
        public static String strToJson(String msg) {
            StringBuffer json = new StringBuffer("");
            json.append("{ \"msg\":[");
            json.append("\"");
            json.append(msg);
            json.append("\""); 
            json.append("]}");
            return json.toString();
        }
}
