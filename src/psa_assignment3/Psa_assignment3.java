/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psa_assignment3;

/**
 *
 * @author root
 */
public class Psa_assignment3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Random rand = new Random();
        ArrayList<Long> times_ijk = new ArrayList<Long>();
        ArrayList<Long> times_str = new ArrayList<Long>();
        ArrayList<Integer> sizes = new ArrayList<Integer>();
        for(int iter = 0; iter<998; iter++){
           
           ArrayList<ArrayList<Integer>> mat = new ArrayList<ArrayList<Integer>>();
        
            for(int i =0;i<iter+2;i++){
                ArrayList<Integer> inner = new ArrayList<Integer>();
                for(int j = 0; j<iter+2; j++){
                    int rand_int1 = rand.nextInt(10);
                     inner.add(rand_int1);
                }
                mat.add(inner);
            }
            ArrayList<ArrayList<Integer>> mat1 = new ArrayList<ArrayList<Integer>>();

            for(int i =0;i<iter+2;i++){
                ArrayList<Integer> inner = new ArrayList<Integer>();
                for(int j = 0; j<iter+2; j++){
                    int rand_int1 = rand.nextInt(10); 
                     inner.add(rand_int1);
                }
                mat1.add(inner);
            } 
            
            Date date = new Date();
            long timeMilli = date.getTime();
            int ansStr[][] = strassen(mat, mat1);
            date = new Date();
            long timeMilliEnd = date.getTime();
            times_str.add(timeMilliEnd - timeMilli);
            long ss = timeMilliEnd-timeMilli;
            
            sizes.add(mat.size());
            date = new Date();
            timeMilli = date.getTime();
            int ansIJK[][] = ikjAlgorithm(mat, mat1);
            date = new Date();
            timeMilliEnd = date.getTime();
            times_ijk.add(timeMilliEnd - timeMilli);
            System.out.println("ikj "+String.valueOf(timeMilliEnd-timeMilli)+"   strassion :" + String.valueOf(ss) + " size : " + mat.size() );
        }
        
        File file = new File("test.csv");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("ijk,strassen");
        bw.newLine();
        for(int i=0;i<times_ijk.size();i++)
        {
            bw.write(times_ijk.get(i)+","+times_str.get(i));
            bw.newLine();
        }
        
         ArrayList<ArrayList<Integer>> m = new ArrayList<ArrayList<Integer>>();
        for(int i =0;i<1000;i++){
                ArrayList<Integer> inner = new ArrayList<Integer>();
                for(int j = 0; j<1000; j++){
                    int rand_int1 = rand.nextInt(10); 
                     inner.add(rand_int1);
                }
                m.add(inner);
            } 
         ArrayList<ArrayList<Integer>> m2 = new ArrayList<ArrayList<Integer>>();
        for(int i =0;i<1000;i++){
                ArrayList<Integer> inner = new ArrayList<Integer>();
                for(int j = 0; j<1000; j++){
                    int rand_int1 = rand.nextInt(10); 
                     inner.add(rand_int1);
                }
                m2.add(inner);
            }
        ArrayList<Integer> ls = new ArrayList<Integer>();
        ArrayList<Long> to = new ArrayList<Long>();
        for(int i =0; i< 1000; i = i+50){
            Date date = new Date();
            long t = date.getTime();
            int ansStr[][] = strassen(m, m2);
            date = new Date();
            long t2 = date.getTime();
            to.add(t2 - t);
            ls.add(leaf_size);
            leaf_size += 50;
            
        }
        
        File file1 = new File("test_for_leaf_size.csv");
        FileWriter fw1 = new FileWriter(file1);
        BufferedWriter bw1 = new BufferedWriter(fw1);

        bw1.write("LEAF_SIZE,RunTime");
        bw1.newLine();
        for(int i=0;i<ls.size();i++)
        {
            bw1.write(ls.get(i)+","+to.get(i));
            bw1.newLine();
        }
        
       
        
        
        
        
        
        
        
        
        bw1.close();
        fw1.close();
        bw.close();
        fw.close();
    }
     public static int[][] multiply(int[][] A, int[][] B)
    {        
        int n = A.length;
        int[][] R = new int[n][n];
        /** base case **/
        if (n == 1)
            R[0][0] = A[0][0] * B[0][0];
        else
        {
            int[][] A11 = new int[n/2][n/2];
            int[][] A12 = new int[n/2][n/2];
            int[][] A21 = new int[n/2][n/2];
            int[][] A22 = new int[n/2][n/2];
            int[][] B11 = new int[n/2][n/2];
            int[][] B12 = new int[n/2][n/2];
            int[][] B21 = new int[n/2][n/2];
            int[][] B22 = new int[n/2][n/2];
 
            split(A, A11, 0 , 0);
            split(A, A12, 0 , n/2);
            split(A, A21, n/2, 0);
            split(A, A22, n/2, n/2);
            /** Dividing matrix B into 4 halves **/
            split(B, B11, 0 , 0);
            split(B, B12, 0 , n/2);
            split(B, B21, n/2, 0);
            split(B, B22, n/2, n/2);
 
 
            int [][] M1 = multiply(add(A11, A22), add(B11, B22));
            int [][] M2 = multiply(add(A21, A22), B11);
            int [][] M3 = multiply(A11, sub(B12, B22));
            int [][] M4 = multiply(A22, sub(B21, B11));
            int [][] M5 = multiply(add(A11, A12), B22);
            int [][] M6 = multiply(sub(A21, A11), add(B11, B12));
            int [][] M7 = multiply(sub(A12, A22), add(B21, B22));
 

            int [][] C11 = add(sub(add(M1, M4), M5), M7);
            int [][] C12 = add(M3, M5);
            int [][] C21 = add(M2, M4);
            int [][] C22 = add(sub(add(M1, M3), M2), M6);
 
            join(C11, R, 0 , 0);
            join(C12, R, 0 , n/2);
            join(C21, R, n/2, 0);
            join(C22, R, n/2, n/2);
        }  
        return R;
    }

    public static int[][] sub(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    public static int[][] add(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    public static void split(int[][] P, int[][] C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    public static void join(int[][] C, int[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }
    
    public static int[][] ikjAlgorithm(int[][] A, int[][] B) {
        int n = A.length;

        // initialise C
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
        
     public static int[][] ikjAlgorithm(ArrayList<ArrayList<Integer>> A,
            ArrayList<ArrayList<Integer>> B) {
        int n = A.size();

        // initialise C
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += A.get(i).get(k) * B.get(k).get(j);
                }
            }
        }
        return C;
    }

    private static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    private static int nextPowerOfTwo(int n) {
        int log2 = (int) Math.ceil(Math.log(n) / Math.log(2));
        return (int) Math.pow(2, log2);
    }

    public static int[][] strassen(ArrayList<ArrayList<Integer>> A,
            ArrayList<ArrayList<Integer>> B) {

        int n = A.size();
        int m = nextPowerOfTwo(n);
        int[][] APrep = new int[m][m];
        int[][] BPrep = new int[m][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                APrep[i][j] = A.get(i).get(j);
                BPrep[i][j] = B.get(i).get(j);
            }
        }

        int[][] CPrep = strassenR(APrep, BPrep);
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = CPrep[i][j];
            }
        }
        return C;
    }

    
}
