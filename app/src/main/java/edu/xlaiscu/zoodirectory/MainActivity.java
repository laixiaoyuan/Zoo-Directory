package edu.xlaiscu.zoodirectory;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<Animal> animals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // build animal list
        animals = new ArrayList<>();

        animals.add(new Animal("Eeyore", "eeyore.png", "He is a pessimistic, gloomy, depressed, anhedonic, old grey stuffed donkey who is a friend of Winnie-the-Pooh."));
        animals.add(new Animal("Piglet", "piglet.png", "Piglet is Winnie‑the‑Pooh's closest friend amongst all the animals featured in the stories. Despite the fact that he is a \"Very Small Animal\" of a generally timid disposition, he tries to be brave and on occasion conquers his fears."));
        animals.add(new Animal("Winnie", "winnie.png", "Winnie-the-Pooh, also called Pooh Bear, is a fictional anthropomorphic teddy bear created by English author A. A. Milne. "));
        animals.add(new Animal("Tigger", "tigger.png", "Tigger is based on one of Christopher Robin Milne's stuffed animals. Nowadays he is also widely recognized as reinterpreted by the Disney studios, with distinctive orange and black stripes, large eyes, a long chin, a springy tail, and (the one detail originating from A. A. Milne) his love of bouncing. As he says himself, \"Bouncing is what Tiggers do best.\""));
        animals.add(new Animal("Judy Hopps", "judy.png", "Judy Hopps is a fictional character who appears in Walt Disney Animation Studios/Pixar's 17th animated film and first animated comedy musical film Zootopia."));
        animals.add(new Animal("Nick Wilde", "nick.png", "Nick is generally charismatic, sociable, wise-cracking and highly intelligent. A shifty, sly, mischievous and cunning fox, traits supposedly common to all foxes, Nick is easygoing and slick, easily conning and tricking others through his charm and friendly demeanor. He always has a sly smile on his face and seems unfazed throughout most of the events."));

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new AnimalArrayAdaptor(this, R.layout.custom_row, animals));
        lv.setOnItemClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Zoo Directory");
        actionBar.setSubtitle("Welcome to D-land!");
        actionBar.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.action_bar_background));
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Animal animal = animals.get(position);
        Toast.makeText(getApplicationContext(), "Animal clicked : " + animal.getName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, AnimalDetail.class);
        Bundle bundle = new Bundle();
        bundle.putString("animalName", animal.getName());
        bundle.putString("fileName", animal.getFilename());
        bundle.putString("description", animal.getDescription());

        intent.putExtras(bundle);
        final Intent intentFinal = intent;

        if (position == animals.size() - 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Scary Animal!")
                    .setMessage("Are you sure you want to proceed?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Positive button clicked!", Toast.LENGTH_SHORT).show();
                            startActivity(intentFinal);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(), "Negative button clicked!", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
            builder.create().show();
        }
        else {
            startActivity(intent);
        }
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
                Intent intent = new Intent(MainActivity.this, Information.class);
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
