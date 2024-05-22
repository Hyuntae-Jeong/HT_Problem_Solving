// 백준 2578 - 빙고

#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

int main(){
    // freopen("2578_input.txt", "r", stdin);

    vector<pair<int, int>> number(26);
    int h[5], v[5], sameCross = 0, addCross = 0;

    memset(h, 0, sizeof(h)); memset(v, 0, sizeof(v));
    
    int num;

    for(int i=0; i<5; i++){
        for(int j=0; j<5; j++){
            cin >> num;
            number[num] = {i, j};
        }
    }
    int choosen, bingo = 0;
    int x, y;
    for(int i=1; i<=25; i++){
        cin >> choosen;
        x = number[choosen].first; y = number[choosen].second;

        if(++h[x] == 5) bingo++;
        if(++v[y] == 5) bingo++;
        if(x == y && ++sameCross == 5) bingo++;
        if(x+y == 4 && ++addCross==5) bingo++;
        

        // cout << "h[" << x << "] = " << h[x] << "\n";
        // cout << "v[" << y << "] = " << v[y] << "\n";
        // cout << "sameCross = " << sameCross << "\n";
        // cout << "addCross = " << addCross << "\n";

        if(bingo >= 3){
            cout << i << "\n";
            return 0;
        }
        // cout << choosen << " pair is " << number[choosen].first << " " << number[choosen].second << "\n";
    }
    return 0;
}