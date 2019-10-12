package com.example.andre.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    DireccionIP direccionIP = new DireccionIP();
    private String ip = direccionIP.getIPStatic();

    EditText txtNombre, txtUsuario, txtEmail, txtPassword, txtOcupacion;
    Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //vincular los editText
        txtNombre = findViewById(R.id.txt_registro_nombre);
        txtUsuario = findViewById(R.id.txt_registro_nombreusuario);
        txtEmail = findViewById(R.id.txt_registro_email);
        txtPassword = findViewById(R.id.txt_registro_password);
        txtOcupacion = findViewById(R.id.txt_registro_ocupacion);

        //vincular boton
        btnRegistro = findViewById(R.id.btn_registrar);

        btnRegistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //atrapar datos de los EditText
        final String name = txtNombre.getText().toString();
        final String user = txtUsuario.getText().toString();
        final String email = txtEmail.getText().toString();
        final String password = txtPassword.getText().toString();
        final String ocupacion = txtOcupacion.getText().toString();


       Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        Intent intent = new Intent(Registro.this,MainActivity.class);
                        Registro.this.startActivity(intent);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                        builder.setMessage("Error registro")
                                .setNegativeButton("Retry",null)
                                .create().show();
                        Toast mensajeDefault = Toast.makeText(getApplicationContext(),"Error de registro",Toast.LENGTH_LONG);
                        mensajeDefault.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
                    builder.setMessage("Error registro: "+e)
                            .setNegativeButton("Retry",null)
                            .create().show();
                    Toast mensajeDefault = Toast.makeText(getApplicationContext(),"Error de registro",Toast.LENGTH_LONG);
                    mensajeDefault.show();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(name,user,email,password,ocupacion,respoListener);
        RequestQueue queue = Volley.newRequestQueue(Registro.this);
        queue.add(registerRequest);

    }
}
