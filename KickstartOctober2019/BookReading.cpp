#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
#include <set>

using namespace std;

typedef long long int lli;

#define MAXL 100001

int main()
{
    int t=0;
    cin >> t;

    for (int casev=1; casev <=t; casev++)
    {
        set<int> mset;
        int n=0, m=0, q=0;
        cin >> n >> m >> q;

        for (int i=0; i<m; i++)
        {
            int el = 0;
            cin >> el;

            // insert in set
            mset.insert(el);
        }

        // for each value between 1 to MAXL
        int counts[MAXL];
        fill(counts, counts+MAXL, 0);

        for (int i=1; i<=n; i++)
            for (int j=1; i*j <= n; j++)
                // found in set 
                if(mset.find(i*j)!=mset.end()) {
                    counts[i]++;
                }

        // now we know multiples for every number torn out
        lli sum = 0;
        for (int i=0; i<q; i++)
        {
            int r=0;
            cin >> r;

            int totalPages = n/r;
            int tornPages = counts[r];
            int readPages = totalPages - tornPages;

            sum += readPages;
        }

        cout << "Case #" << casev << ": " << sum << endl;
    }
}