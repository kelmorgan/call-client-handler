package com.kelmorgan.callclienthandler.services;

public interface BaseService {

    void setProcessName(String processName);

    void setAppCode(String appCode);

    void setWiName(String wiName);

    void setEndpoint(String endpoint);

    void setAppKey(String appKey);

    void setSoapAction(String soapAction);
}
