package engine;

import engine.Bateaux;
import engine.Case;
import engine.GrilleJeux;
import engine.Jukebox.Jukebox;
import java.util.Arrays;

public class Equipe {
	private Jukebox jukebox = new Jukebox();
	GrilleJeux gj;
	private static boolean tirDisponible = false;
	private String nomEquipe = "";
	private Bateaux[] bateaux = new Bateaux[5];

	public Equipe(GrilleJeux _gj, String nom_equipe) {
		this.gj = _gj;
		this.nomEquipe = nom_equipe;
	}

	public boolean tire(Equipe e, int x, int y) {
		Case c = e.getGj().getCases()[x][y];
		c.touche();
		return !c.getVide();
	}

	public Case[] setPlacement(Bateaux b, int x1, int y1, int x2, int y2) throws Exception {
		Case[] cases = new Case[b.getTaille()];

		int i;
		for(i = 0; i < b.getTaille(); ++i) {
			if(x2 - x1 == b.getTaille() - 1 && y1 == y2) {
				if(!this.getGj().getCases()[x1 + i][y1].getVide()) {
					throw new Exception("Case " + this.convertToLettre(x1 + i) + ',' + (y1 + 1) + " non vide");
				}
			} else {
				if(y2 - y1 != b.getTaille() - 1 || x1 != x2) {
					throw new Exception("Cases non alignées");
				}

				if(!this.getGj().getCases()[x1][y1 + i].getVide()) {
					throw new Exception("Case " + this.convertToLettre(x1) + ',' + (y1 + i + 1) + " non vide");
				}
			}
		}

		for(i = 0; i < b.getTaille(); ++i) {
			if(x2 - x1 == b.getTaille() - 1) {
				if(this.getGj().getCases()[x1 + i][y1].getVide()) {
					this.getGj().getCases()[x1 + i][y1].setBateau(b);
					this.getGj().getCases()[x1 + i][y1].setVide(false);
					cases[i] = this.getGj().getCases()[x1 + i][y1];
				}
			} else if(this.getGj().getCases()[x1][y1 + i].getVide()) {
				this.getGj().getCases()[x1][y1 + i].setBateau(b);
				this.getGj().getCases()[x1][y1 + i].setVide(false);
				cases[i] = this.getGj().getCases()[x1][y1 + i];
			}
		}

		return cases;
	}

	protected char convertToLettre(int x) {
		int res = 65 + x;
		char resultat = (char)res;
		System.out.println(resultat + "\t\t" + res);
		return resultat;
	}

	protected boolean verification(Equipe e, int a, int b) {
		return a >= 0 && a <= 9 && b >= 0 && b <= 9 && !e.gj.getCases()[a][b].getTouche();
	}

	protected boolean verification_adjacent_case2(Equipe e, int x, int y, Case[] c) {
		boolean bool = false;
		if((x == c[0].getX() + 1 && y == c[0].getY() || x == c[0].getX() - 1 && y == c[0].getY() || x == c[0].getX() && y == c[0].getY() + 1 || x == c[0].getX() && y == c[0].getY() - 1) && !e.gj.getCases()[x][y].getTouche()) {
			bool = true;
		}

		return bool;
	}

	protected boolean verification_adjacent_x(Equipe e, int x, int y, Case[] c, int taille) {
		boolean bool = false;
		int min = c[0].getX();
		int max = c[0].getX();

		for(int i = 0; i < c.length - 1; ++i) {
			if(c[i].getX() > max) {
				max = c[i].getX();
			} else if(c[i].getX() < min) {
				min = c[i].getX();
			}
		}

		if((x == min + 1 || x == min - 1 || x == max + 1 || x == max - 1) && !e.gj.getCases()[x][y].getTouche()) {
			bool = true;
		}

		return bool;
	}

	protected boolean verification_adjacent_y(Equipe e, int x, int y, Case[] c, int taille) {
		boolean bool = false;
		int min = c[0].getY();
		int max = c[0].getY();

		for(int i = 0; i < taille; ++i) {
			if(c[i].getY() > max) {
				max = c[i].getY();
			} else if(c[i].getY() < min) {
				min = c[i].getY();
			}
		}

		if((y == min + 1 || y == min - 1 || y == max + 1 || y == max - 1) && !e.gj.getCases()[x][y].getTouche()) {
			bool = true;
		}

		return bool;
	}

	public String getNomEquipe() {
		return this.nomEquipe;
	}

	public void setNomEquipe(String newNomEquipe) {
		this.nomEquipe = newNomEquipe;
	}

	public boolean getPeuxTirer() {
		return tirDisponible;
	}

	public void setPeuxTirer(boolean tirDisponible) {
		tirDisponible = tirDisponible;
	}

	public Bateaux[] getBateaux() {
		return this.bateaux;
	}

	public boolean equipeEnVie() {
		int count = 0;

		for(int i = 0; i < this.bateaux.length; ++i) {
			if(this.bateaux[i].getEstCoule()) {
				++count;
			}
		}

		return count == 5;
	}

	public int hashCode() {
		boolean prime = true;
		byte result = 1;
		int result1 = 31 * result + Arrays.hashCode(this.bateaux);
		result1 = 31 * result1 + (this.nomEquipe == null?0:this.nomEquipe.hashCode());
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
			Equipe other = (Equipe)obj;
			if(!Arrays.equals(this.bateaux, other.bateaux)) {
				return false;
			} else {
				if(this.nomEquipe == null) {
					if(other.nomEquipe != null) {
						return false;
					}
				} else if(!this.nomEquipe.equals(other.nomEquipe)) {
					return false;
				}

				return true;
			}
		}
	}

	public String toString() {
		return "Equipe [nomEquipe=" + this.nomEquipe + ", bateaux=" + Arrays.toString(this.bateaux) + "]";
	}

	public GrilleJeux getGj() {
		return this.gj;
	}

	public void setGj(GrilleJeux gj) {
		this.gj = gj;
	}

	public void setBateaux(Bateaux[] bateaux) {
		this.bateaux = bateaux;
	}

	public Jukebox getJukebox() {
		return this.jukebox;
	}
}