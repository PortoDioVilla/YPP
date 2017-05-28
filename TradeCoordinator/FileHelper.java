package warehouse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHelper is a class responsible for reading and writing files. Note: The
 * two methods of reading and writing are both static; no instances of
 * FileHelper should be created.
 */
public class FileHelper {

	

	/**
	 * Reads file and returns a list of strings, where each item in the list
	 * represents one line in the file. Returns an empty list if an IOException
	 * error is raised.
	 * 
	 * @param filename
	 *            the name of the file to read
	 * @return a list lines from the file
	 */
	public static ArrayList<String> readFile(String filename) {
		// change filename to include full path
		filename = new File(WarehouseSim.FILEFOLDER + File.separatorChar + filename).getAbsolutePath();
		ArrayList<String> lines = new ArrayList<String>();

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(filename);
			bufferedReader = new BufferedReader(fileReader);

			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				currentLine.trim();
				lines.add(currentLine);
			}
		} catch (IOException ioe) {
			// ERROR
			lines.clear();
			System.out.println(ioe.getMessage());
		} finally {
			try {
				if (fileReader != null)
					fileReader.close();
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException ioe) {
				// ERROR
				lines.clear();
				System.out.println(ioe.getMessage());
			}
		}
		return lines;
	}

	/**
	 * Writes the list of lines into a file, and returns true if successful.
	 * 
	 * If file does not exist, creates a new file. If it does exist, overwrites
	 * the contents.
	 * 
	 * @param lines
	 *            list of lines to write to file
	 * @param filename
	 *            name of the file
	 * @return true if successfully completed
	 */
	public static boolean writeFile(List<String> lines, String filename) {
		// change filename to include path
		filename = new File(WarehouseSim.FILEFOLDER + File.separatorChar + filename).getAbsolutePath();
		boolean success = false;

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(filename);
			bufferedWriter = new BufferedWriter(fileWriter);

			for (String line : lines) {
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
			fileWriter.flush();
			bufferedWriter.flush();
		} catch (IOException ioe) {
			// ERROR
			System.out.println(ioe.getMessage());
		} finally {
			try {
				if (fileWriter != null)
					fileWriter.close();
				if (bufferedWriter != null)
					bufferedWriter.close();
				success = true;
			} catch (IOException ioe) {
				// ERROR
				System.out.println(ioe.getMessage());
			}
		}
		return success;
	}

	public static boolean addLine(String filename, String addedline) {
		filename = new File(WarehouseSim.FILEFOLDER  + File.separatorChar + filename).getAbsolutePath();
		boolean success = false;

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			fileWriter = new FileWriter(filename, true);
			bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(addedline);
			bufferedWriter.newLine();
			fileWriter.flush();
			bufferedWriter.flush();
		} catch (IOException ioe) {
			// ERROR
			System.out.println(ioe.getMessage());
		} finally {
			try {
				if (fileWriter != null)
					fileWriter.close();
				if (bufferedWriter != null)
					bufferedWriter.close();
				success = true;
			} catch (IOException ioe) {
				// ERROR
				System.out.println(ioe.getMessage());
			}
		}
		return success;
	}

	public static String locate(String traversalFile, String SKU) {
		ArrayList<String> travList = FileHelper.readFile(traversalFile);
		travList.remove(0);
		for (String locVolPair : travList) {
			// e.g. line = "A,0,1,2,25"
			String[] lineParts = locVolPair.split(",");
			// e.g. lineParts = [A, 0, 1, 2, 25]

			StringBuilder sb = new StringBuilder();
			for (String part : lineParts) {
				sb.append(part);
			}
			String line = sb.toString();
			// e.g. line = "A01225"
			String location = line.substring(0, 4);
			// e.g. location = "A012"
			String tempSKU = line.substring(4);
			if (tempSKU.equals(SKU)) {
				return location;
			} 
				
			
		}
		return "MIA";
	}
}
