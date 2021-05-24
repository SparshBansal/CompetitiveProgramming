#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define c 1000000000

int main()
{
    int t=0;
    cin >> t;

    while(t--)
    {
        lli x0,x1,y0,y1,d1,d2,d3,d4,d5;
        cout << "Q " << 0 << " " << 0 << endl;
        cin >> d1;
        cout << "Q " << 0 << " " << c << endl;
        cin >> d2;
        cout << "Q " << c << " " << 0 << endl;
        cin >> d3;
        cout << "Q " << c << " " << c << endl;
        cin >> d4;

        lli mid = (c + d1 - d3)/2;
        cout << "Q " << mid << " " << 0 << endl;
        cin >> d5;

        y0 = d5;
        x0 = d1 - y0;
        y1 = c + x0 - d2;
        x1 = c + y0 - d3;

        int ans=0;
        cout << "A " << x0 << " " << y0 << " " << x1 << " " << y1 << endl;
        cin >> ans;
    }
}