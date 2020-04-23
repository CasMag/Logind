package com.example.logind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrarUsuarios extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextInputLayout edtNombUsu, edtApellUsu, edtEmailUsu, edtPassUsu, edtTelcUsu;
    private ProgressBar progressBar;
    private Button btnRegiUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stateWindow();
        setContentView(R.layout.activity_registrar_usuarios);
        edtNombUsu = findViewById(R.id.txtimpuNombreUsu);
        edtApellUsu = findViewById(R.id.txtImpuApellidosUsu);
        edtEmailUsu = findViewById(R.id.txtImpuCorreoUsu);
        edtPassUsu = findViewById(R.id.txtImpPasswordUsu);
        edtTelcUsu = findViewById(R.id.txtImpNumberUsu);

        progressBar = findViewById(R.id.progressBarR_usu);
        mAuth = FirebaseAuth.getInstance();
       /* if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Acitvity_MenuPrincipal.class));
            finish();
        }*/

        btnRegiUsu = findViewById(R.id.btnRegistrarUsuario);
        btnRegiUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurandoRegistro();
            }
        });

    }

    private void configurandoRegistro() {
        String email = edtEmailUsu.getEditText().getText().toString();
        String pass = edtPassUsu.getEditText().getText().toString();

        if (TextUtils.isEmpty(email)) {
            edtEmailUsu.setError("crear email");
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            edtPassUsu.setError("crear contraseña");
            return;
        }

        if (pass.length() < 6) {
            edtPassUsu.setError("la contrseña debe ser >= 6 caracteres");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //registrar el usuario en firebase
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegistrarUsuarios.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Acitvity_MenuPrincipal.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                } else {
                    Toast.makeText(RegistrarUsuarios.this, "Error !!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void stateWindow() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(this.getResources().getColor(R.color.agua_oscuro, null));
            }
        }
    }
}
