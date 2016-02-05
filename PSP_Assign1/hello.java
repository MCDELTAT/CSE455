public class hello {

    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        System.out.println("Hello, World");
        int x = 5;
        int repeat_counter = 10;
        int incBy = 2;
        int inc_result = increment(x, repeat_counter, incBy);
        
        //output the setup and run of my increment function     
        System.out.println("The value of x is: " + x);
        System.out.println("The function will increment it " + repeat_counter + " times by " + incBy + " each time.");
        System.out.println("The result is: " + inc_result);
    }

    static int increment(int val1, int loop_limit, int incBy){
    	int result = val1;
    	for (int i=0; i<loop_limit; i++){
    		result = result + incBy;
    	}
    	return result;
    }
}