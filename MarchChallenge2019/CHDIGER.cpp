#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

lli find_min(lli num, int d)
{
    int ar[20], sz = 0;
    lli tmp = num;

    // store digits in an array
    while (tmp != 0)
    {
        ar[sz++] = tmp % 10;
        tmp /= 10;
    }

    // reverse the array
    reverse(ar, ar + sz);

    // now find most significant digit which can be reduced
    bool isMin = true;
    do
    {
        isMin = true;
        int idx = -1;
        for (int i = 0; i < sz - 1; i++)
            if (ar[i] > ar[i + 1])
                isMin = false, idx = i;

        if (isMin)
            if (ar[sz - 1] > d)
                isMin = false, idx = sz - 1;

        if (!isMin)
        {
            // shift letters
            for (int i = idx; i < sz - 1; i++)
                swap(ar[i], ar[i + 1]);
            ar[sz - 1] = d;
        }

    } while (!isMin);

    lli val = 0;
    for (int i = 0; i < sz; i++)
        val = (val * 10) + ar[i];
    return val;
}

int main()
{
    int t = 0;
    cin >> t;

    while (t--)
    {
        lli n = 0;
        int d = 0;
        cin >> n >> d;

        lli ans = find_min(n, d);
        cout << ans << endl;
    }
}