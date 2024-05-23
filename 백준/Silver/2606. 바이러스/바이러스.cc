#include <iostream>
#include <vector>
#include <deque>
#include <cstring>

using namespace std;

bool connect[102][102];
bool virus[102];

int main(){
    // freopen("2606_input.txt", "r", stdin);

    int N, C;
    cin >> N >> C;
    memset(connect, false, sizeof(connect));
    memset(virus, false, sizeof(virus));

    int a, b;
    for(int i=0; i<C; i++){
        cin >> a >> b;
        connect[a][b] = true; connect[b][a] = true;
    }

    deque<int> q;
    q.push_back(1);
    int victim, affected=-1;
    while(!q.empty()){
        victim = q.front(); q.pop_front();
        if(!virus[victim]){
            virus[victim] = true; affected++;
            for(int i=1; i<=N; i++){
                if(connect[victim][i] && !virus[i]) q.push_back(i);
            }
        }
    }

    cout << affected ;

    return 0;
}