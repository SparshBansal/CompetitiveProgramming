#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 1001

lli solveIt(int l, int r, lli ar[], lli hs=0)
{
    // base case
    if (l == r) 
    {
        // find the solution
        if (ar[l] == hs) 
            return 0;
        else
            return 1;
    }
    

    // find the min
    lli mini = LLONG_MAX, idx=0;
    for (int i=l; i<=r; i++) 
    {
        if (mini > ar[i]-hs) {
            mini = ( ar[i] - hs);
            idx = i;
        }
    }

    // now we split across mini
    lli ans_1= 0, ans_2=0;
    // 1. we fill this plank vertically
    if (l <= idx-1) ans_1 += solveIt(l, idx-1, ar, hs);
    if (idx+1 <=r) ans_1 += solveIt(idx+1, r, ar, hs);
    if (mini > 0) ans_1++;

    // 2. we can fill this plank horizontally
    if (l <= idx-1) ans_2 += solveIt(l, idx-1, ar, hs+mini);
    if (idx+1 <= r) ans_2 += solveIt(idx+1, r, ar, hs+mini);
    ans_2 += mini;

    // find the minimum
    return min(ans_1, ans_2);
}

int main()
{
    int n=0;
    cin >> n;

    lli ar[MAXL];

    for (int i=0; i<n; i++)
        cin >> ar[i];

    lli ans = solveIt(0, n-1, ar);

    cout << ans << endl;
}