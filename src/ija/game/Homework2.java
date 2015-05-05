
package ija.game;

import ija.game.board.MazeCard;
import ija.game.board.MazeField;
import ija.game.board.MazeBoard;
import java.lang.reflect.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Homework2: uloha c. 2 z IJA
 * Trida testujici implementaci zadani 2. ukolu.
 */
public class Homework2 {

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testMazeCard() {
        MazeCard mcC1 = null, mcC2 = null, mcL1 = null, mcF1 = null;

        try { mcC1 = MazeCard.create("C"); } catch (IllegalArgumentException ex) { fail("Chyba pri vytvareni kamene C"); }
        try { mcC2 = MazeCard.create("C"); } catch (IllegalArgumentException ex) { fail("Chyba pri vytvareni kamene C"); }
        try { mcL1 = MazeCard.create("L"); } catch (IllegalArgumentException ex) { fail("Chyba pri vytvareni kamene L"); }
        try { mcF1 = MazeCard.create("F"); } catch (IllegalArgumentException ex) { fail("Chyba pri vytvareni kamene F"); }

        try {
            MazeCard mcFail = MazeCard.create("U");
            fail("Hraci kamen U nelze vytvorit");
        } catch (IllegalArgumentException ex) { /* test ok */  }

        assertFalse("Byly vytvoreny stejne kameny", mcC1.equals(mcC2));

        assertTrue("Kostka mcC1 - lze jit doleva", mcC1.canGo(MazeCard.CANGO.LEFT));
        assertTrue("Kostka mcC1 - lze jit nahoru",  mcC1.canGo(MazeCard.CANGO.UP));
        assertFalse("Kostka mcC1 - nelze jit doprava", mcC1.canGo(MazeCard.CANGO.RIGHT));
        assertFalse("Kostka mcC1 - nelze jit dolu", mcC1.canGo(MazeCard.CANGO.DOWN));
        mcC1.turnRight();
        assertTrue("Kostka mcC1 - lze jit doprava", mcC1.canGo(MazeCard.CANGO.RIGHT));
        assertTrue("Kostka mcC1 - lze jit nahoru",  mcC1.canGo(MazeCard.CANGO.UP));
        assertFalse("Kostka mcC1 - nelze jit doleva", mcC1.canGo(MazeCard.CANGO.LEFT));
        assertFalse("Kostka mcC1 - nelze jit dolu", mcC1.canGo(MazeCard.CANGO.DOWN));

        assertTrue("Kostka mcF1 - lze jit doleva", mcF1.canGo(MazeCard.CANGO.LEFT));
        assertTrue("Kostka mcF1 - lze jit nahoru",  mcF1.canGo(MazeCard.CANGO.UP));
        assertTrue("Kostka mcF1 - lze jit doprava", mcF1.canGo(MazeCard.CANGO.RIGHT));
        assertFalse("Kostka mcF1 - nelze jit dolu", mcF1.canGo(MazeCard.CANGO.DOWN));
        mcF1.turnRight();
        assertFalse("Kostka mcF1 - nelze jit doleva", mcF1.canGo(MazeCard.CANGO.LEFT));
        assertTrue("Kostka mcF1 - lze jit nahoru",  mcF1.canGo(MazeCard.CANGO.UP));
        assertTrue("Kostka mcF1 - lze jit doprava", mcF1.canGo(MazeCard.CANGO.RIGHT));
        assertTrue("Kostka mcF1 - nelze jit dolu", mcF1.canGo(MazeCard.CANGO.DOWN));
    }

    @Test
    public void testMazeBoard01() {
        int n = 5;
        MazeBoard mb = MazeBoard.createMazeBoard(n);

        MazeField mf1 = mb.get(1, 2);
        assertEquals("Policko: radek = 1", 1, mf1.row());
        assertEquals("Policko: sloupec = 2", 2, mf1.col());
        assertNull("Policko je prazdne", mf1.getCard());
    }

    @Test
    public void testMazeBoard02() {
        int n = 5;
        int c = 4;

        MazeBoard mb = MazeBoard.createMazeBoard(n);
        mb.newGame();

        MazeField mf1 = mb.get(1, c);
        assertEquals("Policko: radek = 1", 1, mf1.row());
        assertEquals("Policko: sloupec = " + c, c, mf1.col());
        MazeCard c1 = mf1.getCard();
        assertNotNull("Policko neni prazdne", c1);

        MazeField mf2 = mb.get(n, c);
        assertEquals("Policko: radek = " + n, n, mf2.row());
        assertEquals("Policko: sloupec = " + c, c, mf2.col());
        MazeCard c2 = mf2.getCard();

        MazeCard cF = mb.getFreeCard();
        assertNotNull("Volna karta existuje", cF);
    }

    @Test
    public void testMazeBoard03() {
        int n = 5;
        int c = 4;

        MazeBoard mb = MazeBoard.createMazeBoard(n);
        mb.newGame();

        MazeField mf1 = mb.get(1, c);
        MazeCard cF = mb.getFreeCard();
        assertNotNull("Volna karta existuje", cF);

        MazeCard [] ar = new MazeCard[n];
        for (int i = 0; i < n; i++) {
            ar[i] = mb.get(i+1, c).getCard();
        }
        mb.shift(mf1); // [1,c]
        for (int i = 0; i < n-1; i++) {
            assertEquals("Test posunu sloupce", ar[i], mb.get(i+2, c).getCard());
        }
        assertEquals("Na prvni radek byla vlozena volna karta", cF, mb.get(1,c).getCard());
        assertEquals("Volnou kartou se stala karta puvodne na poslednim radku", ar[n-1], mb.getFreeCard());
    }

    @Test
    public void testMazeBoard04() {
        int n = 5;
        int c = 1;

        MazeBoard mb = MazeBoard.createMazeBoard(n);
        mb.newGame();

        MazeField mf1 = mb.get(1, c);
        MazeCard cF = mb.getFreeCard();
        assertNotNull("Volna karta existuje", cF);

        MazeCard [] ar = new MazeCard[n];
        for (int i = 0; i < n; i++) {
            ar[i] = mb.get(i+1, c).getCard();
        }
        mb.shift(mf1); // [1,c]
        for (int i = 0; i < n; i++) {
            assertEquals("Test posunu sloupce (nema k nemu dojit)", ar[i], mb.get(i+1, c).getCard());
        }
        assertEquals("Volna karta je stejna", cF, mb.getFreeCard());
    }
}
