#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 100001

lli bin_search(lli ar[], lli n, lli k, lli l, lli r)
{
    // handle base case (back of the queue)
    if (l == r && l == n+1)
        return n+1;

    lli m = l + (r-l)/2;
    lli m_val = ar[m];

    // assume chef to be standing in front of mth person
    lli anger_r = 0, anger_l=0;
    for (lli i=m,j=1; i<n; i++, j++) anger_r += (ar[i]/j);

    if (m - 1 < 0) anger_l = LLONG_MAX;
    else for (lli i=m-1,j=1; i<n; i++, j++) anger_l += (ar[i]/j);

    if (anger_r <= k && anger_l > k)return m;
    else if (anger_r > k) return bin_search(ar, n, k, m+1, r);
    else return bin_search(ar, n, k, l, m-1);
}

int main()
{
    lli t=0;
    cin >> t;

    while(t--)
    {
        lli n=0;
        lli k=0;
        cin >> n >> k;

        lli ar[MAXL];
        for (lli i=0; i<n; i++) cin>>ar[i];
        cout << bin_search(ar, n, k, 0, n+1) + 1 << endl;
    }
}