#include <iostream>
#include <cstdio>
using namespace std;

int main()
{
    char ar[1000];
    gets(ar);

    int s=-1, e=-1;

    for (int i=0; i<strlen(ar); i++) 
    {
        if (ar[i] == ' ') 
        {
            if (e == -1)
                s = i, e = i;
            else
                e++;
        }
        else
        {
            if (s != -1) 
            {
                ar[s] = ar[i];
                ar[i] = ' ';
                s++, e++;
            }
        }
    }

    for (int i=0; i < strlen(ar); i++) cout << ar[i];
}