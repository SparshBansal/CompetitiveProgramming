#include <iostream>
#include <climits>
#include <cmath>
#include <algorithm>

using namespace std;

typedef long long int ll;

int main()
{
	int n=0;
	cin >> n;

	const int MAXL=3001;
	ll s[MAXL], c[MAXL];

	for(int i=0;i<n;i++) 
		cin >> s[i];

	for(int i=0;i<n;i++) 
		cin >> c[i];

	// construct dp array
	ll dp1[MAXL], dp2[MAXL], dp3[MAXL];

	for(int i=0; i<n; i++)
		dp1[i] = c[i];

	dp2[0] = LLONG_MAX;
		
	// construct dp2
	for(int i=1; i<n; i++)
	{
		dp2[i] = LLONG_MAX;
		for (int j=i-1; j>=0; j--)	
			if (s[j] < s[i])
				dp2[i] = min(dp2[i] , c[i] + c[j]);
	}
	
	// construct dp3
	for (int i=2; i<n; i++)
	{
		dp3[i] = LLONG_MAX;
		for (int j=i-1; j>=0; j--)
			if (s[j] < s[i] && dp2[j]!=LLONG_MAX)
				dp3[i] = min(dp3[i], dp2[j] + c[i]);
	}
	
	ll ans = LLONG_MAX;
	for (int i=2; i<n; i++)
		ans = min(ans, dp3[i]);

	if (ans == LLONG_MAX)
		cout << -1 << endl;
	else
		cout << ans << endl;
}
