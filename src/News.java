import de.svenjacobs.loremipsum.LoremIpsum;

public class News {

    private final int MAX_NEWS = 10;
    private StringBuffer[] news;
    private int count = 0;
    private int nextIn = 0;
    private int nextOut = 0;
    private LoremIpsum loremIpsum;

    public News() {
        this.news = new StringBuffer[MAX_NEWS];
        loremIpsum = new LoremIpsum();
    }

    public synchronized void consume(String threadName) {
        while (this.count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Erro ao fazer thread esperar: " + threadName);
                System.out.println(e.getMessage());
            }
        }

        System.out.printf("\nConsumidor: \t%s\t - \tNotícia consumida: \t%s", threadName, nextOut);
        System.out.printf("\nNotícia: %s\n\n", news[nextOut]);
        this.news[nextOut] = null;
        count--;

        if (nextOut + 1 == MAX_NEWS) {
            nextOut = 0;
        } else {
            nextOut++;
        }

        notifyAll();
    }

    public synchronized void produce(String threadName) {
        while (this.count == MAX_NEWS) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Erro ao fazer thread esperar: " + threadName);
                System.out.println(e.getMessage());
            }
        }

        StringBuffer newsGenerated = generateLoremIpsum();
        news[nextIn] = newsGenerated;

        System.out.printf("\nProdutor:\t%s\t Notícia produzida: \t%s", threadName, nextIn);

        if (nextIn + 1 == MAX_NEWS) {
            nextIn = 0;
        } else {
            nextIn++;
        }

        count++;
        notifyAll();
    }

    private StringBuffer generateLoremIpsum() {
        return new StringBuffer(loremIpsum.getWords(15));
    }
}
