package com.example.kur;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class HistoryActivity extends AppCompatActivity {
    private ListView list;
    private ArrayList<Integer> ids;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorOrangeFox)));
        int brown = getResources().getColor(R.color.colorBrownFox);
        String htmlColor = String.format(Locale.US, "#%06X", (0xFFFFFF & Color.argb(0, Color.red(brown), Color.green(brown), Color.blue(brown))));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='"+htmlColor+"'>Saved Sudoku</font>"));

        list = findViewById(R.id.list);

        HistoryTask ht = new HistoryTask();
        ht.execute(-1);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HistoryTask ht = new HistoryTask();
                ht.execute(0, position);
                finish();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                HistoryTask ht = new HistoryTask();
                ht.execute(1, position);

                String date = adapter.getItem(position);
                adapter.remove(date);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    class HistoryTask extends AsyncTask<Integer, Void, Void>{
        private ArrayList<Integer> idsH = new ArrayList<>();
        private ArrayList<String> datesH = new ArrayList<>();
        private int b;
        @Override
        protected Void doInBackground(Integer... integers) {
            switch (integers[0]) {
                case -1: {
                    b=-1;
                    SQLiteDatabase db;
                    SQLHelper help = new SQLHelper(getApplicationContext(), Commands.DB_VERS);
                    db = help.getWritableDatabase();

                    Cursor c = db.query(Commands.TABLE1_NAME, null, null, null, null, null, null);
                    while (c.moveToNext()) {
                        int id = c.getInt(c.getColumnIndex(Commands.TABLE1_ID));
                        String date = c.getString(c.getColumnIndex(Commands.TABLE1_DATE));
                        idsH.add(id);
                        datesH.add(date);
                    }
                    help.close();
                    break;
                }
                case 0:{
                    b=0;
                    int id = ids.get(integers[1]);
                    SQLiteDatabase db;
                    SQLHelper help = new SQLHelper(getApplicationContext(), Commands.DB_VERS);
                    db = help.getWritableDatabase();
                    Cursor cur = db.rawQuery(Commands.SELECT_CELLS, new String[]{String.valueOf(id)});
                    while(cur.moveToNext()){
                        int i = cur.getInt(cur.getColumnIndex(Commands.TABLE2_Y));
                        int j = cur.getInt(cur.getColumnIndex(Commands.TABLE2_X));
                        int v = cur.getInt(cur.getColumnIndex(Commands.TABLE2_VALUE));
                        MainActivity.setCellVal(i, j, v);
                    }
                    help.close();
                    break;
                }
                case 1:{
                    b=1;
                    int id = ids.get(integers[1]);
                    SQLiteDatabase db;
                    SQLHelper help = new SQLHelper(getApplicationContext(), Commands.DB_VERS);
                    db = help.getWritableDatabase();
                    db.execSQL(Commands.DELETE_TAB2+String.valueOf(id)+";");
                    db.execSQL(Commands.DELETE_TAB1+String.valueOf(id)+";");
                    help.close();
                    break;
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void voids) {
            if(b==-1) {
                ids = idsH;
                adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.row, datesH);
                list.setAdapter(adapter);
            }
            if(b==0){
                Toast toast = Toast.makeText(getApplicationContext(), "Готово", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
