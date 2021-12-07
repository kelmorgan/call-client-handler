package com.kelmorgan.callclienthandler.services;

import com.kelmorgan.callclienthandler.handler.RequestResponseHandler;
import com.kelmorgan.callclienthandler.utils.Constants;
import com.kelmorgan.xmlparser.parser.XmlParser;

public class RestServiceHandler extends CrudServiceHandler implements RequestResponseHandler {

    private String appKey;

    public RestServiceHandler(String processName, String endpoint, String wiName, String callType, String appKey) {
        super.processName = processName;
        super.endpoint = endpoint;
        super.wiName = wiName;
        super.callType = callType;
        this.appKey = appKey;
    }

    public RestServiceHandler() {
    }

    @Override
    public String getHandledRequest(String request) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        stringBuilder.append("<message>");
        stringBuilder.append("<ngProcess>").append(processName).append("</ngProcess>");
        stringBuilder.append("<ngMethod>").append(endpoint).append("</ngMethod>");
        stringBuilder.append("<ngOFId>").append(wiName).append("</ngOFId>");
        stringBuilder.append("<callType>").append(callType).append("</callType>");
        stringBuilder.append("<target>").append(endpoint).append("</target>");
        stringBuilder.append("<user>").append(Constants.user).append("</user>");
        stringBuilder.append("<AppName>").append(Constants.appName).append("</AppName>");
        stringBuilder.append("<AppId>").append(Constants.appId).append("</AppId>");
        stringBuilder.append("<ngXmlRequest>").append(request).append("</ngXmlRequest>");
        stringBuilder.append("</message>");


        return stringBuilder.toString();
    }

    @Override
    public String getHandledResponse(String request) {
        try {
            String response = getCallResponse(request);
            if (response.isEmpty()) return "No Response from Call Client";
            String message = new XmlParser(response).getValueOf("message",true);
            String mainCode = new XmlParser(message).getValueOf("MainCode");
            if (isSuccess(mainCode)) return new XmlParser(handler(message)).getValueOf("Output");
        } catch (Exception e){
            return "Exception occurred RestService getHandledResponse method";
        }
        return Constants.apiFailureMessage;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
