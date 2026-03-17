import java.util.*;

class Solution {
    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, 0, 0, "");
        return res;
    }

    void backtrack(String s, int idx, int parts, String curr) {

        if (parts == 4 && idx == s.length()) {
            res.add(curr.substring(0, curr.length() - 1));
            return;
        }

        if (parts == 4 || idx == s.length()) return;

        for (int len = 1; len <= 3; len++) {

            if (idx + len > s.length()) break;

            String sub = s.substring(idx, idx + len);

            if ((sub.startsWith("0") && sub.length() > 1) || Integer.parseInt(sub) > 255)
                continue;

            backtrack(s, idx + len, parts + 1, curr + sub + ".");
        }
    }
}