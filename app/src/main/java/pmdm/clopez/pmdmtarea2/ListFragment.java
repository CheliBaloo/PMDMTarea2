package pmdm.clopez.pmdmtarea2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import pmdm.clopez.pmdmtarea2.databinding.FragmentListBinding;

/**
 * Clase encargada de mostrar el fragmento de la lista de personajes
 */
public class ListFragment extends Fragment {

    /** Binding del fragmento que muestra la lista de personajes */
    FragmentListBinding binding;

    /**Metodo que infla el fragmento
     * @param inflater           LayoutInflater que inflará los elementos del fragmento,
     * @param container          Si no es nulo, es la View padre que contendrá al fragmento
     * @param savedInstanceState Si no es nulo, el fragmento será reconstruido a un estado anterior guardodo
     * @return vista creada
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /** Metodo encargado de cargar los componentes que componen el fragmento
     * @param view               Vista retornada por {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState Si no es nulo, el fragmento será reconstruido a un estado anterior guardodo
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Establecemos el LayoutManager del recycler view
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        //Creamos la lista de personajes que apareceran en la lista
        ArrayList<Character> characters = new ArrayList<>();
        characters.add(new Character(getString(R.string.boo_name),getString(R.string.boo_abilities), getString(R.string.boo_description), R.drawable.boo));
        characters.add(new Character(getString(R.string.bowser_name),getString(R.string.bowser_abilities), getString(R.string.bowser_description), R.drawable.bowser));
        characters.add(new Character(getString(R.string.bowserjr_name),getString(R.string.bowserjr_abilities), getString(R.string.bowserjr_description), R.drawable.bowser_jr));
        characters.add(new Character(getString(R.string.rosalina_name),getString(R.string.rosalina_abilities), getString(R.string.rosalina_description), R.drawable.rosalina));
        characters.add(new Character(getString(R.string.goomba_name),getString(R.string.goomba_abilities), getString(R.string.goomba_description), R.drawable.goomba));
        characters.add(new Character(getString(R.string.koopa_name),getString(R.string.koopa_abilities), getString(R.string.koopa_description), R.drawable.koopa));
        characters.add(new Character(getString(R.string.luigi_name),getString(R.string.luigi_abilities), getString(R.string.luigi_description), R.drawable.luigi));
        characters.add(new Character(getString(R.string.mario_name),getString(R.string.mario_abilities), getString(R.string.mario_description), R.drawable.mario));
        characters.add(new Character(getString(R.string.daisy_name),getString(R.string.daisy_abilities), getString(R.string.daisy_description), R.drawable.daisy));
        characters.add(new Character(getString(R.string.peach_name),getString(R.string.peach_abilities), getString(R.string.peach_description), R.drawable.peach));
        characters.add(new Character(getString(R.string.toad_name),getString(R.string.toad_abilities), getString(R.string.toad_description), R.drawable.toad));
        characters.add(new Character(getString(R.string.waluigi_name),getString(R.string.waluigi_abilities), getString(R.string.waluigi_description), R.drawable.waluigi));
        characters.add(new Character(getString(R.string.wario_name),getString(R.string.wario_abilities), getString(R.string.wario_description), R.drawable.wario));
        characters.add(new Character(getString(R.string.yoshi_name),getString(R.string.yoshi_abilities), getString(R.string.yoshi_description), R.drawable.yoshi));

        //Le vinculamos el adaptador
        binding.recyclerView.setAdapter(new CharacterRecyclerViewAdapter(binding.getRoot().getContext(),characters));

        //SnackBar para dar la bienvenida cada vez que se carga el fragmento de la lista
        Snackbar.make(binding.getRoot(), R.string.welcome, Snackbar.LENGTH_SHORT).show();
    }
    /**
     * Metodo que se ejecuta al iniciar la vista
     */
    public void onStart() {
        super.onStart();
        // Cambia el título del ActionBar
        if (getActivity() != null) {
            //Le damos el título a la actividad que nos indica qué fragmento está activo
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null){
                actionBar.setTitle(R.string.character_list);
            }
        }
    }
}