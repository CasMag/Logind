package com.example.logind.ui.empalme;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.logind.db.EmpalmeRepositorio;
import com.example.logind.db.entidades.EmpalmeEntity;

import java.util.List;

public class EmpalmeFDialogViewModel extends AndroidViewModel {

    private LiveData<List<EmpalmeEntity>> allEmpalmes;
    private EmpalmeRepositorio empalmeRepositorio;

    public EmpalmeFDialogViewModel(Application application){
        super(application);
        empalmeRepositorio = new EmpalmeRepositorio(application);
        allEmpalmes = empalmeRepositorio.getAllempalme();

    }
    //el fragmento que necesita recibir la nueva lista de empalmes, metodo de consulta
    public LiveData<List<EmpalmeEntity>> getAllEmpalmes(){return allEmpalmes;}

    //el fragment que inserta una nueva nota ,debera comunicar al viewmodel, metodo de insercion
    public void insertarEmpalmess(EmpalmeEntity nuevoEmpalme){empalmeRepositorio.insertEmpalm(nuevoEmpalme);}

    //la actividad que aptauliza los datos del empalme
    public void updateEmpalmess(EmpalmeEntity editEmpalme){empalmeRepositorio.updateEmpalme(editEmpalme);
    }
    //eliminar empalme por id
    public void deleteEmpalmeById(EmpalmeEntity deletEmpalme){empalmeRepositorio.deletEmpalme(deletEmpalme);}

}