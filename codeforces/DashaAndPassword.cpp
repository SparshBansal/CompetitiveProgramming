#include <iostream>
#include <climits>

using namespace std;

bool isAlpha(char c){return c >= 'a' && c <= 'z';}
bool isNum(char c){return c>='0' && c <= '9';}
bool isSpecial(char c){return c=='*' || c == '#' || c=='&';}

int computeDp(string ar[], int dp[][2][2][2], 
	int idx, bool lower, bool num, bool special)
{

	// cout << "Computing for " << idx << " " << lower << " " << num << " " << special << endl;
	// return already computed value
	if (dp[idx][lower][num][special] != -1)
		return dp[idx][lower][num][special];

	// check for base case 
	string op = ar[idx];
	int n = op.length();

	if (idx == 0)
	{
		// printf("%s\n", "Computing base case");
		int cnt = 0,ans = 0;

		if (lower) 
		{
			cnt++;
			int cans = INT_MAX;
			for (int i=0; i<op.length(); i++)
			{
				if (isAlpha(op[i]))
					cans = min(cans, min(i , n - i));
			}
			ans = cans;
		}

		if (num)
		{
			cnt++;
			int cans = INT_MAX;
			for (int i=0; i<op.length(); i++)
			{
				if (isNum(op[i]))
					cans = min(cans, min(i , n - i));
			}
			ans = cans;
		}

		if (special)
		{
			cnt++;
			int cans = INT_MAX;
			for (int i=0; i<op.length(); i++)
			{
				if (isSpecial(op[i]))
					cans = min(cans, min(i , n - i));
			}
			ans = cans;
		}

		if (cnt == 0)
			dp[idx][lower][num][special] = 0;
		else if (cnt > 1)
			dp[idx][lower][num][special] = INT_MAX;
		else 
			dp[idx][lower][num][special] = ans;

		// printf("dp[%d][%d][%d][%d] = %d \n",idx, lower, num, special, dp[idx][lower][num][special]);
		return dp[idx][lower][num][special];
	}
		
	// handle non base case
	int ans = INT_MAX;
	for (int i=0; i < op.length(); i++)
	{
		if (isAlpha(op[i]))
		{
			int dpval = computeDp(ar, dp, idx-1, false, num, special);
			int cans = dpval + min(i, n-i);

			if (dpval == INT_MAX)
				cans = dpval;

			ans = min(ans, cans);
		}
		if (isNum(op[i]))
		{
			int dpval = computeDp(ar, dp, idx-1, lower, false, special);
			int cans = dpval + min(i, n-i);

			if (dpval == INT_MAX)
				cans = dpval;

			ans = min(ans, cans);
		}
		if (isSpecial(op[i]))
		{
			int dpval = computeDp(ar, dp, idx-1, lower, num, false);
			int cans = dpval + min(i, n-i);

			if (dpval == INT_MAX)
				cans = dpval;

			ans = min(ans, cans);
		}
	}
	dp[idx][lower][num][special] = ans;
	return dp[idx][lower][num][special];
}

int main()
{
	int n=0, m=0;
	cin >> n >> m;

	string ar[51];

	for(int i=0; i<n; i++)
	{
		string in;
		cin >> in;
		ar[i] = in;
	}

	const int MAXL = 51;
	// construct the dp array
	int dp[MAXL][2][2][2];

	// init
	for (int i=0; i<MAXL; i++)
	{
		dp[i][0][0][0] = -1;
		dp[i][0][0][1] = -1;
		dp[i][0][1][0] = -1;
		dp[i][0][1][1] = -1;
		dp[i][1][0][0] = -1;
		dp[i][1][0][1] = -1;
		dp[i][1][1][0] = -1;
		dp[i][1][1][1] = -1;
	}

	// [0] -> lower case english
	// [1] -> number
	// [2] -> special character


	// compute dp
	int ans = computeDp(ar, dp, n-1, true, true, true);
	cout << ans << endl;
}