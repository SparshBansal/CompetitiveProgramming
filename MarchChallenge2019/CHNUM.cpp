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
    int t = 0;
    cin >> t;

    while (t--)
    {
        int n = 0, ar[MAXL];
        cin >> n;
        for (int i = 0; i < n;)
            cin >> ar[i++];

        int neg = 0, pos = 0, zero = 0;

        for (int i = 0; i < n; i++)
            if (ar[i] > 0) pos++;
            else if (ar[i] < 0) neg++;
            else zero++;

        // max group size
        int max_grp_sz = max(pos, neg) + zero;  
        int min_grp_sz = 0;
        if (pos == 0 || neg == 0) min_grp_sz = max(pos, neg);
        else min_grp_sz = min(pos, neg);

        cout << max_grp_sz << " " << min_grp_sz << endl;
    }
}