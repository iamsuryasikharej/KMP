import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Test {

    public static void main(String[] args)
        throws InterruptedException, ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        MyRecusiveAction action=new MyRecusiveAction(100);
        ForkJoinTask<Long> l=forkJoinPool.submit(action);

        System.out.println(l.get());
        System.out.println(18%10);

        CompletableFuture<List<Integer>> s=CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return new ArrayList<>(Arrays.asList(1, 2, 3, 4));});
        
                    CompletableFuture<List<Integer>> s2=CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return new ArrayList<>(Arrays.asList(5,6,7,8));});

        // s.thenAccept(x->System.out.println(x));


        CompletableFuture<List<Integer>> z=s.thenCombine(s2, (x,y)->{x.addAll(y);return x;});
        z.thenAccept(x->System.out.println(x));
        System.out.println("hilo");
        Thread.sleep(100000);

    


        // Future f=CompletableFuture.runAsync(()->{
        //     try{
        //     Thread.sleep(4000);
        //     }
        //     catch(Exception e)
        //     {}
        //     System.out.println("hello"+Thread.currentThread().getName());});
       
        
        // System.out.println("In main thread"+Thread.currentThread().getName());
        // Thread.sleep(5000);
        // System.out.println("After Sleeping"+Thread.currentThread().getName());

        // System.out.println(f.get());




    }
}

class MyThd implements Callable {

    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 10000; i++) {}
        return new String("Surya");
    }
}

class MyRecusiveTask extends RecursiveAction {

    int x;

    public MyRecusiveTask(int x) {
        this.x = x;
    }

    @Override
    protected void compute() {
        // TODO Auto-generated method stub

        if (x < 20) {
            System.out.println("processing" + Thread.currentThread());
        } else {
            System.out.println(x + "[][][][][][]");
            int left = x / 2;
            int right = x - left;
            MyRecusiveTask myRecusiveTask = new MyRecusiveTask(left);
            MyRecusiveTask myRecusiveTask2 = new MyRecusiveTask(right);
            // invokeAll(myRecusiveTask,myRecusiveTask2);
            myRecusiveTask.fork();
            myRecusiveTask2.fork();
        }
    }
}

class MyRecusiveAction extends RecursiveTask<Long> {
    long x;

    public MyRecusiveAction(long x)
    {
        this.x=x;
    }

    @Override
    protected Long compute() {


        if(x>20)
        {
            long left=this.x/2;
            long right=x-left;
            MyRecusiveAction myRecusiveAction=new MyRecusiveAction(left);
            MyRecusiveAction myRecusiveAction2=new MyRecusiveAction(right);


            myRecusiveAction.fork();
            myRecusiveAction2.fork();

            long z=myRecusiveAction.join();
            long y=myRecusiveAction2.join();
            
            return z*y;


        }


        System.out.println("returning"+x);
        return x;
        
        
    }
}

