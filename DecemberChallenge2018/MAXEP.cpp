#include <iostream>
#include <cmath>

using namespace std;

#define MAXL 150000

int main()
{
    int n,c,C=1000;
    int res=0;
    cin >> n >> c;

    int root_n = sqrt(n);

    // use sqrt n to jump
    int s = 0;

    while ( s < n) 
    {
        int e = min(n, s + root_n);
        cout << 1 << " " << e << endl;
        cin >> res;
        if (res == 1)
        {
            cout << 2 << endl;
            break;
        }
        s += root_n;
    }

    // found s
    for ( int i=s+1; i<= min(n, s+root_n); i++) 
    {
        cout << 1 << " " << i << endl;
        cin >> res;

        if (res == 1)
        {
            cout << 3 << " " << i << endl;
            break;
        }
    }

    return 0;
}   