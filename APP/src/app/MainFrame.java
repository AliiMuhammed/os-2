/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author DELL
 */
public class MainFrame extends JFrame {
    private JLabel waitLabel=new JLabel("Wait");
    private JLabel runningLabel=new JLabel("Running");
    private JLabel finishLabel=new JLabel("Finish");
    private JLabel statusLabel=new JLabel("task not completed");
    private JButton startButton=new JButton("start");
    private JButton stopButton=new JButton("stop");
    private   JTable waitTabel = new JTable() ;
    private   JTable runningTabel = new JTable() ;
    private   JTable finishTabel = new JTable() ;

        

    
    public MainFrame(String title){
        super(title);
        setLayout(new GridBagLayout());
        GridBagConstraints gc=new GridBagConstraints();
        gc.fill=GridBagConstraints.NONE;
        
        gc.gridx=0;
        gc.gridy=0;
        gc.weightx=1;
        gc.weighty=1;
        
        add(waitLabel,gc);
        
        gc.gridx=1;
        gc.gridy=0;
        gc.weightx=1;
        gc.weighty=1;
        
        add(runningLabel,gc);
        
        gc.gridx=2;
        gc.gridy=0;
        gc.weightx=1;
        gc.weighty=1;
        
        add(finishLabel,gc);
        

        
        

        
        
        
        
        
        
        
        
        

        waitTabel = new JTable();
        waitTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Waitting"
            }
        ));
        gc.gridx=0;
        gc.gridy=1;
        gc.weightx=1;
        gc.weighty=1;
        waitTabel.setBounds(50, 100, 200, 300);
        JScrollPane w = new JScrollPane(waitTabel);
        add(waitTabel,gc);
        add(w,gc);

//        
//       
        runningTabel = new JTable();
          runningTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Waitting"
            }
        ));
        gc.gridx=1;
        gc.gridy=1;
        gc.weightx=1;
        gc.weighty=1;
        runningTabel.setBounds(50, 100, 200, 300);
        JScrollPane r = new JScrollPane(runningTabel);
        add(runningTabel,gc);
        add(r,gc);
//        
//        
       
        finishTabel = new JTable();
        finishTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Waitting"
            }
        ));
        gc.gridx=2;
        gc.gridy=1;
        gc.weightx=1;
        gc.weighty=1;
        finishTabel.setBounds(30, 40, 200, 300);
        JScrollPane f = new JScrollPane(finishTabel);
        add(finishTabel,gc);
        add(f,gc);
        
       
        gc.gridx=1;
        gc.gridy=2;
        gc.weightx=1;
        gc.weighty=1;
        add(statusLabel,gc);
        
        gc.gridx=1;
        gc.gridy=3;
        gc.weightx=1;
        gc.weighty=1;
        add(startButton,gc);
        startButton.addActionListener(new ActionListener (){
            public void actionPerformed(ActionEvent arg0){
                start();
            }
        });
        
//        stopButton.addActionListener(new ActionListener (){
//            public void actionPerformed(ActionEvent arg0){
//                Stop();
//            }
//        });
        
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void start(){
        
        Random random=new Random();
        Read read = new Read();
        Write write = new Write();
       
        SwingWorker<Boolean, String> worker=new SwingWorker<Boolean, String>(){
            @Override
            protected Boolean doInBackground() throws Exception {
//                for (int i = 1; i < 30; i++) {
//                    Thread.sleep(100);
//                    System.out.println("hello"+i);
//                    
//                    publish(i);
//                }

            for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                  int num=1+random.nextInt(10);
                  if ((num%2==0)){
                      Thread r=  new Thread(read);
                      r.setName("READ"+(i+1));
                      System.out.println("random number :"+num);
                      r.start();
//                      r.sleep(1000);
//////////////////////////ADD to wait tabel/////////////////////////////////////
                    if(r.getState().equals(Thread.State.WAITING)){
                        addToWait(r.getName());
                    }
//////////////////////////ADD to running tabel/////////////////////////////////////
                    if(r.getState().equals(Thread.State.RUNNABLE)){
                        addToRun(r.getName());
                    }
//////////////////////////ADD to Finish tabel/////////////////////////////////////
                    if(r.getState().equals(Thread.State.TERMINATED)){
                        addToFinish(r.getName());
                        
                    }                    
//                        publish(r.getName());
                  }
                  else{
                    Thread w = new Thread(write);
                    w.setName("WRITE"+(i+1));
                    System.out.println("random number :"+num);
                    w.start();
//                    w.sleep(1000);

//////////////////////////ADD to wait tabel/////////////////////////////////////
                    if(w.getState().equals(Thread.State.WAITING)){
                        addToWait(w.getName());
                    }
//////////////////////////ADD to running tabel/////////////////////////////////////
                    if(w.getState().equals(Thread.State.RUNNABLE)){
                        addToRun(w.getName());
                    }
//////////////////////////ADD to Finish tabel/////////////////////////////////////
                    if(w.getState().equals(Thread.State.TERMINATED)){
                        addToFinish(w.getName());
                    } 
//                        publish(w.getName());
                  }
   
              }
               return true;
            }
            
            @Override
            protected void process(List<String> chunks ){
                String value=chunks.get(chunks.size()-1);
 
//                finishLabel.setText("Current Thread: "+value);
            }
            
            
            @Override
            protected void done(){
                try {
                    Boolean status=get();
                    statusLabel.setText("Task complete: "+ status);

                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
            
        };
        
        


        worker.execute();
    }
    
    private void addToWait(String state){
            DefaultTableModel dt=(DefaultTableModel)waitTabel.getModel();
            String []toadd={state};
            dt.addRow(toadd);
        }
    private void addToRun(String state){
            DefaultTableModel dt=(DefaultTableModel)runningTabel.getModel();
            String []toadd={state};
            dt.addRow(toadd);
        }
    private void addToFinish(String state){
            DefaultTableModel dt=(DefaultTableModel)finishTabel.getModel();
            String []toadd={state};
            dt.addRow(toadd);
        }
 
}
