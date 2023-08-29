import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int cnt = scan.nextInt();
		int[][] a = new int[cnt+1][3];
		int[][] d = new int[cnt+1][3];
		
		for(int i = 1; i<=cnt; i++)
		{
			for(int j = 0; j<3; j++)
			{
				a[i][j] = scan.nextInt();
			}
		}
		
		for(int i = 1; i<=cnt; i++)
		{
			d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + a[i][0];
			d[i][1] = Math.min(d[i-1][0], d[i-1][2]) + a[i][1];
			d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + a[i][2];
		}
			
		System.out.println(Math.min(Math.min(d[cnt][0], d[cnt][1]), d[cnt][2]));
	}

}
