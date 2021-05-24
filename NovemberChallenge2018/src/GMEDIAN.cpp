#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

#define MAXL 1001
#define MOD 1000000007

long ncr[MAXL][MAXL];
long sum[MAXL][MAXL];

void init()
{
  for (int i = 0; i < MAXL; i++)
    ncr[i][0] = 1, ncr[i][i] = 1;

  for (int i = 2; i < MAXL; i++)
    for (int j = 1; j < i; j++)
      ncr[i][j] = (ncr[i - 1][j - 1] % MOD + ncr[i - 1][j] % MOD) % MOD;

  for (int i = 0; i < MAXL; i++)
  {
    long current_sum = 0;
    for (int j = 0; j <= i; j++)
    {
      current_sum = (current_sum % MOD + ncr[i][j] % MOD) % MOD;
      sum[i][j] = current_sum;
    }
  }
}

int get_val(vector<pair<int,int> > counts, int index)
{
	if (index < 0)
		return 0;
	else
		return counts[index].second;
}

int main(int argc, char **argv)
{
  init();
  int t = 0, n = 0, ar[MAXL];
  cin >> t;

  while (t--)
  {
    cin >> n;
    // input
    for (int i = 0; i < n; i++)
    {
      cin >> ar[i];
    }

    sort(ar, ar + n);
    // now we can compute using prefix sums
    vector<pair<int, int> > counts;
    vector<int> pref;

    int count = 1, prev = ar[0];
    int prev_sum = 0;
    for (int i = 1; i < n; i++)
    {
      if (ar[i] == prev)
      {
        count++;
      }
      else
      {

        counts.push_back(make_pair(prev, count));
        pref.push_back(prev_sum + count);

        prev_sum = prev_sum + count;
        prev = ar[i];
        count = 1;
      }
    }
    counts.push_back(make_pair(prev, count));
    pref.push_back(prev_sum + count);

    for (int i = 0; i < counts.size(); ++i)
    {
      long count = counts[i].second;
      long value = counts[i].first;
      // consider for odd and even
      // for odd
      long left = get_val(counts, i - 1);
      long right = n - left - count;

    }
  }
}