package com.example.andre.login;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AdministradoresRequest extends StringRequest{
    static DireccionIP direccionIP = new DireccionIP();
    private static String ip = direccionIP.getIPStatic();

    private static final String ADMINISTRADORES_REQUEST_URL=ip+"/updateTipo.php";
    private Map<String,String> params;
    public AdministradoresRequest(String horarioTrabajo, String id, Response.Listener<String> listener){
        super(Request.Method.POST, ADMINISTRADORES_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("tipo",horarioTrabajo);
        params.put("id",id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
