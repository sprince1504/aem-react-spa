package com.adobe.aem.guides.aemspa.react.core.services;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public interface GraphqlConnection {
    public JSONObject createConnection(String query) throws IOException, JSONException;
}
