#include <iostream>
#include <string>

using namespace std;

typedef long long int lli;

int main()
{
    int t=0;
    cin >> t;

    while(t--)
    {
        string input;
        cin >> input;

        lli p1[input.length()+1], p2[input.length()+1];
        p1[0] = input[0] == '(' ? 1 : 0;


        // populating prefix arrays
        for (int i=1; i<input.length(); i++)
            if (input[i] == '(')
                p1[i] = p1[i-1] + 1;
            else p1[i] = p1[i-1];
        
        p2[input.length()-1] = input[input.length()-1] == ')' ? 1 : 0;

        for (int i=input.length()-2; i >=0; --i)
            if (input[i] == ')')
                p2[i] = p2[i+1] + 1;
            else p2[i] = p2[i+1];

        // compute ans
        lli ans =0;
        for (int i=0; i<input.length(); i++)
            ans = max(ans, min(p1[i],p2[i]));

        cout << (2*ans) << endl;
    }

    return 0;
}