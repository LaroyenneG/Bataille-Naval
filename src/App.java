import engine.Equipe;
import engine.GrilleJeux;
import engine.Jukebox.Jukebox;
import javax.swing.SwingUtilities;
import listener.GroupListener;

public class App {
	public App() {
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GrilleJeux gj1 = new GrilleJeux();
				GrilleJeux gj2 = new GrilleJeux();
				Equipe equipes1 = new Equipe(gj1, "");
				Equipe equipes2 = new Equipe(gj2, "");
				Equipe[] equipes = new Equipe[]{equipes1, equipes2};
				gj1.setEquipes(equipes);
				gj2.setEquipes(equipes);
				new GroupListener(gj1, gj2, equipes1, equipes2);
				Jukebox jukebox = new Jukebox();
				jukebox.activeSon(true);
				jukebox.playSon("Ouverture");
			}
		});
	}
}

