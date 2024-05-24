#include<iostream>
using namespace std;

int main(){
    ios_base::sync_with_stdio(NULL);
    cin.tie(0);

    int X, N, answer;
    cin >> N;
    int S=1, E=N;
    for(int i=0; i<50; i++){
        X = (S+E) / 2;
        cout << "? " << X << endl;

        // get answer
        cin >> answer;
        if(answer == -1) S = X+1;
        else if(answer == 1) E = X-1;
        else if (answer == 0){
            cout << "= " << X << endl;
            return 0;
        }
    }

    return 0;
}