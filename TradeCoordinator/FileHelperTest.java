package warehouse;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FileHelperTest {

	private static final String TRAVERSALTABLE = "testTraversalTable.csv";

	@Test
	public void testReadWrite() {
		String filename = "testWrite.txt";

		ArrayList<String> linestoWrite = new ArrayList<String>();

		String quotetoWrite = "First Second Third Fourth FifthAndLast";

		for (String word : quotetoWrite.split(" ")) {
			linestoWrite.add(word);
		}

		assertTrue(FileHelper.writeFile(linestoWrite, filename));
		ArrayList<String> linesRead = FileHelper.readFile(filename);

		assertEquals(linestoWrite.size(), linesRead.size());
		assertEquals(5, linesRead.size());
		for (int i = 0; i < linestoWrite.size(); i++) {
			assertEquals(linestoWrite.get(i), linesRead.get(i));
			System.out.println(linestoWrite.get(i));
		}

		// TODO: delet file
	}

	@Test
	public void testReadsTravTable() {
		ArrayList<String> traversalList = FileHelper.readFile(FileHelperTest.TRAVERSALTABLE);
		assertFalse(traversalList == null);
		assertEquals(9, traversalList.size());
	}

}
