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
    int t=0;
    cin >> t;

    while(t--) {
        int n=0;
        cin >> n;

        lli ans = 0;       
        for (int i=0; i<n; ++i) {
            int tmp;
            cin >> tmp;
            ans += (tmp-1);
        }
        ans++;
        cout << ans << endl;
    }
}