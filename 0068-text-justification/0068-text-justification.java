import java.util.*;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0;

        while (i < words.length) {

            int j = i;
            int len = 0;

            while (j < words.length && len + words[j].length() + (j - i) <= maxWidth) {
                len += words[j].length();
                j++;
            }

            int spaces = maxWidth - len;
            int gaps = j - i - 1;

            StringBuilder line = new StringBuilder();

            if (j == words.length || gaps == 0) {

                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) {
                        line.append(" ");
                        spaces--;
                    }
                }

                while (spaces-- > 0)
                    line.append(" ");
            } 
            else {

                int spaceEach = spaces / gaps;
                int extra = spaces % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {
                        for (int s = 0; s < spaceEach; s++)
                            line.append(" ");

                        if (extra-- > 0)
                            line.append(" ");
                    }
                }
            }

            res.add(line.toString());
            i = j;
        }

        return res;
    }
}
