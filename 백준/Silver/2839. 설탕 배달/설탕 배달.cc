#include <iostream>
using namespace std;

int main(){
    int n, left_bags, min_bags = 10000;
    cin >> n;

    for(int i = n/5; i>=0; i--){
        left_bags = n - i*5;
        if(left_bags % 3 == 0){
            min_bags = min(min_bags, i+(left_bags/3));
        }
    }
    if(min_bags == 10000) min_bags = -1;
    cout << min_bags;

    return 0;
}