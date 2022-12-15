
package app;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Write  extends ReadAndWrite implements Runnable {

     
 
     
        @Override
        public void run() {
            
                
                try {
                mutex2.acquire();
                 
               writeCount++;
               if(writeCount==1){
//               readLock.acquire();
               }
               mutex2.release();
              writeLock.acquire();
              //////////////////////////////////////
              
              System.out.println("Thread "+Thread.currentThread().getName() + " Write is Acquired");
              try {

            Scanner input =new Scanner(System.in);
               
//                Thread.sleep(1000);
            
            
            FileWriter fw = new FileWriter("C:\\Users\\DELL\\Downloads\\java\\APP\\src\\test.txt",true);
            try (PrintWriter pw = new PrintWriter(fw)) {
            pw.print(Thread.currentThread().getName()+"\n");


            pw.close();}
            } catch (IOException ex) {}
              
              
              
              System.out.println("Thread "+Thread.currentThread().getName() + " Write is Released");
               //////////////////////////////////////
              
              writeLock.release();
              mutex2.acquire();
              writeCount--;
              if(writeCount==0){
//                 readLock.release();
               }
               mutex2.release();
               } catch (InterruptedException ex) {
                     java.util.logging.Logger.getLogger(Write.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                 }
               
          
      
        }
        }
   

