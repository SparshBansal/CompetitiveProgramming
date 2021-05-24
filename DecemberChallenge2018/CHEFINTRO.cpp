#include<iostream>

using namespace std;

int main()
{
    int N,R,r;
    cin >> N >> r;

    for (int i=0; i<N; i++)
    {
        cin >> R;
        if (R >= r) cout << "Good boi\n";
        else cout << "Bad boi\n";
    }
}