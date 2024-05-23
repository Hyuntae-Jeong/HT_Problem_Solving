#include <iostream>
#include <string>
#include <set>

using namespace std;

int main(){
    string S, mini;
    set<string> words;
    cin >> S;
    // iterate length 1, 2, 3, ..., length of string
    for(int len = 1; len <= S.length(); len++){
        for(int i = 0; i <= S.length() - len; i++){
            mini = S.substr(i, len);
            words.insert(mini);
        }
    }
    cout << words.size();
}