#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 101

int main()
{
    int t = 0;
    cin >> t;

    while (t--)
    {
        int n;
        cin >> n;

        int a[n], d[n];
        for (int i = 0; i < n; i++)
            cin >> a[i];
        for (int i = 0; i < n; i++)
            cin >> d[i];

        int ans = -1;
        for (int i = 1; i < n - 1; i++)
            if (d[i] > a[i - 1] + a[i + 1])
                ans = max(ans, d[i]);

        if (d[0] > a[1] + a[n-1]) ans = max(ans, d[0]);
        if (d[n-1] > a[0] + a[n-2]) ans = max(ans, d[n-1]);

        cout << ans << endl;
    }
}