#include <iostream>
#include <cstring>
using namespace std;

int main(){
    ios_base::sync_with_stdio(NULL);
    cin.tie(0);

    int N, P, Q;
    cin >> N >> P >> Q;
    int diff = P-Q;

    int straw[101], shine[101], answer[101];
    memset(straw, 0, sizeof(straw)); memset(shine, 0, sizeof(shine)); memset(answer, 0, sizeof(answer));

    for(int i=0; i<N; i++) cin >> straw[i];
    for(int i=0; i<N; i++) cin >> shine[i];

    int i_diff;
    for(int i=0; i<N; i++){
        i_diff = shine[i] - straw[i];
        if((shine[i] ==0 && straw[i] == 0) && diff != 0){
            cout << "NO";
            return 0;
        }
        else if(i_diff == 0 && diff == 0){
            answer[i] = 0;
        }
        else if (diff == 0 || (i_diff * diff < 0) || (i_diff % diff != 0))
        {
            cout << "NO";
            return 0;
        }
        else{
            answer[i] = i_diff / diff;
            if(answer[i] > 10000){
                cout << "NO";
                return 0;
            }
        }
    }
    cout << "YES\n";
    for(int i=0; i<N; i++) cout << answer[i] << " ";
}