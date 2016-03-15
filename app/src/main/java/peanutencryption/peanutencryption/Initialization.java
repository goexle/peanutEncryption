/*
 * @author Gabriel Oexle
 * 2015.
 */
package peanutencryption.peanutencryption;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Initialization extends AppCompatActivity {

    public String LOG_str = "peanutencryption";
    private String MY_PREF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialization);

        MY_PREF  = getString(R.string.sharedPref);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_initialization, menu);
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

    public void clickButtonConfirm(View v)
    {
        TextInputLayout inputLayoutPasswordFirst = (TextInputLayout) findViewById(R.id.textInputLayout_Password_First);
        TextInputLayout inputLayoutPasswordSecond = (TextInputLayout) findViewById(R.id.textInputLayout_Password_Second);

        String firstPassword = inputLayoutPasswordFirst.getEditText().getText().toString();
        String secondPassword = inputLayoutPasswordSecond.getEditText().getText().toString();

        if(firstPassword.contentEquals(secondPassword))
        {
            Button btn = (Button) findViewById(R.id.ConfirmBtn);
            btn.setEnabled(false);
            SharedPreferences settings = getSharedPreferences(MY_PREF, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("isInitialized", true);
            editor.commit();

            Intent broadcastIntent = new Intent();
            broadcastIntent.putExtra("password", firstPassword);
            setResult(RESULT_OK, broadcastIntent);
            finish();
        }
        else
        {
            inputLayoutPasswordFirst.getEditText().setText("");
            inputLayoutPasswordSecond.getEditText().setText("");
            inputLayoutPasswordFirst.setError("Password does not match. Type in again");
            inputLayoutPasswordSecond.setError(" ");
        }



    }



}
