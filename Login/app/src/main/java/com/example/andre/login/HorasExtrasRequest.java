package com.example.andre.login;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class HorasExtrasRequest extends StringRequest{

    static DireccionIP direccionIP = new DireccionIP();
    private static String ip = direccionIP.getIPStatic();

    private static final String HORAS_EXTRAS_REQUEST_URL=ip+"/update.php";
    private Map<String,String> params;
    public HorasExtrasRequest(String horasExtras, String id, Response.Listener<String> listener){
        super(Request.Method.POST, HORAS_EXTRAS_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("horasExtras",horasExtras);
        params.put("id",id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
