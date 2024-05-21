#include <cstdio>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;

void Critical(int temp){
    int nT;
    scanf("%d", &nT);
    int *task_time = new int[nT+1];
    vector<int> Task[10001];
    int *check_next = new int[nT+1]();
    for(int i=1; i<=nT; i++){
        scanf("%d", &task_time[i]);
        int a;
        while(true){
            scanf("%d", &a);
            if(a == -1) break;
            Task[a].push_back(i);
            check_next[i]++;
        }
    }

    //insert task that has no dependency
    queue<int> run;
    int *total = new int[nT+1]();
    for(int i=1; i<=nT; i++){
        if(!check_next[i]){
            run.push(i);
            total[i] = task_time[i];
        }
    }
    //start doing task
    int cur_task, nxt_task;
    while(!run.empty()){
        cur_task = run.front(); run.pop();
        for(int i=0; i<Task[cur_task].size(); i++){
            nxt_task = Task[cur_task][i];
            check_next[nxt_task]--;

            if(total[nxt_task] < total[cur_task] + task_time[nxt_task])
                total[nxt_task] = total[cur_task] + task_time[nxt_task];
            
            if(!check_next[nxt_task]) run.push(nxt_task);
        }
    }
    for(int i=1; i<=nT; i++){
        printf("%d\n", total[i]);
    }

    delete [] total;
    delete [] task_time;
    delete [] check_next;

    return;
}

int main(){
    Critical(1);
    return 0;
}