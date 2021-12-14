package com.kelmorgan.callclienthandler.handler;

import com.kelmorgan.callclienthandler.services.BaseService;

public interface RequestResponseHandler extends BaseService {

    String getHandledRequest(String request);

    String getHandledResponse(String request);
}
