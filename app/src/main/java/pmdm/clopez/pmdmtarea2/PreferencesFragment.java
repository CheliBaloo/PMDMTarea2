package pmdm.clopez.pmdmtarea2;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import java.util.Locale;


/**
 * Clase del fragmento que se encarga de guardar ajustes en guardado local
 */
public class PreferencesFragment extends PreferenceFragmentCompat {

    /**Metodo que crea el fragmento de preferencias
     * @param savedInstanceState Estado anterior guardado para recrearlo
     * @param rootKey            Si no es nulo, key que vincula con la pantalla de preferencias
     */
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        //Guardamos el estado del switch en una variable para acceder a él
        SwitchPreferenceCompat switchPreference = findPreference("language");

        //Si hay cambios en el estado, ejecutará el metodo onPreferenceChange
        if (switchPreference != null) {
            switchPreference.setOnPreferenceChangeListener((this::onPreferenceChange));
        }
    }

    /**Metodo encargado de gestionar el cambio de estado del SwitchPreferenceCompat (gestión del idioma)
     * @param preference preference que cambia
     * @param newValue nuevo valor
     * @return Verdadero si ha cambiado
     */
    private boolean onPreferenceChange(Preference preference, Object newValue) {
        //String para guardar el idioma
        String lang;
        //Si es verdadero, el switch esta encendido, por lo que está activado el idioma Inglés
        if((Boolean) newValue) {
            lang = "en";
        }else{ //Si es falso, el switch esta apagado, por lo que está desactivado el idioma Inglés y está en español
            lang="es";
        }
        //Establecemos la configuración y reiniciamos la actividad
        Configuration config = new Configuration();
        config.setLocale(new Locale(lang));
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        Activity activity = getActivity();
        if (activity != null) {
            activity.recreate();
        }
        return true;
    }
    /**
     * Metodo que se ejecuta al iniciar la vista
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título del ToolBar al cambia de fragmento
        if (getActivity() != null) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null){
                actionBar.setTitle(R.string.settings);
            }
        }

    }
}