package epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Katerina on 01.02.2017.
 */
public class IntSetTest {
    @Test
    public void add() throws Exception {
        final IntSet set = new IntSet();
        assertFalse(set.contains(0));
        set.add(0);
        assertTrue(set.contains(0));
        assertFalse(set.contains(128));
        set.add(128);
        assertTrue(set.contains(128));
    }
    @Test
    public void contains() throws Exception {
        final IntSet set = new IntSet();
        for (int i = -1; i < 65 ; i++) {
            assertFalse(set.contains(i));
        }
        set.add(0);
        set.add(65);
        set.add(7);
        set.add(15);
        set.add(15);
        set.add(-1);
        for (int i = -1; i < set.getData().length ; i++) {
            if(i==0||i==65||i==7||i==15)
            assertTrue(set.contains(i));
        }
    }

    @Test
    public void remove() throws Exception {
        final IntSet set = new IntSet();
        assertFalse(set.contains(0));
        set.add(0);
        set.add(128);
        set.add(14);
        assertTrue(set.contains(14));
        assertTrue(set.contains(128));
        set.remove(14);
        assertFalse(set.contains(14));
    }

    @Test
    public void union() throws Exception {
        final IntSet set = new IntSet();
        final IntSet set1 = new IntSet();
        set.add(0);
        set.add(128);
        set.add(14);
        set1.add(25);
        assertFalse(set.contains(25));
        final IntSet union = set.union(set1);
        assertTrue(union.contains(14));
        assertTrue(union.contains(128));
        assertTrue(union.contains(25));
    }

    @Test
    public void intersection() throws Exception {
        final IntSet set = new IntSet();
        final IntSet set1 = new IntSet();
        set.add(0);
        set.add(128);
        set.add(14);
        set1.add(14);
        assertFalse(set.contains(25));
        final IntSet intersect = set.intersection(set1);
        assertTrue(intersect.contains(14));
        assertFalse(intersect.contains(128));
        assertFalse(intersect.contains(0));
    }

    @Test
    public void difference() throws Exception {
        final IntSet set = new IntSet();
        final IntSet set1 = new IntSet();
        set.add(0);
        set.add(128);
        set.add(14);
        set.add(25);
        set1.add(14);
        final IntSet diff = set.difference(set1);
        assertFalse(diff.contains(14));
        assertTrue(diff.contains(128));
        assertTrue(diff.contains(25));
    }

    @Test
    public void isSubsetOf() throws Exception {
        final IntSet set = new IntSet();
        final IntSet set1 = new IntSet();
        set.add(0);
        set.add(128);
        set.add(14);
        set.add(25);
        set1.add(0);
        set1.add(128);
        set1.add(14);
        set1.add(25);
        assertFalse(set1.isSubsetOf(set));
        assertTrue(set.isSubsetOf(set1));
    }
}