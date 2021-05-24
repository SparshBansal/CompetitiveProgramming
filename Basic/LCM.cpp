#include <iostream>
#include <cmath>

using namespace std;

typedef long long int lli;

lli gcd(lli a, lli b)
{
    if (b > a) swap(a,b);
    if (a%b == 0) return b;
    else return gcd(b, a%b);
}

int main()
{   
    int t=0;
    lli x, y, a, b, n;

    cin >> t;

    while(t--)
    {
        cin >> n;
        cin >> x >> y >> a >> b;

        lli a_disc = n/x;
        lli b_disc = n/y;

        a_disc = a*a_disc;
        b_disc = b*b_disc;

        lli lcm = (x*y)/gcd(x,y);
        lli extra_disc = n/lcm;


        lli subtract = min(a,b)*extra_disc;

        // cout << "lcm : " << lcm << endl;
        lli total_disc = a_disc + b_disc - subtract;

        cout << total_disc << endl;
    }
}