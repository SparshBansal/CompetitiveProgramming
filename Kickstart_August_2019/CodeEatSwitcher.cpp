#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 100001

int binarySearch(double ar[], int l, int r, double val)
{
    if ( l > r) return -1;

    int m = (l + (r-l)/2);
    int left = (m-1 >= 0) ? ar[m-1] : INT_MIN;

    if (ar[m] >= val && left < val)
        return m;
    else if (ar[m] < val)
        return binarySearch(ar, m+1, r, val);
    else if (ar[m] >= val && left >= val)
        return binarySearch(ar, l, m-1, val);
}

int compare(pair<double, double> a, pair<double, double> b)
{
    return (a.second / a.first) <= (b.second / b.first);
}

int main()
{
    int t=0;
    cin >> t;

    for (int q=1; q<=t; q++)
    {
        int d=0, s=0, c=0, e=0;
        vector<pair<double, double> > slots;
        vector<pair<double, double> > days;
        cin >> d >> s;
        for (int i=0; i<s; i++)
        {
            cin >> c >> e;
            slots.push_back(make_pair(c,e));
        }

        for (int i=0; i<d; i++)
        {
            cin >> c >> e;
            days.push_back(make_pair(c,e));
        }

        // sort by coding fraction
        sort(slots.begin(), slots.end(), compare);

        // create prefix arrays
        double prec[MAXL], sufe[MAXL];
        prec[0] = slots[0].first;

        for (int i=1; i<slots.size(); i++)
            prec[i] = prec[i-1] + slots[i].first;

        sufe[slots.size()-1] = slots[slots.size()-1].second;
        for (int i=slots.size()-2; i>=0; i--)
            sufe[i] = sufe[i+1] + slots[i].second;


        cout << "Case #" << q << ": ";

        for (int i=0; i<days.size(); i++)   
        {
            pair<double, double> day = days[i];
            int idx = binarySearch(prec, 0, slots.size()-1, day.first);
            if (idx == -1)
            {
                cout << "N";
                continue;
            }

            double prev = (idx == 0) ? 0 : prec[idx-1];
            double current = day.first - prev;

            // fraction required
            double fraction = current / slots[idx].first;
            double remainingFraction = 1 - fraction;

            double eatingRemaining  = (1 - fraction) * slots[idx].second;
            eatingRemaining += ((idx + 1 == slots.size()) ? 0 : sufe[idx+1]);

            if (eatingRemaining < days[i].second)
            {
                cout << "N" ;
                continue;
            }
            cout << "Y";
        }
        cout << endl;
    }
}