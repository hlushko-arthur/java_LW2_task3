import java.util.Random;
import java.util.Scanner;

public class LW2_task3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		System.out.print("Enter the matrix dimension n: ");
		int n = scanner.nextInt();

		int[][] matrix = new int[n][n];

		System.out.println("Matrix with random values in the range from -n to n:");

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = random.nextInt(2 * n + 1) - n;
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}

		long determinant = calculateDeterminant(matrix);
		System.out.println("\nMatrix determinant: " + determinant);

		scanner.close();
	}

	private static long calculateDeterminant(int[][] matrix) {
		int n = matrix.length;

		if (n == 2) {
			return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
		}

		long det = 0;
		for (int i = 0; i < n; i++) {
			det += (i % 2 == 0 ? 1 : -1) * matrix[0][i] * calculateDeterminant(getSubmatrix(matrix, i));
		}

		return det;
	}

	private static int[][] getSubmatrix(int[][] matrix, int colToRemove) {
		int n = matrix.length;
		int[][] submatrix = new int[n - 1][n - 1];

		for (int i = 1; i < n; i++) {
			int subCol = 0;
			for (int j = 0; j < n; j++) {
				if (j != colToRemove) {
					submatrix[i - 1][subCol] = matrix[i][j];
					subCol++;
				}
			}
		}

		return submatrix;
	}
}
