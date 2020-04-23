package com.example.logind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logind.databinding.ActivityMainBinding;

import viewModel.LoginViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        openOptionsMenu();
        stateWindow();
        ActivityMainBinding  _bindingEmail = DataBindingUtil.setContentView(this,R.layout.activity_main);
        _bindingEmail.setEmailModel(new LoginViewModel(this,_bindingEmail,null));

    }

    private void stateWindow() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.setStatusBarColor(this.getResources().getColor(R.color.agua_oscuro,null));
            }
        }
    }
}
