#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int ll;

#define MAXL 100001

ll find_greatest(int dig[])
{
    ll ans=0;
    for (int i=9; i>=0; )
    {
        if (dig[i] == 0) --i; 
        else {
            ans *= 10;
            ans += i;
            dig[i]--;
        }
    }
    return ans;
}

ll reverse(ll a)
{
    ll ra = 0;
    while(a!=0)
    {
        ra = ra * 10;
        ra += (a%10);
        a /= 10;
    }
    return ra;
}

int main()
{
    ll a, b;
    cin >> a >> b;
    int ta = a, tb = b, ca=0, cb=0;

    int da[10], db[10];

    fill(da, da+10, 0);
    while(ta != 0)
    {
        da[ta%10]++;
        ta /= 10;
        ca++;
    }
    while (tb != 0)
    {
        db[tb%10]++;
        tb /= 10;
        cb++;
    }

    if (cb > ca) 
    {
        cout << find_greatest(da);
        return 0;
    }

    // else start with the first digit
    ll rb = reverse(b);

    // now we try to match the as closely as possible
    int drop = -1, min_dig=-1;
    ll ans = 0;
    while(rb!=0)
    {
        int dig = rb % 10;
        rb /= 10;
        int cdig = -1;
        for (int i=0; i<= dig; i++)
            if (da[i] > 0) cdig = i;
        

        // if no possible digit found
        if (cdig == -1)
        {
            // we need to drop till the fallback, it is guaranteed that fallback
            // exists
            for (int i=0; i<=drop; i++) 
            {
                da[ans%10]++;
                ans /= 10;
            }

            ans *= 10;
            ans += min_dig;
            da[min_dig]--;

            // find the greatest number from here
            ll greatest = find_greatest(da);

            cout << "Ans : " << ans << " " << "Greatest : " << greatest;
            break;
        }

        // we can only replace by smaller number
        else if (cdig < dig)
        {
            ans *= 10;
            ans += cdig;
            da[cdig]--;
            // get the greatest possible number now and break;
            ll greatest = find_greatest(da);
            cout << "Ans : " << ans << " " << "Greatest : " << greatest;
            break;
        }
        // cdig == dig
        else {
            // here we need to populate drop and min_dig
            for (int i=0; i<dig; i++)
                if (da[i] > 0)
                    drop = 0, min_dig = i;
            drop++;

            ans *= 10;
            ans += cdig;

            // use cdig
            da[cdig]--;
        }
    }
}