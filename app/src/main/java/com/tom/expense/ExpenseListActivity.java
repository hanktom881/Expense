package com.tom.expense;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class ExpenseListActivity extends Activity {
    DBHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);
        dbHelper = new DBHelper(this, "expense.db", null, 1);
        Cursor c = dbHelper.getReadableDatabase()
                .query("expense", null, null, null, null, null, null);
        String[] from = {"ctext", "amount"};
        int[] to = {android.R.id.text1, android.R.id.text2};
        SimpleCursorAdapter adapter = new
                SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c, from, to, 1);
        /*while(c.moveToNext()){
            int id = c.getInt(c.getColumnIndex("_id"));
            String cdate = c.getString(c.getColumnIndex("cdate"));
            String info = c.getString(c.getColumnIndex("ctext"));
            int amount = c.getInt(c.getColumnIndex("amount"));
            Log.d("EXP", id+"/"+cdate+"/"+info+"/"+amount);
        }*/
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense_list, menu);
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
