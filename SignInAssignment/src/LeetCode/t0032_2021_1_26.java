package LeetCode;

import java.util.Stack;

public class t0032_2021_1_26 {
	class Solution {
	    public int longestValidParentheses(String s) {
	        boolean[] record = new boolean[s.length()];
	        for(int i = 0; i < record.length; i++) record[i] = false;
	        Stack<Integer> stackI = new Stack<Integer>();
	        for(int i = 0; i < s.length(); i++){
	            //如果为右括号的时候，弹栈，没栈即找下一个值
	            if(s.charAt(i) == ')'){
	                if(stackI.empty())
	                    continue;
	                else{
	                    record[i] = true;
	                    record[stackI.pop()] = true;
	                }
	            }//if')'
	            //如果为左括号的时候，压栈
	            else stackI.push(i);
	        }
	        int re = 0;
	        int current = 0;
	        for(int i = 0; i < record.length; i++){
	            if(record[i] == true){
	                current++;
	                if(current > re) re = current;
	            }
	            else current = 0;
	        }
	        return re;
	    }
	}
}
