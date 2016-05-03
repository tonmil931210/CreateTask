package com.example.miltoncasanovag.createactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private String url = "https://testlogindb.firebaseio.com/";
    private EditText edtRadio;
    private EditText edtCentro;
    private EditText edtName;
    private Button btnCrearActivity;
    Firebase rootUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        rootUrl = new Firebase(url);
    }

    public void btnCrearActibity(View view) {
        edtName = (EditText) findViewById(R.id.edtName);
        edtCentro = (EditText) findViewById(R.id.edtCentro);
        edtRadio = (EditText) findViewById(R.id.edtRadio);
        rootUrl.child("Activitys/" + edtName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    Map<String, String> post1 = new HashMap<String, String>();
                    post1.put("Radious", edtRadio.getText().toString());
                    post1.put("Center", edtCentro.getText().toString());
                    rootUrl.child("Activitys").child(edtName.getText().toString()).setValue(post1);
                } else {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "ya existe actividad", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
}
