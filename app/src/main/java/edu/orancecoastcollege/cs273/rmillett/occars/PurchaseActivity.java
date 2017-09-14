package edu.orancecoastcollege.cs273.rmillett.occars;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class PurchaseActivity extends AppCompatActivity {

    // Connections to the VIEW
    private EditText mPriceEditText;
    private EditText mDownPaymentEditText;
    private RadioButton mThreeYearsRadioButton;
    private RadioButton mFourYearsRadioButton;
    private RadioButton mFiveYearsRadioButton;

    // Connections to the MODEL
    private CarLoan mCarLoan = new CarLoan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_old);

        // Use findViewById to connect controller to each view
        mPriceEditText = (EditText) findViewById(R.id.carPriceEditText);
        mDownPaymentEditText = (EditText) findViewById(R.id.downPaymentEditText);
        mThreeYearsRadioButton = (RadioButton) findViewById(R.id.year3RadioButton);
        mFourYearsRadioButton = (RadioButton) findViewById(R.id.year4RadioButton);
        mFiveYearsRadioButton = (RadioButton) findViewById(R.id.year5RadioButton);
    }

    private void collectCarLoanData() {
        mCarLoan.setPrice(Double.parseDouble(mPriceEditText.getText().toString()));
        mCarLoan.setDownPayment(Double.parseDouble(mDownPaymentEditText.getText().toString()));

        if (mThreeYearsRadioButton.isChecked()) mCarLoan.setTerm(3);
        else if (mFourYearsRadioButton.isChecked()) mCarLoan.setTerm(4);
        else mCarLoan.setTerm(5);
    }

    protected void reportSummary(View v) {
        collectCarLoanData();
        String report = "Monthly Payment: $" + mCarLoan.monthlyPayment();
        // ...continue building report...

        // Intents start new activities and can share data with them
        Intent launchLoanSummary = new Intent(this, LoanSummaryActivity.class);
        // Put data into the Intent for LoanSummary to receive
        launchLoanSummary.putExtra("loanReport", report);
        // Start the second activity
        startActivity(launchLoanSummary);
    }
}
