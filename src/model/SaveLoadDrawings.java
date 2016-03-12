package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SaveLoadDrawings {

	synchronized protected static void saveToFile(List<Shape> list,
			String toPath) {

		FileOutputStream fOut = null;
		ObjectOutputStream oOut = null;

		try {
			fOut = new FileOutputStream(toPath);
			oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(list);
			oOut.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				oOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	synchronized protected static List<Shape> loadFromFile(String fromPath)	throws FileNotFoundException {

		// throws FileNotFoundException
		FileInputStream fileInputStream = new FileInputStream(fromPath);
		
		ObjectInputStream input = null;
		List<Shape> shapes = null;

		try {
			input = new ObjectInputStream(fileInputStream);
			shapes = (List<Shape>) input.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return shapes;

	}

}
