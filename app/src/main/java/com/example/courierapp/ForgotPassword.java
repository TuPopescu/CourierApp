package com.example.courierapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class ForgotPassword extends AppCompatActivity {
    private TextInputEditText textInputEditTextEmail;

    private DatabaseHelper databaseHelper;

    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Objects.requireNonNull(getSupportActionBar()).hide();

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        AppCompatButton appCompatButtonConfirm = (AppCompatButton) findViewById(R.id.appCompatButtonConfirm);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        databaseHelper = new DatabaseHelper(this);

        setTitle("Recover password");
        appCompatButtonConfirm.setOnClickListener(view -> verifyFromSQLite());
    }

    private void verifyFromSQLite(){
        if (Objects.requireNonNull(textInputEditTextEmail.getText()).toString().isEmpty()){
            Toast.makeText(this, "Please fill your email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){
            Intent accountsIntent = new Intent(this, ConfirmPassword.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
        else{
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText("");
    }
}
