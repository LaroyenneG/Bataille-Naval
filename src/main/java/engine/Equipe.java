/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package engine;

import java.util.Arrays;

import engine.Jukebox.Jukebox;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Equipe.
 *
 * @author nicolas
 */
public class Equipe
{
	// Son de l equipe
	private Jukebox			jukebox			= new Jukebox();

	// Grille de l'equipe
	GrilleJeux				gj;
	// Si l'equipe a tirer dans le tour passage a true
	static private boolean	tirDisponible	= false;

	/**
	 * Description of the property nomEquipe.
	 */
	private String			nomEquipe		= "";

	/**
	 * Description of the property bateaux.
	 */
	private Bateaux[]		bateaux			= new Bateaux[5];

	/**
	 * The constructor.
	 */
	public Equipe(GrilleJeux _gj, String nom_equipe)
	{
		// Start of user code constructor for Equipe)
		super();
		this.gj = _gj;
		nomEquipe = nom_equipe;
		// End of user code
	}

	/*
	 * Fonction de tire
	 *
	 */
	public boolean tire(Equipe e, int x, int y)
	{
		// Si la case contient un bateau
		Case c = e.getGj().getCases()[x][y];
		c.touche();
		return !c.getVide();

	}

	// Start of user code (user defined methods for Equipe)
	/*
	 * Placement du torpilleur
	 *
	 */
	public Case[] setPlacement(Bateaux b, int x1, int y1, int x2, int y2) throws Exception
	{

		Case[] cases = new Case[b.getTaille()];
		for (int i = 0 ; i < b.getTaille() ; i++)
		{
			if (x2 - x1 == b.getTaille() - 1 && y1 == y2)
			{
				if (!this.getGj().getCases()[x1 + i][y1].getVide())
				{
					throw new Exception("Case " + (convertToLettre(x1 + i)) + ',' + (y1+1) + " non vide");
				}

			}
			else if (y2 - y1 == b.getTaille() - 1 && x1 == x2)
			{
				if (!this.getGj().getCases()[x1][y1 + i].getVide())
				{
					throw new Exception("Case " + (convertToLettre(x1)) + ',' + (y1 + i +1) + " non vide");
				}

			}
			else
			{
				throw new Exception("Cases non alignées");
			}
		}
		for (int i = 0 ; i < b.getTaille() ; i++)
		{
			if (x2 - x1 == b.getTaille() - 1)
			{
				if (this.getGj().getCases()[x1 + i][y1].getVide())
				{
					this.getGj().getCases()[x1 + i][y1].setBateau(b);
					this.getGj().getCases()[x1 + i][y1].setVide(false);
					cases[i] = this.getGj().getCases()[x1 + i][y1];
				}

			}
			else
			{
				if (this.getGj().getCases()[x1][y1 + i].getVide())
				{
					this.getGj().getCases()[x1][y1 + i].setBateau(b);
					this.getGj().getCases()[x1][y1 + i].setVide(false);
					cases[i] = this.getGj().getCases()[x1][y1 + i];
				}

			}
		}

		return cases;

	}
	protected char convertToLettre(int x){
		char resultat;
		int res= 65+x;
		resultat = ((char)res);
		System.out.println(resultat+"		"+res);
		return resultat;
	}


	protected boolean verification(Equipe e, int a, int b)
	{
		if (a >= 0 && a <= 9 && b >= 0 && b <= 9)
		{
			if (!e.gj.getCases()[a][b].getTouche())
				return true;
		}
		return false;
	}

	protected boolean verification_adjacent_case2(Equipe e, int x, int y, Case c[])
	{
		boolean bool = false;
		if ((x == (c[0].getX() + 1) && y == (c[0].getY())) || (x == (c[0].getX() - 1) && y == (c[0].getY()))
				|| (x == (c[0].getX()) && y == (c[0].getY() + 1)) || (x == (c[0].getX()) && y == (c[0].getY() - 1)))
		{
			if (!e.gj.getCases()[x][y].getTouche())
			{
				bool = true;
			}
		}
		return bool;
	}

	protected boolean verification_adjacent_x(Equipe e, int x, int y, Case c[], int taille)
	{
		boolean bool = false;
		int i;
		int min = c[0].getX();
		int max = c[0].getX();
		for (i = 0 ; i < c.length - 1 ; i++)
		{
			if (c[i].getX() > max)
				max = c[i].getX();
			else if (c[i].getX() < min)
				min = c[i].getX();
		}
		if (x == (min + 1) || x == (min - 1) || x == (max + 1) || x == max - 1)
		{
			if (!e.gj.getCases()[x][y].getTouche())
			{
				bool = true;
			}
		}
		return bool;
	}

	protected boolean verification_adjacent_y(Equipe e, int x, int y, Case c[], int taille)
	{
		boolean bool = false;
		int i;
		int min = c[0].getY();
		int max = c[0].getY();
		for (i = 0 ; i < taille ; i++)
		{
			if (c[i].getY() > max)
			{
				max = c[i].getY();
			}
			else if (c[i].getY() < min)
			{
				min = c[i].getY();
			}
		}
		if (y == (min + 1) || y == (min - 1) || y == (max + 1) || y == (max - 1))
		{
			if (!e.gj.getCases()[x][y].getTouche())
			{
				bool = true;
			}
		}
		return bool;
	}

	// End of user code
	/**
	 * Returns nomEquipe.
	 * 
	 * @return nomEquipe
	 */
	public String getNomEquipe()
	{
		return this.nomEquipe;
	}

	/**
	 * Sets a value to attribute nomEquipe.
	 * 
	 * @param newNomEquipe
	 */
	public void setNomEquipe(String newNomEquipe)
	{
		this.nomEquipe = newNomEquipe;
	}

	public boolean getPeuxTirer()
	{
		return tirDisponible;
	}

	public void setPeuxTirer(boolean tirDisponible)
	{
		this.tirDisponible = tirDisponible;
	}

	/**
	 * Returns bateaux.
	 * 
	 * @return bateaux
	 */
	public Bateaux[] getBateaux()
	{
		return this.bateaux;
	}

	public boolean equipeEnVie()
	{
		int count = 0;
		for (int i = 0 ; i < bateaux.length ; i++)
		{
			if (bateaux[i].getEstCoule())
			{
				count++;
			}
		}
		return count == 5;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(bateaux);
		result = prime * result + ((nomEquipe == null) ? 0 : nomEquipe.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipe other = (Equipe) obj;
		if (!Arrays.equals(bateaux, other.bateaux))
			return false;
		if (nomEquipe == null)
		{
			if (other.nomEquipe != null)
				return false;
		}
		else if (!nomEquipe.equals(other.nomEquipe))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Equipe [nomEquipe=" + nomEquipe + ", bateaux=" + Arrays.toString(bateaux) + "]";
	}

	public GrilleJeux getGj()
	{
		return gj;
	}

	public void setGj(GrilleJeux gj)
	{
		this.gj = gj;
	}

	public void setBateaux(Bateaux[] bateaux)
	{
		this.bateaux = bateaux;
	}

	public Jukebox getJukebox()
	{
		return jukebox;
	}

}
