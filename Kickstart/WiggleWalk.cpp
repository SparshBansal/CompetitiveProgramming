#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 100001

int main()
{
    int t=0;
    cin >> t;

    // we need 4 maps 
    map<pair<int,int> , pair<int,int> > hs, he, vs, ve;

    int n,r,c,sr,sc;
    cin >> n >> r >> c >> sr >> sc;

    // now we can start moving
    // we need to 

    // we need to add the values of start point
    // to the maps
    hs.insert(make_pair(sr,sc), make_pair(sr, sc));
    he.insert(make_pair(sr,sc), make_pair(sr, sc));
    vs.insert(make_pair(sr,sc), make_pair(sr, sc));
    ve.insert(make_pair(sr,sc), make_pair(sr, sc));

    // we will make 4 variables for directions
    pair<int,int> e,w,no,so;

    e = make_pair(sr,sc+1);
    w = make_pair(sr,sc-1);
    no = make_pair(sr-1,sc);
    so = make_pair(sr+1,sc);

    int moves = 0;

    string in;
    cin >> in;

    pair<int, int> np;

    while(moves < n)
    {
        // input the direction
        char d = in[moves];

        switch (d)
        {
        case 'E':
            np = e;
            break;
        
        case 'W':
            np = w;
            break;

        case 'N':
            np = no;
            break;

        case 'S':
            np = so;
            break;

        default:
            break;
        }

        // now we need to link the pairs
        // link horizontal chains
        // get start and end
        pair<int, int> l, r;
        l = make_pair(np.first, np.second-1);
        r = make_pair(np.first, np.second+1);
        
        // this element links 2 chains
        if (he.find(l) != he.end() && hs.find(r) != hs.end())
        {
            // remove these chains and add a single chain
            pair<int,int> sc = (he.find(l)->second);
            pair<int,int> ec = (hs.find(r)->second);

            // remove the 2 from the chain
            he.erase(l);
            hs.erase(r);

            // insert a new chain
            he.insert(ec, sc);
            hs.insert(sc, ec);
        }

        // this element appends a chain
        else if (he.find(l) != he.end())
        {
            
        }
        
        // this element prepends a chain
        else if (hs.find(r) != hs.end())
        {

        }

        // this elements adds a new chain
        else 
        {

        }
    }
}