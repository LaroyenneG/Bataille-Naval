package engine;

import engine.Case;
import engine.Equipe;

public class GrilleJeux {
	private Case[][] cases = new Case[10][10];
	private Equipe[] equipes = new Equipe[2];
	int currentEquipe;

	public GrilleJeux() {
		for(int x = 0; x < this.cases.length; ++x) {
			for(int y = 0; y < this.cases[x].length; ++y) {
				this.cases[x][y] = new Case(this, x, y);
			}
		}

	}

	public Case[][] getCases() {
		return this.cases;
	}

	public Equipe[] getEquipes() {
		return this.equipes;
	}

	public void setEquipes(Equipe[] equipes) {
		this.equipes = equipes;
	}

	public int getCurrentEquipe() {
		return this.currentEquipe;
	}

	public void setCurrentEquipe(int currentEquipe) {
		this.currentEquipe = currentEquipe;
	}
}

