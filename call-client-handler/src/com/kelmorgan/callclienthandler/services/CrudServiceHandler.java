package com.kelmorgan.callclienthandler.services;

import com.newgen.callClient.CallHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrudServiceHandler {

       String processName;
       String ngMethod;
       String wiName;
       String callType;
       String endpoint;

     void setProcessName(String processName) {
        this.processName = processName;
    }

     void setNgMethod(String ngMethod) {
        this.ngMethod = ngMethod;
    }

     void setWiName(String wiName) {
        this.wiName = wiName;
    }

     void setCallType(String callType) {
        this.callType = callType;
    }

     void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }


     String handler(String message){
        String newString = null;
        String REGULAR_EXPRESSION= "(\\<MainCode>.+?\\</MainCode>)";
        Pattern pattern = Pattern.compile(REGULAR_EXPRESSION, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) newString = message.replaceAll(matcher.group(1), "");

        return newString;
    }

     boolean isSuccess(String mainCode){
        return mainCode.equalsIgnoreCase("0");
    }

    String getCallResponse(String request){
         return CallHandler.executeIntegrationCall(request);
    }
}
