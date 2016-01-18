package com.ocbiofuel.collection;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import org.apache.http.impl.client.DefaultHttpClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by scott on 1/17/16.
 */
public class postCollect extends Activity {


    private int year;
    private int month;
    private int day;
    private EditText mDateDisplay;
    private Button mPickDate;

    static final int DATE_DIALOG_ID = 1;

    DefaultHttpClient client = new DefaultHttpClient();
    ProgressDialog pd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collectionpost);



        EditText mDateDisplay = (EditText) findViewById(R.id.dateCollected);
        Button mPickDate = (Button) findViewById(R.id.btnChangeDate);
        Button post = (Button)findViewById(R.id.postcollection);
        EditText sid = (EditText)findViewById(R.id.siteID);
        EditText driverid = (EditText)findViewById(R.id.driverID);


        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date and sets and initial value into the text field mDateDisplay
        final Calendar c = Calendar.getInstance();
        String dateString[] = formatDate(c);

        String year = dateString[0].trim();
        String month = dateString[1].trim();
        String day = dateString[2].trim();

        String sDate = year + "-" + month + "-" + day;

        mDateDisplay.setText(sDate);


    }

    public String[] formatDate(Calendar c) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(c.getTime());
        String[] dateData = dateString.split("-");


        return dateData;


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:

                Calendar cal = Calendar.getInstance();
                int m1Year;
                int m1Month;
                int m1Day;


                m1Year = cal.get(Calendar.YEAR);
                m1Month = cal.get(Calendar.MONTH);
                m1Day = cal.get(Calendar.DAY_OF_MONTH);


                return new DatePickerDialog(this,
                        mDateSetListener,
                        m1Year, m1Month, m1Day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
                    Calendar c = Calendar.getInstance();
                    c.set(year, monthOfYear, dayOfMonth);
                    String dateString[] = formatDate(c);

                    String year1 = dateString[0].trim();
                    String month1 = dateString[1].trim();
                    String day1 = dateString[2].trim();

                    String sDate = year1 + "-" + month1 + "-" + day1;
                    EditText mDateDisplay = (EditText) findViewById(R.id.dateCollected);
                    mDateDisplay.setText(sDate);

                }

            };
} //**end of Activity
