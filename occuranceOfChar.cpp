#include <string>int occurancesOfChar(std::string wordToCheck, char letterToCheck){    int iRetV = 0;    // itertes through the word    for (int i =0;i<wordToCheck.length();i++)    {        //returns adds to the counter everytime we see the character        if (wordToCheck[i] == letterToCheck)            iRetV++;    }    return iRetV;}int main(){    std::string word1;    word1 = "happy";    char letterToCheck = 'p';    bool testvar = occurancesOfChar(word1,letterToCheck);    printf("%d",testvar);    return 0;}