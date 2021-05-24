#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

int findForTwo(int w, int a, int b) {
    if (w >= a + b) return 1;
    else return 2;
}

int findMaxForThree(int w, int a, int b, int c) {
    if (w < a + b + c) {
        if (w < a + b) {
            return 1;
        }
        return 2;
    }
    return 3;
}

int main()
{
    int t=0;
    cin >> t;

    while(t--)
    {
        int w,s1,s2,s3;
        cin >> w >> s1 >> s2 >> s3;

        // how many can we break from each side
        int l = findMaxForThree(w, s1, s2, s3);
        int r = findMaxForThree(w, s3, s2, s1);
        
        int rem = 3 - max(l , r);
        int ans = 0;
        switch(rem) {
            case 0:
                ans = 1;
                break;
            case 1:
                ans = 2;
                break;
            case 2:
                ans = 1 + findForTwo(w, s1, s2);
                break;
        }
        cout << ans << endl;
    }
}