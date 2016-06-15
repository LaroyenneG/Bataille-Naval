package engine;

import engine.Bateaux;
import engine.GrilleJeux;

public class Case {
	boolean vide = true;
	private GrilleJeux grilleJeuxs;
	private Bateaux bateau;
	boolean touche = false;
	int x = -1;
	int y = -1;

	public Case(GrilleJeux gj, int x, int y) {
		this.grilleJeuxs = gj;
		this.x = x;
		this.y = y;
	}

	public boolean getVide() {
		return this.vide;
	}

	public void setVide(boolean newVide) {
		this.vide = newVide;
	}

	public GrilleJeux getGrilleJeuxs() {
		return this.grilleJeuxs;
	}

	public void setGrilleJeuxs(GrilleJeux newGrilleJeuxs) {
		this.grilleJeuxs = newGrilleJeuxs;
	}

	public Bateaux getBateau() {
		return this.bateau;
	}

	public void setBateau(Bateaux newBateau) {
		this.bateau = newBateau;
	}

	public boolean getTouche() {
		return this.touche;
	}

	public void setTouche(boolean newTouche) {
		this.touche = newTouche;
	}

	public void touche() {
		this.setTouche(true);

		try {
			this.bateau.touche();
		} catch (Exception var2) {
			;
		}

	}

	public boolean casePossible(Case[] c) {
		if(c.length == 1) {
			;
		}

		return true;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString() {
		return "Case [vide=" + this.vide + ", grilleJeuxs=" + this.grilleJeuxs + ", bateau=" + this.bateau + ", touche=" + this.touche + ", x=" + this.x + ", y=" + this.y + "]";
	}

	public int hashCode() {
		boolean prime = true;
		byte result = 1;
		int result1 = 31 * result + (this.bateau == null?0:this.bateau.hashCode());
		result1 = 31 * result1 + (this.grilleJeuxs == null?0:this.grilleJeuxs.hashCode());
		result1 = 31 * result1 + (this.touche?1231:1237);
		result1 = 31 * result1 + (this.vide?1231:1237);
		result1 = 31 * result1 + this.x;
		result1 = 31 * result1 + this.y;
		return result1;
	}

	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		} else if(obj == null) {
			return false;
		} else if(this.getClass() != obj.getClass()) {
			return false;
		} else {
			Case other = (Case)obj;
			if(this.bateau == null) {
				if(other.bateau != null) {
					return false;
				}
			} else if(!this.bateau.equals(other.bateau)) {
				return false;
			}

			if(this.grilleJeuxs == null) {
				if(other.grilleJeuxs != null) {
					return false;
				}
			} else if(!this.grilleJeuxs.equals(other.grilleJeuxs)) {
				return false;
			}

			return this.touche != other.touche?false:(this.vide != other.vide?false:(this.x != other.x?false:this.y == other.y));
		}
	}
}

