package com.hugodemont.parionsmmi;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }



public void get_info_user(View view){
    ImageView profile_image = findViewById(R.id.profile_image_id);
    TextView name_txt = findViewById(R.id.name_txt_id);
    TextView email_txt = findViewById(R.id.email_txt_id);

}

    //Fonction pour enregistrement
    public void load_after_reg(View view) {

        EditText email_input = findViewById(R.id.idEmailRegister);
        String email_input_string = email_input.getText().toString();

        EditText password_input = findViewById(R.id.idRegisterPassword);
        String password_input_string = password_input.getText().toString();

        EditText pseudo_input = findViewById(R.id.idNicknameRegister);
        String pseudo_input_string = pseudo_input.getText().toString();


        //test if the condition are fulfilled
        //test the e-mail
        if (email_input_string.contains("@") && email_input_string.contains(".") && email_input_string.length() > 5) {
            //test the password
            if (password_input_string.length() >= 7 && password_input_string.length() <= 20) {
                //test the pseudo length
                if (pseudo_input_string.length() <= 15 && pseudo_input_string.length() >= 3) {

                    if (!pseudo_input_string.contains(" ") && !pseudo_input_string.contains(":") && !pseudo_input_string.contains("\"") && !pseudo_input_string.contains("|") && !pseudo_input_string.contains("#")) {

                        //declare the response to the incoming request for register
                        Response.Listener<String> responseListener = response -> {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                //if the user was registered
                                if (success) {
                                    //Account created with success!! A message is showed
                                    Toast.makeText(MainActivity.this, "Account created",
                                            Toast.LENGTH_SHORT).show();

                                    setContentView(R.layout.home);

                                } else {
                                    //If there was an error
                                    Toast.makeText(MainActivity.this, "Account creation failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                try {
                                    //Fetch the custom error message
                                    JSONObject jsonResponse = new JSONObject(response);
                                    String error = jsonResponse.getString("success");
                                    Toast.makeText(MainActivity.this, error,
                                            Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                } catch (JSONException e2) {
                                    //If everything has gone wrong put the error message
                                    Toast.makeText(MainActivity.this, "Error, try again later",
                                            Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }
                        };
                        //Register user with email, pseudo, and username
                        RegisterRequest registerRequest = new RegisterRequest(MainActivity.this, email_input_string, pseudo_input_string, password_input_string, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                        queue.add(registerRequest);
                    } else {
                        Toast.makeText(MainActivity.this, "The pseudo can't contains spaces or special characters",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                //if the pseudo is to long
                else {
                    Toast.makeText(MainActivity.this, "The pseudo must be between 3 and 15 character",
                            Toast.LENGTH_SHORT).show();
                }
            }
            //if the password isn't valid
            else {
                Toast.makeText(MainActivity.this, "The password is not valid.",
                        Toast.LENGTH_SHORT).show();
            }
        }
        //if the email isn't valid
        else {
            Toast.makeText(MainActivity.this, "The e-mail is not valid.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    //LOGIN FONCTION
    public void load_after_log(View view) {

        EditText email_input = findViewById(R.id.idEmailRegister);
        String email_input_string = email_input.getText().toString();

        EditText password_input = findViewById(R.id.idPasswordLogin);
        String password_input_string = password_input.getText().toString();

        if (email_input_string.contains("@") && email_input_string.contains(".") && email_input_string.length() > 5) {
            //test the password
            if (password_input_string.length() >= 7 && password_input_string.length() <= 20) {

                Response.Listener<String> responseListener = response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        //if the user was registered
                        if (success) {
                            //Account created with success!! A message is showed
                            Toast.makeText(MainActivity.this, "Your connected",
                                    Toast.LENGTH_SHORT).show();

                            setContentView(R.layout.home);

                        } else {
                            //If there was an error
                            Toast.makeText(MainActivity.this, "Retry you don't have an account with this information(s)",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        try {
                            //Fetch the custom error message
                            JSONObject jsonResponse = new JSONObject(response);
                            String error = jsonResponse.getString("success");
                            Toast.makeText(MainActivity.this, error,
                                    Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        } catch (JSONException e2) {
                            //If everything has gone wrong put the error message
                            Toast.makeText(MainActivity.this, "Error, try again later",
                                    Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest LoginRequest = new LoginRequest(MainActivity.this, email_input_string, password_input_string, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(LoginRequest);

            } else {
                Toast.makeText(MainActivity.this, "The password is not valid.",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "The e-mail is not valid.",
                    Toast.LENGTH_SHORT).show();
        }

    }



    //Button pour load la page register
    public void load_register_layout(View view) {
        setContentView(R.layout.register);
    }
    //Button pour load la page login
    public void load_login_layout(View view) {
        setContentView(R.layout.activity_main);
    }




}


