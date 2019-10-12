package com.example.andre.login;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class HorasExtrasDiurnasRequest extends StringRequest {
    static DireccionIP direccionIP = new DireccionIP();
    private static String ip = direccionIP.getIPStatic();

    private static final String HORAS_EXTRAS_DIURNAS_REQUEST_URL=ip+"/updateDiurna.php";
    private Map<String,String> params;
    public HorasExtrasDiurnasRequest(String horasExtrasDiurnas, String id, Response.Listener<String> listener){
        super(Request.Method.POST, HORAS_EXTRAS_DIURNAS_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("horasExtrasDiurnas",horasExtrasDiurnas);
        params.put("id",id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
