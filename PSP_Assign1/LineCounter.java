//LineCounter by Aaron Chamberlain on 2-5-16
//Subtractively counts the logical lines of code, i.e. it count the exact lines of 
//of the input, then subtracts the blank space and the comments.
import java.io.File;
import java.util.Scanner;

class Counter {
	public int fileLines = 0;
	public int blankLines = 0;
	public int commentLines = 0;
	public int logicLines = 0;

	//count the total lines in the file
	public int countLines(String file){
		int totalLineCount = 0;
		try {
			File input = new File(file);
			Scanner fileInput = new Scanner(input);
			while(fileInput.hasNext()){
				totalLineCount++;
				fileInput.nextLine();
			}
			fileInput.close();
			System.out.println("There are " + totalLineCount + " lines in this file.");
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return totalLineCount;
	}	

	//count the number of blank lines
	public int countBlanks(String file){
		int blankLineCount = 0;
		try {
			File input = new File(file);
			Scanner fileInput = new Scanner(input);
			while(fileInput.hasNext()){
				String s = fileInput.nextLine();
				if(s.length() == 0){
					blankLineCount++;
				}
			}
			fileInput.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
		System.out.println("There are " + blankLineCount + " blank lines in this file.");
		return blankLineCount;
	}

	//count the number of comments
	public int countComments(String file){
		int commentLineCount = 0;
		try {
			File input = new File(file);
			Scanner fileInput = new Scanner(input);
			while(fileInput.hasNext()){
				String s = fileInput.nextLine();
				if(s.contains("//") && !s.contains("'//'")){
					commentLineCount++;
				}
			}
			fileInput.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
		System.out.println("There are " + commentLineCount + " comments in this file.");
		return commentLineCount;
	}

	//calculate how many lines are actually logic
	public int calcLogicLines(int lines, int bloc, int cloc){
		int logicalLines = 0;
		logicalLines = lines - (bloc + cloc);

		System.out.println("There are " + logicalLines + " logical lines in this file.");
		return logicalLines;		
	}
}

public class LineCounter {

	static public void main(String[] args){
		Counter input1 = new Counter();
		int totalLines = input1.countLines("LineCounter.java");
		int blankLines = input1.countBlanks("LineCounter.java");
		int commentLines = input1.countComments("LineCounter.java");
		int eloc = input1.calcLogicLines(totalLines, blankLines, commentLines);
	}
}