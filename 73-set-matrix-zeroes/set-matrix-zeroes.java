class Solution {
    public void setZeroes(int[][] matrix) {
        boolean r1 = false, c1 = false;

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j] == 0){
                    if(i==0) r1 = true;
                    if(j==0) c1 = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        if(r1){
            for(int j=0; j<matrix[0].length; j++){
                matrix[0][j] = 0;
            }
        }

        if(c1){
            for(int i=0; i<matrix.length; i++){
                matrix[i][0] = 0;
            }
        }
    }
}