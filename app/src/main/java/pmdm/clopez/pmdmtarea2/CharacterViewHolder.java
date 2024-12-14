package pmdm.clopez.pmdmtarea2;

import androidx.recyclerview.widget.RecyclerView;

import pmdm.clopez.pmdmtarea2.databinding.ItemCardviewBinding;

/**
 * Clase encargada de vincular la View con los datos
 */
public class CharacterViewHolder extends RecyclerView.ViewHolder {
    private final ItemCardviewBinding binding;

    /**Constructor de 1 parámetro
     * @param binding ViewBinding para vincular las View con los datos
     */
    public CharacterViewHolder(ItemCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /** Metodo para vincular los datos del Character con la CardView
     * @param character El personaje actual
     */
    public void bind(Character character){
        binding.nameCard.setText(character.getName());
        binding.imgCard.setImageResource(character.getImage());
        binding.executePendingBindings(); //para aplicar los cambios de inmediato
    }
}
