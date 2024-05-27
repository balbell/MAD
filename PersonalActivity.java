package com.fare4z.pspflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonalActivity extends AppCompatActivity {
    EditText etName, etAge, etState;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etState = findViewById(R.id.etState);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PersonalActivity.this, SubscriptionActivity.class);
                Bundle bundle = new Bundle();

                bundle.putString("name",etName.getText().toString());
                bundle.putString("age", etAge.getText().toString());
                bundle.putString("state", etState.getText().toString());

                i.putExtras(bundle);
                startActivity(i);

            }
        });
    }
}
