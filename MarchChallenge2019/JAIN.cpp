#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>
#include <vector>
#include <map>
using namespace std;

typedef long long int lli;

#define MAXL 100001

struct node
{
    int cnt = 0;
    // left, right pointers
    node *l, *r;

    static node *get_new_node(int cnt)
    {
        node *new_node = new node();
        new_node->cnt = cnt;
        new_node->l = new_node->r = NULL;

        return new_node;
    }

    static int get_count(node *root)
    {
        if (root == NULL)
            return 0;
        else
            return root->cnt;
    }
};

// print, the tree
void print(node* root)
{
    if (root == NULL) return;
    print(root->l);
    cout << root->cnt << " " << node::get_count(root->l) << " " << node::get_count(root->r) << endl;
    print(root->r);
}

node *insert(node *root, int *ar, int idx)
{
    // base case, just increment the count
    if (idx == 4)
    {
        root->cnt++;
        return root;
    }

    // assume a length 5 array to be passed
    if (root == NULL)
        root = node::get_new_node(0);

    if (ar[idx] == 0)
    {
        // check if left node exists, create one
        if (root->l == NULL)
            root->l = node::get_new_node(0);

        insert(root->l, ar, idx + 1);
    }
    else if (ar[idx] == 1)
    {
        // check if right node does not exist, create one
        if (root->r == NULL)
            root->r = node::get_new_node(0);
        insert(root->r, ar, idx + 1);
    }

    root->cnt = node::get_count(root->l) + node::get_count(root->r);
    return root;
}

int query()

int main()
{
    map<char, int> idx_map;

    // create vowel -> idx map
    idx_map['a'] = 0;
    idx_map['e'] = 1;
    idx_map['i'] = 2;
    idx_map['o'] = 3;
    idx_map['u'] = 4;

    int t = 0;
    cin >> t;

    while (t--)
    {
        int n = 0;
        cin >> n;

        // array of vowel strings
        int* vow_ar[n];

        // input the strings
        for (int i = 0; i < n; i++)
        {
            string ar;
            cin >> ar;

            // initialize vowel array
            int *vow = new int[5];
            fill(vow, vow + 5, 0);

            for (int i = 0; i < ar.length(); i++)
            {
                int idx = idx_map[ar[i]];
                vow[idx] = 1;
            }

            vow_ar[i] = vow;
        }

        node* root = NULL;

        // insert in trie

        for (int i=0; i<n; i++) 
            root = insert(root, vow_ar[i], 0);
        
        print(root);
    }
}