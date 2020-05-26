package sv.edu.ues.fia.ac1300202;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor,new CheckBoxFragment()).commit();
        //fragmentManager.beginTransaction().replace(R.id.contenedor,new RadioButtonFragment()).commit();
        //fragmentManager.beginTransaction().replace(R.id.contenedor,new GalleryFragment()).commit();
        //fragmentManager.beginTransaction().replace(R.id.contenedor,new SpinnerFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //@Override
    public boolean onNavigationItemSelected(MenuItem item){
        int id= item.getItemId();
        FragmentManager fragmentManager=getSupportFragmentManager();
        if (id==R.id.nav_button){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new ButtonFragment()).commit();

        }else if (id==R.id.nav_textview){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new TextViewFragment()).commit();
        }else if (id==R.id.nav_edittext){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new EditTextFragment()).commit();
        }else if (id==R.id.nav_checkbox){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new CheckBoxFragment()).commit();
        }else if (id==R.id.nav_radiobutton){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new RadioButtonFragment()).commit();
        }else if (id==R.id.nav_gallery){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new GalleryFragment()).commit();
        }else if (id==R.id.nav_spinner){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new SpinnerFragment()).commit();
        }else if (id==R.id.nav_tabwidget){
            fragmentManager.beginTransaction().replace(R.id.contenedor,new TabWidgetFragment()).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
