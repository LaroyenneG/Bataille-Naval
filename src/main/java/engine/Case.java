/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package engine;

import engine.Bateaux;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Case.
 * 
 * @author nicolas
 */
public class Case {

	/**
	 * Description of the property vide.
	 */
	boolean vide = true;

	/**
	 * Description of the property grilleJeuxs.
	 */
	private GrilleJeux grilleJeuxs;

	/**
	 * Description of the property bateau.
	 */
	private Bateaux bateau;

	/**
	 * Description of the property touche.
	 */
	boolean touche = false;

	int x = -1;
	int y = -1;

	// Start of user code (user defined attributes for Case)

	// End of user code

	/**
	 * The constructor.
	 */
	public Case(GrilleJeux gj, int x, int y) {
		// Start of user code constructor for Case)
		super();
		grilleJeuxs = gj;
		this.x = x;
		this.y = y;
		// End of user code
	}

	// Start of user code (user defined methods for Case)

	/**
	 * Returns vide.
	 * 
	 * @return vide
	 */
	public boolean getVide() {
		return this.vide;
	}

	/**
	 * Sets a value to attribute vide.
	 * 
	 * @param newVide
	 */
	public void setVide(boolean newVide) {
		this.vide = newVide;
	}

	/**
	 * Returns grilleJeuxs.
	 * 
	 * @return grilleJeuxs
	 */
	public GrilleJeux getGrilleJeuxs() {
		return this.grilleJeuxs;
	}

	/**
	 * Sets a value to attribute grilleJeuxs.
	 * 
	 * @param newGrilleJeuxs
	 */
	public void setGrilleJeuxs(GrilleJeux newGrilleJeuxs) {
		this.grilleJeuxs = newGrilleJeuxs;
	}

	/**
	 * Returns bateau.
	 * 
	 * @return bateau
	 */
	public Bateaux getBateau() {
		return this.bateau;
	}

	/**
	 * Sets a value to attribute bateau.
	 * 
	 * @param newBateau
	 */
	public void setBateau(Bateaux newBateau) {
		this.bateau = newBateau;
	}

	/**
	 * Returns touche.
	 * 
	 * @return touche
	 */
	public boolean getTouche() {
		return this.touche;
	}

	/**
	 * Sets a value to attribute touche.
	 * 
	 * @param newTouche
	 */
	public void setTouche(boolean newTouche) {
		this.touche = newTouche;

	}
	public void touche()
	{
		setTouche(true);
		try{
			bateau.touche();
		}catch(Exception e)
		{
			//e.printStackTrace();
		}
	}

	/**
	 * Lors du placement Detecte les cases qui sont disponibles
	 */

	public boolean casePossible(Case[] c) {
		if (c.length == 1) {

		}
		return true;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Case [vide=" + vide + ", grilleJeuxs=" + grilleJeuxs + ", bateau=" + bateau + ", touche=" + touche
				+ ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bateau == null) ? 0 : bateau.hashCode());
		result = prime * result + ((grilleJeuxs == null) ? 0 : grilleJeuxs.hashCode());
		result = prime * result + (touche ? 1231 : 1237);
		result = prime * result + (vide ? 1231 : 1237);
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Case other = (Case) obj;
		if (bateau == null) {
			if (other.bateau != null)
				return false;
		} else if (!bateau.equals(other.bateau))
			return false;
		if (grilleJeuxs == null) {
			if (other.grilleJeuxs != null)
				return false;
		} else if (!grilleJeuxs.equals(other.grilleJeuxs))
			return false;
		if (touche != other.touche)
			return false;
		if (vide != other.vide)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}


}
