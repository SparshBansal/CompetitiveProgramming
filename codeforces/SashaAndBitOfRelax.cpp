#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int ll;

#define MAXL 300001

void putOrIncrement(map<ll, int> &m, ll k)
{
    if (m.find(k)!=m.end()) {
        m[k] = m[k]+1;
    }
    else {
        m.insert(make_pair(k, 1));
    }
}

int main()
{
    int n=0;
    ll ar[MAXL];
    cin >> n;

    for (int i=0; i<n; i++)
        cin>> ar[i];

    // create 2 prefix arrays
    map<ll, int> m1, m2;
    m1.insert(make_pair(0, 1));
    m2.insert(make_pair(ar[0], 1));
    ll prev = ar[0];
    ll ans =0;
    // iterate over array
    for (int i=1; i<n; i++)
    {
        prev = prev ^ ar[i];
        if (i%2) {
            if (m1.find(prev) != m1.end()) {
                ans += m1[prev];
            }
            putOrIncrement(m1, prev);
        }
        else 
        {
            if (m2.find(prev) != m2.end()) {
                ans += m2[prev];
            }
            putOrIncrement(m2, prev);
        }       
    }
    cout << ans << endl;
}