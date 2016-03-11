package model;

import java.util.Stack;

public class Undo {
	
	private Stack<UndoInterface> undoStack;
	
	public Undo(){
		undoStack = new Stack<>();
	}

	protected void addToHistory(Shape s, int indexOfselectedShape) {
	
		undoStack.push(new UndoObject(s, indexOfselectedShape)); 
		
	}
	
	protected UndoInterface undoChange(){
		System.out.println("Undostack: "+undoStack.size());
		
		UndoInterface u =undoStack.pop();
		
		return u;
	}
	

	
}
