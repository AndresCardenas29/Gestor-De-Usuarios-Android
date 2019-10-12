package com.example.andre.login;

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

public class HorasExtras extends AppCompatActivity {

    DireccionIP direccionIP = new DireccionIP();
    private String ip = direccionIP.getIPStatic();

    EditText NIT, horasExtras, NITDiurna, horasExtrasDiurnas, NITNocturno, horasExtrasNocturno;
    Button guardar, guardarDiurna, guardarNocturno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horas_extras);

        //vincular los EditText con los del xml
        NIT = findViewById(R.id.et_horas_extras_nit);
        horasExtras = findViewById(R.id.et_horas_extras_horasExtras);
        NITDiurna = findViewById(R.id.et_horas_extras_diurnas_nit);
        horasExtrasDiurnas = findViewById(R.id.et_horas_extras_diurnas_horasExtras);
        NITNocturno = findViewById(R.id.et_horas_extras_nocturnas_nit);
        horasExtrasNocturno = findViewById(R.id.et_horas_extras_nocturnas_horasExtras);

        //vincular botones del xml
        guardar = findViewById(R.id.btn_horas_extras_guardar);
        guardarDiurna = findViewById(R.id.btn_horas_extras_diurnas_guardar);
        guardarNocturno = findViewById(R.id.btn_horas_extras_nocturnas_guardar);

        //accion al precionar el boton
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGuardar();
            }
        });
        guardarDiurna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGuardarDiurna();
            }
        });
        guardarNocturno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnGuardarNocturno();
            }
        });


    }

    public void btnGuardar(){
        //atrapar los datos
        final String getNit = NIT.getText().toString();
        final String getHorasExtras = horasExtras.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(HorasExtras.this);
                        builder.setMessage("Se ha actualizado la cantidad de horas extras")
                                .setNegativeButton("Aceptar",null)
                                .create().show();
                        NIT.setText("");
                        horasExtras.setText("");
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(HorasExtras.this);
                        builder.setMessage("Error al guardar las horas extras")
                                .setNegativeButton("Intentar",null)
                                .create().show();
                        Toast mensajeDefault = Toast.makeText(getApplicationContext(),"Error al guardar",Toast.LENGTH_LONG);
                        mensajeDefault.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        HorasExtrasRequest horasExtrasRequest = new HorasExtrasRequest(getHorasExtras,getNit,responseListener);
        RequestQueue queue = Volley.newRequestQueue(HorasExtras.this);
        queue.add(horasExtrasRequest);
    }

    public void btnGuardarDiurna(){
//atrapar los datos
        final String getNitDiruno = NITDiurna.getText().toString();
        final String getHorasExtrasDiurnas = horasExtrasDiurnas.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(HorasExtras.this);
                        builder.setMessage("Se ha actualizado la cantidad de horas extras diurnas")
                                .setNegativeButton("Aceptar",null)
                                .create().show();
                        NITDiurna.setText("");
                        horasExtrasDiurnas.setText("");
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(HorasExtras.this);
                        builder.setMessage("Error al guardar las horas extras")
                                .setNegativeButton("Intentar",null)
                                .create().show();
                        Toast mensajeDefault = Toast.makeText(getApplicationContext(),"Error al guardar",Toast.LENGTH_LONG);
                        mensajeDefault.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        HorasExtrasDiurnasRequest horasExtrasDiurnasRequest = new HorasExtrasDiurnasRequest(getHorasExtrasDiurnas,getNitDiruno,responseListener);
        RequestQueue queue = Volley.newRequestQueue(HorasExtras.this);
        queue.add(horasExtrasDiurnasRequest);
    }

    public void btnGuardarNocturno(){
        //atrapar los datos
        final String getNitNocturno = NITNocturno.getText().toString();
        final String getHorasExtrasNocturnas = horasExtrasNocturno.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(HorasExtras.this);
                        builder.setMessage("Se ha actualizado la cantidad de horas extras nocturnas")
                                .setNegativeButton("Aceptar",null)
                                .create().show();
                        NITNocturno.setText("");
                        horasExtrasNocturno.setText("");
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(HorasExtras.this);
                        builder.setMessage("Error al guardar las horas extras")
                                .setNegativeButton("Intentar",null)
                                .create().show();
                        Toast mensajeDefault = Toast.makeText(getApplicationContext(),"Error al guardar",Toast.LENGTH_LONG);
                        mensajeDefault.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        HorasExtrasNocturnasRequest horasExtrasNocturnasRequest = new HorasExtrasNocturnasRequest(getHorasExtrasNocturnas,getNitNocturno,responseListener);
        RequestQueue queue = Volley.newRequestQueue(HorasExtras.this);
        queue.add(horasExtrasNocturnasRequest);
    }


}
