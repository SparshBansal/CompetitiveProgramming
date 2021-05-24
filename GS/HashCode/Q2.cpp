#include <iostream>
#include <vector>
#include <set>

using namespace std;

struct image
{
    char orientation;
    vector<string> tags;
    bool operator < (const image &other) const { return tags.size() < other.tags.size(); }
};

int weight(vector<string> tagsA, vector<string> tagsB)
{
    vector<string> inter;
    set_intersection(tagsA.begin(), tagsA.end(), tagsB.begin(), tagsB.end(), inter.begin());
    return min(tagsA.size() - inter.size(), min(tagsB.size() - inter.size(), inter.size()));
}

int main()
{
    int n = 0;
    cin >> n;

    vector<image> images;

    for (int q = 0; q < n; q++)
    {
        int m = 0;
        image im;

        cin >> im.orientation;
        cin >> m;

        for (int i = 0; i < m; i++)
        {
            string tag;
            cin >> tag;

            im.tags.push_back(tag);
        }
        images.push_back(im);
    }

    image prev = images[0];
    set<image> remaining(images.begin(), images.end());

    remaining.erase(prev);
    int cnt = 1;
    cout << remaining.size();
    for (set<image>::iterator it = remaining.begin(); it != remaining.end(); it++)  cout << (*it).orientation << " ";
    cout << endl;

    vector<image> solution;
    solution.push_back(prev);

    while (!(remaining.size() == 1 && (*remaining.begin()).orientation == 'V') || !remaining.empty())
    {
        int max = INT_MIN;
        image ansImg;
        for (set<image>::iterator it = remaining.begin(); it != remaining.end(); it++)
        {
            int score = weight(prev.tags, (*it).tags);
        
            if (score > max)
            {
                max = score;
                ansImg = (*it);
            }
        }
        vector<string> firstTags = ansImg.tags;

        remaining.erase(ansImg);
        solution.push_back(ansImg);
        prev = ansImg;

        if (ansImg.orientation == 'V')
        {
            for (set<image>::iterator it = remaining.begin(); it != remaining.end(); it++)
            {
                if ((*it).orientation == 'H')
                    continue;

                int score = weight(prev.tags, (*it).tags);

                if (score > max)
                {
                    max = score;
                    ansImg = (*it);
                }
            }

            solution.push_back(ansImg);
            remaining.erase(ansImg);

            prev = ansImg;
            vector<string> un;
            set_union(firstTags.begin(), firstTags.end(), ansImg.tags.begin(), ansImg.tags.end(), un.begin());
            prev.tags = un;
        }
    }
    
    for (int i=0; i<solution.size(); i++)
    {
        for (int j=0; j<solution[i].tags.size(); j++)
            cout << solution[i].tags[j] << " ";
    
        cout << endl;
    }
}