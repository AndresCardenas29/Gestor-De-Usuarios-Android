package com.example.andre.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class usuario_normal extends AppCompatActivity implements View.OnClickListener {

    static DireccionIP direccionIP = new DireccionIP();
    private static String ip = direccionIP.getIPStatic();

    TextView bienvenido,nit,nombreT,usuario,ocupacion,salario,horarioTrabajo,horasExtras,salarioTotal,tvalerta,mensajedecomando;
    TextView horasExtrasNocturnas;
    TextView horasExtrasDiurnas;
    Button calcular;
    ImageButton botonMicrofono;

    String password;
    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_normal);

        //######## # # # # ###########
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        bienvenido = findViewById(R.id.bienvenido);
        bienvenido.setText(nombre);

        //asignar los TextView
        nit = findViewById(R.id.codigo_nit);
        usuario = findViewById(R.id.usuario);
        ocupacion = findViewById(R.id.ocupacion);
        salario = findViewById(R.id.salario);
        horarioTrabajo = findViewById(R.id.horario_trabajo);
        horasExtras = findViewById(R.id.horas_trabajadas);
        salarioTotal = findViewById(R.id.salarioTotal);
        nombreT = findViewById(R.id.nombreTrabajador);
        tvalerta = findViewById(R.id.usuario_normal_alerta);
        horasExtrasDiurnas = findViewById(R.id.horas_trabajadas_diurnas);
        mensajedecomando = findViewById(R.id.tvUsuarioNormal_mensaje);
        horasExtrasNocturnas = findViewById(R.id.horas_trabajadas_nocturnas);

        //asignar botones
        calcular = findViewById(R.id.calcularSaldo);
        botonMicrofono = findViewById(R.id.btn_usuario_normal_microfono);
        calcular.setOnClickListener(this);

        //agregar los datos
        String getnit = intent.getStringExtra("id");
        nit.setText(getnit);
        String getusuario = intent.getStringExtra("usuario");
        usuario.setText(getusuario);
        nombreT.setText(nombre);
        String getocupacion = intent.getStringExtra("ocupacion");
        ocupacion.setText(getocupacion);
        String getsalario = intent.getStringExtra("salario");
        salario.setText(getsalario);
        String getHorarioTrabajo = intent.getStringExtra("horarioTrabajo");
        horarioTrabajo.setText(getHorarioTrabajo);
        String getHorasExtras = intent.getStringExtra("horasExtras");
        horasExtras.setText(getHorasExtras);
        String getHorasExtrasDiurnas = intent.getStringExtra("horasExtrasDiurnas");
        horasExtrasDiurnas.setText(getHorasExtrasDiurnas);
        password =  intent.getStringExtra("password");
        String getHorasNocturnas = intent.getStringExtra("horasExtrasNocturnas");
        horasExtrasNocturnas.setText(getHorasNocturnas);


        tvalerta.setText("Recuerde que cada hora extra equivale a 3,255.00 COP, la diurna equivale a 4,069.00 COP y la hora extra nocturna equivale a 2,441.00 COP.\nPor PHIS SAS.");

        botonMicrofono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentActionRecognizeSpeech = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                // Configura el Lenguaje (Español-Colombia)
                intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-CO");
                try {
                    startActivityForResult(intentActionRecognizeSpeech,RECOGNIZE_SPEECH_ACTIVITY);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Tú dispositivo no soporta el reconocimiento por voz",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private int salarioTotal(int cantHoras, int sueldoTotal, int cantHorasDiurnas){
        int valorHoraExtra = 3255;
        int valorHoraExtraDiurna = 4069;
        int valorHoraNocturna = 2441;

        return sueldoTotal+(valorHoraExtra*cantHoras)+(valorHoraExtraDiurna*cantHorasDiurnas);
    }

    @Override
    public void onClick(View v) {
        Toast mensajeDefault = Toast.makeText(getApplicationContext(),"Calculando",Toast.LENGTH_SHORT);
        mensajeDefault.show();
        int cantidadHoras = Integer.parseInt((String) horasExtras.getText());
        int salariot = Integer.parseInt((String) salario.getText());
        int cantHorasDiurnas = Integer.parseInt((String) horasExtrasDiurnas.getText());
        salarioTotal.setText(String.valueOf(salarioTotal(cantidadHoras,salariot,cantHorasDiurnas))+" COP");
    }

    private String mensajeSalarioTotal(){
        int cantidadHoras = Integer.parseInt((String) horasExtras.getText());
        int salariot = Integer.parseInt((String) salario.getText());
        int cantHorasDiurnas = Integer.parseInt((String) horasExtrasDiurnas.getText());
        return String.valueOf(salarioTotal(cantidadHoras,salariot,cantHorasDiurnas))+" COP";
    }

    private void mensaje(String mensaje){
        Toast mensajeSalario = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
        mensajeSalario.show();
    }

    private void comando(String strSpeech2Text){
        mensajedecomando.setText(strSpeech2Text);
        switch (strSpeech2Text){
            case "Hola":
                mensaje("¡Hola usuario de !");
                mensajedecomando.setText(strSpeech2Text+": "+"¡Hola usuario de!");
                break;

            case "Codigo nit":
                mensaje("Su codigo nit es: "+nit.getText().toString());
                mensajedecomando.setText(strSpeech2Text+": "+"Su codigo nit es "+nit.getText().toString());
                break;

            case "contraseña":
                mensaje("Su contraseña es: "+password);
                mensajedecomando.setText(strSpeech2Text+": "+"Su contraseña es "+password);
                break;

            case "Cuál es mi contraseña":
                mensaje("Su contraseña es: "+password);
                mensajedecomando.setText(strSpeech2Text+": "+"Su contraseña es "+password);
                break;

            case "calcula el saldo con horas extras":
                mensaje(mensajeSalarioTotal());
                mensajedecomando.setText(strSpeech2Text+": "+mensajeSalarioTotal());
                break;

            case "calcular el saldo con horas extras":
                mensaje(mensajeSalarioTotal());
                mensajedecomando.setText(strSpeech2Text+": "+mensajeSalarioTotal());
                break;

            case "calcular el salario con horas extras":
                mensaje(mensajeSalarioTotal());
                mensajedecomando.setText(strSpeech2Text+": "+mensajeSalarioTotal());
                break;

            case "calcula el salario con horas extras":
                mensaje(mensajeSalarioTotal());
                mensajedecomando.setText(strSpeech2Text+": "+mensajeSalarioTotal());
                break;

            case "calcula saldo":
                mensaje(mensajeSalarioTotal());
                mensajedecomando.setText(strSpeech2Text+": "+mensajeSalarioTotal());
                break;

            case "calcular saldo":
                mensaje(mensajeSalarioTotal());
                mensajedecomando.setText(strSpeech2Text+": "+mensajeSalarioTotal());
                break;

            case "calcular salario":
                mensaje(mensajeSalarioTotal());
                mensajedecomando.setText(strSpeech2Text+": "+mensajeSalarioTotal());
                break;

            case "mi codigo nit":
                mensaje(nit.getText().toString());
                mensajedecomando.setText(strSpeech2Text+": "+nit.getText().toString());
                break;

            case "horario de trabajo":
                mensaje(horarioTrabajo.getText().toString());
                mensajedecomando.setText(strSpeech2Text+": "+horarioTrabajo.getText().toString());
                break;


            default:
                mensajedecomando.setText(strSpeech2Text);
                Toast mensajeDefault = Toast.makeText(getApplicationContext(),"No se reconoce el comando: "+strSpeech2Text,Toast.LENGTH_LONG);
                mensajeDefault.show();
                break;

        }
    }

    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:

                if (resultCode == RESULT_OK && null != data) {
                    //comandos de voz
                    ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String strSpeech2Text = speech.get(0);

                    comando(strSpeech2Text);
                }

                break;
            default:

                break;
        }
    }

}
