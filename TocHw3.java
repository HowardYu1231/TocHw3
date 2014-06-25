//TocHW3
//Name:HowardYu

import org.json.*;
import java.io.*;
import java.net.*;

public class TocHw3 {
	
		 public static void main(String[] argv) throws Exception,IOException,FileNotFoundException{  //throws JSONException
			 
			 try{
				
			 if(argv.length == 4)
			 {
			 
			 System.out.println("Copy JSON file from the URL!");
			 System.out.println("Please wait...");
			 
			 //---
			 //the following code between (//---) referenced from the website below
			 //http://robertvmp.pixnet.net/blog/post/26585200-java---%E8%AE%80%E7%B6%B2%E9%A0%81%E7%AF%84%E4%BE%8B-
			 URL pageUrl = new URL(argv[0]);
			 /*
			 BufferedReader buffer_input_string = new BufferedReader(new InputStreamReader(pageUrl.openStream(), "UTF-8"));
			 BufferedWriter buffer_output_string = new BufferedWriter(new FileWriter("URL.json", false));
			 String oneLine = null ;
			 while ((oneLine = buffer_input_string.readLine()) != null) {
				 	buffer_output_string.write(oneLine);                
	            }
			 buffer_output_string.close();
			 buffer_input_string.close(); 
			//---
			 
			 */
			 //new version - Read URL directly
		     BufferedReader in = new BufferedReader(new InputStreamReader(pageUrl.openStream(),"UTF-8"));
			 JSONArray obj = new JSONArray(new JSONTokener(in));
			 
			 
			 //JSONArray obj = new JSONArray(new JSONTokener(new FileReader(new File("URL.json"))));  
			
			 
			 boolean conti = true ; 
			 String s_to_compare ;//, s_to_find
			 String road_to_compare;//, road_to_find
			 int year_to_compare;
			 int index = 0;
			 int count = 0;
			 int sum = 0;
			 
			 while (conti == true)
			 {
				 try{
					 JSONObject ob = obj.getJSONObject(index);
					 index++;
					 s_to_compare = ob.getString("鄉鎮市區");
					 road_to_compare = ob.getString("土地區段位置或建物區門牌");
					 year_to_compare = ob.getInt("交易年月");
					 
					 //compare
					 //if(s_to_compare.equals(s_to_find))
					 if(s_to_compare.equals(argv[1]))
					 {
						 //if(road_to_compare.indexOf(road_to_find) != -1)
						 if(road_to_compare.indexOf(argv[2]) != -1)
						 {
							 int target_year = year_to_compare-(Integer.valueOf(argv[3])*100);
							 int id_value = ob.getInt("總價元");
							 if(target_year >= 0)
							 {
								 //加入判斷
								 System.out.print(argv[1]);
								 System.out.print("\t" +  road_to_compare);
								 System.out.printf("\t %d" ,year_to_compare);
								 System.out.printf("\t %d\n" , id_value);
								 sum += id_value;
								 count++;
							 }
						 }
					}	
				 }
				 catch(JSONException e)
				 	{
					 conti = false;
				 	}
			}//end while
			 
			 int avg_price = 0;
			 
			 if (count > 0)
			 {
				avg_price = sum / count;
			 }
			 else
			 {
				System.out.println("No Search Result!");
				System.out.println("Please check that all you arguments are correct!");
			 }
			 System.out.println("Output:");
			 System.out.printf("%d \n" , avg_price);
			 
			}//end if(argv.length == 4)
			 else
			 {
				 System.out.println("Unexpected Number of Arguments!");
			 }
			}//end try
			 
			catch(IOException e)
			{
				System.out.println("File Not Found!");
			}
			
		 }
		 
}
