#include <iostream>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(NULL);
    cin.tie(0);

    int N, suffix = 0, count=0;
    cin >> N;
    // if(N == 1 || N == 2) suffix = 1;
    while(N/2!=0){
        count++;
        // if(N > 1 && N % 2 != 0) suffix = 1;
        N /= 2;
    }
    cout << 10+count;

    return 0;

}