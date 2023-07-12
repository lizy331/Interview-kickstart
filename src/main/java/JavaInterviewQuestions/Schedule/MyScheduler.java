//package PracticalExperiments.Schedule;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//@Component
//public class MyScheduler {
//    private final YourRepository yourRepository;
//    private final ExecutorService executorService;
//    private final Lock entityLock;
//
//    @Autowired
//    public MyScheduler(YourRepository yourRepository) {
//        this.yourRepository = yourRepository;
//        this.executorService = Executors.newFixedThreadPool(5); // Adjust the pool size as per your requirements
//        this.entityLock = new ReentrantLock();
//    }
//
//    @Scheduled(fixedDelay = 10000) // Specify the scheduling interval
//    public void processEntities() {
//        List<YourEntity> entities = yourRepository.findAll(); // Fetch entities to be processed
//
//        for (YourEntity entity : entities) {
//            executorService.execute(() -> processEntity(entity)); // Submit entity for processing
//        }
//
//        executorService.shutdown();
//        try {
//            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }
//
//    private void processEntity(YourEntity entity) {
//        entityLock.lock(); // Acquire lock on the entity
//        try {
//            // Processing logic for the entity
//            // ...
//
//            // Save the modified entity back to the database
//            yourRepository.save(entity);
//        } finally {
//            entityLock.unlock(); // Release the lock on the entity
//        }
//    }
//}
//
//
//
//
//
