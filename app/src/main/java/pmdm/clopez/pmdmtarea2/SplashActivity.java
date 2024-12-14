package pmdm.clopez.pmdmtarea2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import pmdm.clopez.pmdmtarea2.databinding.ActivitySplashBinding;

/**
 * Clase encarga de mostrar la pantalla Splash al iniciar la app en versiones de API inferiores a 31
 */
public class SplashActivity extends AppCompatActivity {

    /** Binding de la pantalla de splash */
    ActivitySplashBinding binding;
    /** Tiempo que durará la pantalla*/
    private static final int SPLASH_TIME = 3000; //Tiempo que se mantendra la pantalla Splash

    /**Metodo encargado de crear la pantalla
     * @param savedInstanceState Estado que se restablecerá en caso de que se haya guardado con {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());

        //Verificamos si la versión de la API <31
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.S){
            setContentView(binding.getRoot()); //mostramos la pantalla

            //Controlamos el inicio de la MainActivity
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run(){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME);
        }else{ //si API>= 31, se muestra directamente la MainActivity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }



    }
}