import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        StringBuilder uppers = new StringBuilder();
        StringBuilder lowers = new StringBuilder();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)>='a'&&str.charAt(i)<='z' ){
                sb.append(str.charAt(i));
                lowers.append(str.charAt(i));
            }
            if(str.charAt(i)>='A'&&str.charAt(i)<='Z'){
                sb.append(str.charAt(i));
                uppers.append(str.charAt(i));
            }
        }
        char[] lowerChars = lowers.toString().toCharArray();
        char[] upperChars = uppers.toString().toCharArray();
        Arrays.sort(lowerChars);
        Arrays.sort(upperChars);
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        List<Character> lowerList = new LinkedList<>();
        List<Character> upperList = new LinkedList<>();
        for(int i=0,j=0;i<lowerChars.length && j<upperChars.length;){
            int lo = lowerChars[i] - 'a';
            int hi = upperChars[j] - 'A';
            if(lo<hi){
                lowerChars[i] = ' ';
                i++;
            }else if(lo == hi){
                sb1.append(String.valueOf(lowerChars[i]));
                sb2.append(String.valueOf(upperChars[j]));
                lowerList.add(lowerChars[i]);
                upperList.add(upperChars[j]);
                i++;
                j++;
            }else{
                upperChars[j] = ' ';
                j++;
            }
        }
        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
        List<String> res = new LinkedList<>();
        while(lowerList.size()>0){
            int index = 0;
            StringBuilder result = new StringBuilder();
            List<Integer> delList = new ArrayList<>();
            result.append(upperList.get(index));
            result.append(lowerList.get(index));
            delList.add(index);
            while(index<lowerList.size()-1 && ((lowerList.get(index) == lowerList.get(index+1)) ||
                    (index<lowerList.size()-1 && (lowerList.get(index+1)-'a')-1 == (lowerList.get(index)-'a')))){
                if(index<lowerList.size()-1 && (lowerList.get(index) == lowerList.get(index+1))){
                    index++;
                }
                else if(index<lowerList.size()-1 && (lowerList.get(index+1)-'a')-1 == (lowerList.get(index)-'a')){
                    index++;
                    result.append(upperList.get(index));
                    result.append(lowerList.get(index));
                    delList.add(index);

                }
            }
            int tmp = 0;
            for(Integer i:delList){
                lowerList.remove(i-tmp);
                upperList.remove(i-tmp);
                tmp=i+1;
            }
            res.add(result.toString());
        }
        Collections.sort(res, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.charAt(0) - o2.charAt(0)==0){
                    return o2.length()-o1.length();
                }else {
                    return o1.charAt(0) - o2.charAt(0);
                }
            }
        });
        for(int i=0;i<res.size();i++){
            System.out.println(res.get(i));
        }
    }
}