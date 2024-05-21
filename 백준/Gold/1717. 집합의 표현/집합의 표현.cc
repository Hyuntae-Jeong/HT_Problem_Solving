#include<cstdio>

int find_root(int node, int *set){
    while(1){
        if(set[node]==node) return node;
        node = set[node];
    }
}

int main(){

    int n, m, op, a, b;
    int root_a, root_b;

    scanf("%d %d", &n, &m);
    int *set = new int[n+1];
    int *height = new int[n+1];
    
    for(int i=0; i<=n; i++){ set[i]=i; height[i]=0; }

    for(int i=0; i<m; i++){
        scanf("%d %d %d", &op, &a, &b);

        root_a = find_root(a, set);
        root_b = find_root(b, set);

        if(op == 0){
            if(height[root_a] == height[root_b]){
                set[root_b] = root_a;
                height[root_a]++;
            }
            else if(height[root_a] > height[root_b]){
                set[root_b] = root_a;
            }
            else set[root_a] = root_b;
        }
        else if(op == 1){
            if(root_a == root_b) printf("YES\n");
            else printf("NO\n");
        }
    }
    
    delete[] height;
    delete[] set;
    
    return 0;
}