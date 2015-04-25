<%-- 
    Document   : index2.jsp
    Created on : 26/04/2015, 12:03:06 AM
    Author     : Ajit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                    <div id="displayPushForm">
                <blockquote>
                    <h2>Push Operands</h2>
                    This form calls a RESTFul service "http://localhost:8080/UnicoAssignment-war/jms/push" and uses POST method form to capture parameters.
                    <br/>Enter Non-negative and Non-Zero integers in following text boxes and click Submit. 
                    <br/>Click Reset to clear form.
                    <br/>
                    <form name="frmJMSPostTemp" id="frmJMSPostTemp" action="rest/jms/push" method="post">
                        <table width="70%">
                            <tr>
                                <td width="30%" align="right"><label for="i1"><b>i1</b></label></td>
                                <td><input type="text" name="i1" id="i1" class="numeric"/></td>
                            </tr>
                            <tr>
                                <td width="30%" align="right"><label for="i2"><b>i2</b></label></td>
                                <td><input type="text" name="i2" id="i2" class="numeric"/></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><input type="submit" onclick="callPushREST();
                                        return false;"/> <input type="reset"/> </td>
                            </tr>
                        </table>
                    </form>
                </blockquote>
            </div>
    </body>
</html>
