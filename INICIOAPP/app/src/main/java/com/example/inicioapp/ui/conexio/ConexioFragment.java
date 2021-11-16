package com.example.inicioapp.ui.conexio;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inicioapp.MainActivity;
import com.example.inicioapp.R;
import com.example.inicioapp.databinding.FragmentConexioBinding;

import java.io.IOException;

import conexion.Interfaz;
import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.Client;

public class ConexioFragment extends Fragment {

    private ConexioViewModel conexioViewModel;
    private FragmentConexioBinding binding;
    private String ip = "";
    private Handler handler;
    private ImageView semaforo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        handler = new Handler(Looper.getMainLooper());
        View v = inflater.inflate(R.layout.fragment_conexio, container, false);
        semaforo = v.findViewById(R.id.imageView);

        Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et1 = (EditText) v.findViewById(R.id.ip);
                String texto = et1.getText().toString();
                ip = texto;
                new Conn().execute();
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    class Conn extends AsyncTask<Void, Void, MainActivity> {


        protected MainActivity doInBackground(Void... params) {
            Looper.prepare();
            try {

                CallHandler callHandler = new CallHandler();

                Client client = new Client(ip, 7777, callHandler);

                Interfaz interfaz = (Interfaz) client.getGlobal(Interfaz.class);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        semaforo.setImageResource(R.drawable.btnnaranja);
                    }
                });

                Toast.makeText(getContext(), "Se ha conectado correctamente", Toast.LENGTH_LONG).show();
                client.close();
            } catch (IOException e) {
                Toast.makeText(getContext(), "No se ha conectado correctamente", Toast.LENGTH_LONG).show();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        semaforo.setImageResource(R.drawable.btnrojo);
                    }
                });
            }
            Looper.loop();
            return null;
        }

    }
}