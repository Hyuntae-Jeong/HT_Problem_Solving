#include <iostream>
using namespace std;

int main(){
    ios_base::sync_with_stdio(NULL);
    cin.tie(0);

    int A, B, C;
    cin >> A >> B >> C;

    cout << max(A+C, B);
}