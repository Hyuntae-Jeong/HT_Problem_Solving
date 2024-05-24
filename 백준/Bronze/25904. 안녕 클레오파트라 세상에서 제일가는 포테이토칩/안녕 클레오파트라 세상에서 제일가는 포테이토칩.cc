#include <iostream>
#include <cstring>
using namespace std;

int main(){
    ios_base::sync_with_stdio(NULL);
    cin.tie(0);
    //freopen("25904_input.txt","r",stdin);

    int N, M, i;
    int limit[101]; memset(limit, 0, sizeof(limit));
    cin >> N >> M;
    for(i=0; i<N; i++) cin >> limit[i];
    i=0;
    while(limit[i] >= M){
        M++; i++;
        if(i == N) i=0;
    }
    cout << i+1;
    return 0;
}