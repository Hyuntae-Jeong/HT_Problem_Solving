import java.util.*;
import java.util.stream.Collectors;

class Solution {
    
    static int[] todayDate;
    static Map<String, Integer> termMap;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        convertDataType(today, terms, privacies);
        
        int[] answer = findExpiredPrivacy(privacies);
        
        return answer;
    }
    
    public void convertDataType(String today, String[] terms, String[] privacies) {
        // [index] 0: year, 1: month, 2:date
        todayDate = Arrays.stream(today.split("\\."))
            .mapToInt(Integer::valueOf)
            .toArray();
        
        // key: 약관 종류, value: 유효기간
        termMap = Arrays.stream(terms)
            .map(term -> term.split(" "))
            .collect(Collectors.toMap(
                parts -> parts[0],
                parts -> Integer.parseInt(parts[1])
            ));
        
        /* for (Map.Entry<String, Integer> entry : termMap.entrySet()) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        } */
    }
    
    public int[] findExpiredPrivacy(String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        
        for (int i = 0; i < privacies.length; i++) {
            String parts[] = privacies[i].split(" ");
            
            int[] date = Arrays.stream(parts[0].split("\\."))
                .mapToInt(Integer::valueOf)
                .toArray();
            
            // 날짜 계산하기: 먼저 수집 일자에서 하루 뺀 뒤에 유효기간을 뺀다
            
            // 일 빼기 (-1 day)
            date[2] -= 1;
            if (date[2] <= 0) {
                date[1] -= 1;
                date[2] += 28;
            }
            
            /* for (int x = 0; x < 3; x++) {
                System.out.printf("%d ", date[x]);
            }
            System.out.println(); */
            
            // 월 더하기
            date[1] += termMap.get(parts[1]);            
            while (date[1] > 12) {
                date[0] += 1;
                date[1] -= 12;
            }
            
           
            
            // 날짜 비교하기: todayDate보다 date가 과거이면 만료된 것
            if (todayDate[0] > date[0]) {
                answer.add(i+1);
            } else if (todayDate[0] == date[0]) {
                if (todayDate[1] > date[1]) {
                    answer.add(i+1);
                } else if (todayDate[1] == date[1]) {
                    if (todayDate[2] > date[2]) {
                        answer.add(i+1);
                    }
                }
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}