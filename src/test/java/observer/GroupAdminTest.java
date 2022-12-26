package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this class implements observer pattern by Organize a group of recipients of updates on the status of
 * UndoableStringBuilder and send all updates to it in real time.
 * @ authors : evyatar yosef , itamar gueta.
 * @ version : 0.99.
 */
class GroupAdminTest {

    GroupAdmin ga = new GroupAdmin();
   // UndoableStringBuilder ustb = new UndoableStringBuilder();
    ConcreteMember cm1 = new ConcreteMember();
    ConcreteMember cm2 = new ConcreteMember();


 @Test
    void register()
    {
        ga.append("evyatar");//
        ga.register(cm1);
        assertEquals(ga.observers.get(0),cm1,"register failed");

        ga.register(cm2);
        assertEquals(ga.observers.get(1),cm2,"register failed");
    }

    @Test
    void unregister()
    {
         ga.append("evyatar");
         ga.register(cm1);
         ga.register(cm2);
         ga.unregister(cm1);
         assertFalse(ga.observers.contains(cm1),"failed");
         ga.unregister(cm2);
         assertFalse(ga.observers.contains(cm2),"failed");
    }

    @Test
    void insert()
    {
        ga.append("foot");
        ga.insert(4,"ball");
        assertEquals("football",ga.toString(),"test failed");
        ga.register(cm1);
        assertEquals(ga.observers.get(0).toString(),ga.toString(),"test failed");
    }

    @Test
    void append()
    {
        ga.append("evyatar");
        ga.register(cm1);
        assertEquals("evyatar",ga.toString(),"test failed");
        assertEquals(ga.observers.get(0).toString(),ga.toString(),"test failed");

        ga.append(" ");
        assertEquals("evyatar ", ga.toString(),"test failed");
        assertEquals(ga.observers.get(0).toString(),ga.toString(),"test failed");
        ga.register(cm2);
        assertEquals(ga.observers.get(1).toString(),ga.toString(),"test failed");

        ga.append(null);
        assertEquals("evyatar null", ga.toString(),"test failed");
    }

    @Test
    void delete()
    {
        ga.register(cm1);
        ga.append("evyatar is very");
        ga.delete(0,3);
        assertEquals("atar is very", ga.toString(),"test failed");
        assertEquals(ga.observers.get(0).toString(),ga.toString(),"test failed");

        ga.delete(8,12);
        ga.register(cm2);
        assertEquals("atar is ", ga.toString(),"test failed");
        assertEquals(ga.observers.get(1).toString(),ga.toString(),"test failed");
    }

    @Test
    void undo()
    {
        ga.register(cm1);
        ga.append("test");
        ga.register(cm2);
        ga.undo();
        assertEquals("",ga.toString(),"test failed");
        assertEquals(ga.observers.get(0).toString(),ga.toString(),"test failed");
        ga.undo();
        assertEquals(ga.observers.get(0).toString(),"","test failed");
        assertEquals(ga.observers.get(1).toString(),ga.observers.get(0).toString(),"test failed");

        ga.append("evyatar");
        ga.delete(2,5);
        ga.undo();
        assertEquals("evyatar",ga.toString(),"test failed");
        assertEquals(ga.observers.get(1).toString(),ga.toString(),"test failed");
        assertEquals(ga.observers.get(1).toString(),ga.observers.get(0).toString(),"test failed");
        ga.undo();

        ga.append("test success");
        ga.undo();
        assertEquals("",ga.toString(),"test failed");
        ga.undo();


    }
}