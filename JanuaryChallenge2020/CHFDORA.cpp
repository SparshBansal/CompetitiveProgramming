#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <unordered_map>
#include <unordered_set>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 100001

void findVal(int **ar, int r, int c, vector<pair<int,int> > &rp, vector<pair<int,int> > &cp, int cl, int &sum) {
    // base case
    if (cl > min(r, c)) {
        return;
    }

    vector<pair<int,int> > nrp, ncp;
    bool **cache;
    cache = new bool*[r];
    for (int i=0; i<r; i++) cache[i] = new bool[c];

    for (int i=0; i<r; i++)
        for (int j=0; j<c; j++) cache[i][j] = false;

    for (auto it = rp.begin(); it!=rp.end(); it++) {
        int row = it->first;
        int col = it->second;   

        int diff = cl / 2;
        if (col - diff >= 0 && col + diff < c) {
            if (ar[row][col-diff] == ar[row][col+diff]) {
                nrp.push_back(make_pair(row,col));
                cache[row][col] = true;
            }
        }
    }

    for (auto it = cp.begin(); it!=cp.end(); it++) {
        int row = it->first;
        int col = it->second;   

        int diff = cl / 2;
        if (row - diff >= 0 && row + diff < r) {
            if (ar[row-diff][col] == ar[row+diff][col]) {
                ncp.push_back(make_pair(row,col));
                if (cache[row][col]) sum++;
            }
        }
    }
    findVal(ar, r, c, nrp, ncp, cl+2, sum);
}

int main()
{
    int t=0;
    cin >> t;

    while(t--)
    {
        int n=0, m=0;
        cin >> n >> m;

        int **ar;
        ar = new int*[n];
        for (int i=0; i<n; i++) {
            ar[i] = new int[m];
        }

        vector<pair<int,int> > rp, cp;
        int sum = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                cin >> ar[i][j];
                rp.push_back(make_pair(i,j));
                cp.push_back(make_pair(i,j));
                sum++;
            }
        }

        findVal(ar, n, m, rp, cp, 3, sum);
        cout << sum << endl;
    }
}