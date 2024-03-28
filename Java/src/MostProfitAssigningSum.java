import java.util.Arrays;

public class MostProfitAssigningSum {

    private class DifProf {
        private int difficulty;
        private int profit;

        public DifProf(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }

        public int getProfit() {
            return this.profit;
        }

        public int getDifficulty() {
            return this.difficulty;
        }
    }

    public int maxProfit(int[] difficulty, int[] profit, int[] worker) {
        int numJobs = difficulty.length;
        assert profit.length == numJobs;

        DifProf[] jobs = new DifProf[numJobs];
        for (int i = 0; i < numJobs; i++) {
            jobs[i] = new DifProf(difficulty[i], profit[i]);
        }
        // sort by difficulty
        Arrays.sort(jobs, (o1, o2) -> Integer.compare(o1.getDifficulty(), o2.getDifficulty()));

        // now create a max prefix sum
        int max = -1;
        int[] prefix = new int[numJobs];
        for (int i = 0; i < numJobs; i++) {
            max = Math.max(max, jobs[i].getProfit());
            prefix[i] = max;
        }

        // for each worker now we add
        int total = 0;
        // sort the workers by difficulty as well
        Arrays.sort(worker);

        // now we have two pointers
        int i = 0, j = 0;
        while (j < worker.length) {
            if (i == prefix.length) {
                total += prefix[i - 1];
                j++;
                continue;
            }
            // can we easily perform the current job
            if (jobs[i].getDifficulty() <= worker[j]) {
                i++;
                continue;
            }
            // else previous job was better
            if (i == 0) {
                total += 0;
            } else {
                total += prefix[i - 1];
            }
            j++;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(new MostProfitAssigningSum().maxProfit(
                new int[] { 85,47,57 },
                new int[] { 24,66,99 },
                new int[] { 40,25,25}));
    }

}
