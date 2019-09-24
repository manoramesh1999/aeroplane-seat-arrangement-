
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class seat_set
{	
	int row,col;
	int[][][] seats;
	char type;
	
	public seat_set(int row,int col) 
	{
		
		this.type='s';
		this.row=row;
		this.col=col;		
		seats = new int[row][col][2];	
	}	
}
public class aero 
{
	static ArrayList<seat_set> list= new ArrayList<>();
	static int pass_count,row_max,col_max,count=1;
	static void aisle_seat(int[][] arr) 
	{	
        	for(int i=0;i<row_max&&count<=pass_count;i++) 
		{	
			for(int j=0;j<arr.length&&count<=pass_count;j++) 
			{
				if(j==0&&i<arr[j][0]) 
				{
					list.get(j).seats[i][arr[j][1]-1][0]=count++;
				}
				else if(j==arr.length-1&&i<arr[j][0]&&count<=pass_count)
				{
					list.get(j).seats[i][0][0]=count++;	
				}
				else if(i<arr[j][0]&&count<=pass_count) 	
				{			
					list.get(j).seats[i][0][0]=count++;
					if(count<=pass_count)
					list.get(j).seats[i][arr[j][1]-1][0]=count++;
				}
			}
		}	
	}
	static void window_seat(int[][] arr) 
	{	
        	for(int i=0;i<row_max&&count<=pass_count;i++) 
		{	
			if(i<arr[0][0])
				list.get(0).seats[i][0][0]=count++;
			if(i<arr[arr.length-1][0]&&count<=pass_count) 
			{
				int j = arr[arr.length-1][1]-1;
				list.get(arr.length-1).seats[i][j][0]=count++; 
			}
		}		
	}
	static void middle_seat(int[][] arr)
	{	
        	for(int i=0;i<row_max&&count<=pass_count;i++)
		{	
			for(int j=0;j<arr.length&&count<=pass_count;j++)
			{
				
				for(int k=1;k<arr[j][1]-1&&count<=pass_count&&i<arr[j][0];k++)
				{	
					list.get(j).seats[i][k][0]=count++;	
				}	
			}
		}

	}
	static void print_output()
	{
		System.out.println("        ");
    		System.out.println("SEAT ALLOTMENTS");
		System.out.println("       ");
    		for(seat_set arr : list)
		{
    			for(int i=0;i<arr.seats.length;i++)
    			{
				
				for(int j=0;j<arr.seats[0].length;j++)
    				{
    					System.out.print(arr.seats[i][j][0]+"    ");
    				
    				}
    				System.out.println(" ");
    			}	
    			System.out.println(" ");	
    		}
    	}
public static void print(int[][] arr)
{
     for(int i =0;i<arr.length;i++)
     {
     	if(i==0)
                            for(int j =0;j<arr[i][1];j++){
                            if(j==0){
                            System.out.print("W  ");
                            }
                            else if(j==arr[i][1]-1){
                            System.out.print("A  ");
                            }
                            else{

                            System.out.print("M  ");
                            }
                            }
              else if(i==arr.length-1)
                            for(int j =0;j<arr[i][1];j++){
                            if(j==0){
                            System.out.print("A  ");
                            }
                            else if(j==arr[i][1]-1){
                            System.out.print("W  ");
                            }
                            else{

                            System.out.print("A  ");
                            }


                            }

              
              else
                            for(int j =0;j<arr[i][1];j++){
                            if(j==0){
                            System.out.print("A  ");
                            }
                            else if(j==arr[i][1]-1){
                            System.out.print("A  ");
                            }
                            else{
                            System.out.print("M  ");
                            }
                            }

          System.out.println("");
              
          }
     }
	    
	public static void main(String args[]) {
 
                 int n,i,j;
                 Scanner in=new Scanner(System.in);
                 System.out.println("Enter the Number of containers to be: ");
                 n=in.nextInt();
		 System.out.println("Total Number of passengers:");
		 pass_count =in.nextInt();
                 int arr[][]=new int[n][2];
                 System.out.println("Enter the rows and columns for the containers");
		 System.out.println("  ");
                 for(i=0;i<n;i++)
		 {
			System.out.println("For container :"+(i+1));
			for(j=0;j<2;j++)
			{
                     		arr[i][j]=in.nextInt();
                       }
                 }
		System.out.println("***********SEAT ARRANGEMENTS TYPES**********");
		System.out.println(" ");
		System.out.println("W -> Window seat");
		System.out.println("M -> Middle seat");
		System.out.println("A -> Aisle seat");
		System.out.println(" ");
		               Comparator<int[]> c=new Comparator<int[]>() {	
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0]==o2[0])
				return o2[1]-o1[1];
				return o1[0]-o2[0];
			}
		};
		 Arrays.sort(arr,c);
		for(int[] seat : arr) {
			if(seat[0]>row_max)
				row_max=seat[0];
			col_max=seat[1]+col_max;
			list.add(new seat_set(seat[0],seat[1]));
			
		}
		aisle_seat(arr);
		window_seat(arr);
		middle_seat(arr);
		print_output();	       
	}
	
}

