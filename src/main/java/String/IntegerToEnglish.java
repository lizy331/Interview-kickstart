package String;

public class IntegerToEnglish {


    public String[] oneToNt;
    public String[] twToNty;
    public String numberToWords(int num) {
        oneToNt = new String[]{"","One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

        twToNty = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        // num can be divide into 4 group,
        // Billion, Million, Thousand, hun
        // the largest number possible 2,147,483,647
        String res = helper(num%1000);

        // System.out.println(res);

        // res supposed six hund fourty seven
        // num /=1000 => 2,147,483

        // make a list of String of {Billion, Million, Thousand}
        String[] prefix = new String[]{"Thousand", "Million", "Billion"};
        for(int i = 0;i<prefix.length;i++){
            num /= 1000;
            // System.out.println(num);
            if(num==0){
                break;
            }
            res = num%1000 == 0 ? res : helper(num%1000) + " " + prefix[i] + " " + res;
            // System.out.println(res);
        }

        res = res.trim();

        return res.equals("") ? "Zero" : res;
    }

    public String helper(int num){
        StringBuilder res = new StringBuilder("");
        int a = num/100;
        int b = num%100;
        int c = num%10;

        if(a>0){
            res.append(oneToNt[a]);
            if(b>0){
                res.append(" Hundred ");
            }else{
                res.append(" Hundred");
            }
        }
        if(b>=20){
            res.append(twToNty[b/10]);
            if(c>0){
                res.append(" ").append(oneToNt[c]);
            }
        }else{
            res.append(oneToNt[b]);
        }

        return res.toString();

    }
}
