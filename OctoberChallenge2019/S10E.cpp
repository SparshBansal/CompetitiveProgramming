#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 101

int main()
{
    int t=0;
    cin >> t;

    while(t--)
    {
        int n=0, ar[MAXL];
        cin >> n;

        for (int i=0; i<n; i++) cin >> ar[i];

        int num_days = 0;

        for (int i=0; i<n; i++)
        {
            bool smallest = true;
            for (int j=i-1; j>=0 && i-j <=5; j--)
                if (ar[j] <= ar[i]) smallest = false;
            
            if (smallest) num_days++;
        }
        cout << num_days << endl;
    }
}   