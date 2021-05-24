#include<iostream>

using namespace std;

int main()
{
    char ar[5];
    int a=1, b=2;

    ar[0] = 0;
    ar[0] = a<<4;
    ar[0] = ar[0] | b;

    cout << (int)ar[0] << endl;
    return 0;
}   