package com.example.marcelo.appbluetooth_final;

import android.app.Activity;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_BT = 0;
    private static final int DISCOVER_BT = 1;
    private static final int REQUEST_CONECTION = 2;

    boolean Conexao = false;

    BluetoothAdapter mBluetoothAdapter = null;

    Button btnConectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BOTÕES

        btnConectar = (Button) findViewById(R.id.btnConectar);

        //VERIFICA SE APARELHO POSSUI BLUETOOTH

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Seu dispositivo não possui bluetooth", Toast.LENGTH_LONG).show();
            finish();


        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent ATIVAR = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(ATIVAR, REQUEST_BT);


        } else {
            Intent DISCOVERINTENT = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivityForResult(DISCOVERINTENT, DISCOVER_BT);
        }

        // AO PRECISAR BOTAO CONECTAR

        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Conexao) {
                    //desconectar
                }
                else {
                    //conectar

                    Intent abreLista = new Intent (MainActivity.this, ListaDispositivos.class);
                    startActivityForResult(abreLista,REQUEST_CONECTION );
                }
            }
        });


    }
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case REQUEST_BT:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(getApplicationContext(), "Bluetooth ativado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "O app será finalizado", Toast.LENGTH_LONG).show();
                    finish();
                }


        }
    }

}








