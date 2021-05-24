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

    while(t--)
    {
        int n=0;
        cin >> n;

        lli pow10 = pow(10, n);

        lli a, b, c, d, e;
        cin >> a;

        lli target = (2 * pow10) + a;

        cout << target << endl;

        cin >> b;
        c = pow10 - b;
        cout << c << endl;

        cin >> d;
        e = pow10 - d;
        cout << e << endl;

        int correct = 0;
        cin >> correct;
        if (correct == -1) {
            break;
        }
    }
}