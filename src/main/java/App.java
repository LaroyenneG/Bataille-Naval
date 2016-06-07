import static javax.swing.SwingUtilities.invokeLater;

import engine.Equipe;
import engine.GrilleJeux;
import engine.Jukebox.Jukebox;
import listener.GroupListener;

/**
* 
*/

/**
 * @author nicolas
 *
 */
public class App
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		invokeLater(new Runnable()
		{
			public void run()
			{
				GrilleJeux gj1 = new GrilleJeux();

				GrilleJeux gj2 = new GrilleJeux();

				Equipe equipes1 = new Equipe(gj1, "");
				Equipe equipes2 = new Equipe(gj2, "");

				Equipe[] equipes = new Equipe[] { equipes1, equipes2 };
				gj1.setEquipes(equipes);
				gj2.setEquipes(equipes);

				new GroupListener(gj1, gj2, equipes1, equipes2);

				//Gestion audio uniquement pour le demarage
				Jukebox jukebox = new Jukebox();
				//activer || desactiver
				jukebox.activeSon(true);
				jukebox.playSon("Ouverture");
			}
		});
	}

}
