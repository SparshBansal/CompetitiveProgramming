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
    int val = 0;
    int cnt=0, height=0;
    node *l, *r;

    node(int val)
    {
        this->val = val;
    }

    static node* get_node(int val) 
    {
        node* nNode = new node(val);
        nNode->cnt = 1;
        nNode->height = 1;
        nNode->l = nNode->r = NULL;

        return nNode;
    }

    static int get_height(node* mNode)
    {
        if (mNode == NULL) return 0;
        else return mNode->height;
    }

    static int get_count(node* mNode)
    {
        if (mNode == NULL) return 0;
        else return mNode->cnt;
    }
};

struct tree
{
    node* root = NULL;
    
    // initialize
    void init(int val) {this->root = node::get_node(val);}

    // update height and count
    void updHeightAndCnt(node* p)
    {
        p->height = max(node::get_height(p->l) , node::get_height(p->r))+1;
        p->cnt = (node::get_count(p->l) + node::get_count(p->r)) + 1;
    }

    // left rotate 
    node* left_rotate(node* root)
    {
        node* nRoot = root->r;
        root->r = nRoot->l;
        nRoot->l = root;

        // update height and count
        updHeightAndCnt(nRoot);
        return nRoot;
    }

    // right rotate
    node* right_rotate(node* root)
    {
        node* nRoot = root->l;
        root->l = nRoot->r;
        nRoot->r = root;

        // update height and count
        updHeightAndCnt(nRoot);
        return nRoot;
    }


    node* balance(node* root)
    {
        bool needBalance = abs(node::get_height(root->l) - node::get_height(root->r)) > 1;
        if (needBalance)
        {
            // left heavy
            if (node::get_height(root->l) > node::get_height(root->r))
            {
                root = right_rotate(root);
                // check if balanced
                needBalance = abs(node::get_height(root->l) - node::get_height(root->r)) > 1;
                if (needBalance)
                {
                    // still not balanced 
                    root = left_rotate(root);
                    root->l = right_rotate(root->l);
                    root = right_rotate(root);
                }
            }

            // right heavy
            else
            {
                
            }
        }
    }

    // overloaded insert function
    node* insert(node* p, int val)
    {
        if (p == NULL)
        {
            p = node::get_node(val);
            return p;
        }
        if (p->val > val) p->r = this->insert(p->r, val);
        else p->l = this->insert(p->l, val);
        


        // might need balancing
        balance(p);
        return p;
    }

    // insert operation
    void insert(int val)
    {
        // if not initialized return;
        if (this->root == NULL) 
        {
            this->init(val);
            return;
        }

        // else insert like a binary search tree
        this->root = this->insert(this->root, val);
    }


    // remove an element
    void remove(int val)
    {

    }
}

int main()
{

}