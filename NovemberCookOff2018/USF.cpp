#include<iostream>
#include<set>

using namespace std;

const int MAXL=100001;

struct info
{
    bool done=false;
    set<int> factors;
};

info infos[MAXL];

void sieve()
{
    infos[0].done = true;
    infos[1].done = true;

    for (int factor=2; factor <= MAXL/2; factor++)
    {
        if (infos[factor].done)
            continue;
        int mult = 1;
        while(factor * mult < MAXL)
        {
            int val = factor * mult;
            infos[val].done = true;
            infos[val].factors.insert(factor);

            mult++;
        }
    }
}

int getNumPrimes(int num)
{
    return infos[num].factors.size();
}

int main()
{
    int t=0;
    cin >> t; 
   
    // preprocess
    sieve();

    // int c = 0;
    // cin >> c;

    // for(set<int>::iterator i=infos[c].factors.begin(); i!= infos[c].factors.end(); i++)
    //     cout << (*i) << " ";
    // cout << endl;

    while(t--)
    {
        int n;
        cin  >> n;

        int ar[n];
        for(int i=0; i<n; i++) cin >> ar[i];

        const int MAXL = 100001;
        int count[MAXL];

        fill(count, count+MAXL, 0);
        for (int i=0; i<n; i++)
            count[ar[i]]++;

        int ans = 0;
        for (int factor=2; factor <= MAXL/2; factor++)
        {
            int mult = 1;
            int cnt=0;
            while(mult * factor < MAXL)
            {
                if (count[mult*factor] > 0)
                    cnt += count[mult*factor];
                mult++;
            }

            ans = max(ans, cnt*getNumPrimes(factor));
        }
        cout << ans << endl;
    }
}