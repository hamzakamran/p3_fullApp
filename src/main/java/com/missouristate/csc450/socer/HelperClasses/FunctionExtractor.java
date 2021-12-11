/**

 -- AUTHORS --
 + Hamza Kamran
 + Adam Gibbons
 + Kimmy Thach

 -- DESCRIPTION: --
 This is the file that will extract all the C++ functions that are in a file.

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
import java.util.List;
import java.util.Scanner;
//

public class FunctionExtractor {
	private String[] functionComments = new String[50];
	private String[] theFormattedCode = new String[5000];

	private ArrayList<ArrayList<String>> listOfFunctions = new ArrayList<ArrayList<String>>();
	private ArrayList<String> tempFunctionList = new ArrayList<String>();

	private ArrayList<String> variableNamesArray = new ArrayList<>();
	private ArrayList<String> commentWordsArray = new ArrayList<>();

	private int functionIteration = 0;
	private boolean isInEnum = false;
	private int iSemicolonsNeeded = 0;
	private boolean bHasFunctionStarted = false;
	private boolean isBeforeFirstFunction = true;

	private boolean possibleOverride = false;
	private boolean isValidFunction;
	private boolean hasFirstFunctionStarted;
	private String errorMessage= "";

	public FunctionExtractor(String fileName) {
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
			if (!isBeforeFirstFunction && iNumOpenBraces != 0) {
				tempFunctionList.add(sNextLine);
			}
			else if (isBeforeFirstFunction && sNextLine.contains("(") && sNextLine.contains(")") && !bIsMultiLineComment)
			{
				tempFunctionList.add(sNextLine);
				isBeforeFirstFunction = false;
			}
			else if(!isBeforeFirstFunction && iNumOpenBraces==0 && sNextLine.contains("(") && sNextLine.contains(")") && !bIsMultiLineComment)
			{
				tempFunctionList.add(sNextLine);
			}
			else if(sNextLine.contains("{")  && !bIsMultiLineComment)
			{
				tempFunctionList.add(sNextLine);
			}
			// System.out.println(sNextLine);
			for (int i = 0; i < sNextLine.length(); i++) {
				// System.out.println("LineNum: " + iLineNumber);
				// System.out.println("INumBraces: " + iNumOpenBraces);


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
					if (iNumOpenBraces == 0)
					{
						if(tempFunctionList.get(0).length() > 3) {
							listOfFunctions.add(tempFunctionList);
						}

						for (String string: tempFunctionList) {
							//System.out.println(string);

						}

						tempFunctionList = new ArrayList<String>();
					}
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
					else if(sCurrentWord.length()>0){
						variableNamesArray.add(sCurrentWord);
						sCurrentWord = "";
					}
				}
				else if(iNumOpenBraces>0){
					if(isLetter(sNextLine.charAt(i)))
					{
						sCurrentWord += sNextLine.charAt(i);
					}
					else if(sCurrentWord.length()>0){
						commentWordsArray.add(sCurrentWord);
						System.out.println(sCurrentWord);
						sCurrentWord = "";
					}
				}

				if (iNumOpenBraces == 0) {
					if (bHasFunctionStarted) {
						functionComments[functionIteration] = sGiantString;
						functionIteration++;

						sGiantString = "";

					}
					bHasFunctionStarted = false;
				}

				// what to do at the end of each line
				if (i == sNextLine.length() - 1) {
					if (sBuffer != "") {

						// System.out.println(sBuffer);
						sGiantString += (sBuffer + ' ');
					}
					if (!bIsMultiLineComment && !bIsSingleLineComment && !bIsInString && !bIsInChar) {
						// System.out.println("Before: " + iSemicolonsNeeded);
						// System.out.println(sLineText);
						// iSemicolonsNeeded += semicolonsNeededForKeyword(sLineText);
						// System.out.println("After: " + iSemicolonsNeeded);
					}


					sBuffer = "";
					sLineText = "";
					bIsSingleLineComment = false;


				}

			}
		}
		if ((iNumOpenParenthesis) != 0) {
			bRetV = false;
			errorMessage = "We suspect a missing parenthesis";
			// System.out.println(iNumOpenBraces);
			// System.out.println(iNumOpenParenthesis);
		}
		if (iNumOpenBraces != 0)
		{
			bRetV = false;
			errorMessage = "We suspect a missing curly brace";
		}
		if (iSemicolonsNeeded > 0) {
			//System.out.println(iSemicolonsNeeded);
			errorMessage = "We suspect a missing semicolon";
			bRetV = false;
		}
		for (int j = 0; j < functionComments.length; j++) {
			if (functionComments[j] != null) {
				// System.out.println("Function " + (j+1) +" Description: ");
				// System.out.println(functionComments[j]);
			}
		}
		return bRetV;
	}

	boolean isFunctionDeclaration(String x) {
		String firstWord = "";
		String secondWord = "";
		boolean firstWordSet = false;
		boolean openP = false;
		boolean closeP = false;
		boolean wordHasStarted = false;

		String temp = "";
		for (int i = 0; i < x.length(); i++) {
			if (isLetter(x.charAt(i))) {
				temp += x.charAt(i);
				wordHasStarted = true;
			} else if (wordHasStarted && firstWordSet == false && !(isLetter(x.charAt(i)))) {
				firstWord = temp;
				firstWordSet = true;
				wordHasStarted = false;
			} 
			if (x.charAt(i) == '(') {
				openP = true;
			} else if (x.charAt(i) == ')') {
				closeP = true;
			} else if (x.charAt(i) == '=') {
				return false;
			} else if (x.charAt(i) == ';') {
				return false;
			}
			
		}
		if (openP && closeP)
			return true;
		else
			return false;
	}

	public ArrayList<ArrayList<String>> getfunctionList()
	{
		return listOfFunctions;
	}
}