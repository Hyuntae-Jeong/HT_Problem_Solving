import java.util.*;
import java.util.stream.Collectors;
class Solution {
    static int todayDate = 0;
    static Map<String, Integer> termMap;

    public static int[] solution(String today, String[] terms, String[] privacies) {
        convertDataType(today, terms);
        int[] answer = findExpiredPrivacy(privacies);
        /* for (int i = 0; i < answer.length; i++) {
            System.out.printf("%d ", answer[i]);
        } */
        return answer;
    }

    static void convertDataType(String today, String[] terms) {
        // 오늘 날짜를 일수로 변경하기 (한달에 28일 고정)
        String[] todayValue = today.split("\\.");
        todayDate += Integer.parseInt(todayValue[0]) * 12 * 28;     // year
        todayDate += Integer.parseInt(todayValue[1]) * 28;          // month
        todayDate += Integer.parseInt(todayValue[2]);               // date

        // 약관 유효기간 Map 형태로 변환하기
        termMap = new HashMap<>(terms.length);
        for (int i = 0; i < terms.length; i++) {
            termMap.put(terms[i].split(" ")[0], Integer.valueOf(terms[i].split(" ")[1]));
        }
    }

    static int[] findExpiredPrivacy(String[] privacies) {
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] dateValue = privacies[i].split(" ")[0].split("\\.");
            int expireDate = Integer.parseInt(dateValue[0]) * 12 * 28;     // year
            expireDate += Integer.parseInt(dateValue[1]) * 28;            // month
            expireDate += Integer.parseInt(dateValue[2]);                 // date

            expireDate -= 1;
            expireDate += 28 * termMap.get(privacies[i].split(" ")[1]);

            if (todayDate > expireDate) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}