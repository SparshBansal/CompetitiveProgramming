#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;
#define MAXL 200001

int main(int argc, char const *argv[])
{   
    long long int n,m;
    long long int a[MAXL], b[MAXL];

    cin >> n >> m;
    for(long long int i=0; i<n; i++) cin >> a[i];
    for(long long int i=0; i<m; i++) cin >> b[i];

    long long int min_b = LLONG_MAX, idx_b = -1;
    long long int max_a = LLONG_MIN, idx_a = -1;

    for(long long int i=0; i<m; i++) if (b[i] < min_b) min_b = b[i], idx_b = i;
    for(long long int i=0; i<n; i++) if (a[i] > max_a) max_a = a[i], idx_a = i;

    for (long long int i=0; i<n; i++)
        cout << i << " " << idx_b << endl;
    
    for (long long int i=0; i<m; i++)
        if (i == idx_b) continue;
        else cout << idx_a << " " << i << endl;
}
