package com.ocbiofuel.collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;





public class CollectMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collectmain);

        Button collect = (Button)findViewById(R.id.buttonCollect);
        Button navigate = (Button)findViewById(R.id.buttonNavigate);
        Button maint = (Button)findViewById(R.id.btnData);




        collect.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent myIntent = new Intent(getBaseContext(), postCollect.class);
                startActivityForResult(myIntent, 0);


            }});

        maint.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent myIntent = new Intent(getBaseContext(), filemaintmain.class);
                startActivityForResult(myIntent, 0);


            }});


    }














}



