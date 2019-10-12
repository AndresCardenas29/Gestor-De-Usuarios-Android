package com.example.andre.login;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //para llamar al btn_reg del activity_main.xml
    TextView tv_register;
    Button tv_boton_iniciar;
    EditText txt_user, txt_pass;

    DireccionIP direccionIP = new DireccionIP();
    String ip = direccionIP.getIP();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //se llama el btn_reg por la id
        tv_register = (TextView) findViewById(R.id.btn_reg);

        //evento a realizar cuando se preciona REGISTRARME
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Desde MainActivity llamar al Activity Registro
                Intent intentReg = new Intent(MainActivity.this, Registro.class);
                MainActivity.this.startActivity(intentReg);
            }
        });

        tv_boton_iniciar = findViewById(R.id.btn_login_inicio);
        tv_boton_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_user = findViewById(R.id.txt_login_usuario);
                txt_pass = findViewById(R.id.txt_login_password);
                final String usuario = txt_user.getText().toString();
                final String password = txt_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                String name = jsonResponse.getString("nombre");
                                String email = jsonResponse.getString("email");
                                String id = jsonResponse.getString("id");
                                int tipo = Integer.parseInt(jsonResponse.getString("tipo"));
                                String ocupacion = jsonResponse.getString("ocupacion");
                                String salario = jsonResponse.getString("salario");
                                String horarioTrabajo = jsonResponse.getString("horarioTrabajo");
                                String horasExtras = jsonResponse.getString("horasExtras");
                                String horasExtrasDiurnas = jsonResponse.getString("horasExtrasDiurnas");
                                String password = jsonResponse.getString("password");
                                String horasExtrasNocturnas = jsonResponse.getString("horasExtrasNocturnas");

                                if (tipo == 1){
                                    Intent intent = new Intent(MainActivity.this,Usuario.class);
                                    intent.putExtra("nombre",name);
                                    intent.putExtra("email", email);
                                    MainActivity.this.startActivity(intent);
                                }else if(tipo == 0){
                                    Intent intent = new Intent(MainActivity.this,usuario_normal.class);
                                    intent.putExtra("nombre",name);
                                    intent.putExtra("email", email);
                                    intent.putExtra("id", id);
                                    intent.putExtra("usuario",usuario);
                                    intent.putExtra("ocupacion",ocupacion);
                                    intent.putExtra("salario", salario);
                                    intent.putExtra("horarioTrabajo", horarioTrabajo);
                                    intent.putExtra("horasExtras", horasExtras);
                                    intent.putExtra("horasExtrasDiurnas", horasExtrasDiurnas);
                                    intent.putExtra("password",password);
                                    intent.putExtra("horasExtrasNocturnas",horasExtrasNocturnas);
                                    MainActivity.this.startActivity(intent);
                                }


                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                 builder.setMessage("Usuario o contraseña incorrecta")
                                        .setNegativeButton("Retry",null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast mensajeDefault = Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                            mensajeDefault.show();
                        }
                    }
                };


                LoginRequest loginRequest = new LoginRequest(usuario,password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });


    }
}
