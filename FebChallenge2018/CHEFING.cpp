#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 1001;

int main()
{
    int t=0;
    string ar[1001];

    cin >> t;
    while(t--) {
        int n;
        cin >> n;

        // input
        for (int i=0; i<n; i++)
            cin >> ar[i];

        int cnt[n][26];
        for (int i=0; i<n; i++)
            for (int j=0; j<26; j++) cnt[i][j] = 0;

        for (int i=0; i<n; i++) 
            for (int j=0; j<ar[i].length(); j++) 
                cnt[i][ar[i][j]-'a']++;

        int ans=0;
        for (int j=0; j<26; j++) {
            bool allPresent = true;
            for (int i=0; i<n; i++) {
                if (cnt[i][j] == 0) allPresent = false;
            }
            if (allPresent) ans++;
        }
        cout << ans << endl;
    }
}