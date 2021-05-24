#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
#include <set>

using namespace std;

typedef long long int lli;

#define MAXL 100001

int main()
{
    int n=0, m=0;
    cin >> n >> m;

    int ar[n+1][m+1];
    for (int i=0; i<n; i++)
        for (int j=0; j<m; j++)
            cin >> ar[i][j];

    
    bool flag = false;
    // create a set

    int ans[n];
    for (int i=0; i<n; i++) ans[i] = 1;

    int check=0;
    for (int i=0; i<n; i++) check = check ^ ar[i][ans[i]-1];

    if (check != 0)
    {
        cout << "TAK" << endl;
        for (int i=0; i<n; i++)
            cout << ans[i] << " ";
        cout << endl; 

        return 0;
    }


    // find row and column number
    int r=0, c=0;
    for (int i=0; i<n; i++)
    {
        if (flag) continue;

        set<int> s;
        for (int j=0; j<m; j++)
            s.insert(ar[i][j]);

        if (s.size() > 1) 
        {
            r = i;
            flag = true;

            for (int j=1; j<m; j++) 
                if (ar[i][j] != ar[i][j-1])
                {
                    c = j;
                    break;
                }
        }
    }

    for (int i=0; i<n; i++) ans[i] = 1;

    check=0;
    for (int i=0; i<n; i++) check = check ^ ar[i][ans[i]-1];

    if (check == 0)
        ans[r] = c+1;

    if (flag) 
    {
        cout << "TAK" << endl;
        for (int i=0; i<n; i++)
            cout << ans[i] << " ";
        cout << endl;
    }
    else cout << "NIE" << endl;
}