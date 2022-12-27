package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {

    UndoableStringBuilder ustb = new UndoableStringBuilder();
    ConcreteMember cm1 = new ConcreteMember();

    @Test
    void update()
    {
        ustb.append("evyatar");
        cm1.update(ustb);
        assertEquals("evyatar",cm1.toString(),"update failed");
        ustb.delete(3,4);
        cm1.update(ustb);
        assertEquals("evytar",cm1.toString(),"update failed");
        ustb.insert(3,"a");
        ustb.insert(7," theking");
        cm1.update(ustb);
        assertEquals("evyatar theking",cm1.toString(),"update failed");

    }

    @Test
    void testToString()
    {
        ustb.append("to sring() work well");
        cm1.update(ustb);
        cm1.toString();
        assertEquals("to sring() work well",ustb.toString(),"to string failed");
    }
}