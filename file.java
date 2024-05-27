MainActivity
package com.fare4z.pspflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Declare widget
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Instance
        btnEnter = findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PersonalActivity.class);
                startActivity(i);
            }
        });
    }
}
















personal
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






















Subcription
package com.fare4z.pspflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SubscriptionActivity extends AppCompatActivity {
    Button btnSubmit;
    RadioGroup rgPackage;
    EditText etDuration , etDiscount;

    Double intPrice = 0.00;
    Double dblPricePayment, dblDisc, dblPriceAfterDisc, dblFinalPrice;
    String strDiscount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        btnSubmit = findViewById(R.id.btnSubmit);
        rgPackage = findViewById(R.id.rgPackage);
        etDiscount = findViewById(R.id.etDiscount);
        etDuration = findViewById(R.id.etDuration);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int SelectedID = rgPackage.getCheckedRadioButtonId();
                RadioButton rbPackage = findViewById(SelectedID);

                if (rbPackage.getId() == R.id.rbBasic) {
                    intPrice = 17.00;
                } else if (rbPackage.getId() == R.id.rbStandard) {
                    intPrice = 28.00;
                } else if (rbPackage.getId() == R.id.rbPremium) {
                    intPrice = 45.00;
                }

                dblPricePayment = intPrice * Double.parseDouble(etDuration.getText().toString());

                Toast.makeText(SubscriptionActivity.this, dblPricePayment.toString(), Toast.LENGTH_SHORT).show();

                if (etDiscount.getText().toString().equals("JOMBELAJAR")) {
                    dblDisc = 0.10;
                    dblPriceAfterDisc = Double.parseDouble(String.format("%.2f", dblPricePayment * dblDisc));
                    dblFinalPrice = dblPricePayment - dblPriceAfterDisc;
                    strDiscount = etDiscount.getText().toString() + "(-RM" + dblPriceAfterDisc + ")" ;
                } else {
                    dblFinalPrice = dblPricePayment;
                    strDiscount = "N/A";
                    Toast.makeText(SubscriptionActivity.this, "Invalid discount code", Toast.LENGTH_SHORT).show();
                }

                Bundle bundle = getIntent().getExtras();
                Intent i = new Intent(getApplicationContext(), InvoiceActivity.class);
                Bundle dataSubscription = new Bundle();
                dataSubscription.putString("package", rbPackage.getText().toString());
                dataSubscription.putString("duration", etDuration.getText().toString());
                dataSubscription.putString("discount", strDiscount);
                dataSubscription.putDouble("pricePayment", dblPricePayment);
                dataSubscription.putDouble("finalPrice", dblFinalPrice);

                i.putExtras(bundle);
                i.putExtras(dataSubscription);


                startActivity(i);
                Toast.makeText(SubscriptionActivity.this, "Please make payment within 15 Minutes", Toast.LENGTH_SHORT).show();


            }
        });
    }
}






















Invoice
package com.fare4z.pspflix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class InvoiceActivity extends AppCompatActivity {

    TextView tvOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        tvOutput = findViewById(R.id.tvOutput);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String state = bundle.getString("state");
        String age = bundle.getString("age");
        String packages = bundle.getString("package");
        String duration = bundle.getString("duration");
        String discount = bundle.getString("discount");

        Double PricePayment = bundle.getDouble("pricePayment");
        Double finalPrice = bundle.getDouble("finalPrice");

        Double tax = finalPrice * 0.06;
        finalPrice = finalPrice + tax;

        StringBuilder sb = new StringBuilder();
        sb.append("Name : ").append(name).append("\n")
                .append("State : ").append(state).append("\n")
                .append("Age : ").append(age).append("\n")
                .append("Plan : ").append(packages).append("\n")
                .append("Duration : ").append(duration + " Months").append("\n")
                .append("Price : RM").append(String.format("%.2f",PricePayment)).append("\n")
                .append("Discount : ").append(discount).append("\n")
                .append("Service Tax (6%) : RM").append(String.format("%.2f",tax)).append("\n")
                .append("Total : RM").append(String.format("%.2f",finalPrice)).append("\n");

        tvOutput.setText(sb);


    }
}
