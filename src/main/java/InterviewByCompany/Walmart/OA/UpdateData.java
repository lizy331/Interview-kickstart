//package InterviewByCompany.Walmart.OA;
//
//import com.datastax.driver.core.Cluster;
//import com.datastax.driver.core.Session;
//
//public class UpdateData {
//    public static void main(String[] args) {
//        // Query to update marks
//        String query = "UPDATE results SET marks = 90 WHERE sroll = 5 AND subject_name = 'English';";
//
//        // Creating Cluster object
//        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
//
//        // Creating Session object
//        Session session = cluster.connect("tp");
//
//        // Run the query
//        session.execute(query);
//        System.out.println("Data updated");
//
//        // Close the session and cluster
//        session.close();
//        cluster.close();
//    }
//}
//
