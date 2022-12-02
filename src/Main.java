public class Main {
    public static void main(String[] args) {

        final int PRODUCERS_QUANT = 1;
        final int CONSUMERS_QUANT = 10;

        News news = new News();

        for (int i = 0; i < PRODUCERS_QUANT; i++) {
            Producer producer = new Producer(news);
            producer.start();
        }

        for (int i = 0; i < CONSUMERS_QUANT; i++) {
            Consumer consumer = new Consumer(news);
            consumer.start();
        }
    }
}