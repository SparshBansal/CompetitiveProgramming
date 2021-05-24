#include <iostream>
#include <algorithm>
#include <cmath>
#include <set>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 100001
#define MOD 1000000007

lli fact[MAXL], invFact[MAXL];

lli binpow(lli a, lli b, lli m)
{
    a %= m;
    lli res = 1;
    while (b > 0)
    {
        if (b & 1)
            res = res * a % m;
        a = a * a % m;
        b >>= 1;
    }
    return res;
}

void computeFactAndInvfact()
{
    fact[0] = 1;
    for (int i = 1; i < MAXL; i++)
        fact[i] = (i * fact[i - 1]) % MOD;

    // compute inverse factorial
    invFact[0] = 1;
    for (int i = 1; i < MAXL; i++)
        invFact[i] = (invFact[i - 1] * (binpow(i, MOD - 2, MOD))) % MOD;
}

int compare(pair<int, int> a, pair<int, int> b)
{
    if (a.first == b.first)
        return a.second < b.second;
    return a.first < b.first;
}

// decrement or remove from map
void decrementOrRemove(map<int, int> &counts, int key)
{
    if (counts.find(key) != counts.end())
    {
        counts[key] = counts[key] - 1;
        if (counts[key] == 0)
        {
            // remove from map
            counts.erase(key);
        }
    }
    return;
}

// removes single element from the multiset
void removeSingle(multiset<int> &mset, int key)
{
    auto it = mset.find(key);
    if (it != mset.end()) mset.erase(it);
}

// use move semantics of vector here
std::vector<pair<int, int> > createPrefixSuffixVector(multiset<int> &elements, int arSum)
{
    std::multiset<int> elementsQry = elements;
    std::vector<pair<int, int> > mvec;
    for (auto it = elements.begin(); it != elements.end(); it++)
    {
        int element = (*it);

        // find if present in the qryset
        if (elementsQry.find(element) != elementsQry.end())
        {
            // check if suff can also be found
            int suff = arSum - element;
            if (elementsQry.find(suff) != elementsQry.end())
            {
                if (element < suff)
                    mvec.push_back(make_pair(element, suff));
                else
                    mvec.push_back(make_pair(suff, element));

                // remove the element from the qry set
                removeSingle(elementsQry, element);
                removeSingle(elementsQry, suff);
            }
        }
    }
    return mvec;
}

int main()
{

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t = 0;
    cin >> t;

    // compute fact and inverse factorial
    computeFactAndInvfact();

    while (t--)
    {
        int n = 0;
        cin >> n;

        int ar[2 * n];

        // special case
        if (n == 1)
        {
            cin >> ar[0] >> ar[1];
            if (ar[0] != ar[1]) 
            {
                cout << 0 << endl;
                continue;
            }
            cout << 1 << endl;
            continue;
        }

        // else
        for (int i = 0; i < (2 * n); i++)
            cin >> ar[i];

        // compute sum of array first
        lli sum = 0;
        for (int i = 0; i < (2 * n); i++)
            sum += ar[i];

        // compute sum of original array
        lli arSum = sum / (n + 1);

        // create multiset
        multiset<int> elements;

        for (int i = 0; i < (2 * n); i++)
            elements.insert(ar[i]);

        // remove elements from multiset
        removeSingle(elements, arSum);
        removeSingle(elements, arSum);

        std::vector<pair<int, int> > mvec = createPrefixSuffixVector(elements, arSum);

        // check if ans is possible
        if (mvec.size() != n - 1)
        {
            // ans not possible
            cout << 0 << endl;
            continue;
        }

        // now deduping is required
        map<pair<int, int> , int> dedupCounts;
        for (auto i = mvec.begin(); i!=mvec.end(); i++) {
            if (dedupCounts.find((*i))!= dedupCounts.end()) 
                dedupCounts[(*i)]++;
            else dedupCounts.insert(make_pair((*i),1));
        }

        // compute ans now
        lli ans = 1;
        // numerator
        ans *= fact[n-1];
        for (auto i = dedupCounts.begin(); i != dedupCounts.end(); i++) {
            ans = ((ans * invFact[(i->second)]) % MOD);
        }

        for (auto i = dedupCounts.begin(); i != dedupCounts.end(); i++) {
            if (i->first.first != i->first.second) {
                ans = (ans * binpow(2, i->second, MOD)) % MOD;
            }
        }

        cout << ans << endl;
    }
}