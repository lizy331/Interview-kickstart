package InterviewByCompany.Walmart.May0324;

/*
We are working on a security system for a badged-access room in our company's building.

Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collections:

1. All employees who didn't use their badge while exiting the room - they recorded an enter without a matching exit. (All employees are required to leave the room before the log ends.)

2. All employees who didn't use their badge while entering the room - they recorded an exit without a matching enter. (The room is empty when the log begins.)

Each collection should contain no duplicates, regardless of how many times a given employee matches the criteria for belonging to it.

records1 = [
  ["Paul",     "enter"],
  ["Pauline",  "exit"],
  ["Paul",     "enter"],
  ["Paul",     "exit"],
  ["Martha",   "exit"],
  ["Joe",      "enter"],
  ["Martha",   "enter"],
  ["Steve",    "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "enter"],
  ["Joe",      "enter"],
  ["Curtis",   "exit"],
  ["Curtis",   "enter"],
  ["Joe",      "exit"],
  ["Martha",   "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "exit"],
  ["Joe",      "enter"],
  ["Joe",      "enter"],
  ["Martha",   "exit"],
  ["Joe",      "exit"],
  ["Joe",      "exit"]
]


Paul - enter

Paul - exit


leave without badge result list - res1

enter without badge result list - res2

List<List> [res1,res2]

case 1. leave without bage

Paul - enter

Paul - enter

res.add(Paul)

case 2.


Expected output: ["Steve", "Curtis", "Paul", "Joe"], ["Martha", "Pauline", "Curtis", "Joe"]

Other test cases:

records2 = [
  ["Paul", "enter"],
  ["Paul", "exit"],
]

Expected output: [], []

records3 = [
  ["Paul", "enter"],
  ["Paul", "enter"],
  ["Paul", "exit"],
  ["Paul", "exit"],
]

Expected output: ["Paul"], ["Paul"]

records4 = [
  ["Raj", "enter"],
  ["Paul", "enter"],
  ["Paul", "exit"],
  ["Paul", "exit"],
  ["Paul", "enter"],
  ["Raj", "enter"],
]

Expected output: ["Raj", "Paul"], ["Paul"]

All Test Cases:
mismatches(records1) => ["Steve", "Curtis", "Paul", "Joe"], ["Martha", "Pauline", "Curtis", "Joe"]
mismatches(records2) => [], []
mismatches(records3) => ["Paul"], ["Paul"]
mismatches(records4) => ["Raj", "Paul"], ["Paul"]

n: length of the badge records array


Paul - enter

Paul - exit


leave without badge result list - res1

enter without badge result list - res2

List<List> [res1,res2]

case 1. leave without bage

Paul - enter

Paul - enter

res.add(Paul)

case 2.


*/

import java.io.*;
import java.util.*;

public class CheckBedage {
    public static List<List<String>> check(String[][] logs){
        // init the map
        Map<String,String> map = new HashMap<>();

        // init result list
        // res1 indicating a person leave withou using badge
        HashSet<String> res1 = new HashSet<>();

        // res2 indicating a person enter with using badge
        HashSet<String> res2 = new HashSet<>();


        // loop the array
        // check if exist, if not put in map
        // if the exiting value is enter,and cur value is alos enter, then put in res1, (if a person onlys shows in the logs one time,and it is enter, which also means leave without badge)

        // if the... is exit, but the cur value is also eixt, then put in res2, (if a person first show up in the log and the operation is "exit", then we put int the res2, )
        for(int i = 0;i<logs.length;i++){

            String person = logs[i][0];
            String action = logs[i][1];

            if(action.equals("enter")){
                if(map.containsKey(person) && map.get(person).equals("enter")){
                    res1.add(person);
                }
                map.put(person,action);
            }else{
                if(map.containsKey(person) && map.get(person).equals("exit")){
                    res2.add(person);
                }else if(!map.containsKey(person)){
                    // first time show in log, but it is an exit, so he must be enter before and did not using badge
                    res2.add(person);
                }
                map.put(person,action);
            }
        }


        // check person who is still in action "enter", they must leave without badge
        for(Map.Entry<String,String> entry : map.entrySet()){
            if (entry.getValue().equals("enter")){
                res1.add(entry.getKey());
            }
        }

        // create a noe more list to add the res1 and res2
        List<List<String>> result = new ArrayList<>();

        // new result list to copy the value from set to list
        List<String> list1 = new ArrayList<>();
        for(String s : res1){
            list1.add(s);
        }

        List<String> list2 = new ArrayList<>();
        for(String s : res2){
            list2.add(s);
        }

        result.add(list1);
        result.add(list2);


        return result;
    }

    public static void main(String[] argv) {
        String[][] records1 = {
                {"Paul", "enter"},
                {"Pauline", "exit"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Martha", "exit"},
                {"Joe", "enter"},
                {"Martha", "enter"},
                {"Steve", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "enter"},
                {"Joe", "enter"},
                {"Curtis", "exit"},
                {"Curtis", "enter"},
                {"Joe", "exit"},
                {"Martha", "enter"},
                {"Martha", "exit"},
                {"Jennifer", "exit"},
                {"Joe", "enter"},
                {"Joe", "enter"},
                {"Martha", "exit"},
                {"Joe", "exit"},
                {"Joe", "exit"},
        };

        String[][] records2 = {
                {"Paul", "enter"},
                {"Paul", "exit"},
        };

        String[][] records3 = {
                {"Paul", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
        };

        String[][] records4 = {
                {"Raj", "enter"},
                {"Paul", "enter"},
                {"Paul", "exit"},
                {"Paul", "exit"},
                {"Paul", "enter"},
                {"Raj", "enter"},
        };

        System.out.println(check(records1));
        System.out.println(check(records2));
        System.out.println(check(records3));
        System.out.println(check(records4));
    } // main
}
