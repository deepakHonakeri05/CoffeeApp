package com.your.company.name.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int no_of_cups = 0;
    int price = 2000;

    String summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void increaseCups(View v)
    {
        no_of_cups = no_of_cups + 1;   // 0 + 1 = 1;

        //we need to display the no_of_cups = 1

        //we reference TextView
        TextView noOfCupsTextView = findViewById(R.id.quantityCups);

        noOfCupsTextView.setText(no_of_cups+"");

        priceTotal();
        updateSummary();
    }

    public void decreaseCups(View v)
    {

        if(no_of_cups > 0 ) //0 is not greater than 0
            no_of_cups = no_of_cups - 1;   // 0 + 1 = 1;

        //we need to display the no_of_cups = 1

        //we reference TextView
        TextView noOfCupsTextView = findViewById(R.id.quantityCups);

        noOfCupsTextView.setText(no_of_cups+""); //display 0

        priceTotal();
        updateSummary();
    }

    public void priceTotal()
    {
        int totalPrice = 0;

        if(no_of_cups > 0)
            totalPrice = price*no_of_cups;
            // initally our price = 2000
            // price = 2*2000 = 4000
            // we are keeping the price constant  and not increasing the price

        TextView totalTextView = findViewById(R.id.total);
        totalTextView.setText(totalPrice+"Rp.");

    }

    public void updateSummary()
    {
        String name="Name : Potter\n";

        CheckBox whippedCreamCheckBox = findViewById(R.id.check1);
        Boolean isWhippedCreamSelected = whippedCreamCheckBox.isChecked();
        String addedWhippedCream = "Added Whipped Cream ? "+ isWhippedCreamSelected+"\n";

        CheckBox toppingNutellaCheckBox = findViewById(R.id.check2);
        Boolean isToppingNutellaChecked = toppingNutellaCheckBox.isChecked();
        String addedNutella = "Added Nutella ? "+ isToppingNutellaChecked+"\n";

        String quantity="Quantity : " +no_of_cups+"\n";
        String total="Total : " + price*no_of_cups+"\n";
        String thank="Thank You"+"\n";

        summary = name + addedWhippedCream + addedNutella + quantity + total + thank;

        TextView totalTextView = findViewById(R.id.summaryTextView);
        totalTextView.setText(summary);


    }

    public  void order(View v)
    {
        Toast.makeText( getApplicationContext(), "Order Placed!",Toast.LENGTH_SHORT).show();

        CheckBox sendEmail = findViewById(R.id.check3);
        Boolean isEmailSelected = sendEmail.isChecked();

        if(isEmailSelected == true) {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"potter@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee order App");
            intent.putExtra(Intent.EXTRA_TEXT, ""+summary);
            startActivity(intent);
        }
    }

}
//            Intent intent = new Intent(Intent.ACTION_SENDTO);
//
//            intent.setData(Uri.parse("mailto:"));
//
//            intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
//
//            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//
//            intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
//
//            startActivity(intent);