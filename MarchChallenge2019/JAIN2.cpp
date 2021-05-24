#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 100001

int find_char_and_shift(map<char, int> vow_map, char vowel, int bin)
{
    if (vow_map.find(vowel) == vow_map.end())
        bin = bin << 1;
    else
        bin = (bin << 1) + 1;
    
    return bin;
}

int get_binary(map<char, int> vow_map)
{
    int bin = 0;
    bin = find_char_and_shift(vow_map, 'a', bin);
    bin = find_char_and_shift(vow_map, 'e', bin);
    bin = find_char_and_shift(vow_map, 'i', bin);
    bin = find_char_and_shift(vow_map, 'o', bin);
    bin = find_char_and_shift(vow_map, 'u', bin);
    return bin;
}

int main()
{
    int t = 0;
    cin >> t;

    while (t--)
    {
        int n = 0;
        cin >> n;

        // input the strings
        string str_ar[n];
        for (int i = 0; i < n; i++)
            cin >> str_ar[i];

        lli cnts[32];
    
        fill(cnts, cnts+32,0);
        lli ans=0;
        for (int i=0; i<n; i++)
        {
            string ar = str_ar[i];

            map<char, int> vow_map;
            vow_map['a'] = 16;
            vow_map['e'] = 8;
            vow_map['i'] = 4;
            vow_map['o'] = 2;
            vow_map['u'] = 1;

            int bin = 0;
            for (int i = 0; i < ar.length(); i++)
            {
                char vowel = ar[i];
                bin = bin | vow_map[vowel];
            }
            cnts[bin]++;
        }

        for (int i=0; i<=31; i++)
            for (int j=i; j<=31; j++)
                if ((i|j) == 31) 
                    if (i == j) ans += (((cnts[i] * (cnts[i]-1))/2));
                    else ans += (cnts[i] * cnts[j]);

        cout << ans << endl;
    }
}