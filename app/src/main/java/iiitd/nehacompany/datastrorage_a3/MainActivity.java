package iiitd.nehacompany.datastrorage_a3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mNameEditText;
    EditText mEmailEditText;
    EditText mBioEditText;

    Button mButtonAddDetails;
    Button mButtonGetDetails;
    Button mButtonDelDetails;
    Button mNextMain;
    SharedPreferences data_pref;
    public static final String Name = "Name_key";
    public static final String Email = "Email_key";
    public static final String Bio = "Bio_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEditText=(EditText)findViewById(R.id.nameEditText);
        mEmailEditText=(EditText)findViewById(R.id.emailEditText);
        mBioEditText=(EditText)findViewById(R.id.bioEditText);


        mButtonAddDetails=(Button)findViewById(R.id.btnAddDetails);
        mButtonGetDetails=(Button)findViewById(R.id.btnGetDetails);
        mButtonDelDetails=(Button)findViewById(R.id.btnDelDetails);
        mNextMain=(Button)findViewById(R.id.btnNext);

        data_pref = getApplicationContext().getSharedPreferences("Preferences",MODE_PRIVATE);

        mNameEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNameEditText.setText("");
            }
        });

        mEmailEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEmailEditText.setText("");
            }
        });

        mBioEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBioEditText.setText("");
            }
        });


        mButtonAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = mNameEditText.getText().toString();
                String email  = mEmailEditText.getText().toString();
                String bio  = mBioEditText.getText().toString();

                SharedPreferences.Editor editor = data_pref.edit();

                editor.putString(Name, name);
                editor.putString(Email, email);
                editor.putString(Bio, bio);
                editor.commit();
                Toast.makeText(MainActivity.this, "Preferences added!" + name+email+bio,Toast.LENGTH_SHORT).show();
            }
        });

        mButtonGetDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = data_pref.edit();
                String nameGot=null;
                String emailGot=null;
                String bioGot=null;
                if (data_pref.contains(Name)) {
                    nameGot = data_pref.getString(Name, "");
                    mNameEditText.setText(nameGot);
                }
                if (data_pref.contains(Email)) {
                    emailGot= data_pref.getString(Email, "");
                    mEmailEditText.setText(emailGot);
                }
                if (data_pref.contains(Bio)) {
                    bioGot = data_pref.getString(Bio, "");
                    mBioEditText.setText(bioGot);
                }
                editor.commit();
                Toast.makeText(MainActivity.this, "Preferences retrieved!" + nameGot+bioGot+emailGot,Toast.LENGTH_SHORT).show();
            }
        });

        mButtonDelDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = data_pref.edit();

                editor.remove(Name); // will delete key key_name3
                editor.remove(Email);
                editor.remove(Bio);
                editor.commit();
                Toast.makeText(MainActivity.this, "Preferences removed!",Toast.LENGTH_SHORT).show();
            }
        });

        mNextMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fileIntent = new Intent(MainActivity.this, FileStorageActivity.class);
                MainActivity.this.startActivity(fileIntent);
            }
        });

    }
}
