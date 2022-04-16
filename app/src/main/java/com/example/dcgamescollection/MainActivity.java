package com.example.dcgamescollection;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dcgamescollection.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        binding.appBarMain.fab.setImageResource(R.drawable.ic_baseline_question_mark_24);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDestination destination = navController.getCurrentDestination();
                switch (destination.getId()) {
                    case R.id.nav_search:
                        new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Hint -  Search")
                            .setMessage("Search for your favorite games by title. You can see MORE information for each and SAVE them to your collection")
                            .setPositiveButton("Okay", null)
                            .show();
                        break;
                    case R.id.nav_upcomingtrending:
                        if(UpcomingAndTrending.getTab() == 0) {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Hint -  Upcoming")
                                    .setMessage("See the titles that are on the verge of being released. You can see MORE information for each and MARK the release date on your calendar")
                                    .setPositiveButton("Okay", null)
                                    .show();
                        } else {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Hint -  Trending")
                                    .setMessage("See the titles that are currently on top of the charts. You can see MORE information for each and SAVE them to your collection")
                                    .setPositiveButton("Okay", null)
                                    .show();
                        }
                        break;
                    case R.id.moreInfoFragment:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Hint -  More Info")
                                .setMessage("Scroll through detailed information about the selected game, including screenshots.")
                                .setPositiveButton("Okay", null)
                                .show();
                        break;
                    case R.id.nav_collections:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Hint - Collection")
                                .setMessage("This is a list of all games you have added to your collection of favorite games.\nTap and Hold to remove the game from your list.\nTap and hold this button to navigate to the Stats Display Screen.")
                                .setPositiveButton("Okay", null)
                                .show();
                        break;
                    default:
                        break;
                }
            }
        });
        binding.appBarMain.fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                NavDestination destination = navController.getCurrentDestination();
                if(destination.getId() == R.id.nav_collections) {
                    navController.navigate(R.id.nav_stats_display);
                }
                return false;
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_apphome, R.id.nav_search, R.id.nav_collections,R.id.nav_upcomingtrending, R.id.nav_tips)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}