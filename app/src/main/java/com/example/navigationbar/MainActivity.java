package com.example.navigationbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    RecyclerView recyclerView;
    RecyclerView recyclerViewVertical;
    CardAdp adp;
    List<Card> cardData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        toolbar =  findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(MainActivity.this,"clicked home",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.send:
                        Toast.makeText(MainActivity.this,"clicked send",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.draft:
                        Toast.makeText(MainActivity.this,"clicked draft",Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });

        initializeRecycler();
        cardListData();
    }

    void initializeRecycler()
    {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerViewVertical = findViewById(R.id.recycler_view_vertical);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewVertical.setLayoutManager(new LinearLayoutManager(this));
    }

    void cardListData()
    {

        cardData = new ArrayList<Card>();

        Card burger = new Card(R.drawable.burger);
        Card pizza = new Card(R.drawable.pizza);
        Card sanwich = new Card(R.drawable.sandwich);

        cardData.add(burger);
        cardData.add(pizza);
        cardData.add(sanwich);

        adp = new CardAdp(this,cardData);
        recyclerView.setAdapter(adp);
        recyclerViewVertical.setAdapter(adp);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}