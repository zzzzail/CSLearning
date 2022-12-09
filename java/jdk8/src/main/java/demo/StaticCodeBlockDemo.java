package demo;

public class StaticCodeBlockDemo {
    
    public static void main(String[] args) {
        
        CodeBlock codeBlock = new CodeBlock();
    }
    
    static class CodeBlock {
        static {
            System.out.println("hey");
        }
    }
}
