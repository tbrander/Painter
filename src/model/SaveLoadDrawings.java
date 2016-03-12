package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SaveLoadDrawings {


	synchronized protected static void saveToFile(List<Shape> list, String toPath) {

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
	synchronized protected static List<Shape> readFromFile(String fromPath) throws IOException {

		FileInputStream fileInputStream = new FileInputStream(fromPath);
		ObjectInputStream input = new ObjectInputStream(fileInputStream);
		List<Shape> shapes = null;
		try {
			shapes = (List<Shape>) input.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			input.close();
		}
		

		return shapes;

	}

}
