#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

lli gcd(lli  a, lli b) {
    if (a%b == 0)
        return b;
    else return gcd(b, a%b);
}

int main()
{
    lli n,a,b,k;
    int t;
    cin >>t;
    while(t--) {
        cin >> n >> a >> b >> k;
        lli ansA = n/a, ansB = n/b;
        lli lcm = (a * b)/gcd(a, b);
        lli ansBoth = n/lcm;

        ansA -= ansBoth;
        ansB -= ansBoth;
        lli total = a!=b ? ansA + ansB : ansA;
        if ( total >= k) cout << "Win\n";
        else cout << "Lose\n";
    }
}
