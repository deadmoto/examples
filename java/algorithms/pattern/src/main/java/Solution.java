import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Solution {

    private static boolean matches(String pattern, String input, int[] chunks) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int start = 0;
        for (int i = 0; i < chunks.length; i++) {
            String chunk = input.substring(start, start + chunks[i]);
            int hashCode = chunk.hashCode();
            char key = pattern.charAt(i);
            if (hashMap.containsKey(key)) {
                if (!hashMap.get(key).equals(hashCode)) {
                    return false;
                }
            } else if (hashMap.containsValue(hashCode)) {
                return false;
            } else {
                hashMap.put(key, hashCode);
            }
            start += chunks[i];
        }
        return true;
    }

    static int wordpattern(String pattern, String input) {
        if (pattern.length() == 1) return 1;

        HashSet<Character> charSet = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            charSet.add(pattern.charAt(i));
        }

        int[] chunks = new int[pattern.length()];
        Arrays.fill(chunks, 1);

        LinkedList<int[]> linkedList = new LinkedList<>();
        linkedList.add(chunks);
        while (!linkedList.isEmpty()) {
            int[] first = linkedList.removeFirst();

            int sum = 0;
            for (int i : first) {
                sum += i;
            }

            if (sum > input.length()) continue;
            if (sum == input.length()) {
                if (matches(pattern, input, first))
                    return 1;
            }

            for (Character character : charSet) {
                int[] next = Arrays.copyOf(first, first.length);
                for (int i = 0; i < next.length; i++) {
                    if (character.equals(pattern.charAt(i))) {
                        next[i]++;
                    }
                }
                linkedList.add(next);
            }
        }

        return 0;
    }
}
