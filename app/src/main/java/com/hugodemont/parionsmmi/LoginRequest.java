package com.hugodemont.parionsmmi;

import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    private Map<String,String> params;


    public LoginRequest(MainActivity mainActivity, String user_email, String password,Response.Listener<String> listener) {

        super(Method.POST, "http://hugodemont.fr/4654z494/a9d84az98d4a984da984zd984az98d4a98zd4.php", listener, null);
        params = new HashMap<>();
        params.put("useremail", user_email);
        params.put("password", password);

    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}