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
                                .setTitle(R.string.hintTitle_Home)
                                .setTitle(R.string.hintText_Home)
                                .setPositiveButton(R.string.hintButton, null)
                                .show();
                        break;
                    case R.id.nav_search:
                        new AlertDialog.Builder(MainActivity.this)
                            .setTitle(R.string.hintTitle_Search)
                            .setMessage(R.string.hintText_Search)
                            .setPositiveButton(R.string.hintButton, null)
                            .show();
                        break;
                    case R.id.nav_upcomingtrending:
                        if(UpcomingAndTrending.getTab() == 0) {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle(R.string.hintTitle_Upcoming)
                                    .setMessage(R.string.hintText_Upcoming)
                                    .setPositiveButton(R.string.hintButton, null)
                                    .show();
                        } else {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle(R.string.hintTitle_Trending)
                                    .setMessage(R.string.hintText_Trending)
                                    .setPositiveButton(R.string.hintButton, null)
                                    .show();
                        }
                        break;
                    case R.id.moreInfoFragment:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.hintTitle_MoreInfo)
                                .setMessage(R.string.hintText_MoreInfo)
                                .setPositiveButton(R.string.hintButton, null)
                                .show();
                        break;
                    case R.id.nav_collections:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.hintTitle_Collection)
                                .setMessage(R.string.hintText_Collection)
                                .setPositiveButton(R.string.hintButton, null)
                                .show();
                        break;
                    case R.id.nav_stats_display:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.hintTitle_Stats)
                                .setMessage(R.string.hintText_Stats)
                                .setPositiveButton(R.string.hintButton, null)
                                .show();
                        break;
                    case R.id.ask_user_record:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.hintTitle_StatInput)
                                .setMessage(R.string.hintText_StatInput)
                                .setPositiveButton(R.string.hintButton, null)
                                .show();
                        break;
                    case R.id.nav_settings:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.hintTitle_Settings)
                                .setMessage(R.string.hintText_Settings)
                                .setPositiveButton(R.string.hintButton, null)
                                .show();
                        break;
                    case R.id.nav_profile:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.hintTitle_Profile)
                                .setMessage(R.string.hintText_Profile)
                                .setPositiveButton(R.string.hintButton, null);
                        break;
                    case R.id.nav_credits:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.hintTitle_Credits)
                                .setMessage(R.string.hintText_Credits)
                                .setPositiveButton(R.string.hintButton, null)
                                .show();
                        break;
                    case R.id.nav_filter:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle(R.string.hintTitle_Filter)
                                .setMessage(R.string.hintText_Filter)
                                .setPositiveButton(R.string.hintButton, null)
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