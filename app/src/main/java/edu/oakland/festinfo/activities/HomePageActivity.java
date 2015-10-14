package edu.oakland.festinfo.activities;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import edu.oakland.festinfo.R;

@EActivity(R.layout.activity_main)
public class HomePageActivity extends BaseActivity {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.navigation_view)
    NavigationView navigationView;
    @ViewById(R.id.drawer)
    DrawerLayout drawerLayout;

    @AfterViews
    void init() {

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {

                    //Replacing the main content with chosen Fragment;
                    case R.id.user_profile:
                        switchFragment(new UserProfileActivity(), "User Profile");
                        return true;
                    case R.id.search:
                        switchFragment(new SearchPageActivity(), "Search");
                        return true;
                    case R.id.directions:
                        switchFragment(new DirectionsPageActivity(), "Directions");
                        return true;
                    case R.id.weather:
                        switchFragment(new WeatherPageActivity(), "Weather");
                        return true;
                    case R.id.tickets:
                        switchFragment(new TicketsPageActivity(), "Tickets");
                        return true;
                    case R.id.notifications:
                        switchFragment(new NotificationsPageActivity(), "Notifications");
                        return true;
                    case R.id.logout:
                        switchFragment(new LogoutPage(), "Logout");
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

    }

    public void switchFragment(Fragment f, String label) {
        Toast.makeText(getApplicationContext(), label + " Selected", Toast.LENGTH_SHORT).show();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, f);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Click(R.id.settings_button)
    public void showSettings(){
        SettingsPageActivity_
                .intent(this)
                .start();
    }

    @Click(R.id.map_button)
    public void showMap() {
        MapPageActivity_
                .intent(this)
                .start();

    }




}
