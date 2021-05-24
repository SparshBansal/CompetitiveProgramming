#include <iostream>
using namespace std;
typedef long long int lli;

lli compute(lli a, lli b)
{
    lli sum = 0;
    lli pow = 1;
    while(a!=0 || b!=0)
    {
        int da = a%10, db = b%10;
        int v = (da + db)%10;
        sum = (pow*v) + sum;

        a /= 10, b /=10;
        pow *= 10;
    }

    return sum;
}

int main()
{
    int t=0;
    cin >> t;

    while(t--)
    {
        lli a, b;
        cin >> a >> b;

        lli actual = a+b;
        lli computed = compute(a, b);

        cout << actual - computed << endl;
    }
}