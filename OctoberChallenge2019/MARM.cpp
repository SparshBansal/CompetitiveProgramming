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
        lli n=0;
        lli k=0;

        // input
        cin >> n >> k;
        lli ar[MAXL];

        // input array
        for (lli i=0; i<n; i++) cin >> ar[i];

        lli eff = k%(3*n);

        if (n%2==1 && k > (n/2))
            // set middle element to zero
            ar[n/2] = 0;
        
        for (lli i=0; i<eff; i++)
            ar[i%n] = ar[i%n] ^ ar[n-1-(i%n)];

        for (lli i=0; i<n; i++)
            cout << ar[i] << " ";
        cout << endl;
    }
}