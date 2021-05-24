#include <iostream>
#include <algorithm>

using namespace std;

#define MAXL 100001
#define MOD 1000000007
int main()
{
    long long int n=0, a[MAXL], b[MAXL];
    cin >> n;
    for(long long int i=0; i<n; i++) cin >> a[i];
    for(long long int i=0; i<n; i++) cin >> b[i];

    sort(a, a+n);
    sort(b, b+n);

    long long int diff = 0;
    for (int i=0; i<n; i++)
        diff = (diff%MOD + (abs(a[i] - b[i]))%MOD)%MOD;

    cout << diff << endl;
}