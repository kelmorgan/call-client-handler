package com.kelmorgan.callclienthandler.services;

import com.newgen.callClient.CallHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseServiceHandler implements BaseService {

    protected String processName;
    protected String appCode;
    protected String wiName;
    protected String endpoint;
    protected String appKey;
    protected String soapAction;

    @Override
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Override
    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    @Override
    public void setWiName(String wiName) {
        this.wiName = wiName;
    }

    @Override
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public void setSoapAction(String soapAction) {
        this.soapAction = soapAction;
    }

    String handler(String message) {
        String newString = null;
        String REGULAR_EXPRESSION = "(\\<MainCode>.+?\\</MainCode>)";
        Pattern pattern = Pattern.compile(REGULAR_EXPRESSION, Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) newString = message.replaceAll(matcher.group(1), "");

        return newString;
    }

    boolean isSuccess(String mainCode) {
        return mainCode.equalsIgnoreCase("0");
    }

    String getCallResponse(String request) {
        return CallHandler.executeIntegrationCall(request);
    }
}
