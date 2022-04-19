package com.example.dcgamescollection;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if(destination.getId() == R.id.nav_collections) {
                    binding.appBarMain.fab.setImageResource(R.drawable.ic_baseline_auto_graph_24);
                } else {
                    binding.appBarMain.fab.setImageResource(R.drawable.ic_baseline_question_mark_24);
                }
            }
        });

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDestination destination = navController.getCurrentDestination();
                switch (destination.getId()) {
                    case R.id.nav_apphome:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Hint - Home")
                                .setTitle("Use this page to log your Name and DOB to the app")
                                .setPositiveButton("Okay", null)
                                .show();
                        break;
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
                                .setMessage("This is a list of all games you have added to your collection of favorite games.\nTap and Hold a game to remove the game from your list.\nTap the \"M EXP\" button to enter your stats for your latest playthrough.\nTap and hold this button to navigate to the Stats Display Screen.")
                                .setPositiveButton("Okay", null)
                                .show();
                        break;
                    case R.id.nav_stats_display:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Hint - Your Stats")
                                .setMessage("See the stats of the games you play.\nTap the \"Edit\" button to alter the displayed stats.")
                                .setPositiveButton("Okay", null)
                                .show();
                        break;
                    case R.id.ask_user_record:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Hint - Input Stats")
                                .setMessage("Enter your stats for the selected game here.\nToggle between a High-Score or Scoreboard-type stats with the button on top.")
                                .setPositiveButton("Okay", null)
                                .show();
                        break;
                    case R.id.nav_settings:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Hint - Settings")
                                .setMessage("View your \"Personal Details\" from this page.\n \"Share\" the app on Social Media via this page.\nYou can also see who is credited with the making of this app.")
                                .setPositiveButton("Okay", null)
                                .show();
                        break;
                    case R.id.nav_profile:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Hint - Profile Settings")
                                .setMessage("This is the information you entered when you first opened the app.");
                    case R.id.nav_credits:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Hint - Credits")
                                .setMessage("These are the people and sites responsible for the making of this app. Thank you for downloading DC Games Collection!")
                                .show();
                        break;
                    case R.id.nav_filter:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Hint - Filter menu")
                                .setMessage("Filter your searches based on your favorite platform or genre.\nOr, limit your searches to family-friendly content with \"Parent Mode\".")
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
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_apphome, R.id.nav_search,
                R.id.nav_collections,R.id.nav_upcomingtrending)
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.action_settings:
                Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_settings);
                break;
            case R.id.action_filter:
                Navigation.findNavController(this, R.id.nav_host_fragment_content_main).navigate(R.id.nav_filter);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}