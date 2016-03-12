package model;

class UndoObject implements UndoInterface {

	private Shape shape;
	private int index;

	public UndoObject(Shape shape, int index) {
		this.shape = shape;
		this.index = index;
	}

	@Override
	public Shape undo() {
		return shape;
	}

	@Override
	public int getIndex() {

		return index;
	}

}
