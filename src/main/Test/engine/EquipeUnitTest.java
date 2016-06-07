package engine;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by valenza on 04/06/16.
 */
public class EquipeUnitTest {

    @Test
    public void testVerifictation(){
        GrilleJeux grille = mock(GrilleJeux.class);
        Equipe eq1 = mock(Equipe.class);
        when(eq1.verification(eq1,1,2)).thenReturn(false);
    }

    @Test
    public void testVerificationAdjacentCase2(){
        GrilleJeux grille = new GrilleJeux();
        Equipe eq1 =new Equipe(grille, "e1");
        Case case1 = new Case(grille,0,2);
        Case[] c = new Case[]{case1};
        assertEquals(true, eq1.verification_adjacent_case2(eq1, 1, 2,c ));

        assertEquals(false, eq1.verification_adjacent_case2(eq1, 0, 9,c ));
        assertEquals(false, eq1.verification_adjacent_case2(eq1, 0, 10,c ));
    }

    @Test
    public void testVerificationAdjacentX(){
        GrilleJeux grille = new GrilleJeux();
        Equipe eq1 =new Equipe(grille, "eq1");
        Case case1 = new Case(grille,0,9);
        Case[] c = new Case[]{case1};

        assertEquals(true, eq1.verification_adjacent_x(eq1, 1,2, c, 5));
        assertEquals(true, eq1.verification_adjacent_x(eq1, 1,9, c, 5));
        assertEquals(true, eq1.verification_adjacent_x(eq1, 1,1, c, 5));
        assertEquals(false, eq1.verification_adjacent_x(eq1, 0,9, c, 5));
    }

    @Test
    public void testVerficationAdajacentY(){
        GrilleJeux grille = new GrilleJeux();
        Equipe eq1 =new Equipe(grille, "eq1");
        Case case1 = new Case(grille,0,9);
        Case[] c = new Case[]{case1, case1, case1, case1, case1};

        assertEquals(false, eq1.verification_adjacent_y(eq1, 0, 1, c, 5));
        assertEquals(true, eq1.verification_adjacent_y(eq1, 1, 8, c, 5));
        assertEquals(false, eq1.verification_adjacent_y(eq1, 1, 7, c, 5));
        assertEquals(false, eq1.verification_adjacent_y(eq1, 0, 9, c, 5));

    }

    @Test
    public void testTireVide(){
        GrilleJeux gj = new GrilleJeux();
        Equipe e2 = new Equipe(mock(GrilleJeux.class), "e2");
        Equipe e1 = new Equipe(gj, "e1");
        assertFalse(e2.tire(e1, 0, 0));
        assertFalse(e2.tire(e1, 9, 9));
    }

    @Test
    public void testTirePasVide(){
        GrilleJeux gj = new GrilleJeux();
        Equipe e2 = new Equipe(mock(GrilleJeux.class), "e2");
        Equipe e1 = new Equipe(gj, "e1");
        e1.getGj().getCases()[5][5].setVide(false);
        assertTrue(e2.tire(e1, 5, 5));
    }

    @Test
    public void testSetPlacementEnX() throws Exception {
        GrilleJeux gj = new GrilleJeux();
        Equipe e1 = new Equipe(gj, "e1");
        PorteAvion b = new PorteAvion(0,0,4,0,e1);

        e1.gj.getCases()[0][0].setVide(true);
        e1.gj.getCases()[1][0].setVide(true);
        e1.gj.getCases()[2][0].setVide(true);
        e1.gj.getCases()[3][0].setVide(true);
        e1.gj.getCases()[4][0].setVide(true);

        Case[] c = new Case[]{gj.getCases()[0][0], gj.getCases()[1][0], gj.getCases()[2][0], gj.getCases()[3][0], gj.getCases()[4][0]};
        assertArrayEquals(c, e1.setPlacement(b, 0,0,4,0));

    }

    @Test
    public void testSetPlacementEnY() throws Exception {
        GrilleJeux gj = new GrilleJeux();
        Equipe e1 = new Equipe(gj, "e1");
        PorteAvion b = new PorteAvion(0,0,0,4,e1);

        e1.gj.getCases()[0][0].setVide(true);
        e1.gj.getCases()[0][1].setVide(true);
        e1.gj.getCases()[0][2].setVide(true);
        e1.gj.getCases()[0][3].setVide(true);
        e1.gj.getCases()[0][4].setVide(true);

        Case[] c = new Case[]{gj.getCases()[0][0], gj.getCases()[0][1], gj.getCases()[0][2], gj.getCases()[0][3], gj.getCases()[0][4]};
        assertArrayEquals(c, e1.setPlacement(b, 0,0,0,4));
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = Exception.class)
    public void testSetPlacementNonAligner() throws Exception {
        GrilleJeux gj = new GrilleJeux();
        Equipe e1 = new Equipe(gj, "e1");
        PorteAvion b = new PorteAvion(0, 0, 1, 4, e1);

        thrown.expect(Exception.class);
        thrown.expectMessage("Case non alignÃ©e");
    }

    @Test(expected = Exception.class)
    public void testSetPlacementXNonVide() throws Exception {
        GrilleJeux gj = new GrilleJeux();
        Equipe e1 = new Equipe(gj, "e1");
        PorteAvion b = new PorteAvion(0, 0, 4, 0, e1);

        e1.gj.getCases()[0][0].setVide(true);
        e1.gj.getCases()[1][0].setVide(true);
        e1.gj.getCases()[3][0].setVide(true);
        e1.gj.getCases()[4][0].setVide(true);

        e1.setPlacement(b, 0,0,4,0);
        thrown.expect(Exception.class);
        thrown.expectMessage("Case 2,0 non vide");
    }

    @Test(expected = Exception.class)
    public void testSetPlacementYNonVide() throws Exception {
        GrilleJeux gj = new GrilleJeux();
        Equipe e1 = new Equipe(gj, "e1");
        PorteAvion b = new PorteAvion(0,0,0,4,e1);

        e1.gj.getCases()[0][0].setVide(true);
        e1.gj.getCases()[0][1].setVide(true);
        e1.gj.getCases()[0][3].setVide(true);
        e1.gj.getCases()[0][4].setVide(true);

        e1.setPlacement(b, 0,0,0,4);
        thrown.expect(Exception.class);
        thrown.expectMessage("Case 0,2 non vide");
    }

    @Test
    public void testConvertToLetter(){
        GrilleJeux gj = mock(GrilleJeux.class);
        Equipe e = new Equipe(gj, "e");
        int x = 0;
        assertEquals(65, e.convertToLettre(x));
        assertNotEquals(66, e.convertToLettre(x));
        x = 9;
        assertEquals(74, e.convertToLettre(x));
        assertNotEquals(73, e.convertToLettre(x));
    }


}