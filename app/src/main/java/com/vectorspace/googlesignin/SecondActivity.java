/*======================================================================================================================================
| READ ME
========================================================================================================================================
| protected void onCreate(Bundle savedInstanceState)                                    on create
| private void signOut()                                                                sign out
=======================================================================================================================================*/

package com.vectorspace.googlesignin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SecondActivity extends AppCompatActivity {

    // declare
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name, email;
    Button btn_Sign_Out;
    ImageView profile_image;

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
        profile_image = findViewById(R.id.profile_image);

        // initiate Google Sign In
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        // get last Google sign in account
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        // get Google account info
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            // display information
            name.setText(personName);
            email.setText(personEmail);

            // display image
            Glide.with(this).load(personPhoto).into(profile_image);
        }

        // sign out btn onclick listener
        btn_Sign_Out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    // ==============================
    // sign out
    // ==============================
    private void signOut() {

        gsc.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}