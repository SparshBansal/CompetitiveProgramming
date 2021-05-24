#include <iostream>
#include <vector>

#define fo(i,s,e) for(int i=s; i<e; i++)

using namespace std;
typedef long long int lli;

lli gcd(lli a, lli b)
{
    if (a%b == 0)
        return b;
    return gcd(b, b%a);
}

pair<int,int> getX(lli sum , lli b)
{   
    sum -= b;

    lli hcf = gcd(sum, b);
    sum /= hcf;
    b /= hcf;
    return make_pair(sum , b);
}

int main()
{
    int t=0;
    cin >> t;

    while(t--)
    {
        int n=0;
        cin >> n;

        vector<pair<int ,char> > ar;
        int num;
        char type;
        fo(i,0,n)
        {
            cin >> num;
            cin >> type;

            if (ar.empty())
                ar.push_back(make_pair(num, type));
            else if (ar.back().second == type)
                ar.back().first += num;
        }

        // compute sums
        lli sum_a = 0, sum_b = 0;
        fo(i,0,ar.size()) 
            if (ar[i].second == 'A') sum_a += ar[i].first;
            else if (ar[i].second == 'B') sum_b += ar[i].first;

        if (sum_a == 0)
        {
            cout << sum_b << endl;
            continue;
        }
        if (sum_b == 0)
        {
            cout << sum_a << endl;
            continue;
        }

        // find the common ratio as (num, den) pair
        pair<lli, lli> ratio = getX(sum_a + sum_b , sum_b);

        // now that we have the ratio , we can start looking for it
        lli ca=0, cb=0;

        fo(i, 0, ar.size())
        {
            if (ar[i].second == 'A')
            {
                if (cb == 0)
                    ca += ar[i].first;
                else
                {
                    bool possible = ((ratio.first * cb)%ratio.second== 0);
                    if (possible)
                    {
                        lli val = (ratio.first * cb)/ratio.second;
                        lli rem = val - ca;
                        if ( rem > 0 )
                        {
                            if (ar[i].first - rem >= 0)
                            {
                                count++;
                                cb = 0;
                                ca = ar[i].first - rem;
                            }
                            else 
                                ca += ar[i].first;
                        }
                        else
                            ca += ar[i].first;
                    }
                    else
                        ca += ar[i].first;
                }
            }
            else 
            {

            }
        }
    }
}