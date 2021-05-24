#include<iostream>
#include<vector>

using namespace std;

int main()
{
  int t=0;
  cin >> t;

  while(t--)
  {
    int n=0;
    cin >> n;

    int maxi[11];

    // fill with min vals
    for (size_t i = 0; i < 11; i++) {
      maxi[i] = 0;
    }

    // input submissions
    for (int i=0; i<n; i++) {
      int p=0, s=0;
      cin >> p >> s;
      maxi[p-1] = max(maxi[p-1], s);
    }

    int sum=0;
    for (int i=1; i<=8; i++)
      sum += maxi[i-1];

    cout << sum << endl;
  }
  return 0;
}
