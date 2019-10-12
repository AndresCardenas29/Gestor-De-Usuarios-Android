package com.example.andre.login;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{

    static DireccionIP direccionIP = new DireccionIP();
    private static String ip = direccionIP.getIPStatic();

    private static final String REGISTER_REQUEST_URL=ip+"/register.php";
    private Map<String,String> params;
    public RegisterRequest(String nombre, String usuario, String email, String password, String ocupacion, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("nombre",nombre);
        params.put("usuario",usuario);
        params.put("email",email);
        params.put("password",password);
        params.put("ocupacion",ocupacion);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
