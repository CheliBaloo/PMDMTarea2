package pmdm.clopez.pmdmtarea2;

/**
 * Clase encargada de guardar los datos
 */
public class Character {
    /** Nombre del personaje*/
    private String name;
    /** ID de la imagen del personaje*/
    private int image;
    /** Descripción del personaje*/
    private String description;
    /** Habilidades del personaje*/
    private String abilities;

    /**
     * Constructor con cuatro parámetros
     * @param name Nombre del personaje
     * @param abilities Habilidades del personaje
     * @param description Descripción del personaje
     * @param image Identificador de la imagen del personaje
     */
    public Character(String name, String abilities, String description, int image) {
        this.name = name;
        this.abilities = abilities;
        this.description = description;
        this.image = image;
    }

    /**
     * @return String con el nombre del personaje
     */
    public String getName() {
        return name;
    }

    /**
     * @return int con el ID de la imagen del personaje
     */
    public int getImage() {
        return image;
    }

    /**
     * @return String con la descripción del personaje
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return String con las habilidades del personaje
     */
    public String getAbilities() {
        return abilities;
    }

}
