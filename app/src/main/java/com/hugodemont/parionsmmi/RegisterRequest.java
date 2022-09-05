package com.hugodemont.parionsmmi;

import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private Map<String,String> params;


    public RegisterRequest(MainActivity mainActivity, String user_email, String user_name, String password,Response.Listener<String> listener) {

        super(Method.POST, "http://hugodemont.fr/4654z494/fe6s16es1896se9s1fe651s68f46s464q684d684g6846t8h4.php", listener, null);
        params = new HashMap<>();
        params.put("useremail", user_email);
        params.put("username", user_name);
        params.put("password", password);

    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}