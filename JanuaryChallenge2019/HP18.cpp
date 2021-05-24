#include <iostream>
using namespace std;

#define MAXL 200001

int main()
{
    int t,n,a,b;
    int ar[MAXL];
    cin >> t;

    while(t--) 
    {
        cin >> n >> a >> b;
        for (int i=0; i<n; i++) cin >> ar[i];
        
        int only_bob=0, only_alice=0, both=0;

        for (int i=0; i<n; i++)
            if (ar[i]%a == 0 && ar[i]%b == 0) both++;
            else if (ar[i]%a == 0) only_bob++;
            else if (ar[i]%b == 0) only_alice++;

        if (only_alice > only_bob) 
            cout << "ALICE" << endl;
        else if (only_bob > only_alice)
            cout << "BOB" << endl;
        else if (only_bob == only_alice)
        {
            if (both > 0) cout << "BOB" << endl;
            else cout << "ALICE" << endl;
        }
    }
}