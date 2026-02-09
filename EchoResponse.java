package com.example.postmanecho.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class EchoResponse {

    @JsonProperty("args")
    private Map<String, String> args;

    @JsonProperty("data")
    private Object data;

    @JsonProperty("files")
    private Map<String, String> files;

    @JsonProperty("form")
    private Map<String, String> form;

    @JsonProperty("headers")
    private Map<String, String> headers;

    @JsonProperty("json")
    private Object json;

    @JsonProperty("url")
    private String url;

    // Getters and setters
    public Map<String, String> getArgs() { return args; }
    public void setArgs(Map<String, String> args) { this.args = args; }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }

    public Map<String, String> getFiles() { return files; }
    public void setFiles(Map<String, String> files) { this.files = files; }

    public Map<String, String> getForm() { return form; }
    public void setForm(Map<String, String> form) { this.form = form; }

    public Map<String, String> getHeaders() { return headers; }
    public void setHeaders(Map<String, String> headers) { this.headers = headers; }

    public Object getJson() { return json; }
    public void setJson(Object json) { this.json = json; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}