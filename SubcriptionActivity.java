
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
