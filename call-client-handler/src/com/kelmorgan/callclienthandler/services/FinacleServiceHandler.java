package com.kelmorgan.callclienthandler.services;

import com.kelmorgan.callclienthandler.handler.RequestResponseHandler;
import com.kelmorgan.callclienthandler.utils.Constants;
import com.kelmorgan.xmlparser.parser.XmlParser;

public class FinacleServiceHandler extends BaseServiceHandler implements RequestResponseHandler {

    public FinacleServiceHandler(String processName, String appCode, String wiName, String endpoint) {
        super.processName = processName;
        super.appCode = appCode;
        super.wiName = wiName;
        super.endpoint = endpoint;
    }

    public FinacleServiceHandler() {
    }

    @Override
    public String getHandledRequest(String request) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        stringBuilder.append("<message>");
        stringBuilder.append("<ngProcess>").append(processName).append("</ngProcess>");
        stringBuilder.append("<ngMethod>").append(appCode).append("</ngMethod>");
        stringBuilder.append("<ngOFId>").append(wiName).append("</ngOFId>");
        stringBuilder.append("<callType>").append("Finacle").append("</callType>");
        stringBuilder.append("<EndPointUrl>").append(endpoint).append("</EndPointUrl>");
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
            if (isSuccess(mainCode)) return handler(message);
        } catch (Exception e){
            return "Exception occurred FinacleService getHandledResponse method. Kindly check Call Client Logs. ";
        }
        return Constants.apiFailureMessage;
    }
}
