package pmdm.clopez.pmdmtarea2;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import java.util.Locale;

import pmdm.clopez.pmdmtarea2.databinding.ActivityMainBinding;

/**
 * Clase que ejecutará la actividad principal que contendrá los diferentes fragmentos
 */
public class MainActivity extends AppCompatActivity {

    /** Binding para la Actividad principal que vincula con activity_main.xml */
    private ActivityMainBinding binding;
    /** Variable para controlar el menú lateral */
    private ActionBarDrawerToggle toggle;
    /** Controlador de navigación entre fragmentos */
    NavController navController;


    /**Metodo encargado de crear la pantalla
     * @param savedInstanceState Estado que se restablecerá en caso de que se haya guardado con {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //aplicamos el idioma guardado por el usuario en la PreferenceScreen
        applyLanguage();
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Vinculamos la Toolbar al ActionBar
        setSupportActionBar(binding.toolbar);

        //Iniciamos el controlador de navegación
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        //Configuramos el menú lateral
        configureToggleMenu();

        // Configuramos la navegación
        configureNavigation();

        //Activamos el icono del menú lateral
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Metodo encargado de controlar las opciones de navegación del menú lateral
     */
    private void configureNavigation() {
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Manejar la selección de elementos del menú
        binding.navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.home_menu) {//Si pulsa sobre la opción Home
                navController.navigate(R.id.listFragment); // Navegamos al fragmento de inicio

            }else if (menuItem.getItemId() == R.id.settings_menu) { //Si pulsa sobre la opción Ajustes
                navController.navigate(R.id.preferencesFragment); //Navegamos al PreferenceScreen

            }
            binding.drawerLayout.closeDrawers(); // Cerramos el menú
            return true;
        });
    }

    /**
     * Metodo para configurar el Menú lateral
     */
    private void configureToggleMenu() {
        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /** Metodo para controlar el funcionamiento del botón Atrás
     * @return Verdadero si la acción se ha ejecutado correctamente
     */
    @Override
    public boolean onSupportNavigateUp() {
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            NavController navController = NavHostFragment.findNavController(navHostFragment);
            return NavigationUI.navigateUp(navController, binding.drawerLayout) || super.onSupportNavigateUp();
        }
        return super.onSupportNavigateUp();
    }


    /** Metodo encargado de gestionar la selección de personaje , guardar la información para pasarlo al otro fragmento y llamar al fragmento de detalles
     * @param character Pêrsonaje seleccionado
     * @param view View seleccionado
     */
    public void charClicked(Character character, View view) {
        Bundle bundle = new Bundle();
        bundle.putString("name", character.getName());
        bundle.putString("description", character.getDescription());
        bundle.putInt("image", character.getImage());
        bundle.putString("abilities", character.getAbilities());

        Navigation.findNavController(view).navigate(R.id.detailsFragment, bundle);
    }

    /** Metodo para inflar el menu "Acerca de"
     * @param menu El menú a inflar
     * @return Verdadero si el menú se mostrará, falso en caso negativo
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_superior, menu);
        return true;
    }

    /**Metodo para gestionar la selección en el menú del ToolBar
     * @param item El elemento del menú seleccionado
     * @return Verdadero si se ha abierto la opción seleccionada
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Para que funcione lel menú lateral
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        //si se ha pulsado el boton del menu "Acerca de"
        if (item.getItemId() == R.id.about_menu) {
            //Mostramos el AlertDialog con al información de la app
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(getLayoutInflater().inflate(R.layout.about_dialogue, null))
                    .setTitle(R.string.about)
                    .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Acciones a realizar cuando pulsamos el botón.
                            dialog.cancel();
                        }
                    });
            builder.show();
        }
        return true;
    }

    /**
     * Metodo para aplicar el lenguaje guardado en la PreferenceScreen
     */
    public void applyLanguage(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = "es";
        if(sharedPreferences.getBoolean("language", false)) {
            lang = "en";
        }
        Configuration config = new Configuration();
        config.setLocale(new Locale(lang));
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}
