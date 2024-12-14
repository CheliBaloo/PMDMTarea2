package pmdm.clopez.pmdmtarea2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pmdm.clopez.pmdmtarea2.databinding.ItemCardviewBinding;

/**
 * Clase Adaptador del Recycler View
 */
public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    /**Lista de Character (datos con los que trabajará la app) */
    private final ArrayList<Character> characters;
    /**Contexto del ViewHolder*/
    private final Context context;

    /** COnstructor de 2 parámetros
     * @param context Contexto del Recycler View
     * @param characters Lista de objetos de la clase Character
     */
    public CharacterRecyclerViewAdapter(Context context, ArrayList<Character> characters) {
        this.context = context;
        this.characters = characters;
    }

    /** Metodo para crear el View Holder
     * @param parent   VIewGroup donde será añadido el nuevo View
     * @param viewType El tipo del nuevo View
     * @return ViewHolder
     */
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardviewBinding binding = ItemCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CharacterViewHolder(binding);
    }

    /**Metodo para vincular el ViewHolder y el Character Data
     * @param holder   El ViewHolder que contendrá los datos del Character
     * @param position Posición del Character en la lista de datos
     */
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character currentChar = this.characters.get(position); //obtenemos el personaje de la lista en la posición dada
        holder.bind(currentChar); //lo vinculamos con el viewholder
        holder.itemView.setOnClickListener(view -> ((MainActivity) context).charClicked(currentChar, view)); //acción cuando pulsemos sobre la cardview del personaje (en MainActivity)
    }

    /** Metodo para obtener el tamaño de la lista de Character
     * @return Número de elementos en la lista de datos
     */
    @Override
    public int getItemCount() {
        return this.characters.size();
    }
}
