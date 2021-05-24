#include <iostream>
using namespace std;

#define MAXL 100001

int main()
{
	int t=0;
	cin >> t;

	char ar[MAXL];
	int** prefix = new int*[MAXL];
	for (int i=0; i<MAXL; i++)
		prefix[i] = new int[26];

	for(int q=1; q<=t; q++)
	{
		int n=0, qr=0;
		cin >> n >> qr;

		// input the array
		for(int i=0; i<n; i++) 
			cin >> ar[i];

		// create prefix sum arrays
		for (int i=0; i<26; i++)
			prefix[0][i] = 0;

		prefix[0][ar[0] - 'A']+=1;
		for (int i=1; i<n; i++)
		{
			for (int j=0; j<26; j++)
				prefix[i][j] = prefix[i-1][j];
			prefix[i][ar[i] - 'A'] += 1;
		}

		int cnt=0;
		// input the queries
		for (int z=0; z<qr; z++)
		{
			int l=0, r=0;
			cin >> l >> r;

			l--,r--;

			// calculate the difference in sum
			int diff[26];

			for (int i=0; i<26; i++)
				diff[i] = prefix[r][i];

			if (l > 0)
				for (int i=0; i<26; i++)
					diff[i] -= prefix[l-1][i];

			// now condition for palindrome
			bool found = false;
			bool possible = true;

			for (int i=0; i<26; i++)
				if (diff[i]%2)
				{
					if (found) possible = false;
					else found = true;
				}
			if (possible)
				cnt++;
		}

		cout << "Case #"<<q<<": " << cnt << endl;

	}
}