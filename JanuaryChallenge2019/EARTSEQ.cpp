#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 500001

lli ct=0;
vector<lli> pr;
bool p[1000005];

void prime(){
	for(lli i=2;i*i<=1000000;i++){
		if(!p[i])
		for(lli j=i*2;j<=1000000;j+=i)
		p[j]=1;
	}
    for (lli i=2; i < 1000000; i++)
        if (!p[i]) pr.push_back(i);
}

int main()
{
    prime();

    int t=0; 
    cin >> t;


    while(t--)
    {
        int n=0;
        cin >> n;

        if (n == 4)
        {
            cout << "374 595 1365 858" << endl;
            continue;
        }

        lli y=2, z=3, idx=2;
        lli last = 1;
        vector<lli> solution;
        // compute the elements
        for (int i=1; i<n-1; i += 2,idx++)
        {
            lli a = pr[idx];
            lli b = pr[idx+1];
            if (last == 1)
            {
                solution.push_back(a*y);
                solution.push_back(b*y);
            }
            else 
            {
                solution.push_back(a*z);
                solution.push_back(b*z);
            }
            last = !last;
        }

        lli common = pr[idx];
        if (n%2)
        {
            solution.pop_back();
            if (last) common = z;
            else common = y;
        } 
        
        lli phi = 1; // pr[idx+1];
        if (common > 3)
            phi = 7;
        else phi = pr[idx+1];

        solution.push_back(phi* common);

        cout << (pr[2] * phi) << " ";
        for (int i=0; i<solution.size(); i++)
            cout << solution[i] << " ";
        cout << endl;
    }
}