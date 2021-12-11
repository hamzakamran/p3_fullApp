/**

 -- AUTHORS --
 + Hamza Kamran
 + Adam Gibbons
 + Kimmy Thach

 -- DESCRIPTION: --
 This is a class that will extract all the comments and variable names to create a function description.

 -- FUNCTIONAL REQUIREMENT(S) MET: --
 + None

 -- NONFUNCTIONAL REQUIREMENT(S) MET: --
 + None

 -- USER INTERFACE REQUIREMENT(S) MET: --
 + None

 -- SOFTWARE INTERFACE REQUIREMENT(S) MET: --
 + None

 -- LOGICAL DATABASE REQUIREMENT(S) MET: --
 + None

 --------------------------------------

 The MIT License (MIT)

 Copyright (c) 2021 OpenFin

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 **/

package com.missouristate.csc450.socer.HelperClasses;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
//

public class CommentAndVariableExtractor {
	private String[] functionComments = new String[50];
	private String[] theFormattedCode = new String[5000];

	private ArrayList<ArrayList<String>> listOfFunctions = new ArrayList<ArrayList<String>>();
	private ArrayList<String> tempFunctionList = new ArrayList<String>();

	private ArrayList<String> variableNamesArray = new ArrayList<>();
	private ArrayList<String> commentWordsArray = new ArrayList<>();
	private ArrayList<String> allWordsArray = new ArrayList<>();

	private int functionIteration = 0;
	private boolean isInEnum = false;
	private int iSemicolonsNeeded = 0;
	private boolean bHasFunctionStarted = false;
	private boolean isBeforeFirstFunction = true;

	private boolean possibleOverride = false;
	private boolean isValidFunction;
	private boolean hasFirstFunctionStarted;
	private String errorMessage= "";

	public CommentAndVariableExtractor(String fileName) {
		try {

			File file = new File(fileName);
			Scanner sc = new Scanner(file); // file to be scanned
			sc = new Scanner(file);
			isValidFunction = isFunctionValid(sc);

			//System.out.println("Is the file's functions valid? " + (isValidFunction ? "Yes" : "No"));
			sc.close();


		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public boolean isLetter(char x) {
		boolean bRetV = false;
		if (x == 'a' || x == 'b' || x == 'c' || x == 'd' || x == 'e' || x == 'f' || x == 'g' || x == 'h' || x == 'i'
				|| x == 'j' || x == 'k' || x == 'l' || x == 'm' || x == 'n' || x == 'o' || x == 'p' || x == 'q'
				|| x == 'r' || x == 's' || x == 't' || x == 'u' || x == 'v' || x == 'w' || x == 'x' || x == 'y'
				|| x == 'z')
			bRetV = true;
		if (x == 'A' || x == 'B' || x == 'C' || x == 'D' || x == 'E' || x == 'F' || x == 'G' || x == 'H' || x == 'I'
				|| x == 'J' || x == 'K' || x == 'L' || x == 'M' || x == 'N' || x == 'O' || x == 'P' || x == 'Q'
				|| x == 'R' || x == 'S' || x == 'T' || x == 'U' || x == 'V' || x == 'W' || x == 'X' || x == 'Y'
				|| x == 'Z')
			bRetV = true;

		return bRetV;
	}


	public boolean isFunctionValid(Scanner bigCode) {
		int iLineNumber = 0;

		boolean bRetV = true;
		boolean bPreviousWasForwardSlash = false;
		boolean bPreviousWasBackSlash = false;
		boolean bIsSingleLineComment = false;
		boolean bIsMultiLineComment = false;
		boolean bPreviousWasStar = false;
		boolean bIsInString = false;
		boolean bIsInChar = false;
		int iNumOpenParenthesis = 0;
		int iNumOpenBraces = 0;

		String sLineText = "";
		String sBuffer = "";
		String sGiantString = "";
		String sCurrentWord = "";

		while (bigCode.hasNextLine()) {

			iLineNumber++;
			bIsSingleLineComment = false;
			sLineText = "";
			String sNextLine = bigCode.nextLine();

			// System.out.println(sNextLine);
			for (int i = 0; i < sNextLine.length(); i++) {

				// if the next character is a / and we are not in a string or character
				if (sNextLine.charAt(i) == '/' && !bIsInString && !bIsInChar) {
					if (bPreviousWasForwardSlash) {
						bIsSingleLineComment = true;
					} else if (bPreviousWasStar) {
						bIsMultiLineComment = false;
					}
					bPreviousWasStar = false;
					bPreviousWasForwardSlash = true;
					bPreviousWasBackSlash = false;
					sLineText += ' ';

				}

				// if the next char is a letter
				else if (isLetter(sNextLine.charAt(i))) {
					sBuffer += sNextLine.charAt(i);
					sLineText += sNextLine.charAt(i);
					bPreviousWasStar = false;
					bPreviousWasForwardSlash = false;
					bPreviousWasBackSlash = false;

				}

				// if the next life is a star
				else if (sNextLine.charAt(i) == '*' && !bIsInString && !bIsInChar) {
					if (bPreviousWasForwardSlash) {
						bIsMultiLineComment = true;
					}
					bPreviousWasStar = true;
					bPreviousWasForwardSlash = false;
					bPreviousWasBackSlash = false;
					sLineText += ' ';

				} else if (sNextLine.charAt(i) == '"' && !bIsMultiLineComment && !bIsSingleLineComment) {
					if (bIsInString && !bPreviousWasBackSlash) {
						// System.out.println("you get here");
						bIsInString = false;
					} else {
						// System.out.println("here you are");
						bIsInString = true;
					}
					bPreviousWasStar = false;
					bPreviousWasForwardSlash = false;
					bPreviousWasBackSlash = false;
					sLineText += ' ';

				} else if (sNextLine.charAt(i) == '\'' && !bIsMultiLineComment && !bIsSingleLineComment
						&& !bIsInString) {
					if (bIsInChar && !bPreviousWasBackSlash) {
						bIsInChar = false;
					} else {
						bIsInChar = true;
					}
					bPreviousWasStar = false;
					bPreviousWasForwardSlash = false;
					bPreviousWasBackSlash = false;
					sLineText += ' ';

				} else if (sNextLine.charAt(i) == '\\' && !bIsMultiLineComment && !bIsSingleLineComment) {
					if (bPreviousWasBackSlash)
						bPreviousWasBackSlash = false;
					else
						bPreviousWasBackSlash = true;
					bPreviousWasStar = false;
					bPreviousWasForwardSlash = false;
					sLineText += ' ';

				} else if (sNextLine.charAt(i) == '{' && !bIsMultiLineComment && !bIsSingleLineComment && !bIsInString
						&& !bIsInChar) {
					// System.out.println("Open");
					iNumOpenBraces++;
					bPreviousWasStar = false;
					bPreviousWasForwardSlash = false;
					bPreviousWasBackSlash = false;
					sLineText += ' ';

				} else if (sNextLine.charAt(i) == '}' && !bIsMultiLineComment && !bIsSingleLineComment && !bIsInString
						&& !bIsInChar) {
					// System.out.println("Closed");
					iNumOpenBraces--;

					bPreviousWasStar = false;
					bPreviousWasForwardSlash = false;
					bPreviousWasBackSlash = false;
					sLineText += ' ';

				} else if (sNextLine.charAt(i) == '(' && !bIsMultiLineComment && !bIsSingleLineComment && !bIsInString
						&& !bIsInChar) {
					iNumOpenParenthesis++;
					bPreviousWasStar = false;
					bPreviousWasBackSlash = false;
					bPreviousWasForwardSlash = false;
					sLineText += ' ';

				} else if (sNextLine.charAt(i) == ')' && !bIsMultiLineComment && !bIsSingleLineComment && !bIsInString
						&& !bIsInChar) {
					iNumOpenParenthesis--;
					bPreviousWasStar = false;
					bPreviousWasBackSlash = false;
					bPreviousWasForwardSlash = false;
					sLineText += ' ';

				} else if (sNextLine.charAt(i) == ';' && !bIsMultiLineComment && !bIsSingleLineComment && !bIsInString
						&& !bIsInChar) {
					bPreviousWasStar = false;
					bPreviousWasBackSlash = false;
					bPreviousWasForwardSlash = false;
					sLineText += ' ';

				} else if (sNextLine.charAt(i) == ' ') {
					if (sBuffer != "") {
						sBuffer += sNextLine.charAt(i);
						// System.out.println(sBuffer);
						sGiantString += sBuffer;
					}
					sLineText += ' ';
					sBuffer = "";

				}

				if(!bIsMultiLineComment && !bIsSingleLineComment && iNumOpenBraces>0)
				{
					if(isLetter(sNextLine.charAt(i)))
					{
						sCurrentWord += sNextLine.charAt(i);
					}
					else if(sCurrentWord.length()>1 && !isCppWord(sCurrentWord)){
						//System.out.println(sCurrentWord);
						if (!variableNamesArray.contains(sCurrentWord))
						{
							allWordsArray.add(sCurrentWord);
							variableNamesArray.add(sCurrentWord);
						}



						sCurrentWord = "";
					}
					else if(isCppWord(sCurrentWord) || sCurrentWord.length()<2)
					{
						sCurrentWord = "";
					}
				}
				else if (iNumOpenBraces>0){
					if(isLetter(sNextLine.charAt(i)))
					{
						sCurrentWord += sNextLine.charAt(i);
					}
					else if(sCurrentWord.length()>0){
						commentWordsArray.add(sCurrentWord);
						allWordsArray.add(sCurrentWord);
						//System.out.println(sCurrentWord);
						sCurrentWord = "";
					}
				}

				// what to do at the end of each line
				if (i == sNextLine.length() - 1) {
					if (sBuffer != "") {

						// System.out.println(sBuffer);
						sGiantString += (sBuffer + ' ');
					}

					sBuffer = "";
					sLineText = "";
					bIsSingleLineComment = false;
					}

			}
		}

		return bRetV;
	}

	boolean isCppWord(String x1)
	{
		boolean RetV = false;

		if (x1.equals("if") || x1.equals("include") || x1.equals("define") || x1.equals("else")
				|| x1.equals("switch") || x1.equals("while") || x1.equals("case") || x1.equals("template")
				|| x1.equals("explicit") || x1.equals("public") || x1.equals("protected") || x1.equals("private")
				|| x1.equals("namespace")|| x1.equals("default") || x1.equals("try") || x1.equals("catch")
				|| x1.equals("int")|| x1.equals("double") || x1.equals("long") || x1.equals("float") || x1.equals("short")
				|| x1.equals("char")|| x1.equals("for") || x1.equals("do") || x1.equals("Class") || x1.equals("new")
				|| x1.equals("not")|| x1.equals("or") || x1.equals("return") || x1.equals("virtual") || x1.equals("void")
				|| x1.equals("endl")|| x1.equals("null") || x1.equals("extern") || x1.equals("enum") || x1.equals("bool"))
		{
			RetV= true;
		}

		//System.out.println(x1+" " +RetV);

		return RetV;
	}

	public ArrayList<ArrayList<String>> getfunctionList()
	{
		return listOfFunctions;
	}

	public ArrayList<String> getVariableNamesArray() {
		return variableNamesArray;
	}

	public ArrayList<String> getCommentWordsArray() {
		return commentWordsArray;
	}

	public ArrayList<String> getAllWordsArray() {
		return allWordsArray;
	}
}