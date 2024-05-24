#include <iostream>
using namespace std;

int main(){
    //freopen("25905_input.txt","r",stdin);
    ios_base::sync_with_stdio(NULL);
    cin.tie(0);
    double prop, total_prop = 1, min_prop = 10;
    for(int i=0; i<10; i++){
        cin >> prop;
        total_prop *= prop*10;
        min_prop = min(min_prop, prop);
    }
    
    total_prop /= min_prop*10;
    total_prop = total_prop / 362880.0;

    cout.precision(7);
    cout << total_prop;

    return 0;
}