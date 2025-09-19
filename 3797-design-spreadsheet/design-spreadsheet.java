class Spreadsheet {
    private int[][] grid;
    private int rows;

    public Spreadsheet(int rows) {
        this.rows = rows;
        this.grid = new int[26][rows + 1];
    }
    
    public void setCell(String cell, int value) {
        int[] coords = parseCell(cell);
        int col = coords[0];
        int row = coords[1];
        grid[col][row] = value;
    }
    
    public void resetCell(String cell) {
        setCell(cell, 0);
    }
    
    public int getValue(String formula) {
        String exp = formula.substring(1);
        String[] parts = exp.split("\\+");
        String left = parts[0];
        String right = parts[1];

        int leftval = parseOperand(left);
        int rightval = parseOperand(right);

        return leftval + rightval;
    }

    private int[] parseCell(String cell) {
        char colChar = cell.charAt(0);
        int col = colChar - 'A';
        int row = Integer.parseInt(cell.substring(1));
        return new int[]{col, row};
    }

    private int parseOperand(String operand) {
        if(Character.isDigit(operand.charAt(0))) {
            return Integer.parseInt(operand);
        } else {
            int[] coords = parseCell(operand);
            int col = coords[0];
            int row = coords[1];
            return grid[col][row];
        }
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */