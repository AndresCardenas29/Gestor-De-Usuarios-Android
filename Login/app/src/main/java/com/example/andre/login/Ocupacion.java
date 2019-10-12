package com.example.andre.login;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Ocupacion extends AppCompatActivity implements View.OnClickListener {

    DireccionIP direccionIP = new DireccionIP();
    private String ip = direccionIP.getIPStatic();

    EditText et_nit, et_ocupacion;
    Button botonGuardar;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocupacion);

        //vincular los EditText
        et_nit = findViewById(R.id.et_ocupacion_nit);
        et_ocupacion = findViewById(R.id.et_ocupacion_ocupacion);

        //vincular el Button
        botonGuardar = findViewById(R.id.btn_ocupacion_cambiar_ocupacion);

        //vincular ListView
        lista = findViewById(R.id.lv_ocupacion_lista);

        //accion del boton
        botonGuardar.setOnClickListener(this);

        obtenerDatos();
    }


    public void onClick(View v) {
        final String getNit = et_nit.getText().toString();
        final String getOcupacion = et_ocupacion.getText().toString();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(Ocupacion.this);
                        builder.setMessage("Se ha actualizado la ocupacion")
                                .setNegativeButton("Aceptar",null)
                                .create().show();
                        et_nit.setText("");
                        et_ocupacion.setText("");
                        obtenerDatos();
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Ocupacion.this);
                        builder.setMessage("Error al actualizar")
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

        OcupacionRequest ocupacion = new OcupacionRequest(getOcupacion,getNit,responseListener);
        RequestQueue queue = Volley.newRequestQueue(Ocupacion.this);
        queue.add(ocupacion);
    }

    private void CargaLista(ArrayList<String> datos) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        lista.setAdapter(adapter);
    }

    public void obtenerDatos() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = ip+"/getocupacion.php";
        RequestParams parametros = new RequestParams();
        parametros.put("id", "0");

        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    CargaLista(obtieneDatosJSON(new String(responseBody)));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    public ArrayList<String> obtieneDatosJSON(String response) {
        ArrayList<String> listado = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            for (int i = 0; i < jsonArray.length(); i++) {
                texto = jsonArray.getJSONObject(i).getString("id") + " - " +
                        jsonArray.getJSONObject(i).getString("nombre") + " - " +
                        jsonArray.getJSONObject(i).getString("ocupacion") + ".";
                listado.add(texto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listado;
    }


}
