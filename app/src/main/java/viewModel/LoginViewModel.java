package viewModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.logind.Acitvity_MenuPrincipal;
import com.example.logind.R;
import com.example.logind.RegistrarUsuarios;
import com.example.logind.VerificarPassword;
import com.example.logind.databinding.ActivityMainBinding;
import com.example.logind.databinding.ActivityVerificarPasswordBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import interfaces.IonClick;
import librerias.NetWorrks;
import librerias.Validate;
import models.BindableString;

public class LoginViewModel extends ViewModel implements IonClick {

    private Activity _activity;
    public static String emailData = null;
    private static ActivityMainBinding _bindingEmail;
    private static ActivityVerificarPasswordBinding _bindingPassword;
    public BindableString emailUI = new BindableString();
    public BindableString passwordUI = new BindableString();

    private FirebaseAuth mAuth;

    public LoginViewModel(Activity activity, ActivityMainBinding bindingEmail, ActivityVerificarPasswordBinding bindingPassword) {
       _activity = activity;
       _bindingEmail = bindingEmail;
       _bindingPassword =bindingPassword;
       if(emailData != null){
           emailUI.setValue(emailData);
       }
       mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNext:
                VerifyEmail();
                break;
           case R.id.btnSignIn:
                login();
                break;
            case R.id.txtRegistrarUsuarios:
                _activity.startActivity(new Intent(_activity, RegistrarUsuarios.class));
        }
    }
    private void VerifyEmail(){
        Boolean cancel = true;
        _bindingEmail.edtEnterEmail.setError(null);
        if(TextUtils.isEmpty(emailUI.getValue())){
            _bindingEmail.edtEnterEmail.setError(_activity.getString(R.string.erro_field_required));
            _bindingEmail.edtEnterEmail.requestFocus();
            cancel = false;
        }else if(!Validate.isEmail(emailUI.getValue())){
            _bindingEmail.edtEnterEmail.setError(_activity.getString(R.string.error_imeil_invalido));
            _bindingEmail.edtEnterEmail.requestFocus();
            cancel = false;
        }
        if(cancel){
            emailData = emailUI.getValue();
            _activity.startActivity(new Intent(_activity, VerificarPassword.class));
        }

    }
    private void login(){
        boolean cancel = true;
     _bindingPassword.edtPassword.setError(null);
     if(TextUtils.isEmpty(passwordUI.getValue())){
         _bindingPassword.edtPassword.setError(_activity.getString(R.string.erro_field_requiredpasword));
         cancel = false;
     }else if(!isPasswordValid(passwordUI.getValue())){
         _bindingPassword.edtPassword.setError(
                 _activity.getString(R.string.password_invalido));
         cancel = false;
     }
        if (cancel){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(new NetWorrks(_activity).verificarNetworks()){
                    mAuth.signInWithEmailAndPassword(emailData,passwordUI.getValue())
                            .addOnCompleteListener(_activity,(task)->{
                                if(task.isSuccessful()){
                                    _activity.startActivity(new Intent(_activity, Acitvity_MenuPrincipal.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                                }else {
                                    Snackbar.make(_bindingPassword.edtPassword,R.string.credencial_invalido,Snackbar.LENGTH_LONG).show();
                                }
                            });
                }else {
                    Snackbar.make(_bindingPassword.edtPassword,
                            R.string.netWorks,Snackbar.LENGTH_LONG).show();

                }
            }
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() >=6;
    }
}
