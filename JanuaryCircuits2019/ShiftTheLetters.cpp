#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 2049

int main()
{
    string s;
    cin >> s;

    int k=0;
    cin >> k;

    int pre[s.length()][26];
    int lookup[s.length()][s.length()];

    for (int i=0; i<s.length(); i++)
    {
        for (int j=0; j<26; j++)
        {
            char val = 'a' + j;
            int diff = val - s[i];
            if (diff < 0) diff += 26;

            pre[i][j] = diff;
        }
    }

    // precompute
    for (int i=1; i<s.length(); i++)
        for (int j=0; j<26; j++)
            pre[i][j] += pre[i-1][j];

    // populate lookup table
    for (int i=0; i<s.length(); i++)
    {
        for (int j=i; j<s.length(); j++)
        {
            // populate lookup[i][j]
            lookup[i][j] = INT_MAX;
            for (int l=0; l<26; l++)
            {
                int right = pre[j][l];
                int left = (i == 0 ? 0 : pre[i-1][l]);
                lookup[i][j] = min(lookup[i][j], right - left);
            }
        }
    }

    // populate dp table
    int dp[s.length()][k+1];

    for (int i=0; i<=k; i++) dp[0][i] = 0;
    for (int i=0; i<s.length(); i++) dp[i][0] = lookup[0][i];

    for (int i=1; i<s.length(); i++)
    {
        for (int j=1; j<=k; j++)
        {
            if (j >= i) 
            {
                dp[i][j] = 0;
                continue;
            }
            // computing dp[i][j] = ?
            int ans = INT_MAX;
            for (int l=i ; l >=0; l--)
            {
                int prev = (l - 1 < 0 ? 0 : dp[l-1][j-1]);
                int curr = lookup[l][i];
                ans = min(ans, prev+curr);
            }
            dp[i][j] = ans;
        }
    }

    cout << dp[s.length()-1][k];

    return 0;
}