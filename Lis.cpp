#include <iostream>

using namespace std;
	
#define MAXL 100001

int bin_search(int ar[], int val, int l, int r)
{
	int m = l + (r-l)/2;
	int r_val = (m+1 <= r ? ar[m+1] : INT_MAX);
	int m_val = ar[m];

	if (m_val <= val && r_val > val) return m+1;
	else if (m_val <= val && r_val <= val) return bin_search(ar, val, m+1, r);
	else return bin_search(ar, val, l, m-1);
}

int main()
{
	int n=0;
	cin >> n;
	
	int ar[n+1], p[n+1], r[n+1];

	for (int i=0; i<n; i++) cin >> ar[i];
	// we can keep end
	//
		
	r[0] = ar[0];
	// cout << "r[0]  "  << r[0] << endl;
	int e=0;
	for (int i=1; i<n; i++)
	{
		// cout << "Executing for " << ar[i] << endl;
		if (ar[i] > r[e])
		{
			r[++e] = ar[i];
			// cout << "Appending at end " << " : " << r[e] << endl;
		}
		else if (ar[i] < r[0])
		{
			// cout << "Replacing the first" << endl;
			r[0] = ar[i];
		}
		else 
		{
			// we need to binary search
			// over the array
			int idx = bin_search(r, ar[i], 0, e);
			// cout << " idx for " << ar[i] << " : " << idx << endl;
			r[idx] = ar[i];
		}
		// cout << "R after iteration : " << endl;
		// for (int i=0; i<=e; i++) cout << r[i] << " ";
		// cout << endl;
	}

	// for (int i=0; i<=e; i++)
	// 	cout << r[i] << " ";
	cout << (e+1) << endl;
}
