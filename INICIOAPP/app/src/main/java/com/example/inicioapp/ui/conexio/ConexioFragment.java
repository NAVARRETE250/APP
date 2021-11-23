package com.example.inicioapp.ui.conexio;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
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
import com.example.inicioapp.ui.Usuari.UsuariFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private Interfaz interfaz;
    private String username;
    boolean usernameExiste = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        handler = new Handler(Looper.getMainLooper());
        View v = inflater.inflate(R.layout.fragment_conexio, container, false);
        semaforo = v.findViewById(R.id.imageView);

        Button button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UsuariFragment.getUsername().equals("")){
                    Toast.makeText(getContext(), "Introduce un usuario primero", Toast.LENGTH_LONG).show();
                }else {
                    EditText et1 = (EditText) v.findViewById(R.id.ip);
                    String texto = et1.getText().toString();
                    ip = texto;
                    new Conn().execute();
                }
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

                Client client = new Client(ip, 7771, callHandler);

                interfaz = (Interfaz) client.getGlobal(Interfaz.class);

                username = UsuariFragment.getUsername();

                ArrayList<String> usernames = interfaz.getAllUsernames();



                if(usernames.contains(username)){
                    AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                    final EditText edittext = new EditText(getContext());
                    alert.setMessage("Introduce otro username");
                    alert.setTitle("USERNAME YA ESCOGIDO");

                    alert.setView(edittext);

                    alert.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    });

                    alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    });

                    AlertDialog dialog = alert.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View view) {
                            Editable YouEditTextValue = edittext.getText();
                            username = YouEditTextValue.toString();
                            if(usernames.contains(username)){
                                Toast.makeText(getContext(), "El username ya existe", Toast.LENGTH_LONG).show();
                            }else {
                                interfaz.sendUsername(username);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        semaforo.setImageResource(R.drawable.btnnaranja);
                                    }
                                });

                                Toast.makeText(getContext(), "Se ha conectado correctamente", Toast.LENGTH_LONG).show();

                                try {
                                    client.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                dialog.dismiss();
                            }
                        }
                    });
                }else {

                    interfaz.sendUsername(username);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            semaforo.setImageResource(R.drawable.btnnaranja);
                        }
                    });

                    Toast.makeText(getContext(), "Se ha conectado correctamente", Toast.LENGTH_LONG).show();

                    client.close();
                }

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