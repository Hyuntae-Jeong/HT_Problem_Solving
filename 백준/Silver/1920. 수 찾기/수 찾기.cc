#include <iostream>
#include <algorithm>
#include <cstring>

using namespace std;
int arr[100001];
int N;

bool binarySearch(int target){
    if(target < arr[0] || target > arr[N-1]) return false;
    int S=0, E=N-1, M;
    while(S <= E){
        M = (S + E) / 2;
        if(arr[M] == target) return true;
        else if(arr[M] > target){
            E = M - 1;
        }
        else if(arr[M] < target){
            S = M + 1;
        }
    }
    return false;
}
int main(){
    // freopen("1920_input.txt","r",stdin);
    ios_base::sync_with_stdio(NULL);
    cin.tie(0);

    memset(arr, 0, sizeof(arr));

    int M, i;
    cin >> N;

    for(i=0; i<N; i++){
        cin >> arr[i];
    }
    sort(arr, arr+N);
    cin >> M;

    int target;
    int result[100001]; 
    for (i = 0; i < M; i++)
    {
        cin >> target;
        result[i] = binarySearch(target);
    }
    for(i=0; i<M; i++){
        cout << result[i] << "\n";
    }
    return 0;

}