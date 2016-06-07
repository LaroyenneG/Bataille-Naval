package engine;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by valenza on 05/05/16.
 */
public class Appli
{
	static final Scanner	input	= new Scanner(System.in);
	static final String[]	name	= { "Contre Torpillieur", "Croiseur", "Porte Avion", "Sous Marin", "Torpillieur" };

	public static void main(String[] args)
	{

		// On Crée les Objets que l'on à besoins
		GrilleJeux gj1 = new GrilleJeux();

		GrilleJeux gj2 = new GrilleJeux();

		Equipe equipes1 = new Equipe(gj1, "");
		Equipe equipes2 = new Equipe(gj2, "");

		Equipe[] equipes = new Equipe[] { equipes1, equipes2 };
		// On créer le liens entre equipes et grille
		gj1.setEquipes(equipes);
		gj2.setEquipes(equipes);

		// On défini les nom
		System.out.println("Nom equipe 1");
		String nomEquipe1 = input.nextLine();

		System.out.println("Nom equipe 2");
		String nomEquipe2 = input.nextLine();

		equipes1.setNomEquipe(nomEquipe1);
		equipes2.setNomEquipe(nomEquipe2);

		// On Affiche on exemple de grille
		for (int i = 0 ; i < gj1.getCases().length + 1 ; i++)
		{
			if (i == 0)
				System.out.println("Y/X: 0 1 2 3 4 5 6 7 8 9");
			else
				System.out.println("   " + (i - 1) + "| | | | | | | | | | |");
		}
		// On demende à l'équipe 1 de placé ces bateaux
		System.out.println(equipes1.getNomEquipe() + " Placé vos bateaux");
		ArrayList<Integer> list = new ArrayList<Integer>();
		// On demende des coordonées à la suite sans les vérifier (A ne pas
		// faire)
		// Il faudrait instancié directement les bateaux
		for (int i = 0 ; i < equipes1.getBateaux().length ; i++)

		{
			System.out.println(name[i]);
			System.out.println("X1");
			list.add(input.nextInt());
			System.out.println("Y1");
			list.add(input.nextInt());
			System.out.println("X2");
			list.add(input.nextInt());
			System.out.println("Y2");
			list.add(input.nextInt());

		}
		// On instancie les Bateaux de l'équipe 1
		try
		{
			equipes1.getBateaux()[0] = new ContretTorpilleur(list.get(0), list.get(1), list.get(2), list.get(3),
					equipes1);
			equipes1.getBateaux()[1] = new Croiseur(list.get(0), list.get(1), list.get(2), list.get(3), equipes1);
			equipes1.getBateaux()[2] = new PorteAvion(list.get(0), list.get(1), list.get(2), list.get(3), equipes1);
			equipes1.getBateaux()[3] = new SousMarin(list.get(0), list.get(1), list.get(2), list.get(3), equipes1);
			equipes1.getBateaux()[4] = new Torpilleur(list.get(0), list.get(1), list.get(2), list.get(3), equipes1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// On demende à l'équipe 2 de placé ces bateaux
		System.out.println(equipes1.getNomEquipe() + " Placé vos bateaux");
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		// On demende des coordonées à la suite sans les vérifier (A ne pas
		// faire)
		// Il faudrait instancié directement les bateaux
		for (int i = 0 ; i < equipes2.getBateaux().length ; i++)

		{
			System.out.println(name[i]);
			System.out.println("X1");
			list2.add(input.nextInt());
			System.out.println("Y1");
			list2.add(input.nextInt());
			System.out.println("X2");
			list2.add(input.nextInt());
			System.out.println("Y2");
			list2.add(input.nextInt());

		}
		// On instancie les Bateaux de l'équipe 2
		try
		{
			equipes2.getBateaux()[0] = new ContretTorpilleur(list.get(0), list.get(1), list.get(2), list.get(3),
					equipes2);
			equipes2.getBateaux()[1] = new Croiseur(list.get(0), list.get(1), list.get(2), list.get(3), equipes2);
			equipes2.getBateaux()[2] = new PorteAvion(list.get(0), list.get(1), list.get(2), list.get(3), equipes2);
			equipes2.getBateaux()[3] = new SousMarin(list.get(0), list.get(1), list.get(2), list.get(3), equipes2);
			equipes2.getBateaux()[4] = new Torpilleur(list.get(0), list.get(1), list.get(2), list.get(3), equipes2);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// On peux comméncé à joué
		// On joue tant que les deux equipes sont en vie
		while (equipes1.equipeEnVie() && equipes2.equipeEnVie())
		{
			System.out.println("Ou voulez-vous tirer " + equipes1.getNomEquipe());
			System.out.println("X ?");
			int x = input.nextInt();
			System.out.println("Y ?");
			int y = input.nextInt();
			equipes1.tire(equipes2, x, y);
			if (!equipes2.equipeEnVie())
				break;

			System.out.println("Ou voulez-vous tirer " + equipes2.getNomEquipe());
			System.out.println("X ?");
			int x1 = input.nextInt();
			System.out.println("Y ?");
			int y1 = input.nextInt();
			equipes2.tire(equipes1, x1, y1);
			if (!equipes1.equipeEnVie())
				break;

		}
		if (equipes1.equipeEnVie())
		{
			System.out.println(equipes1.getNomEquipe() + " est le gagnant !");
		}
		else
		{
			System.out.println(equipes2.getNomEquipe() + " est le gagnant !");
		}
	}
}
