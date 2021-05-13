package com.example.courierapp;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;


import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;
    private AppCompatTextView textViewLinkForgotPassword;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    private CallbackManager callbackManager;
    private LoginButton loginButton;

    private CircleImageView circleImageView;
    private TextView txtEmail;
    private TextView txtName;
    private int t = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        if (Preference.getEmail(this) != null){
            Intent intent = new Intent(LoginActivity.this, UsersActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        loginButton = findViewById(R.id.login_button);
        circleImageView = findViewById(R.id.profile_image);
        txtName = findViewById(R.id.fbName);
        txtEmail = findViewById(R.id.user_email);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setPermissions(Arrays.asList("email","public_profile"));

        checkLoginStatus();


        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
<<<<<<< HEAD
                /*initDeliveries();
                t = 1;
=======
                initDeliveries();
>>>>>>> 6beb248675fd29ae3b2dd74b783a7c2760a760b9
                Intent homeIntent = new Intent(LoginActivity.this, UsersActivity.class);
                startActivity(homeIntent);
                finish();*/
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        //if(t==0){
        initViews();
        initListeners();
        initObjects();
        initDeliveries();
    }

    private void initViews(){
        nestedScrollView = findViewById(R.id.nestedScrollView);

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = findViewById(R.id.textViewLinkRegister);
        textViewLinkForgotPassword = findViewById(R.id.forgotPassword);
    }

    private void initListeners(){
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
        textViewLinkForgotPassword.setOnClickListener(this);
    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    private void initDeliveries(){
        Delivery delivery;
        delivery = new Delivery(0, "Mihaela Stefania", "Iolanda Dacian", "Bulevardul Timisoara nr. 17A");
        databaseHelper.addDelivery(delivery);
        delivery = new Delivery(0, "Radu Dorinel", "Matei Cosmin", "Piata Alexandru Lahovari 1");
        databaseHelper.addDelivery(delivery);
        delivery = new Delivery(0, "Ana Maria Simona", "Manuel Cristian", "Calea Calarasi nr. 172");
        databaseHelper.addDelivery(delivery);
        delivery = new Delivery(0, "Isac Cezara", "Aurel Filip", "Soseaua Pantelimon nr. 340-342");
        databaseHelper.addDelivery(delivery);
        delivery = new Delivery(0, "Alexandru Lazar", "Paul Miron", "Strada Garleanu Emil nr. 13");
        databaseHelper.addDelivery(delivery);
        delivery = new Delivery(0, "Ilie Iuliana", "Carmen Natalia", "Strada Frumoasa 54");
        databaseHelper.addDelivery(delivery);
        delivery = new Delivery(0, "Constantin Cosmin", "Manuela Cristiana", "Strada Pargarilor 63");
        databaseHelper.addDelivery(delivery);
        delivery = new Delivery(0, "Viorela Valeria", "Livia Dana", "Strada Brancusi Constantin nr. 23");
        databaseHelper.addDelivery(delivery);
        delivery = new Delivery(0, "Alexandru Narcisa", "Ioana Florentin", "Strada Pajurei nr. 15");
        databaseHelper.addDelivery(delivery);
        delivery = new Delivery(0, "Mihaela Costel", "Eugen Ieronim", "Strada Lipscani nr. 19");
        databaseHelper.addDelivery(delivery);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
        if (currentAccessToken==null)
        {
            txtName.setText("");
            txtEmail.setText("");
            circleImageView.setImageResource(0);
            Toast.makeText(LoginActivity.this,"User Logged out",Toast.LENGTH_LONG).show();
        }
        else
            loadUserProfile(currentAccessToken);
        }
    };

    private void loadUserProfile(AccessToken newAccessToken){
        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String name = first_name + " " + last_name;
                    if (!databaseHelper.checkUser(email)) {
                        User user = new User();
                        user.setName(name);
                        user.setEmail(email);
                        String password = "123456";
                        user.setPassword(password);
                        databaseHelper.addUser(user);
                        Preference.saveEmail(email, getApplicationContext());
                        Preference.savePassword(password, getApplicationContext());
                        Intent homeIntent = new Intent(LoginActivity.this, UsersActivity.class);
                        startActivity(homeIntent);
                        finish();
                    }
                    else
                    {
                        Intent homeIntent = new Intent(LoginActivity.this, UsersActivity.class);
                        startActivity(homeIntent);
                        finish();
                    }
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/"+id+"/picture?type=normal";

                    txtEmail.setText(email);
                    txtName.setText(first_name + " " + last_name);

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                    Glide.with(LoginActivity.this).load(image_url).into(circleImageView);

                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void checkLoginStatus(){
        if (AccessToken.getCurrentAccessToken()!=null)
            loadUserProfile(AccessToken.getCurrentAccessToken());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.forgotPassword:
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
        }
    }

    private void verifyFromSQLite(){
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))){
            return;
        }

        String email = Objects.requireNonNull(textInputEditTextEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(textInputEditTextPassword.getText()).toString().trim();

        if (databaseHelper.checkUser(email, password)){
            Preference.saveEmail(email, this);
            Preference.saveName(email, this);
            Preference.savePassword(password, this);
            Intent accountsIntent = new Intent(activity, UsersActivity.class);
            accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
        else {
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}