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

public class Administradores extends AppCompatActivity implements View.OnClickListener {

    DireccionIP direccionIP = new DireccionIP();
    private String ip = direccionIP.getIPStatic();

    EditText nit, tipo;
    Button boton;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administradores);

        //vincular los EdtiText
        nit = findViewById(R.id.et_administradores_nit);
        tipo = findViewById(R.id.et_administradores_tipo);

        //vincular el Button
        boton = findViewById(R.id.btn_administradores_guardar);

        //vincular lista
        lista = findViewById(R.id.list_administradores);

        //accion boton
        boton.setOnClickListener(this);

        obtenerDatos();
    }

    @Override
    public void onClick(View v) {
        final String getNit = nit.getText().toString();
        final String getTipo = tipo.getText().toString();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success){
                        AlertDialog.Builder builder = new AlertDialog.Builder(Administradores.this);
                        builder.setMessage("Se ha actualizado")
                                .setNegativeButton("Aceptar",null)
                                .create().show();
                        nit.setText("");
                        tipo.setText("");
                        obtenerDatos();
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Administradores.this);
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

        AdministradoresRequest administradoresRequest = new AdministradoresRequest(getTipo,getNit,responseListener);
        RequestQueue queue = Volley.newRequestQueue(Administradores.this);
        queue.add(administradoresRequest);
    }

    private void CargaLista(ArrayList<String> datos) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        lista.setAdapter(adapter);
    }

    public void obtenerDatos() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = ip+"/getTipo.php";
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
                        jsonArray.getJSONObject(i).getString("tipo") + ".";
                listado.add(texto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listado;
    }
}
