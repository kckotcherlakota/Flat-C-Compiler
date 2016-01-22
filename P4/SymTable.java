///////////////////////////////////////////////////////////
// Title: 				Programming Assignment 3
// Main Class File: 	P4.java
// Files: 				SymTable.java
// Semester: 			CS 536 Fall 2015
//
// Author: 				Yuting Liu
// Email:				liu487@wisc.edu
// CS Login: 			yuting
// Lecturer: 			Aws Albarghouthi
//////////////////////////////////////////////////////////

/**
 * This class classify the symbol table implemented in YES
 */
import java.util.*;

public class SymTable {
    private List<HashMap<String, SemSym>> list;
    
    public SymTable() {
        list = new LinkedList<HashMap<String, SemSym>>();
        list.add(new HashMap<String, SemSym>());
    }
    
    public void addDecl(String name, SemSym sym) 
    throws DuplicateSymException, EmptySymTableException {
        if (name == null || sym == null)
            throw new NullPointerException();
        
        if (list.isEmpty())
            throw new EmptySymTableException();
        
        HashMap<String, SemSym> symTab = list.get(0);
        if (symTab.containsKey(name))
            throw new DuplicateSymException();
        
        symTab.put(name, sym);
    }
    
    public void removeDecl(String name) 
    throws EmptySymTableException {
		if(list.isEmpty() || list.get(0).isEmpty())
			throw new EmptySymTableException();
			
		HashMap<String, SemSym> symTab = list.get(0);
		symTab.remove(name);
	}
	
    public void addScope() {
        list.add(0, new HashMap<String, SemSym>());
    }
    
    public SemSym lookupLocal(String name) {
        if (list.isEmpty())
            return null;
        
        HashMap<String, SemSym> symTab = list.get(0); 
        return symTab.get(name);
    }
    
    public SemSym lookupGlobal(String name) {
        if (list.isEmpty())
            return null;
        
        for (HashMap<String, SemSym> symTab : list) {
            SemSym sym = symTab.get(name);
            if (sym != null)
                return sym;
        }
        return null;
    }
    
    public void removeScope() throws EmptySymTableException {
        if (list.isEmpty())
            throw new EmptySymTableException();
        list.remove(0);
    }
    
    public void print() {
        System.out.print("\nSym Table\n");
        for (HashMap<String, SemSym> symTab : list) {
            System.out.println(symTab.toString());
        }
        System.out.println();
    }
    
    /* 
     * Add the whole list to the 1st hashtable
     */ 
    public void addList(HashMap<String, SemSym> l) {
		list.remove(0);
		list.add(l);
	}
	
	/*
	 * Get the current scope
	 */
    public SymTable getScope()
    throws EmptySymTableException {
		if (list.isEmpty())
            throw new EmptySymTableException();
            
		SymTable table = new SymTable();
		table.addList(list.get(0));
		return table;
	}
}
