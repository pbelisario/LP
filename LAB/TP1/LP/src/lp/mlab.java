package lp;

public class mlab {

  public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java msi [Mini Shell Script File]");
            return;
        }

        try(LexicalAnalysis l = new LexicalAnalysis(args[0]);){
            SyntaticalAnalysis a = new SyntaticalAnalysis(l);
            a.start();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

