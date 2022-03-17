/*======================================================================================================================================
| READ ME
========================================================================================================================================
| protected void onCreate(Bundle savedInstanceState)                                    on create
=======================================================================================================================================*/

package com.vectorspace.googlesignin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class SecondActivity extends AppCompatActivity {

    // declare
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email;
    Button btn_Sign_Out;

    // ==============================
    // on create
    // ==============================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // find view
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        btn_Sign_Out = findViewById(R.id.btn_sign_out);

        // initiate Google Sign In
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        // get last Google sign in account
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        // get Google account name & email
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }
    }
}