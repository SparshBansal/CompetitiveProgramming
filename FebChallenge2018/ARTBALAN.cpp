#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;


int main()
{
    int t=0;
    cin >> t;
    while(t--) {
        string s;
        cin >> s;

        int cnt[26];
        fill(cnt, cnt+26, 0);
        cnt[0] = 0;
        for (int i=0; i<s.length(); ++i) cnt[s[i]-'A']++;
        sort(cnt, cnt+26, greater<int>());

        int ans= s.length()+2;
        for (int distinct=0; distinct < min((int)s.length(), 26); distinct++) {
            bool possible = (s.length()%(distinct+1) == 0);
            if (!possible) continue;

            int val = s.length()/(distinct+1);

            int cans = 0;
            for (int i=0; i<=distinct; i++)
                if (cnt[i] > val) cans += (cnt[i] - val);
            
            for (int i=distinct+1; i<26; i++) cans += cnt[i];
            ans = min(ans, cans);
        }

        cout << ans << endl;
    }
}