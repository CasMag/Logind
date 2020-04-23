package com.example.logind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logind.db.entidades.EmpalmeEntity;
import com.example.logind.ui.empalme.EmpalmeFDialogViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.ref.PhantomReference;
import java.util.List;
import java.util.Objects;

import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_DISTANCIA;
import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_ID_E;
import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_NOMBRE_EMPALME;
import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_NUM_POSTE;
import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_OBSERVACION;
import static com.example.logind.ui.empalme.EmpalmesFragment.EXTRA_TRAMO;

public class DetallesEmpalmes extends AppCompatActivity {

    private EmpalmeEntity empalmeEntity;
    private List<EmpalmeEntity> empalmeEntityList;

    private Button btnActualizarEmpl;
    private TextView txtId_empalme;
    public DetallesEmpalmes(){
    }
    private TextInputLayout edtempalmeDetail,edttramoDetail,edtdistanciaDetail,edtnumposteDetail,edtobservacionDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_empalmes);
        config();
    }

    private void config() {
        txtId_empalme = findViewById(R.id.textViewId_empalme);
        edtempalmeDetail = findViewById(R.id.editTextNempalmDetall);
        edttramoDetail = findViewById(R.id.editTextTramoDetalle);
        edtdistanciaDetail = findViewById(R.id.editTextDistanciaDetalles);
        edtnumposteDetail = findViewById(R.id.editTextNuPosteDetalles);
        edtobservacionDetail = findViewById(R.id.editTextObservDetalle);

        Intent intent = getIntent();
        String id_empalme = intent.getStringExtra(EXTRA_ID_E);
        String nombre_empalme = intent.getStringExtra(EXTRA_NOMBRE_EMPALME);
        String tramo = intent.getStringExtra(EXTRA_TRAMO);
        String distancia = intent.getStringExtra(EXTRA_DISTANCIA);
        String numero_poste = intent.getStringExtra(EXTRA_NUM_POSTE);
        String observacion = intent.getStringExtra(EXTRA_OBSERVACION);

        txtId_empalme.setText(id_empalme);
        edtempalmeDetail.getEditText().setText(nombre_empalme);
        edttramoDetail.getEditText().setText(tramo);
        edtdistanciaDetail.getEditText().setText(distancia);
        edtnumposteDetail.getEditText().setText(numero_poste);
        edtobservacionDetail.getEditText().setText(observacion);
    }

    public void onClickActualizarEmpalme(View view) {
        empalmeEntity.setId_e(Integer.parseInt(txtId_empalme.getText().toString()));
        empalmeEntity.setNombre_e(Objects.requireNonNull(edtempalmeDetail.getEditText()).getText().toString());
        empalmeEntity.setTramo(edttramoDetail.getEditText().getText().toString());
        empalmeEntity.setDistancia(Double.parseDouble(edtdistanciaDetail.getEditText().getText().toString()));
        empalmeEntity.setNumero_poste(Objects.requireNonNull(edtnumposteDetail.getEditText()).getText().toString());
        empalmeEntity.setObservacion(edtobservacionDetail.getEditText().getText().toString());

        EmpalmeFDialogViewModel empalmeFDialogViewModel = ViewModelProviders.of(this).get(EmpalmeFDialogViewModel.class);
        empalmeFDialogViewModel.updateEmpalmess(empalmeEntity);

        Toast.makeText(this, "Actualizado correctamente", Toast.LENGTH_SHORT).show();

        finish();
    }
}
