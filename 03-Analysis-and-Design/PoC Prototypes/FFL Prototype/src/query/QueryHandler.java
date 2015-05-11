package query;

import java.util.ArrayList;
import java.util.List;

public class QueryHandler {
   private static final int JOIN_MAX_WAIT = 500;

   private List<Query> queries;
   private List<Thread> threads;

   public QueryHandler() {
      queries = new ArrayList<>();
      threads = new ArrayList<>();
   }

   public void addQuery(Query q) {
      queries.add(q);
   }

   /**
    * Run all queries. Each query is run in its own thread.
    */
   public void runAll() {
      for (Query q : queries) {
         Thread t = new Thread(q);
         threads.add(t);
         t.start();
      }
   }

   /**
    * Waits for all queries to finish.
    *
    * @throws QueryTimeoutException if any query has timed out
    */
   public void waitForAll() throws QueryTimeoutException {
      // Iterate through all threads, checking
      // if they have timed out. Reiterate after
      // having waited at most JOIN_MAX_WAIT for
      // any live thread to finish.
      long start = System.currentTimeMillis();
      while (true) {
         long time = System.currentTimeMillis() - start;

         Thread live = null;
         for (int i = 0; i < threads.size(); i++) {
            Thread t = threads.get(i);
            Query q = queries.get(i);

            if (t.isAlive()) {
               live = t;
               if (time > q.getTimeout())
                  abortByTimeout(q);
            }
         }

         if (live != null) {
            try {
               live.join(JOIN_MAX_WAIT);
            } catch (InterruptedException e) {
               // No-op
            }
         } else {
            // All threads have finished
            break;
         }
      }
   }

   /**
    * Aborts all current tasks throwing a <code>QueryTimeoutException</code> for the
    * given <code>Query</code>. All live threads are interrupted.
    *
    * @param q the <code>Query</code> that has timed out
    * @throws QueryTimeoutException
    */
   private void abortByTimeout(Query q) throws QueryTimeoutException {
      for (Thread t : threads) {
         if (t.isAlive())
            t.interrupt();
      }

      throw new QueryTimeoutException(q);
   }
}
