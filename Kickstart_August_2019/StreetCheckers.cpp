#include <iostream>
#include <algorithm>
#include <cmath>
#include <cstring>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long ll;

#define MAXL 100001

bool prime[32000];
vector<int> primes;

void SieveOfEratosthenes(int n) 
{ 
    // Create a boolean array "prime[0..n]" and initialize 
    // all entries it as true. A value in prime[i] will 
    // finally be false if i is Not a prime, else true. 
    memset(prime, true, sizeof(prime)); 
  
    for (int p=2; p*p<=n; p++) 
    { 
        // If prime[p] is not changed, then it is a prime 
        if (prime[p] == true) 
        { 
            // Update all multiples of p greater than or  
            // equal to the square of it 
            // numbers which are multiple of p and are 
            // less than p^2 are already been marked.  
            for (int i=p*p; i<=n; i += p) 
                prime[i] = false; 
        } 
    } 
  
    // Print all prime numbers 
    for (int p=2; p<=n; p++) 
       if (prime[p]) primes.push_back(p);
} 

bool isprime(ll n)
{
    for (int i=0; primes[i] < n && i < primes.size(); i++)
    {
        if (n % primes[i] == 0 && n != primes[i])
            return false;
    }
    return true;
}


int main() 
{

    SieveOfEratosthenes(32000);

    for (int i=0; i<primes.size(); i++)
        cout << primes[i] << endl;

    int t=0;
    cin >> t;

    for (int q=1; q<=t; q++)
    {
        ll l, r;
        cin >> l >> r;
        ll sum=0;
        for (ll i = l ; i<=r; i++)
        {
            if (i == 8)
            {
                // cout << "i : " << i << endl;
                sum++;
                continue;
            }
            if (i % 2 != 0)
            {
                if (isprime(i))
                {
                    // cout << "i : " << i << endl;
                    sum++;
                    continue;
                }
            }
            else
            {
                if (i%8 == 0)
                {
                    continue;
                }
                else if (i%4 == 0)
                {
                    if (isprime(i/4))
                    {
                        // cout << "i : " << i << endl;
                        sum++;
                    }
                    else 
                        continue;
                }
                else if (i % 2 == 0)
                {
                    // cout << "i : " << i << endl;
                    sum++;
                }
            }
            
        }
        cout << "Case #" << q << ": " << sum << endl;
    }
}