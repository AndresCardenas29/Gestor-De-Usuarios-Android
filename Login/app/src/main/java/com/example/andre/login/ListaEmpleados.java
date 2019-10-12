package com.example.andre.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListaEmpleados extends AppCompatActivity implements View.OnClickListener {

    DireccionIP direccionIP = new DireccionIP();
    private String ip = direccionIP.getIPStatic();

    Button consultar, consultarPorId, consultaPorUser;
    EditText identificador, txusuario;

    ListView listado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleados);


        //enlaces con elementos visuales del xml

        consultar = findViewById(R.id.btn_lista_empleados_consultar);
        consultarPorId = findViewById(R.id.btn_lista_empleados_consultarid);
        consultaPorUser = findViewById(R.id.btn_lista_empleados_usuarios);

        identificador = findViewById(R.id.et_lista_empleados_id);
        txusuario = findViewById(R.id.et_lista_empleados_usuario);


        consultar.setOnClickListener(this);
        consultarPorId.setOnClickListener(this);
        consultaPorUser.setOnClickListener(this);

        listado = findViewById(R.id.lvLista);
        obtenerDatos();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_lista_empleados_consultar:
                Toast mensajeListaEmpleados = Toast.makeText(getApplicationContext(), "actualizando", Toast.LENGTH_SHORT);
                mensajeListaEmpleados.show();
                obtenerDatos();
                break;

            case R.id.btn_lista_empleados_consultarid:
                obtenerDatosPorId();
                break;

            case R.id.btn_lista_empleados_usuarios:
                obtenerDatosPorUsuario();
                break;
            default:

                break;
        }
    }
    private void CargaLista(ArrayList<String> datos) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adapter);
    }
    public void obtenerDatos() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = ip+"/getdata.php";
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
                        jsonArray.getJSONObject(i).getString("usuario") + " - " +
                        jsonArray.getJSONObject(i).getString("ocupacion") + " - " +
                        jsonArray.getJSONObject(i).getString("salario") + " - " +
                        jsonArray.getJSONObject(i).getString("horarioTrabajo") + ".";
                listado.add(texto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listado;
    }

    public void obtenerDatosPorId() {
        AsyncHttpClient client = new AsyncHttpClient();
        String id = identificador.getText().toString();
        RequestParams parametros = new RequestParams();
        parametros.put("id", id);
        String url = ip+"/getdataporid.php";
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

    public void obtenerDatosPorUsuario() {
        AsyncHttpClient client = new AsyncHttpClient();
        String usuario = txusuario.getText().toString();
        String url = ip+"/getdataporuser.php";
        RequestParams parametros = new RequestParams();
        parametros.put("usuario", usuario);
        Toast mensajeListaEmpleados = Toast.makeText(getApplicationContext(), "actualizando", Toast.LENGTH_SHORT);
        mensajeListaEmpleados.show();
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
}
