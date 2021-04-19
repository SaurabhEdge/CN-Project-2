//package networks;



/*
 *
 * @author      Saurabh Shukla
 *
 * Version:  1.0
 *     
 *     
 * DistanceVector.java
 
 This class performs the Distance vector calculation of rover and it's neighbors
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class DistanceVector {

	ArrayList ipData = new ArrayList();
	ArrayList nextHOP = new ArrayList();
	ArrayList Matrics = new ArrayList();	
	static Map<String, Integer> hm=new HashMap<String, Integer>();
	
	
	/*
	 * 
	 * Below method calculates the 10 sec timer behavior.
	 * Value and key is stored in hashmap and checked with router table.
	 * if value exist then don't remove it 
	 * else delete that row from router table.
	 * 
	 */
		
	
	public ArrayList timerOffline(ArrayList ipData) {
		
		
		
		
		for(int i=0;i<ipData.size();i++) {        	
        	if(hm.equals(ipData.get(i))) {	}
        	else{        		
        		hm.put((String) ipData.get(i), 0);
        	}
		 }
		  
		for (String url : hm.keySet())  {
			boolean flag=false;
            for(int i=0;i<ipData.size();i++) {            	
            	String temp=(String) ipData.get(i);	
            	if(url.equals(temp)) {
            		flag=true;
            		break;
            	}
            	else {
            		flag=false;
            		Integer a = hm.get((String)ipData.get(i));
	}
          }
            
            if(!flag) {
            	Integer a = hm.get(url);
            hm.put(url, a+1);}
            else {
            	hm.put(url, 0);
            }
		}	
		 
	
	ArrayList all=new ArrayList();
	 for (String url : hm.keySet()) {		 
		 Integer a = hm.get(url);
		 if(a==2) {			
			all.add(url);
		 } 		 
	 }
	 
	 for(int i=0;i<all.size();i++) {
		 hm.remove(all.get(i));
		 
	 }
		ArrayList finalIPList=new ArrayList();
		for(String temp:hm.keySet()) {
			finalIPList.add(temp);
			
		
		}
	//	System.out.println(hm);
		return finalIPList;
		
		
	}
	
	

	/*
	 * 
	 * Below method calculates distance vector algorithm to calculate
	 * cost and hops of it's neighbor.
	 * 
	 * 
	 */
		
	
	public void calculateDistance(byte[] clientDatapwd, int maskingValue) {

	

		int receiverSize = 0;
		for (int i = 0; i < (clientDatapwd.length/20); i++) {
			int value = 5 + 20 * i;
			receiverSize++;
			if (clientDatapwd[value] == 0) {			
				receiverSize = (5 + 20 * i) - 1;
				break;
			}

		}
		
		int pp[] = new int[clientDatapwd.length];	
		
		for(int i=0;i<clientDatapwd.length;i++) {
			pp[i]=clientDatapwd[i]&0xFF;
			
		}
		int nexTCount[] = new int[(receiverSize)];
		int cost[] = new int[(receiverSize)];
		String ppp[] = new String[(receiverSize)];

		for (int i = 0; i < ppp.length; i++) {

			ppp[i] = "" + pp[8 + 20 * i] + "." + pp[9 + 20 * i] + "." + pp[10 + 20 * i] + "." + pp[11 + 20 * i];
			nexTCount[i] = pp[7 + 20 * i];
			cost[i] = pp[23 + 20 * i];

		}
		
		int sourceRover = 0;
		for (int i = 0; i < cost.length; i++) {
			if (cost[i] == 0) {
				sourceRover = nexTCount[i];
				break;
			}
		}
	
		ipData.add(0, (ppp[0] + "/" + maskingValue));
		nextHOP.add(0, nexTCount[0]);
		Matrics.add(cost[0]);

		for (int i = 0; i < ppp.length; i++) {
			boolean isPresent = false;
			for (int j = 0; j < ipData.size(); j++) {
				String temp = ppp[i] + "/" + maskingValue;
				if (temp.equals(ipData.get(j))) {
					isPresent = true;

				}

			}

			
			if (!isPresent) {
				ipData.add(ppp[i] + "/" + maskingValue);
				nextHOP.add(sourceRover);
				Matrics.add((cost[i] + 1));
			}
					
		}	
		

	}

	public ArrayList showIP() {
		return ipData;
	}

	public ArrayList showHOP() {
		return nextHOP;
	}

	public ArrayList showCost() {
		return Matrics;
	}

}
