#include <iostream>#include <string>using namespace std;string reverseString(string str) {    //saves a copy of the string and the index we are on	string temp = str;	int index = 0;    //loops though the string	for (int x = temp.length()-1; x >= 0; x--)	{        //sets the value of the return to its inverse value		str[index] = temp[x];		index++;	}	return str;}int main(){    std::string word1;    word1 = "happy";    string testvar = reverseString(word1);    std::cout << testvar;    return 0;}