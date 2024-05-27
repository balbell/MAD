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
