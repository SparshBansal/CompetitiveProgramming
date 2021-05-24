#include <iostream>
#include <map>
using namespace std;

int main()
{
    string input;
    cin >> input;

    string a, b;

    cin >> a;
    cin >> b;

    map<char, char> sm;

    for (int i=0; i<26; i++)
    {
        char mapped_char = i + 'a';
        char mapping_char = mapped_char;

        for (int j=0; j<a.length(); j++)
        {
            if (a[j] == mapping_char)
                mapping_char = b[j];
            else if (b[j] == mapping_char)
                mapping_char = a[j];
        }
        sm.insert(make_pair(mapped_char, mapping_char));
    }

    for (int i=0; i<input.length(); i++)
    {
        if (sm.find(input[i]) != sm.end())
            input[i] = sm[input[i]];
    }
    cout << input << endl;
}