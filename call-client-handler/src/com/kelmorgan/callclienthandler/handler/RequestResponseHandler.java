package com.kelmorgan.callclienthandler.handler;

import com.kelmorgan.callclienthandler.services.CrudService;

public interface RequestResponseHandler extends CrudService {

    String getHandledRequest(String request);

    String getHandledResponse(String request);
}
