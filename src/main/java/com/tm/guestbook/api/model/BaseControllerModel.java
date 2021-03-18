package com.tm.guestbook.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Dipak Chouhan
 * Purpose: To hold the final response
 */

@JsonPropertyOrder({"status", "payload", "infoMessages", "errorMessages"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseControllerModel {
    private List<String> errorMessages;
    private List<String> infoMessages;
    private Object payloads;
    private Boolean success;

    public List<String> getErrorMessages() {
        if (null == errorMessages) {
            errorMessages = new ArrayList<>();
        }
        return errorMessages;
    }

    public List<String> getInfoMessages() {
        if (null == infoMessages) {
            infoMessages = new ArrayList<>();
        }
        return infoMessages;
    }

    public Object getPayloads() {
        return payloads;
    }

    public void setPayloads(Object payloads) {
        this.payloads = payloads;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
