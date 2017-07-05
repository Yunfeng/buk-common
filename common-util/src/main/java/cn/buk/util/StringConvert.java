package cn.buk.util;

public class StringConvert
{

   public StringConvert()
   {
   }

 public String dealNull(String s)
 {
     String s1 = null;
     if(s == null)
         s1 = "";
     else
         s1 = s;
     return s1;
 }

 public String replace(String s, String s1, String s2)
 {
     String as[] = split(s, s1);
     String s3 = null;
     if(as.length != 0)
     {
         s3 = as[0];
         for(int i = 0; i < as.length - 1; i++)
             s3 = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(dealNull(s3))))).append(s2).append(as[i + 1])));

     }
     return dealNull(s3);
 }

 public String[] split(String s, String s1)
 {
     int i = 0;
     int j = 0;
     //boolean flag = false;
     int i1 = s1.length();
     if(s.compareTo("") != 0)
     {
         if(s.indexOf(s1) != -1)
         {
             j = s.indexOf(s1);
             int j1 = 1;
             do
             {
                 if(s.indexOf(s1, j + i1) != -1)
                 {
                     j = s.indexOf(s1, j + i1);
                     i = j1;
                 } else
                 {
                     i += 2;
                     break;
                 }
                 j1++;
             } while(true);
         } else
         {
             i = 1;
         }
     } else
     {
         i = 0;
     }
     j = 0;
     //flag = false;
     String as[] = new String[i];
     if(s.compareTo("") != 0)
     {
         if(s.indexOf(s1) != -1)
         {
             int k = s.indexOf(s1);
             as[0] = s.substring(0, k);
             int k1 = 1;
             do
             {
                 if(s.indexOf(s1, k + i1) != -1)
                 {
                     int l = s.indexOf(s1, k + i1);
                     as[k1] = s.substring(k + i1, l);
                     k = s.indexOf(s1, k + i1);
                 } else
                 {
                     as[k1] = s.substring(k + i1, s.length());
                     break;
                 }
                 k1++;
             } while(true);
         } else
         {
             as[0] = s.substring(0, s.length());
             return as;
         }
     } else
     {
         return as;
     }
     return as;
 }
}