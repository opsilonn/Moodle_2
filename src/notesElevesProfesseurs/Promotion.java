package notesElevesProfesseurs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * Une promotion est un ensemble d'{@link Eleve}s
 */
public class Promotion extends ArrayList<Eleve>
{
	private String nom;

	private static final String NOM_PAR_DEFAUT = "Promo Scions";

	/**
	 * Constructeur par défaut
	 */
	public Promotion() {}
}
