public class Producer extends Thread {

    private News news;

    public Producer(News news) {
        this.news = news;
    }

    @Override
    public void run() {
        while (true) {
            news.produce(this.getName());

            try {
                sleep(1500);
            } catch (Exception e) {
                System.out.println("Producer\n Erro ao tentar fazer a thread 'dormir': " + e.getMessage());
                System.out.println("Thread: " + this.getName());
            }
        }
    }
}
