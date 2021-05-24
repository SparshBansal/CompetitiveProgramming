#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 100001

int dfs(vector<int> g[], int root, int visited[])
{
    int num=0;
    if (visited[root]) 
        return num;

    if (!visited[root])
    {
        num++;
        visited[root] = true;

        // run dfs on neighbours
        for (int i=0; i<g[root].size(); i++)
        {
            int child = g[root][i];
            num += dfs(g, child, visited);
        }
    }
    return num;
}

int main()
{
    int t=0;
    cin >> t;

    for(int q=1; q<=t; q++)
    {
        vector<int> g[MAXL];
        int n=0, m=0, a=0, b=0;
        cin >> n >> m;

        for (int i=0; i<m; i++)
        {
            cin >> a >> b;
            g[a].push_back(b);
            g[b].push_back(a);
        }   

        int visited[MAXL] = {0};

        vector<int> components;
        for (int i=1; i<=n; i++)
            if (!visited[i])
                components.push_back(dfs(g, i, visited));

        lli ans = 0;
        for (int i=0; i<components.size(); i++)
            ans += components[i]-1;
        ans += ((components.size() - 1 ) * 2);
        cout << "Case #" << q << ": " << ans << endl;
    }
}