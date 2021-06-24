package com.smartfoxpro.pojo;

import lombok.Data;

@Data
public class Response {
    String numFound;
    int start;
    Docs docs = new Docs();
}