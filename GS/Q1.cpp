#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <set>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 100001

void update(int BIT[], int n, int x, int delta)
{
      for(; x <= n; x += x&-x)
        BIT[x] += delta;
}

int query(int BIT[], int x)
{
     int sum = 0;
     for(; x > 0; x -= x&-x)
        sum += BIT[x];
     return sum;
}
 
int func(int n, int ar[])
{
    int BIT_L[n+2], BIT_R[n+2];

    fill(BIT_L, BIT_L + n + 2, 0);
    fill(BIT_R, BIT_R + n + 2, 0);

    map<int, int> ccm;

    int aux[n];
    for (int i=0; i<n; i++) 
        aux[i] = ar[i];

    sort(aux, aux + n);

    for (int i=0; i<n; i++) 
        ccm.insert(make_pair(aux[i], i+1));

    for (int i=n-1; i >= 1; i--) 
        update(BIT_R, n+1, ccm[ar[i]], 1);

    int sum = 0;

    for (int i=1; i< n-1; i++)
    {
        int cc = ccm[ar[i]];

        update(BIT_L, n+1, ccm[ar[i-1]], 1);
        update(BIT_R, n+1, cc, -1);

        int lc = query(BIT_L, cc-1);
        int rc = query(BIT_R, n) - query(BIT_R, cc);

        sum += (lc * rc);

        lc = query(BIT_L, n) - query(BIT_L, cc);
        rc = query(BIT_R, cc-1);

        sum += (lc * rc);
    }
    return sum;
}

int main()
{
    int ar[] = {12, 8, 11, 13, 10, 15, 14, 16, 20};
    cout << func(9, ar);
}