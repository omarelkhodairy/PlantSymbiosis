package com.example.symbio.ui.main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "app.db";
    public static final String plantsTable = "plantsTable";
    public static final String plantNameCol = "plantName";
    public static final String favourableLightCol = "favourableLight";
    public static final String soilTypeCol = "soilType";
    public static final String wateringConditionCol = "WateringCondition";
    public static final String maximumProductionCol = "MaximumProduction";
    public static final String descriptionPlantCol = "Description";
  @Override
    public void onCreate(SQLiteDatabase db){
   String createTable = "CREATE TABLE plantsTable" + "(PLANTNAME TEXT, FAVOURABLELIGHT TEXT,SOILTYPE TEXT, WATERCONDITION TEXT, MAXIMUMPRODUCTION TEXT, DESCRIPTION TEXT)";
   db.execSQL(createTable);

  }

    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    String dropTable = "DROP TABLE plantsTable IF EXISTS";
    db.execSQL(dropTable);
    onCreate(db);
  }

}
