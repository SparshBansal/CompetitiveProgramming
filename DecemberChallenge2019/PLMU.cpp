#include<iostream>

using namespace std;

int main(int argc, char const *argv[]) {
  int t=0;
  cin >> t;

  while (t-- ) {
    int n=0;
    cin >> n;
    int cnt_zero=0,cnt_two=0,val=0;
    for (int i=0; i<n; i++) {
      cin >> val;
      (val == 2 ? cnt_two++ : cnt_two);
      (val == 0 ? cnt_zero++ : cnt_zero);
    }
    int sum=0;
    sum += ((cnt_two * (cnt_two-1))/2);
    sum += ((cnt_zero * (cnt_zero -1))/2);
    cout << sum << endl;
  }
  return 0;
}
