#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
#include <set>

using namespace std;

typedef long long int lli;

int main()
{
    int n=0, m=0;
    cin >> n >> m;

    int ar[n][m];

    for (int i=0; i<n; i++)
        for (int j=0; j<m; j++)
            cin >> ar[i][j];

    // construct kadane
    int dp[n][m];

    for (int j=0; j<m; j++)
    {
        dp[0][j] = 1;
        for(int i=1; i<n; i++)
            if (ar[i][j] >= ar[i-1][j])
                dp[i][j] = dp[i-1][j]+1;
            else 
                dp[i][j] = 1;
    }

    // make sets for each row
    set<int> csets[n];

    for (int i=0; i<n; i++)
        for (int j=0; j<m; j++)
            csets[i].insert(dp[i][j]);

    int q=0;
    cin >> q;
    // input queries
    for (int z=0; z<q; ++z)
    {
        int l, r;
        cin >> l >> r;

        l--, r--;
        int len = r - l;

        // check the set for r
        if (csets[r].upper_bound(len) != csets[r].end())
            cout << "Yes" << endl;
        else
            cout << "No" << endl;
    }
}