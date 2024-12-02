package InterviewByCompany.DoorDash;

import java.util.*;

/*
直到最近才知道們衝是什麼意思..!
implement a key value store with four functions
Create (key, val)
set(key, val) (throw exception if path is not continuous. e.g, existing path "level1" set "level1/level2/level3" will fail but set "level1/level2" will success)
get(key)
delete (key) - only delete the deepest children (e.g, delete("level1/level2/level3) means delete "level3")
key is a path like string ("level1/level2/level3").
其實總體而言算是簡單的(其實就是Trie)，但是寫的有點慢，加上要求exception handling，還要跑過幾個test。 結論是掛在一堆compiler error. 寫cpp 沒怎麼寫過exception handling 。
回家面壁思過。加強exception handling 與coding speed.
LC 1166
https://www.1point3acres.com/bbs/thread-1089803-1-1.html

是LC 1166 的变态难版 key-value store for storing path.
path 是类似/first/second 这种 root 是/ and root 对应的value 是#
一共要implement 4 个 method
- void create(String path, String value) throw Exception
   - 只能create 深一层， 不能update 当层的value 也不能create 没有parent的level
- String get(String path) throw Exception
  - return 当前层的 value - throw exception 如果不存在
- void set(String path, String value) throw Exception
- 只能update 存在的level 对应的value，如果不存在 throw exception
- void delete(String path) throw Exception
- 只能删除没有children 的level 不能删除 root
应该还有其他的限制 不太记得了 corner case太多了

 */
public class DesignFileSystem {

    class Trie{
        String pathName;
        String value;
        Map<String,Trie> childMap;

        public Trie(String p){
            this.pathName = p;
            childMap = new HashMap<>();
        }
    }

    public Trie root;
    public void FileSystem() {
        root = new Trie("/");
        root.value = "#";
    }

    public String[] parseString(String path){
        return path.split("/");
    }
    public boolean create(String path, String value) throws Exception {
        String[] folders = parseString(path);
        Trie pointer = root;
        int n = folders.length;
        for(int i = 0;i<n;i++){
            String folder = folders[i];
            if(!pointer.childMap.containsKey(folder)){
                if(i==n-1){
                    pointer.childMap.put(folder,new Trie(folder));
                }else {
                    throw new Exception("cant create path without parent");
                }
            }
            pointer = pointer.childMap.get(folder);
        }
        if (pointer.value != null) {
            throw new Exception("Path already exists: " + path);
        }
        pointer.value = value;
        return true;
    }

    public String get(String path) throws Exception {
        String[] folders = parseString(path);
        int n = folders.length;
        Trie pointer = root;
        for(int i = 0;i<n;i++){
            String folder = folders[i];
            if(!pointer.childMap.containsKey(folder)){
                throw new Exception("path did not find");
            }else{
                pointer = pointer.childMap.get(folder);
            }
        }

        return pointer.value;

    }

    public void set(String path, String value) throws Exception {
        String[] folders = parseString(path);
        Trie pointer = root;
        int n = folders.length;
        for(int i = 0;i<n;i++){
            String folder = folders[i];
            if(!pointer.childMap.containsKey(folder)){
                throw new Exception("path does not exist");
            }else{
                pointer = pointer.childMap.get(folder);
            }
        }
        pointer.value = value;
    }

    public void delete(String path) throws Exception{
//        System.out.println("deleting ...: " + path);
        String[] folders = parseString(path);
        Trie pointer = root;
        Trie parent = null;
        String lastFolder = null;

        for (int i = 0; i < folders.length; i++) {
            String folder = folders[i];
//            System.out.println(folder);
            if (!pointer.childMap.containsKey(folder)) {
                throw new Exception("Path does not exist: " + path);
            }
            parent = pointer;
            pointer = pointer.childMap.get(folder);
            lastFolder = folder;
        }

        if (pointer.childMap.size() > 0) {
            throw new Exception("Cannot delete non-leaf path: " + path);
        }
        if (parent != null && lastFolder != null) {
            parent.childMap.remove(lastFolder);
        }
    }

    public static void main(String[] args) throws Exception {
        DesignFileSystem solution = new DesignFileSystem();

        String testcasePath1 = "leet";
        String testcaseValue1 = "1";


        String testcasePath2 = "leet/code";
        String testcaseValue2 = "2";

        String testcasePath3 = "leet/code/foo/bar";
        String testcaseValue3 = "3";

        solution.FileSystem();
        solution.create(testcasePath1,testcaseValue1);
        System.out.println(solution.get(testcasePath1)); // 1

        solution.create(testcasePath2,testcaseValue2);
        System.out.println(solution.get(testcasePath2)); // 2

//        solution.create(testcasePath3,testcaseValue3);
//        System.out.println(solution.get(testcasePath3)); // throw exception


        String testcasePath4 = "leet/folder1";
        String testcaseValue4 = "4";
        solution.create(testcasePath4,testcaseValue4);
        System.out.println(solution.get(testcasePath4)); // 4


        String testcasePath5 = "leet/code/foo";
        String testcaseValue5 = "5";
        solution.create(testcasePath5,testcaseValue5);
        System.out.println(solution.get(testcasePath5)); // 5

        solution.delete(testcasePath5);
        System.out.println(solution.get(testcasePath5)); // throw exception, foo is been deleted

//        solution.delete("leet"); // throw exception, leet has two children "code" and "folder1"





    }
}
