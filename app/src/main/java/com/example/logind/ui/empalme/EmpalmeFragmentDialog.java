package com.example.logind.ui.empalme;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.logind.R;
import com.example.logind.db.entidades.EmpalmeEntity;
import com.google.android.material.textfield.TextInputLayout;

public class EmpalmeFragmentDialog extends DialogFragment {

    private View view;
    private TextInputLayout edtEmpalme,edtTramo,edtDistancia,edtNumPoste,edtObservacion;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.titulo);
        builder.setMessage(R.string.nuevo_datos_empalme)
                .setPositiveButton(R.string.guardar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String empalme = edtEmpalme.getEditText().getText().toString();
                        String tramo = edtTramo.getEditText().getText().toString();
                        String distancia = edtDistancia.getEditText().getText().toString();
                        String numposte = edtNumPoste.getEditText().getText().toString();
                        String observacion = edtObservacion.getEditText().getText().toString();
                        //comunicar la viewmodel el nuevo dato
                        EmpalmeFDialogViewModel empalmeFDialogViewModel =
                                ViewModelProviders.of(getActivity()).get(EmpalmeFDialogViewModel.class);
                        empalmeFDialogViewModel.insertarEmpalmess(new EmpalmeEntity(empalme, tramo, Double.parseDouble(distancia), numposte, observacion));

                        dialog.dismiss();

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        // User cancelled the dialog
                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_empalmes_dialog,null);

        edtEmpalme = view.findViewById(R.id.edtImpEmpalme);
        edtTramo = view.findViewById(R.id.edtImpTramo);
        edtDistancia = view.findViewById(R.id.edtImpDistancia);
        edtNumPoste = view.findViewById(R.id.edtImpNumPoste);
        edtObservacion = view.findViewById(R.id.edtImpObservacion);


        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
