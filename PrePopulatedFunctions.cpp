#include <bits/stdc++.h>
#include <cmath>
#include <math.h>
#include <stdio.h>
#include <string.h>
#include <time.h>

using namespace std;

#define MAX 100

void absoluteValueOfThree() {
  float a, b, c;
  printf("\nEnter a: ");
  scanf("%f", & a);

  printf("\nEnter b: ");
  scanf("%f", & b);

  printf("\nEnter c: ");
  scanf("%f", & c);

  if (a < 0) {
    a = -a;
  }
  if (b < 0) {
    b = -b;
  }
  if (c < 0) {
    c = -c;
  }

  printf("\na = %f", a);
  printf("\nb = %f", b);
  printf("\nc = %f", c);
}

void input(int a[], int & n) {
  do {
    printf("\nHow many numbers in the array?  ");
    scanf("%d", & n);
    if (n <= 0 || n > MAX) {
      printf("\nInvalid element numbers. Please check again !");
    }
  } while (n <= 0 || n > MAX);
  for (int i = 0; i < n; i++) {
    printf("\nEnter a[%d]: ", i);
    scanf("%d", & a[i]);
  }
}

void output(int a[], int n) {
  for (int i = 0; i < n; i++) {
    printf("%4d", a[i]);
  }
}

float averageDistanceBetweenValues(int a[], int n) {
  int sum = 0;
  int y = 0;
  int distance = 0;

  for (int i = 0; i < n - 1; i++) {
    printf("\nDistance of ");
    printf("number %d to other values is", a[i]);

    for (int j = i + 1; j < n; j++) {
      sum += abs(a[i] - a[j]);
      distance = abs(a[i] - a[j]);
      printf(" %d ", distance);
      y++;
    }

  }
  float result;
  result = static_cast < float > (sum) / static_cast < float > (y);
  return result;
}

void swap(int * xp, int * yp) {
  int temp = * xp;
  * xp = * yp;
  * yp = temp;
}

// A function to implement bubble sort
void bubbleSort(int arr[], int n) {
  int i, j;
  for (i = 0; i < n - 1; i++) {
    for (j = 0; j < n - i - 1; j++) {
      if (arr[j] > arr[j + 1]) {
        swap( & arr[j], & arr[j + 1]);
      }
    }
  }
}

/* Function to print an array */
void printArray(int arr[], int size) {
  int i;
  for (i = 0; i < size; i++) {
    cout << arr[i] << " ";
  }
  cout << endl;
}

int checkTwoConsecutiveZeros(int a[], int n) {
  int flag = 0; // lúc đầu chưa có
  for (int i = 0; i < n; i++) {
    if (n == 1) {
      printf("\nThere is no 2 consecutive 0 values");
      break;
    }
    if (a[i] == 0 && a[i + 1] == 0) {
      flag = 1;
      printf("\nThere is 2 consecutive 0 values");
      break;
    } else {
      printf("\nThere is no 2 consecutive 0 values");
    }

  }
  return flag;
}

int aInB(int a[], int b[], int n, int m) {
  int flag = 0;
  int Count = 0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (a[i] == b[j]) {
        Count++;
      }
    }
  }
  if (Count == n) {
    flag = 1;
  }
  return flag;
}

int checkAllAInB() {
  int n, m;
  int a[MAX];
  input(a, n);
  output(a, n);

  int b[MAX];
  input(a, n);
  output(a, n);

  int flag = aInB(a, b, n, m);
  if (flag == 1) {
    printf("\nAll elements in array a are in array b");
  } else {
    printf("\nThe condition is not satisfied");
  }

  return 0;
}

int checkDecreaseOfDigits() {
  int n;
  do {
    printf("\nEnter n ( n > 0 ): ");
    scanf("%d", & n);

    if (n <= 0) {
      printf("\nInvalid element number, please check again ! ");
    }

  } while (n <= 0);

  bool CheckDecrease = true;
  int Num = n;
  int TheLastNum = Num % 10; // take the last number
  Num /= 10;

  printf("\nIs %d a number which have digits decreases from left to right?\n ", n);
  while (Num != 0) {
    int TheSecondLastNum = Num % 10;
    Num /= 10;
    if (TheSecondLastNum < TheLastNum) {
      CheckDecrease = false;
      break;
    } else {
      TheLastNum = TheSecondLastNum; // Update digits again for the next comparison
    }
  }
  if (CheckDecrease) {
    printf("\nTrue !");
  } else {
    printf("\nFalse !");
  }
  return 0;
}

int checkIncreaseOfDigits() {
  int n;

  do {
    printf("\nEnter n ( n > 0 ): ");
    scanf("%d", & n);

    if (n <= 0) {
      printf("\nInvalid element number, please check again !");
    }

  } while (n <= 0);

  bool CheckIncease = true;
  int Num = n;
  int TheLastDigit = Num % 10; // take the last number
  Num /= 10;

  printf("\nIs %d  a number which have digits inceases from left to right ?\n ", n);
  while (Num != 0) {
    int TheSecondLastDigit = Num % 10;
    Num /= 10;
    if (TheLastDigit < TheSecondLastDigit) {
      CheckIncease = false;
      break;
    } else {
      TheLastDigit = TheSecondLastDigit; // Update the digit again for the next comparison
    }
  }
  if (CheckIncease) {
    printf("\nTrue !");
  } else {
    printf("\nFalse !");
  }
  return 0;
}

void checkTriangleType(int a, int b, int c) {
  if (a + b <= c || a + c <= b || b + c <= a) {
    printf("\nInvalid triangle, please check again !");
  } else {
    printf("\nThis is : ");
    if ((a == b) && (b == c)) {
      printf("An Equilateral Triangle ");
    } else {
      if (a * a + b * b == c * c || a * c + c * c == b * b || b * b + c * c == a * c) {
        printf(" A Right Triangle");
      } else if (a == b || a == c || b == c) {
        printf("An Isosceles Triangle");
      } else {
        printf("A Normal Triangle");
      }
    }
  }
}

int countEnding(int a[], int n) {
  int Count = 0;
  int x;
  do {
    printf("\nEnter X: ");
    scanf("%d", & x);
    if (x < 0 || x > 10) {
      printf("\nInvalid element numbers. Please check again !");
    }
  } while (x < 0 || x > MAX);
  for (int i = 0; i < n; i++) {
    if (a[i] % 10 == x) {
      Count++;
    }
  }
  return Count;
}

int countOccurencesOfX(int a[], int n, int x) {
  int Count = 0;
  for (int i = 0; i < n; i++) {
    if (a[i] == x) {
      Count++;
    }
  }
  return Count;
}

void input2(float a[][MAX], int & Row, int & Columns) {

  do {
    printf("\nHow many rows? ");

    scanf("%d", & Row);

    if (Row < 1 || Row > MAX) {
      printf("\nInvalid number. Please try with another number!");
    }

  } while (Row < 1 || Row > MAX);

  do {
    printf("\nHow many columns? ");
    scanf("%d", & Columns);

    if (Columns < 1 || Columns > MAX) {
      printf("\nInvalid number. Please try with another number!");

    }

  } while (Columns < 1 || Columns > MAX);
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Columns; j++) {
      float temp;
      printf("\nEnter a[%d][%d] = ", i, j);
      scanf("%f", & temp);
      a[i][j] = temp;
    }
    printf("\n\n");
  }
}

void output2(float a[][MAX], int Row, int Columns) {
  printf("\nThe Matrix: \n");
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Columns; j++) {
      printf("%8.3f", a[i][j]);
    }
    printf("\n\n");
  }
}

int countPositiveNumbers(float a[][MAX], int Row, int Columns) {
  int Count = 0;
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Columns; j++) {
      if (a[i][j] > 0) {
        Count++;
      }
    }
  }
  return Count;
}

/*
- Check that the element is the largest on the line it is standing or not?
- Check that the element is the largest on the column it is standing or not?
- Check if the element is the largest on 2 diagonals (This is a rather complicated function). 
  In this case, I will allow you to browse in 4 directions (4 while):
+ from current element position, i--, j-- (decrease left)
+ from current element position i--,j++ (decrease right)
+ // i++,j++ (right increase)
+ // i++,j-- (left increment)
After performing the above 3 functions, traversing the matrix will find the element "Queen"
*/
bool isQueenValue(int a[][MAX], int LocalRow, int LocalCol, int Row, int Col) {
  int x = a[LocalRow][LocalCol];

  // check row
  for (int i = 0; i < Col; i++) {
    if (a[LocalRow][i] > x) {
      return false;
    }
  }
  // check column
  for (int j = 0; j < Row; j++) {
    if (a[j][LocalCol] > x) {
      return false;
    }
  }
  //check  diagonal(s)
  int LocalRow1 = LocalRow + 1;
  int LocalCol1 = LocalCol + 1;
  while (LocalCol1 + 1 < Col && LocalRow1 < LocalRow) {
    if (a[LocalCol1][LocalRow1] > x) {
      return false;
    }
    // increase right
    LocalCol1++;
    LocalRow1++;
  }
  LocalRow1 = LocalRow - 1;
  LocalCol1 = LocalCol - 1;
  while (LocalCol1 - 1 >= 0 && LocalRow1 >= 0) {
    if (a[LocalCol1][LocalRow1] > x) {
      return false;
    }
    //decrease to the left
    LocalCol1--;
    LocalRow1--;
  }
  // the second diagonal 
  LocalRow1 = LocalRow + 1;
  LocalCol1 = LocalCol - 1;
  while (LocalCol1 - 1 >= 0 && LocalRow1 < Row) {
    if (a[LocalCol1][LocalRow1] > x) {
      return false;
    }
    // increase left
    LocalRow1++;
    LocalCol1--;
  }
  LocalRow1 = LocalRow - 1;
  LocalCol1 = LocalCol + 1;

  while (LocalRow1 - 1 >= 0 && LocalCol1 < Col) {
    if (a[LocalCol1][LocalRow1] > x) {
      return false;
    }
    // right down
    LocalRow1--;
    LocalCol1++;
  }
  return true;
}

int countQueenValues(int a[][MAX], int Row, int Col) {
  int Count = 0;
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Col; j++) {
      if (isQueenValue(a, i, j, Row, Col) == true) {
        Count++;
      }
    }
  }
  return Count;
}

int countQueenValuesInMatrix() {
  int a[MAX][MAX], Row, Col;
  input2(a, Row, Col);
  output2(a, Row, Col);

  int Count = countQueenValues(a, Row, Col);
  printf("\nthe number of Queen values = %d", Count);
  return 0;
}

bool isPrime(int n) {
  if (n < 2) {
    return false;
  } else if (n > 2) {
    if (n % 2 == 0) // even number
    {
      return false;
    }
    for (int i = 3; i <= sqrt((float) n); i += 2) // odd number
    {
      if (n % i == 0) {
        return false;
      }
    }
  }
  return true;
}

int countPrimes(int a[], int n) {
  int count = 0;
  for (int i = 0; i < n; i++) {
    if (isPrime(a[i]) == true && a[i] < 100) {
      count++;
    }
  }
  return count;
}

int smallestOfRow(int a[][MAX], int vtRow, int vtCol, int Row, int Col) {
  int x = a[vtRow][vtCol];
  for (int i = 0; i < Col; i++) {
    if (a[vtRow][i] < x) {
      return false;
    }
  }
  return true;
}

int largestOfColumn(int a[][MAX], int vtRow, int vtCol, int Row, int Col) {
  int x = a[vtRow][vtCol];
  for (int j = 0; j < Row; j++) {
    if (a[j][vtCol] > x) {
      return false;
    }
  }
  return true;
}

void countTheSaddle(int a[][MAX], int Row, int Col) {
  int Count = 0;
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Col; j++) {
      if (smallestOfRow(a, i, j, Row, Col) && largestOfColumn(a, i, j, Row, Col)) {
        printf("A[%d][%d] = %d is a Saddle\n", i, j, a[i][j]);
        Count++;
      }
    }
  }
  printf("\nNumber of Saddle values is %d", Count);
}

struct Node {
  char data[50];
  int counter;
};

void addOrCount(char * word, struct Node * words, int * size) {
  int i;
  bool Exist = false;
  for (i = 0; i < * size; i++) {
    if (strcmp(word, words[i].data) == 0) {
      words[i].counter++;
      Exist = true;
      break;
    }
  }
  if (!Exist) {
    strcpy(words[ * size].data, word);
    words[ * size].counter = 1;
    ( * size) ++;
  }
}

void splitAndCount(char * s, struct Node * words, int * size) {
  char * p;
  const char * delim = " \t\n,.?!;:";
  for (p = strtok(s, delim); p != NULL; p = strtok(NULL, delim)) {
    addOrCount(p, words, size);
  }
}

void show(struct Node * words, int size) {
  int i;
  for (i = 0; i < size; i++) {
    printf("%s : %d\n", words[i].data, words[i].counter);
  }
}

int countWords(char s[5000]) {
  struct Node words[500];
  int size = 0;

  splitAndCount(s, words, & size);
  show(words, size);

  return 0;
}

int countVowels(string text) {
  int ctr = 0;
  //loops through the passed in string and adds one to the counter each time a vowel is seen
  for (int i = 0; i < int(text.size()); i++) {
    if (text[i] == 'a' || text[i] == 'e' || text[i] == 'i' || text[i] == 'o' || text[i] == 'u')
      ++ctr;
    if (text[i] == 'A' || text[i] == 'E' || text[i] == 'I' || text[i] == 'O' || text[i] == 'U')
      ++ctr;
  }
  return ctr;

}

int countWords(string text) {

  int ctr = 0;
  //loop through the string passed in 
  for (int x = 0; x < text.length(); x++) {
    // everytime we see a space we count this as a new word
    if (text[x] == ' ') {
      ctr++;
    }
  }
  // we return the counter plus 1 to include the first word
  return ctr + 1;
}

const int minYear = 1900,
  maxYear = 10000;
int specialYear(int Year) {
  return (Year % 4 == 0 && Year % 100 != 0) || (Year % 400 == 0);
}

void numberOfDayInMonth(int Month, int Year) {
  switch (Month) {
  case 1:
  case 3:
  case 5:
  case 7:
  case 8:
  case 10:
  case 12:
    printf("\nhas 31 days");
    break;
  case 4:
  case 6:
  case 9:
  case 11:
    printf("\nhas 30 days");
    break;
  case 2:
    int Check = specialYear(Year);
    if (Check == 1) {
      printf("\nhas 29 days !");
    } else {
      printf("\nhas 28 days !");
    }
  }
}

int daysOfMonth() {
  int Month, Year;

  do {
    printf("\nEnter the Month: ");
    scanf("%d", & Month);
    if (Month < 1 || Month > 12) {
      printf("\nInvalid element number, please check again !");
    }
  } while (Month < 1 || Month > 12);
  do {
    printf("\nEnter the Year: ");
    scanf("%d", & Year);
    if (Year < minYear || Year > maxYear) {
      printf("\nInvalid element number, please check again !");
    }
  } while (Year < minYear || Year > maxYear);
  numberOfDayInMonth(Month, Year);
  return 0;
}

int countDivisionBy7(int a[], int n) {
  int count = 0;
  for (int i = 0; i < n; i++) {
    if (a[i] > 0 && a[i] % 7 == 0) {
      count++;
    }
  }
  return count;
}

bool doesWordContainLetter(std::string wordToCheck, char letterToCheck) {
  // itertes through the word
  for (int i = 0; i < wordToCheck.length(); i++) {
    //returns true once we find the letter once
    if (wordToCheck[i] == letterToCheck) {
      return true;
    }
  }
  return false;
}

int SumOfEvenDivisors() {
  int i, n;
  do {
    printf("\nEnter n(n > 0): ");
    scanf("%d", & n);
    if (n <= 0) {
      printf("\n N > 0. please inter again !");
    }
  } while (n <= 0);
  i = 1;

  printf("\nEven divisor(s) of %d is/are: ", n);
  while (i <= n) {
    if (n % i == 0) {
      if (i % 2 == 0)
        printf("%4d", i);
    }
    i++;
  }
  return 0;
}

void evenNumbers(int a[], int n) {
  for (int i = 0; i < n; i++) {
    if (a[i] % 2 == 0) {
      printf("%4d", a[i]);
    }
  }
}

int listOfEvenNumbers() {
  int n;
  int a[MAX];

  input(a, n);
  output(a, n);
  printf("\nThe even numbers in the array : ");
  evenNumbers(a, n);
  return 0;
}

int evenOrOddArray(int a[], int n) {
  int CountEven = 0;
  for (int i = 0; i < n; i++) {
    if (a[i] % 2 == 0) {
      CountEven++;
    }
  }
  if (CountEven > n - CountEven) {
    return -1;
  } else if (n - CountEven == CountEven) {
    return 0;
  }
  return 1;
}

int factorial(int n) {
  int i;
  long P;
  i = 1;
  P = 1;
  do {

    if (n < 1) {
      printf("\nN The number have to > or = 1 !");
    }
  } while (n < 1);

  while (i <= n) {
    P = P * i;
    i++;
  }

  return P;
}

//find the largest positive integer m such that 1 + 2 + ... + m < N
int findNumberInSeries() {
  int N, m, S;
  printf("\nEnter N: ");
  scanf("%d", & N);

  S = 0;
  m = 0;
  do {
    m = m + 1;
    S = S + m;
  } while (S + m + 1 < N);
  printf("\nthe largest positive integer m such that 1 + 2 + ... + m < N, m = %d", m);
  return 0;
}

// create array b contain distances between number x and other numbers in the array
void createArray(float a[], int n, float b[], int x) {
  for (int i = 0; i < n; i++) {
    b[i] = abs(x - a[i]);
  }
}

// the max in array b
float findMax(float b[], int n) {
  int Max = b[0];
  for (int i = 1; i < n; i++) {
    if (b[i] > Max) {
      Max = b[i];
    }
  }
  return Max;
}
void longestDistances(float a[], float b[], int n) {
  printf("\nThe longest distance from x is : ");
  int Max = findMax(b, n);
  for (int i = 0; i < n; i++) {
    if (b[i] == Max) {
      printf("%8.3f", a[i]);
    }
  }
}

int findLongestDistances() {
  int n;
  float a[MAX];
  float b[MAX];

  input(a, n);
  output(a, n);

  float x;
  printf("\nPlease Enter x: ");
  scanf("%f", & x);

  createArray(a, n, b, x);
  printf("\nThe distances from x = %.3f to each Number in the array:\n", x);
  output(b, n);
  longestDistances(a, b, n);
  return 0;
}

//find min in array b
float findMin(float b[], int n) {
  int Min = b[0];
  for (int i = 1; i < n; i++) {
    if (b[i] < Min) {
      Min = b[i];
    }
  }
  return Min;
}
void shortestDistance(float a[], float b[], int n) {
  printf("\nThe shortest distance from x is :  ");
  int Min = findMin(b, n);
  for (int i = 0; i < n; i++) {
    if (b[i] == Min) {
      printf("%8.3f", a[i]);
    }
  }
}

int findShortestDistance() {
  int n;
  float a[MAX];
  float b[MAX];

  input(a, n);
  output(a, n);

  float x;
  printf("\n Please Enter x:  ");
  scanf("%f", & x);

  createArray(a, n, b, x);
  printf("\nThe distances from x = %.3f to each Number in the array:\n", x);
  output(b, n);
  shortestDistance(a, b, n);
  return 0;
}

void findTheDay() {

  int Day, Month, Year;
  cout << "Enter Month: \n";
  cin >> Month;
  cout << "Enter Day : \n";
  cin >> Day;
  cout << "Enter Year : \n";
  cin >> Year;

  int a, b, D;
  D = Year - 1;

  int K = 365 * D;
  D = Year % 4;

  int NumOfDay;
  int Result = 0;
  for (a = 1; a <= Year - 1; a++) {

    if (a % 100 == 0) {
      if (a % 400 == 0) {
        K++;
      }
    } else if (a % 4 == 0) {
      K++;
    }

  }

  if (Year % 100 == 0) {
    if (Year % 400 == 0) {
      Result = 1;
    } else {
      Result = 0;
    }
  } else {
    if (Year % 4 == 0) {
      Result = 1;
    } else {
      Result = 0;
    }
  }

  int ds = 0;
  for (a = 1; a <= Month; a++) {

    if (a == 1 || a == 3 || a == 5 || a == 7 || a == 8 || a == 10 || a == 12) NumOfDay = 31;
    if (a == 4 || a == 6 || a == 9 || a == 11) NumOfDay = 30;
    if (a == 2) {
      if (Result == 1) {
        NumOfDay = 29;
      } else {
        NumOfDay = 28;
      }
    }

    for (b = 1; b <= NumOfDay; b++) {
      K = K + 1;
      if (b == Day && a == Month) {
        ds = 1;
        break;
      }
    }

    if (ds == 1) {
      break;
    }
  }

  K = K % 7;
  switch (K) {

  case 0:
    cout << "\nThis is Sunday !" << endl;
    break;
  case 1:
    cout << "\nThis is Monday !" << endl;
    break;
  case 2:
    cout << "\nThis is Tuesday !" << endl;
    break;
  case 3:
    cout << "\nThis is Wednesday !" << endl;
    break;
  case 4:
    cout << "\nThis is Thursday !" << endl;
    break;
  case 5:
    cout << "\nThis is Friday !" << endl;
    break;
  case 6:
    cout << "\nThis is Saturday !" << endl;
    break;

  }

}

float firstPositive(float a[], int n) {
  for (int i = 0; i < n; i++) {
    if (a[i] > 0) {
      return a[i];
    }
  }
  return -1;
}
int findTheFirstPositive() {
  int n;
  float a[MAX];
  input(a, n);
  output(a, n);

  float first = firstPositive(a, n);
  printf("\nThe first positive number in the array is %.3f", first);
  return 0;
}

int findTheFistNegativeNumber(float a[], int n) {
  for (int i = 0; i < n; i++) {
    if (a[i] < 0) {
      return i;
    }
  }
  return -1;
}

float theLargestNegativeNumber(float a[], int n, int FirstLoca) {
  float NegativeMax = a[FirstLoca];

  for (int i = FirstLoca + 1; i < n; i++) {
    if (a[i] < 0 && a[i] > NegativeMax) {
      NegativeMax = a[i];
    }
  }
  return NegativeMax;
}

int findTheLargestNegativeNumber() {
  int n;
  float a[MAX];

  input(a, n);
  output(a, n);
  int FirstLoca = findTheFistNegativeNumber(a, n);
  if (FirstLoca == -1) {
    printf("\nThe array has no negative number");
  } else {
    float NegativeMax = theLargestNegativeNumber(a, n, FirstLoca);
    printf("\nthe Largest negative value in the array is: %.3f", NegativeMax);
  }
  return 0;
}

int findTheLastEven(int a[], int n) {
  for (int i = n - 1; i >= 0; i--) {
    if (a[i] % 2 == 0) {
      return a[i];
    }
  }
  return -1;
}

void segment(float a[], int n) {
  float max = a[0];
  float min = a[0];
  for (int i = 0; i < n; i++) {
    max = (a[i] > max) ? a[i] : max;
    min = (a[i] < min) ? a[i] : min;
  }
  printf("\n[ %.3f , %.3f ] contains all the values in the array \n", min, max);
}
int findSegment() {
  int n;
  float a[MAX];
  float b[MAX];

  input(a, n);
  output(a, n);
  segment(a, n);
  return 0;
}

float findTheFistPositiveNumber(float a[], int n) {
  for (int i = 0; i < n; i++) {
    if (a[i] > 0) {
      return a[i];
    }
  }
  return -1;
}
float theSmallestPositiveNumber(float a[], int n) {
  float SmallestPositiveNumber = findTheFistPositiveNumber(a, n);
  if (SmallestPositiveNumber == -1) {
    return -1;
  }
  for (int i = 0; i < n; i++) {
    if (a[i] > 0 && a[i] < SmallestPositiveNumber) {
      SmallestPositiveNumber = a[i];
    }
  }
  return SmallestPositiveNumber;
}

int findTheSmallestPositiveNumber() {
  int n;
  float a[MAX];

  input(a, n);
  output(a, n);

  float SmallestPositiveNumber = theSmallestPositiveNumber(a, n);
  printf("\nThe smallest positive number is %.3f", SmallestPositiveNumber);
  return 0;
}

//Determine the size of the rectangle so that the difference between the width and the length is minimal.
int findWidthAndLength() {
  int n;
  cout << "Enter the Area of the rectangle: ";
  cin >> n;
  cout << "\nThe width and Length are: ";
  for (int i = int(sqrt(n)); i > 0; i--) {
    if (n % i == 0) {
      cout << i << " and " << n / i << endl;
      break;
    }
  }
  return 0;
}

float findX(float a[], int n) {
  //Given a 1-dimensional array of real numbers, find the value x such that the segment [-x, x] contains all the values in the array
  float x = a[0];
  for (int i = 1; i < n; i++) {
    x = (x > (fabs)(a[i])) ? x : (fabs)(a[i]);
  }
  return x;
}
int foundX() {
  //Given a 1-dimensional array of real numbers, find the value x such that the segment [-x, x] contains all the values in the array
  int n;
  float a[MAX];
  float b[MAX];

  input(a, n);
  output(a, n);
  float x = findX(a, n);
  printf("\nValue X = %.3f satisfies the condition [-%.3f, %.3f] which contains all values in the array", x, x, x);
  return 0;
}

void frequencyOfOccurrence(int a[], int n) {
  //List the frequency of occurrence of values in the array (listed once each value)
  for (int i = 0; i < n; i++) {
    int Count = 0;
    for (int j = 0; j < n; j++) // j run from 0
    {
      if (a[i] == a[j]) {
        if (i <= j) {
          Count++;
        } else {
          break; // pass if get the same element
        }
      }
    }
    if (Count != 0) {
      printf("\n%d appears: %d time(s)", a[i], Count);
    }
  }
}

int futurePopulation() {
  /* Indicates the population of a city is P people.
Know that the population growth rate of this city is R% per year.
Calculate the population of this city after N years
(P, R, N is an integer input from the keyboard).
*/

  int n, Population, Rate;

  do {
    printf("\nHow many people are there in the city?  ");
    scanf("%d", & Population);

    if (Population < 1) {
      printf("\nInvalid number. Please check again !");
    }

  } while (Population < 1);

  printf("\nEnter the rate (percent) of population growth of this city each year? ");
  scanf("%d", & Rate);

  do {
    printf("\nHow many years later do you want to know the population? ");
    scanf("%d", & n);

    if (n < 1) {
      printf("\nInvalid number. Please check again !");

    }

  } while (n < 1);

  for (int i = 1; i <= n; i++) {
    Population = Population + (Population * Rate / 100);
  }
  cout << "\nIn " << n << " later, the population of the city will be " << Population << ".";
  return 0;
}

int greatestCommonDivisor() {
  //Find greatest common divisor of two positive integer number
  int a, b;

  do {
    printf("\nPlease Enter a (a > 0) = ");
    scanf("%4d", & a);
    if (a < 0) {
      printf("\na > 0, please check again !");
    }
  } while (a < 0);

  do {
    printf("\nPlease Enter b (b > 0)= ");
    scanf("%4d", & b);
    if (b < 0) {
      printf("\nb > 0, please check again !");
    }
  } while (b < 0);

  int Max = a > b ? a : b;
  int Min = a < b ? a : b;

  if (Max % Min == 0) {
    printf("\n The greatest common divisor is: %d", Min);
  } else {
    for (int i = Min / 2; i >= 1; i--) {
      if (Min % i == 0 && Max % i == 0) {
        printf("\nThe greatest common divisor is: %d", i);
        break;
      }
    }
  }
  return 0;
}

/* Function to sort an array using insertion sort*/
void insertionSort(int arr[], int n) {
  int i, key, j;
  for (i = 1; i < n; i++) {
    key = arr[i];
    j = i - 1;

    /* Move elements of arr[0..i-1], that are
    greater than key, to one position ahead
    of their current position */
    while (j >= 0 && arr[j] > key) {
      arr[j + 1] = arr[j];
      j = j - 1;
    }
    arr[j + 1] = key;
  }
}

bool isPalindrome(std::string wordToCheck) {
  int j;
  // declare the length of the word to check
  int len = wordToCheck.length();
  // loop though half of the word 
  for (int i = 0, j = len - 1; i < len / 2; ++i, --j) {
    // if the front half of the word doesnt match the second half, return false
    if (wordToCheck[j] != wordToCheck[i])
      return false;
  }

  return true;
}

int subarrayOfB(int a[], int b[], int na, int nb) {
  int i, j, Check = 0;
  for (i = 0; i < nb; i++) {
    if (b[i] == a[0]) // if b contain a
    {
      int Start = i;
      Check = 1;
      for (j = 0; j < na; j++) {
        if (a[j] != b[Start++]) {
          Check = 0;
          break;
        }
      }
      if (Check == 1) {
        return Check;
      }
    }
  }
  return Check;
}
int ssSubArrayOfB() {
  int na, nb;
  int a[MAX], b[MAX];
  printf("\nArray A:");

  input(a, na);
  output(a, na);
  printf("\nArray B:");
  input(b, nb);
  output(b, nb);

  int Check = subarrayOfB(a, b, na, nb);
  if (Check != 1) {
    printf("\nThe array A is NOT a subarray of the array B!");
  } else {
    printf("\nThe array A is a subarray of the array B");
  }

  return 0;
}

int IsSymmetricNumber() {
  //Check if the positive integer n is a symmetric number
  int n;
  do {
    printf("\nEnter n ( n > 0 ): ");
    scanf("%d", & n);

    if (n <= 0) {
      printf("\nError: n > 0 !");
    }

  } while (n <= 0);

  int NumOfNum = (int) log10((float) n) + 1;
  int SymmetricNumber = 0;
  int Num = n;

  printf("\nIs %d a symmetric number ?\n", n);
  while (Num != 0) {
    int Number = Num % 10;
    Num /= 10;
    SymmetricNumber += Number * pow(10.0, --NumOfNum);
  }

  if (SymmetricNumber == n) {
    printf("Yes !");
  } else {
    printf("No !");
  }
  return 0;
}

int leastCommonMultiple() {
  //Find the least common multiple of two positive integer number
  int i, a, b, Max, Mu = 1;

  cout << "\nPlease Enter number a (a > 0): ";
  cin >> a;
  cout << "\nPlease Enter number b (b > 0): ";
  cin >> b;
  if (a <= 0 || b <= 0) {
    cout << a << " and " << b << " a > 0 and b > 0 " << endl;
  } else {
    if (a > b) {
      Max = a;
    } else {
      Max = b;
    }

    int i = Max;

    while (1) {
      if (i % a == 0 && i % b == 0) {
        Mu = i;
        break;
      }
      i += Max;
    }
    cout << "\nThe least common multiple is: " << Mu << endl;
  }

  return 0;
}

void listAllEvenNumberBetweenXY(int a[], int n, int x, int y) {
  //List the even numbers in a 1-dimensional array of integers of the given range [x, y] (x, y are integers).
  for (int i = 0; i < n; i++) {
    if (a[i] % 2 == 0 && x <= a[i] && a[i] <= y) {
      printf("\n%4d", a[i]);
    }
  }
}

void negativeNumbers(float a[], int n) {
  //List negative numbers in a 1-dimensional array of real numbers
  int x = 0;
  printf("\n\nNegative number(s) in the array is/are: ");
  for (int i = 0; i < n; i++) {
    if (a[i] < 0) {
      printf("%8.3f", a[i]);
      x++;
    }
  }
  if (x == 0) {
    printf(" No negative number");
  }
}
int listAllNegativeNumbers() {
  int n;
  float a[MAX];

  input(a, n);
  output(a, n);
  negativeNumbers(a, n);
  return 0;
}

void numberBetweenXY(float a[], int n, float x, float y) {
  //List the numbers in a 1-dimensional array of real numbers in the given interval [x, y]
  for (int i = 0; i < n; i++) {
    if (x <= a[i] && a[i] <= y) {
      printf("\n%8.3f", a[i]);
    }
  }
}
int listAllNumberBetweenXY() {
  //List the numbers in a 1-dimensional array of real numbers in the given interval [x, y]
  int n;
  float a[MAX];
  float x, y;
  printf("\nEnter x: ");
  scanf("%f", & x);

  printf("\nEnter y: ");
  scanf("%f", & y);
  input(a, n);
  output(a, n);

  printf("\nAll real numbers of the array in the interval  [%.3f, %.3f] are/is: ", x, y);
  numberBetweenXY(a, n, x, y);
  return 0;
}

bool testPerfectSquare(int n) {
  return sqrt(float(n)) == (int) sqrt((float) n);
}

void listPerfectSquare(int n) {
  //List all perfect squares less than n
  for (int i = 2; i < n; i++) {
    if (testPerfectSquare(i) == true) {
      printf("%4d", i);
    }
  }
}

void listOfSubarrays(int a[], int n) {
  int Length; // the length of the subarrays
  for (int i = 0; i < n; i++) {
    for (Length = 1; Length <= n; Length++) // 
    {
      for (int j = i; j < Length; j++) {
        printf("%4d", a[j]);
      }
      printf("\n");
    }
  }
}

int findTheFirstOdd(int n) {
  int dv;
  while (n >= 10) // 
  {
    dv = n % 10;
    n = n / 10;
  }
  if (n % 2 == 0) {
    return 0;
  }
  return 1;
}

int listTheNumber(int a[], int n) {
  //List the values in the array of integers whose first digit is an odd number
  for (int i = 0; i < n; i++) {
    if (findTheFirstOdd(a[i]) == 1) {
      printf("%4d", a[i]);
    }
  }
  return 0;
}

void listPrimes(int n) {
  for (int i = 2; i < n; i++) {
    if (testPrime(i) == true) {
      printf("%4d", i);
    }
  }
}

void listSubarrayslonger2(int a[], int n) {
  //List subarrays with length greater than 2 elements
  int Length;
  for (int i = 0; i < n; i++) {
    for (Length = 3 + i; Length <= n; Length++) {
      for (int j = i; j < Length; j++) {
        printf("%4d", a[j]);
      }
      printf("\n");
    }
  }
}

void in (float a[], int & n) {
  do {
    printf("\nHow many numbers in the array?");
    scanf("%d", & n);
    if (n <= 0 || n > MAX) {
      printf("\nInvalid element numbers. Please check again !");
    }
  } while (n <= 0 || n > MAX);
  for (int i = 0; i < n; i++) {
    printf("\nEnter a[%d]: ", i);
    scanf("%f", & a[i]);
  }
}

void out(float a[], int n) {
  for (int i = 0; i < n; i++) {
    printf("%8.3f", a[i]);
  }
}

int location(float a[], int n) {
  int Loca = 0;
  for (int i = 0; i < n; i++) {
    if (a[i] < a[Loca]) {
      Loca = i;
    }
  }
  return Loca;
}

int locationOfTheSmallest() {
  //find the location of the smallest number in an array
  int n;
  float a[MAX]; in (a, n);
  out(a, n);

  int Loca = location(a, n);

  printf("\nThe location of the smallest number in the array %d", Loca);
  return 0;
}

// create array b contain distances between number x and other numbers in the array
void createArray(float a[], int n, float b[], int x) {
  for (int i = 0; i < n; i++) {
    b[i] = abs(x - a[i]);
  }
}

// the max in array b
float findMax(float b[], int n) {
  int Max = b[0];
  for (int i = 1; i < n; i++) {
    if (b[i] > Max) {
      Max = b[i];
    }
  }
  return Max;
}
void longestDistances(float a[], float b[], int n) {
  //find the longest distances between number x and other numbers in an array
  printf("\nThe longest distance from x is : ");
  int Max = findMax(b, n);
  for (int i = 0; i < n; i++) {
    if (b[i] == Max) {
      printf("%8.3f", a[i]);
    }
  }
}

int findLongestDistances() {
  //find the longest distances between number x and other numbers in an array
  int n;
  float a[MAX];
  float b[MAX];

  input(a, n);
  output(a, n);

  float x;
  printf("\nPlease Enter x: ");
  scanf("%f", & x);

  createArray(a, n, b, x);
  printf("\nThe distances from x = %.3f to each number in the array:\n", x);
  output(b, n);
  longestDistances(a, b, n);
  return 0;
}

/*
1 2 3 7 4 1
A = 4 5 6 ---> C = 8 5 2
7 8 9 9 6 3
Noticing that rows have turned into columns and vice versa, we immediately think of
formula B[i][j] = A[j][i]
But this is the familiar formula of the transpose matrix
The result when transposed is as follows
1 4 7
B = 2 5 8
3 6 9
Compared to the desired result, we see that C only differs from B in the order of columns
that is, column (j) of C is column (n - 1 - j) of B
C[i][j] = B[i][n - 1 - j] = A[n - 1 - j][i]
*/
void the90DegreeTurn(int a[][MAX], int Row, int Column) {
  int c[MAX][MAX];
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Column; j++) {
      printf("%4d", c[i][j] = a[Column - 1 - j][i]);
    }
    printf("\n\n");
  }
}

void the90DegreeTurn_2(int a[][MAX], int Row, int Column) {
  for (int i = 0; i < Column; i++) {
    for (int j = Row - 1; j >= 0; j--) {
      printf("%4d", a[j][i]);
    }
    printf("\n\n");
  }
}
int matrixAfterRotating90Degrees() {
  int a[MAX][MAX], Row, Column;
  input2(a, Row, Column);
  output2(a, Row, Column);

  printf("\nRotate the matrix by 90 degrees clockwise: \n");

  the90DegreeTurn(a, Row, Column);
  return 0;
}

void sort(int a[], int n) {
  for (int i = 0; i < n - 1; i++) {
    for (int j = i + 1; j < n; j++) {
      if (a[i] > a[j]) {
        swap(a[i], a[j]);
      }
    }
  }
}

void mix(int a[], int b[], int c[], int na, int nb, int & nc) {
  nc = na + nb; // number of all elements 
  sort(a, na); // put A in order
  sort(b, nb); // put B in order
  int Local_A = 0, Local_B = 0;
  for (int i = 0; i < nc; i++) // check the array
  {
    if (Local_A < na && Local_B < nb) // larger element from 2 arrays      
    {
      if (a[Local_A] < b[Local_B]) // smaller element for A
      {
        c[i] = a[Local_A++]; // Put A into the final Array
      } else {
        c[i] = b[Local_B++]; // Put B into the final Array
      }
    } else if (Local_B == nb) // If position b increases by the number of array elements B
    {
      c[i] = a[Local_A++]; // Only array a is left to pour into the array to be merged
    } else {
      c[i] = b[Local_B++];
    }
  }
}

int merge2In1AscendingArray() {
  //Merge 2 arrays into 1 sorted array in ascending order
  int na, nb, nc;
  int a[MAX], b[MAX], c[MAX];

  input(a, na);
  output(a, na);

  input(b, nb);
  output(b, nb);

  mix(a, b, c, na, nb, nc);
  printf("\nThe final array : ");
  output(c, nc);
  return 0;
}

// Merges two subarrays of array[].
// First subarray is arr[begin..mid]
// Second subarray is arr[mid+1..end]
void merge(int array[], int
  const left, int
  const mid, int
  const right) {
  auto
  const subArrayOne = mid - left + 1;
  auto
  const subArrayTwo = right - mid;

  // Create temp arrays
  auto * leftArray = new int[subArrayOne];
  auto * rightArray = new int[subArrayTwo];

  // Copy data to temp arrays leftArray[] and rightArray[]
  for (auto i = 0; i < subArrayOne; i++) {
    leftArray[i] = array[left + i];
  }
  for (auto j = 0; j < subArrayTwo; j++) {
    rightArray[j] = array[mid + 1 + j];
  }

  auto indexOfSubArrayOne = 0; // Initial index of first sub-array
  auto indexOfSubArrayTwo = 0; // Initial index of second sub-array
  int indexOfMergedArray = left; // Initial index of merged array

  // Merge the temp arrays back into array[left..right]
  while (indexOfSubArrayOne < subArrayOne && indexOfSubArrayTwo < subArrayTwo) {
    if (leftArray[indexOfSubArrayOne] <= rightArray[indexOfSubArrayTwo]) {
      array[indexOfMergedArray] = leftArray[indexOfSubArrayOne];
      indexOfSubArrayOne++;
    } else {
      array[indexOfMergedArray] = rightArray[indexOfSubArrayTwo];
      indexOfSubArrayTwo++;
    }
    indexOfMergedArray++;
  }
  // Copy the remaining elements of
  // left[], if there are any
  while (indexOfSubArrayOne < subArrayOne) {
    array[indexOfMergedArray] = leftArray[indexOfSubArrayOne];
    indexOfSubArrayOne++;
    indexOfMergedArray++;
  }
  // Copy the remaining elements of
  // right[], if there are any
  while (indexOfSubArrayTwo < subArrayTwo) {
    array[indexOfMergedArray] = rightArray[indexOfSubArrayTwo];
    indexOfSubArrayTwo++;
    indexOfMergedArray++;
  }
}

// begin is for left index and end is
// right index of the sub-array
// of arr to be sorted */
void mergeSort(int array[], int
  const begin, int
  const end) {
  if (begin >= end) {
    return; // Returns recursively
  }

  auto mid = begin + (end - begin) / 2;
  mergeSort(array, begin, mid);
  mergeSort(array, mid + 1, end);
  merge(array, begin, mid, end);
}

int countAppearance(int a[][MAX], int Row, int Column, int x) {
  int temp = 0;
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Column; j++) {
      if (a[i][j] == x) {
        temp++;
      }
    }
  }
  return temp;
}
int mostAppear(int a[][MAX], int Row, int Column) {
  int temp1, temp = countAppearance(a, Row, Column, a[0][0]), index1 = 0, index2 = 0;
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Column; j++) {
      temp1 = CountAppearance(a, Row, Column, a[i][j]);
      if (temp < temp1) {
        temp = temp1;
        index1 = i;
        index2 = j;
      }
    }
  }
  return a[index1][index2];
}
int mostAppearValueInMatrix() {
  //Find the value that appears the most in a matrix
  int a[MAX][MAX], Row, Column;
  input2(a, Row, Column);
  output2(a, Row, Column);

  int Value = mostAppear(a, Row, Column);
  printf("\nthe value that appears the most: %d", Value);

  return 0;
}

int multiplicationTableOfZ() {
  //Input the number Z, then output the multiplication table of Z.
  int z, j, Product;
  printf("\nEnter Z: ", z);
  scanf("%d", & z);
  Product = 0;
  printf("\nThe multiplication table of Z = %d : \n", z);
  for (j = 1; j <= 10; j++) {
    Product = z * j;
    cout << z << "x" << j << "=" << Product << endl;
  }
  return 0;
}

int occurancesOfChar(std::string wordToCheck, char letterToCheck) {
  int iRetV = 0;
  // itertes through the word
  for (int i = 0; i < wordToCheck.length(); i++) {
    //returns adds to the counter everytime we see the character
    if (wordToCheck[i] == letterToCheck) {
      iRetV++;
    }
  }
  return iRetV;
}

int countTheTimesAInB(int a[], int b[], int na, int nb) {
  //Given 2 arrays a, b. Count the number of occurrences of array A in array B
  int i, j, Start, flag, Count = 0;
  for (i = 0; i < nb; i++) // Check B
  {
    if (a[0] == b[i] && nb - i >= na) // if the first element of array a is equal to any element of array b
    { // the number of array elements b minus i is greater than or equal to the number of array elements a
      Start = i; // Start variable will be the starting variable to count, initialized right at i satisfying the condition
      flag = 1;
      for (j = 0; j < na; j++) {
        if (a[j] != b[Start++]) {
          flag = 0;
          break;
        }
      }
      if (flag == 1) {
        Count++;
      }
    }
  }
  return Count;
}
int occurrencesAInB() {
  //Given 2 arrays a, b. Count the number of occurrences of array A in array B
  int na, nb;
  int a[MAX], b[MAX];

  printf("\nThe Array A:");
  input(a, na);
  output(a, na);

  printf("\nThe Array B:");
  input(b, nb);
  output(b, nb);

  int Count = countTheTimesAInB(a, b, na, nb);
  printf("\nthe number of occurrences of array A in array B = %d", Count);
  return 0;
}

int occurrencesOfMax() {
  //Count the number of occurrences of the largest number which is an element in the positive integer n
  int i, n;
  int max, Count;
  max = 0;
  Count = 1;
  do {
    printf("\nEnter n: ");
    scanf("%d", & n);
  } while (n < 0 && printf("\nError: n >= 0 !"));
  if (n == 0) {
    Count = 1;
  }

  do {
    i = n % 10;
    if (i == max) {
      Count++;
    }
    if (i > max) {
      max = i;
    }
  } while (n /= 10);
  printf("\nThe MAX: %d", max);
  printf("\nThe number of occurrences of %d is %d", max, Count);
  return 0;
}

int occurrencesOfMin() {
  //Count the number of occurrences of the smallest number which is an element in the positive integer n
  int i, n;
  int min, Count;
  Count = 0;
  do {
    printf("\nEnter n: ");
    scanf("%d", & n);
  } while (n < 0 && printf("\nError: n >= 0 !"));
  min = n % 10;
  if (n == 0) {
    min = 0;
  }
  do {
    i = n % 10;
    if (i == min) {
      Count++;
    }
    if (i < min) {
      min = i;
    }
  } while (n /= 10);

  printf("\nThe MIN: %d", min);
  printf("\nThe number of occurrences of %d is %d", min, Count);

  getch();
  return 0;
}

int sumOfOddDivisors() {
  //show all odd divisors of a positive integer number

  int i, n;
  do {
    printf("\nEnter n(n > 0): ");
    scanf("%d", & n);
    if (n <= 0) {
      printf("\n N > 0. please Enter again !");
    }
  } while (n <= 0);
  i = 1;

  printf("\nOdd divisor(s) of %d is/are: ", n);
  while (i <= n) {
    if (n % i == 0) {
      if (i % 2 == 1)
        printf("%4d", i);
    }
    i++;
  }

  return 0;
}

int findTheFistNegativeNumber(float a[], int n) {
  int Loca = -1;
  for (int i = 0; i < n; i++) {
    if (a[i] < 0) {
      Loca = i;
      break;
    }
  }
  return Loca;
}

float findTheLargestNegativeNumber(float a[], int n, int FistNegativeNumbers) {
  for (int i = FistNegativeNumbers + 1; i < n; i++) {
    if (a[i] < 0 && a[i] > a[FistNegativeNumbers]) {
      FistNegativeNumbers = i;
    }
  }
  return FistNegativeNumbers;
}
int positionOfLargestNegativeNumber() {
  //Find the position of the Largest negative value in the array of real numbers. If the array has no negative numbers, return -1
  int n;
  float a[MAX];

  input(a, n);
  output(a, n);

  int FistNegativeNumbers = findTheFistNegativeNumber(a, n);
  if (FistNegativeNumbers == -1) {
    printf("\nthe array has no negative numbers");
  } else {
    int Loca = findTheLargestNegativeNumber(a, n, FistNegativeNumbers);
    printf("\nthe Largest negative value in the array of real numbers is at: %d", Loca);
  }

  return 0;
}

int theFirstEven(int a[], int n) {
  for (int i = 0; i < n; i++) {
    if (a[i] % 2 == 0) {
      return i;
    }
  }
  return -1;
}

int positionOfTheFirstEven() {
  //Find the position of the first even value in a 1-dimensional array of integers. If the array has no even values, -1 . is returned
  int n;
  int a[MAX];
  input(a, n);
  output(a, n);

  int Position = theFirstEven(a, n);
  printf("\nthe position of the first even value is %d", Position);
  return 0;
}

int findThePosition(float a[], int n) {
  int position = 0;
  for (int i = 0; i < n; i++) {
    if (a[i] < a[position]) {
      position = i;
    }
  }
  return position;
}
int positionOfTheSmallestNumber() {
  int n;
  float a[MAX];
  input(a, n);
  output(a, n);

  int position = findThePosition(a, n);

  printf("\nPosition of the smallest number %d", position);
  return 0;
}

float productOfCol(float a[][MAX], int y, int Row) {
  float Product = 1;
  for (int i = 0; i < Row; i++) {
    if (a[i][y] > 0) {
      Product *= a[i][y];
    }
  }
  return Product;
}
int productOfAColumnInMatrix() {
  //Calculate the product of positive values on a column in a matrix of real numbers
  float a[MAX][MAX];
  int Row, Col;
  input(a, Row, Col);
  output(a, Row, Col);

  int y;
  do {
    printf("\nEnter the column Y Which you want to know the Product: ");
    scanf("%d", & y);

    if (y < 0 || y > Col - 1) {
      printf("\nInvalid number. Please check again !");
    }
  } while (y < 0 || y > Col - 1);

  float Product = productOfCol(a, y, Row);
  printf("\nthe Product of column %d = %f", y, Product);
  return 0;
}

int ProductOfAllDivisors() {
  //Product of all divisors of a positive integer n
  int i, n;
  long P;
  do {
    printf("\nEnter n(n > 0): ");
    scanf("%d", & n);
    if (n <= 0) {
      printf("\n Invalid number. Please check again with n > 0!");
    }
  } while (n <= 0);
  i = 1;
  P = 1;
  while (i <= n) {
    if (n % i == 0) {
      printf("%4d", i);
      P = P * i;
    }
    i++;
  }
  printf("\nProduct of all divisors of %d is: %ld", n, P);
  return 0;
}

int ProductOfTheComponents() {
  //Find the product of numbers that are components of a positive integer n
  int x, n;
  int P;

  do {
    printf("\nEnter n: ");
    scanf("%d", & n);
  } while (n < 0 && printf("\nError: n >= 0 !"));
  P = 1;
  x = n;

  while (x != 0) {
    P = P * (x % 10);
    x = x / 10;
  }
  printf("\nThe product is %d", P);

  return 0;
}

/* This function takes last element as pivot, places
the pivot element at its correct position in sorted
array, and places all smaller (smaller than pivot)
to left of pivot and all greater elements to right
of pivot */
int partition(int arr[], int low, int high) {
  int pivot = arr[high]; // pivot
  int i = (low - 1); // Index of smaller element and indicates the right position of pivot found so far

  for (int j = low; j <= high - 1; j++) {
    // If current element is smaller than the pivot
    if (arr[j] < pivot) {
      i++; // increment index of smaller element
      swap( & arr[i], & arr[j]);
    }
  }
  swap( & arr[i + 1], & arr[high]);
  return (i + 1);
}

/* The main function that implements QuickSort
arr[] --> Array to be sorted,
low --> Starting index,
high --> Ending index */
void quickSort(int arr[], int low, int high) {
  if (low < high) {
    /* pi is partitioning index, arr[p] is now
    at right place */
    int pi = partition(arr, low, high);

    // Separately sort elements before
    // partition and after partition
    quickSort(arr, low, pi - 1);
    quickSort(arr, pi + 1, high);
  }
}

/* Function to print an array */
void printArray(int arr[], int size) {
  int i;
  for (i = 0; i < size; i++) {
    cout << arr[i] << " ";
  }
  cout << endl;
}

int printRectangleMxN() {
  //print out a rectangle of size m x n
  int i, j, m, n;

  printf("\nEnter m: ");
  scanf("%d", & m);

  printf("\nEnter n: ");
  scanf("%d", & n);

  for (i = 1; i <= m; ++i) {
    for (j = 1; j <= n; ++j) {
      printf("*");
    }
    printf("\n");
  }

  printf("\n");

  for (i = 1; i <= m; ++i) {
    for (j = 1; j <= n; ++j) {
      if (i == 1 || i == m || j == 1 || j == n) {
        printf("*");
        if (j == n) {
          printf("\n");
        }
      } else {
        printf(" ");
      }
    }
  }
  return 0;
}

// Recursive function to sort an array using
// insertion sort
void insertionSortRecursive(int arr[], int n) {
  // Base case
  if (n <= 1) {
    return;
  }

  // Sort first n-1 elements
  insertionSortRecursive(arr, n - 1);

  // Insert last element at its correct position
  // in sorted array.
  int last = arr[n - 1];
  int j = n - 2;

  /* Move elements of arr[0..i-1], that are
  greater than key, to one position ahead
  of their current position */
  while (j >= 0 && arr[j] > last) {
    arr[j + 1] = arr[j];
    j--;
  }
  arr[j + 1] = last;
}

void remove1(int a[], int & n, int LocalRemove) {
  for (int i = LocalRemove; i < n; i++) {
    a[i] = a[i + 1];
  }
  n--;
}

void removeRepeatedElements(int a[], int & n) {
  //Remove all repeated elements in the array
  for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
      if (a[i] == a[j]) {
        remove1(a, n, j);
        j--;
      }
    }
  }
}

void reverseStr(string & str) {
  // Reverse a string program
  int n = str.length();

  // Swap character starting from two
  // corners
  for (int i = 0; i < n / 2; i++) {
    swap(str[i], str[n - i - 1]);
  }
}

int ReverseNumber() {
  //Find the reverse number of the positive integer n
  int n;

  do {
    printf("\nEnter n: ");
    scanf("%d", & n);
  } while (n < 0 && printf("\nError: (n >= 0)"));

  printf("\nThe reverse number of %d is: ", n);
  do {
    printf("%d", n % 10);
  } while (n /= 10); // n = n / 10; n != 0;

  return 0;
}

string reverseString(string str) {
  //saves a copy of the string and the index we are on
  string temp = str;
  int index = 0;

  //loops though the string
  for (int x = temp.length() - 1; x >= 0; x--) {
    //sets the value of the return to its inverse value
    str[index] = temp[x];
    index++;
  }
  return str;
}

int smallestNumber() {
  //Find the smallest positive integer n such that 1 + 2 + … + n > X
  int x;
  do {
    printf("\nEnter X: ");
    scanf("%d", & x);
  } while (x < 0 && printf("\nError: x >= 0 !"));

  int S = 0;
  int n = 0;
  while (S < x) {
    n++;
    S = S + n;
  }
  printf("\nSum is %d", S);
  printf("\n%d is the smallest positive integer such that 1 + 2 + ...+ %d = %d > %d", n, n, S, x);
  return 0;
}

int solveDegreeEquation() {
  //solve degree equation. ax^2 + bx + c = 0
  float a, b, c; // .
  float x, y;

  printf("\nEnter a = ");
  scanf("%f", & a);

  printf("\nEnter b = ");
  scanf("%f", & b);

  printf("\nEnter c = ");
  scanf("%f", & c);

  if (a == 0) // form: bx + c = 0
  {
    if (b == 0) // form: c = 0
    {
      if (c == 0) {
        printf("\nCountless Solutions");
      } else {
        printf("\nNo Solution");
      }
    } else {

      x = y = -c / b;

      printf("\n x1 = x2 = %f", x);
    }
  } else {
    float Delta = b * b - 4 * a * c;

    if (Delta < 0) {
      printf("\nNo Solution");
    } else if (Delta == 0) {
      x = y = -b / (2 * a);

      printf("\n x1 = x2 = %f", x);
    } else // Delta > 0
    {
      x = (-b + sqrt(Delta)) / (2 * a);
      y = (-b - sqrt(Delta)) / (2 * a);

      printf("\nx1 = %f\nx2 = %f", x, y);

    }
  }
  return 0;
}

const int NoSolution = 0,
  Unknown = -1;
int solveSimultaneousEquations(int a, int b, int c, int d, int e, int f, int & x, int & y) {
  //solve the Simultaneous Equations: ax + by = c, Dx + ey = f. The coefficients entered from the keyboard
  int D = a * e - d * b;
  int Dx = c * e - f * b;
  int Dy = a * f - d * c;

  int Solutions;
  if (D != 0) {
    x = Dx / D;
    y = Dy / D;
    printf("\nThe Simultaneous Equations just have 1 solution: %d and %d", x, y);
    Solutions = 1;
  } else {

    if (Dx == Dy == 0) {
      printf("\nThe Simultaneous Equations are correct with any solution");
      Solutions = -1;
    } else {
      printf("\n The Simultaneous Equations have no solution");
      Solutions = 0;
    }
  }
  return Solutions;
}

int firstDegreeEquation() {
  //Find the solution of the equation ax + b = 0
  float a, b;
  float x;
  printf("\nThe equation ax + b = 0 ");
  printf("\nPlease Enter a: ");
  scanf("%f", & a);
  printf("\nPlease Enter b: ");
  scanf("%f", & b);

  if (a == 0) {
    if (b == 0) {
      printf("\nThe infinite equation");
    } else {
      printf("\nThe equation has no solution");
    }
  } else {
    x = -b / a;
    printf("\nx = %.3f", x);
  }
  return 0;
}

float sumOfAllNegativeNums(float a[], int n) {
  //sum negative values in a 1-dimensional array of real numbers
  float s = 0;
  for (int i = 0; i < n; i++) {
    if (a[i] < 0) {
      s = s + a[i];
    }
  }
  return s;
}

int sumOfAllExtremes(int a[], int n) {
  //Sum the "extreme" elements in the array. 
  //An element is said to be extreme when it is larger or smaller than its surrounding elements.
  int Sum = 0;
  for (int i = 0; i < n; i++) {
    if (i == 0 && a[i] != a[i + 1]) // check the previous number
    {
      Sum += a[i];
    } else if (i == n - 1 && a[i] != a[i - 1]) // check the following number
    {
      Sum += a[i];
    } else if ((a[i] < a[i + 1] && a[i] < a[i - 1]) || (a[i] > a[i + 1] && a[i] > a[i - 1])) {
      Sum += a[i];
    }
  }
  return Sum;
}

float sumOfRow(float a[][MAX], int x, int Col) {
  //Sum values on 1 row in matrix of real numbers
  float Sum = 0;
  for (int j = 0; j < Col; j++) {
    Sum += a[x][j];
  }
  return Sum;
}
int sumOfARowInMatrix() {
  //Sum values on 1 row in matrix of real numbers
  float a[MAX][MAX];
  int Row, Col;
  input2(a, Row, Col);
  output2(a, Row, Col);

  int x;
  do {
    printf("\nEnter the row you want to know the sum: ");
    scanf("%d", & x);

    if (x < 0 || x > Row - 1) {
      printf("\nInvalid number. Please check again !");
    }
  } while (x < 0 || x > Row - 1);

  float Sum = sumOfRow(a, x, Row);
  printf("\nThe sum of row %d = %f", x, Sum);

  return 0;
}

bool prc(int a) {
  for (int y = 1; y < a; y++) {
    int x = a / (y + 1) - y / 2;
    if ((y + 1) * (x + (float) y / 2) == a) {
      cout << a << " = ";
      for (int j = x; j < x + y; j++) {
        cout << j << " + ";
      }
      cout << x + y << endl;
      return 1;
    }
  }
  return 0;
}

int sumOfConsecutivePositiveIntegers() {
  //Given a number N. Express N as the sum of at least 2 consecutive positive integers. Eg
  //10 = 1 + 2 + 3 + 4
  //24 = 7 + 8 + 9
  //If there are multiple answers, print the answer with the least number of elements. If there is no answer, print 1 line "IMPOSSIBLE"
  int A = 1;
  while (A--) {
    int n;
    cout << "Enter N: ";
    cin >> n;
    if (n < 0) {
      cout << "Invalid number. Please check again !";
      break;
    } else {
      if (!prc(n)) {
        cout << "IMPOSSIBLE" << endl;
      }
    }
  }

  return 0;
}

int theSum(int a, int b) {
  int s = 0;
  for (int i = a; i <= b; i++) {
    s = s + i;
  }
  return s;
}
void print(int a, int b) {
  for (int i = a; i <= b; i++) {
    cout << i << " ";
    if (i < b) {
      cout << "+ ";
    }

  }
}
int sumOfConsecutivePositiveIntegers() {
  //Given a number N. Express N as the sum of at least 2 consecutive positive integers. Eg
  //10 = 1 + 2 + 3 + 4
  //24 = 7 + 8 + 9
  //If there are multiple answers, print the answer with the least number of elements. If there is no answer, print 1 line "IMPOSSIBLE"
  int n;
  cout << " Enter N: ";
  cin >> n;
  int sum = 0;
  cout << n << " = ";
  for (int i = 1; i <= int(n / 2); i++) {
    for (int j = i + 1; j <= int(n / 2) + 1; j++) {
      if (TheSum(i, j) == n) {
        print(i, j);
        cout << "\n";
        break;
      } else {
        sum++;
      }
    }
  }
  if (sum == TheSum(1, int(n / 2))) {
    cout << "IMPOSSIBLE" << endl;
  }
  return 0;
}

int sumOfDivisors() {
  //Sum all the divisors of a positive integer n
  int i, n;
  long S;
  do {
    printf("\nEnter n(n > 0): ");
    scanf("%d", & n);
    if (n <= 0) {
      printf("\n Error,N <= 0. !");
    }
  } while (n <= 0);
  i = 1;
  S = 0;
  while (i <= n) {
    if (n % i == 0) {
      printf("%4d", i);
      S = S + i;
    }
    i++;
  }
  printf("\nThe Sum of all divisors of %d is: %ld", n, S);
  return 0;

}

int findTensIs5(int n) {
  //Sum of all numbers with tens digit is 5 in array of integers
  n = abs(n);
  n = n / 10;
  int Tens = n % 10;

  if (Tens == 5) {
    return 1;
  }
  return 0;
}

int sumOfTensIs5(int a[], int n) {
  //Sum of all numbers with tens digit is 5 in array of integers
  int Sum = 0;
  for (int i = 0; i < n; i++) {
    if (FindTensIs5(a[i]) == 1) {
      Sum += a[i];
    }
  }
  return Sum;
}

int sumOfTheComponents() {
  //Find the sum of the numbers that are the components of the positive integer n
  int x, n;
  int S;

  do {
    printf("\nEnter n: ");
    scanf("%d", & n);
  } while (n < 0 && printf("\nError: n >= 0 !"));
  S = 0;
  x = n;

  while (x != 0) {
    S = S + x % 10;
    x = x / 10;
  }
  printf("\nThe sum is %d", S);

  getch();
  return 0;
}

int sumOfSeries(int n) {
  //The sum of the series S(n) = 1 + 2 + 3 + 4 +...+ n
  int i;
  long S;
  S = 0;
  i = 1;

  while (i <= n) {
    S = S + i;
    i++;
  }

  return S;
}

int sumOfNSquaresSeries(int n) {
  //Sum of Squares Formula for n numbers. the sum of the series S(n) = 1 + 2^2 + 3^2 +....+ n^2
  int i;
  long S;
  S = 0;
  i = 1;

  while (i <= n) {
    S = S + i * i;
    i++;
  }

  return S;
}

float sumOfFisrtNSeries(int n) {
  //Sum of the First n Terms of a Series. The sum of the series S(n) = 1 + 1/2 + ... + 1/n
  int i;
  float S;
  S = 0;
  i = 1;
  do {

    if (n < 1) {
      printf("\nThe number have to > or = 1 !");
    }

  } while (n < 1);

  while (i <= n) {
    S = S + 1.0 / i; // remember 1.0 / i
    i++;
  }
  return S;
}

int sumOfNPowerOfX(float x, int n) {
  //The sum on the series X to power of n. The sum S(n) = x + x^2 + x^3 + ... + x^n
  int i;
  float T, S;
  i = 1;
  T = 1;
  S = 0;

  while (i <= n) {
    T = T * x;
    S = S + T;
    i++;
  }

  return S;
}

int find2ndMax(int a[][MAX], int Row, int Col) {
  //Find the 2nd largest value in a matrix
  int Max = a[0][0];
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Col; j++) {
      Max = (a[i][j] > Max) ? a[i][j] : Max;
    }
  }
  int Max2 = a[0][0];
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Col; j++) {
      if (Max2 < Max && a[i][j] != Max && Max2 < a[i][j]) {
        Max2 = a[i][j];
      }
    }
  }
  return Max2;
}
int the2ndMaxInMatrix() {
  //Find the 2nd largest value in a matrix
  int a[MAX][MAX], Row, Col;
  input2(a, Row, Col);
  output2(a, Row, Col);
  int max = Find2ndMax(a, Row, Col);
  printf("\nThe 2nd largest value in the matrix is %d", max);

  return 0;
}

int TheLargestNumber() {
  //Find the largest positive integer m such that 1 + 2 + … + m < N
  int N, m, S;
  printf("\nEnter N: ");
  scanf("%d", & N);

  S = 0;
  m = 0;
  do {
    m = m + 1;
    S = S + m;
  } while (S + m + 1 < N);
  printf("The sum is : %d", S);
  printf("\nThe largest positive integer m is %d such that 1 + 2 + .... + %d = %d < %d ", m, m, S, N);
  return 0;
}

void in (float a[], int & n) {
  do {
    printf("\nHow many numbers in the array? ");
    scanf("%d", & n);
    if (n <= 0 || n > MAX) {
      printf("\nInvalid element numbers. Please check again !");
    }
  } while (n <= 0 || n > MAX);
  for (int i = 0; i < n; i++) {
    printf("\nEnter a[%d]: ", i);
    scanf("%f", & a[i]);
  }
}

float largest(float a[], int n) {
  //The largest number in an array
  float ln = a[0];
  for (int i = 0; i < n; i++) {
    if (a[i] > ln) {
      ln = a[i];
    }
  }
  return ln;
}
void out(float a[], int n) {
  for (int i = 0; i < n; i++) {
    printf("%8.3f", a[i]);
  }
}

int largestInArray() {
  //The largest number in an array
  int n;
  float a[MAX]; in (a, n);
  out(a, n);

  float ln = largest(a, n);

  printf("\nThe Largest Number in the Array %.3f", ln);
  return 0;
}

int theLargestOddDivisor() {
  //Find the largest odd divisor of a positive integer n. For example, for n = 100, the largest odd divisor is 25
  int i, n, max;
  do {
    printf("\nEnter n(n > 0): ");
    scanf("%d", & n);
    if (n <= 0) {
      printf("\n N > 0. please, Inter another number !");
    }
  } while (n <= 0);
  i = 1;
  max = 1;
  printf("\nOdd divisors of %d are: ", n);
  while (i <= n) {
    if ((n % i == 0) && (i % 2 == 1)) {
      if (i > max) {
        max = i;
      }
      printf("%4d", i);
    }
    i++;
  }
  printf("\n\nThe largest odd divisor is %d", max);
  return 0;
}

void swap2Columns(int a[][MAX], int Row, int Column, int Column1, int Column2) {
  // Swap 2 Columns in a matrix 
  if ((Column1 >= 0 && Column1 < Column) && (Column2 >= 0 && Column2 < Column)) {
    for (int i = 0; i < Row; i++) {
      swap(a[i][Column1], a[i][Column2]);
    }
  }
}
int theMatrixAfterSwap2Columns() {
  // Swap 2 Columns in a matrix 
  int a[MAX][MAX], Row, Column;
  int Column1, Column2;
  input2(a, Row, Column);
  output2(a, Row, Column);

  do {
    printf("\nEnter the first column you want to swap: ");
    scanf("%d", & Column1);

    if (Column1 < 0 || Column1 >= Column) {
      printf("\nInvalid number. Please check again !");
    }
  } while (Column1 < 0 || Column1 >= Column);

  do {
    printf("\nEnter the second column you want to swap: ");
    scanf("%d", & Column2);

    if (Column2 < 0 || Column2 >= Column) {
      printf("\nInvalid number. Please check again !");
    }
  } while (Column2 < 0 || Column2 >= Column);

  swap2Columns(a, Row, Column, Column1, Column2);

  printf("\nThe Matrix after Swaping Column %d and Column %d is: \n", Column1, Column2);
  output2(a, Row, Column);
  return 0;
}

void swap2Row(int a[][MAX], int Row, int Column, int Row1, int Row2) {
  //Swap 2 rows in a matrix
  if ((Row1 >= 0 && Row1 < Row) && (Row2 >= 0 && Row2 < Row)) {
    for (int j = 0; j < Column; j++) {
      swap(a[Row1][j], a[Row2][j]);
    }
  }
}
int theMatrixAfterSwap2Rows() {
  //Swap 2 rows in a matrix
  int a[MAX][MAX], Row, Column;
  int Row1, Row2;
  input2(a, Row, Column);
  output2(a, Row, Column);

  do {
    printf("\nEnter the first row you want to swap: ");
    scanf("%d", & Row1);

    if (Row1 < 0 || Row1 >= Row) {
      printf("\nInvalid number. Please check again !");
    }
  } while (Row1 < 0 || Row1 >= Row);

  do {
    printf("\nEnter the second row you want to swap: ");
    scanf("%d", & Row2);

    if (Row2 < 0 || Row2 >= Row) {
      printf("\nInvalid number. Please check again !");
    }
  } while (Row2 < 0 || Row2 >= Row);

  swap2Row(a, Row, Column, Row1, Row2);

  printf("\nThe Matrix after Swaping Row %d and Row %d is: \n", Row1, Row2);
  output2(a, Row, Column);
  return 0;
}

void findTheMax(int a[][100], int Row, int Col) {
  //Find the Max on a Row
  for (int i = 0; i < Row; i++) {
    int Max = a[i][0];
    for (int j = 0; j < Col; j++) {
      Max = (Max > a[i][j]) ? Max : a[i][j];
    }
    printf("\nRow %d: ", i);
    printf("The Max is %d", Max);
  }
}
int theMaxInEachRow() {
  //Find the Max on a Row
  int a[MAX][MAX], Row, Col;
  input2(a, Row, Col);
  output2(a, Row, Col);
  findTheMax(a, Row, Col);
  return 0;
}

void findTheMin(int a[][100], int Row, int Col) {
  //Find the Min value in a column
  for (int i = 0; i < Col; i++) {
    int Min = a[0][i];
    for (int j = 0; j < Row; j++) {
      Min = (Min < a[j][i]) ? Min : a[j][i];
    }
    printf("\nColumn %d: ", i);
    printf("The min is %d", Min);
  }
}

int theMinInEachColumn() {
  //Find the Min value in a column
  int a[MAX][MAX], Row, Col;
  input2(a, Row, Col);
  output2(a, Row, Col);
  findTheMin(a, Row, Col);
  return 0;
}

int findMax(int a[][MAX], int Col, int Row) {
  //List Rows of matrix contain the max
  int Max = a[0][0];
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Col; j++) {
      Max = (Max > a[i][j]) ? Max : a[i][j];
    }
  }
  return Max;
}

void rowsHaveMax(int a[][MAX], int Row, int Col) {
  //List Rows of matrix contain the max
  int Max = FindMax(a, Row, Col);
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Col; j++) {
      if (a[i][j] == Max) {
        printf("\nRow(s) [%d] have/has max: ", i);
        for (j = 0; j < Col; j++) {
          printf("%4d", a[i][j]);
        }
      }
    }
  }
}
int theRowHasMaxInMatrix() {
  //List Rows of matrix contain the max
  int a[MAX][MAX], Row, Col;
  input2(a, Row, Col);
  output2(a, Row, Col);
  rowsHaveMax(a, Row, Col);

  return 0;
}

void sumOfMatrices(int a[][MAX], int b[][MAX], int c[][MAX], int Row, int Col) {
  //SumOfMatrices of 2 matrices
  for (int i = 0; i < Row; i++) {
    for (int j = 0; j < Col; j++) {
      c[i][j] = a[i][j] + b[i][j];
    }
  }
}
int theSumOf2Matrices() {
  //SumOfMatrices of 2 matrices
  int a[MAX][MAX], b[MAX][MAX], c[MAX][MAX], Row, Col;
  //Row
  do {
    printf("\nHow many rows?  ");

    scanf("%d", & Row);

    if (Row < 1 || Row > MAX) {
      printf("\nInvalid number. Please check again !");
    }

  } while (Row < 1 || Row > MAX);

  //Column
  do {
    printf("\nHow many columns? ");
    scanf("%d", & Col);

    if (Col < 1 || Col > MAX) {
      printf("\nInvalid number. Please check again !");

    }

  } while (Col < 1 || Col > MAX);
  printf("\nEnter Matrix A: \n");
  input2(a, Row, Col);

  printf("\nEnter Matrix B: \n");
  input2(b, Row, Col);

  printf("\nMatrix A: \n");
  output2(a, Row, Col);

  printf("\nMatrix B: \n");
  output2(b, Row, Col);

  sumOfMatrices(a, b, c, Row, Col);
  printf("\nMatrix C = A + B \n");
  output2(c, Row, Col);

  return 0;
}

void toLowerCase(string str) {
  for (int i = 0; str[i] != '\0'; i++) {
    if (str[i] >= 'A' && str[i] <= 'Z') { //checking for uppercase characters
      str[i] = str[i] + 32; //converting uppercase to lowercase
    }
  }
  cout << "\n The string in lower case: " << str;
}

void toUpperCase(string str) {
  for (int i = 0; str[i] != '\0'; i++) {
    if (str[i] >= 'a' && str[i] <= 'z') { //checking for lowercase characters
      str[i] = str[i] - 32; //converting lowercase to uppercase  
    }
  }
  cout << "\n The string in upper case: " << str;
}

void valuesAppearOneTimeInTwoArrays(int a[], int b[], int na, int nb) {
  //Given 2 arrays a, b. List values that appear in only 1 of 2 arrays
  int i, j, flag;
  for (i = 0; i < na; i++) {
    flag = 1;
    for (j = 0; j < nb; j++) {
      if (a[i] == b[j]) {
        flag = 0;
        break;
      }
    }
    if (flag == 1) {
      printf("\n%d", a[i]);
    }
  }
  for (i = 0; i < nb; i++) {
    flag = 1;
    for (j = 0; j < na; j++) {
      if (b[i] == a[j]) {
        flag = 0;
        break;
      }
    }
    if (flag == 1) {
      printf("\n%d", b[i]);
    }
  }
}

int main() {
  return 0;
}