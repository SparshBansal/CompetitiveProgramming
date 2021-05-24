#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 100001

lli v[MAXL], m[MAXL], ans[MAXL];

lli gcd (lli a , lli b) {
    if (a%b == 0) return b;
    else return gcd(b, a%b);
}

void dfs(vector<int> tree[],  int root, int p, lli hcf) {
    // check if root
    bool isRoot = (tree[root].size() == 1);
    if (isRoot) 
    {
        const lli fHcf = gcd(hcf, m[root]);
        if (fHcf == m[root]) ans[root] = 0;
        else if (fHcf == 1) ans[root] = m[root] - 1;
        else 
        {
            const lli val = m[root]/fHcf;
            ans[root] = (val - 1) * fHcf;
        }
        return;
    }

    const lli nHcf = gcd(hcf, v[root]);

    for (int i=0; i<tree[root].size(); i++) 
    {
        const int child = tree[root][i];
        if (child == p) continue;
        dfs(tree, child, root, nHcf);
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int t=0;
    cin >> t;

    while(t--)
    {
        int n=0;
        cin >> n;

        vector<int> tree[n+1];

        // root at 1
        tree[1].push_back(0);

        // input
        for (int i=0; i<n-1; i++)
        {
            int x=0, y=0;
            cin >> x >> y;

            tree[x].push_back(y);
            tree[y].push_back(x);
        }

        for (int i=1; i<=n; i++) 
        {
            cin >> v[i];
            ans[i] = -1;
        }
        for (int i=1; i<=n; i++) cin >> m[i];

        // lets compute using dfs
        lli hcf = v[1];
        dfs(tree, 1, 0, hcf);

        for (int i=1; i<=n; i++) 
        {
            if (ans[i] > -1)
                cout << ans[i] << " ";
        }
        cout << endl;
    }

}