#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

int main() {
    int t=0;
    long long int n=0, p=0;
    cin >> t;

    while(t--) {
        cin >> n >> p;

        // basic cases
        if (n == 1 || n == 2) 
        {
            cout << p*p*p << endl;
            continue;
        }

        long long int min_div = (n/2) + 1;
        long long int mod_val = n%min_div;

        long long int val_1 = 1*(p-mod_val)*(p-mod_val);
        long long int val_2 = (p-n)*1*(p-mod_val);
        long long int val_3 = (p-n)*(p-n)*1;

        long long int ans = val_1 + val_2 + val_3;
        cout << ans << endl;
    }
}