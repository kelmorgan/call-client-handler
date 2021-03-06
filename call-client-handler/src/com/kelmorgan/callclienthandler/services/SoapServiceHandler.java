package com.kelmorgan.callclienthandler.services;

import com.kelmorgan.callclienthandler.handler.RequestResponseHandler;
import com.kelmorgan.callclienthandler.utils.Constants;
import com.kelmorgan.xmlparser.parser.XmlParser;

public class SoapServiceHandler extends BaseServiceHandler implements RequestResponseHandler {
    public SoapServiceHandler(String processName, String wiName, String endpoint, String soapAction) {
        super.processName = processName;
        super.wiName = wiName;
        super.endpoint = endpoint;
        super.soapAction = soapAction;
    }

    public SoapServiceHandler() {
    }

    @Override
    public String getHandledRequest(String request) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        stringBuilder.append("<message>");
        stringBuilder.append("<ngProcess>").append(processName).append("</ngProcess>");
        stringBuilder.append("<ngMethod>").append(soapAction).append("</ngMethod>");
        stringBuilder.append("<ngOFId>").append(wiName).append("</ngOFId>");
        stringBuilder.append("<callType>").append("WebService").append("</callType>");
        stringBuilder.append("<target>").append(endpoint).append("</target>");
        stringBuilder.append("<AppName>").append(Constants.appName).append("</AppName>");
        stringBuilder.append("<ngXmlRequest>").append(request).append("</ngXmlRequest>");
        stringBuilder.append("</message>");

        return stringBuilder.toString();
    }

    @Override
    public String getHandledResponse(String request) {
        try {
            String response = getCallResponse(request);
            if (response.isEmpty()) return "No Response from Call Client";
            String message = new XmlParser(response).getValueOf("message", true);
            String mainCode = new XmlParser(message).getValueOf("MainCode");
            if (isSuccess(mainCode)) return handler(message);
        } catch (Exception e) {
            return "Exception occurred SoapService getHandledResponse method. Kindly check Call Client Logs.";
        }
        return Constants.apiFailureMessage;
    }
}
