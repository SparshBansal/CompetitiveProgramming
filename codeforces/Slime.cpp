#include <iostream>
#include <climits>

using namespace  std;

typedef long long int ll;

int main(int argc, char const *argv[])
{
	int n=0;
	cin >> n;

	const int MAXL = 500001;

	int ar[MAXL];

	for (int i = 0; i < n; ++i)
		cin >> ar[i];

	int minPos = INT_MAX, minNeg = INT_MIN;

	if (n == 1)
	{
		cout << ar[0] << endl;
		return 0;
	}

	for (int i = 0; i < n; ++i)
	{
		if (ar[i] >= 0)
			minPos = min(minPos, ar[i]);
		if (ar[i] <= 0)
			minNeg = max(minNeg, ar[i]);
	}

	ll sum =0, ans=0;
	for (int i=0; i<n; i++) 
		if (ar[i] < 0) sum -= ar[i];
		else sum += ar[i];

	// construct the sum
	if (minNeg == INT_MIN)
		// no negative is found
		ans = sum - 2 * (minPos);
	else if (minPos == INT_MAX)
		// if no positive is found
		ans = sum - 2 * abs(minNeg);
	else 
		ans = sum;


	cout << ans << endl;
	return 0;
}