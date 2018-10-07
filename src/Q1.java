import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Q1 {

    public static void main(String args[]) throws IOException {

        List<String> names = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첨부된 샘플로 테스트하려면 아래 코드 사용.
//        try {
//            File file = new File("./Vote_testcase/1.input.txt");
//            br = new BufferedReader(new FileReader(file));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        String line = null;
        while((line = br.readLine()) != null) {
            names.add(line);
        }

        HashMap<String, Integer> nameCountMap = new HashMap<>();

        int totalMaxCount = 0;
        for (String name : names) {
            int count = 1;
            if (nameCountMap.containsKey(name)) count = nameCountMap.get(name)+1;
            if (count > totalMaxCount) totalMaxCount = count;
            nameCountMap.put(name, count);
        }

        int finalTotalMaxCount = totalMaxCount;
        String result = nameCountMap.entrySet().stream().filter(entry -> entry.getValue() == finalTotalMaxCount).map(Map.Entry::getKey).sorted().collect(Collectors.joining("\n"));
        System.out.println(result);
    }
}
