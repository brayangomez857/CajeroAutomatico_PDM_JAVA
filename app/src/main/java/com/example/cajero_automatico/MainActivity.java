package com.example.cajero_automatico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.CarrierConfigManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  private Button verificar;
  int Total;
  private EditText Valorconsignar, Valorretirar;
  private TextView Totalsaldo, mensaje;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    verificar = (Button) findViewById(R.id.btnverificar);
    Valorconsignar = (EditText) findViewById(R.id.etvalorconsignar);
    Valorretirar = (EditText) findViewById(R.id.etvalorretirar);
    Totalsaldo = (TextView) findViewById(R.id.tvtotalsaldo);
    mensaje = (TextView) findViewById(R.id.tvmensaje);

    SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);

    String v = String.valueOf(prefe.getInt("salario", 0));
    Totalsaldo.setText(v);

    verificar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        int valor=Integer.parseInt(Valorconsignar.getText().toString());
        int valor2=Integer.parseInt(Valorretirar.getText().toString());
        int saldoactual=Integer.parseInt(Totalsaldo.getText().toString());
        if (valor>0){

          saldoactual=saldoactual+valor;
          Totalsaldo.setText(String.valueOf(saldoactual));
          mensaje.setText("Correcto.");
          Valorconsignar.setText("");

          SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
          SharedPreferences.Editor editor=prefe.edit();
          editor.putInt("salario", saldoactual);
          editor.commit();
        }
        if (valor2<saldoactual){
          saldoactual=saldoactual-valor2;
          Totalsaldo.setText(String.valueOf(saldoactual));
          mensaje.setText("Correcto.");
          Valorretirar.setText("");

          SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
          SharedPreferences.Editor editor=prefe.edit();
          editor.putInt("salario", saldoactual);
          editor.commit();
          }
        else {
          mensaje.setText("Inconrrecto. El valor es mayor al salario actual.");
          Valorretirar.setText("");
        }


      }
    });

  }



}