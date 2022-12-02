public class Consumer extends Thread {

    private News news;

    public Consumer(News news) {
        this.news = news;
    }

    @Override
    public void run() {
        while (true) {
            this.news.consume(this.getName());
            try {
                sleep(3000);
            } catch (Exception e) {
                System.out.println("Erro ao tentar fazer a thread 'dormir': " + e.getMessage());
            }
        }
    }
}
