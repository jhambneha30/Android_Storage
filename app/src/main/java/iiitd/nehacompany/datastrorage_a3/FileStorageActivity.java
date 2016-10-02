package iiitd.nehacompany.datastrorage_a3;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileStorageActivity extends AppCompatActivity {

    Button mSaveInternalButton;
    Button mGetInternalButton;
    Button mSaveExternalButton;
    Button mGetExternalButton;
    Button mSaveExternalButtonPrivate;
    Button mGetExternalButtonPrivate;
    Button mNextFileStorage;

    EditText mContactEditText;
    String number;
    protected String FileName="MyFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);

        mSaveInternalButton=(Button)findViewById(R.id.btnSaveToInternal);
        mGetInternalButton=(Button)findViewById(R.id.btnGetFromInternal);
        mSaveExternalButton=(Button)findViewById(R.id.btnSaveToExternal);
        mGetExternalButton=(Button)findViewById(R.id.btnGetFromExternal);
        mSaveExternalButtonPrivate=(Button)findViewById(R.id.btnSaveToExternalPrivate);
        mGetExternalButtonPrivate=(Button)findViewById(R.id.btnGetFromExternalPrivate);
        mNextFileStorage=(Button)findViewById(R.id.btnNext2);


        mContactEditText=(EditText)findViewById(R.id.contactEditText);
        //tv=(TextView)findViewById(R.id.textView2);
        mSaveInternalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number=mContactEditText.getText().toString();

                try {
                    FileOutputStream fo = openFileOutput(FileName, Context.MODE_PRIVATE);
                    fo.write(number.getBytes());
                    fo.close();
                    mContactEditText.setText("");
                    Toast.makeText(getBaseContext(),"Number saved to internal storage!",Toast.LENGTH_SHORT).show();
                }

                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        mGetInternalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileInputStream fin = openFileInput(FileName);
                    int ch;
                    String str="";
                    while( (ch = fin.read()) != -1){
                        str = str + Character.toString((char)ch);
                    }
                    mContactEditText.setText(str);
                    Toast.makeText(getBaseContext(),"Data retrieved from file!",Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                }
            }
        });

        mSaveExternalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mNextFileStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fileIntent = new Intent(FileStorageActivity.this, SQLStorageActivity.class);
                FileStorageActivity.this.startActivity(fileIntent);
            }
        });
    }

    //Function to check if External storage is available to be written and read.
    public boolean isExternalStorageWritable() {
        String ext_state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(ext_state))
        {
            return true;
        }
        return  false;
    }

    //Function to check if External storage is readable
    public boolean isExternalStorageReadable() {
        String ext_state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(ext_state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(ext_state))
        {
            return true;
        }
        return  false;
    }
}
