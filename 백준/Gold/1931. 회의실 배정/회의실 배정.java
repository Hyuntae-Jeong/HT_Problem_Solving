import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Meeting implements Comparable<Meeting> {
    int start;
    int end;

    public Meeting(int s, int e) {
        this.start = s;
        this.end = e;
    }

    @Override
    public int compareTo(Meeting o) {
        if (this.end < o.end) return -1;
        else if (this.end == o.end) {
            if (this.start < o.start) return -1;
            else if (this.start > o.start) return 1;
            else return 0;
        }
        else return 1;
    }

}
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static ArrayList<Meeting> meetings;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        meetings = new ArrayList<Meeting>();

        getMeetingInfos();
//        printMeetingInfos();
        planMeetings();
    }

    private static void planMeetings() {
        int meetingCount = 0;
        int lastEndMeetingTime = 0;

        for (Meeting meeting : meetings) {
            if (meeting.start >= lastEndMeetingTime) {
                lastEndMeetingTime = meeting.end;
                meetingCount++;
            }
        }

        System.out.println(meetingCount);
    }

    private static void printMeetingInfos() {
        for (Meeting meeting : meetings) {
            System.out.println(meeting.start + " , " + meeting.end);
        }
    }

    private static void getMeetingInfos() throws IOException {
        StringTokenizer token;

        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(br.readLine());
            meetings.add(new Meeting(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken())));
        }

        Collections.sort(meetings);
    }
}