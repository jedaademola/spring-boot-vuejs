package com.poc.microservice;

public class TestKforce {

   public static String addKbeforeFs(String text) {
       // String output ="";
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<text.length(); ++i ) {
            char temp = text.charAt(i);
            if( temp =='f' || temp == 'F' ){
               // output = output + 'K' + temp;
                sb.append("K" + temp);
            } else {
                //output = output + temp;
                sb.append(temp);
            }
        }
        return sb.toString();
      // return output;
    }

    public static  void main (String args []){
        System.out.println(addKbeforeFs("force"));//Kforce
        System.out.println(addKbeforeFs("fluffy"));//KfluKfKfy
    }

}
