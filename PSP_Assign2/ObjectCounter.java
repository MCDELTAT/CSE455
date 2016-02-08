//LineCounter by Aaron Chamberlain on 2-5-16
//Subtractively counts the logical lines of code, i.e. it count the exact lines of 
//of the input, then subtracts the blank space and the comments.
import java.io.File;
import java.util.Scanner;
import java.lang.reflect.Method;

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

	//for now, simply outputs which line the class started on and ended on
	public void objectDetector(String file){
		int curLine = 0;
		int classStartLine = 0;
		int classEndLine = 0;
		int classCurlyCounter = 0;
		int class1Lines = 0;
		int class2Lines = 0;
		int iter = 0;
		boolean isClass = false;
		try {
			File input = new File(file);
			Scanner fileInput = new Scanner(input);
			while(fileInput.hasNext()){
				String s = fileInput.nextLine();
				curLine++;
				if(s.contains("{")){
					classCurlyCounter++;
					//System.out.println("LEFT  brace was found on line: "+curLine+" curls: " + classCurlyCounter);
				}
				if(s.contains("}") && !s.contains("s.contains")){
					classCurlyCounter--;
					//System.out.println("RIGHT brace was found on line: "+curLine+" curls: " + classCurlyCounter);
				}
				if(s.contains("class ") && !s.contains("//")){
					classStartLine = curLine;
					System.out.println("The Class starts on line: " + classStartLine);
					classCurlyCounter = 1;
					isClass = true;

				}
				//we have reached the end of the class
				if(classCurlyCounter == 0 && isClass){
					classEndLine = curLine;
					System.out.println("The Class ends on the line: " + classEndLine);
					isClass = false;
					if(iter == 0){
						class1Lines = (classEndLine-classStartLine);
						iter++;
					}
					if (iter == 1){
						class2Lines = (classEndLine-classStartLine);
					}	
				}
			}
			fileInput.close();
		} catch (Exception ex){
			ex.printStackTrace();
		}
		Counter methods = new Counter();
		Class count = methods.getClass();
		Method[] listMethods = count.getDeclaredMethods();
		System.out.println("Class lines: " + (class1Lines) + ", methods: " + listMethods.length);

		ObjectCounter methods1 = new ObjectCounter();
		Class count1 = methods1.getClass();
		Method[] listMethods1 = count1.getDeclaredMethods();
		System.out.println("Class lines: " + (class2Lines) + ", methods: " + listMethods1.length);
	}
}

public class ObjectCounter {

	static public void main(String[] args){
		Counter input1 = new Counter();
		int totalLines = input1.countLines("ObjectCounter.java");
		int blankLines = input1.countBlanks("ObjectCounter.java");
		int commentLines = input1.countComments("ObjectCounter.java");
		int eloc = input1.calcLogicLines(totalLines, blankLines, commentLines);
		input1.objectDetector("ObjectCounter.java");
	}
}