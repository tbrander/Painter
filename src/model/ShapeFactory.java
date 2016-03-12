package model;

import java.awt.Color;
import java.util.HashMap;

import model.EnumShapes;

public class ShapeFactory {

	HashMap<EnumShapes, Shape> shapeMap;

	public ShapeFactory() {
		shapeMap = new HashMap<>();

		for (int i = 0; i < EnumShapes.values().length; i++) {
			shapeMap.put(EnumShapes.values()[i],
					EnumShapes.values()[i].getEnumShape());
		}

	}

	public Shape makeShape(EnumShapes s, double x1, double y1, double x2,
			double y2, Color color, int lineThickness, boolean isFilled) {

		Shape newShape = shapeMap.get(s).clone();
		newShape.setNewShapeAttributes(x1, y1, x2, y2, color, lineThickness,
				isFilled);
		newShape.defineDimensions();
		return newShape;
	}

}
