package com.example.kur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button answ;
    private static EditText[][] cells;
    private boolean[][][] errcells;
    private final int PICK_IMAGE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        answ = findViewById(R.id.button_answer);
        TableLayout tabl = findViewById(R.id.tableLayout);
        Button photo = findViewById(R.id.button_photo);
        Button save = findViewById(R.id.button_save);
        Button history = findViewById(R.id.button_history);
        Button ac = findViewById(R.id.button_AC);
        errcells = new boolean[3][9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int p = 0; p < 3; p++) {
                    errcells[p][i][j] = false;
                }
            }
        }

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = (size.x - 80) / 10;

        cells = new EditText[9][9];//ячейки
        TableRow[] lines = new TableRow[9];//строки таблицы
        InputFilter[] filter = new InputFilter[1];
        filter[0] = new InputFilter.LengthFilter(1);//фильтр

        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(width, width);

        layoutParams.setMargins(1, 1, 1, 1);

        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);

            for (int i = 0; i < 9; i++) {
                lines[i] = new TableRow(this);
                for (int j = 0; j < 9; j++) {
                    cells[i][j] = new EditText(this);
                    cells[i][j].setLayoutParams(layoutParams);
                    cells[i][j].setPadding(1, 1, 1, 1);

                    cells[i][j].setInputType(InputType.TYPE_CLASS_NUMBER);
                    cells[i][j].setFilters(filter);

                    TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(cells[i][j], 2, 40, 2, TypedValue.COMPLEX_UNIT_SP);

                    cells[i][j].setGravity(Gravity.CENTER);
                    setBackGroundCell(i, j);
                    cells[i][j].setTextColor(getResources().getColorStateList(R.color.colorBrownFox));

                    f.set(cells[i][j], R.drawable.my_cursor);

                    final int x = i;
                    final int y = j;

                    cells[i][j].addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (!cells[x][y].getText().toString().equals("")&&!cells[x][y].getText().toString().equals("0")) {
                                if(errCheck(x, y)){
                                    answ.setClickable(false);
                                    answ.setBackgroundResource(R.color.colorLightBrownFox);
                                }
                                else {
                                    answ.setClickable(true);
                                    answ.setBackgroundResource(R.color.colorSplashScreen);
                                }
                            }
                            else{
                                answ.setClickable(true);
                                answ.setBackgroundResource(R.color.colorSplashScreen);
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });


                    lines[i].addView(cells[i][j]);
                }
                tabl.addView(lines[i]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        answ.setOnClickListener(new View.OnClickListener(){
            int[][] cellsValue;
            @Override
            public void onClick(View v) {
                cellsValue=new int[10][10];
                int n=0;
                for(int i=0; i<9; i++){
                    for(int j=0; j<9; j++){
                        if(cells[i][j].getText().toString().equals("")) cellsValue[i+1][j+1]=0;
                        else {
                            n++;
                            cellsValue[i+1][j+1]=Integer.parseInt(cells[i][j].getText().toString());
                        }
                    }
                }
                if(n<17){ //доказано, что в судоку не может быть меньше 17 известных цифр
                    Toast toast = Toast.makeText(getApplicationContext(), "Должно быть известно больше 17 цифр", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    n=0;
                    cellsValue = countTabl(cellsValue);
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if(cellsValue[i+1][j+1]==0){
                                n++;
                                if(n==1){
                                    Toast toast = Toast.makeText(getApplicationContext(), "Это для меня пока сложно", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                                cells[i][j].setText("");
                            }
                            else
                            cells[i][j].setText(String.valueOf(cellsValue[i + 1][j + 1]));
                        }
                    }
                }
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PICK_IMAGE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SaveTask st = new SaveTask();
               st.execute(getApplicationContext());
               Toast toast = Toast.makeText(getApplicationContext(), "Запомнил", Toast.LENGTH_SHORT);
               toast.show();
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        for (int p = 0; p < 3; p++) {
                            errcells[p][i][j] = false;
                        }
                    }
                }
                for(int i=0; i<9; i++){
                    for(int j=0; j<9; j++){
                        cells[i][j].setText("");
                    }
                }
            }
        });
    }

    private int[][]countTabl(int[][] a){
        Tabl su_do_ku = new Tabl();
        su_do_ku.set_tabl(a);
        su_do_ku.getAnswer();
        if(su_do_ku.check_error()){
            Toast toast = Toast.makeText(getApplicationContext(), "Что-то пошло не так", Toast.LENGTH_LONG);
            toast.show();
        }
        return su_do_ku.get_tabl(a);
    }

    private boolean errCheck(int x, int y) {
        int[][] values = new int[3][10];
        boolean errAll = false;

        for(int i=0; i<9; i++){ //для строк
            if(!cells[i][y].getText().toString().equals("")){
                values[0][Integer.parseInt(cells[i][y].getText().toString())]++;
            }
        }

        for(int j=0; j<9; j++){ //для столбцов
            if(!cells[x][j].getText().toString().equals("")) values[1][Integer.parseInt(cells[x][j].getText().toString())]++;
        }
        for(int i=x-x%3; i<(x-x%3+3); i++){ //для квадратов
            for(int j=y-y%3; j<(y-y%3+3); j++) {
                if(!cells[i][j].getText().toString().equals("")) values[2][Integer.parseInt(cells[i][j].getText().toString())]++;
            }
        }

        for(int t=1; t<10; t++) {
            if (values[0][t] == 1) {
                for (int i = 0; i < 9; i++) {
                    if (cells[i][y].getText().toString().equals(Integer.toString(t))) errcells[0][i][y] = false;
                }
            }
            if (values[0][t]>1) {
                for (int i=0; i<9; i++) {
                    if (cells[i][y].getText().toString().equals(Integer.toString(t))) errcells[0][i][y] = true;
                }
            }

            if(values[1][t]==1){
                for(int j=0; j<9; j++){
                    if(cells[x][j].getText().toString().equals(Integer.toString(t)))errcells[1][x][j]=false;
                }
            }
            if(values[1][t]>1){
                for(int j=0; j<9; j++){
                    if(cells[x][j].getText().toString().equals(Integer.toString(t)))errcells[1][x][j]=true;
                }
            }

            if(values[2][t]==1){
                for(int i=x-x%3; i<(x-x%3+3); i++) {
                    for (int j=y-y%3; j<(y-y%3+3); j++) {
                        if(cells[i][j].getText().toString().equals(Integer.toString(t)))errcells[2][i][j]=false;
                    }
                }
            }
            if(values[2][t]>1){
                for(int i=x-x%3; i<(x-x%3+3); i++) {
                    for (int j=y-y%3; j<(y-y%3+3); j++) {
                        if(cells[i][j].getText().toString().equals(Integer.toString(t)))errcells[2][i][j]=true;
                    }
                }
            }
        }

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if((errcells[0][i][j])||(errcells[1][i][j])||(errcells[2][i][j])) cells[i][j].setBackgroundResource(R.color.colorAccent);
                else setBackGroundCell(i, j);
                errAll = errAll|(errcells[0][i][j])||(errcells[1][i][j])||(errcells[2][i][j]);
                if(cells[i][j].getText().toString().equals(""))setBackGroundCell(i, j);
            }
        }
        return errAll;
    }

    private void setBackGroundCell(int x, int y){
        if((x<3)||(x>5)){
            if((y<3)||(y>5)) cells[x][y].setBackgroundResource(R.color.colorBlondeFox);
            else cells[x][y].setBackgroundResource(R.color.colorGreyFox);
        }
        else{
            if((y>2)&&(y<6))cells[x][y].setBackgroundResource(R.color.colorBlondeFox);
            else cells[x][y].setBackgroundResource(R.color.colorGreyFox);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = imageReturnedIntent.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    TextRecognizer textRecognizer = new TextRecognizer.Builder(this).build();
                    if (!textRecognizer.isOperational()) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Зависимости дедектора пока не доступны", Toast.LENGTH_SHORT);
                        toast.show();

                        IntentFilter lowstorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
                        boolean hasLowStorage = registerReceiver(null, lowstorageFilter) != null;
                        if (hasLowStorage) {
                            toast = Toast.makeText(getApplicationContext(), "Не хватает памяти для зависимостей", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                    Frame frame = new Frame.Builder().setBitmap(selectedImage).build();
                    SparseArray<TextBlock> text = textRecognizer.detect(frame);
                    for (int i = 0; i < text.size(); ++i) {
                        TextBlock item = text.valueAt(i);
                        if (item != null && item.getValue() != null) {
                            checkString(item.getValue());
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "И где там текст?", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void checkString(String s){
        String[] subStr;
        String[] strs = new String[9];
        for(int i=0; i<9; i++) strs[i]="000000000";
        subStr = s.split("\n");
        for(int i=0; i<subStr.length; i++){
            if(subStr[i].length()>9){
                subStr[i]=subStr[i].substring(0, 9);
            }
            if(subStr[i].length()<9){
                subStr[i]=String.format("%1$-"+9+"s", subStr[i]).replace(' ', '0');
            }
            for(int j=0; j<subStr[i].length(); j++){
                if(!(((int)subStr[i].charAt(j)>47)&&((int)subStr[i].charAt(j)<58))){
                    subStr[i]=subStr[i].replace(subStr[i].charAt(j), '0');
                }
            }
            strs[i]=subStr[i];
        }

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                cells[i][j].setText(String.valueOf(strs[i].charAt(j)));
            }
        }
    }



    static class SaveTask extends AsyncTask<Context, Void, Void>{
        @Override
        protected Void doInBackground(Context... contexts) {
            SQLiteDatabase db;
            SQLHelper help = new SQLHelper(contexts[0], Commands.DB_VERS);
            ContentValues cv = new ContentValues();
            db = help.getWritableDatabase();

            cv.put(Commands.TABLE1_DATE, new Date().toString());
            db.insert(Commands.TABLE1_NAME, null, cv);

            String[] columns = {"MAX("+Commands.TABLE1_ID+") AS id"};
            Cursor c = db.query(Commands.TABLE1_NAME, columns, null, null, null, null, null);
            c.moveToFirst();
            int id = c.getInt(c.getColumnIndex("id"));

            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    String val = cells[i][j].getText().toString();
                    if(!val.equals("") && !val.equals("0")){
                        int value = Integer.parseInt(val);
                        cv = new ContentValues();
                        cv.put(Commands.TABLE2_ID, id);
                        cv.put(Commands.TABLE2_Y, i);
                        cv.put(Commands.TABLE2_X, j);
                        cv.put(Commands.TABLE2_VALUE, value);
                        db.insert(Commands.TABLE2_NAME, null, cv);
                    }
                }
            }
            c.close();
            help.close();
            return null;
        }
    }

    public static void setCellVal(int i, int j, int val){
        cells[i][j].setText(String.valueOf(val));
    }
}


