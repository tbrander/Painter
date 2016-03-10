package model;

import java.util.Stack;

public class Undo {
	
	private Stack<UndoInterface> undoStack;
	
	
	public Undo(){
		undoStack = new Stack<>();
	}

	protected void addToHistory(Shape s, int indexOfselectedShape) {
	
		undoStack.push(new UndoObject(s, indexOfselectedShape)); 
		System.out.println("stack: "+undoStack.size());
		
	}
	
	protected UndoInterface undoChange(){
		
		return undoStack.pop();
	}
	
}
