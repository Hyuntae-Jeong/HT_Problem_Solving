#include <cstdio>
#include <algorithm>
#include <ctime>
#include <cstdlib>
#include <iostream>
#include <vector>
#include <queue>
#include <math.h>

using namespace std;

class Graph
{
public:
    int N, M, T, S;

    vector < pair< int , int > > *up;
    vector < pair< int , int > > *down;
    int height[26][26]={0, };
    
    int nNode, nNode_up, nNode_left;

    Graph() {}
    Graph(int temp){
        scanf("%d %d" , &N, &M);
        scanf("%d %d", &T, &S);
        up = new vector< pair< int , int > >[ N*M+1 ]; // edges[i].pair<j, weight>
        down = new vector< pair< int , int > >[ N*M+1 ];
        int diff_up, diff_left;
        char c;
        int c_value;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                cin >> c;
                if(c >= 'A' && c <= 'Z') c_value = (int)(c - 'A');
                else if(c >= 'a' && c <= 'z') c_value = (int)(c - 'a' + 26);
                //printf("%d %d : %d\n", i, j, c_value);
                height[i][j] = c_value;
                nNode = (i-1)*M + j;
                //위 노드랑 관계
                diff_up = height[i][j] - height[i-1][j];
                if((i > 1) && ( abs( diff_up ) <= T )){
                    nNode_up = (i-2)*M + j;

                    if(diff_up > 0){
                        up[nNode].push_back( make_pair( nNode_up , 1 ) );
                        down[nNode].push_back( make_pair( nNode_up , diff_up*diff_up ) );

                        up[nNode_up].push_back( make_pair( nNode , diff_up*diff_up ) );
                        down[nNode_up].push_back( make_pair( nNode , 1 ) );
                    }
                    else if(diff_up < 0){
                        up[nNode].push_back( make_pair( nNode_up, diff_up*diff_up) );
                        down[nNode].push_back( make_pair( nNode_up , 1 ) );

                        up[nNode_up].push_back( make_pair( nNode, 1 ) );
                        //down[nNode_up].push_back( make_pair( nNode , (int)pow( diff_up , 2) ) );
                        down[nNode_up].push_back( make_pair( nNode , diff_up*diff_up ) );
                    }
                    else if(diff_up == 0){
                        up[nNode].push_back( make_pair( nNode_up, 1 ) );
                        down[nNode].push_back( make_pair( nNode_up , 1 ) );

                        up[nNode_up].push_back( make_pair( nNode, 1 ) );
                        down[nNode_up].push_back( make_pair( nNode , 1 ) );
                    }
                }
                //왼쪽 노드랑 관계
                diff_left = height[i][j] - height[i][j-1];
                if((j > 1) && ( abs( diff_left ) <= T )){
                    nNode_left = nNode-1;
                    if(diff_left > 0){
                        up[nNode].push_back( make_pair( nNode_left, 1 ) );
                        down[nNode].push_back( make_pair( nNode_left , diff_left*diff_left ) );

                        up[nNode_left].push_back( make_pair( nNode , diff_left*diff_left ) );
                        down[nNode_left].push_back( make_pair( nNode , 1 ) );
                    }
                    else if(diff_left < 0){
                        up[nNode].push_back( make_pair( nNode_left, diff_left*diff_left ) );
                        down[nNode].push_back( make_pair( nNode_left , 1 ) );

                        up[nNode_left].push_back( make_pair( nNode, 1 ) );
                        down[nNode_left].push_back( make_pair( nNode , diff_left*diff_left ) );
                    }
                    else if(diff_left == 0){
                        up[nNode].push_back( make_pair( nNode_left, 1 ) );
                        down[nNode].push_back( make_pair( nNode_left , 1 ) );

                        up[nNode_left].push_back( make_pair( nNode, 1 ) );
                        down[nNode_left].push_back( make_pair( nNode , 1 ) );
                    }
                }
            }
        }
    }
};

int Dijkstra_up(Graph &_g)
{
    bool *visit = new bool [ _g.N*_g.M+1];
    int *dist = new int[ _g.N*_g.M+1 ];
    int _stIdx = 1;

    for(int i=1; i<=_g.N*_g.M; i++){
        visit[i] = false;
        dist[i] = 2147483647;
    }

    dist[_stIdx] = 0;

    priority_queue< pair<int,int> , vector< pair<int,int> > , greater< pair<int,int> > > pq; 
    pq.push( make_pair( 0 , _stIdx ) );

    for(;;){
        if( pq.empty() ){ break; }
        
        pair<int, int> x = pq.top(); pq.pop();
        if(visit[x.second]) {continue;}

        visit[x.second] = true;

        for( int i=0; i<_g.up[x.second].size(); i++){
            int idx = _g.up[x.second][i].first;
            int w = _g.up[x.second][i].second;
            if(!visit[idx] && (dist[idx] > dist[x.second] + w) ) //difference btw prim's algorithm
            {
                pq.push( make_pair(dist[x.second] + w, idx));
                dist[idx] = dist[x.second] + w;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //going back down
    int *dist_d = new int[ _g.N*_g.M+1 ];

    for(int i=1; i<=_g.N*_g.M; i++){
        visit[i] = false;
        dist_d[i] = 1000001;
    }

    dist_d[_stIdx] = 0;

    pq.push( make_pair( 0 , _stIdx ) );

    for(;;){
        if( pq.empty() ){ break; }
        
        pair<int, int> x = pq.top(); pq.pop();
        if(visit[x.second]) {continue;}

        visit[x.second] = true;

        for( int i=0; i<_g.down[x.second].size(); i++){
            int idx = _g.down[x.second][i].first;
            int w = _g.down[x.second][i].second;
            if(!visit[idx] && (dist_d[idx] > dist_d[x.second] + w) ) //difference btw prim's algorithm
            {
                pq.push( make_pair(dist_d[x.second] + w, idx));
                dist_d[idx] = dist_d[x.second] + w;
            }
        }
    }

    //find max height;
    int max_height=-1, max_dist=-1, hI, hJ;
    for(int i=1; i<=_g.N*_g.M; i++){
        if(dist[i] + dist_d[i] <= _g.S){
            if(dist[i]+dist_d[i] < max_dist) continue;
            hI = i/_g.M;
            hJ = i%_g.M;
            if(hJ == 0) hJ=_g.M;
            else if(hJ > 0) hI++;
            if(_g.height[hI][hJ] > max_height) max_height = _g.height[hI][hJ];
        }
    }
    //printf("max height: %d\n", max_height);
    delete [] dist_d;
    delete [] visit;
    delete [] dist;

    return max_height;
}

int main(){
    Graph g(1);
    printf("%d\n", Dijkstra_up(g));
    return 0;
}