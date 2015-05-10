package query;

public class QueryTimeoutException extends Exception {
   private Query query;

   public QueryTimeoutException(Query q) {
      query = q;
   }

   public Query getQuery() {
      return query;
   }
}
