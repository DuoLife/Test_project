/**
* <p>Title: LargestSubsegmentSum1.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2014</p>
* <p>Company: ColdWorks</p>
* @author xuming
* @date 2015-3-27
* @version 1.0
*/
package maxsum;

import java.util.Scanner;

/**
 * <p>Title: LargestSubsegmentSum1.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: ColdWorks</p>
 * @author xuming
 * @date 2015-3-27
 * Email: vip6ming@126.com
 */
public class LargestSubsegmentSum1 {
	public static void main(String[] args)  
	 {  
	  /** 
	   *从键盘输入所要求的序列的长度n 
	   */  
//	  Scanner in=new Scanner(System.in);  
//	    
//	  System.out.println("Please enter the length of segment you want to make(输入你要求的序列的长度):");  
//	  int n=in.nextInt();  
//	  /** 
//	   *从键盘输入所要求的序列，存储在a[n]中 
//	   */  
//	  int[] a=new int[n];  
//	  System.out.println("Now,please enter the elements of the segment you want(现在请依次输入这个序列包含的元素(整数)):");  
//	  int i;  
//	  for(i=0;i<n;i++)  
//	  {  
//	   a[i]=in.nextInt();  
//	  }  
	  /** 
	   *求解最大子段和存在maxSum中 
	   */  
	  double startTime=System.currentTimeMillis();//starttime  
	  int maxSum=0;  
//	  int[] b=new int[n];  
	  int[] a = {1,15,-111,23,111,1239,-900,1,53,-223,123,553,-24};
	  int n = a.length;
	  int[] b = new int[n];  
	  b[0]=0;  
	  for(int j=1;j<n;j++)  
	  {  
	   if(b[j-1]>0)  
	    b[j]=b[j-1]+a[j];
	   else  
	   {  
	    b[j]=a[j];  
	   }  
	  }  
	  for(int j=0;j<n;j++)  
	  {  
	   if(b[j]>maxSum)  
	    maxSum=b[j];  
	  }  
	  double endTime=System.currentTimeMillis();//endtime  
	  /** 
	   *打印输出结果和程序运行所用时间 
	   */  
	  System.out.println("The largest sub-segment sum is(最大子段和是):"+maxSum);  
	  System.out.println("Basic Statements take(基本语句用时) "+(endTime-startTime)+" milliseconds!");  
	 }  
}
