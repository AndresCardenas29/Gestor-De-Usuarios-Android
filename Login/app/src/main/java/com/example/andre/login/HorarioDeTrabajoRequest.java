package com.example.andre.login;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class HorarioDeTrabajoRequest extends StringRequest {

    static DireccionIP direccionIP = new DireccionIP();
    private static String ip = direccionIP.getIPStatic();

    private static final String HORARIO_DE_TRABAJO_REQUEST_URL=ip+"/updateHorarioTrabajo.php";
    private Map<String,String> params;
    public HorarioDeTrabajoRequest(String horarioTrabajo, String id, Response.Listener<String> listener){
        super(Request.Method.POST, HORARIO_DE_TRABAJO_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("horarioTrabajo",horarioTrabajo);
        params.put("id",id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
