package model;

public enum EnumShapes {
	CIRCLE, SQUARE, RECTANGLE, TRIANGLE, LINE, ARC;

	private Shape shape;

	static {
		CIRCLE.shape = new Circle();
		ARC.shape = new Arc();
		RECTANGLE.shape = new Rectangle();
		LINE.shape = new Line();
		SQUARE.shape = new Square();
		TRIANGLE.shape = new Triangle();
	}

	public Shape getEnumShape() {
		return shape;
	}

}
