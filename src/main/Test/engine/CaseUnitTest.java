package engine;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Created by valenza on 06/06/16.
 */
public class CaseUnitTest {

    @Test
    public void testTouchePasCouler() throws Exception{
        GrilleJeux gj = new GrilleJeux();
        Equipe e1 = new Equipe(gj, "e1");
        Case c = new Case(gj, 0, 0);
        PorteAvion b = new PorteAvion(0,0,4,0,e1);

        c.setBateau(b);
        b.setTaille(2);
        b.touche();

        assertEquals(1,b.getTaille());
        assertFalse(b.getEstCoule());
    }
    @Test
    public void testToucheCouler() throws Exception{
        GrilleJeux gj = new GrilleJeux();
        Equipe e1 = new Equipe(gj, "e1");
        PorteAvion b = new PorteAvion(0,0,4,0,e1);

        b.setTaille(2);
        b.touche();
        b.touche();

        assertEquals(0,b.getTaille());
        assertTrue(b.getEstCoule());
    }
}
