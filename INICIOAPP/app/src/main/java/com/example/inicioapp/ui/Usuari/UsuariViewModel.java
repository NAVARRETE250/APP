package com.example.inicioapp.ui.Usuari;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UsuariViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UsuariViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("PANTALLA DE ENMEDIO");
    }

    public LiveData<String> getText() {
        return mText;
    }
}