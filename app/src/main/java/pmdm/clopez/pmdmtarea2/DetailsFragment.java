package pmdm.clopez.pmdmtarea2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import pmdm.clopez.pmdmtarea2.databinding.FragmentDetailsBinding;


/**
 * Clase del fragmento que muestra los detalles del personaje elegido
 */
public class DetailsFragment extends Fragment {

    /** Binding del fragmento de detalles */
    FragmentDetailsBinding binding;


    /** Metodo encargado de inflar el fragmento
     * @param inflater           LayoutInflater encargdo de inflar los componentes del fragmento
     * @param container          Si no es nulo, es la View padre que contendrá al fragmento
     * @param savedInstanceState Si no es nulo, el fragmento será reconstruido a un estado anterior guardodo
     * @return vista creada
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /** Metodo encargado de cargar los componentes que componen el fragmento
     * @param view               Vista retornada por {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState Si no es nulo, el fragmento será reconstruido a un estado anterior guardodo
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtenemos los datos del argumento que inicia este fragmento y se guardan en variables
        if (getArguments() != null) {
            String name = getArguments().getString("name");
            String description = getArguments().getString("description");
            int image = getArguments().getInt("image");
            String abilities = getArguments().getString("abilities");

            //Mensaje que informa al usuario el personaje seleccionado
            Toast.makeText(binding.getRoot().getContext(), getString(R.string.toast_msg) + " " +name, Toast.LENGTH_SHORT).show();

            //Cargamos la información del personaje en cada una de las vistas
            binding.nameCharacter.setText(name);
            binding.descCharacter.setText(description);
            binding.imgCharacter.setImageResource(image);
            binding.abCharacter.setText(abilities);
        }
    }

    /**
     * Metodo que se ejecuta al iniciar la vista
     */
    @Override
    public void onStart() {
        super.onStart();
        // Cambia el título de la ToolBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.character_details);
        }
    }
}