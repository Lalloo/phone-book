package util;

public class Timer {
    private long start;
    private long end;

    public void start() {
        this.start = System.currentTimeMillis();
    }

    public void stop() {
        this.end = System.currentTimeMillis();
    }

    public void printInfo(String info, long timeLapse) {
        long[] timeFrame = getTimeLapse(timeLapse);
        System.out.printf("%s Time taken: %d min. %d sec. %d ms.\n", info, timeFrame[0], timeFrame[1], timeFrame[2]);
    }

    public long[] getTimeLapse(long lapse) {
        long[] result = new long[3];
        long minutes = lapse / 1_000 / 60;
        result[0] = minutes;
        long seconds = (lapse / 1_000) % 60;
        result[1] = seconds;
        long ms = lapse % 1000;
        result[2] = ms;
        return result;
    }

    public long getTimeLapse() {
        return end - start;
    }

    public String getTimeLapseAsString(long timeLapse) {
        long[] timeFrame = getTimeLapse(timeLapse);
        return String.format("%d min. %d sec. %d ms.", timeFrame[0], timeFrame[1], timeFrame[2]);
    }

}
