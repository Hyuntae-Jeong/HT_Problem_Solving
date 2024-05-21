#include <cstdio>
#include <map>
#include <queue>
#include <cmath>
#include <vector>
#include <algorithm>
using namespace std;
const double eps = 1e-2;
#define MN 50005
struct Point {
    int x, y;
    bool operator < (const Point p) const {
        if (p.x != x) return p.x < x;
        return p.y < y;
    }
} b[MN], t;
int n, F;
double d[MN];       //d[x] : x번 정점까지의 최단 거리
map <Point, int> c;   //c[p] : 좌표가 p인 점의 정점 번호
vector <int> v[MN];   //v[x] : x번 정점에서 갈 수 있는 징검 다리 번호
struct _Q {
    int who;
    double dist;
    bool operator < (const _Q q) const { return q.dist < dist; }
} now, nxt;
int main() {
    scanf("%d%d", &n, &F);
    n++;
    vector <int> fin; //y = F인 정점의 번호 집합
      
    //1번 징검다리는 (0, 0)
    t.x = t.y = 0;
    c[t] = 1;
    if (F == 0) fin.push_back(1);
    b[1].x = b[1].y = 0; d[1] = 0;
  
    for (int i = 2; i <= n; i++) {
        scanf("%d%d", &b[i].x, &b[i].y);
        d[i] = -1;
        if (b[i].y == F) fin.push_back(i);
        c[b[i]] = i;
    }
  
    for (int i = 1; i <= n; i++) {
        for (int px = -2; px <= 2; px++) {
            for (int py = -2; py <= 2; py++) {
                if (px == 0 && py == 0) continue;
                t.x = b[i].x + px;
                t.y = b[i].y + py;
                if (c[t]) v[i].push_back(c[t]);
            }
        }
    }
      
    priority_queue <_Q> Q;
    now.who = 1; now.dist = 0;
    Q.push(now);
    while (!Q.empty()) {
        now = Q.top(); Q.pop();
        if (now.dist > d[now.who] + eps) continue;
        int x = now.who;
        double y = d[x];
        for (auto &z : v[x]) {
            double dx = b[x].x - b[z].x;
            double dy = b[x].y - b[z].y;
            double cost = y + sqrt(dx*dx + dy*dy);
            if (d[z] < 0 || cost < d[z]) {
                nxt.dist = d[z] = cost;
                nxt.who = z;
                Q.push(nxt);
            }
        }
    }
      
    double res = -1;
    for (auto &x : fin) if (d[x] > 0  && (res < -eps || res > d[x])) res = d[x];
      
    if (res < -eps) puts("-1");
    else {
        long long ans = (res + eps) * 10;
        int mod = ans % 10;
        ans /= 10;
        if (mod >= 5) ans++;
        printf("%lld", ans);
    }
}