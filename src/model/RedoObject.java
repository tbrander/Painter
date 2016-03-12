package model;

class RedoObject implements RedoInterface {

	private Shape shape;
	private int index;

	public RedoObject(Shape shape, int index) {
		this.shape = shape;
		this.index = index;
	}

	@Override
	public Shape redo() {
		return shape;
	}

	@Override
	public int getIndex() {

		return index;
	}

}
