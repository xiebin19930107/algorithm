package NowCoder.HuaWei;
import java.util.*;
/**
 * ��������
 * @author SpicyJelly
 * @param 3+2*{1+2*[-4/(8-6)+7]}
 */
public class HJ50 {
	static public void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //String �����û��append������String��һ��ֵ�����ѱ仯
        StringBuilder str = new StringBuilder();
        while(scanner.hasNext()){
            str.append(scanner.next());
        }
        HJ50 so = new HJ50();
        int[] Current = {0};
        int re = so.calculate(str,Current);
        System.out.println(re);
    }

	    public int calculate(final StringBuilder str,int[] Current){
	        if(Current[0] == str.length()) return 0;
	        //����һ����ջ
	        Stack<Integer> stack = new Stack<Integer>();
	        //����ǰ�ַ�����Ч��ʱ��iterator
	        while(Current[0] < str.length()){
	            //��¼��ǰ�ַ�����ֵ
	            char key = str.charAt(Current[0]);
	            //����Ǽӷ����򲻿���
	            if(key == '+'){
	                Current[0]++;
	            }//if
	            /**����Ǽ������ȼ�¼���ţ�Ȼ�󿴺�����ַ�����
	            *��������֣��������ֵ�������Ϸ���λ
	            *��������ţ���ݹ���ú�������������ڵ�ֵ�������Ϸ���λ
	            *���߶���Ҫ��ջ
	            */
	            else if(key == '-'){
	                //������ܸ����֣�Ҳ���ܸ�����
	                int symbol = -1;
	                Current[0]++;
	                int num = 0;
	                key = str.charAt(Current[0]);
	                if(key >= '0' && key <= '9'){
	                    num = getNumber(str,Current);
	                }//if
	                else if(key == '(' || key == '[' || key == '{'){
	                    Current[0]++;
	                    num = calculate(str,Current);
	                }//else if
	                num *= -1;
	                stack.push(num);
	            }//else if
	            /**����ǳ˳������ջtop�����ſ���һ����ֵ
	            �����ֿ��ܣ�һ�������֣�һ��������
	            */
	            else if(key == '*' || key == '/'){
	                char symbol = key;
	                int first = stack.pop();
	                Current[0]++;
	                key = str.charAt(Current[0]);
	                int second = 0;
	                if(key >= '0' && key <= '9'){
	                    second = getNumber(str,Current);
	                }
	                else if(key == '(' || key == '[' || key == '{'){
	                    Current[0]++;
	                    second = calculate(str,Current);
	                }
	                try{
	                    if(symbol == '*')
	                        stack.push(first * second);
	                    else if(symbol == '/')
	                        stack.push(first / second);
	                }catch(Exception e){}
	            }

	            /**��������ֵĻ����������ֵ�ֵ������ջ
	            */
	            else if(key >= '0' && key <= '9'){
	                int num = getNumber(str,Current);
	                stack.push(num);
	            }//else if
	            /**
	            ����������ţ���ݹ����������ı���ʽ
	            */
	            else if(key == '(' || key == '[' || key == '{'){
	                Current[0]++;
	                int re = calculate(str,Current);
	                stack.push(re);
	            }//else if
	            else if(key == ')' || key == ']' || key == '}'){
	                Current[0]++;
	                return Sum(stack);
	            }
	        }//while
	        return Sum(stack);
	    }//calculate
	    int getNumber(final StringBuilder str,int[] Current){
	        int re = 0;
	        while(str.charAt(Current[0]) >= '0' && str.charAt(Current[0]) <= '9'){
	            re = re * 10 + str.charAt(Current[0]) - '0';
	            Current[0]++;
	            if(Current[0] == str.length()) break;
	        }//while
	        return re;
	    }
	    int Sum(Stack<Integer> stack){
	        int re = 0;
	        while(!stack.empty()) re += stack.pop();
	        return re;
	    }
	
}