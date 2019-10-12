package com.example.andre.login;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class OcupacionRequest extends StringRequest{

    static DireccionIP direccionIP = new DireccionIP();
    private static String ip = direccionIP.getIPStatic();

    private static final String OCUPACION_REQUEST_URL=ip+"/updateOcupacion.php";
    private Map<String,String> params;

    public OcupacionRequest(String ocupacion, String id, Response.Listener<String> listener){
        super(Request.Method.POST, OCUPACION_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("ocupacion",ocupacion);
        params.put("id",id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
