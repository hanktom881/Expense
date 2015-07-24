package com.tom.expense;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;


public class MainActivity extends Activity {
    DBHelper dbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this, "expense.db", null, 1);
    }

    public void list(View v){
        Intent intent = new Intent(this, ExpenseListActivity.class);
        startActivity(intent);
    }

    public void add(View v){
        EditText edDate = (EditText)findViewById(R.id.ed_date);
        DatePicker dPicker = (DatePicker) findViewById(R.id.datePicker);
        String d2 = new StringBuilder().append(dPicker.getYear()).append("-")
                .append(dPicker.getMonth()).append("-")
                .append(dPicker.getDayOfMonth()).toString();

        EditText edInfo = (EditText)findViewById(R.id.ed_info);
        EditText edAmount = (EditText)findViewById(R.id.ed_amount);
        String date = edDate.getText().toString();
        String info = edInfo.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());
        //INSERT INTO expense(cdate, ctext, amount) VALUES('aaa', 'bbb', 123)
        ContentValues values = new ContentValues();
//        values.put("cdate", date);
        values.put("cdate", d2);
        values.put("ctext", info);
        values.put("amount", amount);
        long id = dbHelper.getWritableDatabase().insert("expense", null, values);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
