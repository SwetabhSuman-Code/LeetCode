import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();

        if (!dict.contains(endWord)) return res;

        Map<String, List<String>> map = new HashMap<>();
        Set<String> curr = new HashSet<>();
        curr.add(beginWord);

        boolean found = false;

        while (!curr.isEmpty() && !found) {

            dict.removeAll(curr);
            Set<String> next = new HashSet<>();

            for (String word : curr) {

                char[] arr = word.toCharArray();

                for (int i = 0; i < arr.length; i++) {

                    char old = arr[i];

                    for (char c = 'a'; c <= 'z'; c++) {

                        arr[i] = c;
                        String newWord = new String(arr);

                        if (!dict.contains(newWord)) continue;

                        next.add(newWord);

                        map.computeIfAbsent(newWord, k -> new ArrayList<>()).add(word);

                        if (newWord.equals(endWord))
                            found = true;
                    }

                    arr[i] = old;
                }
            }

            curr = next;
        }

        List<String> path = new ArrayList<>();
        path.add(endWord);

        dfs(res, map, path, beginWord, endWord);

        return res;
    }

    void dfs(List<List<String>> res, Map<String, List<String>> map,
             List<String> path, String beginWord, String word) {

        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            res.add(temp);
            return;
        }

        if (!map.containsKey(word)) return;

        for (String prev : map.get(word)) {

            path.add(prev);
            dfs(res, map, path, beginWord, prev);
            path.remove(path.size() - 1);
        }
    }
}