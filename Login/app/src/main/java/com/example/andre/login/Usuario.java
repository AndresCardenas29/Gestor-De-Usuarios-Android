package com.example.andre.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.speech.RecognizerIntent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Usuario extends AppCompatActivity {

    static DireccionIP direccionIP = new DireccionIP();
    private static String ip = direccionIP.getIPStatic();

    TextView name,bienvenido;
    Button listEmpleados, horarasExtras, botonOcupacion, horasTrabajadas,botonAdministradores;

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);


        //grabar = findViewById(R.id.txtGrabarVoz);
        //botones
        listEmpleados = findViewById(R.id.btn_lista_empleados);
        horarasExtras = findViewById(R.id.btnHorasExtras);
        horasTrabajadas = findViewById(R.id.btnHorariosDeTrabajo);
        botonOcupacion = findViewById(R.id.btnOcupacion);
        botonAdministradores = findViewById(R.id.btnAdministradores);


        listEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaEmpleados();
            }
        });

        horarasExtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horasExtras();
            }
        });

        botonOcupacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ocupacion();
            }
        });
        horasTrabajadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                horasTrabajadas();
            }
        });

        botonAdministradores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                administradores();
            }
        });

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        bienvenido = findViewById(R.id.bienvenido);
        bienvenido.setText(nombre);


    }

    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:

                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String strSpeech2Text = speech.get(0);
                    //comadno de voz atrapado
                    comando(strSpeech2Text);
                }

                break;
            default:

                break;
        }
    }

    public void onClickImgBtnHablar(View v) {

        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

// Configura el Lenguaje (Español-Colombia)
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-CO");
        try {
            startActivityForResult(intentActionRecognizeSpeech,RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Tú dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show();
        }

    }


    //comandos
    private void comando(String strSpeech2Text){
        switch (strSpeech2Text){
            case "lista de empleados":
                listaEmpleados();
                break;

            case "horas extras":
                horasExtras();
                break;

            case "horario de trabajo":
                horasTrabajadas();
                break;

            case "ocupacion":
                ocupacion();

                break;

            case "administradores":
                administradores();

                break;


            default:
                Toast mensajeDefault = Toast.makeText(getApplicationContext(),"No se reconoce el comando: "+strSpeech2Text,Toast.LENGTH_LONG);
                mensajeDefault.show();
                break;

        }
    }

    private void listaEmpleados(){
        Intent intentListaEmpleados = new Intent(Usuario.this, ListaEmpleados.class);
        Usuario.this.startActivity(intentListaEmpleados);
        Toast mensjaeHorarios = Toast.makeText(getApplicationContext(),"Lista de empleados.",Toast.LENGTH_LONG);
        mensjaeHorarios.show();
    }

    private void horasExtras(){
        Intent intentHorario = new Intent(Usuario.this,HorasExtras.class);
        Usuario.this.startActivity(intentHorario);
        Toast mensajeListaEmpleados = Toast.makeText(getApplicationContext(),"Horas extras.",Toast.LENGTH_LONG);
        mensajeListaEmpleados.show();
    }

    private void ocupacion(){
        Intent intentOcupacion = new Intent(Usuario.this,Ocupacion.class);
        Usuario.this.startActivity(intentOcupacion);
        Toast mensajeListaEmpleados = Toast.makeText(getApplicationContext(),"ocupacion.",Toast.LENGTH_LONG);
        mensajeListaEmpleados.show();
    }

    private void horasTrabajadas(){
        Intent intentHorasTrabajadas = new Intent(Usuario.this, HorarioDeTrabajo.class);
        Usuario.this.startActivity(intentHorasTrabajadas);
        Toast mensajeListaEmpleados = Toast.makeText(getApplicationContext(),"Horario de trabajo.",Toast.LENGTH_LONG);
        mensajeListaEmpleados.show();
    }

    private void administradores(){
        Intent intentAdmin = new Intent(Usuario.this, Administradores.class);
        Usuario.this.startActivity(intentAdmin);
        Toast mensjaeAdmin = Toast.makeText(getApplicationContext(),"Administradores.",Toast.LENGTH_LONG);
        mensjaeAdmin.show();
    }
}
