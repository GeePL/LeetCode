import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main{
    public static void main(String[] args){
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        //canner sc = new Scanner(System.in);
        int N = 8;
        //sc.nextLine();
        int[] a = {1,1, 3, 4, 5, 6, 7, 8};
        Arrays.sort(a);
        int res = 0;
        if(N<=2){
            System.out.println(res);
            return;
        }
        int index = 0;
        for(int i=0;i<N;i++){
            if(i<1 || a[i]>a[index-1])
                a[index++]=a[i];
        }
        for(int i=0;i<index-2;i++){
            for(int j=i+1;j<index-1;j++){
                for(int k=j+1;k<index;k++){
                    if(helper(a[k],a[j])==1 && helper(a[k],a[i])==1)
                        if(helper(a[j],a[i])==1)
                            res++;
                }
            }
        }

        System.out.println(135*246);
        System.out.println(123*456);
    }

    public static int helper(int x, int y){
        if(x<y){
            int z = x;
            x = y;
            y = z;
        }
        int i = x%y;
        while(i!=0){
            x=y;
            y=i;
            i=x%y;
        }
        return y;
    }
    public static void main2(String[] args){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strs = str.split(" ");
        System.out.println(Arrays.toString(strs));
        for(int i=0;i<strs.length/2;i++){
            String tmp = strs[i];
            strs[i] = strs[strs.length-i-1];
            strs[strs.length-i-1] = tmp;
        }
        System.out.println(Arrays.toString(strs));
        System.out.println(1);
        StringBuilder sb = new StringBuilder();
        sb.insert(0,'a');
        for(int i=0;i<strs.length;i++){
            if(strs[i].endsWith(".")){
                strs[i] = "."+strs[i].substring(0,strs[i].length()-1);
            }
        }
        System.out.println(Arrays.toString(strs));
        String res = "";
        for(String string:strs){
            res+=string+" ";
        }
        System.out.println(res);
    }

    public static void main1(String[] args){
        int[][] mat = {{1,6},{4,8},{11,18},{7,9},{1,5},{1,3},{2,4}};
        Arrays.sort(mat, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });
        int[][] matrix = merge(mat);
        for(int i=0;i<matrix.length;i++)
            System.out.println(Arrays.toString(matrix[i]));
    }

    public static int[][] merge(int[][] matrix){
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        for(int i=0;i<matrix.length;i++){
            Map.Entry<Integer, Integer> map = new AbstractMap.SimpleEntry<>(matrix[i][0],matrix[i][1]);
            while(i<matrix.length-1 && matrix[i+1][0]<=map.getValue()){
                if(matrix[i+1][1]>map.getValue())
                    map.setValue(matrix[i+1][1]);
                i++;
            }
            list.add(map);
        }
        int[][] mat = new int[list.size()][2];
        for(int i=0;i<mat.length;i++){
            mat[i][0] = list.get(i).getKey();
            mat[i][1] = list.get(i).getValue();
        }
        return mat;
    }

    public static int[][] sortString(){
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
        return null;
    }

}