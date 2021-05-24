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
    lli t=0;
    cin >> t;

    while(t--)
    {
        lli n=0, m=0, q=0;
        cin >> n >> m >> q;

        lli row[n], col[m];
        fill(row, row+n, 0);
        fill(col, col+m, 0);
        for (lli i=0; i<q; i++)
        {
            lli x=0, y=0;
            cin >> x >> y;

            row[x-1]++, col[y-1]++;
        }

        lli cnt=0;
        lli num_col_odd=0, num_col_even=0;

        for (lli i=0; i<m; i++) 
            if (col[i]%2 == 0) num_col_even++;
            else num_col_odd++;
        
        for (lli i=0; i<n; i++)
        {
            if (row[i]%2 == 0) cnt += num_col_odd;
            else cnt += num_col_even;
        }

        cout << cnt << endl;
    }
    return 0;
}