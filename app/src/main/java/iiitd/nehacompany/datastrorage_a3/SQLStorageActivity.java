package iiitd.nehacompany.datastrorage_a3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SQLStorageActivity extends AppCompatActivity {
    Button mSaveSQLiteButton;
    Button mUpdateSQLiteButton;
    Button mGetSQLiteButton;

    EditText mFavouriteMovieET;
    EditText mNameET;
    EditText mRollNumET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlstorage);

        mFavouriteMovieET=(EditText)findViewById(R.id.editText_favouriteBook);
        mNameET=(EditText)findViewById(R.id.editText_name);
        mRollNumET=(EditText)findViewById(R.id.editText_favouriteNumber);

        mSaveSQLiteButton = (Button)findViewById(R.id.btnSaveSQLite);
        mUpdateSQLiteButton = (Button)findViewById(R.id.btnUpdateSQLite);
        mGetSQLiteButton = (Button)findViewById(R.id.btnGetSQLite);

        mSaveSQLiteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String stu_name =mNameET.getText().toString();
                String fav_movie =mFavouriteMovieET.getText().toString();
                String roll =mRollNumET.getText().toString();

                DBHelper mDbHelper = new DBHelper(getApplicationContext());
                // Gets the data repository in write mode
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_1, stu_name);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_2, fav_movie);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_3, roll);
                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
            }
        });

        mUpdateSQLiteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String stu_name =mNameET.getText().toString();
                String fav_movie =mFavouriteMovieET.getText().toString();
                String roll =mRollNumET.getText().toString();

                DBHelper mDbHelper = new DBHelper(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getReadableDatabase();
                // New value for one column
                ContentValues values = new ContentValues();
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_1, stu_name);
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_2, fav_movie);
                // Which row to update, based on the title
                String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_3 + " LIKE ?";
                String[] selectionArgs = { roll };
                int count = db.update(
                        FeedReaderContract.FeedEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);

            }
        });

        mGetSQLiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent displayData = new Intent(SQLStorageActivity.this, DisplayDataActivity.class);
                SQLStorageActivity.this.startActivity(displayData);
            }
        });
    }
}
