import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    //  method to check external dependencies compatibility
    GroupAdmin ga = new GroupAdmin();
    ConcreteMember cm1 = new ConcreteMember();
    ConcreteMember cm2 = new ConcreteMember();
    UndoableStringBuilder ustb = new UndoableStringBuilder();

    // check the objects' footprint.
    @Test
    public void ObjectSize()
    {
        logger.info(()->JvmUtilities.objectFootprint(ga));
        ga.register(cm1);
        logger.info(()->JvmUtilities.objectFootprint(ga));
        ga.register(cm2);
        logger.info(()->JvmUtilities.objectFootprint(ga));
        logger.info(()->JvmUtilities.objectFootprint(cm1));
        logger.info(()->JvmUtilities.objectFootprint(cm1));
    }

    // Here begins the part of tests for GroupAdmin class.
    @Test
    void register()
    {
        logger.info(()->JvmUtilities.objectFootprint(ga));
        logger.info(()->JvmUtilities.objectTotalSize(ga));
        logger.info(()->JvmUtilities.objectTotalSize(cm1));
        // appending a string to the ga and check if register() works
        // and check the changes in the objectFootprint.
        ga.append("evyatar");
        ga.register(cm1);
        assertEquals(ga.observers.get(0),cm1,"register failed");
        ga.register(cm2);
        assertEquals(ga.observers.get(1),cm2,"register failed");

        logger.info(()->JvmUtilities.objectFootprint(ga));
        logger.info(()->JvmUtilities.objectTotalSize(ga));
        logger.info(()->JvmUtilities.objectTotalSize(cm1));
        logger.info(() -> JvmUtilities.jvmInfo());

    }

    @Test
    void unregister()
    {
        logger.info(()->JvmUtilities.objectTotalSize(ga));

        // appending a string to the ga and  registers members and check if unregister() works
        // and check the changes in the objectFootprint.
        ga.append("evyatar");
        ga.register(cm1);
        ga.register(cm2);

        logger.info(()->JvmUtilities.objectTotalSize(ga));

        ga.unregister(cm1);
        assertFalse(ga.observers.contains(cm1),"failed");
        ga.unregister(cm2);
        assertFalse(ga.observers.contains(cm2),"failed");
        ga.undo();

        logger.info(()->JvmUtilities.objectTotalSize(ga));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    void insert()
    {
        logger.info(()->JvmUtilities.objectTotalSize(ga));
        logger.info(()->JvmUtilities.objectTotalSize(cm1));
        // appending a string to the ga and  registers members and check if insert() works
        // and check the changes in the objectFootprint.
        ga.append("foot");
        ga.insert(4,"ball");
        assertEquals("football",ga.toString(),"test failed");
        ga.register(cm1);

        logger.info(()->JvmUtilities.objectTotalSize(cm1));

        assertEquals(ga.observers.get(0).toString(),ga.toString(),"test failed");
        ga.insert(50,"xxx");
        assertEquals("football",ga.toString(),"test failed");

        logger.info(()->JvmUtilities.objectTotalSize(ga));
        logger.info(() -> JvmUtilities.jvmInfo());

    }

    @Test
    void append()
    {
        logger.info(()->JvmUtilities.objectTotalSize(ga));
        logger.info(()->JvmUtilities.objectTotalSize(cm1));
        logger.info(()->JvmUtilities.objectTotalSize(cm2));

        // appending a string to the ga and  registers members and check if append() works
        // and check the changes in the objectFootprint.
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

        logger.info(()->JvmUtilities.objectTotalSize(ga));
        logger.info(()->JvmUtilities.objectTotalSize(cm1));
        logger.info(()->JvmUtilities.objectTotalSize(cm2));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    void delete()
    {
        logger.info(()->JvmUtilities.objectTotalSize(ga));

        ga.register(cm1);
        ga.append("evyatar is very");
        ga.delete(0,3);
        assertEquals("atar is very", ga.toString(),"test failed");
        assertEquals(ga.observers.get(0).toString(),ga.toString(),"test failed");

        // appending a string to the ga and  registers members and check if delete() works
        // and check the changes in the objectFootprint.
        ga.delete(8,12);
        ga.register(cm2);
        assertEquals("atar is ", ga.toString(),"test failed");
        assertEquals(ga.observers.get(1).toString(),ga.toString(),"test failed");
        ga.delete(-1,50);
        assertEquals("atar is ", ga.toString(),"test failed");
        ga.delete(0,8);
        assertEquals("", ga.toString(),"test failed");

        logger.info(()->JvmUtilities.objectTotalSize(ga));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    void undo()
    {
        logger.info(() -> JvmUtilities.jvmInfo());
        logger.info(()->JvmUtilities.objectTotalSize(ga));

        // appending a string to the ga and registers members and check if undo() works
        // and check the changes in the objectFootprint.
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

        logger.info(()->JvmUtilities.objectTotalSize(ga));
        logger.info(() -> JvmUtilities.jvmInfo());
    }
    //Here begins the part of tests for ConcreteMember class.
    @Test
    void update()
    {
        logger.info(()->JvmUtilities.objectTotalSize(ustb));

        // check if update() method works and the changes in the ustb objectTotalSize.

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

        logger.info(()->JvmUtilities.objectTotalSize(ustb));
        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @Test
    void testToString()
    {
        ustb.append("to string() work well");
        cm1.update(ustb);
        cm1.toString();
        assertEquals("to string() work well",ustb.toString(),"to string failed");
    }
}
