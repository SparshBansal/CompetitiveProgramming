#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 300001

struct el
{
    lli val;
    int l, r;
};

bool compare(el l, el r) { return l.r < r.r; }

int main()
{
    int n;
    lli* ar = new lli[MAXL];
    el* dp = new el[MAXL];

    cin >> n;

    for (int i = 0; i < n; i++)
        cin >> ar[i];

    dp[0].l = 0, dp[0].r = 0, dp[0].val = ar[0];

    for (int i = 1; i < n; i++)
    {
        if (dp[i-1].val + ar[i] > ar[i])
            dp[i].l = dp[i-1].l, dp[i].r = i, dp[i].val = dp[i-1].val + ar[i];
        else 
            dp[i].l = i, dp[i].r = i, dp[i].val = ar[i];
    }

    lli ans = LLONG_MIN;
    for (int i = 0; i < n; i++)
        ans = max(ans, dp[i].val);

    if (ans < 0)
    {
        cout << 0 << " " << 0 << endl;
        return 0;
    }

    // check for multiple ans arrays
    el *anss = new el[MAXL];
    int sz = 0;

    for (int i=0; i<n; i++)
        if (dp[i].val == ans)
            anss[sz++] = dp[i];

    // now we use activity selection
    sort(anss, anss+sz, compare);

    int dis = 1;
    int prev = anss[0].r;

    for (int i=1; i<sz; i++)
        if (anss[i].l > prev) dis++, prev = anss[i].r;

    cout << ans << " " << dis << endl;
    return 0;
}