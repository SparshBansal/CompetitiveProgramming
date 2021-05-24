#include <iostream>
#include <algorithm>
#include <set>
#include <vector>

using namespace std;

#define fo(i,s,n) for(int i=s; i<n; i++)

int main(int argc, char const *argv[])
{
    int t=0;
    int n=0;

    cin >> t;

    fo(q,1,t+1)
    {
        cin >> n;
        string in;
        cin >> in;

        int *ar = new int[in.length()];
        fo(i,0,in.length()) ar[i] = in[i] - '0';

        int window_sz = (n%2 == 0?n/2:(n/2+1));
        int window_sum = 0;
        int window_end = window_sz-1;
        // find max rolling window
        fo(i,0,window_sz) window_sum += ar[i];

        int ans = window_sum;
        fo(i,1,n-window_sz+1)
        {
            window_sum -= ar[i-1];
            window_sum += ar[window_end+1];

            ans = max(ans, window_sum);
            window_end++;
        }

        cout << "Case #"<< q << ": " << ans << endl;
    }
    return 0;
}
