package iiitd.nehacompany.datastrorage_a3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayDataActivity extends AppCompatActivity {

    Button mGetDataButton;
    Button mBackDisplayData;
    Button mDeleteData;
    EditText mRollEditText;
    TextView mSetNameTextView;
    TextView mSetBookTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        mGetDataButton=(Button)findViewById(R.id.btnGet);
        mRollEditText=(EditText)findViewById(R.id.editText_roll);
        mSetNameTextView=(TextView)findViewById(R.id.tv_setName);
        mSetBookTextView=(TextView)findViewById(R.id.tv_setBook);
        mBackDisplayData=(Button)findViewById(R.id.btnBackDisplayData);
        mDeleteData=(Button)findViewById(R.id.btnDeleteSQLite);
        mGetDataButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String roll = mRollEditText.getText().toString();
                DBHelper mDbHelper = new DBHelper(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getReadableDatabase();

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        FeedReaderContract.FeedEntry._ID,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_1,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_2,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_3
                };
                // Filter results WHERE "roll_number" = roll
                String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_3 + " = ?";
                String[] selectionArgs = {roll};

                // How you want the results sorted in the resulting Cursor
                String sortOrder = FeedReaderContract.FeedEntry.COLUMN_NAME_3 + " DESC";
                Cursor resultSet = db.query(
                        FeedReaderContract.FeedEntry.TABLE_NAME, // The table to query
                        projection, // The columns to return
                        selection, // The columns for the WHERE clause
                        selectionArgs, // The values for the WHERE clause
                        null, // don't group the rows
                        null, // don't filter by row groups
                        sortOrder // sorting order
                );
                resultSet.moveToFirst();

                if(resultSet.getCount() > 0)
                {
                    String id_roll=resultSet.getString(3);
                    String student_name=resultSet.getString(1);
                    String fav_book=resultSet.getString(2);
                    mSetNameTextView.setText(student_name);
                    mSetBookTextView.setText(fav_book);
                    Toast.makeText(DisplayDataActivity.this, "Details retrieved for roll number: " + id_roll,Toast.LENGTH_SHORT).show();
//                    while(resultSet.isAfterLast() == false){
//                        resultSet.moveToNext();
//                    }

                }
                else
                {
                    Toast.makeText(DisplayDataActivity.this, "No record with roll number as: " + roll,Toast.LENGTH_SHORT).show();
                }

                long itemId = resultSet.getLong(
                        resultSet.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID)
                );
            }
        });

        mDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String roll = mRollEditText.getText().toString();
                DBHelper mDbHelper = new DBHelper(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getReadableDatabase();

                // Define 'where' part of query.
                String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_3 + " = ?";
                // Specify arguments in placeholder order.
                String[] selectionArgs = { roll };
                // Issue SQL statement.
                db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
            }
        });

        mBackDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToSQLite = new Intent(DisplayDataActivity.this, SQLStorageActivity.class);
                DisplayDataActivity.this.startActivity(backToSQLite);
            }
        });
    }
}
