package dmorenoar.uoc.pra1_uoc.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import dmorenoar.uoc.pra1_uoc.Fragments.ItemFragment;
import dmorenoar.uoc.pra1_uoc.Models.Home;
import dmorenoar.uoc.pra1_uoc.R;

public class HomeSharingListActivity extends FragmentActivity implements ItemFragment.OnListFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*Iniciamos intent hacia AddHomeSharingActivity*/
                Intent intent = new Intent(HomeSharingListActivity.this, AddHomeSharingActivity.class);
                startActivity(intent);
            }
        });

        /*Comprobamos que los extras no estén vacíos, lo que significa que venimos de agregar una casa
        * Realizamos la llamada al fragment para dar de alta la nueva casa.*/
        if(getIntent().getExtras() != null) {
            ItemFragment homeFragment = (ItemFragment) getSupportFragmentManager().findFragmentById(R.id.article_fragment);
            homeFragment.addHome(new Home(getIntent().getStringExtra("cityName"), (Bitmap) getIntent().getParcelableExtra("cityImage")));
        }
    }

    /*Clic en una casa*/
    @Override
    public void onListFragmentInteraction(Home item) {
        Toast.makeText(HomeSharingListActivity.this,"Has pulsado" + item.getCityName(),Toast.LENGTH_SHORT).show();
    }
}

