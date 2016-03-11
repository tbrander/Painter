package model;

import java.util.Stack;

public class Redo {
	
	private Stack<RedoInterface> redoStack;
	
	
	public Redo(){
		redoStack = new Stack<>();
	}

	protected void addToHistory(Shape s, int indexOfselectedShape) {
	
		redoStack.push(new RedoObject(s, indexOfselectedShape)); 
		
	}
	
	protected RedoInterface redoChange(){
		
		return redoStack.pop();
	}
	
}
