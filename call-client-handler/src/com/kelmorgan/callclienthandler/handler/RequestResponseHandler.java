package com.kelmorgan.callclienthandler.handler;

public interface RequestResponseHandler {

    String getHandledRequest(String request);

    String getHandledResponse(String request);
}
