package engine;

import engine.Case;
import engine.Equipe;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public abstract class Bateaux {
	private int taille = -1;
	private Case[] cases;
	private Equipe equipe;
	private boolean estCoule = false;
	private Equipe equipes = null;

	public Bateaux(int x1, int y1, int x2, int y2, Equipe e, int taille) throws Exception {
		this.equipe = e;

		try {
			this.setTaille(taille);
		} catch (ExecutionException var8) {
			var8.printStackTrace();
		}

		this.cases = e.setPlacement(this, x1, y1, x2, y2);
	}

	public int getTaille() {
		return this.taille;
	}

	public void setTaille(int newTaille) throws ExecutionException {
		if(newTaille >= 2 && newTaille <= 5) {
			this.taille = newTaille;
		} else {
			throw new ExecutionException("La taille doit être comprise entre 2 et 5.", (Throwable)null);
		}
	}

	public Case[] getCases() {
		return this.cases;
	}

	public Equipe getEquipe() {
		return this.equipe;
	}

	public void setEquipe(Equipe newEquipe) {
		this.equipe = newEquipe;
	}

	public boolean getEstCoule() {
		return this.estCoule;
	}

	public void setEstCoule(boolean newEstCoule) {
		this.estCoule = newEstCoule;
	}

	public Equipe getEquipes() {
		return this.equipes;
	}

	public int hashCode() {
		boolean prime = true;
		byte result = 1;
		int result1 = 31 * result + Arrays.hashCode(this.cases);
		result1 = 31 * result1 + (this.equipe == null?0:this.equipe.hashCode());
		result1 = 31 * result1 + (this.equipes == null?0:this.equipes.hashCode());
		result1 = 31 * result1 + (this.estCoule?1231:1237);
		result1 = 31 * result1 + this.taille;
		return result1;
	}

	public void touche() {
		if(this.taille > 0) {
			--this.taille;
		}

		if(this.taille <= 0) {
			this.setEstCoule(true);
		}

	}

	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		} else if(obj == null) {
			return false;
		} else if(this.getClass() != obj.getClass()) {
			return false;
		} else {
			Bateaux other = (Bateaux)obj;
			if(!Arrays.equals(this.cases, other.cases)) {
				return false;
			} else {
				if(this.equipe == null) {
					if(other.equipe != null) {
						return false;
					}
				} else if(!this.equipe.equals(other.equipe)) {
					return false;
				}

				if(this.equipes == null) {
					if(other.equipes != null) {
						return false;
					}
				} else if(!this.equipes.equals(other.equipes)) {
					return false;
				}

				return this.estCoule != other.estCoule?false:this.taille == other.taille;
			}
		}
	}

	public String toString() {
		return "Bateaux [taille=" + this.taille + ", cases=" + Arrays.toString(this.cases) + ", equipe=" + this.equipe + ", estCoule=" + this.estCoule + ", equipes=" + this.equipes + "]";
	}
}
