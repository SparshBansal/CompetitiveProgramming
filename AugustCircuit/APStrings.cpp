#include <iostream>
#include <string>
using namespace std;

int main() {

    int t = 0;
    cin >> t;

    while(t--) {
        string s;
        cin >> s;

        int charVal[26];
        std::fill(charVal, charVal + 26, 0);

        int distinct = 0;
        for (int i=0; i< s.length(); i++) {
            if (charVal[s[i] - 'a'] == 0) {
                distinct++;
            }
            charVal[s[i] - 'a']++;
        }
        if (distinct == 1) {
            cout << -1 << endl;
            continue;
        }

        for (int i=0; i<26; i++) {
            if (charVal[i] > 0) {
                for (int j=0; j<charVal[i]; j++) {
                    cout << (char (i + 'a'));
                }
            }
        }
        cout << endl;
    }
}