package engine;

import engine.Bateaux;
import engine.Equipe;

public class SousMarin extends Bateaux {
	public SousMarin(int x1, int y1, int x2, int y2, Equipe e) throws Exception {
		super(x1, y1, x2, y2, e, 3);
	}
}
