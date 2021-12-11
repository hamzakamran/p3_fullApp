/**

 -- AUTHORS --
 + Hamza Kamran
 + Adam Gibbons
 + Kimmy Thach

 -- DESCRIPTION: --
 This was the original way that we were validating files but it is no longer in use.

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
import java.util.Scanner;
//

public class Validater {
	private String[] functionComments = new String[50];
	private String[] theFormattedCode = new String[5000];
	private int functionIteration = 0;
	private boolean isInEnum = false;
	private int iSemicolonsNeeded = 0;
	private boolean bHasFunctionStarted = false;
	private boolean possibleOverride = false;
	private boolean isValidFunction;
	private String errorMessage= "";

	public Validater (String fileName) {
		try {

			File file = new File(fileName);
			Scanner sc = new Scanner(file); // file to be scanned
			formatCode(sc);
			sc = new Scanner(file);
			isValidFunction = isFunctionValid(sc);

			//System.out.println("Is the file's functions valid? " + (isValidFunction ? "Yes" : "No"));
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void formatCode(Scanner bigCode) {

		String sLineText = "";
		int index = 0;
		boolean isMultiLineComment = false;
		boolean isSingleLineComment = false;
		boolean lineStartsWithHash = false;
		boolean lastCharWasBS = false;
		boolean lastCharWasStar = false;
		boolean lastCharWasSemi = false;
		String firstWord = "";
		int numOfparen = 0;
		while (bigCode.hasNextLine()) {
			String sNextLine = bigCode.nextLine();
			firstWord = firstWord(sNextLine);
			// System.out.println(sNextLine);
			for (int i = 0; i < sNextLine.length(); i++) {
				if (sNextLine.charAt(0) == '#')
					lineStartsWithHash = true;
				else
					lineStartsWithHash = false;
				if (sNextLine.charAt(i) == '/') {
					sLineText += sNextLine.charAt(i);
					if (lastCharWasStar && isMultiLineComment) {
						isMultiLineComment = false;
						sLineText = "";
					}
					else if (lastCharWasBS)
					{
						isSingleLineComment = true;
						sLineText = "";
						lastCharWasBS = false;
						lastCharWasStar = false;
						lastCharWasSemi = false;
						break;
					}
					lastCharWasBS = true;
					lastCharWasStar = false;
					lastCharWasSemi = false;
				} else if (sNextLine.charAt(i) == '>') {
					sLineText += sNextLine.charAt(i);
					if (lineStartsWithHash || firstWord.equals("template")) {
						theFormattedCode[index] = sLineText;

						index++;
						sLineText = "";
					}
					lastCharWasStar = false;
					lastCharWasBS = false;
					lastCharWasSemi = false;
				}else if (sNextLine.charAt(i) == ':') {
					sLineText += sNextLine.charAt(i);
					if (firstWord.equals("case") || firstWord.equals("default")) {
						theFormattedCode[index] = sLineText;

						index++;
						sLineText = "";
					}
					lastCharWasStar = false;
					lastCharWasBS = false;
					lastCharWasSemi = false;
				} 
				else if (sNextLine.charAt(i) == '*') {
					sLineText += sNextLine.charAt(i);
					if (lastCharWasBS) {
						isMultiLineComment = true;
						sLineText = "";
					}
					sLineText += sNextLine.charAt(i);
					lastCharWasStar = true;
					lastCharWasBS = false;
					lastCharWasSemi = false;
				} else if (sNextLine.charAt(i) == ';') {
					sLineText += sNextLine.charAt(i);
					theFormattedCode[index] = sLineText;

					index++;
					sLineText = "";
					lastCharWasStar = false;
					lastCharWasBS = false;
					if (!lastCharWasSemi)
						iSemicolonsNeeded--;
					
					lastCharWasSemi = true;
					continue;

				} else if (sNextLine.charAt(i) == '{') {
					theFormattedCode[index] = sLineText;
					theFormattedCode[index + 1] = "{";
					index = index + 2;
					sLineText = "";
					lastCharWasStar = false;
					lastCharWasBS = false;
					lastCharWasSemi = false;
					continue;
				} else if (sNextLine.charAt(i) == '}') {
					theFormattedCode[index] = sLineText;
					theFormattedCode[index + 1] = "}";
					index = index + 2;
					sLineText = "";
					lastCharWasStar = false;
					lastCharWasBS = false;
					lastCharWasSemi = false;
					continue;
				} else if (sNextLine.charAt(i) == '(') {
					if (!isMultiLineComment)
						numOfparen++;
					sLineText += sNextLine.charAt(i);
					lastCharWasStar = false;
					lastCharWasBS = false;
					lastCharWasSemi = false;
				} else if (sNextLine.charAt(i) == ')') {
					if (!isMultiLineComment)
						numOfparen--;
					sLineText += sNextLine.charAt(i);
					lastCharWasStar = false;
					lastCharWasBS = false;
					lastCharWasSemi = false;
				} else {
					sLineText += sNextLine.charAt(i);
					lastCharWasStar = false;
					lastCharWasBS = false;
					lastCharWasSemi = false;
				}
				if (i == sNextLine.length() - 1) {
					if (isMultiLineComment) {
						sLineText = "";
					}
					if (isSingleLineComment) {
						sLineText = "";
					}
					if (numOfparen == 0 && sNextLine.charAt(i) != '=') {
						theFormattedCode[index] = sLineText;

						index++;
						sLineText = "";
					}
					lastCharWasStar = false;
					lastCharWasBS = false;
					isSingleLineComment = false;

				}
			}
		}
//		System.out.println(numOfparen);
//		System.out.println();
		for (int j = 0; j < theFormattedCode.length; j++) {
			if (theFormattedCode[j] != null) {
				iSemicolonsNeeded += (semicolonsNeededForKeyword(theFormattedCode[j]));
				//System.out.println(theFormattedCode[j]);
			}
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

	public int semicolonsNeededForKeyword(String x) {
		int iRetv = 1;
		int indexOfFirstLetter = 0;

		if (isFunctionDeclaration(x)) {
			return 0;
		}
		boolean previousWasLT = false;
		for (int j = 0; j < x.length(); j++) {
			if (x.charAt(j) == '~') {
				return 0;
			}
			if (isLetter(x.charAt(j))) {
				break;
			}
		}
		for (int j = 0; j < x.length(); j++) {
			if (x.charAt(j) == '<') {
				if (previousWasLT)
					return 0;
				previousWasLT = true;
			}
			if (isLetter(x.charAt(j))) {
				break;
			}
		}
		for (int j = 0; j < x.length(); j++) {
			if (x.charAt(j) == '}') {

				isInEnum = false;
			}
		}
		String x1 = "";
		for (int j = 0; j < x.length(); j++) {

			if (isLetter(x.charAt(j))) {
				indexOfFirstLetter = j;
				break;
			}
			if (j == x.length() - 1)
				iRetv = 0;
		}
		for (int j = 0; j < x.length(); j++) {
			if (isLetter(x.charAt(j))) {
				x1 += x.charAt(j);
			} else if (j > indexOfFirstLetter) {
				break;
			}
		}

		if (isInEnum) {
			return 0;
		}

		if (x1.equals("for")) {
			iRetv = 0;
		} else if (x1.equals("if") || x1.equals("include") || x1.equals("define") || x1.equals("else")
				|| x1.equals("switch") || x1.equals("while") || x1.equals("case") || x1.equals("template")
				|| x1.equals("explicit") || x1.equals("public") || x1.equals("protected") || x1.equals("private")
				|| x1.equals("namespace")|| x1.equals("default") || x1.equals("try") || x1.equals("catch") || x1.equals("")) {
			iRetv = 0;
		} else if (x1.equals("enum")) {
			isInEnum = true;
		}
		

//		System.out.println("\"" + x + "\"");
////		System.out.println(iRetv);
//	System.out.println("Total Semicolons Needed: " + iSemicolonsNeeded);
//		System.out.println();
//		System.out.println();
//		System.out.println();
		return iRetv;
	}
	public String firstWord(String x)
	{
		boolean wordStarted = false;
		String retV = "";
		for (int i = 0; i<x.length();i++)
		{
			if (isLetter(x.charAt(i)))
			{
				wordStarted = true;
				retV += x.charAt(i);
			}
			else if (wordStarted && !isLetter(x.charAt(i)))
			{
				return retV;
			}
			
			
		}
		return retV;
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

		while (bigCode.hasNextLine()) {

			iLineNumber++;
			bIsSingleLineComment = false;
			sLineText = "";
			String sNextLine = bigCode.nextLine();
			// System.out.println(sNextLine);
			for (int i = 0; i < sNextLine.length(); i++) {
				// System.out.println("LineNum: " + iLineNumber);
				// System.out.println("INumBraces: " + iNumOpenBraces);
//				if (iLineNumber == 174)
//				{
//					System.out.println(sNextLine);
//				}
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

	public String getErrorMessage(){
		return errorMessage;
	}
	public boolean isValidFunction() {
		return isValidFunction;
	}

	public void setValidFunction(boolean validFunction) {
		isValidFunction = validFunction;
	}
}