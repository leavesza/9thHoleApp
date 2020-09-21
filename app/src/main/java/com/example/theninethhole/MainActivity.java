package com.example.theninethhole;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.theninethhole.Model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText nameInput,memberNrInput;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = (EditText)findViewById(R.id.nameInput);
        memberNrInput = (EditText)findViewById(R.id.memberNrInput);
        btnSignIn = (Button)findViewById(R.id.signInBtn);

        //Init Firebase Database
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = firebaseDatabase.getReference("User");



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //progress dialog pop up
                final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Check if user exists
                        if(dataSnapshot.child(memberNrInput.getText().toString()).exists()) {

                            //Getting user information
                            mDialog.dismiss();

                            User username = dataSnapshot.child(nameInput.getText().toString()).getValue(User.class);
                            User membNo = dataSnapshot.child(memberNrInput.getText().toString()).getValue(User.class);

                            if (membNo.getMembershipNo().equals(memberNrInput.getText().toString()) & username.getName().equals(nameInput.getText().toString())) {
                                Toast.makeText(MainActivity.this, "Sign in succesfully!", Toast.LENGTH_SHORT).show();

                            } else {
                                mDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "User does not exist!", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}