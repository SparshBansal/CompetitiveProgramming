#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 100001

int main()
{
    int t=0;
    cin >> t;


    while(t--)
    {
        string ar;
        cin >> ar;

        // construct KMP pre array
        int f[MAXL];

        f[0] = 0;

        for (int i=1; i<ar.length(); i++)
        {
            int j = f[i-1];

            while (j > 0 && ar[j] != ar[i])
                j = f[j-1];
            if (ar[i] == ar[j]) ++j;
            f[i] = j;
        }

        // vector<int> v;
        // int m = f[0];
        // for (int i=1; i<ar.length(); i++)
        // {
        //     if (f[i] == f[i-1] + 1)
        //     {
        //         m++;
        //     }
        //     else if (m != 0) 
        //     {
        //         v.push_back(m);
        //         m = f[i];
        //     }
        // }

        // if (m  > 0) 
        // {
        //     v.push_back(m);
        // }
        
        // for (int i=0; i<v.size(); i++)
        //     cout << v[i] << " ";
        // cout << endl;

        

    }

}