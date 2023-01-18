package project;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Proj1 {

	public static void main(String[] args) throws FileNotFoundException {

Scanner file = new Scanner(new File("C:\\Users\\SHABARISH\\Downloads\\proj1_testsignal1"));
ArrayList<String> sg = new ArrayList<String>();
while (file.hasNext()){
   sg.add(file.next());
}
file.close();
int length=sg.size();
Scanner sp= new Scanner(System.in);
System.out.println("To learn the statistics, enter in the amount of noise samples");
int nsp=sp.nextInt();
double[] sgdouble = new double[sg.size()+1];
double sum = 0;
for (int i = 0; i <length; ++i) {
sgdouble[i] = Double.parseDouble(sg.get(i));
   }
for (int i = 0; i <nsp; ++i) {
sum += sgdouble[i];
}
double mean=sum/nsp;
double standardDeviation = 0.0;
for (int i = 0; i <nsp ; i++) {
standardDeviation += Math.pow(sgdouble[i] - mean, 2);

}
int strt_sig=0;
double SD= Math.sqrt(standardDeviation/nsp);
System.out.println("Mean "+mean);

System.out.println("Standard Deviation "+SD);
double Threshold=(mean*8)+(SD*16);
System.out.println("Threshold "+Threshold);
for(int i=0;i<length;i++)
{
  if(sgdouble[i]>Threshold)
{
 strt_sig=i;
break;
}
 }
 System.out.println("strt_sig " +strt_sig);
 double[] subset_array = Arrays.copyOfRange(sgdouble, strt_sig, sgdouble.length);
 int count_hundred = 0;
 double current_value_high_threshold =0;
 int current_index_high_threshold = 0;
 ArrayList<Integer> indexValuesArray = new ArrayList<Integer>();
 for(int i=0; i <subset_array.length; i++) {
	count_hundred++;
  if(subset_array[i] > Threshold && subset_array[i] > current_value_high_threshold) {
		
       }
	if(count_hundred >= 100) {
	    indexValuesArray.add(current_index_high_threshold);
		if(current_index_high_threshold==0)
		{
			break;
		}
		count_hundred = 0;
		current_value_high_threshold=0;
		current_index_high_threshold=0;
	}
 }	
 ArrayList<Integer> binaryValues = new ArrayList<Integer>();
 for(int i=0; i <indexValuesArray.size(); i++) {
 if(indexValuesArray.get(i)<= 20) {
		binaryValues.add(0);
	}
   else {
		binaryValues.add(1);
		}
}
int Skip_counter=0;
ArrayList<Integer> binaryValues2 = new ArrayList<Integer>();
for(int i=8; i<binaryValues.size(); i++) {
	try{
		if(Skip_counter<4)
{
	binaryValues2.add(binaryValues.get(i));
	Skip_counter++;
}
       else if(Skip_counter==4){
	   i=i+2;
	   Skip_counter=0;
	
}
}
	catch(IndexOutOfBoundsException e)
	{
	}

}
char[] chars1 = new char[binaryValues2.size() / 8];
  for (int h = 0; h < chars1.length; ++h) {
	    int counter2 = 0;
	    for (int j = h * 8; j < (h + 1) * 8; ++j) {
	    	counter2 = counter2 << 1;
	    	counter2+= binaryValues2.get(j);
	    }
	    chars1[h] = (char)counter2;
	}
    String s = new String(chars1);
	System.out.println(s);
}
}

