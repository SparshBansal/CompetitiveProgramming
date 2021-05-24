#include <iostream>

using namespace std;

#define MAXL 100001
#define fo(i,s,e) for (int i=s; i<e; i++)

int dp[MAXL][4];
int pre[MAXL];

int getSegSum(int a, int b)
{
    if (a == 0)
        return pre[b];
    else
        return pre[b] - pre[a-1];
}

int main()
{
    int n, k;
    cin >> n >> k;
    int ar[n];
    fo(i,0,n) cin >> ar[i];

    pre[0] = ar[0];
    fo(i,1,n) pre[i] = pre[i-1]+ar[i];

    // compute dp
    fo(i,0,n) dp[i][0] = 0;
    fo(i,1,4)
    {
        fo(j,0,n)
        {
            if (j < (i*k)-1)
                dp[j][i] = 0;
            else if (j == (i*k)-1)
                dp[j][i] = getSegSum(j-k+1, j) + (i==1?0:dp[j-k][i-1]);
            else
                dp[j][i] = max(getSegSum(j-k+1, j) + dp[j-k][i-1], dp[j-1][i]);
        }
    }

    int ans =0;
    fo(i, 0, n)
        ans = max(ans, dp[i][3]);

    cout << ans << endl;
}
