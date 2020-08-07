//  https://leetcode.com/problems/word-search/

class Solution {
    public boolean exist(char[][] board, String word) {
        // 10 ms, faster than 39.95%
        // 42 MB, less than 5.09%
        
        /*
        Approach:
        Visit each cell in the matrix, and start a search for the rest of the word if the letter in 
        that cell matches the first letter of the word. The search has to look in all 4 directions
        for each letter. We need a matrix that marks every visited spot as visited while a search 
        is going on, and it needs to unmark it as visited if that path did not lead to the full word.
        If a dfs search found the entire word, we return true. If the entire word is never found,
        we return false.
        */
        
        // edge cases: 
        //  the board contains nothing
        //  the word contains more letters than the matrix
        if(board.length == 0 || board[0].length == 0)
            return false;
        else if(word.length() > board.length * board[0].length)
            return false;
        
        
        // convert the word to a char array for easier use
        char[] wordArray = word.toCharArray();
        
        
        // this matrix will help us know when we've visited a character already,
        // so that we don't reuse it
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        
        // we will visit every letter in the matrix and start a dfs search if
        // that letter is the first letter of our word
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                
                // if potentially found the starting loaction of the word, do a dfs
                if(board[r][c] == wordArray[0])
                    if(dfs(board, visited, wordArray, r, c, 0))
                        return true; // return true only if we found the word
                    else
                        visited = new boolean[board.length][board[0].length]; // if we didn't find it, we want to reset visited
            }
        }
        
        
        // if we never found the word, then it's not in the matrix
        return false;
    }
    
    /**
    - r and c are the current location we are in in the matrix
    - i is how far into the word we are. i starts out at 0.
        - if i reaches the length of the word, then we found the word and we return true.
    */
    public boolean dfs(char[][] board, boolean[][] visited, char[] wordArray, int r, int c, int i) {
        // if we reached the end of the word, then that means we found every letter of the word
        // in our dfs search, so we return true
        if(i == wordArray.length)
            return true;
        
        
        // if we've gone out of bounds, return false
        if(r < 0 || c < 0 || r >= board.length || c >= board[0].length)
            return false;
        
        
        // if we've alerady visited this spot, return false
        if(visited[r][c])
            return false;
        
        
        // if the current letter isn't even a match then we return false
        if(board[r][c] != wordArray[i])
            return false;
        
        
        // else, we're gonna take a look at this spot to see if it will
        // help us find the word
        visited[r][c] = true;
        
        
        // continue the dfs in all 4 directions
        if(dfs(board, visited, wordArray, r + 1, c, i + 1))
            return true;
        else if(dfs(board, visited, wordArray, r - 1, c, i + 1))
            return true;
        else if(dfs(board, visited, wordArray, r, c+ 1, i + 1))
            return true;
        else if(dfs(board, visited, wordArray, r, c - 1, i + 1))
            return true;
        
        
        // if this letter didn't help us find the word, we set this location to not visited
        // because it may need to be used again for another path, so we can't leave it 
        // marked as visited, and we return false
        visited[r][c] = false;
        return false;
    }
}

/*

Test cases:
(Image: imgur.com/a/oR6KgsP )

true:
[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
"ABCCED"
[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
"SEE"
[["A","B"],["B","E"],["C","D"]]
"ABCDEB"
[["U","O","E","R","B","R","Q"],["E","O","I","O","E","B","R"],["I","R","B","E","Z","E","B"],["F","I","R","X","E","B","R"],["H","G","O","O","B","B","R"],["I","J","L","A","R","Q","F"]]
"ZEBRA"
[["A","A","A","A","X"],["A","A","A","A","X"],["A","A","A","A","A"]]
"AAAAAAAAAAAAA"
[["D","G","F","E","H"],["C","B","C","D","X"],["H","A","B","C","X"],["G","F","E","D","X"]]
"ABCDEFGH"
[["a","b"],["c","d"]]
"cdba"


false:
[["X","X","X","X","X"],["A","B","C","D","E"],["X","X","H","G","F"]]
"ABCDEFGHC"
[["A","A","A","A","X","X"],["X","X","A","A","X","X"],["X","X","A","A","X","X"],["X","X","A","A","A","A"]]
"AAAAAAAAAAAA"
[["X","X","X","X"],["X","A","X","X"],["X","X","A","X"],["X","X","X","A"]]
"AAA"
[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
"ABCB"

*/
