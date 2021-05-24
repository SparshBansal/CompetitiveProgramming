#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

lli mod = 1000000007;

lli mpow(lli b, lli ex)
{
    if (b == 1)
        return 1;
    lli r = 1;
    while (ex)
    {
        if (ex & 1)
            r = (r * b) % mod;
        ex = ex >> 1;
        b = (b * b) % mod;
    }
    return r;
}

lli gcd(lli a, lli b)
{
    if (a % b == 0)
        return b;
    else
        return gcd(b, a % b);
}

lli computeGpTerms(lli numTerms, lli n)
{
    lli invD = mpow(n, mod - 2);
    lli l = (n % mod) * (invD) % mod;
    lli r = ((n - 1) % mod * (invD)) % mod;
    lli la = mpow(l, numTerms);
    lli ra = mpow(r, numTerms);
    if (la - ra < 0)
        return mod + la - ra;
    else 
        return la-ra;
}

int main()
{
    int t = 0;
    cin >> t;

    while (t--)
    {
        lli n, k, m;
        cin >> n >> k >> m;

        // solve for odd
        if (m % 2 == 1)
        {
            lli numTerms = (m / 2) + 1;
            lli ans = computeGpTerms(numTerms, n);
            cout << ans << endl;
        }
        // solve for even
        else
        {
            lli numTerms = m/2;
            lli left = computeGpTerms(numTerms, n);
            

            // compute modinv
            lli modInv = mpow(n, mod-2);
            lli r = ((n - 1) % mod * (modInv)) % mod;

            lli fracExp = mpow(r, numTerms);
            lli l = mpow(n+k, mod-2);

            lli inter = (fracExp * l)%mod;
            lli ans = (left + inter)%mod;

            if (ans < 0) ans += mod;

            cout << ans << endl;
        }
    }
}