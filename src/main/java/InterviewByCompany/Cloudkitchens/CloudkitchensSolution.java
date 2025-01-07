package InterviewByCompany.Cloudkitchens;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.*;
import java.io.FileInputStream;
import java.util.Properties;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 use case1: dasher arrived: value > 0
 Courier picked up and delivered " + pickedOrder + " after " + arrivalDelay + " seconds.");

 use case2: dasher arrived: value -> 0, not remove yet
 Courier arrived for " + pickedOrder + " but it was already wasted.");

 use case3: dasher arrived: value -> 0, clearn cron job removed
 Courier arrived for Order ID " + order.id + " but it was already removed or does not exist.");

 case: clearn cron job logging
 "Removed expired order " + order + " from " + shelf.name);

 manage overflow:
 step1: matches
 step2: match full -> put on overflow
 step3: overflow full -> loop all overflow item and find order move to match if have places
 "Moved " + orderToMove + " from Overflow to " + targetShelf.name);
 step4: can not move to match -> random discard order from overflow, put order
 Discarded " + discardedOrder + " from Overflow Shelf to make space.");


 arrivalDelay
 */
// Temperature.java
enum Temperature {
    hot,
    cold,
    frozen,
    overflow
}

// Order.java
class Order {
    String id;
    String name;
    Temperature temp;
    int shelfLife;    // in seconds
    double decayRate;

    Long placementTime; // Epoch milliseconds when the order was placed on the shelf
    double value;

    public Order() {
    }

    public Order(String id, String name, Temperature temp, int shelfLife, double decayRate) {
        this.id = id;
        this.name = name;
        this.temp = temp;
        this.shelfLife = shelfLife;
        this.decayRate = decayRate;
        this.value = 1.0; // Initial value
    }

    /**
     * Calculate current value based on age and shelfDecayModifier.
     *
     * @param shelfDecayModifier Modifier based on shelf type.
     */
    public synchronized void updateValue(int shelfDecayModifier) {
        long currentTime = Instant.now().toEpochMilli();
        long orderAgeMillis = currentTime - placementTime;
        double orderAgeSeconds = orderAgeMillis / 1000.0;
        value = (shelfLife - decayRate * orderAgeSeconds * shelfDecayModifier) / shelfLife;
        if (value < 0) value = 0;
    }

    @Override
    public String toString() {
        return String.format("Order{id='%s', name='%s', temp='%s', value=%.2f}", id, name, temp, value);
    }
}


// Shelf.java
abstract class Shelf {
    String name;
    Temperature temperature;
    int capacity;
    List<Order> orders; // Synchronized list for thread safety
    Logger logger;

    public Shelf(String name, Temperature temperature, int capacity, Logger logger) {
        this.name = name;
        this.temperature = temperature;
        this.capacity = capacity;
        this.orders = Collections.synchronizedList(new ArrayList<>());
        this.logger = logger;
    }

    /**
     * Check if the shelf can accommodate the order.
     *
     * @param order The order to be placed.
     * @return true if the order can be placed, false otherwise.
     */
    public boolean canPlace(Order order) {
        return order.temp == this.temperature && orders.size() < capacity;
    }

    /**
     * Place an order on the shelf.
     *
     * @param order The order to be placed.
     * @return true if placement is successful, false otherwise.
     */
    public boolean placeOrder(Order order, int shelfDecayModifier) {
        if (canPlace(order)) {
            synchronized (orders) {
                if (canPlace(order)) { // Double-checked locking
                    if (order.placementTime == null) {
                        order.placementTime = System.currentTimeMillis(); // 设置放置时间
                    }

                    orders.add(order);

                    // Call updateValue after setting placementTime
                    order.updateValue(shelfDecayModifier);

                    logger.info("Initialized Order ID " + order.id + " with value " + String.format("%.2f", order.value));

                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Remove an order from the shelf.
     *
     * @param orderId The ID of the order to be removed.
     * @return The removed order if successful, null otherwise.
     */
    public Order removeOrder(String orderId) {
        synchronized (orders) {
            for (Order order : orders) {
                if (order.id.equals(orderId)) {
                    orders.remove(order);
                    return order;
                }
            }
        }
        return null;
    }

    /**
     * Get all orders currently on the shelf.
     *
     * @return List of orders.
     */
    public List<Order> getOrders() {
        synchronized (orders) {
            return new ArrayList<>(orders);
        }
    }

    /**
     * Get the number of available spaces on the shelf.
     *
     * @return Number of available spaces.
     */
    public int getAvailableSpace() {
        return capacity - orders.size();
    }
}


// HotShelf.java
class HotShelf extends Shelf {
    public HotShelf(Logger logger) {
        super("Hot Shelf", Temperature.hot, 10, logger);
    }
}

// ColdShelf.java
class ColdShelf extends Shelf {
    public ColdShelf(Logger logger) {
        super("Cold Shelf", Temperature.cold, 10, logger);
    }
}

// FrozenShelf.java
class FrozenShelf extends Shelf {
    public FrozenShelf(Logger logger) {
        super("Frozen Shelf", Temperature.frozen, 10, logger);
    }
}

// OverflowShelf.java
class OverflowShelf extends Shelf {
    public OverflowShelf(Logger logger) {
        super("Overflow Shelf", Temperature.overflow, 15, logger);
    }

    @Override
    public boolean canPlace(Order order) {
        return (order.temp == Temperature.hot || order.temp == Temperature.cold || order.temp == Temperature.frozen) && orders.size() < capacity;
    }
}


// ShelfManager.java
class ShelfManager {
    HotShelf hotShelf;
    ColdShelf coldShelf;
    FrozenShelf frozenShelf;
    OverflowShelf overflowShelf;
    Logger logger;
    private final Random random = new Random();

    public ShelfManager(Logger logger) {
        this.logger = logger;
        this.hotShelf = new HotShelf(logger);
        this.coldShelf = new ColdShelf(logger);
        this.frozenShelf = new FrozenShelf(logger);
        this.overflowShelf = new OverflowShelf(logger);
    }

    /**
     * Place an order on the appropriate shelf.
     *
     * @param order The order to be placed.
     */
    public synchronized void placeOrder(Order order) {
        boolean placed = false;
        Shelf targetShelf = getShelfByTemp(order.temp);

        // 1. Try to place on the matching temperature shelf
        if (targetShelf != null && targetShelf.canPlace(order)) {
            placed = targetShelf.placeOrder(order, 1);
        }

        // 2. If not placed, try to place on Overflow Shelf
        if (!placed) {
            placed = overflowShelf.placeOrder(order, 2);
            if (!placed) {
                // 3. Manage Overflow Shelf
                manageOverflow(order);
            }
        }
    }

    /**
     * Manage the overflow shelf when it's full.
     *
     * @param newOrder The new order to be placed.
     */
    private void manageOverflow(Order newOrder) {
        // Attempt to move an existing order from overflow to its respective shelf
        List<Order> overflowOrders = overflowShelf.getOrders();
        if (!overflowOrders.isEmpty()) {
            // 循环遍历溢出货架中的订单，查找可以移动的订单
            for (Order orderToMove : overflowOrders) {
                Shelf targetShelf = getShelfByTemp(orderToMove.temp);
                if (targetShelf != null && targetShelf.canPlace(orderToMove)) {
                    overflowShelf.removeOrder(orderToMove.id);
                    targetShelf.placeOrder(orderToMove, 1); // placementTime 不会重置
                    logger.info("Moved " + orderToMove + " from Overflow to " + targetShelf.name);
                    // 现在将新订单放置到溢出货架
                    boolean placedInOverflow = overflowShelf.placeOrder(newOrder, 2);
                    if (placedInOverflow) {
                        logger.info("Placed " + newOrder + " on Overflow Shelf after moving an order.");
                        return;
                    }
                }
            }
        }

        // 如果无法移动任何订单，则从溢出货架中随机丢弃一个订单
        if (overflowShelf.getOrders().size() >= overflowShelf.capacity) {
            List<Order> currentOverflowOrders = overflowShelf.getOrders();
            if (!currentOverflowOrders.isEmpty()) {
                Order discardedOrder = currentOverflowOrders.get(random.nextInt(currentOverflowOrders.size()));
                overflowShelf.removeOrder(discardedOrder.id);
                logger.warning("Discarded " + discardedOrder + " from Overflow Shelf to make space.");
                // 将新订单放置到溢出货架
                boolean placedInOverflow = overflowShelf.placeOrder(newOrder, 2);
                if (placedInOverflow) {
                    logger.info("Placed " + newOrder + " on Overflow Shelf after discarding an order.");
                }
            }
        }
    }


    /**
     * Get the appropriate shelf based on temperature.
     *
     * @param temp The temperature category of the order.
     * @return The corresponding Shelf object.
     */
    private Shelf getShelfByTemp(Temperature temp) {
        if (temp == null) {
            logger.warning("Order has null temperature. Cannot determine shelf.");
            return null;
        }
        switch (temp) {
            case hot:
                return hotShelf;
            case cold:
                return coldShelf;
            case frozen:
                return frozenShelf;
            default:
                return null;
        }
    }

    /**
     * Remove an order from any shelf.
     *
     * @param orderId The ID of the order to be removed.
     * @param removedOrderHolder A single-element array to hold the removed order.
     * @return The name of the shelf from which the order was removed, or null if not found.
     */
    public synchronized String removeOrder(String orderId, Order[] removedOrderHolder) {
        Order removed = hotShelf.removeOrder(orderId);
        if (removed != null) {
            removedOrderHolder[0] = removed;
            return "Hot Shelf";
        }

        removed = coldShelf.removeOrder(orderId);
        if (removed != null) {
            removedOrderHolder[0] = removed;
            return "Cold Shelf";
        }

        removed = frozenShelf.removeOrder(orderId);
        if (removed != null) {
            removedOrderHolder[0] = removed;
            return "Frozen Shelf";
        }

        removed = overflowShelf.removeOrder(orderId);
        if (removed != null) {
            removedOrderHolder[0] = removed;
            return "Overflow Shelf";
        }

        return null;
    }

    /**
     * Get the current state of all shelves.
     *
     * @return A map of shelf names to their current orders.
     */
    public synchronized Map<String, List<Order>> getShelfStatus() {
        Map<String, List<Order>> status = new HashMap<>();
        status.put(hotShelf.name, hotShelf.getOrders());
        status.put(coldShelf.name, coldShelf.getOrders());
        status.put(frozenShelf.name, frozenShelf.getOrders());
        status.put(overflowShelf.name, overflowShelf.getOrders());
        return status;
    }

    /**
     * Remove expired orders (value <= 0) from all shelves.
     */
    public synchronized void removeExpiredOrders() {
        List<Shelf> shelves = Arrays.asList(hotShelf, coldShelf, frozenShelf, overflowShelf);
        for (Shelf shelf : shelves) {
            List<Order> orders = shelf.getOrders();
            for (Order order : orders) {
                // Determine shelfDecayModifier based on shelf name
                int shelfDecayModifier = getShelfDecayModifier(shelf.name);
                order.updateValue(shelfDecayModifier);
                if (order.value <= 0) {
                    shelf.removeOrder(order.id);
                    logger.warning("Removed expired order " + order + " from " + shelf.name);
                }
            }
        }
    }

    /**
     * Determine the shelfDecayModifier based on the shelf name.
     *
     * @param shelfName The name of the shelf.
     * @return The decay modifier (1 for normal shelves, 2 for overflow).
     */
    private int getShelfDecayModifier(String shelfName) {
        if (shelfName.equalsIgnoreCase("Overflow Shelf")) {
            return 2;
        } else {
            return 1;
        }
    }
}


// Courier.java
class Courier implements Runnable {
    private final Order order;
    private final ShelfManager shelfManager;
    private final Random random = new Random();
    private final Logger logger;

    public Courier(Order order, ShelfManager shelfManager, Logger logger) {
        this.order = order;
        this.shelfManager = shelfManager;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            // Simulate courier arrival time between 2-6 seconds
            int arrivalDelay = 2 + random.nextInt(5); // 2 to 6 seconds
            TimeUnit.SECONDS.sleep(arrivalDelay);

            // Upon arrival, attempt to pick up the order
            Order[] removedOrderHolder = new Order[1];
            String shelfName = shelfManager.removeOrder(order.id, removedOrderHolder);
            Order pickedOrder = removedOrderHolder[0];

            if (pickedOrder != null) {
                // Determine shelfDecayModifier based on shelf name
                int shelfDecayModifier = getShelfDecayModifier(shelfName);
                pickedOrder.updateValue(shelfDecayModifier);
                if (pickedOrder.value > 0) {
                    logger.info("Courier picked up and delivered " + pickedOrder + " after " + arrivalDelay + " seconds.");
                } else {
                    logger.warning("Courier arrived for " + pickedOrder + " but it was already wasted.");
                }
            } else {
                logger.warning("Courier arrived for Order ID " + order.id + " but it was already removed or does not exist.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.severe("Courier interrupted while delivering Order ID " + order.id);
        }
    }

    /**
     * Determine the shelfDecayModifier based on the shelf name.
     *
     * @param shelfName The name of the shelf.
     * @return The decay modifier (1 for normal shelves, 2 for overflow).
     */
    private int getShelfDecayModifier(String shelfName) {
        if (shelfName.equalsIgnoreCase("Overflow Shelf")) {
            return 2;
        } else {
            return 1;
        }
    }
}

// OrderProducer.java
class OrderProducer implements Runnable {
    private final List<Order> orders;
    private final ShelfManager shelfManager;
    private final ExecutorService courierPool;
    private final int ingestionRate; // orders per second
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    private final Logger logger;

    public OrderProducer(String filePath, ShelfManager shelfManager, ExecutorService courierPool, int ingestionRate, Logger logger) throws IOException {
        File orderFile = new File(filePath);
        if (!orderFile.exists()) {
            logger.severe("Order file does not exist at path: " + filePath);
            throw new IOException("Order file does not exist at path: " + filePath);
        }

        Gson gson = new Gson();
        Type orderListType = new TypeToken<List<Order>>() {}.getType();
        try (FileReader reader = new FileReader(orderFile)) {
            this.orders = gson.fromJson(reader, orderListType);
        }
        this.shelfManager = shelfManager;
        this.courierPool = courierPool;
        this.ingestionRate = ingestionRate;
        this.logger = logger;
    }

    @Override
    public void run() {
        int index = 0;
        long intervalMillis = 1000 / ingestionRate;

        while (isRunning.get() && index < orders.size()) {
            Order order = orders.get(index);
            shelfManager.placeOrder(order);

            // Dispatch a courier for the order
            Courier courier = new Courier(order, shelfManager, logger);
            courierPool.execute(courier);

            index++;

            try {
                TimeUnit.MILLISECONDS.sleep(intervalMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.severe("OrderProducer interrupted.");
                break;
            }
        }

        isRunning.set(false);
        logger.info("All orders have been ingested.");
    }

    public void stop() {
        isRunning.set(false);
    }
}


// KitchenOrderSystem.java
class KitchenOrderSystem {
    private static final Logger logger = Logger.getLogger(KitchenOrderSystem.class.getName());

    //    // 全局变量: 订单文件路径
//    private static final String FILE_PATH = "/Users/xinyidai/Documents/work/leetcode/order.json"; // 请根据实际路径修改
//    private static final String LOG_FILE_PATH = "logs/application.log"; // 日志文件路径
    // 全局变量: 订单文件路径和日志文件路径
    private static String FILE_PATH;
    private static String LOG_FILE_PATH;
    private static int ingestionRate;
    private static int cleaningIntervalSeconds;

    public static void main(String[] args) {
        // 配置
//        int ingestionRate = 2; // Orders per second (configurable)
//        int cleaningIntervalSeconds = 5; // 清理间隔（秒）
        loadConfig("/Users/liziyang/workplace/leetcode/leetcode/src/main/java/InterviewByCompany/Cloudkitchens/config.properties");
        setupLogger(LOG_FILE_PATH);

        // 初始化 ShelfManager
        ShelfManager shelfManager = new ShelfManager(logger);

        // 初始化 ExecutorService for couriers
        ExecutorService courierPool = Executors.newCachedThreadPool();

        // 初始化并启动 OrderProducer
        OrderProducer producer = null;
        Thread producerThread = null;
        try {
            producer = new OrderProducer(FILE_PATH, shelfManager, courierPool, ingestionRate, logger);
            producerThread = new Thread(producer);
            producerThread.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to start OrderProducer: " + e.getMessage(), e);
            System.exit(1);
        }

        // 初始化并启动后台清理线程
        ScheduledExecutorService cleanerService = Executors.newSingleThreadScheduledExecutor();
        cleanerService.scheduleAtFixedRate(() -> {
            logger.info("Running scheduled cleanup of expired orders...");
            shelfManager.removeExpiredOrders();
        }, cleaningIntervalSeconds, cleaningIntervalSeconds, TimeUnit.SECONDS);

        // 打印当前工作目录
        logger.info("Current working directory: " + System.getProperty("user.dir"));

        // 等待 producer 完成
        try {
            producerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.severe("Main thread interrupted.");
        }

        // 停止后台清理线程
        cleanerService.shutdown();
        try {
            if (!cleanerService.awaitTermination(10, TimeUnit.SECONDS)) {
                cleanerService.shutdownNow();
            }
        } catch (InterruptedException e) {
            cleanerService.shutdownNow();
        }

        // 优雅地关闭 courier pool
        courierPool.shutdown();
        try {
            if (!courierPool.awaitTermination(60, TimeUnit.SECONDS)) {
                courierPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            courierPool.shutdownNow();
        }

        // 最后一次清理
        shelfManager.removeExpiredOrders();

        // 显示最终货架状态
        logger.info("\nFinal Shelf Status:");
        Map<String, List<Order>> shelfStatus = shelfManager.getShelfStatus();
        for (Map.Entry<String, List<Order>> entry : shelfStatus.entrySet()) {
            logger.info(entry.getKey() + " (" + entry.getValue().size() + " orders):");
            for (Order order : entry.getValue()) {
                int shelfDecayModifier = getShelfDecayModifier(entry.getKey());
                order.updateValue(shelfDecayModifier);
                logger.info("  - " + order);
            }
        }

        logger.info("\nSystem terminating.");
    }

    /**
     * Load configuration from a properties file.
     *
     * @param configFilePath Path to the configuration file.
     */
    private static void loadConfig(String configFilePath) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);
            FILE_PATH = properties.getProperty("filePath");
            LOG_FILE_PATH = properties.getProperty("logFilePath");
            ingestionRate = Integer.parseInt(properties.getProperty("ingestionRate"));
            cleaningIntervalSeconds = Integer.parseInt(properties.getProperty("cleaningIntervalSeconds"));
        } catch (IOException e) {
            System.err.println("Failed to load configuration: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Setup the logger to log messages to both console and a file.
     *
     * @param logFilePath The path to the log file.
     */
    private static void setupLogger(String logFilePath) {
        try {
            // 移除默认的处理器
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }

            // 定义日志目录
            Path logDir = Paths.get("/Users/liziyang/workplace/leetcode/leetcode/src/main/java/InterviewByCompany/Cloudkitchens/logs");
            if (!Files.exists(logDir)) {
                Files.createDirectories(logDir);
                System.out.println("Created log directory at: " + logDir.toAbsolutePath());
            }

            // 生成带有时间戳的唯一日志文件名
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String timestamp = LocalDateTime.now().format(formatter);
            String uniqueLogFilePath = logDir.resolve("application-" + timestamp + ".log").toString();

            // 初始化 FileHandler，不使用轮转参数
            FileHandler fileHandler = new FileHandler(uniqueLogFilePath, false);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);

            // 初始化 ConsoleHandler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.INFO);

            // 将处理器添加到 logger
            logger.addHandler(fileHandler);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.ALL);

            System.out.println("Logging to: " + uniqueLogFilePath);

        } catch (IOException e) {
            System.err.println("Failed to setup logger handler.");
            e.printStackTrace();
        }
    }

    /**
     * Determine the shelfDecayModifier based on the shelf name.
     *
     * @param shelfName The name of the shelf.
     * @return The decay modifier (1 for normal shelves, 2 for overflow).
     */
    private static int getShelfDecayModifier(String shelfName) {
        if (shelfName.equalsIgnoreCase("Overflow Shelf")) {
            return 2;
        } else {
            return 1;
        }
    }
}
