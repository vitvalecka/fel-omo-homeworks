//package homework2;

import java.util.HashSet;
import java.util.Set;

public class Homework2 {}

interface OMOSetView {
    boolean contains(int element); // testuje na přítomnost prvku v množině
 
    int[] toArray(); //vrátí kopii prvků množiny v poli (na pořadí prvků nezáleží)
 
    OMOSetView copy(); //vrátí kopii množiny
}


//------------------------------------------------------------------------------


// třída reprezentující obecnou množinu, definuje metody add/remove pro přidávání/odebírání prvků
class OMOSet implements OMOSetView {
    private final Set<Integer> set = new HashSet();
    
    public void add(int element) {
    //přidá prvek "element" do množiny
        set.add(element);
    }
 
    public void remove(int element) {
    //odebere prvek "element" z množiny
        set.remove(element);
    }
    
    // metody rozhraní OMOSetView
    @Override
    public boolean contains(int element) {
        return set.contains(element);
    }
    
    @Override
    public int[] toArray() {
        int[] foo = new int[set.size()];
        int i = 0;
        for (int num: set) {
            foo[i] = num;
            i++;
        }
        return foo;
    }
    
    @Override
    public OMOSetView copy() {
        OMOSet newSet = new OMOSet();
        for (int num: set) {
            newSet.add(num);
        }
        return newSet;
    }
    
}


//------------------------------------------------------------------------------


// třída reprezentující sjednocení dvou množin: A sjednoceno B
class OMOSetUnion implements OMOSetView {
    private OMOSetView set1 = new OMOSet();
    private OMOSetView set2 = new OMOSet();
    
    OMOSetUnion(OMOSetView setA, OMOSetView setB) {
        set1 = setA;
        set2 = setB;
    }
 
    // metody rozhraní OMOSetView
    @Override
    public boolean contains(int element) {
        return set1.contains(element) || set2.contains(element);
    }
    
    @Override
    public int[] toArray() {
        OMOSet newSet = new OMOSet();
        for (int num: set1.toArray()) {
            newSet.add(num);
        }
        for (int num: set2.toArray()) {
            newSet.add(num);
        }
        return newSet.toArray();
    }
    
    @Override
    public OMOSetView copy() {
        return null;
    }
}


//------------------------------------------------------------------------------


// třída reprezentující průnik dvou množin: A průnik B
class OMOSetIntersection implements OMOSetView {
    private OMOSetView set1 = new OMOSet();
    private OMOSetView set2 = new OMOSet();
    
    OMOSetIntersection(OMOSetView setA, OMOSetView setB) {
        set1 = setA;
        set2 = setB;
    }
 
    // metody rozhraní OMOSetView
    @Override
    public boolean contains(int element) {
        return set1.contains(element) && set2.contains(element);
    }
    
    @Override
    public int[] toArray() {
        return this.copy().toArray();
    }
    
    @Override
    public OMOSetView copy() {
        OMOSet foo = new OMOSet();
        int[] array = set1.toArray();
        for (int num: array) {
            if (set2.contains(num)) {
                foo.add(num);
            }
        }
        return foo;
    }
    
}


//------------------------------------------------------------------------------


// třída reprezentující A\B: doplněk množiny B vzhledem k množině A:  A\B = { x | x∈A ∧ x∉B }
class OMOSetComplement implements OMOSetView {
    private OMOSetView set1 = new OMOSet();
    private OMOSetView set2 = new OMOSet();
    
    OMOSetComplement(OMOSetView setA, OMOSetView setB) {
        set1 = setA;
        set2 = setB;
    }
 
    // metody rozhraní OMOSetView
    @Override
    public boolean contains(int element) {
        return set1.contains(element) && !set2.contains(element);
    }
    
    @Override
    public int[] toArray() {
        return this.copy().toArray();
    }
    
    @Override
    public OMOSetView copy() {
        OMOSet foo = new OMOSet();
        int[] array = set1.toArray();
        for (int num: array) {
            if (!set2.contains(num)) {
                foo.add(num);
            }
        }
        return foo;
    }
}


//------------------------------------------------------------------------------


// třída reprezentující množinu sudých čísel 
class OMOSetEven implements OMOSetView {
    private OMOSetView set = new OMOSet();
    
    OMOSetEven(OMOSetView setA) {
        set = setA;
    }
    
    // metody rozhraní OMOSetView
    @Override
    public boolean contains(int element) {
        if (element % 2 == 0) {
            return set.contains(element);
        } else {
            return false;
        }
    }
    
    @Override
    public int[] toArray() {
        return this.copy().toArray();
    }
    
    @Override
    public OMOSetView copy() {
        OMOSet foo = new OMOSet();
        int[] array = set.toArray();
        for (int num: array) {
            if (num % 2 == 0) {
                foo.add(num);
            }
        }
        return foo;
    }
}