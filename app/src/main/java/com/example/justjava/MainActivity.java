package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int numOfCoffe = 0;
    private String namee = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText name = (EditText) findViewById(R.id.name);
        String personName = name.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, personName);
//        displayMessage(priceMessage);
        composeEmail("alih7a@gmail.com","Coffee Order " + personName, priceMessage );
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
//    private void displayMessage(String message){
//        TextView orderUmmaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderUmmaryTextView.setText(message);
//    }


    /**
     * This method displays the given price on the screen.
     */


    private String createOrderSummary(int num, boolean hasWhippedCream, boolean hasChocolate, String personName){
        String message ="Name: " + personName + "\n";
        message+="Quantity: " + numOfCoffe + "\n";
        message+="Whipped Cream?\n" + hasWhippedCream + "\n";
        message+="Chocoalte?\n" + hasChocolate + "\n";
        message+="Total: $" + num + "\n";
        message+="Thank You!";

        return message;
    }

    public void decrement(View view) {
        numOfCoffe--;
        if (numOfCoffe<0)
            return;
        display(numOfCoffe);

    }

    public void increment(View view) {
        numOfCoffe++;
        if (numOfCoffe>100)
            return;
        display(numOfCoffe);

    }
    private int calculatePrice(boolean whippedCream, boolean choco){
        int basePrice = 5;
        if(whippedCream)
            basePrice++;
        if(choco)
            basePrice+=2;
        return numOfCoffe * basePrice;
    }

    public void composeEmail(String addresse, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresse);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }




}
