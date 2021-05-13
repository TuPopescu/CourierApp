package com.example.courierapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class ConfirmPassword extends AppCompatActivity {
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private DatabaseHelper databaseHelper;
    private NestedScrollView nestedScrollView;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
        Objects.requireNonNull(getSupportActionBar()).hide();

        databaseHelper = new DatabaseHelper(this);

        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputpass);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputConfirmPassword);

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        AppCompatButton appCompatButtonReset = (AppCompatButton) findViewById(R.id.appCompatButtonReset);

        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");

        setTitle("Reset password");

        appCompatButtonReset.setOnClickListener(view -> updatePassword());
    }

    private void updatePassword(){
        String value1 = Objects.requireNonNull(textInputEditTextPassword.getText()).toString().trim();
        String value2 = Objects.requireNonNull(textInputEditTextConfirmPassword.getText()).toString().trim();

        if (value1.isEmpty() && value2.isEmpty()){
            Toast.makeText(this, "Fill in all fields!", Toast.LENGTH_LONG).show();
            return;
        }

        if (!value1.contentEquals(value2)){
            Toast.makeText(this, "Passwords don't match!", Toast.LENGTH_LONG).show();
            return;
        }

        if (!databaseHelper.checkUser(email)){
            Snackbar.make(nestedScrollView, "Email doesn't exist!", Snackbar.LENGTH_LONG).show();
        }
        else{
            databaseHelper.updatePassword(email, value1);
            Toast.makeText(this, "Password reset successful!", Toast.LENGTH_SHORT).show();
            emptyInputEditText();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            ActivityCompat.finishAfterTransition(this);
        }
    }

    private void emptyInputEditText(){
        textInputEditTextPassword.setText("");
        textInputEditTextConfirmPassword.setText("");
    }
}
