#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;

int ar[51][51];
int temp[51][51];

void transpose(int n, int m, bool trans)
{
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            if (trans)
                ar[j][i] = temp[i][j];
            else
                ar[i][j] = temp[i][j];
}

void fillOneCrossN(int n, int m)
{
    bool trans = false;

    if (n > m)
    {
        swap(n, m);
        trans = true;
    }
    int val = 0;
    for (int i = 0; i < m; i++)
    {
        if (i % 2 == 0)
            val = (val % 2) + 1;

        temp[0][i] = val;
        // cout << val << endl;
    }

    transpose(n, m, trans);

    return;
}

void fillNCrossM(int n, int m, int ul)
{
    int val = -1;
    bool trans = false;

    if (n > m)
    {
        swap(n, m);
        trans = true;
    }

    for (int i = 0; i < n; i++)
    {
        if (i % 2 == 0)
            val = (val + 2) % ul;

        for (int j = 0, k = val; j < m; j++, k = (k % ul) + 1)
        {
            temp[i][j] = k;
        }
    }

    transpose(n, m, trans);

    return;
}

int findMin(int n, int m)
{
    int maxi = INT_MIN;

    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            maxi = max(maxi, ar[i][j]);
    return maxi;
}

void printSolution(int n, int m)
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
            cout << ar[i][j] << " ";
        cout << endl;
    }
    return;
}

int main()
{
    int t = 0, n = 0, m = 0;

    cin >> t;

    while (t--)
    {
        cin >> n >> m;

        // check the base cases
        int mini = min(n, m), maxi = max(n, m);
        if (mini == 1)
            fillOneCrossN(n, m);
        else if (mini == 2)
        {
            if (maxi == 2)
                fillNCrossM(n, m, 2);
            else
                fillNCrossM(n, m, 3);
        }
        else
            fillNCrossM(n, m, 4);

        cout << "Input " << n << " " << m << endl;
        int ans = findMin(n, m);
        cout << ans << endl;

        printSolution(n, m);
    }
}