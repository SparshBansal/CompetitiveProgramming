#include <iostream>
#include <algorithm>
#include <set>
#include <vector>
using namespace std;

#define fo(i,s,n) for(int i=s; i<n; i++)

int compare(string a, string b){return a.length() < b.length();}

int main()
{
    int t=0; 
    cin >> t;
    
    fo(q,1,t+1) 
    {
        int n,p;
        cin >> n >> p;
        vector<string> dict;
        string in;
        fo(i,0,p)
        {
            cin >> in;
            dict.push_back(in);
        }
        sort(dict.begin(), dict.end(), compare);    
        set<string> hashset;

        // fo(i,0,dict.size()) cout << dict[i] << endl;
        long long int sum=0;
        for(vector<string>::iterator it = dict.begin(); it!=dict.end(); it++)
        {
            // check for all the prefixes
            bool found = false;
            fo(i,0,(*it).length()) 
            {
                if(hashset.find((*it).substr(0, i+1)) != hashset.end()) 
                    found = true;
            }

            if (!found)
            {   
                long long int val = 1;
                fo(i,0,n-(*it).length()) val *= 2;
                sum += val;
                hashset.insert((*it));
            }
        }

        long long int total = 1;
        fo(i,0,n) total *= 2;

        cout << "Case #" << q<< ": " << total - sum << endl;
    }
}