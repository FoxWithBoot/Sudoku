package com.example.kur;

import android.provider.BaseColumns;

class Commands implements BaseColumns {
    static final String DB_NAME = "SUDOKU";
    static final int DB_VERS = 1;

    static final String TABLE1_NAME = "SudDate";
    static final String TABLE1_ID = "id";
    static final String TABLE1_DATE = "date";

    static final String TABLE2_NAME = "Cells";
    static final String TABLE2_ID = "id";
    static final String TABLE2_Y = "y";
    static final String TABLE2_X = "x";
    static final String TABLE2_VALUE = "value";

    static final String CREATE_TABLE1 =
            "CREATE TABLE "
            +TABLE1_NAME+"("
            +TABLE1_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +TABLE1_DATE+" VARCHAR"+");";

    static final String CREATE_TABLE2 =
            "CREATE TABLE "
            +TABLE2_NAME+"("
            +TABLE2_ID+" INTEGER,"
            +TABLE2_Y+" INTEGER,"
            +TABLE2_X+" INTEGER,"
            +TABLE2_VALUE+" INTEGER"+");";

    static final String SELECT_CELLS =
            "SELECT "
            +TABLE2_Y+", "+TABLE2_X+", "+TABLE2_VALUE  //+", "+TABLE2_NAME+"."+TABLE2_ID
            +" FROM "+TABLE1_NAME
            +" JOIN "+TABLE2_NAME
            +" ON "
            +TABLE1_NAME+"."+TABLE1_ID+" = "+TABLE2_NAME+"."+TABLE2_ID
            +" WHERE "+TABLE2_NAME+"."+TABLE2_ID+" =?";

    static final String DELETE_TAB1 =
            "DELETE FROM "+TABLE1_NAME
            +" WHERE "+TABLE1_ID+" = ";

    static final String DELETE_TAB2 =
            "DELETE FROM "+TABLE2_NAME
            +" WHERE "+TABLE2_ID+" = ";

}
