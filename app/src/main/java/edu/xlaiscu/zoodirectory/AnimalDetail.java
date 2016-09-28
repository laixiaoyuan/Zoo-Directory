package edu.xlaiscu.zoodirectory;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class AnimalDetail extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_detail);

        Bundle bundle = getIntent().getExtras();
        String animalName = bundle.getString("animalName");
        String fileName = bundle.getString("fileName");
        String description = bundle.getString("description");

        TextView animalNameText = (TextView) findViewById(R.id.animalName);
        TextView descriptionText = (TextView) findViewById(R.id.description);
        ImageView fileNameImage = (ImageView) findViewById(R.id.image);

        animalNameText.setText(animalName);
        descriptionText.setText(description);

        InputStream inputStream = null;
        try {
            inputStream = getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable drawable = Drawable.createFromStream(inputStream, null);
        fileNameImage.setImageDrawable(drawable);

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
                Intent intent = new Intent(AnimalDetail.this, Information.class);
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

}
