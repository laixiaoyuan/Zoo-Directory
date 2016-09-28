package edu.xlaiscu.zoodirectory;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Zoo Directory");
        actionBar.setSubtitle("Welcome to D-land!");
        actionBar.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.action_bar_background));
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setDisplayShowHomeEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_info:
                toast("Zoo information action ...");
                Intent intent = new Intent(Information.this, Information.class);
                startActivity(intent);
                break;
            case R.id.action_uninstall:
                toast("Uninstall action ...");
                Uri packageURI = Uri.parse("package:" + MainActivity.class.getPackage().getName());
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
                startActivity(uninstallIntent);
                break;
            default:
                toast("unknown action ...");
        }

        return super.onOptionsItemSelected(item);
    }

    private void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void callTel(View view){
        String telephone = "8888888888";
        Intent callTelephone = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telephone));
        startActivity(callTelephone);


    }
}
