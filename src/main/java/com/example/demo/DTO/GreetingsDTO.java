package com.example.demo.DTO;

public class GreetingsDTO {
    private String OrginalMessage;

    public String getOrginalMessage() {
        return OrginalMessage;
    }

    public void setOrginalMessage(String orginalMessage) {
        OrginalMessage = orginalMessage;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }

    private String ResponseMessage;
}
