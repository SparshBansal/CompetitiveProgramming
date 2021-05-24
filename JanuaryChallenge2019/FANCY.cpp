#include <iostream>
#include <cstring>
using namespace std;

int main()
{
    int t = 0;
    cin >> t;

    string empty;
    getline(cin, empty);

    while (t--)
    {
        string ar;
        getline(cin, ar);

        if (ar.length() < 3)
        {
            cout << "regularly Fancy\n";
            continue;
        }

        // search for not
        bool flag = false;
        for (int i = 0; i < ar.length() - 2; i++)
        {
            flag = false;
            if (ar[i] == 'n' && ar[i + 1] == 'o' && ar[i + 2] == 't')
            {
                bool left_check = true, right_check = true;
                if (i > 0)
                    if (ar[i - 1] != ' ')
                        left_check = false;
                if (i + 2 < ar.length() - 1)
                    if (ar[i + 3] != ' ')
                        right_check = false;

                if (left_check && right_check)
                {
                    flag = true;
                    break;
                }
            }
        }

        if (flag)
            cout << "Real Fancy\n";
        else
            cout << "regularly fancy\n";
    }
}