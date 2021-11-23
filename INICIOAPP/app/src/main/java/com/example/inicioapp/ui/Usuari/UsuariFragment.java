package com.example.inicioapp.ui.Usuari;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inicioapp.R;
import com.example.inicioapp.databinding.FragmentUsuariBinding ;

public class UsuariFragment extends Fragment {

    private static String username = "";
    private UsuariViewModel usuariViewModel;
    private FragmentUsuariBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_usuari, container, false);

        binding = FragmentUsuariBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText editUsername = v.findViewById(R.id.editTextTextPersonName);

        Button button = (Button) v.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                setUsername(String.valueOf(editUsername.getText()));
                Toast.makeText(getContext(), username, Toast.LENGTH_LONG).show();
            }
        });


        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UsuariFragment.username = username;
    }
}