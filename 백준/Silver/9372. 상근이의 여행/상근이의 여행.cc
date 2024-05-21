#include<stdio.h>
int main(){
    //freopen("input.txt","r",stdin);
    int tc, n, m, a, b;
    scanf("%d", &tc);
    for(int t=1; t<=tc; t++){
        scanf("%d %d", &n, &m);
        for(int i=0; i<m; i++){
            scanf("%d %d", &a, &b);
        }
        printf("%d\n", n-1);
    }
    return 0;
}