$(document).ready(function(){
    $(".numeric").numeric();
});

function showPushRESTForm() {
    resetMenu(1);
    $('#rightContent').empty();
    var tempHTMLStr= $("#displayPushForm").clone().html().replace(/frmJMSPostTemp/gi,"frmJMSPost");
    $("#rightContent").html($(tempHTMLStr));
    $(".numeric").numeric();
}

function callPushREST() {
    resetMenu(1);
    if(document.frmJMSPost.i1.value == '' || document.frmJMSPost.i2.value == '' ) {
        alert('Please enter both numeric operands');
        return false;
    }
    $.ajax({
         method: "POST",
         url: "rest/jms/push",
         data: $("#frmJMSPost").serialize(),
         dataType: "text",
         contentType: "application/x-www-form-urlencoded",
         success: function (data, status, jqXHR) {
             alert( data);
             document.frmJMSPost.i1.value = '';
             document.frmJMSPost.i2.value = '';
         },
     
         error: function (jqXHR, status) {            
            alert("Error Occurred!!!\n " + status);
         }

     });
}
function callListDBREST(){
    resetMenu(2);
        $.ajax({
         method: "GET",
         url: "rest/jms/pop",
         dataType: "json",
         
         success: function (jsonObj, status, jqXHR) {

             //errorOccurred
             if(jsonObj.msg != null) {
                 alert("Error Occurred!!!\n " + jsonObj.msg);
             } else if(jsonObj.elements != null) {
                 var htmlStr = "<table style='margin-left:35px'>";
                 
                 for(var i=0; i<jsonObj.elements.length; i++) {
                     htmlStr += "<tr><td>" + jsonObj.elements[i] + "</td></tr>";
                 }
                 htmlStr += "</table>";
                 $('#rightContent').empty();
                 $('#rightContent').html($('#displayList').clone().append(htmlStr));
             }
         },
     
         error: function (jqXHR, status) {            
            alert("Error Occurred!!!\n " + status);
         }

     });
    
}

function callCalcGSTSOAP() {
    resetMenu(3);
       var soapMessage= "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:soap='http://soap.assignment.unico.com.au/'>";
       soapMessage += "   <soapenv:Header/>";
       soapMessage += "      <soapenv:Body>";
       soapMessage += "         <soap:gcd/>";
       soapMessage += "      </soapenv:Body>";
       soapMessage += "</soapenv:Envelope>";
    
        $.ajax({
         method: "POST",
         url: "UnicoAssignmentSOAPWS",
         dataType: "xml",
         data: soapMessage, 
         processData: false,
         contentType: "text/xml; charset=\"utf-8\"",
         success: function (xmlObj, status, jqXHR) {
             var htmlStr = $(jqXHR.responseXML).find("return").text();
             $('#rightContent').empty();
              $('#rightContent').html($('#displayGCD').clone().append(htmlStr));

         },
     
         error: function (jqXHR, status) {            
            var str = $(jqXHR.responseXML).find("message").text();
            alert("Error Occurred!!!\n " + status + "\n" + str);
         }

     });
}

function callCalcGSTSumSOAP() {
    resetMenu(4);
       var soapMessage= "<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:soap='http://soap.assignment.unico.com.au/'>";
       soapMessage += "   <soapenv:Header/>";
       soapMessage += "      <soapenv:Body>";
       soapMessage += "         <soap:gcdSum/>";
       soapMessage += "      </soapenv:Body>";
       soapMessage += "</soapenv:Envelope>";
    
        $.ajax({
         method: "POST",
         url: "UnicoAssignmentSOAPWS",
         dataType: "xml",
         data: soapMessage, 
         processData: false,
         contentType: "text/xml; charset=\"utf-8\"",
         success: function (xmlObj, status, jqXHR) {
             
             var htmlStr = $(jqXHR.responseXML).find("return").text();
             $('#rightContent').empty();
              $('#rightContent').html($('#displayGCDSum').clone().append(htmlStr));

         },
     
         error: function (jqXHR, status) {            
             var str = $(jqXHR.responseXML).find("message").text();
            alert("Error Occurred!!!\n " + status + "\n" + str);
         }

     });
}

function resetMenu(src) {
    for(var i=1; i<=4; i++){
        $("#menuItem"+i).css("font-weight","normal");
        
    }
    $("#menuItem"+src).css("font-weight","bold");
}