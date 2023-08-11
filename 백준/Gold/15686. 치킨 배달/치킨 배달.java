import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	static class House {
		int y;
		int x;

		public House(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static class Joint {
		int y;
		int x;

		public Joint(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		List<House> houses = new ArrayList<>();
		List<Joint> joints = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < n; j++)
				if (line[2 * j] == '1')
					houses.add(new House(j, i));
				else if (line[2 * j] == '2')
					joints.add(new Joint(j, i));
		}
		
		int jointCnt = joints.size();
		int houseCnt = houses.size();
		int[][] distances = new int[jointCnt][houseCnt];
		for (int i = 0; i < joints.size(); i++)
			for (int j = 0; j < houses.size(); j++)
				distances[i][j] = Math.abs(joints.get(i).x - houses.get(j).x) + Math.abs(joints.get(i).y - houses.get(j).y);
		
//		int[] nums = new int[n];
//		for (int i = 0; i < n; i++)
//			nums[i] = Integer.parseInt(st.nextToken());
		int[] comb = new int[jointCnt];
		for (int i = 1; i <= m; i++)
			comb[jointCnt - i] = 1;

		int minDIstance = Integer.MAX_VALUE;
		do {
			int sumDistance = 0;
			for (int i = 0; i < houseCnt; i++) {
				int nowDistance = Integer.MAX_VALUE;
				for(int j = 0; j < jointCnt; j++) {
					if (comb[j] == 0)
						continue;
					nowDistance = Math.min(nowDistance, distances[j][i]);
				}
				sumDistance += nowDistance;
			}
			minDIstance = Math.min(minDIstance, sumDistance);
		} while (np(comb));
		
		System.out.println(minDIstance);
	}

	private static boolean np(int[] comb) {
		int i, j, k;
		i = j = k = comb.length - 1;
		
		while(i > 0 && comb[i - 1] >= comb[i]) i--;
		
		if(i == 0) return false;
		
		while(comb[i - 1] >= comb[j]) j--;
		
		swap(comb, i - 1, j);
		
		while(i < k) swap(comb, i++, k--);
		
		return true;
	}
	
	private static void swap(int[] comb, int i, int j) {
		int tmp = comb[i];
		comb[i] = comb[j];
		comb[j] = tmp;
	}
}
