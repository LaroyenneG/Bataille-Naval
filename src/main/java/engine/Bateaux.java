/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package engine;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Bateaux.
 * 
 * @author nicolas
 */
public abstract class Bateaux
{
	/**
	 * Description of the property taille.
	 */
	private int		taille		= -1;

	/**
	 * Description of the property cases.
	 */
	private Case[]	cases;

	/**
	 * Description of the property equipe.
	 */
	private Equipe	equipe;

	/**
	 * Description of the property estCoule.
	 */
	private boolean	estCoule	= false;

	/**
	 * Description of the property equipes.
	 */
	private Equipe	equipes		= null;

	// Start of user code (user defined attributes for Bateaux)

	// End of user code

	/**
	 * The constructor.
	 * 
	 * @throws Exception
	 * @throws ExecutionException
	 */
	public Bateaux(int x1, int y1, int x2, int y2, Equipe e, int taille) throws Exception
	{
		// Start of user code constructor for Bateaux)
		super();
		this.equipe = e;
		try
		{
			setTaille(taille);
		}
		catch (ExecutionException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cases = e.setPlacement(this, x1, y1, x2, y2);
		// End of user code
	}

	// Start of user code (user defined methods for Bateaux)

	// End of user code
	/**
	 * Returns taille.
	 * 
	 * @return taille
	 */
	public int getTaille()
	{
		return this.taille;
	}

	/**
	 * Sets a value to attribute taille.
	 * 
	 * @param newTaille
	 * @throws ExecutionException
	 */
	public void setTaille(int newTaille) throws ExecutionException
	{
		if (newTaille >= 2 && newTaille <= 5)
		{
			this.taille = newTaille;
		}
		else
			throw new ExecutionException("La taille doit être comprise entre 2 et 5.", null);

	}

	/**
	 * Returns cases.
	 * 
	 * @return cases
	 */
	public Case[] getCases()
	{
		return this.cases;
	}

	/**
	 * Returns equipe.
	 * 
	 * @return equipe
	 */
	public Equipe getEquipe()
	{
		return this.equipe;
	}

	/**
	 * Sets a value to attribute equipe.
	 * 
	 * @param newEquipe
	 */
	public void setEquipe(Equipe newEquipe)
	{
		this.equipe = newEquipe;
	}

	/**
	 * Returns estCoule.
	 * 
	 * @return estCoule
	 */
	public boolean getEstCoule()
	{
		return this.estCoule;
	}

	/**
	 * Sets a value to attribute estCoule.
	 * 
	 * @param newEstCoule
	 */
	public void setEstCoule(boolean newEstCoule)
	{
		this.estCoule = newEstCoule;
	}

	/**
	 * Returns equipes.
	 * 
	 * @return equipes
	 */
	public Equipe getEquipes()
	{
		return this.equipes;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cases);
		result = prime * result + ((equipe == null) ? 0 : equipe.hashCode());
		result = prime * result + ((equipes == null) ? 0 : equipes.hashCode());
		result = prime * result + (estCoule ? 1231 : 1237);
		result = prime * result + taille;
		return result;
	}

	public void touche()
	{
		if (taille > 0)
		{
			taille--;
		}
		if (taille <= 0)
			setEstCoule(true);
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
		Bateaux other = (Bateaux) obj;
		if (!Arrays.equals(cases, other.cases))
			return false;
		if (equipe == null)
		{
			if (other.equipe != null)
				return false;
		}
		else if (!equipe.equals(other.equipe))
			return false;
		if (equipes == null)
		{
			if (other.equipes != null)
				return false;
		}
		else if (!equipes.equals(other.equipes))
			return false;
		if (estCoule != other.estCoule)
			return false;
		if (taille != other.taille)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Bateaux [taille=" + taille + ", cases=" + Arrays.toString(cases) + ", equipe=" + equipe + ", estCoule="
				+ estCoule + ", equipes=" + equipes + "]";
	}

}
