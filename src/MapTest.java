import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

public class MapTest {
    public static void main(String[] args){
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("aa", 7);
        map.put("b", 2);
        map.put("c", 0);
        map.put("cc", 3);
        map.put("ccc", 5);
        map.put("d", 6);
        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(2);set.add(3);
        List<Integer> list = new ArrayList<>(set);
        List<Integer> list1 = new ArrayList<Integer>(){{add(2);add(3);add(2);}};
        Set<Integer> set1 = new HashSet<>(list1);
        for(int i:list){
            System.out.println(i);
        }
        for(int i:set1){
            System.out.println(i);
        }

        MapTest mapTest = new MapTest();
        Comparator<Map.Entry<String, Integer>> comparator1 = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() - o2.getValue() == 0){
                    return o1.getKey().compareTo(o2.getKey());
                }else {
                    return o1.getValue() - o2.getValue();
                }
            }
        };
        mapTest.printAfterSort(map, comparator1);
        mapTest.printMap(map);
    }

    /**
     * 对Map排序
     * 按某种顺序输出map中的entry
     */
    private void printAfterSort(Map<String, Integer> map,
                                Comparator<Map.Entry<String, Integer>> comparator){
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(comparator);
        for(Map.Entry entry:list){
            System.out.println(entry.getKey()+"--"+entry.getValue());
        }
    }

    /**
     * Map的遍历
     */
    private void printMap(Map<String, Integer> map){
        // for-each 遍历
        for(Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey()+"--"+entry.getValue());
        }

        //迭代器遍历
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println(entry.getKey()+"--"+entry.getValue());
        }
    }

}
