#include <iostream>
#include <climits>
#include <string>

using namespace std;

int main(int argc, char const *argv[]) {
  int t=0;
  cin >> t;

  while(t--)
  {
    int n=0;
    cin >> n;
    string in;
    cin >> in;

    int last[26];
    fill(last, last+26, -1);
    // find closest two characters
    int ans = INT_MAX;
    for (int i=0; i<in.length(); i++) {
      int idx = in[i] - 'a';
      if (last[idx] != -1){
        int distance = i - last[idx];
        ans = min(ans, distance);
      }
      last[idx] = i;
    }

    if (ans < INT_MAX) {
      // compute the ans
      cout << in.length() - ans << endl;
    } else {
      cout << 0 << endl;
    }
  }

  return 0;
}
