package com.example.inicioapp.ui.Usuari;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inicioapp.databinding.FragmentUsuariBinding ;

public class UsuariFragment extends Fragment {

    private UsuariViewModel usuariViewModel;
    private FragmentUsuariBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        usuariViewModel =
                new ViewModelProvider(this).get(UsuariViewModel.class);

        binding = FragmentUsuariBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}