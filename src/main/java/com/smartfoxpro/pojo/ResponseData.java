package com.smartfoxpro.pojo;

import lombok.Data;

@Data
public class ResponseData {

    Response response = new Response();

    @Override
    public String toString() {
        return response.docs.id + "   " + response.docs.g + "   " +  response.docs.a;
    }
}
